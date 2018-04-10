package com.example.swipeitem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by IQB on 2018/4/8.
 */

public class XCMenuView extends View {

    private static final String TAG = "xianchao";
    private Path path;
    private Paint paint;

    public XCMenuView(Context context) {
        super(context);
        init();
    }

    public XCMenuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XCMenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthMeasureSpec = MeasureSpec.getSize(widthMeasureSpec);
        heightMeasureSpec = MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG, "onMeasure() called with: widthMeasureSpec = [" + widthMeasureSpec + "], heightMeasureSpec = [" + heightMeasureSpec + "]");
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
        Log.i(TAG, "onLayout: getwidth" + width + "getheight" + height);
    }

    int width, height;

    int menuColor;
    int menuWidth;
    public void init() {
        path = new Path();
        paint = new Paint();
        paint.setColor(Color.parseColor("#FF0000"));
    }

    public void setMenuColor(int menuColor) {
        this.menuColor = menuColor;
        paint.setColor(menuColor);
    }
    public int getMenuColor() {
        return menuColor;
    }

    public int getMenuWidth() {
        return menuWidth;
    }

    public void setMenuWidth(int menuWidth) {
        this.menuWidth = menuWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw: ");
        path.reset();
        path.moveTo(width, 0);
        path.cubicTo(width / 8 * 7, height / 2,
                width / 8, height / 4,
                0, height / 2);
        path.cubicTo(width / 8, height / 4 * 3,
                width / 8 * 7, height / 2,
                width, height);
        path.lineTo(width, 0);

        canvas.drawPath(path, paint);
    }
}
