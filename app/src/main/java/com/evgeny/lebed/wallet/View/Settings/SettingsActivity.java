package com.evgeny.lebed.wallet.View.Settings;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.evgeny.lebed.wallet.Interface.ContractPresenter;
import com.evgeny.lebed.wallet.Interface.ContractView;
import com.evgeny.lebed.wallet.Model.DBHelper;
import com.evgeny.lebed.wallet.Presenter.PresenterSettings;
import com.evgeny.lebed.wallet.R;

public class SettingsActivity extends AppCompatActivity implements ContractView.Settings, View.OnClickListener {

    ContractPresenter.Settings presenter;
    private CheckBox checkBoxCurrency, checkBoxBudget, checkBoxStartDate, checkBoxLanguage;
    private CurrencySettingsFragment currencySettingsFragment;
    private BudgetSettingsFragment budgetSettingsFragment;
    private StartDateSettingsFragment startDateSettingsFragment;
    private LanguageSettingsFragment languageSettingsFragment;
    private TextView textViewCurrentCurrency, textViewCurrentBudget, textViewCurrentStartDate, textViewCurrentLanguage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
    }

    private void init() {

        ImageButton btnBack, btnDone;
        btnBack = findViewById(R.id.settingsBackButton);
        btnDone = findViewById(R.id.settingsDoneButton);
        checkBoxCurrency = findViewById(R.id.settings_check_currency);
        checkBoxBudget = findViewById(R.id.settings_check_budget);
        checkBoxStartDate = findViewById(R.id.settings_check_start_date);
        checkBoxLanguage = findViewById(R.id.settings_check_language);
        textViewCurrentCurrency = findViewById(R.id.settings_current_currency);
        textViewCurrentBudget = findViewById(R.id.settings_current_budget);
        textViewCurrentStartDate = findViewById(R.id.settings_current_start_date);
        textViewCurrentLanguage = findViewById(R.id.settings_current_language);

        btnBack.setOnClickListener(this);
        btnDone.setOnClickListener(this);
        checkBoxCurrency.setOnClickListener(this);
        checkBoxBudget.setOnClickListener(this);
        checkBoxStartDate.setOnClickListener(this);
        checkBoxLanguage.setOnClickListener(this);

        currencySettingsFragment = new CurrencySettingsFragment();
        budgetSettingsFragment = new BudgetSettingsFragment();
        startDateSettingsFragment = new StartDateSettingsFragment();
        languageSettingsFragment = new LanguageSettingsFragment();

        presenter = new PresenterSettings(this, new DBHelper(this), this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settingsBackButton:
                onBackPressed();
                break;
            case R.id.settingsDoneButton:
                presenter.applyNewSettings();
                break;
            case R.id.settings_check_currency:

                if (checkBoxBudget.isChecked()) {
                    super.onBackPressed();
                    checkBoxBudget.setChecked(false);
                }

                if (checkBoxStartDate.isChecked()) {
                    super.onBackPressed();
                    checkBoxStartDate.setChecked(false);
                }
                if (checkBoxLanguage.isChecked()){
                    super.onBackPressed();
                    checkBoxLanguage.setChecked(false);
                }
                if (checkBoxCurrency.isChecked()) {
                    openCurrencyFragment();

                } else {
                    onBackPressed();
                }
                break;
            case R.id.settings_check_budget:

                if (checkBoxCurrency.isChecked()) {
                    super.onBackPressed();
                    checkBoxCurrency.setChecked(false);
                }

                if (checkBoxStartDate.isChecked()) {
                    super.onBackPressed();
                    checkBoxStartDate.setChecked(false);
                }

                if (checkBoxLanguage.isChecked()){
                    super.onBackPressed();
                    checkBoxLanguage.setChecked(false);
                }
                if (checkBoxBudget.isChecked()) {
                    openBudgetFragment();

                } else {
                    onBackPressed();
                }
                break;

            case R.id.settings_check_start_date:

                if (checkBoxCurrency.isChecked()) {
                    super.onBackPressed();
                    checkBoxCurrency.setChecked(false);
                }

                if (checkBoxBudget.isChecked()) {
                    super.onBackPressed();
                    checkBoxBudget.setChecked(false);
                }

                if (checkBoxLanguage.isChecked()){
                    super.onBackPressed();
                    checkBoxLanguage.setChecked(false);
                }
                if (checkBoxStartDate.isChecked()) {
                    openStartDateFragment();

                } else {
                    onBackPressed();
                }


                break;
            case R.id.settings_check_language:
                if (checkBoxCurrency.isChecked()) {
                    super.onBackPressed();
                    checkBoxCurrency.setChecked(false);
                }

                if (checkBoxBudget.isChecked()) {
                    super.onBackPressed();
                    checkBoxBudget.setChecked(false);
                }


                if (checkBoxStartDate.isChecked()) {
                    super.onBackPressed();
                    checkBoxStartDate.setChecked(false);

                }
                if (checkBoxLanguage.isChecked()) {
                    openLanguageFragment();
                } else {
                    onBackPressed();
                }


        }
    }

    private void openLanguageFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_to_top, R.anim.slide_from_bottom, R.anim.slide_to_bottom, R.anim.slide_from_top);


        fragmentTransaction.replace(R.id.settings_fragment_container, languageSettingsFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (checkBoxCurrency.isChecked()) {
            checkBoxCurrency.setChecked(false);
        } else if (checkBoxBudget.isChecked()) {
            checkBoxBudget.setChecked(false);
        } else if (checkBoxStartDate.isChecked()) {
            checkBoxStartDate.setChecked(false);
        } else if (checkBoxLanguage.isChecked()){
            checkBoxLanguage.setChecked(false);
        }
        super.onBackPressed();
    }

    public void openBudgetFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_to_top, R.anim.slide_from_bottom, R.anim.slide_to_bottom, R.anim.slide_from_top);


        fragmentTransaction.replace(R.id.settings_fragment_container, budgetSettingsFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    public void openStartDateFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_to_top, R.anim.slide_from_bottom, R.anim.slide_to_bottom, R.anim.slide_from_top);


        fragmentTransaction.replace(R.id.settings_fragment_container, startDateSettingsFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    public void numberClicked(int number) {
        switch (number) {
            case R.id.btn0_f_s:
                presenter.numberClicked(0);
                break;
            case R.id.btn1_f_s:
                presenter.numberClicked(1);

                break;
            case R.id.btn2_f_s:
                presenter.numberClicked(2);

                break;
            case R.id.btn3_f_s:
                presenter.numberClicked(3);

                break;
            case R.id.btn4_f_s:
                presenter.numberClicked(4);

                break;
            case R.id.btn5_f_s:
                presenter.numberClicked(5);

                break;
            case R.id.btn6_f_s:
                presenter.numberClicked(6);

                break;
            case R.id.btn7_f_s:
                presenter.numberClicked(7);

                break;
            case R.id.btn8_f_s:
                presenter.numberClicked(8);

                break;
            case R.id.btn9_f_s:
                presenter.numberClicked(9);

                break;
            case R.id.button_delete__f_s:
                presenter.deleteClicked();
                break;
        }
    }

    public void numberClickedStartDate(int number) {
        switch (number) {
            case R.id.btn0_f_s_sd:
                presenter.numberClickedStartDate(0);
                break;
            case R.id.btn1_f_s_sd:
                presenter.numberClickedStartDate(1);

                break;
            case R.id.btn2_f_s_sd:
                presenter.numberClickedStartDate(2);

                break;
            case R.id.btn3_f_s_sd:
                presenter.numberClickedStartDate(3);

                break;
            case R.id.btn4_f_s_sd:
                presenter.numberClickedStartDate(4);

                break;
            case R.id.btn5_f_s_sd:
                presenter.numberClickedStartDate(5);

                break;
            case R.id.btn6_f_s_sd:
                presenter.numberClickedStartDate(6);

                break;
            case R.id.btn7_f_s_sd:
                presenter.numberClickedStartDate(7);

                break;
            case R.id.btn8_f_s_sd:
                presenter.numberClickedStartDate(8);

                break;
            case R.id.btn9_f_s_sd:
                presenter.numberClickedStartDate(9);

                break;
            case R.id.button_delete__f_s_sd:
                Log.e("newStartDate", "  ");

                presenter.deleteClickedStartDate();
                break;
        }
    }

    public void startDateEntered(String startDate) {
        if (startDate.isEmpty() || Integer.parseInt(startDate) == 0) {
            Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.settings_fragment_container);

            (fragment.getView().findViewById(R.id.layout_amount_f_s_sd)).startAnimation(animShake);


        } else {
            presenter.newStartDateEntered();
            onBackPressed();
        }
    }


    public String getEnteredStartDate() {
        return presenter.getEnteredStartDate();
    }

    public void budgetEntered(String budget) {
        if (budget.isEmpty() || Integer.parseInt(budget) == 0) {
            Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.settings_fragment_container);

            (fragment.getView().findViewById(R.id.layout_amount_f_s)).startAnimation(animShake);


        } else {
            presenter.newBudgetEntered();
            onBackPressed();
        }
    }

    public String getEnteredAmount() {
        return presenter.getEnteredAmount();
    }

    public void currencyChosen(int view) {
        switch (view) {

            case R.id.btn_dollar_f:
                presenter.dollarChosen();

                break;
            case R.id.btn_russian_ruble_f:
                presenter.russianRubleChosen();

                break;
            case R.id.btn_euro_f:
                presenter.eurChosen();

                break;
            case R.id.btn_belarusian_ruble_f:
                presenter.belarusianRubleChosen();

                break;
            case R.id.btn_krona_f:
                presenter.kronaChosen();

                break;
            case R.id.btn_pound_sterling_f:
                presenter.poundSterlingChosen();

                break;
            case R.id.btn_polish_zloty_f:
                presenter.polishZlotyChosen();

                break;
            case R.id.btn_kazakhstan_tenge_f:
                presenter.kazakhstanTengeChosen();

                break;
            case R.id.btn_ukrainian_hryvnia_f:
                presenter.ukrainianHryvniaChosen();

                break;
            case R.id.btn_israel_shekel_f:
                presenter.israelShekelChosen();
                break;
            case R.id.btn_swiss_frank_f:
                presenter.swissFrankChosen();
                break;
        }

        onBackPressed();
    }

    public void languageChosen(int view) {
        switch (view) {
            case R.id.btn_english:
                presenter.englishChosen();
                break;
            case R.id.btn_russian:
                presenter.russianChosen();
                break;
        }

        onBackPressed();
        checkBoxLanguage.setChecked(false);
    }

    public void openCurrencyFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_to_top, R.anim.slide_from_bottom, R.anim.slide_to_bottom, R.anim.slide_from_top);


        fragmentTransaction.replace(R.id.settings_fragment_container, currencySettingsFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }



    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    @Override
    public void showLanguage(String language) {
        textViewCurrentLanguage.setText(language);
    }

    @Override
    public void showAmount(String amount) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.settings_fragment_container);
        Log.e("showBudget", amount);
        ((TextView) fragment.getView().findViewById(R.id.text_view_amount_f_s)).setText(amount);
    }

    @Override
    public void showStartDate(String startDate) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.settings_fragment_container);
        Log.e("showBudget", startDate);
        ((TextView) fragment.getView().findViewById(R.id.text_view_start_date_f_s_sd)).setText(startDate);
    }



    @Override
    public void missionAccomplished(Boolean somethingChanged) {
        if (somethingChanged) {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
        }
        finish();
    }

    @Override
    public void showCurrentCurrency(String currency) {
        textViewCurrentCurrency.setText(currency);
    }

    @Override
    public void showCurrentBudget(String budget) {
        textViewCurrentBudget.setText(budget);
    }

    @Override
    public void showCurrentStartDate(String startDate) {
        textViewCurrentStartDate.setText(startDate);
    }

    public String getLanguage() {
        return presenter.getLanguage();
    }

    public String getCurrency() {
        return presenter.getCurrencySymbol();
    }
}
