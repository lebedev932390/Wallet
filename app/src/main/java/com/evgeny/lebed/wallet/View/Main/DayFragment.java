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
public class DayFragment extends Fragment {

    private ContractPresenter.Main presenter;



    public DayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        presenter = ((MainActivity)getActivity()).getPresenter();
        TextView textView = view.findViewById(R.id.fragment_day_period);
        textView.setText(presenter.getDayStatus());
        return view;
    }

}
