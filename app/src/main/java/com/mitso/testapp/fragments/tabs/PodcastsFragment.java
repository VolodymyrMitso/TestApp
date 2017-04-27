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
import com.mitso.testapp.api.tasks.GetEntryListTask;
import com.mitso.testapp.constants.Constants;
import com.mitso.testapp.databinding.FragmentListCommonBinding;
import com.mitso.testapp.fragments.BaseFragment;
import com.mitso.testapp.models.Entry;
import com.mitso.testapp.recycler_view.EntryAdapter;
import com.mitso.testapp.recycler_view.IEntryHandler;
import com.mitso.testapp.support.Support;

import java.util.ArrayList;
import java.util.List;

public class PodcastsFragment extends BaseFragment implements IEntryHandler {

    private String                          LOG_TAG = Constants.PODCASTS_FRAGMENT_LOG_TAG;

    private FragmentListCommonBinding       mBinding;

    private Support                         mSupport;

    private List<Entry>                     mEntryList;
    private EntryAdapter                    mEntryAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {

        mBinding = DataBindingUtil.inflate(_inflater, R.layout.fragment_list_common, _container, false);
        final View rootView = mBinding.getRoot();

        Log.i(LOG_TAG, "PODCASTS FRAGMENT IS CREATED.");

        initSupport();
        initActionBar();

        if (mSupport.checkNetworkConnection(mMainActivity))
            getEntryList(Constants.CONTENT_TYPE_PODCAST);
        else
            mSupport.showToastNoNetworkConnection(mMainActivity);

        return rootView;
    }

    private void initSupport() {

        mSupport = new Support();
    }

    private void initActionBar() {

        if (mMainActivity.getSupportActionBar() != null)
            mMainActivity.getSupportActionBar().setTitle(getResources().getString(R.string.s_podcasts));
    }

    public void getEntryList(int _contentType) {

        final GetEntryListTask getEntryListTask = new GetEntryListTask(_contentType);
        getEntryListTask.setCallback(new GetEntryListTask.Callback() {
            @Override
            public void onSuccess(List<Entry> _result) {

                Log.i(getEntryListTask.LOG_TAG, "ON SUCCESS: PODCAST LIST.");
                Log.i(getEntryListTask.LOG_TAG, "LIST SIZE = " + String.valueOf(_result.size()) + ".");
                Log.i(getEntryListTask.LOG_TAG, _result.get(0).toString());
                Log.i(getEntryListTask.LOG_TAG, _result.get(_result.size() - 1).toString());

                for (int i = 0; i < _result.get(0).getLink().size(); i++)
                    Log.i(getEntryListTask.LOG_TAG, _result.get(0).getLink().get(i).toString());

                mEntryList = new ArrayList<>(_result);

                initRecyclerView();
                setHandler();

                getEntryListTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                Log.e(getEntryListTask.LOG_TAG, "ON FAILURE: ERROR.");
                _error.printStackTrace();

                getEntryListTask.releaseCallback();
            }
        });
        getEntryListTask.execute();
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
