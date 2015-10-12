package com.piquor.app.quiz.fragment;


import android.app.LoaderManager;
import android.content.Loader;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.piquor.app.quiz.R;
import com.piquor.app.quiz.adapter.DeleteQuestionListAdapter;
import com.piquor.app.quiz.adapter.QuestionListAdapter;
import com.piquor.app.quiz.database.DBHelper;
import com.piquor.app.quiz.database.QuestionDBDataSource;
import com.piquor.app.quiz.loader.GenericDataLoader;
import com.piquor.app.quiz.model.Question;
import com.piquor.app.quiz.utils.Constant;
import com.piquor.app.quiz.utils.SharedPreferenceUtils;
import com.piquor.app.quiz.utils.ViewUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteQuestionFragment extends Fragment  implements LoaderManager.LoaderCallbacks<List<Question>>, View.OnClickListener {

    private static final int LOADER_ID = 3;
    private ListView listView;
    private Button deleteButton;
    private DeleteQuestionListAdapter adapter;
    private GenericDataLoader dataLoader;
    private Bundle bundle;

    public DeleteQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_question, container, false);
        listView = (ListView)view.findViewById(R.id.questionList);
        deleteButton = (Button)view.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);
        bundle = getArguments();
        getLoaderManager().initLoader(LOADER_ID,bundle,this);
        return view;
    }

    @Override
    public Loader<List<Question>> onCreateLoader(int i, Bundle bundle) {
        String category_id = bundle.getString(Constant.CATEGORY_ID);
        QuestionDBDataSource questionDBDataSource = new QuestionDBDataSource(DBHelper.getInstance(getActivity()).getReadableDatabase());
        dataLoader = new GenericDataLoader(getActivity(),questionDBDataSource,QuestionDBDataSource.COLUMN_CATEGORY_ID+"=?",new String[] {category_id},null,null,null);
        return dataLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<Question>> loader, List<Question> questions) {
        SharedPreferenceUtils.setScore(getActivity(), 0);
        adapter = new DeleteQuestionListAdapter(questions,getActivity());
        listView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<List<Question>> loader) {

    }


    @Override
    public void onClick(View view) {
        boolean success=false;
        List<Question> questionList =  adapter.getList();
        SQLiteDatabase database = DBHelper.getInstance(getActivity()).getWritableDatabase();
        QuestionDBDataSource dataSource = new QuestionDBDataSource(database);
        for(Question question : questionList){
            if(question.isChecked()) {
                success = dataSource.delete(question);
                if (!success) {
                    ViewUtils.showOkAlert(getActivity(), "Error deleting questions.");
                    break;
                }
            }
        }
        if(success) {
            getLoaderManager().restartLoader(LOADER_ID, bundle, this);
            ViewUtils.showOkAlert(getActivity(), "Selected questions deleted.");
        }
    }
}
