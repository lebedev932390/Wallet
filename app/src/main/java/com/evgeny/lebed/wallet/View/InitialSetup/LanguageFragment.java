package com.evgeny.lebed.wallet.View.InitialSetup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.evgeny.lebed.wallet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LanguageFragment extends Fragment implements View.OnClickListener {


    public LanguageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_language, container, false);
        Button btnRussian = view.findViewById(R.id.btn_russian);
        Button btnEnglish = view.findViewById(R.id.btn_english);
        btnRussian.setOnClickListener(this);
        btnEnglish.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_russian:
                ((InitialSetupActivity) getActivity()).languageChosen(R.id.btn_russian);
                break;
            case R.id.btn_english:
                ((InitialSetupActivity) getActivity()).languageChosen(R.id.btn_english);

                break;
        }
    }
}
