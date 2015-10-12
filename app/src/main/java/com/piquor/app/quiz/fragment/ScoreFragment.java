package com.piquor.app.quiz.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.piquor.app.quiz.R;
import com.piquor.app.quiz.utils.Constant;
import com.piquor.app.quiz.utils.SharedPreferenceUtils;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends Fragment {

    private TextView scoreTV;

    public ScoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);
        scoreTV = (TextView)view.findViewById(R.id.scoreTV);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        int score = SharedPreferenceUtils.getScore(getActivity());
        scoreTV.setText(score + "");
    }

}
