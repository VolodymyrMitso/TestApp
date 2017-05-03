package com.mitso.testapp.recycler_view.favourites;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mitso.testapp.databinding.CardEntryFavouriteBinding;

public class FavouriteViewHolder extends RecyclerView.ViewHolder {

    private CardEntryFavouriteBinding mBinding;

    public FavouriteViewHolder(View _itemView) {
        super(_itemView);

        mBinding = DataBindingUtil.bind(_itemView);
    }

    public CardEntryFavouriteBinding getBinding() {
        return mBinding;
    }
}
