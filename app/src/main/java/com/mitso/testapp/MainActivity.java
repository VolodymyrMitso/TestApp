package com.mitso.testapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.mitso.testapp.constants.Constants;
import com.mitso.testapp.databinding.ActivityMainBinding;
import com.mitso.testapp.fragments.BaseFragment;
import com.mitso.testapp.fragments.tabs.AudiobooksFragment;
import com.mitso.testapp.fragments.tabs.FavouritesFragment;
import com.mitso.testapp.fragments.tabs.MoviesFragment;
import com.mitso.testapp.fragments.tabs.PodcastsFragment;

public class MainActivity extends AppCompatActivity {

    private String                      LOG_TAG = Constants.MAIN_ACTIVITY_LOG_TAG;

    private ActivityMainBinding         mBinding;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initBottomBar();

        commitFragment(new AudiobooksFragment(), null);
    }

    private void initBottomBar() {

        mBinding.bottomBarAm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem _item) {
                switch (_item.getItemId()) {

                    case R.id.mi_audiobooks:
                        Log.i(LOG_TAG, "AUDIOBOOKS ITEM IS SELECTED.");
                        commitFragment(new AudiobooksFragment(), null);
                        return true;

                    case R.id.mi_movies:
                        Log.i(LOG_TAG, "MOVIES ITEM IS SELECTED.");
                        commitFragment(new MoviesFragment(), null);
                        return true;


                    case R.id.mi_podcasts:
                        Log.i(LOG_TAG, "PODCASTS ITEM IS SELECTED.");
                        commitFragment(new PodcastsFragment(), null);
                        return true;

                    case R.id.mi_favourites:
                        Log.i(LOG_TAG, "FAVOURITES ITEM IS SELECTED.");
                        commitFragment(new FavouritesFragment(), null);
                        return true;

                    default:
                        return true;
                }
            }
        });

        mBinding.bottomBarAm.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem _item) {

                Log.i(LOG_TAG, "ITEM IS RESELECTED.");
            }
        });
    }

    public void commitFragment(BaseFragment _baseFragment, Bundle _bundle) {

        _baseFragment.setArguments(_bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container_am, _baseFragment)
                .commitAllowingStateLoss();
    }

    @Override
    protected void onSaveInstanceState(Bundle _outState) {
        super.onSaveInstanceState(_outState);

        final BaseFragment baseFragment = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.fl_container_am);

        if (baseFragment instanceof AudiobooksFragment)
            _outState.putInt(Constants.FRAGMENT_TYPE_KEY, Constants.FRAGMENT_TYPE_AUDIOBOOKS);

        if (baseFragment instanceof MoviesFragment)
            _outState.putInt(Constants.FRAGMENT_TYPE_KEY, Constants.FRAGMENT_TYPE_MOVIES);

        if (baseFragment instanceof PodcastsFragment)
            _outState.putInt(Constants.FRAGMENT_TYPE_KEY, Constants.FRAGMENT_TYPE_PODCASTS);

        if (baseFragment instanceof FavouritesFragment)
            _outState.putInt(Constants.FRAGMENT_TYPE_KEY, Constants.FRAGMENT_TYPE_FAVOURITES);
    }

    @Override
    protected void onRestoreInstanceState(Bundle _savedInstanceState) {
        super.onRestoreInstanceState(_savedInstanceState);

        final int fragmentType = _savedInstanceState.getInt(Constants.FRAGMENT_TYPE_KEY);

        if (fragmentType == Constants.FRAGMENT_TYPE_AUDIOBOOKS) {

            mBinding.bottomBarAm.setSelectedItemId(R.id.mi_audiobooks);
            commitFragment(new AudiobooksFragment(), null);
        }

        if (fragmentType == Constants.FRAGMENT_TYPE_MOVIES) {

            mBinding.bottomBarAm.setSelectedItemId(R.id.mi_movies);
            commitFragment(new MoviesFragment(), null);
        }

        if (fragmentType == Constants.FRAGMENT_TYPE_PODCASTS) {

            mBinding.bottomBarAm.setSelectedItemId(R.id.mi_podcasts);
            commitFragment(new PodcastsFragment(), null);
        }

        if (fragmentType == Constants.FRAGMENT_TYPE_FAVOURITES) {

            mBinding.bottomBarAm.setSelectedItemId(R.id.mi_favourites);
            commitFragment(new FavouritesFragment(), null);
        }
    }
}
