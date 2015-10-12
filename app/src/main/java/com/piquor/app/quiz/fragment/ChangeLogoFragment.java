package com.piquor.app.quiz.fragment;


import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.piquor.app.quiz.R;
import com.piquor.app.quiz.adapter.ChangeLogoGridViewAdapter;
import com.piquor.app.quiz.adapter.GridViewAdapter;
import com.piquor.app.quiz.database.CategoryDBDataSource;
import com.piquor.app.quiz.database.DBHelper;
import com.piquor.app.quiz.loader.GenericDataLoader;
import com.piquor.app.quiz.model.Category;
import com.piquor.app.quiz.utils.Constant;
import com.piquor.app.quiz.utils.ViewUtils;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeLogoFragment extends Fragment implements View.OnClickListener {

    private static final int LOADER_ID = 4;
    private GridView gridView;
    private int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private Button changeButton;
    private List<Category> categoryList;
    private Category category;
    private CategoryDBDataSource categorydataSource;

    public ChangeLogoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chane_logo, container, false);
        changeButton = (Button)view.findViewById(R.id.changeButton);
        changeButton.setOnClickListener(this);
        gridView = (GridView)view.findViewById(R.id.gridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                imageView = (ImageView) view.findViewById(R.id.imageView);
                category = (Category)adapterView.getAdapter().getItem(i);
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });
        categorydataSource = new CategoryDBDataSource(DBHelper.getInstance(getActivity()).getReadableDatabase());
        categoryList = categorydataSource.read();
        ChangeLogoGridViewAdapter adapter = new ChangeLogoGridViewAdapter(categoryList,getActivity());
        gridView.setAdapter(adapter);
        //getActivity().getLoaderManager().initLoader(LOADER_ID,null,this);
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                category.setLogo_path(uri.toString());
            } catch (IOException e) {
                Log.e("Error","Exception",e);
            }
        }
    }

    @Override
    public void onClick(View view) {
        boolean success = false;
        ChangeLogoGridViewAdapter adapter = (ChangeLogoGridViewAdapter)gridView.getAdapter();
        for(Category category : adapter.getList()){
            if(category.getLogo_path()!=null && category.getLogo_path() !="") {
                success = categorydataSource.update(category);
                if (!success) {
                    ViewUtils.showOkAlert(getActivity(), "Error changing logos.");
                    break;
                }
            }
        }
        if(success) {
            ViewUtils.showOkAlert(getActivity(), "New logos added.");
        }
    }


}

