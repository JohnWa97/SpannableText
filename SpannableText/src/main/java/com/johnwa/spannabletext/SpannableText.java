package com.johnwa.spannabletext;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

/**
 * @Author: qzh
 * @Date: 2022/6/9
 * @Description:
 */
public class SpannableText {

    private SpannableStringBuilder stringBuilder;
    private ITextListener mListener;
    private Context mContext;
    private boolean mUnderLine;
    private int mColor;

    /**
     * 构造函数初始化默认选项
     *
     * @param context 上下文
     * @param listener 点击监听
     * */
    public SpannableText(Context context, ITextListener listener){
        stringBuilder = new SpannableStringBuilder();
        mContext = context;
        mListener = listener;
        mUnderLine = false;
        mColor = R.color.blue;
    }

    /**
     * 设置参数
     *
     * @param textContent 文字内容
     * @param target1 目标字符
     * @param target2 目标字符
     * @param url1 目标字符1的链接
     * @param url2 目标字符2的链接
     * */
    public void setParam(String textContent, String target1, String target2, String url1, String url2){
        int start1 = textContent.indexOf(target1);
        int end1 = start1 + target1.length();
        int start2 = textContent.indexOf(target2);
        int end2 = start2 + target2.length();
        ClickTextSpan textSpan1 = new ClickTextSpan(url1);
        ClickTextSpan textSpan2 = new ClickTextSpan(url2);
        stringBuilder.append(textContent);
        stringBuilder.setSpan(textSpan1, start1, end1 , Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        stringBuilder.setSpan(textSpan2, start2, end2 , Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
    }

    /**
     * 设置目标样式
     *
     * @param color 颜色
     * @param underLine 是否显示下划线
     * */
    public void setTargetStyle(int color, boolean underLine){
        mColor = color;
        mUnderLine = underLine;
    }

    /**
     * 设置文本控件
     * */
    public void setTextView(TextView textView){
        textView.setHighlightColor(Color.TRANSPARENT);
        textView.setText(stringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }


    class ClickTextSpan extends ClickableSpan {

        private final String mUrl;

        public ClickTextSpan(String url){
            mUrl = url;
        }

        @Override
        public void onClick(@NonNull View view) {
            mListener.onClickText(mUrl);
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(mContext.getResources().getColor(mColor));
            ds.setUnderlineText(mUnderLine);
        }

    }

}
