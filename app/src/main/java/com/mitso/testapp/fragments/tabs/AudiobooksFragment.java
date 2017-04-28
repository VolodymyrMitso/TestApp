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
import com.mitso.testapp.databinding.FragmentListCommonBinding;
import com.mitso.testapp.fragments.BaseFragment;
import com.mitso.testapp.models.Entry;
import com.mitso.testapp.recycler_view.EntryAdapter;
import com.mitso.testapp.recycler_view.IEntryHandler;
import com.mitso.testapp.support.Support;

import java.util.ArrayList;
import java.util.List;

public class AudiobooksFragment extends BaseFragment implements IEntryHandler {

    private String                          LOG_TAG = Constants.AUDIOBOOKS_FRAGMENT_LOG_TAG;

    private FragmentListCommonBinding       mBinding;

    private Support                         mSupport;

    private List<Entry>                     mEntryList;
    private EntryAdapter                    mEntryAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {

        mBinding = DataBindingUtil.inflate(_inflater, R.layout.fragment_list_common, _container, false);
        final View rootView = mBinding.getRoot();

        Log.i(LOG_TAG, "AUDIOBOOKS FRAGMENT IS CREATED.");

        initSupport();
        initActionBar();

        if (mSupport.checkNetworkConnection(mMainActivity))
            getEntryListApi(Constants.CONTENT_TYPE_AUDIOBOOK);
        else
            mSupport.showToastNoNetworkConnection(mMainActivity);

        return rootView;
    }

    private void initSupport() {

        mSupport = new Support();
    }

    private void initActionBar() {

        if (mMainActivity.getSupportActionBar() != null)
            mMainActivity.getSupportActionBar().setTitle(getResources().getString(R.string.s_audiobooks));
    }


    public void getEntryListApi(int _contentType) {

        final ApiGetEntryListTask apiGetEntryListTask = new ApiGetEntryListTask(_contentType);
        apiGetEntryListTask.setCallback(new ApiGetEntryListTask.Callback() {
            @Override
            public void onSuccess(List<Entry> _result) {

                Log.i(apiGetEntryListTask.LOG_TAG, "ON SUCCESS: AUDIOBOOK LIST.");
                Log.i(apiGetEntryListTask.LOG_TAG, "LIST SIZE = " + String.valueOf(_result.size()) + ".");
                Log.i(apiGetEntryListTask.LOG_TAG, _result.get(0).toString());
                Log.i(apiGetEntryListTask.LOG_TAG, _result.get(_result.size() - 1).toString());

                for (int i = 0; i < _result.get(0).getLink().size(); i++)
                    Log.i(apiGetEntryListTask.LOG_TAG, _result.get(0).getLink().get(i).toString());

                mEntryList = new ArrayList<>(_result);

                initRecyclerView();
                setHandler();

                apiGetEntryListTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                Log.e(apiGetEntryListTask.LOG_TAG, "ON FAILURE: ERROR.");
                Log.e(apiGetEntryListTask.LOG_TAG, _error.toString());

                apiGetEntryListTask.releaseCallback();
            }
        });
        apiGetEntryListTask.execute();
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

        addEntryDatabase(_entry);
    }

    private void addEntryDatabase(Entry _entry) {

        final DbAddEntryTask dbAddEntryTask = new DbAddEntryTask(mMainActivity, _entry);
        dbAddEntryTask.setCallback(new DbAddEntryTask.Callback() {
            @Override
            public void onSuccess() {

                Log.i(dbAddEntryTask.LOG_TAG, "ON SUCCESS: ENTRY IS ADDED TO DATABASE.");

                dbAddEntryTask.releaseCallback();
            }

            @Override
            public void onFailure(Throwable _error) {

                Log.e(dbAddEntryTask.LOG_TAG, "ON FAILURE: ERROR.");
                Log.e(dbAddEntryTask.LOG_TAG, _error.toString());

                dbAddEntryTask.releaseCallback();
            }
        });
        dbAddEntryTask.execute();
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
