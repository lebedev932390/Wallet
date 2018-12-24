package com.evgeny.lebed.wallet.Presenter;

import android.content.Context;
import android.util.Log;

import com.evgeny.lebed.wallet.Class.WalletMethods;
import com.evgeny.lebed.wallet.Class.Payment;
import com.evgeny.lebed.wallet.Interface.ContractModel;
import com.evgeny.lebed.wallet.Interface.ContractPresenter;
import com.evgeny.lebed.wallet.Interface.ContractView;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class PresenterAmount implements ContractPresenter.Amount {

    private ContractView.Amount view;
    private ContractModel model;
    private Context context;


    private boolean todayIsChosen = true;
    private String language;
    private SimpleDateFormat dateFormatDMYHM = new SimpleDateFormat("dd.MM.yyyy  HH:mm");

    private Integer lastDayTempInt, lastDayTempDec, lastMonthTempInt, lastMonthTempDec;

    private List<Payment> listOfPayments = new ArrayList<>();

    private Long today, monthStart;
    private int daysLeft;
    private Double budget, dayBudget, monthBudget, total = 0.0, tempDayBudget = 0.0;

    private Calendar calendar;
    private int beginningOfTheMonth;


    public PresenterAmount(ContractView.Amount view, ContractModel model, Context context) {
        this.view = view;
        this.model = model;
        this.context = context;
        budget = model.getBudget();
        language = model.getLanguage();
        beginningOfTheMonth = model.getStartDate();

        initListOfPayments();

        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        view.showDate(WalletMethods.formatDate(calendar.getTimeInMillis(), context, model.getLanguage()));

        int maxDate;
        calendar = Calendar.getInstance();
        nullCalendar();


        if (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) < beginningOfTheMonth) {
            if (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
                monthStart = calendar.getTime().getTime();
                calendar.add(Calendar.MONTH, 1);
                maxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                daysLeft = maxDate;
                nullCalendar();


            } else {
                calendar.add(Calendar.MONTH, -1);
                if (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) < beginningOfTheMonth) {
                    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                } else {
                    calendar.set(Calendar.DAY_OF_MONTH, beginningOfTheMonth);
                }

                nullCalendar();
                monthStart = calendar.getTime().getTime();
                calendar = Calendar.getInstance();
                nullCalendar();
                maxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                daysLeft = maxDate - calendar.get(Calendar.DAY_OF_MONTH);

            }

            calendar = Calendar.getInstance();


        } else if (calendar.get(Calendar.DAY_OF_MONTH) < beginningOfTheMonth) {
            calendar.add(Calendar.MONTH, -1);
            if (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) < beginningOfTheMonth) {
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else {
                calendar.set(Calendar.DAY_OF_MONTH, beginningOfTheMonth);
            }

            nullCalendar();
            monthStart = calendar.getTime().getTime();
            calendar = Calendar.getInstance();
            nullCalendar();
            maxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            daysLeft = beginningOfTheMonth - calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, beginningOfTheMonth);
            monthStart = calendar.getTime().getTime();
            maxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            calendar = Calendar.getInstance();
            nullCalendar();
            if (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
                daysLeft = beginningOfTheMonth;
            } else {
                daysLeft = maxDate - calendar.get(Calendar.DAY_OF_MONTH) + beginningOfTheMonth;
            }


        }

        Log.e("daysLeft", Integer.toString(daysLeft));

        calendar = Calendar.getInstance();
        nullCalendar();

        today = calendar.getTime().getTime();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, maxDate);

        dayBudget = calculateDayBudget();
        monthBudget = calculateMonthBudget();
        ;


        view.showCurrency(model.getCurrency());
        view.showAmount("0", ".00");

        view.setDayAndMonthBackgrounds(dayBudget > 0, monthBudget > 0);
        if (dayBudget > 0.00) {
            view.countIntAnimationDay(0, Integer.parseInt(countInt(dayBudget)), true);
            view.countDecimalAnimationDay(0, Integer.parseInt(countDecimals(dayBudget)));
            lastDayTempInt = Integer.parseInt(countInt(dayBudget));
        } else if (dayBudget == 0) {
            view.countIntAnimationDay(0, Integer.parseInt("-" + countInt(dayBudget)), false);
            view.countDecimalAnimationDay(0, Integer.parseInt(countDecimals(dayBudget)));
            lastDayTempInt = Integer.parseInt("-" + countInt(dayBudget));
        } else {
            view.countIntAnimationDay(0, Integer.parseInt("-" + countInt(dayBudget)), false);
            view.countDecimalAnimationDay(0, Integer.parseInt(countDecimals(dayBudget)));
            lastDayTempInt = Integer.parseInt("-" + countInt(dayBudget));
        }
        if (monthBudget > 0.00) {
            view.countIntAnimationMonth(0, Integer.parseInt(countInt(monthBudget)), true);
            view.countDecimalAnimationMonth(0, Integer.parseInt(countDecimals(monthBudget)));
            lastMonthTempInt = Integer.parseInt(countInt(monthBudget));
        } else if (monthBudget == 0) {
            view.countIntAnimationMonth(0, Integer.parseInt("-" + countInt(monthBudget)), false);
            view.countDecimalAnimationMonth(0, Integer.parseInt(countDecimals(monthBudget)));
            lastMonthTempInt = Integer.parseInt("-" + countInt(monthBudget));
        } else {
            view.countIntAnimationMonth(0, Integer.parseInt("-" + countInt(monthBudget)), false);
            view.countDecimalAnimationMonth(0, Integer.parseInt(countDecimals(monthBudget)));
            lastMonthTempInt = Integer.parseInt("-" + countInt(monthBudget));
        }
        lastDayTempDec = Integer.parseInt(countDecimals(dayBudget));
        lastMonthTempDec = Integer.parseInt(countDecimals(monthBudget));
    }

    private void nullCalendar() {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }


    @Override
    public void numberClicked(Double number) {
        Double checkMax = total * 10;

        checkMax += number / 100;
        if (checkMax < 1000000) {
            if (total == 0) {
                total = total + number / 100;
                total = Math.round(total * 100.0) / 100.00;
            } else {
                total = total * 1000;
                total += number;
                total = total / 100;
                total = Math.round(total * 100.0) / 100.00;
            }
            view.showAmount(countInt(total) + ".", countDecimals(total));

            showInView();
        }

        Log.e("total", Double.toString(total));
    }

    private void showInView() {

        Double tempMonthBudget;
        if (todayIsChosen) {
            tempDayBudget = dayBudget - total;
            tempDayBudget = Math.round(tempDayBudget * 100.0) / 100.00;
        } else {
            tempDayBudget = calculateTotalPast();
            tempDayBudget = Math.round(tempDayBudget * 100.0) / 100.00;
        }

        tempMonthBudget = monthBudget - total;
        tempMonthBudget = Math.round(tempMonthBudget * 100.0) / 100.00;

        if (tempDayBudget > 0.00) {
            view.countIntAnimationDay(lastDayTempInt, Integer.parseInt(countInt(tempDayBudget)), true);
            view.countDecimalAnimationDay(lastDayTempDec, Integer.parseInt(countDecimals(tempDayBudget)));
            lastDayTempInt = Integer.parseInt(countInt(tempDayBudget));
            lastDayTempDec = Integer.parseInt(countDecimals(tempDayBudget));
        } else if (tempDayBudget == 0) {
            view.countIntAnimationDay(lastDayTempInt, Integer.parseInt(countInt(tempDayBudget)), false);
            view.countDecimalAnimationDay(lastDayTempDec, Integer.parseInt(countDecimals(tempDayBudget)));
            lastDayTempInt = Integer.parseInt(countInt(tempDayBudget));
            lastDayTempDec = Integer.parseInt(countDecimals(tempDayBudget));

        } else {
            view.countIntAnimationDay(lastDayTempInt, Integer.parseInt("-" + countInt(tempDayBudget)), false);
            view.countDecimalAnimationDay(lastDayTempDec, Integer.parseInt(countDecimals(tempDayBudget)));
            lastDayTempInt = Integer.parseInt("-" + countInt(tempDayBudget));
            lastDayTempDec = Integer.parseInt(countDecimals(tempDayBudget));
        }
        if (tempMonthBudget > 0.00) {
            view.countIntAnimationMonth(lastMonthTempInt, Integer.parseInt(countInt(tempMonthBudget)), true);
            view.countDecimalAnimationMonth(lastMonthTempDec, Integer.parseInt(countDecimals(tempMonthBudget)));
            lastMonthTempInt = Integer.parseInt(countInt(tempMonthBudget));
            lastMonthTempDec = Integer.parseInt(countDecimals(tempMonthBudget));
        } else if (tempMonthBudget == 0) {
            view.countIntAnimationMonth(lastMonthTempInt, Integer.parseInt(countInt(tempMonthBudget)), false);
            view.countDecimalAnimationMonth(lastMonthTempDec, Integer.parseInt(countDecimals(tempMonthBudget)));
            lastMonthTempInt = Integer.parseInt(countInt(tempMonthBudget));
            lastMonthTempDec = Integer.parseInt(countDecimals(tempMonthBudget));
        } else {
            view.countIntAnimationMonth(lastMonthTempInt, Integer.parseInt("-" + countInt(tempMonthBudget)), false);
            view.countDecimalAnimationMonth(lastMonthTempDec, Integer.parseInt(countDecimals(tempMonthBudget)));
            lastMonthTempInt = Integer.parseInt("-" + countInt(tempMonthBudget));
            lastMonthTempDec = Integer.parseInt(countDecimals(tempMonthBudget));
        }
    }

    private String countInt(Double d) {
        if (d < 0) {
            d = d * (-1);
        }
        return Integer.toString(d.intValue());
    }

    private String countDecimals(Double d) {

        String x = Double.toString(d);
        x = x.substring(x.lastIndexOf(".") + 1);
        if (x.length() == 1) {
            return x + "0";
        } else {
            return x;
        }

    }


    @Override
    public void deleteClicked() {

        if (total != 0) {

            total = (total * 100 - (total * 100) % 10) / 1000;

            view.showAmount(countInt(total) + ".", countDecimals(total));

            showInView();


        }
    }

    @Override
    public void addPaymentClicked(Long date, String amount, String note) {

        if (Double.parseDouble(amount) == 0) {
            view.amountIsEmpty();
        } else {
            if (note.isEmpty()) {
                note = "";
            }
            dateFormatDMYHM.format(date);
            model.insertPayment(date, Double.parseDouble(amount), note);
            view.paymentAdded();
        }
    }

    @Override
    public void dateChosen(Long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (calendar.getTimeInMillis() != today) {
            tempDayBudget = calculateTotalPast();
            tempDayBudget = Math.round(tempDayBudget * 100.0) / 100.00;

            todayIsChosen = false;
        } else {
            tempDayBudget = dayBudget - total;
            tempDayBudget = Math.round(tempDayBudget * 100.0) / 100.00;

            todayIsChosen = true;
        }

        view.showDate(WalletMethods.formatDate(calendar.getTimeInMillis(), context, model.getLanguage()));
        showInView();
    }

    @Override
    public void choseDateClicked() {
        view.showDatePicker(monthStart, today);
    }

    @Override
    public String getLanguage() {
        return model.getLanguage();
    }

    private void initListOfPayments() {

        Gson gson = new Gson();

        List<String> listOfJson = model.getPayments();


        for (int i = 0; i < listOfJson.size(); i++) {
            listOfPayments.add(gson.fromJson(listOfJson.get(i), Payment.class));
        }


        Collections.sort(listOfPayments);
        Collections.reverse(listOfPayments);


    }

    private Double calculateDayBudget() {
        Double monthlyExpenses = 0.00;
        Long paymentDate;

        for (int i = 0; i < listOfPayments.size(); i++) {
            paymentDate = listOfPayments.get(i).getDate();
            if (paymentDate >= monthStart && paymentDate < today) {
                monthlyExpenses += listOfPayments.get(i).getAmount();
            }
        }

        Double result = (budget - monthlyExpenses) / daysLeft;
        for (int i = 0; i < listOfPayments.size(); i++) {
            calendar.setTimeInMillis(listOfPayments.get(i).getDate());
            nullCalendar();
            paymentDate = calendar.getTimeInMillis();
            if (today.equals(paymentDate)) {
                result -= listOfPayments.get(i).getAmount();
            }
        }
        return Math.round(result * 100.0) / 100.00;


    }

    private Double calculateTotalPast() {
        Double monthlyExpenses = 0.00;
        Long paymentDate;

        for (int i = 0; i < listOfPayments.size(); i++) {
            paymentDate = listOfPayments.get(i).getDate();
            if (paymentDate >= monthStart && paymentDate < today) {
                monthlyExpenses += listOfPayments.get(i).getAmount();
            }
        }

        Double result = (budget - monthlyExpenses - total) / daysLeft;
        for (int i = 0; i < listOfPayments.size(); i++) {
            calendar.setTimeInMillis(listOfPayments.get(i).getDate());
            nullCalendar();
            paymentDate = calendar.getTimeInMillis();
            if (today.equals(paymentDate)) {
                result -= listOfPayments.get(i).getAmount();
            }
        }
        return Math.round(result * 100.0) / 100.00;
    }


    private Double calculateMonthBudget() {
        Double monthlyExpenses = 0.00;
        Long paymentDate;

        for (int i = 0; i < listOfPayments.size(); i++) {
            paymentDate = listOfPayments.get(i).getDate();
            if (paymentDate >= monthStart) {
                monthlyExpenses += listOfPayments.get(i).getAmount();
            }
        }

        return Math.round((budget - monthlyExpenses) * 100.0) / 100.00;
    }


}
