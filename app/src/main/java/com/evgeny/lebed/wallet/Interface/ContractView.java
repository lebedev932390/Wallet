package com.evgeny.lebed.wallet.Interface;

import java.util.Locale;

public interface ContractView {

    interface Main {
        void startAmountActivity();
        void startPaymentHistoryActivity();
        void startSettingsActivity();
        void startInitialSetupActivity();

        void setLocale(Locale locale);
        void showCurrency(String currency);
        void showDayBudgetInitially(String dayBudgetInt, String dayBudgetDecimal, boolean isPositive);

        void countIntAnimation(Integer start, Integer end, boolean endIsPositive);
        void countDecimalAnimation(Integer start, Integer end);

    }

    interface Amount {

        void showAmount(String amountInt, String amountDecimals);
        void showCurrency(String currency);
        void startNoteActivity();
        void paymentAdded();
        void amountIsEmpty();
        void countIntAnimationDay(Integer start, Integer end, boolean endIsPositive);
        void countDecimalAnimationDay(Integer start, Integer end);
        void countIntAnimationMonth(Integer start, Integer end, boolean endIsPositive);
        void countDecimalAnimationMonth(Integer start, Integer end);
        void setDayAndMonthBackgrounds(Boolean dayBudgetPlus, Boolean monthBudgetPlus);
        void showDate (String date);
        void showDatePicker(Long minDate, Long maxDate);



    }

    interface Note {
        void amountIsEmpty();
        void missionAccomplished(String note);
    }

    interface PaymentHistory {

    }

    interface InitialSetup{
        void currencyEntry();
        void budgetEntry();
        void languageEntry();
        void missionAccomplished();
        void startDateEntry();
        void showStartDate(String startDate);
        void showAmount(String amount);


    }

    interface Settings{
        void showLanguage(String language);
        void showAmount(String amount);
        void showStartDate(String startDate);
        void missionAccomplished(Boolean somethingChanged);
        void showCurrentCurrency(String currency);
        void showCurrentBudget(String budget);
        void showCurrentStartDate(String startDate);

    }
}
