package com.evgeny.lebed.wallet.View.PaymentHistory;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evgeny.lebed.wallet.Class.Payment;
import com.evgeny.lebed.wallet.Interface.ContractPresenter;
import com.evgeny.lebed.wallet.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PaymentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ContractPresenter.PaymentHistory presenter;
    private List listOfPaymentsWithHeaders;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", new Locale("ru"));
    private String currency;

    public PaymentAdapter(List listOfPaymentsWithHeaders, ContractPresenter.PaymentHistory presenter) {

        this.listOfPaymentsWithHeaders = listOfPaymentsWithHeaders;
        this.presenter = presenter;
        currency = presenter.getCurrency();
    }


    public static class PaymentViewHolder extends RecyclerView.ViewHolder {

        public TextView paymentAmount;
        public TextView paymentNote;
        public TextView paymentTime;
        public TextView paymentCurrency;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            paymentAmount = itemView.findViewById(R.id.payment_amount_card_view);
            paymentNote = itemView.findViewById(R.id.payment_note_card_view);
            paymentTime = itemView.findViewById(R.id.payment_time_card_view);
            paymentCurrency = itemView.findViewById(R.id.payment_currency_card_view);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView header;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.date_card_view);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.payment_card_view, viewGroup, false);
                return new PaymentViewHolder(view);
            case 1:
                View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_card_view, viewGroup, false);
                return new HeaderViewHolder(view1);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        switch (viewHolder.getItemViewType()) {
            case 0:
                PaymentViewHolder paymentViewHolder = (PaymentViewHolder) viewHolder;
                if (((Payment) listOfPaymentsWithHeaders.get(i)).getAmount() * 10 % 1 == 0) {
                    paymentViewHolder.paymentAmount.setText(Double.toString(((Payment) listOfPaymentsWithHeaders.get(i)).getAmount()) + "0");
                } else {
                    paymentViewHolder.paymentAmount.setText(Double.toString(((Payment) listOfPaymentsWithHeaders.get(i)).getAmount()));

                }

                paymentViewHolder.paymentCurrency.setText(currency);
                if (((Payment) listOfPaymentsWithHeaders.get(i)).getNote().equals("")) {
                    paymentViewHolder.paymentNote.setVisibility(View.GONE);
                } else {
                    paymentViewHolder.paymentNote.setText(((Payment) listOfPaymentsWithHeaders.get(i)).getNote());
                }
                paymentViewHolder.paymentTime.setText(dateFormat.format(new Date(((Payment) listOfPaymentsWithHeaders.get(i)).getDate())));
                break;
            case 1:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
                headerViewHolder.header.setText((String) listOfPaymentsWithHeaders.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return listOfPaymentsWithHeaders.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (listOfPaymentsWithHeaders.get(position).getClass().getName().equals(Payment.class.getName())) {
            return 0;
        } else return 1;
    }
}
