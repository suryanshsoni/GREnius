package com.tensai.grenius.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.tensai.grenius.R;

/**
 * Created by ishitabhandari on 21/06/17.
 */

public class SlideTextView extends android.support.v7.widget.AppCompatTextView {

    public SlideTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public SlideTextView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(attrs);
    }
    public SlideTextView(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SlideTextView);
            String fontName = a.getString(R.styleable.SlideTextView_font);

            if (fontName != null)
                try {
                    Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                    setTypeface(tf);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            a.recycle();

        }
    }
}
