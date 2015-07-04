package com.handmark.pulltorefresh.samples;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.samples.adapter.MBaseAdapter;

public class ListViewActivity extends Activity {

	private PullToRefreshListView mPullRefreshListView;
	List<String> dataList;
	MAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ptr_list);
		init();
	}
	private void init(){
		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				Toast.makeText(ListViewActivity.this, "Pull Down List!", Toast.LENGTH_SHORT).show();
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
				refreshView.getLoadingLayoutProxy(true, false).setLastUpdatedLabel("LastUpdatedLabel:"+label);
//				refreshView.getLoadingLayoutProxy(true, false).setRefreshingLabel("RefreshingLabel:"+label);
//				refreshView.getLoadingLayoutProxy(true, false).setReleaseLabel("ReleaseLabel:"+label);
//				refreshView.getLoadingLayoutProxy(true, false).setPullLabel("PullLabel"+label);
//				refreshView.getLoadingLayoutProxy(true, false).setLoadingDrawable(getResources().getDrawable(R.drawable.default_ptr_flip));;
				new MAsyncTask().execute(1);
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				Toast.makeText(ListViewActivity.this, "Pull Down List!", Toast.LENGTH_SHORT).show();
				new MAsyncTask().execute(2);
			}
		});
		dataList = getlist();
		adapter = new MAdapter<String>(this, dataList);
		mPullRefreshListView.setAdapter(adapter);
	}
	List<String> getlist(){
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			list.add("name:"+i);
		}
		return list;
	}
	int top = 0;
	int bottom = 0;
	private class MAsyncTask extends AsyncTask<Integer, Integer, String>{

		@Override
		protected java.lang.String doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			if(params.length > 0){
				if(params[0] == 1){
					dataList.add(0, "top-"+(top++));
				}else{
					dataList.add(dataList.size(), "bottom-"+(bottom++));
				}
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "doInBackground";
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(java.lang.String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			adapter.notifyDataSetChanged();
			mPullRefreshListView.onRefreshComplete();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		@SuppressLint("NewApi") @Override
		protected void onCancelled(java.lang.String result) {
			// TODO Auto-generated method stub
			super.onCancelled(result);
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}
		
	}
}
class MAdapter<String> extends MBaseAdapter<String>{

	public MAdapter(Context context, List<String> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Viewholder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item, null);
			holder = new Viewholder();
			holder.tv = (TextView)convertView.findViewById(R.id.tv);
			holder.index = (TextView)convertView.findViewById(R.id.tv_index);
			convertView.setTag(holder);
		}else{
			holder = (Viewholder)convertView.getTag();
		}
		holder.index.setText("index:"+position);
		holder.tv.setText(""+list.get(position));
		return convertView;
	}
	
	class Viewholder{
		TextView tv;
		TextView index;
		
	}
}















