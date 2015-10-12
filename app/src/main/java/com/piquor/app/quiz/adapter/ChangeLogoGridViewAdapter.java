package com.piquor.app.quiz.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.piquor.app.quiz.R;
import com.piquor.app.quiz.model.Category;

import java.util.List;

public class ChangeLogoGridViewAdapter extends BaseAdapter {
    private List<Category> categoryList;
    private Context context;

    public ChangeLogoGridViewAdapter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return categoryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        CategoryHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.change_logo_grid_item, parent, false);

            holder = new CategoryHolder();
            holder.mImageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.mTextView = (TextView) convertView.findViewById(R.id.textView);

            convertView.setTag(holder);
        } else {
            holder = (CategoryHolder) convertView.getTag();
        }

        holder.mTextView.setText(categoryList.get(i).getCategory_name());
        return convertView;
    }

    static class CategoryHolder {
        ImageView mImageView;
        TextView mTextView;
    }

    public List<Category> getList(){
        return categoryList;
    }
}
