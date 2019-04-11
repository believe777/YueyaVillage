package ycy.ccyy.yueyavillage.widget;

import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import ycy.ccyy.yueyavillage.R;

public class BaseHomeTitle {
    private View mView;

    public BaseHomeTitle(View view) {
        mView = view;
    }

    public BaseHomeTitle(View view, String title) {
        setTitle(view, title);
    }

    public BaseHomeTitle(View view, String title, View leftView, View rightView) {
        if (leftView != null && leftView.getParent() == null) {
            ViewStub leftContent = view.findViewById(R.id.title_left_content);
            ((LinearLayout) leftContent.inflate()).addView(leftView);
        }
        setTitle(view, title);
        if (rightView != null && rightView.getParent() == null) {
            ViewStub rightContent = view.findViewById(R.id.title_right_content);
            ((LinearLayout) rightContent.inflate()).addView(rightView);
        }
    }

    public void setTitle(View view, String title) {
        ViewStub middleContent = view.findViewById(R.id.title_middle_content);
        View showView = middleContent.inflate();
        TextView titleContent = showView.findViewById(R.id.tv_middle_title);
        titleContent.setText(title);
    }

    public void setTitle(String title) {
        ViewStub middleContent = mView.findViewById(R.id.title_middle_content);
        View showView = middleContent.inflate();
        TextView titleContent = showView.findViewById(R.id.tv_middle_title);
        titleContent.setText(title);
    }

    public void setTitle(View view) {
        if (view != null && view.getParent() == null) {
            ViewStub middleContent = mView.findViewById(R.id.title_middle_content);
            LinearLayout leftTitle = (LinearLayout) middleContent.inflate();
            leftTitle.findViewById(R.id.tv_middle_title).setVisibility(View.GONE);
            leftTitle.addView(view);
        }
    }

    public void setRight(View view) {
        setRight(view, null);
    }

    public void setRight(View view, View.OnClickListener listener) {
        if (view != null && view.getParent() == null) {
            ViewStub rightContent = mView.findViewById(R.id.title_right_content);
            LinearLayout layout = (LinearLayout) rightContent.inflate();
            layout.addView(view);
            layout.setOnClickListener(listener);
        }
    }
}
