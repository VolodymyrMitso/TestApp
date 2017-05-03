package com.mitso.testapp.recycler_view.common;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mitso.testapp.databinding.CardEntryCommonBinding;

public class EntryViewHolder extends RecyclerView.ViewHolder {

    private CardEntryCommonBinding      mBinding;

    public EntryViewHolder(View _itemView) {
        super(_itemView);

        mBinding = DataBindingUtil.bind(_itemView);
    }

    public CardEntryCommonBinding getBinding() {
        return mBinding;
    }
}
