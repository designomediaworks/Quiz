package com.piquor.app.quiz.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.piquor.app.quiz.R;
import com.piquor.app.quiz.model.Question;
import com.piquor.app.quiz.utils.SharedPreferenceUtils;

import java.util.List;

public class DeleteQuestionListAdapter extends BaseAdapter {
    private List<Question> questionList;
    private Context context;

    public DeleteQuestionListAdapter(List<Question> questionList, Context context) {
        this.questionList = questionList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return questionList.size();
    }

    @Override
    public Object getItem(int i) {
        return questionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        //View row = convertView;
        QuestionHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.delete_question_list_item, parent, false);

            holder = new QuestionHolder();
            holder.mTextView = (CheckedTextView) convertView.findViewById(R.id.textView);

            convertView.setTag(holder);
        } else {
            holder = (QuestionHolder) convertView.getTag();
        }

        holder.mTextView.setTag(new Integer(i));
        holder.mTextView.setText(questionList.get(i).getQuestion_text());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckedTextView textView = ((CheckedTextView) view);
                textView.toggle();
                Integer pos = (Integer)textView.getTag();
                Question element = questionList.get(pos);
                if(textView.isChecked()){
                    element.setChecked(true);
                }else{
                    element.setChecked(false);
                }

            }
        });

        if (questionList.get(i).isChecked()) {
            holder.mTextView.setChecked(true);
        } else {
            holder.mTextView.setChecked(false);
        }

        return convertView;
    }

    static class QuestionHolder {
        CheckedTextView mTextView;
    }

   public List<Question> getList(){
       return questionList;
    }
}
