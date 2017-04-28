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
import com.mitso.testapp.database.tasks.DbGetEntryListTask;
import com.mitso.testapp.databinding.FragmentListCommonBinding;
import com.mitso.testapp.fragments.BaseFragment;
import com.mitso.testapp.models.Entry;
import com.mitso.testapp.recycler_view.EntryAdapter;
import com.mitso.testapp.recycler_view.IEntryHandler;
import com.mitso.testapp.support.Support;

import java.util.List;

public class FavouritesFragment extends BaseFragment implements IEntryHandler {

    private String                          LOG_TAG = Constants.FAVOURITES_FRAGMENT_LOG_TAG;

    private FragmentListCommonBinding       mBinding;

    private Support                         mSupport;

    private List<Entry>                     mEntryList;
    private EntryAdapter                    mEntryAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {

        mBinding = DataBindingUtil.inflate(_inflater, R.layout.fragment_list_common, _container, false);
        final View rootView = mBinding.getRoot();

        Log.i(LOG_TAG, "FAVOURITES FRAGMENT IS CREATED.");

        initSupport();
        initActionBar();

        if (mSupport.checkDatabaseExistence(mMainActivity))
            getEntriesDatabase();
        else
            mSupport.showToastEmptyFavourites(mMainActivity);

        return rootView;
    }

    private void initSupport() {

        mSupport = new Support();
    }

    private void initActionBar() {

        if (mMainActivity.getSupportActionBar() != null)
            mMainActivity.getSupportActionBar().setTitle(getResources().getString(R.string.s_favourites));
    }

    private void getEntriesDatabase() {

        final DbGetEntryListTask dbGetEntryListTask = new DbGetEntryListTask(mMainActivity);
        dbGetEntryListTask.setCallback(new DbGetEntryListTask.Callback() {
            @Override
            public void onSuccess(List<Entry> _result) {

                if (_result != null && !_result.isEmpty()) {

                    Log.i(dbGetEntryListTask.LOG_TAG, "ON SUCCESS: FAVOURITES LIST.");

                    for (int i = 0; i < _result.size(); i++)
                        Log.i(dbGetEntryListTask.LOG_TAG, _result.get(i).toString());

                    mEntryList = _result;

                    initRecyclerView();
                    setHandler();

                } else
                    Log.i(LOG_TAG, "ON SUCCESS: NULL OR EMPTY.");

                dbGetEntryListTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                Log.e(dbGetEntryListTask.LOG_TAG, "ON FAILURE: ERROR.");
                Log.e(dbGetEntryListTask.LOG_TAG, _error.toString());

                dbGetEntryListTask.releaseCallback();
            }
        });
        dbGetEntryListTask.execute();
    }

    private void initRecyclerView() {

        mEntryAdapter = new EntryAdapter(mMainActivity, mEntryList);

        mBinding.rvEntriesFlc.setAdapter(mEntryAdapter);
        mBinding.rvEntriesFlc.setLayoutManager(new LinearLayoutManager(mMainActivity));
    }

    @Override
    public void showInfo(Entry _entry) {

        Log.i(LOG_TAG, _entry.toString());
    }

    @Override
    public void addToFavourites(Entry _entry) {

        Log.i(LOG_TAG, _entry.toString());
    }

    private void setHandler() {

        if (mEntryAdapter != null)
            mEntryAdapter.setCommonHandler(this);
    }

    private void releaseHandler() {

        if (mEntryAdapter != null)
            mEntryAdapter.releaseCommonHandler();
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