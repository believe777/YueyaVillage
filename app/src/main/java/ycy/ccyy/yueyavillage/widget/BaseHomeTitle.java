package ycy.ccyy.yueyavillage.widget;

import android.app.Activity;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ycy.ccyy.yueyavillage.R;

public class BaseHomeTitle {

    public BaseHomeTitle(Activity activity, String title) {
        setTitle(activity, title);
    }

    public BaseHomeTitle(Activity activity, String title, View leftView, View rightView) {
        ViewStub leftContent = activity.findViewById(R.id.title_left_content);
        ((LinearLayout) leftContent.inflate()).addView(leftView);
        setTitle(activity, title);
        ViewStub rightContent = activity.findViewById(R.id.title_right_content);
        ((LinearLayout) rightContent.inflate()).addView(rightView);
    }

    public void defaltLeftContent(final Activity activity) {
        ViewStub leftContent = activity.findViewById(R.id.title_left_content);
        View showView = leftContent.inflate();
        ImageView backIcon = showView.findViewById(R.id.iv_title_back);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    public void setTitle(Activity activity, String title) {
        ViewStub middleContent = activity.findViewById(R.id.title_middle_content);
        View showView = middleContent.inflate();
        TextView titleContent = showView.findViewById(R.id.tv_middle_title);
        titleContent.setText(title);
    }
}
