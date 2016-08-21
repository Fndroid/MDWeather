package com.fndroid.byweather.textwatcher;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Administrator on 2016/7/6.
 */
public class MyTextWatcher implements TextWatcher {
	private static final String TAG = "MyTextWatcher";

	private TextChangeListener listener;

	public MyTextWatcher(TextChangeListener listener) {
		this.listener = listener;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		listener.onTextChange(s.toString());
	}
}
