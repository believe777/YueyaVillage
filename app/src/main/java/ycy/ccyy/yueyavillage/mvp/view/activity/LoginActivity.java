package ycy.ccyy.yueyavillage.mvp.view.activity;

import android.content.Intent;
import android.view.View;

import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.base.MvpActivity;
import ycy.ccyy.yueyavillage.mvp.contract.LoginContract;
import ycy.ccyy.yueyavillage.mvp.presenter.LoginPresenter;
import ycy.ccyy.yueyavillage.util.KeysUtil;

/**
 * 微信登录流程
 * ①初次登录：  用户授权获取code --> 通过code获取access_token，并缓存 --> 通过access_token获取用户信息
 * ②非初次登录：检查缓存access_token是否有效 --> 无效则通过refresh_token刷新access_token --> 通过access_token获取用户信息
 */
//登录测试
public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener {
    @Override
    protected int getResourceId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initControll() {

    }

    @Override
    protected void initObserable() {
        findViewById(R.id.btn_login_wx).setOnClickListener(this);
        findViewById(R.id.btn_login_qq).setOnClickListener(this);
    }

    //登录失败
    @Override
    public void onLoginFailed(String errorMsg) {
        showToast(errorMsg);
    }

    @Override
    public void pleaseInstallQQ() {
        showToast("请先安装QQ");
    }

    @Override
    public void qqLogin() {
        showToast("登录成功");
        jumpToHomePage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_qq:
                mPresenter.qqLogin(this);
                break;
            case R.id.btn_login_wx:
                showToast("暂不支持微信登录");
                //图片上传
//                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "ycy_login.png");
//                RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
//                NetControl.getInstance().getApi().upload(requestBody)
//                        .compose(RxUtil.<ResponseBase>observerToMainScheduler())
//                        .subscribe(new Observer<ResponseBase>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(ResponseBase responseBase) {
//                                LogUtil.d("upload " + responseBase.resultCode + "--" + responseBase.error);
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                e.printStackTrace();
//                                LogUtil.d("upload failed");
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN || requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mPresenter.getQQLoginListener());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void jumpToHomePage() {
        Intent it = new Intent(this, ChooseHeadFrameActivity.class);
        //Intent it = new Intent(this, HomeActivity.class);
        startActivity(it);
        finish();
    }

}
