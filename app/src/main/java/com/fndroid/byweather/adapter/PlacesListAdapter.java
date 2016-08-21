package com.fndroid.byweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fndroid.byweather.R;
import com.fndroid.byweather.beans.RetDataBean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */

public class PlacesListAdapter extends BaseAdapter {
	private static final String TAG = "PlacesListAdapter";
	private List<RetDataBean> mData;
	private Context mContext;

	public PlacesListAdapter(Context context, List<RetDataBean> data) {
		mData = data;
		mContext = context;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	private static class ViewHolder {
		TextView placeInfo;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View itemView;
		ViewHolder viewHolder;
		if (convertView == null) {
			itemView = LayoutInflater.from(mContext).inflate(R.layout.item_places, null);
			viewHolder = new ViewHolder();
			viewHolder.placeInfo = (TextView) itemView.findViewById(R.id.item_placeInfo);
			itemView.setTag(viewHolder);
		} else {
			itemView = convertView;
			viewHolder = (ViewHolder) itemView.getTag();
		}
		RetDataBean rd = mData.get(position);
		String province = rd.getProvince_cn();
		String district = rd.getDistrict_cn();
		String name = rd.getName_cn();
		viewHolder.placeInfo.setText(province + " " + district + " " + name);
		return itemView;
	}
}
