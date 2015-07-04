package com.handmark.pulltorefresh.samples.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MBaseAdapter<T> extends BaseAdapter {
	
	protected Context context;
	protected LayoutInflater inflater;
	protected List<T> list;
	protected IBinder mBinder;
	public MBaseAdapter(Context context, List<T> list) {
		super();
		this.context = context;
		inflater = LayoutInflater.from(context);
		setList(list);
	}
	
	public void setmBinder(IBinder mBinder) {
		this.mBinder = mBinder;
	}

	/**
	 * 
	 * @param list
	 * @return 当list不为空且大小大于0时，返回true；否则返回false
	 */
	public boolean setList(List<T> list) {
		if(list != null && list.size() > 0){
			this.list = list;
			this.notifyDataSetChanged();
			return true;
		}else{
			this.list = new ArrayList<T>();
			this.notifyDataSetChanged();
			return false;
		}
	}
	/**
	 * 
	 * @param ts
	 * @return 当ts不为空且大小大于0时，返回true；否则返回false
	 */
	public boolean setList(T[] ts) {
		if(ts != null && ts.length > 0){
			this.list = Arrays.asList(ts);
			this.notifyDataSetChanged();
			return true;
		}  	
		return false;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
