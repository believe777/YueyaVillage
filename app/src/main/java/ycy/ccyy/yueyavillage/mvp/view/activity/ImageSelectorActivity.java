package ycy.ccyy.yueyavillage.mvp.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.adapter.ImageSelectorAdapter;
import ycy.ccyy.yueyavillage.base.MvpActivity;
import ycy.ccyy.yueyavillage.bean.ImageSelectorBean;
import ycy.ccyy.yueyavillage.mvp.contract.ImageSelectorContract;
import ycy.ccyy.yueyavillage.mvp.presenter.ImageSelectorPresenter;
import ycy.ccyy.yueyavillage.widget.BaseTitle;

public class ImageSelectorActivity extends MvpActivity<ImageSelectorPresenter> implements ImageSelectorContract.View {
    private TextView finishText;
    private ImageView ivDisplay;
    private RecyclerView rvList;
    private ImageSelectorAdapter adapter;

    @Override
    protected int getResourceId() {
        return R.layout.activity_image_selector;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initControll() {
        initTitle();
        ivDisplay = findViewById(R.id.iv_image_selector_display);
        rvList = findViewById(R.id.rv_image_selector_list);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        rvList.setLayoutManager(layoutManager);
        mPresenter.getLocalImages();
    }

    @Override
    protected void initObserable() {

    }

    @Override
    public void setFinishTextStatus(int selectedNumber) {
        if (selectedNumber > 0) {
            finishText.setTextColor(Color.parseColor("#741DE4"));
            finishText.setEnabled(true);
        } else {
            finishText.setEnabled(false);
            finishText.setTextColor(Color.parseColor("#615F6A"));
        }
        finishText.setText(String.format(getResources().getString(R.string.image_selector_finish), String.valueOf(selectedNumber)));
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void previewImage(Uri imagePath) {
        ivDisplay.setImageURI(imagePath);
    }

    private void initTitle() {
        final ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.publish_close);
        finishText = new TextView(this);
        setFinishTextStatus(0);
        new BaseTitle(this, "相机胶卷", imageView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, finishText, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("data", mPresenter.selectedImagesPath());
                it.putExtras(bundle);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }


    @Override
    public void getLocalImages(List<ImageSelectorBean> imagesPath) {
        adapter = new ImageSelectorAdapter(imagesPath, this);
        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        rvList.setAdapter(adapter);
        adapter.setOnImageSelectedListener(new ImageSelectorAdapter.OnImageSelectedListener() {
            @Override
            public void onImageSelected(int position) {
                mPresenter.onImageSelected(position);
            }

            @Override
            public void onImagePreview(int position) {
                mPresenter.onImagePreview(position);
            }
        });
    }
}
