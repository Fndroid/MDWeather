package com.fndroid.byweather.behaviors;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/7/27.
 */

public class FbaDIsappearBehaviro extends FloatingActionButton.Behavior {
	private static final String TAG = "FbaDIsappearBehaviro";
	private boolean disappear = true;

	public FbaDIsappearBehaviro() {
	}

	public FbaDIsappearBehaviro(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
	                           View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int
			                               dyUnconsumed) {
		super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
				dxUnconsumed, dyUnconsumed);

		if (dyConsumed < 0 && !disappear) {
//			child.scrollBy(0, -200);
			child.show();
			disappear = true;
		} else if (dyConsumed > 0 && disappear){
//			child.scrollBy(0, 200);
			child.hide();
			disappear = false;
		}
	}

	@Override
	public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton
			child, View directTargetChild, View target, int nestedScrollAxes) {
		return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll
				(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
	}
}
