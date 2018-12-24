package com.evgeny.lebed.wallet.View.InitialSetup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.evgeny.lebed.wallet.Class.WalletMethods;
import com.evgeny.lebed.wallet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrencyFragment extends Fragment implements View.OnClickListener {


    public CurrencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency, container, false);
        Button btnDollar, btnEuro, btnUkrainianHryvnia, btnKazakhstanTenge, btnIsraelShekel, btnKrona, btnPoundSterling, btnPolishZloty, btnRussianRuble, btnBelarusianRuble, btnSwissFrank;
        btnDollar = view.findViewById(R.id.btn_dollar);
        btnEuro = view.findViewById(R.id.btn_euro);
        btnUkrainianHryvnia = view.findViewById(R.id.btn_ukrainian_hryvnia);
        btnKazakhstanTenge = view.findViewById(R.id.btn_kazakhstan_tenge);
        btnIsraelShekel = view.findViewById(R.id.btn_israel_shekel);
        btnPoundSterling = view.findViewById(R.id.btn_pound_sterling);
        btnPolishZloty = view.findViewById(R.id.btn_polish_zloty);
        btnRussianRuble = view.findViewById(R.id.btn_russian_ruble);
        btnBelarusianRuble = view.findViewById(R.id.btn_belarusian_ruble);
        btnKrona = view.findViewById(R.id.btn_krona);
        btnSwissFrank = view.findViewById(R.id.btn_swiss_frank);



        btnDollar.setOnClickListener(this);
        btnEuro.setOnClickListener(this);
        btnUkrainianHryvnia .setOnClickListener(this);
        btnKazakhstanTenge.setOnClickListener(this);
        btnIsraelShekel.setOnClickListener(this);
        btnPoundSterling.setOnClickListener(this);
        btnPolishZloty.setOnClickListener(this);
        btnRussianRuble.setOnClickListener(this);
        btnBelarusianRuble.setOnClickListener(this);
        btnKrona.setOnClickListener(this);
        btnSwissFrank.setOnClickListener(this);

        btnDollar.setText(WalletMethods.DOLLAR.getName(getActivity()));
        btnEuro.setText(WalletMethods.EURO.getName(getActivity()));
        btnUkrainianHryvnia .setText(WalletMethods.UKRAINIAN_HRYVNIA.getName(getActivity()));
        btnKazakhstanTenge.setText(WalletMethods.KAZAKHSTANI_TENGE.getName(getActivity()));
        btnIsraelShekel.setText(WalletMethods.ISRAELI_SHEKEL.getName(getActivity()));
        btnPoundSterling.setText(WalletMethods.POUND_STERLING.getName(getActivity()));
        btnPolishZloty.setText(WalletMethods.POLISH_ZLOTY.getName(getActivity()));
        btnRussianRuble.setText(WalletMethods.RUSSIAN_RUBLE.getName(getActivity()));
        btnBelarusianRuble.setText(WalletMethods.BELARUSIAN_RUBLE.getName(getActivity()));
        btnKrona.setText(WalletMethods.KRONA.getName(getActivity()));
        btnSwissFrank.setText(WalletMethods.SWISS_FRANK.getName(getActivity()));




        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dollar:
                ((InitialSetupActivity) getActivity()).currencyChosen(R.id.btn_dollar);
                break;
            case R.id.btn_russian_ruble:
                ((InitialSetupActivity) getActivity()).currencyChosen(R.id.btn_russian_ruble);
                break;
            case R.id.btn_euro:
                ((InitialSetupActivity) getActivity()).currencyChosen(R.id.btn_euro);
                break;
            case R.id.btn_belarusian_ruble:
                ((InitialSetupActivity) getActivity()).currencyChosen(R.id.btn_belarusian_ruble);
                break;
            case R.id.btn_krona:
                ((InitialSetupActivity) getActivity()).currencyChosen(R.id.btn_krona);
                break;
            case R.id.btn_pound_sterling:
                ((InitialSetupActivity) getActivity()).currencyChosen(R.id.btn_pound_sterling);
                break;
            case R.id.btn_polish_zloty:
                ((InitialSetupActivity) getActivity()).currencyChosen(R.id.btn_polish_zloty);
                break;
            case R.id.btn_kazakhstan_tenge:
                ((InitialSetupActivity) getActivity()).currencyChosen(R.id.btn_kazakhstan_tenge);
                break;
            case R.id.btn_ukrainian_hryvnia:
                ((InitialSetupActivity) getActivity()).currencyChosen(R.id.btn_ukrainian_hryvnia);
                break;
            case R.id.btn_israel_shekel:
                ((InitialSetupActivity) getActivity()).currencyChosen(R.id.btn_israel_shekel);
                break;
            case R.id.btn_swiss_frank:
                ((InitialSetupActivity) getActivity()).currencyChosen(R.id.btn_swiss_frank);
                break;

        }
    }
}
