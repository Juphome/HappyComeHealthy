package com.happycomehealthy.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.happycomehealthy.R;

/**
 * 自定义显示文字控件
 * creator: ZZF
 * careate date: 2018/5/17  14:19.
 */

public class TextItemVew extends RelativeLayout {

    private String left_text;
    private String right_text;
    private TextView tv_left_text;
    private TextView tv_right_text;

    public TextItemVew(Context context) {
        super(context);
        initView(context);
    }

    public TextItemVew(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypedArray(context, attrs);
        initView(context);
    }



    public TextItemVew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public TextItemVew(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }
    private void initTypedArray(Context context, AttributeSet attrs) {
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.TextItemVew);
        left_text = mTypedArray.getString(R.styleable.TextItemVew_left_text);
        right_text = mTypedArray.getString(R.styleable.TextItemVew_right_text);
        //获取资源后要及时回收
        mTypedArray.recycle();
    }
    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_text_item, this, true);
        tv_left_text = (TextView)findViewById(R.id.tv_left_text);
        tv_right_text = (TextView)findViewById(R.id.tv_right_text);

        tv_left_text.setText(left_text);
        tv_right_text.setText(right_text);
    }

    public TextView getTv_left_text() {
        return tv_left_text;
    }

    public void setTv_left_text(TextView tv_left_text) {
        this.tv_left_text = tv_left_text;
    }

    public TextView getTv_right_text() {
        return tv_right_text;
    }

    public void setTv_right_text(TextView tv_right_text) {
        this.tv_right_text = tv_right_text;
    }



}
