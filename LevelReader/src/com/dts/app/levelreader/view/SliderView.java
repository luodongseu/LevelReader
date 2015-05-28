package com.dts.app.levelreader.view;

import com.dts.app.levelreader.R;
import com.dts.app.levelreader.utils.PixelUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/***
 * Slider-Bar
 * 
 * @author LD
 * @date 2015-5-20
 */
public class SliderView extends View {
	/** define a global sliderChangeListener **/
	private OnSliderChangedListener sliderChangedListener;
	/** define a global slider choice array **/
	public static String[] slid = { "0", "1", "2", "3", "4", "5", "6" };
	/** define a global slider choice point **/
	private int slider_choice = -1;
	/** define a global slider Paint **/
	private Paint paint = new Paint();
	/** window height **/
	private int win_height;
	/** define a global TextView **/
	private TextView mTextDialog;

	/**
	 * @TODO initialize the dialog
	 * 
	 * @param mTextDialog
	 **/
	public void setTextView(TextView mTextDialog) {
		this.mTextDialog = mTextDialog;
	}

	public SliderView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SliderView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SliderView(Context context) {
		super(context);
	}

	/***
	 * Override super function onDraw()
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		/** initialize win_height **/
		win_height = getHeight();
		/** define slider height **/
		int height = win_height * 2 / 3;
		/** initialize view width **/
		int width = getWidth();
		/** calculate a single text height **/
		int singleHeight = height / slid.length;

		for (int i = 0; i < slid.length; i++) {
			/** set paint type */
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			/** set paint antiAlias */
			paint.setAntiAlias(true);

			if (i == slider_choice) {
				/** high light the selected **/
				paint.setColor(Color.parseColor("#3399ff"));
				/** set paint text size */
				paint.setTextSize(PixelUtil.sp2px(30));
				paint.setFakeBoldText(true);
			} else {
				/** set paint color */
				paint.setColor(getResources().getColor(R.color.slider_text));
				/** set paint text size */
				paint.setTextSize(PixelUtil.sp2px(15));
			}

			/** draw a text in the middle of window **/
			float xPos = width / 2 - paint.measureText(slid[i]) / 2;
			float yPos = singleHeight * i + singleHeight / 2 + win_height / 6;
			canvas.drawText(slid[i], xPos, yPos, paint);
			paint.reset();
		}

	}

	/**
	 * @TODO override onTouch event
	 * @param event
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		/** get user action **/
		final int action = event.getAction();
		/** get position Y **/
		final float pY = event.getY();
		/** last choice **/
		final int oldChoose = slider_choice;
		/** figure new choose **/
		final int mChoice = (int) (3 * (pY - win_height / 6) / (2 * win_height) * slid.length);

		switch (action) {
		case MotionEvent.ACTION_UP:
			invalidate();
			if (mTextDialog != null) {
				try {
					/** let thread sleep 0.2s ***/
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mTextDialog.setVisibility(View.INVISIBLE);
			}
			break;
		default:
			if (oldChoose != mChoice) {
				if (mChoice >= 0 && mChoice < slid.length) {
					if (mTextDialog != null) {
						mTextDialog.setText(slid[mChoice]);
						mTextDialog.setVisibility(View.VISIBLE);
					}
					if (sliderChangedListener != null) {
						sliderChangedListener
								.onSliderChangedListener(slid[mChoice]);
					}
					slider_choice = mChoice;
					/** refresh view **/
					invalidate();
				}
			}
			break;
		}
		return true;
	}

	/**
	 * define a function to set OnSliderChangedListener
	 * 
	 * @param onSliderChangedListener
	 */
	public void setOnSliderChangedListener(
			OnSliderChangedListener onSliderChangedListener) {
		this.sliderChangedListener = onSliderChangedListener;
	}

	/**
	 * define a interface for slider change
	 * 
	 */
	public interface OnSliderChangedListener {
		public void onSliderChangedListener(String s);
	}

}
