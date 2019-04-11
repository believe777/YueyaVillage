package ycy.ccyy.yueyavillage.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.util.ImageUtil;
import ycy.ccyy.yueyavillage.util.LogUtil;

public class ImagePicker extends FrameLayout {
    private static final int SPACING = 20;
    private static final int DEFAULT_ITEM = 4;
    private int line = 1;
    private int avgWidth;
    private OnAddImageClickListener listener;

    public ImagePicker(@NonNull Context context) {
        super(context);
        init();
    }

    public ImagePicker(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImagePicker(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ImagePicker(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        View imageLayout = LayoutInflater.from(getContext()).inflate(R.layout.layout_image_add, this, false);
        imageLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick();
                }
            }
        });
        addView(imageLayout);
    }

    public void setOnAddImageClickListener(OnAddImageClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            int leftSpacing = SPACING;
            int topSpacing = 0;
            if (i % DEFAULT_ITEM == 0) {
                line = i / DEFAULT_ITEM + 1;
                leftSpacing = 0;
            }
            if (line > 1) {
                topSpacing = SPACING;
            }
            int l = i % DEFAULT_ITEM * avgWidth + leftSpacing * (i % DEFAULT_ITEM);
            int t = avgWidth * (line - 1) + topSpacing;
            int r = (i % DEFAULT_ITEM + 1) * avgWidth + leftSpacing * (i % DEFAULT_ITEM);
            int b = avgWidth * line;
            childView.layout(l, t, r, b);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        avgWidth = (width - getPaddingLeft() - getPaddingRight() - SPACING * (DEFAULT_ITEM - 1)) / DEFAULT_ITEM;
        for (int i = 0; i < getChildCount(); i++) {
            measureChild(getChildAt(i), avgWidth, avgWidth);
        }
        int baseLine = getChildCount() / DEFAULT_ITEM + 1;
        setMeasuredDimension(width, avgWidth * baseLine + SPACING * (baseLine - 1));
    }

    public void addImage(String url) {
        addImage(ImageUtil.loadLocalImage(url));
    }

    public void addImage(Uri uri) {
        addImage(ImageUtil.loadLocalImage(getContext(), uri));
    }

    public void addImage(Bitmap bitmap) {
        View imageLayout = LayoutInflater.from(getContext()).inflate(R.layout.layout_picker_image, this, false);
        imageLayout.setLayoutParams(new RelativeLayout.LayoutParams(avgWidth, avgWidth));
        ImageView imageView = imageLayout.findViewById(R.id.iv_picker_image);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(avgWidth, avgWidth));
        imageView.setImageBitmap(bitmap);
        ImageView removeImage = imageLayout.findViewById(R.id.iv_remove_image);
        removeImage.setTag(imageLayout);
        removeImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.this.removeView((View) v.getParent());
            }
        });
        addView(imageLayout, getChildCount() - 1);
    }

    public interface OnAddImageClickListener {
        void onClick();
    }
}
