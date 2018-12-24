package com.evgeny.lebed.wallet.Presenter;

import android.content.Context;

import com.evgeny.lebed.wallet.Class.WalletMethods;
import com.evgeny.lebed.wallet.Class.Payment;
import com.evgeny.lebed.wallet.Interface.ContractModel;
import com.evgeny.lebed.wallet.Interface.ContractPresenter;
import com.evgeny.lebed.wallet.Interface.ContractView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PresenterPaymentHistory implements ContractPresenter.PaymentHistory {

    private ContractView.PaymentHistory view;
    private ContractModel model;
    private Context context;
    private List listOfPaymentsWithHeaders = new ArrayList();

    private Calendar calendar;


    public PresenterPaymentHistory(ContractView.PaymentHistory view, ContractModel model, Context context) {
        this.view = view;
        this.model = model;
        this.context = context;
        calendar = Calendar.getInstance();
        initListOfPayments();


    }


    private void initListOfPayments() {


        Gson gson = new Gson();

        List<String> listOfJson = model.getPayments();


        List<Payment> listOfPayments = new ArrayList<>();
        for (int i = 0; i < listOfJson.size(); i++) {
            listOfPayments.add(gson.fromJson(listOfJson.get(i), Payment.class));
        }


        Collections.sort(listOfPayments);
        Collections.reverse(listOfPayments);


        //вчерашняя, для проверки
        calendar = Calendar.getInstance();
        Long previousDay = null;
        Long currentDay;
        for (int i = 0; i < listOfPayments.size(); i++) {
            calendar.setTime(new Date(listOfPayments.get(i).getDate()));
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            currentDay = calendar.getTimeInMillis();
            if (i == 0) {

                listOfPaymentsWithHeaders.add(WalletMethods.formatDate(calendar.getTimeInMillis(), context, model.getLanguage()));

            } else if (!currentDay.equals(previousDay)) {

                listOfPaymentsWithHeaders.add(WalletMethods.formatDate(currentDay, context,model.getLanguage()));

            }
            previousDay = currentDay;
            listOfPaymentsWithHeaders.add(listOfPayments.get(i));
        }
    }


    @Override
    public List getListOfPaymentsWithHeaders() {
        return listOfPaymentsWithHeaders;
    }

    @Override
    public void deletePaymentClicked(int id) {
        model.deletePayment(id);


    }

    @Override
    public String getCurrency() {
        return model.getCurrency();
    }


}
