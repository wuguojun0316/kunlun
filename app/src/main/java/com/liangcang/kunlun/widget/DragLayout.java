package com.liangcang.kunlun.widget;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * TODO: document your custom view class.
 */
public class DragLayout extends LinearLayout
{
    private ViewDragHelper mDrager;
    private ViewDragHelper.Callback callback;

    private ImageView iv1;
    private ImageView iv2;

    public DragLayout(Context context)
    {
        super(context);
    }

    public DragLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        callback = new DragCallback();
        mDrager = ViewDragHelper.create(this, 1.0f, callback);
        mDrager.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    class DragCallback extends ViewDragHelper.Callback
    {
        @Override
        public boolean tryCaptureView(View view, int pointerid)
        {
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx)
        {
            int leftPadding  = getPaddingLeft();
            int rightPadding = getPaddingRight();
            int rightBound = getWidth() - child.getWidth() - rightPadding;

            return Math.min(Math.max(left, leftPadding), rightBound);
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy)
        {
            int topPadding = getPaddingTop();
            int bottomPadding = getPaddingBottom();
            int bottomBound = getHeight() - child.getHeight() - bottomPadding;

            return Math.min(Math.max(top, topPadding), bottomBound);
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId)
        {
            super.onEdgeTouched(edgeFlags, pointerId);
            Toast.makeText(getContext(), "EdgetTouched", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId)
        {
            // mDrager.captureChildView(edgeFlags, pointerId);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        return mDrager.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        mDrager.processTouchEvent(event);
        return true;
    }
}
