package com.fndroid.byweather;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.fndroid.byweather.beans.Weather;
import com.fndroid.byweather.utils.Util;
import com.fndroid.byweather.views.TrendGraph;
import com.google.gson.Gson;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fndroid.byweather.constant.Constant.APIKEY;
import static com.fndroid.byweather.constant.Constant.URL_IMAGE;
import static com.fndroid.byweather.constant.Constant.URL_WEATHER;
import static com.fndroid.byweather.constant.Constant.WX_APPID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Toolbar
		.OnMenuItemClickListener, TrendGraph.onItemClickListener {
	private static final String TAG = "MainActivity";
	private static final int REQUEST_PLACE = 1;
	private static final int THUMB_SIZE = 200;
	private float mDensity;
	private CoordinatorLayout root;
	private FloatingActionButton add;
	private TabLayout mTabLayout;
	private ViewPager mViewPager;
	private Toolbar mToolbar;
	private List<View> mViews;
	private LayoutInflater mLayoutInflater;
	private MyPagerAdapter mAdapter;
	private RequestQueue mRequestQueue;
	private float scale;
	private int heightPixels;
	private int widthPixels;
	private SharedPreferences mSP;
	private SharedPreferences.Editor mSPEditor;
	private List<String> mCityIdList;
	private int vpLoadFinish = 0;
	private int cityListSize = 0;
	private boolean canAnimate = false;
	private LinearLayout mBottomSheet;
	private BottomSheetBehavior<LinearLayout> mBottomSheetBehavior;
	private IWXAPI wxapi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initData();
		initViews();
		loadSPData();
		wxapi = WXAPIFactory.createWXAPI(this, WX_APPID, true);
		wxapi.registerApp(WX_APPID);
	}

	private void loadSPData() {
		String cityIds = mSP.getString("cityIds", "");
		for (String s : cityIds.split("-")) {
			if (!s.equals("")) {
				cityListSize++;
				loadData(s, false);
			}
		}
		mViewPager.setCurrentItem(mSP.getInt("position", 0));
	}

	private void checkExtras() {
		String cityid = getIntent().getStringExtra("cityid");
		if (cityid != null) {
			loadData(cityid, true);
		}
	}

	private void loadData(final String cityId, final boolean isNew) {
		final View vpView = getEmptyWeatherView();
		mViews.add(vpView);
		mAdapter.notifyDataSetChanged();
		StringRequest sr = new StringRequest(URL_WEATHER + "?cityid=" + cityId, new Response
				.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Gson gson = new Gson();
				Weather weather = gson.fromJson(response, Weather.class);
				vpLoadFinish++;
				if (vpLoadFinish == cityListSize) {
					canAnimate = true;
				}
				if (updateWeatherView(vpView, weather)) {
					mAdapter.notifyDataSetChanged();
					String cityid = weather.getRetData().getCityid();
					String cityName = weather.getRetData().getCity();
					if (isNew) {
						saveToSP(cityid);
					}
					mCityIdList.add(cityid);
					getImgUrlByName(cityName,vpView);
				} else {
					mViews.remove(vpView);
					mAdapter.notifyDataSetChanged();
				}
				Log.d(TAG, "onResponse: " + (mToolbar.getHeight() + mViewPager.getHeight()));
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
			}
		}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers = new HashMap<>();
				headers.put("apikey", APIKEY);
				return headers;
			}
		};
		mRequestQueue.add(sr);


	}

	private void getImgUrlByName(String name, final View view) {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL_IMAGE + "?word=" + name +
				"&rn=1&ie=utf-8", null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					JSONObject result = response.getJSONObject("data").getJSONArray
							("ResultArray").getJSONObject(0);
					String url = result.getString("ObjUrl");
					if (url!=null) {
						loadImgByURL(url,view);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

			}
		}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers = new HashMap<>();
				headers.put("apikey", APIKEY);
				return headers;
			}
		};

		mRequestQueue.add(jsonObjectRequest);
	}

	private void loadImgByURL(String url, final View view) {
		ImageView imageView = (ImageView) view.findViewById(R.id.vp_image);
		Glide.with(this).load(url).placeholder(R.drawable.test2).crossFade().into(imageView);
	}

	private View getEmptyWeatherView() {
		View view = mLayoutInflater.inflate(R.layout.viewpager, null);
		view.setTag("");
		return view;
	}

	private boolean updateWeatherView(View view, Weather weather) {
		String viewName;
		TrendGraph forcastGraph = (TrendGraph) view.findViewById(R.id.vp_forcast_trend);
		TrendGraph historyGraph = (TrendGraph) view.findViewById(R.id.vp_history_trend);
		TextView cityName = (TextView) view.findViewById(R.id.vp_cityName);
		TextView weatherDes = (TextView) view.findViewById(R.id.vp_weather);
		TextView tempNow = (TextView) view.findViewById(R.id.vp_tempNow);
		TextView tempMaxMin = (TextView) view.findViewById(R.id.vp_tempMaxMin);
		TextView forcast_1 = (TextView) view.findViewById(R.id.vp_forcast_1);
		TextView forcast_2 = (TextView) view.findViewById(R.id.vp_forcast_2);
		TextView forcast_3 = (TextView) view.findViewById(R.id.vp_forcast_3);
		TextView forcast_4 = (TextView) view.findViewById(R.id.vp_forcast_4);
		TextView history_1 = (TextView) view.findViewById(R.id.vp_history_1);
		TextView history_2 = (TextView) view.findViewById(R.id.vp_history_2);
		TextView history_3 = (TextView) view.findViewById(R.id.vp_history_3);
		TextView history_4 = (TextView) view.findViewById(R.id.vp_history_4);
		TextView history_5 = (TextView) view.findViewById(R.id.vp_history_5);
		TextView index_gm = (TextView) view.findViewById(R.id.vp_index_gm);
		TextView index_gm_desc = (TextView) view.findViewById(R.id.vp_index_gm_desc);
		TextView index_fs = (TextView) view.findViewById(R.id.vp_index_fs);
		TextView index_fs_desc = (TextView) view.findViewById(R.id.vp_index_fs_desc);
		TextView index_ct = (TextView) view.findViewById(R.id.vp_index_ct);
		TextView index_ct_desc = (TextView) view.findViewById(R.id.vp_index_ct_desc);
		TextView index_yd = (TextView) view.findViewById(R.id.vp_index_yd);
		TextView index_yd_desc = (TextView) view.findViewById(R.id.vp_index_yd_desc);
		TextView index_xc = (TextView) view.findViewById(R.id.vp_index_xc);
		TextView index_xc_desc = (TextView) view.findViewById(R.id.vp_index_xc_desc);
		TextView index_ls = (TextView) view.findViewById(R.id.vp_index_ls);
		TextView index_ls_desc = (TextView) view.findViewById(R.id.vp_index_ls_desc);

		CardView forcast_card = (CardView) view.findViewById(R.id.vp_forcast_card);

		forcast_card.setOnClickListener(this);

		Weather.RetDataBean retData = weather.getRetData();
		if (retData != null) {
			cityName.setText(retData.getCity());
			Weather.RetDataBean.TodayBean today = retData.getToday();
			List<Weather.RetDataBean.ForecastBean> forecast = retData.getForecast();
			List<Weather.RetDataBean.HistoryBean> history = retData.getHistory();
			List<Weather.RetDataBean.TodayBean.IndexBean> index = retData.getToday().getIndex();
			weatherDes.setText(today.getType() + "/" + today.getCurTemp());
			tempNow.setText(today.getFengxiang() + "\n" + today.getFengli());
			tempMaxMin.setText(today.getHightemp() + "/" + today.getLowtemp());
			viewName = retData.getCity();
			forcast_1.setText(getForcastString(forecast.get(0)));
			forcast_2.setText(getForcastString(forecast.get(1)));
			forcast_3.setText(getForcastString(forecast.get(2)));
			forcast_4.setText(getForcastString(forecast.get(3)));
			history_1.setText(getHistoryString(history.get(0)));
			history_2.setText(getHistoryString(history.get(1)));
			history_3.setText(getHistoryString(history.get(2)));
			history_4.setText(getHistoryString(history.get(3)));
			history_5.setText(getHistoryString(history.get(4)));
			index_gm.setText(index.get(0).getName() + ":" + index.get(0).getIndex());
			index_gm_desc.setText(index.get(0).getDetails());
			index_fs.setText(index.get(1).getName() + ":" + index.get(1).getIndex());
			index_fs_desc.setText(index.get(1).getDetails());
			index_ct.setText(index.get(2).getName() + ":" + index.get(2).getIndex());
			index_ct_desc.setText(index.get(2).getDetails());
			index_yd.setText(index.get(3).getName() + ":" + index.get(3).getIndex());
			index_yd_desc.setText(index.get(3).getDetails());
			index_xc.setText(index.get(4).getName() + ":" + index.get(4).getIndex());
			index_xc_desc.setText(index.get(4).getDetails());
			index_ls.setText(index.get(5).getName() + ":" + index.get(5).getIndex());
			index_ls_desc.setText(index.get(5).getDetails());

			ArrayList<TrendGraph.Element> forcastElements = new ArrayList<>();
			for (Weather.RetDataBean.ForecastBean f : forecast) {
				TrendGraph.Element element = new TrendGraph.Element(Double.parseDouble(f
						.getHightemp().replace("℃", "")), Double.parseDouble(f.getLowtemp()
						.replace("℃", "")));
				Log.d(TAG, "updateWeatherView: " + element.toString());
				forcastElements.add(element);
			}
			forcastGraph.setElements(forcastElements);
			forcastGraph.setTextSize(10 * mDensity);
			forcastGraph.notifyDataSetChange();
			forcastGraph.setOnItemClickListener(this);

			ArrayList<TrendGraph.Element> historyElements = new ArrayList<>();
			for (int i = 0; i < 5; i++) {
				Weather.RetDataBean.HistoryBean h = history.get(i);
				TrendGraph.Element element = new TrendGraph.Element(Double.parseDouble(h
						.getHightemp().replace("℃", "")), Double.parseDouble(h.getLowtemp()
						.replace("℃", "")));
				historyElements.add(element);
			}
			historyGraph.setElements(historyElements);
			historyGraph.setTextSize(10 * mDensity);
			historyGraph.notifyDataSetChange();
			historyGraph.setOnItemClickListener(this);

			view.setTag(viewName);
			return true;
		} else {
			Log.d(TAG, "getWeatherView: " + weather.getErrMsg());
			return false;
		}
	}


	private String getForcastString(Weather.RetDataBean.ForecastBean forecastBean) {
		StringBuilder sb = new StringBuilder();
		sb.append(forecastBean.getDate());
		sb.append("\n");
		sb.append(forecastBean.getWeek());
		sb.append("\n");
		sb.append(forecastBean.getFengxiang());
		sb.append("\n");
		sb.append(forecastBean.getFengli());
		sb.append("\n");
		sb.append(forecastBean.getHightemp()).append("/").append(forecastBean.getLowtemp());
		sb.append("\n");
		sb.append(forecastBean.getType());
		return sb.toString();
	}

	private String getHistoryString(Weather.RetDataBean.HistoryBean historyBean) {
		StringBuilder sb = new StringBuilder();
		sb.append(historyBean.getDate());
		sb.append("\n");
		sb.append(historyBean.getWeek());
		sb.append("\n");
		sb.append(historyBean.getFengxiang());
		sb.append("\n");
		sb.append(historyBean.getFengli());
		sb.append("\n");
		sb.append(historyBean.getHightemp()).append("/").append(historyBean.getLowtemp());
		sb.append("\n");
		sb.append(historyBean.getType());
		return sb.toString();
	}


	private void initData() {
		mDensity = getApplicationContext().getResources().getDisplayMetrics().density;
		mCityIdList = new ArrayList<>();
		widthPixels = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
		heightPixels = getApplicationContext().getResources().getDisplayMetrics().heightPixels;
		scale = getApplicationContext().getResources().getDisplayMetrics().density;
		mViews = new ArrayList<>();
		mRequestQueue = Volley.newRequestQueue(this);
		mSP = getSharedPreferences("cities", MODE_PRIVATE);

	}

	private void initViews() {
		mBottomSheet = (LinearLayout) findViewById(R.id.main_bottomSheet);
		mBottomSheet.setOnTouchListener(new MyOnTouchListener());
		mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheet);
		mBottomSheetBehavior.setPeekHeight(0);
		mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
		root = (CoordinatorLayout) findViewById(R.id.main_root);
		mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
		setSupportActionBar(mToolbar);
		setTitle(getResources().getText(R.string.app_name));
		mToolbar.setTitleTextColor(Color.WHITE);
		mToolbar.inflateMenu(R.menu.toobar);
		mToolbar.setOnMenuItemClickListener(this);
		mLayoutInflater = LayoutInflater.from(this);
		add = (FloatingActionButton) findViewById(R.id.main_add);
		add.setOnClickListener(this);
		mTabLayout = (TabLayout) findViewById(R.id.main_tabLayout);
		mViewPager = (ViewPager) findViewById(R.id.main_viewPager);
		mAdapter = new MyPagerAdapter();
		mViewPager.setAdapter(mAdapter);
		mTabLayout.setupWithViewPager(mViewPager);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.main_add:
				Intent intent = new Intent(this, AddPlaceActivity.class);
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					startActivityForResult(intent, REQUEST_PLACE, ActivityOptions
							.makeSceneTransitionAnimation(this).toBundle());
				} else {
					startActivityForResult(intent, REQUEST_PLACE);
				}
				break;
			case R.id.vp_forcast_card:
				openOptionsMenu();
				break;
		}
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_delete:
				if (mViews.size() <= 1) {
					Snackbar.make(root, "至少都要有一个城市的天气哦", Snackbar.LENGTH_SHORT).show();
					return false;
				}
				int position = mViewPager.getCurrentItem();
				Log.d(TAG, "onMenuItemClick() called with: position = [" + position + "]");
				removeFromSP(mCityIdList.remove(position));
				mViews.remove(position);
				mAdapter.notifyDataSetChanged();
				break;
			case R.id.menu_share:
				mBottomSheetBehavior.setState(mBottomSheetBehavior.getState() ==
						BottomSheetBehavior.STATE_EXPANDED ? BottomSheetBehavior.STATE_COLLAPSED :
						BottomSheetBehavior.STATE_EXPANDED);
				break;
		}

		return true;
	}

	@Override
	public void onItemClick(View view, TrendGraph.Element element) {
		int dt = (int) (element.getUp() - element.getDown());
		Snackbar.make(root, "当天温差为：" + dt + "℃", Snackbar.LENGTH_SHORT).show();
	}


	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		@Override
		public int getCount() {
			return mViews.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mViews.get(position).getTag().toString();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mViews.get(position));
			return mViews.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_PLACE && resultCode == RESULT_OK) {
			String cityId = data.getStringExtra("cityId");
			if (cityId != null) {
				loadData(cityId, true);
			}
		}
	}

	private Bitmap getScreenImage() {
		Bitmap bitmap;
		NestedScrollView view = (NestedScrollView) mViews.get(mViewPager.getCurrentItem())
				.findViewById(R.id.vp_root);
		int height = view.getChildAt(0).getHeight();
		bitmap = Bitmap.createBitmap(view.getWidth(), height, Bitmap.Config.ARGB_8888);
		Log.d(TAG, "getScreenImage: height=" + height);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawColor(Color.parseColor("#adadad"));
		view.draw(canvas);
		return bitmap;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.toobar, menu);
		return true;
	}

	private void saveToSP(String cityId) {
		String cityIds = mSP.getString("cityIds", "");
		cityIds += cityId + "-";
		mSPEditor = mSP.edit();
		mSPEditor.putString("cityIds", cityIds);
		mSPEditor.apply();
	}

	private void removeFromSP(String cityId) {
		String cityIds = mSP.getString("cityIds", "");
		cityIds = cityIds.replace(cityId + "-", "");
		mSPEditor = mSP.edit();
		mSPEditor.putString("cityIds", cityIds);
		mSPEditor.apply();
	}

	@Override
	protected void onPause() {
		mSPEditor = mSP.edit();
		mSPEditor.putInt("position", mViewPager.getCurrentItem());
		mSPEditor.apply();
		super.onPause();
	}

	private class MyOnTouchListener implements View.OnTouchListener {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				int width = mBottomSheet.getWidth();
				Bitmap screenImg = getScreenImage();
				if (event.getX() < width / 2) {
					Log.d(TAG, "onTouch: 好友");
					sendToWXTimeline(false, screenImg);
				} else {
					Log.d(TAG, "onTouch: 朋友圈");
					sendToWXTimeline(true, screenImg);
				}
			}
			return true;
		}
	}


	private void sendToWXTimeline(boolean comment, Bitmap bitmap) {
		Log.d(TAG, "sendToWXTimeline: ");
		WXImageObject imageObject = new WXImageObject(bitmap);

		WXMediaMessage msg = new WXMediaMessage();
		msg.mediaObject = imageObject;

		Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, THUMB_SIZE, THUMB_SIZE, true);
		msg.thumbData = Util.bmpToByteArray2(thumbBmp, true);  // 设置缩略图

		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransaction("img");
		req.message = msg;
		req.scene = comment ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req
				.WXSceneSession;
		boolean b = wxapi.sendReq(req);
		Log.d(TAG, "sendToWXTimeline: "+b);
	}

	private String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System
				.currentTimeMillis();
	}
}
