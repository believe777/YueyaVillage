package ycy.ccyy.yueyavillage.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

//首页viewpager禁止左右滑动
public class HomeViewPager extends ViewPager {
    public HomeViewPager(Context context) {
        super(context);
        init();
    }

    public HomeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private int minScroll;
    private float lastTouchX;
    private boolean isDragger;

    private void init() {
        minScroll = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDragger = super.onInterceptTouchEvent(ev);
                lastTouchX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(lastTouchX - ev.getX()) > minScroll) {
                    isDragger = true;
                }
                break;
        }
        return isDragger;
    }

    // TODO: 2019/3/18 后续有非左右滑动事件是再修改
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
