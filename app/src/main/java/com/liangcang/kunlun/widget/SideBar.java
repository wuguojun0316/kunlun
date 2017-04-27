package com.liangcang.kunlun.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Wuguojun on 16/10/27.
 */
public class SideBar extends View
{
    // 字母集合
    public String[] b = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#" };

    private int choose = -1;     // 选中
    private Paint paint = new Paint();

    private TextView mTextDialog;

    public SideBar(Context context)
    {
        super(context);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 为SideBar设置显示字母的TextView
     * @param mTextDialog
     */
    public void setTextView(TextView mTextDialog)
    {
        this.mTextDialog = mTextDialog;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        // 获取焦点,改变背景颜色
        int height = getHeight();                 // 获取对应高度
        int width = getWidth();                   // 获取对应宽度
        int singleHeight = height / b.length;     // 计算每个字母的高度

        for(int i = 0; i < b.length; i++)
        {
            paint.setColor(Color.rgb(33,65,98));
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true);
            paint.setTextSize(20);

            // 如果选中字母
            if(i == choose)
            {
                paint.setColor(Color.parseColor("#3399ff"));
                paint.setFakeBoldText(true);
            }

            // X坐标等于中间字符串宽度一半
            float xPos = width / 2 - paint.measureText(b[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(b[i], xPos, yPos, paint);
            paint.reset();
        }
    }


}
