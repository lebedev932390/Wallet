package com.evgeny.lebed.wallet.View.InitialSetup;


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
public class StartDateFragment extends Fragment implements View.OnClickListener {

    private TextView textViewStartDate;
    public StartDateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_date, container, false);
        ImageButton btnDelete, btnOk;
        Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
        textViewStartDate = view.findViewById(R.id.text_view_start_date_is);
        btnDelete = view.findViewById(R.id.button_delete_is);
        btnOk = view.findViewById(R.id.button_ok_is);
        btn1 = view.findViewById(R.id.btn1_is);
        btn2 = view.findViewById(R.id.btn2_is);
        btn3 = view.findViewById(R.id.btn3_is);
        btn4 = view.findViewById(R.id.btn4_is);
        btn5 = view.findViewById(R.id.btn5_is);
        btn6 = view.findViewById(R.id.btn6_is);
        btn7 = view.findViewById(R.id.btn7_is);
        btn8 = view.findViewById(R.id.btn8_is);
        btn9 = view.findViewById(R.id.btn9_is);
        btn0 = view.findViewById(R.id.btn0_is);

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



        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn0_is:
                ((InitialSetupActivity) getActivity()).numberClickedStartDate(R.id.btn0_is);
                break;
            case R.id.btn1_is:
                ((InitialSetupActivity) getActivity()).numberClickedStartDate(R.id.btn1_is);

                break;
            case R.id.btn2_is:
                ((InitialSetupActivity) getActivity()).numberClickedStartDate(R.id.btn2_is);

                break;
            case R.id.btn3_is:
                ((InitialSetupActivity) getActivity()).numberClickedStartDate(R.id.btn3_is);

                break;
            case R.id.btn4_is:
                ((InitialSetupActivity) getActivity()).numberClickedStartDate(R.id.btn4_is);

                break;
            case R.id.btn5_is:
                ((InitialSetupActivity) getActivity()).numberClickedStartDate(R.id.btn5_is);

                break;
            case R.id.btn6_is:
                ((InitialSetupActivity) getActivity()).numberClickedStartDate(R.id.btn6_is);

                break;
            case R.id.btn7_is:
                ((InitialSetupActivity) getActivity()).numberClickedStartDate(R.id.btn7_is);

                break;
            case R.id.btn8_is:
                ((InitialSetupActivity) getActivity()).numberClickedStartDate(R.id.btn8_is);

                break;
            case R.id.btn9_is:
                ((InitialSetupActivity) getActivity()).numberClickedStartDate(R.id.btn9_is);

                break;
            case R.id.button_delete_is:
                ((InitialSetupActivity) getActivity()).numberClickedStartDate(R.id.button_delete_is);
                break;
            case R.id.button_ok_is:
                ((InitialSetupActivity) getActivity()).startDateEntered((textViewStartDate.getText().toString()));
                break;

        }
    }
}
