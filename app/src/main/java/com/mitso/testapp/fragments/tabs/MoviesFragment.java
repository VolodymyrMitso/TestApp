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
import com.mitso.testapp.database.tasks.DbAddEntryTask;
import com.mitso.testapp.database.tasks.DbGetEntryListTask;
import com.mitso.testapp.databinding.FragmentListCommonBinding;
import com.mitso.testapp.fragments.BaseFragment;
import com.mitso.testapp.models.Entry;
import com.mitso.testapp.recycler_view.common.EntryAdapter;
import com.mitso.testapp.recycler_view.common.IEntryHandler;
import com.mitso.testapp.recycler_view.common.SpaceItemDecoration;
import com.mitso.testapp.support.Support;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends BaseFragment implements IEntryHandler {

    private String                          LOG_TAG = Constants.MOVIES_FRAGMENT_LOG_TAG;

    private FragmentListCommonBinding       mBinding;

    private Support                         mSupport;

    private List<Entry>                     mEntryList;
    private EntryAdapter                    mEntryAdapter;

    private List<Entry>                     mDbEntryList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {

        mBinding = DataBindingUtil.inflate(_inflater, R.layout.fragment_list_common, _container, false);
        final View rootView = mBinding.getRoot();

        Log.i(LOG_TAG, "MOVIES FRAGMENT IS CREATED.");

        initSupport();
        initActionBar();

        if (mSupport.checkNetworkConnection(mMainActivity)) {

            Log.i(LOG_TAG, "NETWORK IS ONLINE.");

            getEntriesFromApi(Constants.CONTENT_TYPE_MOVIE);

        } else {

            Log.i(LOG_TAG, "NETWORK IS OFFLINE.");
            mSupport.showToastNoNetworkConnection(mMainActivity);
        }

        return rootView;
    }

    private void initSupport() {

        mSupport = new Support();
    }

    private void initActionBar() {

        if (mMainActivity.getSupportActionBar() != null)
            mMainActivity.getSupportActionBar().setTitle(getResources().getString(R.string.s_movies));
    }

    public void getEntriesFromApi(String _contentType) {

        final ApiGetEntryListTask apiGetEntryListTask = new ApiGetEntryListTask(_contentType);
        apiGetEntryListTask.setCallback(new ApiGetEntryListTask.Callback() {
            @Override
            public void onSuccess(List<Entry> _result) {

                if (mMainActivity != null && isAdded()) {

                    Log.i(apiGetEntryListTask.LOG_TAG, "ON SUCCESS: MOVIE LIST.");

                    mEntryList = new ArrayList<>(_result);

                    getEntriesFromDatabase();

                } else {

                    Log.e(apiGetEntryListTask.LOG_TAG, "ON SUCCESS.");
                    Log.e(apiGetEntryListTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");
                }

                apiGetEntryListTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                if (mMainActivity != null && isAdded()) {

                    Log.e(apiGetEntryListTask.LOG_TAG, "ON FAILURE: ERROR.");
                    Log.e(apiGetEntryListTask.LOG_TAG, _error.toString());

                    mSupport.showToastError(mMainActivity);

                } else {

                    Log.e(apiGetEntryListTask.LOG_TAG, "ON FAILURE.");
                    Log.e(apiGetEntryListTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");
                }

                apiGetEntryListTask.releaseCallback();
            }
        });
        apiGetEntryListTask.execute();
    }

    private void getEntriesFromDatabase() {

        final DbGetEntryListTask dbGetEntryListTask = new DbGetEntryListTask(mMainActivity);
        dbGetEntryListTask.setCallback(new DbGetEntryListTask.Callback() {
            @Override
            public void onSuccess(List<Entry> _result) {

                if (mMainActivity != null && isAdded()) {

                    if (!_result.isEmpty()) {

                        Log.i(dbGetEntryListTask.LOG_TAG, "ON SUCCESS: FAVOURITES LIST.");

                        mDbEntryList = new ArrayList<>(_result);

                    } else {

                        Log.i(dbGetEntryListTask.LOG_TAG, "ON SUCCESS: LIST IS EMPTY.");

                        mDbEntryList = new ArrayList<>();
                    }

                    initRecyclerView();
                    setHandler();

                } else {

                    Log.e(dbGetEntryListTask.LOG_TAG, "ON SUCCESS.");
                    Log.e(dbGetEntryListTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");
                }

                dbGetEntryListTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                if (mMainActivity != null && isAdded()) {

                    Log.e(dbGetEntryListTask.LOG_TAG, "ON FAILURE: ERROR.");
                    Log.e(dbGetEntryListTask.LOG_TAG, _error.toString());

                    mSupport.showToastError(mMainActivity);

                } else {

                    Log.e(dbGetEntryListTask.LOG_TAG, "ON FAILURE.");
                    Log.e(dbGetEntryListTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");
                }

                dbGetEntryListTask.releaseCallback();
            }
        });
        dbGetEntryListTask.execute();
    }

    private void initRecyclerView() {

        mEntryAdapter = new EntryAdapter(mMainActivity, mEntryList);

        mBinding.rvEntriesFlc.setAdapter(mEntryAdapter);
        mBinding.rvEntriesFlc.setLayoutManager(new LinearLayoutManager(mMainActivity));
        mBinding.rvEntriesFlc.addItemDecoration(
                new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.d_space_size_cec)));
    }

    @Override
    public void showInfo(Entry _entry) {

        Log.i(LOG_TAG, _entry.toString());
    }

    @Override
    public void addToFavourites(Entry _entry) {

        if (!mDbEntryList.contains(_entry)) {

            mDbEntryList.add(_entry);
            addEntryToDatabase(_entry);

        } else {

            Log.i(LOG_TAG, "ENTRY IS ALREADY IN FAVOURITES.");
            mSupport.showToastAlreadyIn(mMainActivity);
        }
    }

    private void addEntryToDatabase(Entry _entry) {

        final DbAddEntryTask dbAddEntryTask = new DbAddEntryTask(mMainActivity, _entry);
        dbAddEntryTask.setCallback(new DbAddEntryTask.Callback() {
            @Override
            public void onSuccess() {

                if (mMainActivity != null && isAdded()) {

                    Log.i(dbAddEntryTask.LOG_TAG, "ON SUCCESS: ENTRY IS ADDED TO DATABASE.");
                    mSupport.showToastAdded(mMainActivity);

                } else {

                    Log.e(dbAddEntryTask.LOG_TAG, "ON SUCCESS.");
                    Log.e(dbAddEntryTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");
                }

                dbAddEntryTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                if (mMainActivity != null && isAdded()) {

                    Log.e(dbAddEntryTask.LOG_TAG, "ON FAILURE: ERROR.");
                    Log.e(dbAddEntryTask.LOG_TAG, _error.toString());

                    mSupport.showToastError(mMainActivity);

                } else {

                    Log.e(dbAddEntryTask.LOG_TAG, "ON FAILURE.");
                    Log.e(dbAddEntryTask.LOG_TAG, "ACTIVITY IS NULL OR FRAGMENT IS NOT ADDED.");
                }

                dbAddEntryTask.releaseCallback();
            }
        });
        dbAddEntryTask.execute();
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
