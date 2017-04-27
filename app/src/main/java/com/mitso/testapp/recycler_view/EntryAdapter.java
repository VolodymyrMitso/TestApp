package com.mitso.testapp.recycler_view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mitso.testapp.constants.Constants;
import com.mitso.testapp.databinding.CardEntryBinding;
import com.mitso.testapp.models.Entry;
import com.squareup.picasso.Picasso;

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

        return new EntryViewHolder(CardEntryBinding.inflate(LayoutInflater.from(_parent.getContext()), _parent, false).getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder _holder, int _position) {

        final Entry entry = mEntryList.get(_position);
        final EntryViewHolder userViewHolder = (EntryViewHolder) _holder;

        userViewHolder.getBinding().setEntry(entry);

        userViewHolder.getBinding().setClickerInfo(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEntryHandler.showInfo(entry);
            }
        });

        userViewHolder.getBinding().setClickerAdd(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEntryHandler.addToFavourites(entry);
            }
        });

        final String imageLink = entry.getImImage().get(1).getLabel();
        Picasso.with(mContext).load(imageLink).into(userViewHolder.getBinding().ivImage60x60Ce);
    }

    @Override
    public int getItemCount() {

        return mEntryList.size();
    }

    public void setCommonHandler(IEntryHandler _entryHandler) {

        if (mEntryHandler == null) {
            mEntryHandler = _entryHandler;
            Log.i(LOG_TAG, "HANDLER IS SET.");
        }
    }

    public void releaseCommonHandler() {

        if (mEntryHandler != null) {
            mEntryHandler = null;
            Log.i(LOG_TAG, "HANDLER IS NULL.");
        }
    }
}