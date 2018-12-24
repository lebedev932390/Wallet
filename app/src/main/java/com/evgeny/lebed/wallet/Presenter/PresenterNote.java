package com.evgeny.lebed.wallet.Presenter;


import android.util.Log;

import com.evgeny.lebed.wallet.Interface.ContractModel;
import com.evgeny.lebed.wallet.Interface.ContractPresenter;
import com.evgeny.lebed.wallet.Interface.ContractView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class PresenterNote implements ContractPresenter.Note {

    private ContractView.Note view;
    private ContractModel model;

    public PresenterNote(ContractView.Note view, ContractModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public List<String> getListOfNotes() {
        List<String> notes = model.getNotes();
        Collections.sort(notes);
        return notes;
    }

    @Override
    public void noteChosenClicked(String note) {
        view.missionAccomplished(note);
    }

    @Override
    public void noteWrittenClicked(String note) {


        if (noteIsOk(note)){
            while (note.charAt(0) == ' ') {
                note = note.substring(1);
            }

            while (note.charAt(note.length() - 1) == ' ') {
                note = note.substring(0, note.length() - 1);
            }

            if (!model.noteExists(note)) {

                model.insertNote(note);
            }
            Log.e("NOTE", note);
            view.missionAccomplished(note);
        }
        else view.amountIsEmpty();
    }


    private boolean noteIsOk(String note) {

        if(note.isEmpty()){
            return false;
        }
        for (int i = 0; i < note.length(); i++) {
            if (note.charAt(i) != ' ') {
                return true;
            }
        }
        return false;
    }

    @Override
    public void forgetNoteClicked(String note) {
        model.deleteNote(note);
    }

    @Override
    public String getLanguage() {
        return model.getLanguage();
    }
}
