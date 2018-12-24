package com.evgeny.lebed.wallet.View.Main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evgeny.lebed.wallet.Interface.ContractPresenter;
import com.evgeny.lebed.wallet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonthFragment extends Fragment {

    private ContractPresenter.Main presenter;

    public MonthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_month, container, false);

        presenter = ((MainActivity)getActivity()).getPresenter();
        TextView textView = view.findViewById(R.id.fragment_month_period);
        textView.setText(presenter.getMonthStatus());


        return view;
    }

}
