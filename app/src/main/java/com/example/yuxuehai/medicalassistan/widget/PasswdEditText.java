package com.example.yuxuehai.medicalassistan.widget;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.yuxuehai.medicalassistan.R;

/**
 * Created by yuxuehai on 17-2-28.
 */

public class PasswdEditText extends AppCompatEditText implements
        View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {


    private Drawable mPasswdTextIcon;
    private OnFocusChangeListener mOnFocusChangeListener;
    private OnTouchListener mOnTouchListener;
    private int clickCount = 0;

    public PasswdEditText(Context context) {
        super(context);
        init(context);
    }

    public PasswdEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PasswdEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        mOnFocusChangeListener = onFocusChangeListener;
    }

    @Override
    public void setOnTouchListener(final OnTouchListener onTouchListener) {
        mOnTouchListener = onTouchListener;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        final int x = (int) motionEvent.getX();
        if (mPasswdTextIcon.isVisible() &&
                x > getWidth() - getPaddingRight() - mPasswdTextIcon.getIntrinsicWidth()) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                clickCount++;
                if (clickCount % 2 != 0) {
                    setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mPasswdTextIcon.setColorFilter(getResources().getColor(R.color.main_green), PorterDuff.Mode.SRC_IN);
                } else {
                    setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mPasswdTextIcon.setColorFilter(getResources().getColor(R.color.main_gray), PorterDuff.Mode.SRC_IN);
                }
            }
            return true;
        }
        return mOnTouchListener != null && mOnTouchListener.onTouch(view, motionEvent);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b) {
            setPasswdIconVisible(getText().length() > 0);
        } else {
            setPasswdIconVisible(false);
        }
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChange(view, b);
        }
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (isFocused()) {
            setPasswdIconVisible(text.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    private void setPasswdIconVisible(final boolean visible) {
        mPasswdTextIcon.setVisible(visible, false);
        final Drawable[] compoundDrawables = getCompoundDrawables();
        setCompoundDrawables(
                compoundDrawables[0],
                compoundDrawables[1],
                visible ? mPasswdTextIcon : null,
                compoundDrawables[3]);
    }

    private void init(final Context context) {

        final Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_eye);
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable); //Wrap the drawable so that it can be tinted pre Lollipop
        DrawableCompat.setTint(wrappedDrawable, getCurrentHintTextColor());
        mPasswdTextIcon = wrappedDrawable;
        mPasswdTextIcon.setBounds(0, 0, mPasswdTextIcon.getIntrinsicHeight(), mPasswdTextIcon.getIntrinsicHeight());
        setPasswdIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

}
