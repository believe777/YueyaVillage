package ycy.ccyy.yueyavillage.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class PromisePoolPager extends ViewPager {
    public PromisePoolPager(Context context) {
        super(context);
    }

    public PromisePoolPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private float downX;

    //禁止右滑
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (downX < ev.getX()) {
                    return true;
                }
                downX = ev.getX();
                break;
        }
        return super.onTouchEvent(ev);
    }
}
