package ycy.ccyy.yueyavillage.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import ycy.ccyy.yueyavillage.R;

public class TakePhotoManager {

    private PopupWindow mPopupWindow;
    private Button btnOpenCamera, btnOpenAlbum, btnCancel;
    private OnPopupItemClickListener listener;

    public TakePhotoManager(final Activity context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_popup_take_photo, null, false);
        btnOpenCamera = view.findViewById(R.id.btn_open_camera);
        btnOpenAlbum = view.findViewById(R.id.btn_open_album);
        btnCancel = view.findViewById(R.id.btn_cancel);
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.anim_popup);
        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCameraClick();
                }
                dismiss();
            }
        });
        btnOpenAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAlbumClick();
                }
                dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                bgAlpha(context, 1f);
            }
        });
    }

    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    private void bgAlpha(Activity activity, float f) {
        WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
        layoutParams.alpha = f;
        activity.getWindow().setAttributes(layoutParams);
    }

    public void setOnPopupItemClickListener(OnPopupItemClickListener listener) {
        this.listener = listener;
    }

    public void showWindows(Activity activity, View alignView) {
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            bgAlpha(activity, 0.25f);
            mPopupWindow.showAtLocation(alignView, Gravity.BOTTOM, 0, 0);
        }
    }

    public interface OnPopupItemClickListener {
        void onCameraClick();

        void onAlbumClick();
    }
}
