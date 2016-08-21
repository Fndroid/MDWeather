package com.fndroid.byweather.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.fndroid.byweather.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/23.
 */

public class TrendGraph extends View {
	private static final String TAG = "TrendGraph";
	private onItemClickListener mOnItemClickListener;
	private ArrayList<Element> mElements;
	private Paint mPaint = new Paint();
	private Paint mTextPaint = new Paint();
	private int circleRadius = 5;
	private int lineWeith = 3;
	private DrawFilter mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint
			.FILTER_BITMAP_FLAG);

	public TrendGraph(Context context) {
		super(context);
	}

	public TrendGraph(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TrendGraph);
		circleRadius = array.getDimensionPixelSize(R.styleable.TrendGraph_circleRadius, 5);
		lineWeith = array.getDimensionPixelSize(R.styleable.TrendGraph_lineWidth, 3);
		mTextPaint.setTextSize(array.getDimensionPixelSize(R.styleable.TrendGraph_textSize, 35));
		mTextPaint.setColor(array.getColor(R.styleable.TrendGraph_textColor, Color.BLACK));
		array.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mElements == null || mElements.size() == 0) {
			return;
		}
		double max_up = getMaxUp();
		double min_down = getMinDown();
		canvas.setDrawFilter(mDrawFilter);
		mPaint.setStrokeWidth(lineWeith);
		float width = getWidth();
		float grap = width / mElements.size();
		float textSize = mTextPaint.getTextSize();
		int textMargin = circleRadius * 2;
		float margin_top = textSize + 2 * textMargin;
		Log.d(TAG, "onDraw: " + margin_top + "|" + textSize);
		float height = getHeight() - 2 * margin_top;

		for (int i = 0; i < mElements.size() - 1; i++) {
			float startX = i * grap + grap / 2;
			float stopX = (i + 1) * grap + grap / 2;
			float startY = (float) (max_up - mElements.get(i).getUp()) / (float) (max_up -
					min_down) * height + margin_top;
			float stopY = (float) (max_up - mElements.get(i + 1).getUp()) / (float) (max_up -
					min_down) * height + margin_top;

			canvas.drawText((int) mElements.get(i).getUp() + "℃", startX - textSize, startY -
					textMargin, mTextPaint);
			canvas.drawCircle(startX, startY, circleRadius, mPaint);
			canvas.drawLine(startX, startY, stopX, stopY, mPaint);
			if (i == mElements.size() - 2) {
				canvas.drawText((int) mElements.get(i + 1).getUp() + "℃", stopX - textSize, stopY
						- textMargin, mTextPaint);
				canvas.drawCircle(stopX, stopY, circleRadius, mPaint);
			}

			startY = (float) (max_up - mElements.get(i).getDown()) / (float) (max_up - min_down) *
					height + margin_top;
			stopY = (float) (max_up - mElements.get(i + 1).getDown()) / (float) (max_up -
					min_down) * height + margin_top;
			canvas.drawText((int) mElements.get(i).getDown() + "℃", startX - textSize, startY +
					textSize + textMargin, mTextPaint);
			canvas.drawCircle(startX, startY, circleRadius, mPaint);
			canvas.drawLine(startX, startY, stopX, stopY, mPaint);
			if (i == mElements.size() - 2) {
				canvas.drawText((int) mElements.get(i + 1).getDown() + "℃", stopX - textSize,
						stopY + textSize + textMargin, mTextPaint);
				canvas.drawCircle(stopX, stopY, circleRadius, mPaint);
			}
		}
	}

	public void setLineWeith(int lineWeith) {
		this.lineWeith = lineWeith;
	}

	public void setCircleRadius(int radius) {
		circleRadius = radius;
	}

	public void setTextColor(int color) {
		mTextPaint.setColor(color);
	}

	public void setTextSize(float textSize) {
		mTextPaint.setTextSize(textSize);
	}

	public void notifyDataSetChange() {
		postInvalidate();
	}

	private double getMaxUp() {
		double max = Double.MIN_VALUE;
		for (Element e : mElements) {
			if (e.getUp() > max) {
				max = e.getUp();
			}
		}
		return max;
	}

	private double getMinDown() {
		double min = Double.MAX_VALUE;
		for (Element e : mElements) {
			if (e.getDown() < min) {
				min = e.getDown();
			}
		}
		return min;
	}

	public void setOnItemClickListener(onItemClickListener onItemClickListener) {
		mOnItemClickListener = onItemClickListener;
	}

	public ArrayList<Element> getElements() {
		return mElements;
	}

	public void setElements(ArrayList<Element> elements) {
		mElements = elements;
	}


	public static class Element {
		private double up;
		private double down;

		public Element(double up, double down) {
			this.up = up;
			this.down = down;
		}

		public double getUp() {
			return up;
		}

		public void setUp(double up) {
			this.up = up;
		}

		public double getDown() {
			return down;
		}

		public void setDown(double down) {
			this.down = down;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int viewWidth = getWidth();
		int itemWidth = viewWidth / mElements.size();
		int viewHeight = getHeight();
		boolean isMove = false;

		switch (event.getAction()) {
			case MotionEvent.ACTION_MOVE:
				isMove = true;
				break;
			case MotionEvent.ACTION_UP:
				if (!isMove){
					int position = (int) (event.getX() / itemWidth);
					mOnItemClickListener.onItemClick(this, mElements.get(position));
				}
				break;
		}

		return true;
	}

	public interface onItemClickListener{
		void onItemClick(View view, Element element);
	}
}
