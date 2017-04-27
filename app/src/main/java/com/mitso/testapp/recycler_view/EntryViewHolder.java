package com.mitso.testapp.recycler_view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mitso.testapp.databinding.CardEntryBinding;

public class EntryViewHolder extends RecyclerView.ViewHolder {

    private CardEntryBinding        mBinding;

    public EntryViewHolder(View _itemView) {
        super(_itemView);

        mBinding = DataBindingUtil.bind(_itemView);
    }

    public CardEntryBinding getBinding() {
        return mBinding;
    }
}
