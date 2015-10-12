package com.piquor.app.quiz.fragment;


import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.piquor.app.quiz.R;
import com.piquor.app.quiz.adapter.QuestionListAdapter;
import com.piquor.app.quiz.database.DBHelper;
import com.piquor.app.quiz.database.QuestionDBDataSource;
import com.piquor.app.quiz.loader.GenericDataLoader;
import com.piquor.app.quiz.model.Question;
import com.piquor.app.quiz.utils.Constant;
import com.piquor.app.quiz.utils.SharedPreferenceUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Question>>{

    private String category_id;
    private static final int LOADER_ID = 2;
    private ListView listView;
    private Bundle bundle;

    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bundle = getArguments();
        category_id = bundle.getString(Constant.CATEGORY_ID);
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        listView = (ListView)view.findViewById(R.id.questionListView);
        getLoaderManager().initLoader(LOADER_ID, bundle, this);
        return view;
    }

    @Override
    public Loader<List<Question>> onCreateLoader(int i, Bundle bundle) {
        String category_id = bundle.getString(Constant.CATEGORY_ID);
        QuestionDBDataSource questionDBDataSource = new QuestionDBDataSource(DBHelper.getInstance(getActivity()).getReadableDatabase());
        GenericDataLoader dataLoader = new GenericDataLoader(getActivity(),questionDBDataSource,QuestionDBDataSource.COLUMN_CATEGORY_ID+"=?",new String[] {category_id},null,null,null);
        return dataLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<Question>> loader, List<Question> questions) {
        SharedPreferenceUtils.setScore(getActivity(), 0);
        QuestionListAdapter adapter = new QuestionListAdapter(questions,getActivity());
        listView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<List<Question>> loader) {

    }

}
