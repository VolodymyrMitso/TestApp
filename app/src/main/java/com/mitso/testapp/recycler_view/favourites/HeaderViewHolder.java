package com.mitso.testapp.recycler_view.favourites;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mitso.testapp.databinding.CardHeaderBinding;

public class HeaderViewHolder extends RecyclerView.ViewHolder {

    private CardHeaderBinding mBinding;

    public HeaderViewHolder(View _itemView) {
        super(_itemView);

        mBinding = DataBindingUtil.bind(_itemView);
    }

    public CardHeaderBinding getBinding() {
        return mBinding;
    }
}
