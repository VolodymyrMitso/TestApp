package com.mitso.testapp.recycler_view.favourites;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.mitso.testapp.constants.Constants;
import com.mitso.testapp.databinding.CardEntryFavouriteBinding;
import com.mitso.testapp.databinding.CardHeaderBinding;
import com.mitso.testapp.databinding.CardSeparatorBinding;
import com.mitso.testapp.models.Entry;
import com.mitso.testapp.models.recycler_view.BaseModel;
import com.mitso.testapp.models.recycler_view.Header;
import com.mitso.testapp.models.recycler_view.Separator;

import java.util.ArrayList;
import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String            LOG_TAG = Constants.FAVOURITE_ADAPTER_LOG_TAG;

    private Context                 mContext;
    private List<BaseModel>         mFavouriteList;
    private IFavouriteHandler       mFavouriteHandler;

    public FavouriteAdapter(Context _context, List<BaseModel> _favouriteList) {

        this.mContext = _context;
        this.mFavouriteList = new ArrayList<>(_favouriteList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup _parent, int _viewType) {

        if (_viewType == BaseModel.TYPE_HEADER)
            return new HeaderViewHolder(CardHeaderBinding.inflate(LayoutInflater.from(_parent.getContext()), _parent, false).getRoot());

        if (_viewType == BaseModel.TYPE_ENTRY)
            return new FavouriteViewHolder(CardEntryFavouriteBinding.inflate(LayoutInflater.from(_parent.getContext()), _parent, false).getRoot());

        if (_viewType == BaseModel.TYPE_SEPARATOR)
            return new SeparatorViewHolder(CardSeparatorBinding.inflate(LayoutInflater.from(_parent.getContext()), _parent, false).getRoot());

        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder _holder, int _position) {

        final int type = getItemViewType(_position);

        if (type == BaseModel.TYPE_HEADER) {

            final Header header = (Header) mFavouriteList.get(_position);
            final HeaderViewHolder headerViewHolder = (HeaderViewHolder) _holder;

            headerViewHolder.getBinding().setHeader(header);
        }

        if (type == BaseModel.TYPE_ENTRY) {

            final Entry entry = (Entry) mFavouriteList.get(_position);
            final FavouriteViewHolder favouriteViewHolder = (FavouriteViewHolder) _holder;

            favouriteViewHolder.getBinding().setEntry(entry);

            favouriteViewHolder.getBinding().setClickerInfo(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mFavouriteHandler.showInfo(entry);
                }
            });

            favouriteViewHolder.getBinding().setClickerDelete(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mFavouriteHandler.deleteFromFavourites(entry, favouriteViewHolder.getAdapterPosition());
                }
            });

            Glide.with(mContext)
                    .load(entry.getImImage().get(0).getLabel())
                    .into(favouriteViewHolder.getBinding().ivCoverCef);
        }
    }

    @Override
    public int getItemCount() {

        return mFavouriteList.size();
    }

    @Override
    public int getItemViewType(int _position) {

        return mFavouriteList.get(_position).getType();
    }

    public void removeFavourite(int _position) {

        try {
            //delete two single items
            if (mFavouriteList.size() == 2) {

                // delete entry
                mFavouriteList.remove(1);
                notifyItemRemoved(1);

                // delete header
                mFavouriteList.remove(0);
                notifyItemRemoved(0);

                Log.i(LOG_TAG, "FAVOURITE IS DELETED FROM RECYCLER VIEW.");
                return;
            }

            // delete last entry and header
            if (_position == mFavouriteList.size() - 1
                    && mFavouriteList.get(_position - 1) instanceof Header
                    && mFavouriteList.get(_position) instanceof Entry) {

                // delete entry
                mFavouriteList.remove(_position);
                notifyItemRemoved(_position);

                // delete header
                mFavouriteList.remove(_position - 1);
                notifyItemRemoved(_position - 1);

                Log.i(LOG_TAG, "FAVOURITE IS DELETED FROM RECYCLER VIEW.");
                return;
            }

            // delete entry and header, entry is between two headers
            if (mFavouriteList.get(_position - 1) instanceof Header
                    && mFavouriteList.get(_position) instanceof Entry
                    && mFavouriteList.get(_position + 1) instanceof Header) {

                // delete entry
                mFavouriteList.remove(_position);
                notifyItemRemoved(_position);

                // delete header
                mFavouriteList.remove(_position - 1);
                notifyItemRemoved(_position - 1);

                Log.i(LOG_TAG, "FAVOURITE IS DELETED FROM RECYCLER VIEW.");
                return;
            }

            // delete entry and separator after
            if (_position < mFavouriteList.size() - 1
                    && mFavouriteList.get(_position) instanceof Entry
                    && mFavouriteList.get(_position + 1) instanceof Separator) {

                // delete separator
                mFavouriteList.remove(_position + 1);
                notifyItemRemoved(_position + 1);

                // delete entry
                mFavouriteList.remove(_position);
                notifyItemRemoved(_position);

                Log.i(LOG_TAG, "FAVOURITE IS DELETED FROM RECYCLER VIEW.");
                return;
            }

            // delete entry and separator before
            if (mFavouriteList.get(_position) instanceof Entry
                    && mFavouriteList.get(_position - 1) instanceof Separator) {

                // delete entry
                mFavouriteList.remove(_position);
                notifyItemRemoved(_position);

                // delete separator
                mFavouriteList.remove(_position - 1);
                notifyItemRemoved(_position - 1);

                Log.i(LOG_TAG, "FAVOURITE IS DELETED FROM RECYCLER VIEW.");
            }

        } catch (Exception _error) {

            _error.printStackTrace();
            Log.e(LOG_TAG, "ERROR.");
            Log.e(LOG_TAG, _error.toString());
        }
    }

    public void setFavouriteHandler(IFavouriteHandler _favouriteHandler) {

        if (mFavouriteHandler == null) {
            mFavouriteHandler = _favouriteHandler;

            Log.i(LOG_TAG, "HANDLER IS SET.");
        }
    }

    public void releaseFavouriteHandler() {

        if (mFavouriteHandler != null) {
            mFavouriteHandler = null;

            Log.i(LOG_TAG, "HANDLER IS NULL.");
        }
    }
}