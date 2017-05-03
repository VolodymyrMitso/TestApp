package com.mitso.testapp.recycler_view.favourites;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mitso.testapp.databinding.CardSeparatorBinding;

public class SeparatorViewHolder extends RecyclerView.ViewHolder {

    private CardSeparatorBinding mBinding;

    public SeparatorViewHolder(View _itemView) {
        super(_itemView);

        mBinding = DataBindingUtil.bind(_itemView);
    }

    public CardSeparatorBinding getBinding() {
        return mBinding;
    }
}
