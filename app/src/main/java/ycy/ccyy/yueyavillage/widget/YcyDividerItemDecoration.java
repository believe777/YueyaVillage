package ycy.ccyy.yueyavillage.widget;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class YcyDividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int DEFAULT_SPACING_HEIGHT = 10;
    private int spacing = DEFAULT_SPACING_HEIGHT;

    public YcyDividerItemDecoration() {
    }

    public YcyDividerItemDecoration(int spacing) {
        this.spacing = spacing;
    }


    private int getSpanCount(RecyclerView parent) {
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        return spanCount;
    }

    private boolean isLastColum(int pos, int spanCount) {
        if ((pos + 1) % spanCount == 0) {
            return true;
        }
        return false;
    }

    private boolean isLastRaw(int pos, int spanCount,
                              int childCount) {
        childCount = childCount - childCount % spanCount;
        if (pos >= childCount) {
            return true;
        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        int itemPosition = parent.getChildAdapterPosition(view);
        if (isLastRaw(itemPosition, spanCount, childCount)) {
            outRect.set(0, 0, spacing, 0);
        } else if (isLastColum(itemPosition, spanCount)) {
            outRect.set(0, 0, 0, spacing);
        } else {
            outRect.set(0, 0, spacing,
                    spacing);
        }
    }
}
