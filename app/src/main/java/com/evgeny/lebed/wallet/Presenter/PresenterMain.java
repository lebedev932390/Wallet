package com.evgeny.lebed.wallet.Presenter;

import android.content.Context;
import android.util.Log;

import com.evgeny.lebed.wallet.Class.Payment;
import com.evgeny.lebed.wallet.Class.WalletMethods;
import com.evgeny.lebed.wallet.Interface.ContractModel;
import com.evgeny.lebed.wallet.Interface.ContractPresenter;
import com.evgeny.lebed.wallet.Interface.ContractView;
import com.evgeny.lebed.wallet.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class PresenterMain implements ContractPresenter.Main {

    List<Payment> listOfPayments = new ArrayList<>();

    private ContractView.Main view;
    private ContractModel model;
    private Context context;
    private Double budget;
    private String currency;
    private Long today;
    private Long monthStart;
    private Long monthEnd;
    private int maxDate;
    private int daysLeft;


    private Calendar calendar;
    private int beginningOfTheMonth;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    private Double dayBudget;
    private Double monthBudget;
    private String language;



    public PresenterMain(ContractView.Main view, ContractModel model, Context context) {
        this.view = view;
        this.model = model;
        this.context = context;


    }

    @Override
    public void initPresenter(){
        if (!model.getInitialSetup()) {
            view.startInitialSetupActivity();
        }
        initListOfPayments();

        language = model.getLanguage();
        currency = model.getCurrency();
        budget = model.getBudget();
        beginningOfTheMonth = model.getStartDate();
        Log.e("startDate",Integer.toString(beginningOfTheMonth));

        calendar = Calendar.getInstance();
        nullCalendar();


        if (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) < beginningOfTheMonth) {
            if (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
                monthStart = calendar.getTime().getTime();
                calendar.add(Calendar.MONTH, 1);
                maxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                daysLeft = maxDate;
                nullCalendar();
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                monthEnd = calendar.getTimeInMillis();


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
                nullCalendar();
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                monthEnd = calendar.getTimeInMillis();

            }

            calendar = Calendar.getInstance();


        }
        else if (calendar.get(Calendar.DAY_OF_MONTH) < beginningOfTheMonth ) {
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
            calendar = Calendar.getInstance();
            nullCalendar();
            calendar.set(Calendar.DAY_OF_MONTH, beginningOfTheMonth);
            nullCalendar();
            monthEnd = calendar.getTimeInMillis();
        }
        else {
            calendar.set(Calendar.DAY_OF_MONTH, beginningOfTheMonth);
            monthStart = calendar.getTime().getTime();
            maxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            calendar = Calendar.getInstance();
            nullCalendar();
            if (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
                daysLeft = beginningOfTheMonth;
            }
            else {
                daysLeft = maxDate - calendar.get(Calendar.DAY_OF_MONTH) + beginningOfTheMonth;
            }
            calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, beginningOfTheMonth);
            calendar.add(Calendar.MONTH,+1);
            nullCalendar();
            monthEnd = calendar.getTimeInMillis();


        }

        calendar = Calendar.getInstance();
        nullCalendar();

        //maxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        today = calendar.getTime().getTime();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //monthStart = calendar.getTime().getTime();
        calendar.set(Calendar.DAY_OF_MONTH, maxDate);

        dayBudget = calculateDayBudget();
        monthBudget = calculateMonthBudget();

        view.showCurrency(model.getCurrency());
        sendBudgetsToViewInitially(dayBudget);
    }

    @Override
    public Locale getLocale() {
        if (model.getLanguage().equals(WalletMethods.ENGLISH)){
            return new Locale("en");
        }
        else {
            return new Locale("ru");
        }
    }

    private void nullCalendar() {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private void sendBudgetsToViewInitially(Double day) {
        boolean isPositive;
        String budgetInt, budgetDecimal;
        if (dayBudget > 0.00) {
            isPositive = true;
            budgetInt = countInt(day);
            budgetDecimal = countDecimals(day);

        } else if (dayBudget == 0) {
            isPositive = false;
            budgetInt = "-" + countInt(day);
            budgetDecimal = countDecimals(day);
        } else {
            isPositive = false;
            budgetInt = "-" + countInt(day);
            budgetDecimal = countDecimals(day);

        }

        Log.e("int", budgetInt);
        Log.e("dec", budgetDecimal);
        view.showDayBudgetInitially(budgetInt, budgetDecimal, isPositive);
        view.countIntAnimation(0, Integer.parseInt(budgetInt), dayBudget > 0);
        view.countDecimalAnimation(0, Integer.parseInt(budgetDecimal));

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


    @Override
    public void newPaymentClicked() {
        view.startAmountActivity();
    }

    @Override
    public void paymentHistoryClicked() {
        view.startPaymentHistoryActivity();
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public void monthSelected() {

        if (monthBudget > 0.00) {
            if (dayBudget < 0) {
                view.countIntAnimation(-Integer.parseInt(countInt(dayBudget)), Integer.parseInt(countInt(monthBudget)), true);

            } else {
                view.countIntAnimation(Integer.parseInt(countInt(dayBudget)), Integer.parseInt(countInt(monthBudget)), true);
            }
            view.countDecimalAnimation(Integer.parseInt(countDecimals(dayBudget)), Integer.parseInt(countDecimals(monthBudget)));

        } else if (monthBudget == 0) {
            if (dayBudget < 0) {
                view.countIntAnimation(-Integer.parseInt(countInt(dayBudget)), Integer.parseInt(countInt(monthBudget)), false);

            } else {

                view.countIntAnimation(Integer.parseInt(countInt(dayBudget)), Integer.parseInt(countInt(monthBudget)), false);
            }
            view.countDecimalAnimation(Integer.parseInt(countDecimals(dayBudget)), Integer.parseInt(countDecimals(monthBudget)));

        } else {
            if (dayBudget < 0) {
                view.countIntAnimation(-Integer.parseInt(countInt(dayBudget)), Integer.parseInt("-" + countInt(monthBudget)), false);

            } else {

                view.countIntAnimation(Integer.parseInt(countInt(dayBudget)), Integer.parseInt("-" + countInt(monthBudget)), false);
            }
            view.countDecimalAnimation(Integer.parseInt(countDecimals(dayBudget)), Integer.parseInt(countDecimals(monthBudget)));

        }
    }

    @Override
    public void daySelected() {
        if (dayBudget > 0.00) {

            if (monthBudget < 0) {
                view.countIntAnimation(-Integer.parseInt(countInt(monthBudget)), Integer.parseInt(countInt(dayBudget)), true);

            } else {

                view.countIntAnimation(Integer.parseInt(countInt(monthBudget)), Integer.parseInt(countInt(dayBudget)), true);
            }
            view.countDecimalAnimation(Integer.parseInt(countDecimals(monthBudget)), Integer.parseInt(countDecimals(dayBudget)));


        } else if (dayBudget == 0) {
            if (monthBudget < 0) {
                view.countIntAnimation(-Integer.parseInt(countInt(monthBudget)), Integer.parseInt(countInt(dayBudget)), false);
            } else {

                view.countIntAnimation(Integer.parseInt(countInt(monthBudget)), Integer.parseInt(countInt(dayBudget)), false);
            }
            view.countDecimalAnimation(Integer.parseInt(countDecimals(monthBudget)), Integer.parseInt(countDecimals(dayBudget)));


        } else {
            if (monthBudget < 0) {
                view.countIntAnimation(-Integer.parseInt(countInt(monthBudget)), Integer.parseInt("-" + countInt(dayBudget)), false);

            } else {

                view.countIntAnimation(Integer.parseInt(countInt(monthBudget)), Integer.parseInt("-" + countInt(dayBudget)), false);
            }
            view.countDecimalAnimation(Integer.parseInt(countDecimals(monthBudget)), Integer.parseInt(countDecimals(dayBudget)));

        }

    }

    @Override
    public String getDayStatus() {
        if (dayBudget <=0){
            return context.getString(R.string.out_of_budget);

        }
        else {
            return context.getString(R.string.left_today);
        }
    }

    @Override
    public String getMonthStatus() {
        if (monthBudget <=0){
            return context.getString(R.string.out_of_budget);
        }

        else {
            return context.getString(R.string.left_until) + " " + WalletMethods.formatDate(monthEnd, context, model.getLanguage());
        }
    }


}

