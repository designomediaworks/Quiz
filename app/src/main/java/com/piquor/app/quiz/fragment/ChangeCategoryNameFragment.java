package com.piquor.app.quiz.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.piquor.app.quiz.R;
import com.piquor.app.quiz.adapter.ChangeLogoGridViewAdapter;
import com.piquor.app.quiz.database.CategoryDBDataSource;
import com.piquor.app.quiz.database.DBHelper;
import com.piquor.app.quiz.model.Category;
import com.piquor.app.quiz.utils.ValidationUtil;
import com.piquor.app.quiz.utils.ViewUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeCategoryNameFragment extends Fragment implements View.OnClickListener{

    private EditText category1,category2,category3,category4,category5,category6;
    private TextView categoryTV1,categoryTV2,categoryTV3,categoryTV4,categoryTV5,categoryTV6;
    private List<Category> categoryList;
    private Button changeButton;
    private CategoryDBDataSource categorydataSource;

    public ChangeCategoryNameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_category_name, container, false);
        category1 = (EditText)view.findViewById(R.id.category1);
        category2 = (EditText)view.findViewById(R.id.category2);
        category3 = (EditText)view.findViewById(R.id.category3);
        category4 = (EditText)view.findViewById(R.id.category4);
        category5 = (EditText)view.findViewById(R.id.category5);
        category6 = (EditText)view.findViewById(R.id.category6);

        categoryTV1 = (TextView)view.findViewById(R.id.categoryTV1);
        categoryTV2 = (TextView)view.findViewById(R.id.categoryTV2);
        categoryTV3 = (TextView)view.findViewById(R.id.categoryTV3);
        categoryTV4 = (TextView)view.findViewById(R.id.categoryTV4);
        categoryTV5 = (TextView)view.findViewById(R.id.categoryTV5);
        categoryTV6 = (TextView)view.findViewById(R.id.categoryTV6);

        changeButton = (Button)view.findViewById(R.id.changeButton);
        changeButton.setOnClickListener(this);

        categorydataSource = new CategoryDBDataSource(DBHelper.getInstance(getActivity()).getReadableDatabase());
        categoryList = categorydataSource.read();
        for(int i=0;i<categoryList.size();i++){
            switch (i) {
                case 0:
                    categoryTV1.setText(categoryList.get(i).getCategory_name());
                    break;
                case 1:
                    categoryTV2.setText(categoryList.get(i).getCategory_name());
                    break;
                case 2:
                    categoryTV3.setText(categoryList.get(i).getCategory_name());
                    break;
                case 3:
                    categoryTV4.setText(categoryList.get(i).getCategory_name());
                    break;
                case 4:
                    categoryTV5.setText(categoryList.get(i).getCategory_name());
                    break;
                case 5:
                    categoryTV6.setText(categoryList.get(i).getCategory_name());
                    break;
            }
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        boolean isAllEmpty = true;
        if(ValidationUtil.isNotEmpty(category1.getText().toString())){
            isAllEmpty = false;
            categoryList.get(0).setCategory_name(category1.getText().toString());
        }else if( ValidationUtil.isNotEmpty(category2.getText().toString())){
            isAllEmpty = false;
            categoryList.get(1).setCategory_name(category2.getText().toString());
        }else if( ValidationUtil.isNotEmpty(category3.getText().toString())){
            isAllEmpty = false;
            categoryList.get(2).setCategory_name(category3.getText().toString());
        }else if(ValidationUtil.isNotEmpty(category4.getText().toString())) {
            isAllEmpty = false;
            categoryList.get(3).setCategory_name(category4.getText().toString());
        }else if(ValidationUtil.isNotEmpty(category5.getText().toString())) {
            isAllEmpty = false;
            categoryList.get(4).setCategory_name(category5.getText().toString());
        }else if(ValidationUtil.isNotEmpty(category6.getText().toString())) {
            isAllEmpty = false;
            categoryList.get(5).setCategory_name(category6.getText().toString());
        }
        boolean success=false;
        if(!isAllEmpty) {
            for (Category category : categoryList) {
                success = categorydataSource.update(category);
                if (!success) {
                    ViewUtils.showOkAlert(getActivity(), "Error changing names.");
                    break;
                }
            }
            if(success) {
                ViewUtils.showOkAlert(getActivity(), "Names changed successfully.");
            }
        }else{
            ViewUtils.showOkAlert(getActivity(), "Please enter values.");
        }
    }
}
