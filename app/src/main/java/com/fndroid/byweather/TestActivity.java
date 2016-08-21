package com.fndroid.byweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fndroid.byweather.views.TrendGraph;

import java.util.ArrayList;
import java.util.Random;

public class TestActivity extends AppCompatActivity {
	private static final String TAG = "TestActivity";
	private TrendGraph mTrendGraph;
	private ArrayList<TrendGraph.Element> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		mTrendGraph = (TrendGraph) findViewById(R.id.tg);
		data = new ArrayList<>();
		Random random = new Random();
		TrendGraph.Element element = new TrendGraph.Element(35, 22);
		data.add(element);
		element = new TrendGraph.Element(32, 25);
		data.add(element);
		element = new TrendGraph.Element(34, 25);
		data.add(element);
		element = new TrendGraph.Element(30, 20);
		data.add(element);
		element = new TrendGraph.Element(32, 31);
		data.add(element);


		mTrendGraph.setElements(data);


		mTrendGraph.notifyDataSetChange();
	}
}
