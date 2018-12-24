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
public class BudgetFragment extends Fragment implements View.OnClickListener {

    TextView textViewAmount;

    public BudgetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_budget, container, false);
        TextView textViewCurrency;
        ImageButton btnDelete, btnOk;
        Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
        textViewAmount = view.findViewById(R.id.text_view_amount_f);
        textViewCurrency = view.findViewById(R.id.text_view_currency_f);
        btnDelete = view.findViewById(R.id.button_delete_f);
        btnOk = view.findViewById(R.id.button_ok_f);
        btn1 = view.findViewById(R.id.btn1f);
        btn2 = view.findViewById(R.id.btn2f);
        btn3 = view.findViewById(R.id.btn3f);
        btn4 = view.findViewById(R.id.btn4f);
        btn5 = view.findViewById(R.id.btn5f);
        btn6 = view.findViewById(R.id.btn6f);
        btn7 = view.findViewById(R.id.btn7f);
        btn8 = view.findViewById(R.id.btn8f);
        btn9 = view.findViewById(R.id.btn9f);
        btn0 = view.findViewById(R.id.btn0f);

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



        textViewCurrency.setText(((InitialSetupActivity) getActivity()).getCurrency());
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn0f:
                ((InitialSetupActivity) getActivity()).numberClicked(R.id.btn0f);
                break;
            case R.id.btn1f:
                ((InitialSetupActivity) getActivity()).numberClicked(R.id.btn1f);

                break;
            case R.id.btn2f:
                ((InitialSetupActivity) getActivity()).numberClicked(R.id.btn2f);

                break;
            case R.id.btn3f:
                ((InitialSetupActivity) getActivity()).numberClicked(R.id.btn3f);

                break;
            case R.id.btn4f:
                ((InitialSetupActivity) getActivity()).numberClicked(R.id.btn4f);

                break;
            case R.id.btn5f:
                ((InitialSetupActivity) getActivity()).numberClicked(R.id.btn5f);

                break;
            case R.id.btn6f:
                ((InitialSetupActivity) getActivity()).numberClicked(R.id.btn6f);

                break;
            case R.id.btn7f:
                ((InitialSetupActivity) getActivity()).numberClicked(R.id.btn7f);

                break;
            case R.id.btn8f:
                ((InitialSetupActivity) getActivity()).numberClicked(R.id.btn8f);

                break;
            case R.id.btn9f:
                ((InitialSetupActivity) getActivity()).numberClicked(R.id.btn9f);

                break;
            case R.id.button_delete_f:
                ((InitialSetupActivity) getActivity()).numberClicked(R.id.button_delete_f);
                break;
            case R.id.button_ok_f:
                ((InitialSetupActivity) getActivity()).budgetEntered((textViewAmount.getText().toString()));
                break;

        }
    }
}
