package ycy.ccyy.yueyavillage.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ycy.ccyy.yueyavillage.R;

public class TitleSelectorView extends LinearLayout {
    public TitleSelectorView(Context context) {
        super(context);
        init();
    }

    public TitleSelectorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TitleSelectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TitleSelectorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
        setOrientation(LinearLayout.HORIZONTAL);
    }

    private OnSelectorItemClickListener listener;
    private int lastSelectedIndex = 0;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), getResources().getDimensionPixelSize(R.dimen.title_height));
    }

    public void setOnSelectorItemClickListener(OnSelectorItemClickListener listener) {
        this.listener = listener;
    }

    public void addItem(String... texts) {
        if (texts != null && getChildCount() <= 0) {
            for (int i = 0; i < texts.length; i++) {
                TextView textView = new TextView(getContext());
                textView.setText(texts[i]);
                textView.setTextSize(14);
                textView.setTag(i);
                addView(textView);
                if (i == 0) {
                    select(textView);
                } else {
                    unSelect(textView);
                }
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedIndex = (int) v.getTag();
                        if (selectedIndex == lastSelectedIndex) {
                            return;
                        }
                        select((TextView) v);
                        unSelect((TextView) getChildAt(lastSelectedIndex));
                        if (listener != null) {
                            listener.onItemClick(v, selectedIndex);
                        }
                        lastSelectedIndex = selectedIndex;
                    }
                });
            }
        }
    }

    public void onItemSelected(int position) {
        if (position == lastSelectedIndex) {
            return;
        }
        select((TextView) getChildAt(position));
        unSelect((TextView) getChildAt(lastSelectedIndex));
        lastSelectedIndex = position;
    }

    private void select(TextView textView) {
        textView.setTextColor(Color.WHITE);
        textView.setBackground(getResources().getDrawable(R.drawable.title_selector_selected));
    }

    private void unSelect(TextView textView) {
        textView.setTextColor(Color.parseColor("#615F6A"));
        textView.setBackground(getResources().getDrawable(R.drawable.title_selector_unselected));
    }

    public interface OnSelectorItemClickListener {
        void onItemClick(View view, int position);
    }
}
