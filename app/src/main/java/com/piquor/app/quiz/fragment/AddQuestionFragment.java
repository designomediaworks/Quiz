package com.piquor.app.quiz.fragment;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.piquor.app.quiz.R;
import com.piquor.app.quiz.utils.Constant;
import com.piquor.app.quiz.database.DBHelper;
import com.piquor.app.quiz.database.QuestionDBDataSource;
import com.piquor.app.quiz.model.Question;
import com.piquor.app.quiz.utils.ValidationUtil;
import com.piquor.app.quiz.utils.ViewUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddQuestionFragment extends Fragment implements View.OnClickListener {
    private Spinner answerSpinner;
    private String[] options = {"A","B","C","D"};

    private EditText questionET,optionAET,optionBET,optionCET,optionDET;
    private Button addButton;
    private String category_id;

    public AddQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_question, container, false);
        Bundle bundle = getArguments();
        category_id = bundle.getString(Constant.CATEGORY_ID);
        answerSpinner = (Spinner)view.findViewById(R.id.answerSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,options);
        answerSpinner.setAdapter(adapter);
        addButton = (Button)view.findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
        questionET = (EditText)view.findViewById(R.id.questionET);
        optionAET = (EditText)view.findViewById(R.id.optionAET);
        optionBET = (EditText)view.findViewById(R.id.optionBET);
        optionCET = (EditText)view.findViewById(R.id.optionCET);
        optionDET = (EditText)view.findViewById(R.id.optionDET);
        return view;
    }


    @Override
    public void onClick(View view) {
        String question = questionET.getText().toString();
        String optionA = optionAET.getText().toString();
        String optionB = optionBET.getText().toString();
        String optionC = optionCET.getText().toString();
        String optionD = optionDET.getText().toString();

        if(ValidationUtil.isNotEmpty(question) && ValidationUtil.isNotEmpty(optionA) && ValidationUtil.isNotEmpty(optionB)
                && ValidationUtil.isNotEmpty(optionC) && ValidationUtil.isNotEmpty(optionD)){
            SQLiteDatabase database = DBHelper.getInstance(getActivity()).getWritableDatabase();
            QuestionDBDataSource dataSource = new QuestionDBDataSource(database);
            Question newQuestion = new Question();
            newQuestion.setQuestion_text(question);
            newQuestion.setOption_a(optionA);
            newQuestion.setOption_b(optionB);
            newQuestion.setOption_c(optionC);
            newQuestion.setOption_d(optionD);
            int optionSelected = answerSpinner.getSelectedItemPosition();
            if(optionSelected == 0 ){
                newQuestion.setAnswer(optionA);
            }else if(optionSelected == 1){
                newQuestion.setAnswer(optionB);
            }else if(optionSelected == 2){
                newQuestion.setAnswer(optionC);
            }else if(optionSelected == 3){
                newQuestion.setAnswer(optionD);
            }else{
                newQuestion.setAnswer(optionA);
            }
            newQuestion.setCategory_id(category_id);
            boolean success = dataSource.insert(newQuestion);
            if(success){
                ViewUtils.showOkAlert(getActivity(),"Question Added.");
                setUpUI();
            }else{
                ViewUtils.showOkAlert(getActivity(),"Error. Please try again.");
            }
        }else{
            ViewUtils.showOkAlert(getActivity(),"Please enter all fields.");
        }
    }

    private void setUpUI() {
        questionET.setText("");
        optionAET.setText("");
        optionBET.setText("");
        optionCET.setText("");
        optionDET.setText("");
        answerSpinner.setSelection(0);
    }
}
