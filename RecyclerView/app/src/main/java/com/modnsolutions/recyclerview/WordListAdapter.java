package com.modnsolutions.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private final LinkedList<String> mWordList;
    private LayoutInflater mInflater;

    public WordListAdapter(Context context, LinkedList<String> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        final View mItemView = mInflater.inflate(R.layout.wordlist_item, viewGroup, false);

        final WordViewHolder holder = new WordViewHolder(mItemView, this);
        holder.wordItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the position of the item that was clicked.
                int mPosition = holder.getLayoutPosition(); // or getAdapterPosition()?

                if (mPosition != RecyclerView.NO_POSITION) {
                    // Use that to access the affected item in mWordList.
                    String element = mWordList.get(mPosition);

                    // Change the word in the mWordList.
                    mWordList.set(mPosition, "Clicked! " + element);

                    // Notify the adapter, that the data has changed so it can update the RecyclerView
                    // to display the data.
                    notifyDataSetChanged();
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder wordViewHolder, int i) {
        String mCurrent = mWordList.get(i);
        wordViewHolder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder { // implements View.OnClickListener {
        public final TextView wordItemView;
        final WordListAdapter mAdapter;

        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
//            itemView.setOnClickListener(this);
        }

        /*@Override
        public void onClick(View v) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();

            // Use that to access the affected item in mWordList.
            String element = mWordList.get(mPosition);

            // Change the word in the mWordList.
            mWordList.set(mPosition, "Clicked! " + element);

            // Notify the adapter, that the data has changed so it can update the RecyclerView
            // to display the data.
            mAdapter.notifyDataSetChanged();
        }*/
    }
}
