package com.evgeny.lebed.wallet.View.Settings;


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
public class LanguageSettingsFragment extends Fragment implements View.OnClickListener {


    public LanguageSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_language_settings, container, false);

        Button buttonEnglish, buttonRussian;
        buttonEnglish = view.findViewById(R.id.btn_english);
        buttonRussian = view.findViewById(R.id.btn_russian);

        buttonEnglish.setOnClickListener(this);
        buttonRussian.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        ((SettingsActivity)getActivity()).languageChosen(v.getId());

    }
}
