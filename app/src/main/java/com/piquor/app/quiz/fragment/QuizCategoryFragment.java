package com.piquor.app.quiz.fragment;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.piquor.app.quiz.R;
import com.piquor.app.quiz.adapter.GridViewAdapter;
import com.piquor.app.quiz.database.CategoryDBDataSource;
import com.piquor.app.quiz.database.DBHelper;
import com.piquor.app.quiz.loader.GenericDataLoader;
import com.piquor.app.quiz.model.Category;
import com.piquor.app.quiz.utils.Constant;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {QuizCategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuizCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizCategoryFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Category>>{
    private static final int LOADER_ID = 1;
    private GridView gridView;

    //private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     */
    public static QuizCategoryFragment newInstance(String param1, String param2) {
        QuizCategoryFragment fragment = new QuizCategoryFragment();
        return fragment;
    }

    public QuizCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_category, container, false);
        gridView = (GridView)view.findViewById(R.id.gridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Category category = (Category) adapterView.getItemAtPosition(i);
                Bundle bundle = new Bundle();
                bundle.putString(Constant.CATEGORY_ID, category.getCategory_id());
                TabbedFragment fragment = new TabbedFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment).addToBackStack(null)
                        .commit();
            }
        });
        //getActivity().getLoaderManager().initLoader(LOADER_ID,null,this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getLoaderManager().restartLoader(LOADER_ID,null,this);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        CategoryDBDataSource categorydataSource = new CategoryDBDataSource(DBHelper.getInstance(getActivity()).getReadableDatabase());
        GenericDataLoader dataLoader = new GenericDataLoader(getActivity(),categorydataSource,null,null,null,null,null);
        return dataLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<Category>> loader, List<Category> data) {
        GridViewAdapter adapter = new GridViewAdapter(data,getActivity());
        gridView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

}
