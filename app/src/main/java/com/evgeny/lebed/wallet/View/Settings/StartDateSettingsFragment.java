package com.evgeny.lebed.wallet.View.Settings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.evgeny.lebed.wallet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartDateSettingsFragment extends Fragment implements View.OnClickListener {

    private TextView textViewStartDate;
    public StartDateSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_date_settings, container, false);
        ImageButton btnDelete, btnOk;
        Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
        textViewStartDate = view.findViewById(R.id.text_view_start_date_f_s_sd);
        btnDelete = view.findViewById(R.id.button_delete__f_s_sd);
        btnOk = view.findViewById(R.id.button_ok_f_s_sd);
        btn1 = view.findViewById(R.id.btn1_f_s_sd);
        btn2 = view.findViewById(R.id.btn2_f_s_sd);
        btn3 = view.findViewById(R.id.btn3_f_s_sd);
        btn4 = view.findViewById(R.id.btn4_f_s_sd);
        btn5 = view.findViewById(R.id.btn5_f_s_sd);
        btn6 = view.findViewById(R.id.btn6_f_s_sd);
        btn7 = view.findViewById(R.id.btn7_f_s_sd);
        btn8 = view.findViewById(R.id.btn8_f_s_sd);
        btn9 = view.findViewById(R.id.btn9_f_s_sd);
        btn0 = view.findViewById(R.id.btn0_f_s_sd);

        btnDelete.setOnClickListener(this);
        btnOk.setOnClickListener(this);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);



        textViewStartDate.setText(((SettingsActivity) getActivity()).getEnteredStartDate());
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn0_f_s_sd:
                ((SettingsActivity) getActivity()).numberClickedStartDate(R.id.btn0_f_s_sd);
                break;
            case R.id.btn1_f_s_sd:
                ((SettingsActivity) getActivity()).numberClickedStartDate(R.id.btn1_f_s_sd);

                break;
            case R.id.btn2_f_s_sd:
                ((SettingsActivity) getActivity()).numberClickedStartDate(R.id.btn2_f_s_sd);

                break;
            case R.id.btn3_f_s_sd:
                ((SettingsActivity) getActivity()).numberClickedStartDate(R.id.btn3_f_s_sd);

                break;
            case R.id.btn4_f_s_sd:
                ((SettingsActivity) getActivity()).numberClickedStartDate(R.id.btn4_f_s_sd);

                break;
            case R.id.btn5_f_s_sd:
                ((SettingsActivity) getActivity()).numberClickedStartDate(R.id.btn5_f_s_sd);

                break;
            case R.id.btn6_f_s_sd:
                ((SettingsActivity) getActivity()).numberClickedStartDate(R.id.btn6_f_s_sd);

                break;
            case R.id.btn7_f_s_sd:
                ((SettingsActivity) getActivity()).numberClickedStartDate(R.id.btn7_f_s_sd);

                break;
            case R.id.btn8_f_s_sd:
                ((SettingsActivity) getActivity()).numberClickedStartDate(R.id.btn8_f_s_sd);

                break;
            case R.id.btn9_f_s_sd:
                ((SettingsActivity) getActivity()).numberClickedStartDate(R.id.btn9_f_s_sd);

                break;
            case R.id.button_delete__f_s_sd:
                ((SettingsActivity) getActivity()).numberClickedStartDate(R.id.button_delete__f_s_sd);
                break;
            case R.id.button_ok_f_s_sd:
                ((SettingsActivity) getActivity()).startDateEntered((textViewStartDate.getText().toString()));
                break;

        }
    }
}
