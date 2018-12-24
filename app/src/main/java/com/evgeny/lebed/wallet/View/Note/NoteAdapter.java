package com.evgeny.lebed.wallet.View.Note;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evgeny.lebed.wallet.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private List<String> cardViewNotes;
    private OnItemClickListener listener;

    public  interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView noteRecyclerView;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            noteRecyclerView = itemView.findViewById(R.id.note_recycler_view);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public NoteAdapter(List<String> list) {
        this.cardViewNotes = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_card_view, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view, listener);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {

        viewHolder.noteRecyclerView.setText(cardViewNotes.get(i));
    }

    @Override
    public int getItemCount() {
        return cardViewNotes.size();
    }


}
