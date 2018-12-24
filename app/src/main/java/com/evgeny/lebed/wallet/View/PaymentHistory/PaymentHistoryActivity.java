package com.evgeny.lebed.wallet.View.PaymentHistory;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageButton;

import com.evgeny.lebed.wallet.Class.Payment;
import com.evgeny.lebed.wallet.Interface.ContractPresenter;
import com.evgeny.lebed.wallet.Interface.ContractView;
import com.evgeny.lebed.wallet.Model.DBHelper;
import com.evgeny.lebed.wallet.Presenter.PresenterPaymentHistory;
import com.evgeny.lebed.wallet.R;

import java.util.List;

public class PaymentHistoryActivity extends AppCompatActivity implements ContractView.PaymentHistory, View.OnClickListener {

    ContractPresenter.PaymentHistory presenter;
    List listOfPayments;
    private boolean paymentDeleted = false;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);
        init();
    }

    private void init() {
        presenter = new PresenterPaymentHistory(this, new DBHelper(this), this);
        ImageButton btnBack = findViewById(R.id.paymentHistoryBackButton);
        btnBack.setOnClickListener(this);

        listOfPayments = presenter.getListOfPaymentsWithHeaders();

        buildRecyclerView();
    }

    private void buildRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view_payment_history);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new PaymentAdapter(listOfPayments, presenter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            //метод отключает свайп для холдера типа хэдэр
            @Override
            public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                if (viewHolder instanceof PaymentAdapter.HeaderViewHolder) return 0;
                return super.getSwipeDirs(recyclerView, viewHolder);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                paymentDeleted = true;
                presenter.deletePaymentClicked(((Payment) listOfPayments.get(viewHolder.getAdapterPosition())).getId());
                listOfPayments.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());


            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.paymentHistoryBackButton:


                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (paymentDeleted){
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
        }

        super.onBackPressed();

    }
}
