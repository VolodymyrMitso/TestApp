package com.mitso.testapp.recycler_view.common;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int     mSpaceSize;

    public SpaceItemDecoration(int _spaceSize) {

        this.mSpaceSize = _spaceSize;
    }

    @Override
    public void getItemOffsets(Rect _outRect, View _view, RecyclerView _parent, RecyclerView.State _state) {

        _outRect.top = mSpaceSize;

        if (_parent.getChildAdapterPosition(_view) == _parent.getAdapter().getItemCount() - 1) {
            _outRect.bottom = mSpaceSize;
        }
    }
}
