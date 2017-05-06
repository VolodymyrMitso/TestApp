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
import com.mitso.testapp.api.tasks.ApiGetEntryListTask;
import com.mitso.testapp.constants.Constants;
import com.mitso.testapp.database.DatabaseHelper;
import com.mitso.testapp.database.tasks.DbAddEntryTask;
import com.mitso.testapp.database.tasks.DbDeleteEntryTask;
import com.mitso.testapp.database.tasks.DbGetEntryListTask;
import com.mitso.testapp.database.tasks.DbSaveEntryListTask;
import com.mitso.testapp.databinding.FragmentListCommonBinding;
import com.mitso.testapp.fragments.BaseFragment;
import com.mitso.testapp.fragments.info.InfoFragment;
import com.mitso.testapp.models.json_entry_list.Entry;
import com.mitso.testapp.recycler_view.common.EntryAdapter;
import com.mitso.testapp.recycler_view.common.IEntryHandler;
import com.mitso.testapp.recycler_view.common.SpaceItemDecoration;
import com.mitso.testapp.support.Support;

import java.util.ArrayList;
import java.util.List;

public class PodcastsFragment extends BaseFragment implements IEntryHandler {

    private String                          LOG_TAG = Constants.PODCASTS_FRAGMENT_LOG_TAG;

    private FragmentListCommonBinding       mBinding;

    private Support                         mSupport;

    private List<Entry>                     mDbFavouriteList;
    private List<Entry>                     mApiPodcastList;
    private List<Entry>                     mDbPodcastList;

    private List<Entry>                     mFinalList;

    private EntryAdapter                    mEntryAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {

        mMainActivity.setShouldCommitFragment(true);

        mBinding = DataBindingUtil.inflate(_inflater, R.layout.fragment_list_common, _container, false);
        final View rootView = mBinding.getRoot();

        Log.i(LOG_TAG, "PODCASTS FRAGMENT IS CREATED.");

        initSupport();
        initActionBar();

        getFavouritesFromDatabase();

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

            mMainActivity.getSupportActionBar().setTitle(getResources().getString(R.string.s_podcasts));
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

                    if (mSupport.checkNetworkConnection(mMainActivity)) {

                        Log.i(LOG_TAG, "NETWORK IS ONLINE.");

                        getPodcastsFromApi();

                    } else {

                        Log.i(LOG_TAG, "NETWORK IS OFFLINE.");

                        getPodcastsFromDatabase();
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

    public void getPodcastsFromApi() {

        final ApiGetEntryListTask apiGetEntryListTask = new ApiGetEntryListTask(Constants.CONTENT_TYPE_PODCAST);
        apiGetEntryListTask.setCallback(new ApiGetEntryListTask.Callback() {
            @Override
            public void onSuccess(List<Entry> _result) {

                if (mMainActivity != null && isAdded()) {

                    Log.i(apiGetEntryListTask.LOG_TAG, "ON SUCCESS: PODCAST LIST IS TAKEN FROM API.");

                    mApiPodcastList = new ArrayList<>(_result);

                    savePodcastsToDatabase();

                } else
                    Log.e(apiGetEntryListTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                apiGetEntryListTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                if (mMainActivity != null && isAdded()) {

                    Log.e(apiGetEntryListTask.LOG_TAG, "ON FAILURE: PODCAST LIST IS NOT TAKEN FROM API.");
                    Log.e(apiGetEntryListTask.LOG_TAG, _error.toString());

                    mSupport.showToastError(mMainActivity);

                } else
                    Log.e(apiGetEntryListTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                apiGetEntryListTask.releaseCallback();
            }
        });
        apiGetEntryListTask.execute();
    }

    private void savePodcastsToDatabase() {

        final DbSaveEntryListTask dbSaveEntryListTask = new DbSaveEntryListTask(mMainActivity, mApiPodcastList, DatabaseHelper.DATABASE_PODCASTS_TABLE);
        dbSaveEntryListTask.setCallback(new DbSaveEntryListTask.Callback() {
            @Override
            public void onSuccess() {

                if (mMainActivity != null && isAdded()) {

                    Log.i(dbSaveEntryListTask.LOG_TAG, "ON SUCCESS: PODCAST LIST IS SAVED TO DATABASE.");

                    mFinalList = mSupport.compareLists(mDbFavouriteList, mApiPodcastList);

                    initRecyclerView();
                    setHandler();

                } else
                    Log.e(dbSaveEntryListTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                dbSaveEntryListTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                if (mMainActivity != null && isAdded()) {

                    Log.e(dbSaveEntryListTask.LOG_TAG, "ON FAILURE: PODCAST LIST IS NOT SAVED TO DATABASE.");
                    Log.e(dbSaveEntryListTask.LOG_TAG, _error.toString());

                    mSupport.showToastError(mMainActivity);

                } else
                    Log.e(dbSaveEntryListTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                dbSaveEntryListTask.releaseCallback();
            }
        });
        dbSaveEntryListTask.execute();
    }

    private void getPodcastsFromDatabase() {

        final DbGetEntryListTask dbGetEntryListTask = new DbGetEntryListTask(mMainActivity, DatabaseHelper.DATABASE_PODCASTS_TABLE);
        dbGetEntryListTask.setCallback(new DbGetEntryListTask.Callback() {
            @Override
            public void onSuccess(List<Entry> _result) {

                if (mMainActivity != null && isAdded()) {

                    if (!_result.isEmpty()) {

                        Log.i(dbGetEntryListTask.LOG_TAG, "ON SUCCESS: PODCAST LIST IS TAKEN FROM DATABASE.");

                        mDbPodcastList = new ArrayList<>(_result);
                        mFinalList = mSupport.compareLists(mDbFavouriteList, mDbPodcastList);

                        initRecyclerView();
                        setHandler();

                    } else {

                        Log.i(dbGetEntryListTask.LOG_TAG, "ON SUCCESS: PODCAST LIST IS EMPTY.");
                        mSupport.showToastFirstRun(mMainActivity);
                    }

                } else
                    Log.e(dbGetEntryListTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                dbGetEntryListTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                if (mMainActivity != null && isAdded()) {

                    Log.e(dbGetEntryListTask.LOG_TAG, "ON FAILURE: PODCAST LIST IS NOT FROM DATABASE.");
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

        mEntryAdapter = new EntryAdapter(mMainActivity, mFinalList);

        mBinding.rvEntriesFlc.setAdapter(mEntryAdapter);
        mBinding.rvEntriesFlc.setLayoutManager(new LinearLayoutManager(mMainActivity));
        mBinding.rvEntriesFlc.addItemDecoration(
                new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.d_space_size_cec)));
    }

    @Override
    public void showInfo(Entry _entry) {

        final Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ENTRY_BUNDLE_KEY, _entry);

        mMainActivity.commitFragment(new InfoFragment(), Constants.INFO_FRAGMENT_TAG, bundle);
    }

    @Override
    public void addOrDelete(Entry _entry, int _position) {

        if (!_entry.isAddedToFavourites())
            addPodcastToFavourites(_entry, _position);
        else
            deletePodcastFromFavourites(_entry, _position);
    }

    private void addPodcastToFavourites(final Entry _entry, final int _position) {

        final DbAddEntryTask dbAddEntryTask = new DbAddEntryTask(mMainActivity, _entry);
        dbAddEntryTask.setCallback(new DbAddEntryTask.Callback() {
            @Override
            public void onSuccess() {

                if (mMainActivity != null && isAdded()) {

                    _entry.setAddedToFavourites(true);
                    mEntryAdapter.notifyItemChanged(_position);

                    Log.i(dbAddEntryTask.LOG_TAG, "ON SUCCESS: PODCAST IS ADDED TO FAVOURITES DATABASE.");
                    mSupport.showToastAdded(mMainActivity);

                } else
                    Log.e(dbAddEntryTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                dbAddEntryTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                if (mMainActivity != null && isAdded()) {

                    Log.e(dbAddEntryTask.LOG_TAG, "ON FAILURE: PODCAST IS NOT ADDED TO FAVOURITES DATABASE.");
                    Log.e(dbAddEntryTask.LOG_TAG, _error.toString());

                    mSupport.showToastError(mMainActivity);

                } else
                    Log.e(dbAddEntryTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                dbAddEntryTask.releaseCallback();
            }
        });
        dbAddEntryTask.execute();
    }

    private void deletePodcastFromFavourites(final Entry _entry, final int _position) {

        final DbDeleteEntryTask dbDeleteEntryTask = new DbDeleteEntryTask(mMainActivity, _entry);
        dbDeleteEntryTask.setCallback(new DbDeleteEntryTask.Callback() {
            @Override
            public void onSuccess() {

                if (mMainActivity != null && isAdded()) {

                    _entry.setAddedToFavourites(false);
                    mEntryAdapter.notifyItemChanged(_position);

                    Log.i(dbDeleteEntryTask.LOG_TAG, "ON SUCCESS: PODCAST IS DELETED FROM FAVOURITES DATABASE.");
                    mSupport.showToastDeleted(mMainActivity);

                } else
                    Log.e(dbDeleteEntryTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");

                dbDeleteEntryTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                if (mMainActivity != null && isAdded()) {

                    Log.e(dbDeleteEntryTask.LOG_TAG, "ON FAILURE: PODCAST IS NOT DELETED TO FAVOURITES DATABASE.");
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

        if (mEntryAdapter != null)
            mEntryAdapter.setEntryHandler(this);
    }

    private void releaseHandler() {

        if (mEntryAdapter != null)
            mEntryAdapter.releaseEntryHandler();
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
