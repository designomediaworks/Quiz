package com.piquor.app.quiz.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;
import com.piquor.app.quiz.fragment.AddQuestionFragment;
import com.piquor.app.quiz.fragment.DeleteQuestionFragment;
import com.piquor.app.quiz.fragment.QuestionFragment;
import com.piquor.app.quiz.fragment.ScoreFragment;
import com.piquor.app.quiz.utils.Constant;
import com.piquor.app.quiz.utils.SharedPreferenceUtils;

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
    private Bundle bundle;
    public FragmentViewPagerAdapter(FragmentManager fm, Bundle bundle) {
        super(fm);
        this.bundle = bundle;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment=null;
        if(i==0) {
            fragment = new QuestionFragment();
            fragment.setArguments(bundle);
        }else if(i==1){
            fragment = new ScoreFragment();
        }else if(i==2){
            fragment = new AddQuestionFragment();
            fragment.setArguments(bundle);
        }else if(i==3){
            fragment = new DeleteQuestionFragment();
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Quiz";
            case 1:
                return "Score";
            case 2:
                return "Add";
            case 3:
                return "Delete";
        }
        return null;
    }


}