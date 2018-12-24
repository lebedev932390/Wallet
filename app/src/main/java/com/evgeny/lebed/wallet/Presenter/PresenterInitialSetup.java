package com.evgeny.lebed.wallet.Presenter;

import android.util.Log;

import com.evgeny.lebed.wallet.Class.WalletMethods;
import com.evgeny.lebed.wallet.Interface.ContractModel;
import com.evgeny.lebed.wallet.Interface.ContractPresenter;
import com.evgeny.lebed.wallet.Interface.ContractView;

public class PresenterInitialSetup implements ContractPresenter.InitialSetup {
    private Integer total = 0;

    private ContractView.InitialSetup view;
    private ContractModel model;
    private String language = WalletMethods.RUSSIAN;
    private String currency;
    private Integer startDate = 1;

    public PresenterInitialSetup(ContractView.InitialSetup view, ContractModel model) {
        this.view = view;
        this.model = model;
        view.languageEntry();
    }


    @Override
    public void englishChosen() {
        this.language = WalletMethods.ENGLISH;
        view.currencyEntry();

    }

    @Override
    public void russianChosen() {
        this.language = WalletMethods.RUSSIAN;
        view.currencyEntry();

    }


    @Override
    public void russianRubleChosen() {
        initCurrency(WalletMethods.RUSSIAN_RUBLE.getSymbol());



    }

    @Override
    public void dollarChosen() {
        initCurrency(WalletMethods.DOLLAR.getSymbol());
    }

    @Override
    public void eurChosen() {
        initCurrency(WalletMethods.EURO.getSymbol());

    }

    @Override
    public void ukrainianHryvniaChosen() {
        initCurrency(WalletMethods.UKRAINIAN_HRYVNIA.getSymbol());


    }

    @Override
    public void kazakhstanTengeChosen() {
        initCurrency(WalletMethods.KAZAKHSTANI_TENGE.getSymbol());


    }

    @Override
    public void israelShekelChosen() {
        initCurrency(WalletMethods.ISRAELI_SHEKEL.getSymbol());
    }

    @Override
    public void poundSterlingChosen() {
        initCurrency(WalletMethods.POUND_STERLING.getSymbol());
    }

    @Override
    public void polishZlotyChosen() {
        initCurrency(WalletMethods.POLISH_ZLOTY.getSymbol());
    }


    @Override
    public void belarusianRubleChosen() {
        initCurrency(WalletMethods.BELARUSIAN_RUBLE.getSymbol());
    }

    @Override
    public void kronaChosen() {
        initCurrency(WalletMethods.KRONA.getSymbol());

    }

    @Override
    public void swissFrankChosen() {
        initCurrency(WalletMethods.SWISS_FRANK.getSymbol());


    }

    private void initCurrency(String currency){
        this.currency = currency;
        view.startDateEntry();
    }


    @Override
    public void startDateChosen(Integer startDate) {
        this.startDate = startDate;
        view.budgetEntry();
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public String getCurrencySymbol() {
        return currency;
    }

    @Override
    public void numberClicked(Integer number) {


        Integer checkMax = total * 10;
        checkMax += number;
        if (checkMax < 1000000) {
            total = total * 10;
            total += number;
            view.showAmount(Integer.toString(total));
        }
    }

    @Override
    public void deleteClicked() {
        if (total != 0) {

            total = (total  - (total) % 10) / 10;

            view.showAmount(Integer.toString(total));
        }


    }

    @Override
    public void deleteClickedStartDate() {
        if (startDate != 0) {

            startDate = (startDate  - (startDate) % 10) / 10;

            Log.e("newStartDate", Integer.toString(startDate));
            view.showStartDate(Integer.toString(startDate));
        }
    }

    @Override
    public void budgetEntered() {
        model.setLanguage(language);
        model.setCurrency(currency);
        model.setBudget(total.doubleValue());
        model.setStartDate(startDate);
        model.updateInitialSetup(true);
        view.missionAccomplished();
    }

    @Override
    public void numberClickedStartDate(Integer number) {
        if (startDate <3){
            startDate = startDate * 10;
            startDate+=number;
            view.showStartDate(Integer.toString(startDate));
        }
        if ((startDate==3) && number < 2){
            startDate = startDate * 10;
            startDate+=number;
            view.showStartDate(Integer.toString(startDate));
        }
    }


}
