package com.evgeny.lebed.wallet.Interface;

import java.util.List;
import java.util.Locale;

public interface ContractPresenter {

    interface Main {

        void initPresenter();
        Locale getLocale();
        void newPaymentClicked();

        void paymentHistoryClicked();
        String getCurrency();

        void monthSelected();
        void daySelected();

        String getDayStatus();
        String getMonthStatus();


    }

    interface Amount {
        void numberClicked(Double number);

        void deleteClicked();

        void addPaymentClicked(Long date, String amount, String note);
        void dateChosen(Long date);
        void choseDateClicked();
        String getLanguage();
    }

    interface Note {
        List<String> getListOfNotes();
        void noteChosenClicked(String note);
        void noteWrittenClicked(String note);
        void forgetNoteClicked(String note);
        String getLanguage();

    }

    interface PaymentHistory {
        List getListOfPaymentsWithHeaders();
        void deletePaymentClicked(int id);
        String getCurrency();
    }

    interface Settings{
        String getLanguage();
        void russianChosen();
        void englishChosen();
        void russianRubleChosen();
        void dollarChosen();
        void eurChosen();
        void ukrainianHryvniaChosen();
        void kazakhstanTengeChosen();
        void israelShekelChosen();
        void poundSterlingChosen();
        void polishZlotyChosen();
        void belarusianRubleChosen();
        void kronaChosen();
        void swissFrankChosen();

        void numberClicked(Integer number);
        void numberClickedStartDate(Integer number);
        void deleteClickedStartDate();
        String getCurrencySymbol();

        void deleteClicked();

        String getEnteredAmount();


        void applyNewSettings();

        String getEnteredStartDate();

        void newStartDateEntered();
        void newBudgetEntered();

    }

    interface InitialSetup{

        void englishChosen();
        void russianChosen();

        void russianRubleChosen();
        void dollarChosen();
        void eurChosen();
        void ukrainianHryvniaChosen();
        void kazakhstanTengeChosen();
        void israelShekelChosen();
        void poundSterlingChosen();
        void polishZlotyChosen();
        void belarusianRubleChosen();
        void kronaChosen();
        void swissFrankChosen();
        void startDateChosen(Integer startDate);
        String getLanguage();
        String getCurrencySymbol();


        void numberClicked(Integer number);

        void deleteClicked();
        void deleteClickedStartDate();

        void budgetEntered();

        void numberClickedStartDate(Integer number);

    }
}
