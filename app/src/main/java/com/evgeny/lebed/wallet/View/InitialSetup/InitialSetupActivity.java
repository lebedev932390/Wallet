package com.evgeny.lebed.wallet.View.InitialSetup;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.evgeny.lebed.wallet.Interface.ContractPresenter;
import com.evgeny.lebed.wallet.Interface.ContractView;
import com.evgeny.lebed.wallet.Model.DBHelper;
import com.evgeny.lebed.wallet.Presenter.PresenterInitialSetup;
import com.evgeny.lebed.wallet.R;
import com.evgeny.lebed.wallet.View.Main.MainActivity;

import java.util.Locale;

public class InitialSetupActivity extends AppCompatActivity implements ContractView.InitialSetup {

    private ContractPresenter.InitialSetup presenter;
    private LanguageFragment languageFragment;
    private CurrencyFragment currencyFragment;
    private BudgetFragment budgetFragment;
    private StartDateFragment startDateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_setup);


        init();


    }

    private void init() {
        languageFragment = new LanguageFragment();
        currencyFragment = new CurrencyFragment();
        budgetFragment = new BudgetFragment();
        startDateFragment = new StartDateFragment();
        presenter = new PresenterInitialSetup(this, new DBHelper(this));



    }




    @Override
    public void currencyEntry() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_to_top, R.anim.slide_from_bottom, R.anim.slide_to_bottom, R.anim.slide_from_top);
        fragmentTransaction.replace(R.id.initial_setup_fragment_container, currencyFragment);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    @Override
    public void budgetEntry() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_to_top, R.anim.slide_from_bottom, R.anim.slide_to_bottom, R.anim.slide_from_top);


        fragmentTransaction.replace(R.id.initial_setup_fragment_container, budgetFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void languageEntry() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_to_top, R.anim.slide_from_bottom, R.anim.slide_to_bottom, R.anim.slide_from_top);


        fragmentTransaction.replace(R.id.initial_setup_fragment_container, languageFragment);
        fragmentTransaction.commit();
    }


    @Override
    public void missionAccomplished() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startDateEntry() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_to_top, R.anim.slide_from_bottom, R.anim.slide_to_bottom, R.anim.slide_from_top);
        fragmentTransaction.replace(R.id.initial_setup_fragment_container, startDateFragment);


        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void showStartDate(String startDate) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.initial_setup_fragment_container);
        ((TextView) fragment.getView().findViewById(R.id.text_view_start_date_is)).setText(startDate);
    }

    @Override
    public void showAmount(String amount) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.initial_setup_fragment_container);
        ((TextView)fragment.getView().findViewById(R.id.text_view_amount_f)).setText(amount);
    }




    public void numberClicked(int number){
        switch (number){
            case R.id.btn0f:
                presenter.numberClicked(0);
                break;
            case R.id.btn1f:
                presenter.numberClicked(1);

                break;
            case R.id.btn2f:
                presenter.numberClicked(2);

                break;
            case R.id.btn3f:
                presenter.numberClicked(3);

                break;
            case R.id.btn4f:
                presenter.numberClicked(4);

                break;
            case R.id.btn5f:
                presenter.numberClicked(5);

                break;
            case R.id.btn6f:
                presenter.numberClicked(6);

                break;
            case R.id.btn7f:
                presenter.numberClicked(7);

                break;
            case R.id.btn8f:
                presenter.numberClicked(8);

                break;
            case R.id.btn9f:
                presenter.numberClicked(9);

                break;
            case R.id.button_delete_f:
                presenter.deleteClicked();
                break;
        }
    }
    public void startDateEntered(String startDate){
        if (startDate.isEmpty() || Integer.parseInt(startDate) == 0) {
            Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.initial_setup_fragment_container);

            (fragment.getView().findViewById(R.id.layout_amount_is)).startAnimation(animShake);


        } else {
            presenter.startDateChosen(Integer.parseInt(startDate));
        }
    }
    public void numberClickedStartDate(int number){
        switch (number){
            case R.id.btn0_is:
                presenter.numberClickedStartDate(0);
                break;
            case R.id.btn1_is:
                presenter.numberClickedStartDate(1);

                break;
            case R.id.btn2_is:
                presenter.numberClickedStartDate(2);

                break;
            case R.id.btn3_is:
                presenter.numberClickedStartDate(3);

                break;
            case R.id.btn4_is:
                presenter.numberClickedStartDate(4);

                break;
            case R.id.btn5_is:
                presenter.numberClickedStartDate(5);

                break;
            case R.id.btn6_is:
                presenter.numberClickedStartDate(6);

                break;
            case R.id.btn7_is:
                presenter.numberClickedStartDate(7);

                break;
            case R.id.btn8_is:
                presenter.numberClickedStartDate(8);

                break;
            case R.id.btn9_is:
                presenter.numberClickedStartDate(9);

                break;
            case R.id.button_delete_is:
                presenter.deleteClickedStartDate();
                break;

        }
    }

    public void budgetEntered(String budget){
        if (budget.isEmpty() || Integer.parseInt(budget)==0){
            Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.initial_setup_fragment_container);

            (fragment.getView().findViewById(R.id.layout_amount_f)).startAnimation(animShake);


        }
        else {
            presenter.budgetEntered();
        }
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void languageChosen(int language){

        Locale locale = new Locale("en");
        switch (language){
            case R.id.btn_english:
                presenter.englishChosen();
                break;
            case R.id.btn_russian:
                locale = new Locale("ru");
                presenter.russianChosen();
                break;
        }
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());


    }

    public void currencyChosen(int currency){
        switch (currency){

            case R.id.btn_dollar:
                presenter.dollarChosen();

                break;
            case R.id.btn_russian_ruble:
                presenter.russianRubleChosen();

                break;
            case R.id.btn_euro:
                presenter.eurChosen();

                break;
            case R.id.btn_belarusian_ruble:
                presenter.belarusianRubleChosen();

                break;
            case R.id.btn_krona:
                presenter.kronaChosen();

                break;
            case R.id.btn_pound_sterling:
                presenter.poundSterlingChosen();

                break;
            case R.id.btn_polish_zloty:
                presenter.polishZlotyChosen();

                break;
            case R.id.btn_kazakhstan_tenge:
                presenter.kazakhstanTengeChosen();

                break;
            case R.id.btn_ukrainian_hryvnia:
                presenter.ukrainianHryvniaChosen();

                break;
            case R.id.btn_israel_shekel:
                presenter.israelShekelChosen();
                break;
            case R.id.btn_swiss_frank:
                presenter.swissFrankChosen();
                break;
        }

    }

    public String getLanguage(){
        return presenter.getLanguage();
    }

    public String getCurrency(){
        return presenter.getCurrencySymbol();
    }
}
