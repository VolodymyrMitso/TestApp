package com.mitso.testapp.fragments.info;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.mitso.testapp.R;
import com.mitso.testapp.api.tasks.ApiGetResultTask;
import com.mitso.testapp.constants.Constants;
import com.mitso.testapp.database.DatabaseHelper;
import com.mitso.testapp.database.tasks.DbAddEntryTask;
import com.mitso.testapp.database.tasks.DbDeleteEntryTask;
import com.mitso.testapp.database.tasks.DbGetEntryListTask;
import com.mitso.testapp.databinding.FragmentInfoBinding;
import com.mitso.testapp.fragments.BaseFragment;
import com.mitso.testapp.models.json_entry_list.Entry;
import com.mitso.testapp.models.json_result.Result;
import com.mitso.testapp.support.Support;

import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends BaseFragment {

    private String                      LOG_TAG = Constants.INFO_FRAGMENT_LOG_TAG;

    private FragmentInfoBinding         mBinding;

    private Support                     mSupport;

    private List<Entry>                 mDbFavouriteList;

    private Entry                       mEntry;

    private Result                      mResult;

    private Menu                        mMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {

        mMainActivity.setShouldCommitFragment(true);

        mBinding = DataBindingUtil.inflate(_inflater, R.layout.fragment_info, _container, false);
        final View rootView = mBinding.getRoot();

        Log.i(LOG_TAG, "INFO FRAGMENT IS CREATED.");

        initSupport();

        getEntry();
        getFavouritesFromDatabase();

        setHasOptionsMenu(true);

        return rootView;
    }

    private void initSupport() {

        mSupport = new Support();
    }

    private void getEntry() {

        try {
            mEntry = (Entry) getArguments().getSerializable(Constants.ENTRY_BUNDLE_KEY);

            if (mEntry == null)
                throw new NullPointerException();

            Log.i(LOG_TAG, "ENTRY IS RECEIVED");

        } catch (NullPointerException _error) {

            Log.e(LOG_TAG, "ENTRY IS NULL.");
            mSupport.showToastError(mMainActivity);
        }
    }

    private void getFavouritesFromDatabase() {

        final DbGetEntryListTask dbGetEntryListTask = new DbGetEntryListTask(mMainActivity, DatabaseHelper.DATABASE_FAVOURITES_TABLE);
        dbGetEntryListTask.setCallback(new DbGetEntryListTask.Callback() {
            @Override
            public void onSuccess(List<Entry> _result) {

                if (mMainActivity != null && isAdded()) {

                    if (!_result.isEmpty()) {

                        Log.i(dbGetEntryListTask.LOG_TAG, "ON SUCCESS: FAVOURITE LIST IS TAKEN FROM DATABASE.");

                        mDbFavouriteList = new ArrayList<>(_result);

                    } else {

                        Log.i(dbGetEntryListTask.LOG_TAG, "ON SUCCESS: FAVOURITE LIST IS EMPTY.");

                        mDbFavouriteList = new ArrayList<>();
                    }

                    initActionBar();

                    if (mSupport.checkNetworkConnection(mMainActivity)) {

                        Log.i(LOG_TAG, "NETWORK IS ONLINE");

                        getResultById();

                    } else {

                        Log.i(LOG_TAG, "NETWORK IS OFFLINE");
                        mSupport.showToastNetworkConnection(mMainActivity);
                    }

                } else
                    Log.e(dbGetEntryListTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                dbGetEntryListTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                if (mMainActivity != null && isAdded()) {

                    Log.e(dbGetEntryListTask.LOG_TAG, "ON FAILURE: FAVOURITE LIST IS NOT TAKEN FROM DATABASE.");
                    Log.e(dbGetEntryListTask.LOG_TAG, _error.toString());

                    mSupport.showToastError(mMainActivity);

                } else
                    Log.e(dbGetEntryListTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                dbGetEntryListTask.releaseCallback();
            }
        });
        dbGetEntryListTask.execute();
    }

    @Override
    public void onCreateOptionsMenu(Menu _menu, MenuInflater _inflater) {

        mMainActivity.getMenuInflater().inflate(R.menu.menu_info, _menu);

        mMenu = _menu;

        super.onCreateOptionsMenu(_menu, _inflater);
    }

    private void initActionBar() {

        if (mMainActivity.getSupportActionBar() != null) {

            mMainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mMainActivity.getSupportActionBar().setHomeButtonEnabled(true);
            mMainActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);

            if (mEntry != null)
                mMainActivity.getSupportActionBar().setTitle(mEntry.getImContentType().getAttributes().getLabel());
        }

        if (mDbFavouriteList != null && mEntry != null) {

            if (!mDbFavouriteList.contains(mEntry)) {

                mMenu.getItem(0).setIcon(mMainActivity.getResources().getDrawable(R.drawable.ic_add_circle_white));
                mMenu.getItem(0).setTitle(mMainActivity.getResources().getString(R.string.s_add));

            } else {

                mMenu.getItem(0).setIcon(mMainActivity.getResources().getDrawable(R.drawable.ic_remove_circle_white));
                mMenu.getItem(0).setTitle(mMainActivity.getResources().getString(R.string.s_delete));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem _item) {

        switch (_item.getItemId()) {

            case android.R.id.home:
                mMainActivity.onBackPressed();
                return true;

            case R.id.mi_add_or_delete:
                addOrDelete();
                return true;

            default:
                return super.onOptionsItemSelected(_item);
        }
    }

    public void addOrDelete() {

        if (mDbFavouriteList!= null && mEntry != null) {

            if (!mDbFavouriteList.contains(mEntry)) {

                mDbFavouriteList.add(mEntry);
                addEntryToFavouritesDatabase();

            } else {

                mDbFavouriteList.remove(mEntry);
                deleteEntryFromFavouritesDatabase();
            }

        } else {

            Log.e(LOG_TAG, "FAVOURITE LIST IS NULL OR ENTRY IS NULL.");
            mSupport.showToastError(mMainActivity);
        }
    }

    private void addEntryToFavouritesDatabase() {

        final DbAddEntryTask dbAddEntryTask = new DbAddEntryTask(mMainActivity, mEntry);
        dbAddEntryTask.setCallback(new DbAddEntryTask.Callback() {
            @Override
            public void onSuccess() {

                if (mMainActivity != null && isAdded()) {

                    mEntry.setAddedToFavourites(true);

                    mMenu.getItem(0).setIcon(mMainActivity.getResources().getDrawable(R.drawable.ic_remove_circle_white));
                    mMenu.getItem(0).setTitle(mMainActivity.getResources().getString(R.string.s_delete));

                    Log.i(dbAddEntryTask.LOG_TAG, "ON SUCCESS: ENTRY IS ADDED TO FAVOURITES DATABASE.");
                    mSupport.showToastAdded(mMainActivity);

                } else
                    Log.e(dbAddEntryTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                dbAddEntryTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                if (mMainActivity != null && isAdded()) {

                    Log.e(dbAddEntryTask.LOG_TAG, "ON FAILURE: ENTRY IS NOT ADDED TO FAVOURITES DATABASE.");
                    Log.e(dbAddEntryTask.LOG_TAG, _error.toString());

                    mSupport.showToastError(mMainActivity);

                } else
                    Log.e(dbAddEntryTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                dbAddEntryTask.releaseCallback();
            }
        });
        dbAddEntryTask.execute();
    }

    private void deleteEntryFromFavouritesDatabase() {

        final DbDeleteEntryTask dbDeleteEntryTask = new DbDeleteEntryTask(mMainActivity, mEntry);
        dbDeleteEntryTask.setCallback(new DbDeleteEntryTask.Callback() {
            @Override
            public void onSuccess() {

                if (mMainActivity != null && isAdded()) {

                    mEntry.setAddedToFavourites(false);

                    mMenu.getItem(0).setIcon(mMainActivity.getResources().getDrawable(R.drawable.ic_add_circle_white));
                    mMenu.getItem(0).setTitle(mMainActivity.getResources().getString(R.string.s_add));

                    Log.i(dbDeleteEntryTask.LOG_TAG, "ON SUCCESS: ENTRY IS DELETED FROM FAVOURITES DATABASE.");
                    mSupport.showToastDeleted(mMainActivity);

                } else
                    Log.e(dbDeleteEntryTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                dbDeleteEntryTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                if (mMainActivity != null && isAdded()) {

                    Log.e(dbDeleteEntryTask.LOG_TAG, "ON FAILURE: ENTRY IS NOT DELETED FROM FAVOURITES DATABASE.");
                    Log.e(dbDeleteEntryTask.LOG_TAG, _error.toString());

                    mSupport.showToastError(mMainActivity);

                } else
                    Log.e(dbDeleteEntryTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                dbDeleteEntryTask.releaseCallback();
            }
        });
        dbDeleteEntryTask.execute();
    }

    public void getResultById() {

        final ApiGetResultTask apiGetResultTask = new ApiGetResultTask(mEntry.getId().getAttributes().getImId());
        apiGetResultTask.setCallback(new ApiGetResultTask.Callback() {
            @Override
            public void onSuccess(Result _result) {

                if (mMainActivity != null && isAdded()) {

                    Log.i(apiGetResultTask.LOG_TAG, "ON SUCCESS: RESULT IS TAKEN FROM API.");

                    mResult = _result;

                    fillViews();

                } else
                    Log.e(apiGetResultTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                apiGetResultTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                if (mMainActivity != null && isAdded()) {

                    Log.e(apiGetResultTask.LOG_TAG, "ON FAILURE: RESULT IS NOT TAKEN FROM API.");
                    Log.e(apiGetResultTask.LOG_TAG, _error.toString());

                    mSupport.showToastError(mMainActivity);

                } else
                    Log.e(apiGetResultTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                apiGetResultTask.releaseCallback();
            }
        });
        apiGetResultTask.execute();
    }

    private void fillViews() {

        Glide.with(mMainActivity)
                .load(mResult.getArtworkUrl100())
                .into(mBinding.ivCoverFi);

        mBinding.setResult(mResult);
    }
}