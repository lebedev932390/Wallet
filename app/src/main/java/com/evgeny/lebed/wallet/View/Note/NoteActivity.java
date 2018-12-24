package com.evgeny.lebed.wallet.View.Note;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;

import com.evgeny.lebed.wallet.Interface.ContractPresenter;
import com.evgeny.lebed.wallet.Interface.ContractView;
import com.evgeny.lebed.wallet.Model.DBHelper;
import com.evgeny.lebed.wallet.Presenter.PresenterNote;
import com.evgeny.lebed.wallet.R;

import java.util.List;

public class NoteActivity extends AppCompatActivity implements View.OnClickListener, ContractView.Note {


    private EditText editTextNote;

    private ContractPresenter.Note presenter;

    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> listOfNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        init();
    }

    void init() {
        presenter = new PresenterNote(this, new DBHelper(this));

        editTextNote = findViewById(R.id.edit_text_note);
        ImageButton btnBack, btnDone;
        btnBack = findViewById(R.id.noteBackButton);
        btnDone = findViewById(R.id.noteDoneButton);

        btnBack.setOnClickListener(this);
        btnDone.setOnClickListener(this);

        listOfNotes = presenter.getListOfNotes();

        buildRecyclerView();

        //блокирует автофокус едиттекста
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void buildRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new NoteAdapter(listOfNotes);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                presenter.forgetNoteClicked(listOfNotes.get(viewHolder.getAdapterPosition()));
                listOfNotes.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                editTextNote.setText(listOfNotes.get(position));

                presenter.noteChosenClicked(listOfNotes.get(position));

            }
        });


    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.noteBackButton:
                onBackPressed();
                break;
            case R.id.noteDoneButton:
                presenter.noteWrittenClicked(editTextNote.getText().toString());

                break;
        }
    }


    @Override
    public void amountIsEmpty() {
        Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        editTextNote.startAnimation(animShake);
    }

    @Override
    public void missionAccomplished(String note) {
        Intent intent = new Intent();
        intent.putExtra("note", note);
        setResult(RESULT_OK, intent);
        finish();
    }
}
