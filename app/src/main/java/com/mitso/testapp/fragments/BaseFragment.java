package com.mitso.testapp.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.mitso.testapp.MainActivity;

public class BaseFragment extends Fragment {

    protected MainActivity      mMainActivity;

    @Override
    public void onAttach(Context _context) {
        super.onAttach(_context);

        mMainActivity = (MainActivity) _context;
    }
}
