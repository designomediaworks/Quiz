package com.piquor.app.quiz.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.piquor.app.quiz.R;
import com.piquor.app.quiz.model.Category;
import com.piquor.app.quiz.utils.Constant;

import java.io.IOException;
import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private List<Category> categoryList;
    private Context context;

    public GridViewAdapter(List<Category> categoryList,Context context) {
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
        //View row = convertView;
        CategoryHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.category_grid_item, parent, false);

            holder = new CategoryHolder();
            holder.mImageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.mTextView = (TextView) convertView.findViewById(R.id.textView);

            convertView.setTag(holder);
        } else {
            holder = (CategoryHolder) convertView.getTag();
        }

        if(categoryList.get(i).getLogo_path()!=null && categoryList.get(i).getLogo_path()!=""){
            Uri uri = Uri.parse(categoryList.get(i).getLogo_path());
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
                holder.mImageView.setImageBitmap(bitmap);
            }catch (IOException e){
                Log.e("Error","Exception",e);
            }
        }else if(categoryList.get(i).getCategory_id().equals("1")){
            holder.mImageView.setImageResource(R.drawable.gk);
        }else if(categoryList.get(i).getCategory_id().equals("2")){
            holder.mImageView.setImageResource(R.drawable.science);
        }else if(categoryList.get(i).getCategory_id().equals("3")){
            holder.mImageView.setImageResource(R.drawable.geography);
        }else if(categoryList.get(i).getCategory_id().equals("4")){
            holder.mImageView.setImageResource(R.drawable.history);
        }else if(categoryList.get(i).getCategory_id().equals("5")){
            holder.mImageView.setImageResource(R.drawable.computers);
        }else if(categoryList.get(i).getCategory_id().equals("6")){
            holder.mImageView.setImageResource(R.drawable.sports);
        }
        holder.mTextView.setText(categoryList.get(i).getCategory_name());
        return convertView;
    }

    static class CategoryHolder {
        ImageView mImageView;
        TextView mTextView;
    }
}
