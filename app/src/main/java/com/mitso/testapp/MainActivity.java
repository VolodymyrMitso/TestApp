package com.mitso.testapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
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

    private boolean                     shouldCommitFragment = true;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initBottomBar();

        commitFragment(new AudiobooksFragment(), Constants.AUDIOBOOKS_FRAGMENT_TAG, null);
    }

    public void commitFragment(BaseFragment _baseFragment, String _fragmentTag, Bundle _bundle) {

        _baseFragment.setArguments(_bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container_am, _baseFragment, _fragmentTag)
                .addToBackStack(_fragmentTag)
                .commitAllowingStateLoss();
    }

    private void initBottomBar() {

        mBinding.bottomBarAm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem _item) {

                switch (_item.getItemId()) {

                    case R.id.mi_audiobooks:
                        Log.i(LOG_TAG, "AUDIOBOOKS ITEM IS SELECTED.");
                        if (shouldCommitFragment)
                            commitFragment(new AudiobooksFragment(), Constants.AUDIOBOOKS_FRAGMENT_TAG, null);
                        return true;

                    case R.id.mi_movies:
                        Log.i(LOG_TAG, "MOVIES ITEM IS SELECTED.");
                        if (shouldCommitFragment)
                            commitFragment(new MoviesFragment(), Constants.MOVIES_FRAGMENT_TAG, null);
                        return true;

                    case R.id.mi_podcasts:
                        Log.i(LOG_TAG, "PODCASTS ITEM IS SELECTED.");
                        if (shouldCommitFragment)
                            commitFragment(new PodcastsFragment(), Constants.PODCASTS_FRAGMENT_TAG, null);
                        return true;

                    case R.id.mi_favourites:
                        Log.i(LOG_TAG, "FAVOURITES ITEM IS SELECTED.");
                        if (shouldCommitFragment)
                            commitFragment(new FavouritesFragment(), Constants.FAVOURITES_FRAGMENT_TAG, null);
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

    @Override
    public void onBackPressed() {

        final FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() > 2) {

            final String lastFragmentTag =
                    fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 2).getName();

            final String penultFragmentTag =
                    fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 3).getName();

            if (lastFragmentTag.equals(Constants.INFO_FRAGMENT_TAG)) {

                if (penultFragmentTag.equals(Constants.AUDIOBOOKS_FRAGMENT_TAG)) {
                    shouldCommitFragment = false;
                    mBinding.bottomBarAm.setSelectedItemId(R.id.mi_audiobooks);
                    getSupportFragmentManager().popBackStack();
                    return;
                }

                if (penultFragmentTag.equals(Constants.MOVIES_FRAGMENT_TAG)) {
                    shouldCommitFragment = false;
                    mBinding.bottomBarAm.setSelectedItemId(R.id.mi_movies);
                    getSupportFragmentManager().popBackStack();
                    return;
                }

                if (penultFragmentTag.equals(Constants.PODCASTS_FRAGMENT_TAG)) {
                    shouldCommitFragment = false;
                    mBinding.bottomBarAm.setSelectedItemId(R.id.mi_podcasts);
                    getSupportFragmentManager().popBackStack();
                    return;
                }

                if (penultFragmentTag.equals(Constants.FAVOURITES_FRAGMENT_TAG)) {
                    shouldCommitFragment = false;
                    mBinding.bottomBarAm.setSelectedItemId(R.id.mi_favourites);
                    getSupportFragmentManager().popBackStack();
                    return;
                }
            }
        }

        if (fragmentManager.getBackStackEntryCount() > 1) {

            final String fragmentTag =
                    fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 2).getName();

            if (fragmentTag.equals(Constants.AUDIOBOOKS_FRAGMENT_TAG)) {
                shouldCommitFragment = false;
                mBinding.bottomBarAm.setSelectedItemId(R.id.mi_audiobooks);
                getSupportFragmentManager().popBackStack();
                return;
            }

            if (fragmentTag.equals(Constants.MOVIES_FRAGMENT_TAG)) {
                shouldCommitFragment = false;
                mBinding.bottomBarAm.setSelectedItemId(R.id.mi_movies);
                getSupportFragmentManager().popBackStack();
                return;
            }

            if (fragmentTag.equals(Constants.PODCASTS_FRAGMENT_TAG)) {
                shouldCommitFragment = false;
                mBinding.bottomBarAm.setSelectedItemId(R.id.mi_podcasts);
                getSupportFragmentManager().popBackStack();
                return;
            }

            if (fragmentTag.equals(Constants.FAVOURITES_FRAGMENT_TAG)) {
                shouldCommitFragment = false;
                mBinding.bottomBarAm.setSelectedItemId(R.id.mi_favourites);
                getSupportFragmentManager().popBackStack();
                return;
            }
        }

        if (fragmentManager.getBackStackEntryCount() == 1)
            finish();
    }

    public void setShouldCommitFragment(boolean _shouldCommitFragment) {

        this.shouldCommitFragment = _shouldCommitFragment;
    }
}
