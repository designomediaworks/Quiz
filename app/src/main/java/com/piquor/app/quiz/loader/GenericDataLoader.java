package com.piquor.app.quiz.loader;

import java.util.List;

import android.content.Context;

import com.piquor.app.quiz.database.DataSource;
import com.piquor.app.quiz.model.BaseModel;


public class GenericDataLoader extends DataLoader<List<? extends BaseModel>> {

		private DataSource<? extends BaseModel> mDataSource;
		private String mSelection;
		private String[] mSelectionArgs;
		private String mGroupBy;
		private String mHaving;
		private String mOrderBy;

		public GenericDataLoader(Context context, DataSource<? extends BaseModel> dataSource, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
			super(context);
			mDataSource = dataSource;
			mSelection = selection;
			mSelectionArgs = selectionArgs;
			mGroupBy = groupBy;
			mHaving = having;
			mOrderBy = orderBy;
		}

		@Override
		protected List<? extends BaseModel> buildList() {
			List<? extends BaseModel> testList = mDataSource.read(mSelection, mSelectionArgs, mGroupBy, mHaving, mOrderBy);
			return testList;
		}


	}

