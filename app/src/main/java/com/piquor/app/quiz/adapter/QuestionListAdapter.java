package com.piquor.app.quiz.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.piquor.app.quiz.R;
import com.piquor.app.quiz.model.Question;
import com.piquor.app.quiz.utils.SharedPreferenceUtils;

import java.util.List;

public class QuestionListAdapter extends BaseAdapter {
    private List<Question> questionList;
    private Context context;

    public QuestionListAdapter(List<Question> questionList, Context context) {
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
            convertView = inflater.inflate(R.layout.question_list_item, parent, false);

            holder = new QuestionHolder();
            holder.mTextView = (TextView) convertView.findViewById(R.id.textView);
            holder.mRadioGroup = (RadioGroup) convertView.findViewById(R.id.radioGroup);
            holder.mRadioButtonA = (RadioButton) convertView.findViewById(R.id.radioButtonA);
            holder.mRadioButtonB = (RadioButton) convertView.findViewById(R.id.radioButtonB);
            holder.mRadioButtonC = (RadioButton) convertView.findViewById(R.id.radioButtonC);
            holder.mRadioButtonD = (RadioButton) convertView.findViewById(R.id.radioButtonD);

            holder.mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    Integer pos = (Integer) radioGroup.getTag();
                    Question element = questionList.get(pos);
                    switch(checkedId){
                        case R.id.radioButtonA:
                            element.current = Question.ANSWER_ONE_SELECTED;
                            break;
                        case R.id.radioButtonB:
                            element.current = Question.ANSWER_TWO_SELECTED;
                            break;
                        case R.id.radioButtonC:
                            element.current = Question.ANSWER_THREE_SELECTED;
                            break;
                        case R.id.radioButtonD:
                            element.current = Question.ANSWER_FOUR_SELECTED;
                            break;
                        default:
                            element.current = Question.NONE;
                    }
                    setCurrentScore();
                }
            });

            convertView.setTag(holder);
        } else {
            holder = (QuestionHolder) convertView.getTag();
        }

        holder.mRadioGroup.setTag(new Integer(i));
        holder.mTextView.setText(questionList.get(i).getQuestion_text());
        holder.mRadioButtonA.setText(questionList.get(i).getOption_a());
        holder.mRadioButtonB.setText(questionList.get(i).getOption_b());
        holder.mRadioButtonC.setText(questionList.get(i).getOption_c());
        holder.mRadioButtonD.setText(questionList.get(i).getOption_d());

        if (questionList.get(i).current != Question.NONE) {
            RadioButton r = (RadioButton) holder.mRadioGroup.getChildAt(questionList.get(i).current);
            r.setChecked(true);
        } else {
            holder.mRadioGroup.clearCheck(); // This is required because although the Model could have the current
            // position to NONE you could be dealing with a previous row where
            // the user already picked an answer.

        }
        return convertView;
    }

    static class QuestionHolder {
        TextView mTextView;
        RadioButton mRadioButtonA,mRadioButtonB,mRadioButtonC,mRadioButtonD;
        RadioGroup mRadioGroup;
    }

   public void setCurrentScore(){
       int score =0;
       for(Question question : questionList){
           if(question.current==0 && question.getOption_a().equals(question.getAnswer()))
           {
               score = score + 20;
           }else if(question.current==1 && question.getOption_b().equals(question.getAnswer()))
           {
               score = score + 20;
           }else if(question.current==2 && question.getOption_c().equals(question.getAnswer()))
           {
               score = score + 20;
           }else if(question.current==3 && question.getOption_d().equals(question.getAnswer()))
           {
               score = score + 20;
           }
       }
       SharedPreferenceUtils.setScore(context,score);
    }
}
