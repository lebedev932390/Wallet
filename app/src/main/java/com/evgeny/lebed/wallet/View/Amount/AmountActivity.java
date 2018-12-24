package com.evgeny.lebed.wallet.View.Amount;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.evgeny.lebed.wallet.Interface.ContractPresenter;
import com.evgeny.lebed.wallet.Interface.ContractView;
import com.evgeny.lebed.wallet.Model.DBHelper;
import com.evgeny.lebed.wallet.Presenter.PresenterAmount;
import com.evgeny.lebed.wallet.R;
import com.evgeny.lebed.wallet.View.Main.MainActivity;
import com.evgeny.lebed.wallet.View.Note.NoteActivity;

import java.util.Calendar;
import java.util.Date;

import static android.app.DatePickerDialog.*;

public class AmountActivity extends AppCompatActivity implements ContractView.Amount, View.OnClickListener, OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private ContractPresenter.Amount presenter;
    private TextView textViewAmount, textViewDayBudgetInt, textViewMonthBudgetInt, textViewDayBudgetDecimal, textViewMonthBudgetDecimal;

    private Button btnAddNote;
    private Calendar calendar = Calendar.getInstance();
    private RelativeLayout layoutAmount;
    private Long date;
    private String note = "";
    private TextView textViewDate;

    private ValueAnimator animationRedToGreenDay, animationGreenToRedDay, animationRedToGreenMonth, animationGreenToRedMonth;
    private RelativeLayout dayBackground, monthBackground;

    boolean dayBackgroundIsRed, monthBackgroundIsRed;

    private final int ANIMATION_TIME_NUMBERS = 400;
    private final int ANIMATION_TIME_COLORS = 200;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount);

        init();

    }

    private void init() {


        textViewAmount = findViewById(R.id.amount_text_view_amount);
        date = new Date().getTime();
        Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
        ImageButton btnDelete, btnOk, btnBack, btnDatePicker;
        btnOk = findViewById(R.id.btnOk);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnBack = findViewById(R.id.amountBackButton);
        btnDelete = findViewById(R.id.btnDelete);
        btnAddNote = findViewById(R.id.btn_add_note);
        btnDatePicker = findViewById(R.id.btn_date_picker);



        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnAddNote.setOnClickListener(this);
        btnDatePicker.setOnClickListener(this);
        textViewDate = findViewById(R.id.amount_date);

        layoutAmount = findViewById(R.id.layout_amount);

        textViewDayBudgetInt = findViewById(R.id.amount_day_budget_int);
        textViewMonthBudgetInt = findViewById(R.id.amount_month_budget_int);
        textViewDayBudgetDecimal = findViewById(R.id.amount_day_budget_decimal);
        textViewMonthBudgetDecimal = findViewById(R.id.amount_month_budget_decimal);

        dayBackground = findViewById(R.id.day_background);
        monthBackground = findViewById(R.id.month_background);
        animationRedToGreenDay = new ValueAnimator();
        animationGreenToRedDay = new ValueAnimator();
        animationRedToGreenMonth = new ValueAnimator();
        animationGreenToRedMonth = new ValueAnimator();

        presenter = new PresenterAmount(this, new DBHelper(this), this);

        animationRedToGreenDay.setIntValues(getResources().getColor(R.color.color_red_background), getResources().getColor(R.color.color_blue_background));
        animationRedToGreenDay.setEvaluator(new ArgbEvaluator());
        animationRedToGreenDay.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                dayBackground.setBackgroundColor((Integer) valueAnimator.getAnimatedValue());
            }
        });
        animationRedToGreenDay.setDuration(ANIMATION_TIME_COLORS);

        animationGreenToRedDay.setIntValues(getResources().getColor(R.color.color_blue_background), getResources().getColor(R.color.color_red_background));
        animationGreenToRedDay.setEvaluator(new ArgbEvaluator());
        animationGreenToRedDay.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                dayBackground.setBackgroundColor((Integer) valueAnimator.getAnimatedValue());
            }
        });
        animationGreenToRedDay.setDuration(ANIMATION_TIME_COLORS);


        animationRedToGreenMonth.setIntValues(getResources().getColor(R.color.color_red_background), getResources().getColor(R.color.color_blue_background));
        animationRedToGreenMonth.setEvaluator(new ArgbEvaluator());
        animationRedToGreenMonth.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                monthBackground.setBackgroundColor((Integer) valueAnimator.getAnimatedValue());
            }
        });
        animationRedToGreenMonth.setDuration(ANIMATION_TIME_COLORS);

        animationGreenToRedMonth.setIntValues(getResources().getColor(R.color.color_blue_background), getResources().getColor(R.color.color_red_background));
        animationGreenToRedMonth.setEvaluator(new ArgbEvaluator());
        animationGreenToRedMonth.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                monthBackground.setBackgroundColor((Integer) valueAnimator.getAnimatedValue());
            }
        });
        animationGreenToRedMonth.setDuration(ANIMATION_TIME_COLORS);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_to_bottom, R.anim.slide_from_top);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.amountBackButton:
                onBackPressed();
                break;
            case R.id.btn0:
                presenter.numberClicked(0.0);
                break;
            case R.id.btn1:
                presenter.numberClicked(1.0);
                break;
            case R.id.btn2:
                presenter.numberClicked(2.0);
                break;
            case R.id.btn3:
                presenter.numberClicked(3.0);
                break;
            case R.id.btn4:
                presenter.numberClicked(4.0);
                break;
            case R.id.btn5:
                presenter.numberClicked(5.0);
                break;
            case R.id.btn6:
                presenter.numberClicked(6.0);
                break;
            case R.id.btn7:
                presenter.numberClicked(7.0);
                break;
            case R.id.btn8:
                presenter.numberClicked(8.0);
                break;
            case R.id.btn9:
                presenter.numberClicked(9.0);
                break;

            case R.id.btnDelete:
                presenter.deleteClicked();
                break;
            case R.id.btnOk:
                presenter.addPaymentClicked(date, textViewAmount.getText().toString(), note);

                break;
            case R.id.btn_add_note:
                startNoteActivity();
                break;
            case R.id.btn_date_picker:
                presenter.choseDateClicked();

        }
    }

    @Override
    public void showDatePicker(Long minDate, Long maxDate) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));


        calendar.setTime(new Date(minDate));
        datePicker.getDatePicker().setMinDate(calendar.getTimeInMillis());
        calendar.setTime(new Date(maxDate));
        datePicker.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        datePicker.setTitle("");
        datePicker.show();


    }

    @Override
    public void startNoteActivity() {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivityForResult(intent, 1);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK) {
            note = data.getStringExtra("note");
            btnAddNote.setText(note);
        }
    }

    @Override
    public void paymentAdded() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_to_bottom, R.anim.slide_from_top);

    }

    @Override
    public void amountIsEmpty() {
        Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        layoutAmount.startAnimation(animShake);
    }


    @Override
    public void countIntAnimationDay(Integer start, Integer end, final boolean endIsPositive) {
        if (endIsPositive && dayBackgroundIsRed) {
            dayBackgroundIsRed = false;
            animationRedToGreenDay.start();
        } else if (!endIsPositive && !dayBackgroundIsRed) {
            animationGreenToRedDay.start();
            dayBackgroundIsRed = true;
            dayBackground.setBackground(getResources().getDrawable(R.color.color_red_background));

        }
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(start, end);// here you set the range, from 0 to "count" value
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textViewDayBudgetInt.setText(String.valueOf(animation.getAnimatedValue()));
                if (Integer.parseInt(textViewDayBudgetInt.getText().toString()) == 0 && (!endIsPositive)) {
                    textViewDayBudgetInt.setText("-0");
                }
            }
        });
        animator.setDuration(ANIMATION_TIME_NUMBERS);
        animator.start();
    }


    @Override
    public void countDecimalAnimationDay(Integer start, Integer end) {

        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(start, end);// here you set the range, from 0 to "count" value
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textViewDayBudgetDecimal.setText(String.valueOf(animation.getAnimatedValue()));
                if (Integer.parseInt(textViewDayBudgetDecimal.getText().toString()) == 0) {
                    textViewDayBudgetDecimal.setText("00");
                } else if (Integer.parseInt(textViewDayBudgetDecimal.getText().toString()) < 10) {
                    textViewDayBudgetDecimal.setText("0" + textViewDayBudgetDecimal.getText().toString());
                }
            }
        });
        animator.setDuration(ANIMATION_TIME_NUMBERS);
        animator.start();

    }

    @Override
    public void countIntAnimationMonth(Integer start, Integer end, final boolean endIsPositive) {
        if (endIsPositive && monthBackgroundIsRed) {
            animationRedToGreenMonth.start();
            monthBackgroundIsRed = false;
            monthBackground.setBackground(getResources().getDrawable(R.color.color_blue_background));
        } else if (!endIsPositive && !monthBackgroundIsRed) {
            animationGreenToRedMonth.start();

            monthBackgroundIsRed = true;
            monthBackground.setBackground(getResources().getDrawable(R.color.color_red_background));

        }

        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(start, end);// here you set the range, from 0 to "count" value
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textViewMonthBudgetInt.setText(String.valueOf(animation.getAnimatedValue()));
                if (Integer.parseInt(textViewMonthBudgetInt.getText().toString()) == 0 && (!endIsPositive)) {
                    textViewMonthBudgetInt.setText("-0");
                }
            }
        });
        animator.setDuration(ANIMATION_TIME_NUMBERS);
        animator.start();
    }

    @Override
    public void countDecimalAnimationMonth(Integer start, Integer end) {

        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(start, end);// here you set the range, from 0 to "count" value
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textViewMonthBudgetDecimal.setText(String.valueOf(animation.getAnimatedValue()));
                if (Integer.parseInt(textViewMonthBudgetDecimal.getText().toString()) == 0) {
                    textViewMonthBudgetDecimal.setText("00");
                } else if (Integer.parseInt(textViewMonthBudgetDecimal.getText().toString()) < 10) {
                    textViewMonthBudgetDecimal.setText("0" + textViewMonthBudgetDecimal.getText().toString());
                }
            }
        });
        animator.setDuration(ANIMATION_TIME_NUMBERS);
        animator.start();

    }

    @Override
    public void setDayAndMonthBackgrounds(Boolean dayBudgetPlus, Boolean monthBudgetPlus) {
        if (dayBudgetPlus) {
            dayBackgroundIsRed = false;
            dayBackground.setBackground(getResources().getDrawable(R.color.color_blue_background));
        } else {
            dayBackgroundIsRed = true;
            dayBackground.setBackground(getResources().getDrawable(R.color.color_red_background));

        }

        if (monthBudgetPlus) {
            monthBackgroundIsRed = false;
            monthBackground.setBackground(getResources().getDrawable(R.color.color_blue_background));
        } else {
            monthBackgroundIsRed = true;
            monthBackground.setBackground(getResources().getDrawable(R.color.color_red_background));

        }
    }

    @Override
    public void showDate(String date) {
        textViewDate.setText(date);
    }


    @Override
    public void showAmount(String amInt, String amDec) {
        textViewAmount.setText(amInt + amDec);
    }

    @Override
    public void showCurrency(String currency) {
        TextView textViewCurrency = findViewById(R.id.amount_text_view_currency);
        TextView textViewDayCurrency = findViewById(R.id.amount_day_currency);
        TextView textViewMonthCurrency = findViewById(R.id.amount_month_currency);
        textViewCurrency.setText(currency);
        textViewDayCurrency.setText(currency);
        textViewMonthCurrency.setText(currency);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTime().getTime();
        presenter.dateChosen(date);
        DialogFragment dialogFragment = new TimePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), null);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        date = calendar.getTime().getTime();
        presenter.dateChosen(date);



    }


}
