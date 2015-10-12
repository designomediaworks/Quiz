package com.piquor.app.quiz.activity;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;

import com.piquor.app.quiz.R;
import com.piquor.app.quiz.fragment.ChangeCategoryNameFragment;
import com.piquor.app.quiz.fragment.ChangeLogoFragment;
import com.piquor.app.quiz.fragment.NavigationDrawerFragment;
import com.piquor.app.quiz.fragment.QuizCategoryFragment;

public class NavigationDrawerActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    public static int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_category);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        if(position == 0){

            fragmentManager.beginTransaction()
                    .replace(R.id.container, new QuizCategoryFragment())
                    .commit();
        }else if(position == 1){
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new ChangeLogoFragment()).addToBackStack(null)
                    .commit();
        }else if(position == 2){
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new ChangeCategoryNameFragment()).addToBackStack(null)
                    .commit();
        }else {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new QuizCategoryFragment())
                    .commit();
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
