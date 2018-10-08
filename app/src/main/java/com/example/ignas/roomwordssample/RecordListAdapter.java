package com.example.ignas.roomwordssample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecordListAdapter extends RecyclerView.Adapter<RecordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Record> mRecords; // Cached copy of words

    RecordListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mRecords != null) {
            Record current = mRecords.get(position);
            holder.pswItemView.setText(current.getWord());
            holder.titleItemView.setText(current.getTitle());
        } else {
            // Covers the case of data not being ready yet.
            holder.pswItemView.setText("No Record");
            holder.titleItemView.setText("No title");
        }
    }

    void setWords(List<Record> records){
        mRecords = records;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mRecords != null)
            return mRecords.size();
        else return 0;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView pswItemView;
        private final TextView titleItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            pswItemView = itemView.findViewById(R.id.pswTextView);
            titleItemView = itemView.findViewById(R.id.titleTextView);
        }
    }

    Record getWordAtPosition (int position) {
        return mRecords.get(position);
    }
}
