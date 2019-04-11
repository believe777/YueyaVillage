package ycy.ccyy.yueyavillage.adapter;

import android.support.v4.view.ViewPager;
import android.view.View;

public class PromisePoolTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        float scaleFator = Math.max(MIN_SCALE, 1 - Math.abs(position));
        float scaleX = Math.max(MIN_SCALE, 1 - Math.abs(position));
        if (position >= -1 && position <= 1) {
            float horzMargin = pageWidth * (1 - scaleFator) / 2;
            if (position < 0) {
                view.setTranslationX(horzMargin);
            } else {
                view.setTranslationX(-horzMargin);
            }
            view.setScaleY(scaleFator);
            view.setScaleX(scaleX);
        }
    }
}
