package com.mitso.testapp.recycler_view.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.mitso.testapp.constants.Constants;
import com.mitso.testapp.databinding.CardEntryCommonBinding;
import com.mitso.testapp.models.json_entry_list.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String            LOG_TAG = Constants.ENTRY_ADAPTER_LOG_TAG;

    private Context                 mContext;

    private List<Entry>             mEntryList;

    private IEntryHandler           mEntryHandler;

    public EntryAdapter(Context _context, List<Entry> _entryList) {

        this.mContext = _context;
        this.mEntryList = new ArrayList<>(_entryList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup _parent, int _viewType) {

        return new EntryViewHolder(CardEntryCommonBinding.inflate(LayoutInflater.from(_parent.getContext()), _parent, false).getRoot());
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder _holder, int _position) {

        final Entry entry = mEntryList.get(_position);
        final EntryViewHolder entryViewHolder = (EntryViewHolder) _holder;

        entryViewHolder.getBinding().setEntry(entry);

        entryViewHolder.getBinding().setClickerInfo(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEntryHandler.showInfo(entry);
            }
        });

        entryViewHolder.getBinding().setClickerAdd(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEntryHandler.addOrDelete(entry, _holder.getAdapterPosition());
            }
        });

        Glide.with(mContext)
                .load(entry.getImImage().get(1).getLabel())
                .into(entryViewHolder.getBinding().ivCoverCec);
    }

    @Override
    public int getItemCount() {

        return mEntryList.size();
    }

    public void setEntryHandler(IEntryHandler _entryHandler) {

        if (mEntryHandler == null) {
            mEntryHandler = _entryHandler;
            Log.i(LOG_TAG, "HANDLER IS SET.");
        }
    }

    public void releaseEntryHandler() {

        if (mEntryHandler != null) {
            mEntryHandler = null;
            Log.i(LOG_TAG, "HANDLER IS NULL.");
        }
    }
}