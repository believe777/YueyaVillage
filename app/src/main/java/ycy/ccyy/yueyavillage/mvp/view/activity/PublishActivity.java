package ycy.ccyy.yueyavillage.mvp.view.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.base.MvpActivity;
import ycy.ccyy.yueyavillage.mvp.contract.PublishContract;
import ycy.ccyy.yueyavillage.mvp.presenter.PublishPresenter;
import ycy.ccyy.yueyavillage.util.CollectionUtil;
import ycy.ccyy.yueyavillage.util.PermissionsUtil;
import ycy.ccyy.yueyavillage.widget.BaseTitle;
import ycy.ccyy.yueyavillage.widget.ImagePicker;
import ycy.ccyy.yueyavillage.widget.TakePhotoManager;

public class PublishActivity extends MvpActivity<PublishPresenter> implements PublishContract.View {
    private static final int RESULT_OPEN_ALBUM = 0;
    private static final int RESULT_TAKE_PHOTO = 1;
    private static final int CLICK_TAKE_PHOTO = 2;
    private static final int CLICK_OPEN_ALBUM = 3;
    private static final int PERMISSION_REQUEST_CODE = 1000;
    private int currentClick;
    private EditText etMsg;
    private ImagePicker imagePicker;
    private TakePhotoManager takePhotoManager;
    private View publish;

    @Override
    protected int getResourceId() {
        return R.layout.activity_publish;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initControll() {
        initTitle();
        etMsg = findViewById(R.id.et_publish_msg);
        imagePicker = findViewById(R.id.ip_imagePicker);
        publish = findViewById(R.id.publish);
        takePhotoManager = new TakePhotoManager(this);
    }

    @Override
    protected void initObserable() {
        takePhotoManager.setOnPopupItemClickListener(new TakePhotoManager.OnPopupItemClickListener() {
            @Override
            public void onCameraClick() {
                currentClick = CLICK_TAKE_PHOTO;
                if (requestPremissions()) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, RESULT_TAKE_PHOTO);
                } else {
                    showToast("如需拍照请开启手机相机权限");
                }
            }

            @Override
            public void onAlbumClick() {
                currentClick = CLICK_OPEN_ALBUM;
                if (requestPremissions()) {
                    Intent intent = new Intent(PublishActivity.this, ImageSelectorActivity.class);
                    startActivityForResult(intent, RESULT_OPEN_ALBUM);
                } else {
                    showToast("如需打开相册请开启手机相册权限");
                }
            }
        });
        imagePicker.setOnAddImageClickListener(new ImagePicker.OnAddImageClickListener() {
            @Override
            public void onClick() {
                takePhotoManager.showWindows(PublishActivity.this, publish);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (requestCode == RESULT_TAKE_PHOTO) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                imagePicker.addImage(bitmap);
            } else if (requestCode == RESULT_OPEN_ALBUM && resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                List<String> imagePath = (List<String>) bundle.get("data");
                if (!CollectionUtil.isEmpty(imagePath)) {
                    for (String s : imagePath) {
                        imagePicker.addImage(s);
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            continueJump();
        }
    }

    private boolean requestPremissions() {
        if (!PermissionsUtil.checkPermission(Manifest.permission.CAMERA)) {
            PermissionsUtil.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
        } else {
            return true;
        }
        return false;
    }

    private void initTitle() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.publish_close);
        TextView publish = new TextView(this);
        publish.setText("发布");
        publish.setTextColor(Color.WHITE);
        publish.setPadding(20, 10, 20, 10);
        publish.setBackgroundColor(Color.parseColor("#741de4"));
        new BaseTitle(this, "相机胶卷", imageView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, publish, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void continueJump() {
        if (currentClick == CLICK_OPEN_ALBUM) {
            Intent intent = new Intent(PublishActivity.this, ImageSelectorActivity.class);
            startActivityForResult(intent, RESULT_OPEN_ALBUM);
        } else if (currentClick == CLICK_TAKE_PHOTO) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, RESULT_TAKE_PHOTO);
        }
    }

    @Override
    public void pushlishSuccess() {
        showToast("上传成功");
    }

    @Override
    public void publishFailed(String msg) {
        showToast("上传失败：" + msg);
    }
}
