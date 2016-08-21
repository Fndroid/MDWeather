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

public class FbaDisappearBehavior extends FloatingActionButton.Behavior {
	private static final String TAG = "FbaDisappearBehavior";

	public FbaDisappearBehavior() {
	}

	public FbaDisappearBehavior(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
	                           View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int
			                               dyUnconsumed) {
		super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
				dxUnconsumed, dyUnconsumed);
		if (dyConsumed < 0) {
//			child.show();
			child.setVisibility(View.VISIBLE);
		} else if (dyConsumed > 0){
//			child.hide();
			child.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton
			child, View directTargetChild, View target, int nestedScrollAxes) {
		return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll
				(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
	}
}
