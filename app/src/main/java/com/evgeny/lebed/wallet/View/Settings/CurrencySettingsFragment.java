package com.evgeny.lebed.wallet.View.Settings;


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
public class CurrencySettingsFragment extends Fragment implements View.OnClickListener {


    public CurrencySettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency_settings, container, false);

        Button btnDollar, btnEuro, btnUkrainianHryvnia, btnKazakhstanTenge, btnIsraelShekel, btnKrona, btnPoundSterling, btnPolishZloty, btnRussianRuble, btnBelarusianRuble, btnSwissFrank;
        btnDollar = view.findViewById(R.id.btn_dollar_f);
        btnEuro = view.findViewById(R.id.btn_euro_f);
        btnUkrainianHryvnia = view.findViewById(R.id.btn_ukrainian_hryvnia_f);
        btnKazakhstanTenge = view.findViewById(R.id.btn_kazakhstan_tenge_f);
        btnIsraelShekel = view.findViewById(R.id.btn_israel_shekel_f);
        btnPoundSterling = view.findViewById(R.id.btn_pound_sterling_f);
        btnPolishZloty = view.findViewById(R.id.btn_polish_zloty_f);
        btnRussianRuble = view.findViewById(R.id.btn_russian_ruble_f);
        btnBelarusianRuble = view.findViewById(R.id.btn_belarusian_ruble_f);
        btnKrona = view.findViewById(R.id.btn_krona_f);
        btnSwissFrank = view.findViewById(R.id.btn_swiss_frank_f);



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
            case R.id.btn_dollar_f:
                ((SettingsActivity) getActivity()).currencyChosen(R.id.btn_dollar_f);
                break;
            case R.id.btn_russian_ruble_f:
                ((SettingsActivity) getActivity()).currencyChosen(R.id.btn_russian_ruble_f);
                break;
            case R.id.btn_euro_f:
                ((SettingsActivity) getActivity()).currencyChosen(R.id.btn_euro_f);
                break;
            case R.id.btn_belarusian_ruble_f:
                ((SettingsActivity) getActivity()).currencyChosen(R.id.btn_belarusian_ruble_f);
                break;
            case R.id.btn_krona_f:
                ((SettingsActivity) getActivity()).currencyChosen(R.id.btn_krona_f);
                break;
            case R.id.btn_pound_sterling_f:
                ((SettingsActivity) getActivity()).currencyChosen(R.id.btn_pound_sterling_f);
                break;
            case R.id.btn_polish_zloty_f:
                ((SettingsActivity) getActivity()).currencyChosen(R.id.btn_polish_zloty_f);
                break;
            case R.id.btn_kazakhstan_tenge_f:
                ((SettingsActivity) getActivity()).currencyChosen(R.id.btn_kazakhstan_tenge_f);
                break;
            case R.id.btn_ukrainian_hryvnia_f:
                ((SettingsActivity) getActivity()).currencyChosen(R.id.btn_ukrainian_hryvnia_f);
                break;
            case R.id.btn_israel_shekel_f:
                ((SettingsActivity) getActivity()).currencyChosen(R.id.btn_israel_shekel_f);
                break;
            case R.id.btn_swiss_frank_f:
                ((SettingsActivity) getActivity()).currencyChosen(R.id.btn_swiss_frank_f);
                break;

        }
    }
}
