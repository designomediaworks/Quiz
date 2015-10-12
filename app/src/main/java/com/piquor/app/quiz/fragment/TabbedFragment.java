package com.piquor.app.quiz.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v13.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.piquor.app.quiz.R;
import com.piquor.app.quiz.adapter.FragmentViewPagerAdapter;
import com.piquor.app.quiz.utils.SharedPreferenceUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabbedFragment extends Fragment {

    private FragmentViewPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private FragmentTabHost mTabHost;
    public TabbedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabbed, container, false);
        //pagerAdapter =new FragmentViewPagerAdapter(getFragmentManager(),getArguments());
        //viewPager = (ViewPager) view.findViewById(R.id.pager);
        Bundle bundle = getArguments();
        mTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("Quiz"),
                QuestionFragment.class, bundle);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Score"),
                ScoreFragment.class, bundle);
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("Add"),
                AddQuestionFragment.class, bundle);
        mTabHost.addTab(mTabHost.newTabSpec("tab4").setIndicator("Delete"),
                DeleteQuestionFragment.class, bundle);
        return view;
    }

   /* @Override
    public void onResume() {
        super.onResume();
        //viewPager.setAdapter(pagerAdapter);
    }*/
}
