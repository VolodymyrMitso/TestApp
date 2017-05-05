package com.mitso.testapp.fragments.tabs;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mitso.testapp.R;
import com.mitso.testapp.constants.Constants;
import com.mitso.testapp.database.DatabaseHelper;
import com.mitso.testapp.database.tasks.DbDeleteEntryTask;
import com.mitso.testapp.database.tasks.DbGetEntryListTask;
import com.mitso.testapp.databinding.FragmentListFavouriteBinding;
import com.mitso.testapp.fragments.BaseFragment;
import com.mitso.testapp.models.json_entry_list.Entry;
import com.mitso.testapp.models.recycler_view.BaseModel;
import com.mitso.testapp.recycler_view.favourites.FavouriteAdapter;
import com.mitso.testapp.recycler_view.favourites.IFavouriteHandler;
import com.mitso.testapp.support.Support;

import java.util.List;

public class FavouritesFragment extends BaseFragment implements IFavouriteHandler {

    private String                          LOG_TAG = Constants.FAVOURITES_FRAGMENT_LOG_TAG;

    private FragmentListFavouriteBinding    mBinding;

    private Support                         mSupport;

    private List<BaseModel>                 mFavouriteList;
    private FavouriteAdapter                mFavouriteAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {

        mMainActivity.setShouldCommitFragment(true);

        mBinding = DataBindingUtil.inflate(_inflater, R.layout.fragment_list_favourite, _container, false);
        final View rootView = mBinding.getRoot();

        Log.i(LOG_TAG, "FAVOURITES FRAGMENT IS CREATED.");

        initSupport();
        initActionBar();

        getEntriesFromDatabase();

        return rootView;
    }

    private void initSupport() {

        mSupport = new Support();
    }

    private void initActionBar() {

        if (mMainActivity.getSupportActionBar() != null) {

            mMainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            mMainActivity.getSupportActionBar().setHomeButtonEnabled(false);
            mMainActivity.getSupportActionBar().setDisplayShowHomeEnabled(false);

            mMainActivity.getSupportActionBar().setTitle(getResources().getString(R.string.s_favourites));
        }
    }

    private void getEntriesFromDatabase() {

        final DbGetEntryListTask dbGetEntryListTask = new DbGetEntryListTask(mMainActivity, DatabaseHelper.DATABASE_FAVOURITES_TABLE);
        dbGetEntryListTask.setCallback(new DbGetEntryListTask.Callback() {
            @Override
            public void onSuccess(List<Entry> _result) {

                if (mMainActivity != null && isAdded()) {

                    if (!_result.isEmpty()) {

                        Log.i(dbGetEntryListTask.LOG_TAG, "ON SUCCESS: FAVOURITE LIST.");

                        mFavouriteList = mSupport.groupList(mMainActivity, _result);

                        initRecyclerView();
                        setHandler();

                    } else {

                        Log.i(dbGetEntryListTask.LOG_TAG, "ON SUCCESS: LIST IS EMPTY.");
                        mSupport.showToastEmptyFavourites(mMainActivity);
                    }

                } else
                    Log.e(dbGetEntryListTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                dbGetEntryListTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                if (mMainActivity != null && isAdded()) {

                    Log.e(dbGetEntryListTask.LOG_TAG, "ON FAILURE: ERROR.");
                    Log.e(dbGetEntryListTask.LOG_TAG, _error.toString());

                    mSupport.showToastError(mMainActivity);

                } else
                    Log.e(dbGetEntryListTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                dbGetEntryListTask.releaseCallback();
            }
        });
        dbGetEntryListTask.execute();
    }

    private void initRecyclerView() {

        mFavouriteAdapter = new FavouriteAdapter(mMainActivity, mFavouriteList);

        mBinding.rvEntriesFlc.setAdapter(mFavouriteAdapter);
        mBinding.rvEntriesFlc.setLayoutManager(new LinearLayoutManager(mMainActivity));
    }

    @Override
    public void showInfo(Entry _entry) {

        Log.i(LOG_TAG, _entry.toString());
    }

    @Override
    public void deleteFromFavourites(Entry _entry, int _position) {

        mFavouriteAdapter.removeFavourite(_position);
        deleteEntryFromDatabase(_entry);
    }

    private void deleteEntryFromDatabase(Entry _entry) {

        final DbDeleteEntryTask dbDeleteEntryTask = new DbDeleteEntryTask(mMainActivity, _entry);
        dbDeleteEntryTask.setCallback(new DbDeleteEntryTask.Callback() {
            @Override
            public void onSuccess() {

                if (mMainActivity != null && isAdded()) {

                    Log.i(dbDeleteEntryTask.LOG_TAG, "ON SUCCESS: ENTRY IS DELETED FROM DATABASE.");
                    mSupport.showToastDeleted(mMainActivity);

                } else
                    Log.e(dbDeleteEntryTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                dbDeleteEntryTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                if (mMainActivity != null && isAdded()) {

                    Log.e(dbDeleteEntryTask.LOG_TAG, "ON FAILURE: ERROR.");
                    Log.e(dbDeleteEntryTask.LOG_TAG, _error.toString());

                    mSupport.showToastError(mMainActivity);

                } else
                    Log.e(dbDeleteEntryTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");


                dbDeleteEntryTask.releaseCallback();
            }
        });
        dbDeleteEntryTask.execute();
    }

    private void setHandler() {

        if (mFavouriteAdapter != null)
            mFavouriteAdapter.setFavouriteHandler(this);
    }

    private void releaseHandler() {

        if (mFavouriteAdapter != null)
            mFavouriteAdapter.releaseFavouriteHandler();
    }

    @Override
    public void onResume() {
        super.onResume();

        setHandler();
    }

    @Override
    public void onPause() {
        super.onPause();

        releaseHandler();
    }
}