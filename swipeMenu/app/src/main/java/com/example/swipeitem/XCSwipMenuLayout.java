package com.example.swipeitem;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by IQB on 2018/4/4.
 */

public class XCSwipMenuLayout extends ViewGroup {
    private static final String TAG = XCSwipMenuLayout.class.getSimpleName();

    XCSwipMenuLayoutListener xcSwipMenuLayoutListener;
    private XCMenuView menuView;

    public XCSwipMenuLayout(Context context) {
        super(context);
    }

    public XCSwipMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XCSwipMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 手指滑动的距离  > 0 左向右滑动
     * < 0 右向左滑动
     */
    int toLeftSlipX;

    public void setXCSwipMenuLayoutListener(XCSwipMenuLayoutListener myViewGroupListener) {
        this.xcSwipMenuLayoutListener = myViewGroupListener;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, "onLayout() called with: changed = [" + changed + "], l = [" + l + "], t = [" + t + "], r = [" + r + "], b = [" + b + "]");
        Log.i(TAG, "onLayout: getWidth= " + getWidth() + "  getHeight=" + getHeight());
        View childAt1 = getChildAt(0);
        if (getChildAt(1) instanceof XCMenuView) {
            menuView = (XCMenuView) getChildAt(1);
        } else {
            try {
                throw new Exception("MyViewGroup child at 1 must be MenuView or subClass");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int childOneLeft = toLeftSlipX > 0 ? -toLeftSlipX : 0;
        int childOneRight = r - childOneLeft;
        childAt1.layout(childOneLeft, 0, childOneRight, b);
        int layoutMenudiff;
        if (menuIsOpen) {
            layoutMenudiff = toLeftSlipX + menuWidth;
        } else {
            layoutMenudiff = toLeftSlipX;
        }
        menuView.layout(r - layoutMenudiff, t, r, b);
    }

    float downX;
    float downY;

    int menuWidth = 200;

    public int getMenuWidth() {
        return menuWidth;
    }

    public void setMenuWidth(int menuWidth) {
        this.menuWidth = menuWidth;
    }

    boolean menuIsOpen;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean intercept = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                intercept = true;
                break;

            case MotionEvent.ACTION_MOVE:
                if (Math.abs((int) (event.getX() - downX)) > 10) {
                    isSlip = true;
                }
                toLeftSlipX = (int) (downX - event.getX());
                if (Math.abs(downY - event.getY()) > Math.abs(downX - event.getX())) {
                    intercept = false;
                } else {
                    if (toLeftSlipX > 0) {
                        intercept = true;
                    } else {
                        intercept = false;
                    }
                }
                if (xcSwipMenuLayoutListener != null) {
                    xcSwipMenuLayoutListener.onSliding(toLeftSlipX);
                }
                if (isSlip) {
                    requestLayout();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // onclick
                if (Math.abs(downX - event.getX()) < 10 &&
                        Math.abs(downY - event.getY()) < 10) {
                    if (menuIsOpen) {
                        if (getWidth() - event.getX() < menuWidth) {
                            if (xcSwipMenuLayoutListener != null) {
                                xcSwipMenuLayoutListener.onClickMenu();
                            }
                        } else {
                            if (xcSwipMenuLayoutListener != null) {
                                xcSwipMenuLayoutListener.onClick();
                            }
                        }
                    }else {
                        if (xcSwipMenuLayoutListener != null) {
                            xcSwipMenuLayoutListener.onClick();
                        }
                    }
                }
                // onslip
                if (isSlip) {
                    if (menuIsOpen) {
                        if (toLeftSlipX > 0) {
                            menuIsOpen = true;
                        } else {
                            menuIsOpen = false;
                        }
                    } else {
                        if (toLeftSlipX >= menuWidth) {
                            menuIsOpen = true;
                            toLeftSlipX = menuWidth;
                        } else {
                            menuIsOpen = false;
                        }
                    }
                }

                downX = 0;
                downY = 0;
                isSlip = false;
                toLeftSlipX = 0;
                requestLayout();
                break;
        }
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(intercept);
        }
        return true;
    }

    boolean isSlip = false;

    public void closeMenu() {
        toLeftSlipX = 0;
        menuIsOpen = false;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure() called with: widthMeasureSpec = [" + widthMeasureSpec + "], heightMeasureSpec = [" + heightMeasureSpec + "]");
    }
}
