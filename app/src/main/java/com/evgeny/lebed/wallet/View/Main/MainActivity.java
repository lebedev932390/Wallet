package com.evgeny.lebed.wallet.View.Main;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.evgeny.lebed.wallet.Interface.ContractPresenter;
import com.evgeny.lebed.wallet.Interface.ContractView;
import com.evgeny.lebed.wallet.Model.DBHelper;
import com.evgeny.lebed.wallet.Presenter.PresenterMain;
import com.evgeny.lebed.wallet.R;
import com.evgeny.lebed.wallet.View.Settings.SettingsActivity;
import com.evgeny.lebed.wallet.View.Amount.AmountActivity;
import com.evgeny.lebed.wallet.View.InitialSetup.InitialSetupActivity;
import com.evgeny.lebed.wallet.View.PaymentHistory.PaymentHistoryActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ContractView.Main, View.OnClickListener {

    private ContractPresenter.Main presenter;
    private RelativeLayout mainLayout;
    private ValueAnimator animationRedToGreen, animationGreenToRed;
    private TextView textViewMainBudgetInt, textViewMainBudgetDecimal;
    private boolean backgroundIsRed;
    private ImageView indicatorDay, indicatorMonth;

    private final int ANIMATION_TIME_NUMBERS = 400;
    private final int ANIMATION_TIME_COLORS = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter =  new PresenterMain(this, new DBHelper(this), this);
        setLocale(presenter.getLocale());
        setContentView(R.layout.activity_main);
        init();

    }

    @Override
    public void setLocale(Locale locale){
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
    }

    private void init() {

        indicatorDay = findViewById(R.id.indicator_day);
        indicatorMonth = findViewById(R.id.indicator_month);
        textViewMainBudgetInt = findViewById(R.id.main_budget_int);
        textViewMainBudgetDecimal = findViewById(R.id.main_budget_decimal);
        mainLayout = findViewById(R.id.main_layout);
        presenter.initPresenter();

        ImageButton buttonNewPayment;
        buttonNewPayment = findViewById(R.id.button_new_payment);
        buttonNewPayment.setOnClickListener(this);
        animationGreenToRed = new ValueAnimator();
        animationRedToGreen = new ValueAnimator();
        ImageButton btnPaymentHistory = findViewById(R.id.btn_payment_history);
        ImageButton btnSettings = findViewById(R.id.btn_main_settings);
        btnPaymentHistory.setOnClickListener(this);
        btnSettings.setOnClickListener(this);

        animationRedToGreen.setIntValues(getResources().getColor(R.color.color_red_background), getResources().getColor(R.color.color_blue_background));
        animationRedToGreen.setEvaluator(new ArgbEvaluator());
        animationRedToGreen.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mainLayout.setBackgroundColor((Integer) valueAnimator.getAnimatedValue());
            }
        });
        animationRedToGreen.setDuration(ANIMATION_TIME_COLORS);

        animationGreenToRed.setIntValues(getResources().getColor(R.color.color_blue_background), getResources().getColor(R.color.color_red_background));
        animationGreenToRed.setEvaluator(new ArgbEvaluator());
        animationGreenToRed.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mainLayout.setBackgroundColor((Integer) valueAnimator.getAnimatedValue());
            }
        });
        animationGreenToRed.setDuration(ANIMATION_TIME_COLORS);

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new SwipeAdapter(getSupportFragmentManager()));
        changeIndicator(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        presenter.daySelected();
                        changeIndicator(i);
                        break;
                    case 1:
                        presenter.monthSelected();
                        changeIndicator(i);

                        break;


                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });


    }

    private void changeIndicator(int position){
        switch (position){
            case 0:
                indicatorDay.setImageDrawable(getResources().getDrawable(R.drawable.ic_dot_chosen));
                indicatorMonth.setImageDrawable(getResources().getDrawable(R.drawable.ic_dot_not_chosen));
                break;
            case 1:
                indicatorMonth.setImageDrawable(getResources().getDrawable(R.drawable.ic_dot_chosen));
                indicatorDay.setImageDrawable(getResources().getDrawable(R.drawable.ic_dot_not_chosen));
        }
    }



    @Override
    public void countIntAnimation(Integer start, Integer end, final boolean endIsPositive) {

        if (backgroundIsRed && endIsPositive) {
            backgroundIsRed = false;
            animationRedToGreen.start();
        }
        if (!backgroundIsRed && !endIsPositive) {
            backgroundIsRed = true;
            animationGreenToRed.start();
        }
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(start, end);// here you set the range, from 0 to "count" value
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textViewMainBudgetInt.setText(String.valueOf(animation.getAnimatedValue()));
                if (Integer.parseInt(textViewMainBudgetInt.getText().toString()) == 0 && (!endIsPositive)) {
                    textViewMainBudgetInt.setText("-0");
                }
            }
        });
        animator.setDuration(ANIMATION_TIME_NUMBERS);
        animator.start();
    }

    @Override
    public void countDecimalAnimation(Integer start, Integer end) {
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(start, end);// here you set the range, from 0 to "count" value
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textViewMainBudgetDecimal.setText(String.valueOf(animation.getAnimatedValue()));
                if (Integer.parseInt(textViewMainBudgetDecimal.getText().toString()) == 0) {
                    textViewMainBudgetDecimal.setText("00");
                } else if (Integer.parseInt(textViewMainBudgetDecimal.getText().toString()) < 10) {
                    textViewMainBudgetDecimal.setText("0" + textViewMainBudgetDecimal.getText().toString());
                }
            }
        });
        animator.setDuration(ANIMATION_TIME_NUMBERS);
        animator.start();

    }


    public void startAmountActivity() {

        //recreate();

        Intent intent = new Intent(this, AmountActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_to_top, R.anim.slide_from_bottom);


    }


    @Override
    public void startPaymentHistoryActivity() {
        Intent intent = new Intent(this, PaymentHistoryActivity.class);
        startActivityForResult(intent, 1);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            recreate();
        }
    }

    @Override
    public void startSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, 1);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void startInitialSetupActivity() {
        Intent intent = new Intent(this, InitialSetupActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void showCurrency(String currency) {
        TextView mainCurrency = findViewById(R.id.main_currency);
        mainCurrency.setText(currency);
    }

    @Override
    public void showDayBudgetInitially(String dayBudgetInt, String dayBudgetDecimal, boolean isPositive) {
        textViewMainBudgetInt.setText(dayBudgetInt);
        textViewMainBudgetDecimal.setText(dayBudgetDecimal);
        if (isPositive) {
            backgroundIsRed = false;
            mainLayout.setBackground(getResources().getDrawable(R.color.color_blue_background));
        } else {
            backgroundIsRed = true;
            mainLayout.setBackground(getResources().getDrawable(R.color.color_red_background));

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_new_payment:
                presenter.newPaymentClicked();
                break;
            case R.id.btn_payment_history:
                presenter.paymentHistoryClicked();
                break;
            case R.id.btn_main_settings:
                startSettingsActivity();
                break;
        }
    }

    public ContractPresenter.Main getPresenter() {
        return presenter;
    }
}
