package com.fndroid.byweather;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fndroid.byweather.adapter.PlacesListAdapter;
import com.fndroid.byweather.beans.CityPinyin;
import com.fndroid.byweather.beans.PlaceList;
import com.fndroid.byweather.beans.RetDataBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fndroid.byweather.constant.Constant.APIKEY;
import static com.fndroid.byweather.constant.Constant.URL_PLACELIST;
import static com.fndroid.byweather.constant.Constant.URL_PLACEPINYIN;

public class AddPlaceActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener, TextView.OnEditorActionListener {
    private static final String TAG = "AddPlaceActivity";
    private ListView placeList;
    private CardView inputCard;
    private TextInputEditText placeName;
    private RequestQueue mRequestQueue;
    private BaseAdapter mAdapter;
    private List<RetDataBean> mRetDataBeanList;
    private boolean isPinyin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        mRequestQueue = Volley.newRequestQueue(this);
        initViews();
        inputCard.post(new Runnable() {
            @Override
            public void run() {
                int half_width = inputCard.getWidth() / 2;
                int half_height = inputCard.getHeight() / 2;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator circularReveal = ViewAnimationUtils.createCircularReveal(inputCard,
                            half_width, half_height, 0, (float) Math.hypot
                                    (half_height, half_width));
                    circularReveal.setDuration(300);
                    circularReveal.setInterpolator(new AccelerateDecelerateInterpolator());
                    circularReveal.setStartDelay(200);
                    circularReveal.start();
                }
            }
        });
    }

    private void initViews() {
        inputCard = (CardView) findViewById(R.id.add_inputCard);
        placeList = (ListView) findViewById(R.id.add_placeList);
        placeName = (TextInputEditText) findViewById(R.id.add_placeName);
        placeName.setOnEditorActionListener(this);
        mRetDataBeanList = new ArrayList<>();
        mAdapter = new PlacesListAdapter(this, mRetDataBeanList);
        placeList.setAdapter(mAdapter);
        placeList.setOnItemClickListener(this);
    }


    private void sendRequestForPlaceList(String name) {
        String url;
        isPinyin = name.length() == name.getBytes().length;
        if (!isPinyin) {
            Log.d(TAG, "sendRequestForPlaceList: 是英文");
            url = URL_PLACELIST + "?cityname=" + name;
        } else {
            url = URL_PLACEPINYIN + "?citypinyin=" + name;
        }
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<RetDataBean> responseDataBean = getPlaceList(response);
                mRetDataBeanList.clear();
                if (responseDataBean != null) {
                    mRetDataBeanList.addAll(responseDataBean);
                }
                mAdapter.notifyDataSetChanged();
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
        mRequestQueue.add(stringRequest);
    }

    private List<RetDataBean> getPlaceList(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        Gson gson = new Gson();
        PlaceList places;
        if (isPinyin) {
            json = json.replace("[", "{");
            json = json.replace("]", "}");
            CityPinyin cityPinyin = gson.fromJson(json, CityPinyin.class);
            places = new PlaceList();
            if (cityPinyin != null && cityPinyin.getErrNum() == 0) {
                Log.d(TAG, "getPlaceList: " + cityPinyin.getRetData().getCity());
                places.setErrNum(cityPinyin.getErrNum());
                List<RetDataBean> retDatas = new ArrayList<>();
                RetDataBean retDataBean = new RetDataBean();
                CityPinyin.RetDataBean retData = cityPinyin.getRetData();
                retDataBean.setDistrict_cn(retData.getCity());
                retDataBean.setProvince_cn(retData.getPinyin());
                retDataBean.setName_cn(retData.getPostCode());
                retDataBean.setArea_id(retData.getCitycode());
                retDatas.add(retDataBean);
                places.setRetData(retDatas);
            } else if (cityPinyin == null) {
                Log.d(TAG, "getPlaceList: cityPinyin为空");
            } else {
                Log.d(TAG, "getPlaceList: " + cityPinyin.getErrMsg());
                placeName.setError(cityPinyin.getErrMsg());
            }
        } else {
            places = gson.fromJson(json, PlaceList.class);
        }
        if (places.getErrNum() == 0) {
            return places.getRetData();
        } else {
            placeName.setError(places.getErrMsg());
            return null;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("cityId", mRetDataBeanList.get(position).getArea_id());
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_SEARCH:
                sendRequestForPlaceList(v.getText().toString());
                break;
        }
        return true;
    }
}
