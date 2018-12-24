package com.evgeny.lebed.wallet.Presenter;

import android.content.Context;
import android.util.Log;

import com.evgeny.lebed.wallet.Class.WalletMethods;
import com.evgeny.lebed.wallet.Interface.ContractModel;
import com.evgeny.lebed.wallet.Interface.ContractPresenter;
import com.evgeny.lebed.wallet.Interface.ContractView;

public class PresenterSettings implements ContractPresenter.Settings {

    private String currency;
    private String language;
    private Integer startDate;
    private Integer total;
    private ContractView.Settings view;
    private ContractModel model;
    private Context context;


    public PresenterSettings(ContractView.Settings view, ContractModel model, Context context) {
        this.view = view;
        this.model = model;
        this.context = context;
        language = model.getLanguage();
        total = model.getBudget().intValue();
        currency = model.getCurrency();
        startDate = model.getStartDate();
        view.showCurrentStartDate(Integer.toString(startDate));
        view.showCurrentBudget(Integer.toString(total) + " " + currency);
        if (currency.equals(WalletMethods.RUSSIAN_RUBLE.getSymbol())){
            view.showCurrentCurrency(WalletMethods.RUSSIAN_RUBLE.getName(context));
        }
        else if (currency.equals(WalletMethods.DOLLAR.getSymbol())){
            view.showCurrentCurrency(WalletMethods.DOLLAR.getName(context));
        }
        else if (currency.equals(WalletMethods.EURO.getSymbol())){
            view.showCurrentCurrency(WalletMethods.EURO.getName(context));
        }
        else if (currency.equals(WalletMethods.UKRAINIAN_HRYVNIA.getSymbol())){
            view.showCurrentCurrency(WalletMethods.UKRAINIAN_HRYVNIA.getName(context));
        }
        else if (currency.equals(WalletMethods.KAZAKHSTANI_TENGE.getSymbol())){
            view.showCurrentCurrency(WalletMethods.KAZAKHSTANI_TENGE.getName(context));
        }
        else if (currency.equals(WalletMethods.ISRAELI_SHEKEL.getSymbol())){
            view.showCurrentCurrency(WalletMethods.ISRAELI_SHEKEL.getName(context));
        }
        else if (currency.equals(WalletMethods.POUND_STERLING.getSymbol())){
            view.showCurrentCurrency(WalletMethods.POUND_STERLING.getName(context));
        }
        else if (currency.equals(WalletMethods.POLISH_ZLOTY.getSymbol())){
            view.showCurrentCurrency(WalletMethods.POLISH_ZLOTY.getName(context));
        }
        else if (currency.equals(WalletMethods.BELARUSIAN_RUBLE.getSymbol())){
            view.showCurrentCurrency(WalletMethods.BELARUSIAN_RUBLE.getName(context));
        }
        else if (currency.equals(WalletMethods.KRONA.getSymbol())){
            view.showCurrentCurrency(WalletMethods.KRONA.getName(context));
        }
        else if (currency.equals(WalletMethods.SWISS_FRANK.getSymbol())){
            view.showCurrentCurrency(WalletMethods.SWISS_FRANK.getName(context));
        }

        if (language.equals(WalletMethods.ENGLISH)){
            view.showLanguage("English");
        }
        else if (language.equals(WalletMethods.RUSSIAN)){
            view.showLanguage("Русский");
        }

    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public void russianRubleChosen() {
        this.currency = WalletMethods.RUSSIAN_RUBLE.getSymbol();
        view.showCurrentCurrency(WalletMethods.RUSSIAN_RUBLE.getName(context));
        view.showCurrentBudget(Integer.toString(total) + " " + currency);

        Log.e("currency", currency);
    }

    @Override
    public void dollarChosen() {
        this.currency = WalletMethods.DOLLAR.getSymbol();
        view.showCurrentCurrency(WalletMethods.DOLLAR.getName(context));
        view.showCurrentBudget(Integer.toString(total) + " " + currency);

        Log.e("currency", currency);
    }

    @Override
    public void eurChosen() {
        this.currency = WalletMethods.EURO.getSymbol();
        view.showCurrentCurrency(WalletMethods.EURO.getName(context));
        view.showCurrentBudget(Integer.toString(total) + " " + currency);

        Log.e("currency", currency);
    }

    @Override
    public void ukrainianHryvniaChosen() {
        this.currency = WalletMethods.UKRAINIAN_HRYVNIA.getSymbol();
        view.showCurrentCurrency(WalletMethods.UKRAINIAN_HRYVNIA.getName(context));
        view.showCurrentBudget(Integer.toString(total) + " " + currency);

        Log.e("currency", currency);
    }

    @Override
    public void kazakhstanTengeChosen() {
        this.currency = WalletMethods.KAZAKHSTANI_TENGE.getSymbol();
        view.showCurrentCurrency(WalletMethods.KAZAKHSTANI_TENGE.getName(context));
        view.showCurrentBudget(Integer.toString(total) + " " + currency);

        Log.e("currency", currency);
    }

    @Override
    public void israelShekelChosen() {
        this.currency = WalletMethods.ISRAELI_SHEKEL.getSymbol();
        view.showCurrentCurrency(WalletMethods.ISRAELI_SHEKEL.getName(context));
        view.showCurrentBudget(Integer.toString(total) + " " + currency);

        Log.e("currency", currency);
    }

    @Override
    public void poundSterlingChosen() {
        this.currency = WalletMethods.POUND_STERLING.getSymbol();
        view.showCurrentCurrency(WalletMethods.POUND_STERLING.getName(context));
        view.showCurrentBudget(Integer.toString(total) + " " + currency);

        Log.e("currency", currency);
    }

    @Override
    public void polishZlotyChosen() {
        this.currency = WalletMethods.POLISH_ZLOTY.getSymbol();
        view.showCurrentCurrency(WalletMethods.POLISH_ZLOTY.getName(context));
        view.showCurrentBudget(Integer.toString(total) + " " + currency);

        Log.e("currency", currency);
    }

    @Override
    public void belarusianRubleChosen() {
        this.currency = WalletMethods.BELARUSIAN_RUBLE.getSymbol();
        view.showCurrentCurrency(WalletMethods.BELARUSIAN_RUBLE.getName(context));
        view.showCurrentBudget(Integer.toString(total) + " " + currency);

        Log.e("currency", currency);
    }

    @Override
    public void kronaChosen() {
        this.currency = WalletMethods.KRONA.getSymbol();
        view.showCurrentCurrency(WalletMethods.KRONA.getName(context));
        view.showCurrentBudget(Integer.toString(total) + " " + currency);

        Log.e("currency", currency);
    }
    @Override
    public void swissFrankChosen() {
        this.currency = WalletMethods.SWISS_FRANK.getSymbol();
        view.showCurrentCurrency(WalletMethods.SWISS_FRANK.getName(context));
        view.showCurrentBudget(Integer.toString(total) + " " + currency);

        Log.e("currency", currency);
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

    @Override
    public void deleteClickedStartDate() {
        if (startDate != 0) {

            startDate = (startDate  - (startDate) % 10) / 10;

            Log.e("newStartDate", Integer.toString(startDate));
            view.showStartDate(Integer.toString(startDate));
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
    public String getEnteredAmount() {
        return Integer.toString(total);
    }

    @Override
    public void englishChosen() {
        language = WalletMethods.ENGLISH;
        view.showLanguage("English");

    }
    @Override
    public void russianChosen() {
        language = WalletMethods.RUSSIAN;
        view.showLanguage("Русский");
    }

    @Override
    public void applyNewSettings() {

        boolean somethingChanged = false;
        if (!language.equals(model.getLanguage())){
            model.updateLanguage(language);
            somethingChanged = true;
        }
        if (!currency.equals(model.getCurrency())){
            model.updateCurrency(currency);
            somethingChanged = true;

        }
        if (!total.equals(model.getBudget().intValue())){
            model.updateBudget(total.doubleValue());
            somethingChanged = true;

        }
        if (!startDate.equals(model.getStartDate())){
            model.updateStartDate(startDate);
            somethingChanged = true;
        }

        view.missionAccomplished(somethingChanged);

    }



    @Override
    public String getEnteredStartDate() {
        return Integer.toString(startDate);
    }

    @Override
    public void newStartDateEntered() {
        view.showCurrentStartDate(Integer.toString(startDate));
    }

    @Override
    public void newBudgetEntered() {
        view.showCurrentBudget(Integer.toString(total) + " " + currency);
    }


}
