package ycy.ccyy.yueyavillage.widget;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import ycy.ccyy.yueyavillage.R;

//通用标题栏
public class BaseTitle {
    private Activity activity;

    public BaseTitle(Activity activity) {
        this.activity = activity;
        defaltLeftContent(activity);
    }

    public BaseTitle(Activity activity, String title) {
        defaltLeftContent(activity);
        setTitle(activity, title);
    }

    public BaseTitle(Activity activity, String title, View leftView, View rightView) {
        this(activity, title, leftView, null, rightView, null);
    }

    public BaseTitle(Activity activity, String title, View leftView, View.OnClickListener leftClick, View rightView, View.OnClickListener rightClick) {
        ViewStub leftContent = activity.findViewById(R.id.title_left_content);
        LinearLayout leftBaseView = (LinearLayout) leftContent.inflate();
        if (leftView != null && leftView.getParent() == null) {
            leftBaseView.findViewById(R.id.iv_title_back).setVisibility(View.GONE);
            leftBaseView.addView(leftView);
        }
        leftBaseView.setOnClickListener(leftClick);
        if (!TextUtils.isEmpty(title)) {
            setTitle(activity, title);
        }
        ViewStub rightContent = activity.findViewById(R.id.title_right_content);
        LinearLayout rightBaseView = (LinearLayout) rightContent.inflate();
        if (rightView != null && rightView.getParent() == null) {
            rightBaseView.addView(rightView);
        }
        rightBaseView.setOnClickListener(rightClick);
    }

    private void defaltLeftContent(final Activity activity) {
        ViewStub leftContent = activity.findViewById(R.id.title_left_content);
        View showView = leftContent.inflate();
        showView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    public void setTitle(Activity activity, String title) {
        this.activity = activity;
        ViewStub middleContent = activity.findViewById(R.id.title_middle_content);
        View showView = middleContent.inflate();
        TextView titleContent = showView.findViewById(R.id.tv_middle_title);
        titleContent.setText(title);
    }

    public void setRightContent(View view) {
        ViewStub rightContent = activity.findViewById(R.id.title_right_content);
        ((LinearLayout) rightContent.inflate()).addView(view);
    }

    public void setRightContent(String text, View.OnClickListener onClickListener) {
        ViewStub rightContent = activity.findViewById(R.id.title_right_content);
        LinearLayout rightView = (LinearLayout) rightContent.inflate();
        TextView textView = new TextView(activity);
        textView.setText(text);
        textView.setTextSize(14);
        rightView.addView(textView);
        rightView.setOnClickListener(onClickListener);
    }
}
