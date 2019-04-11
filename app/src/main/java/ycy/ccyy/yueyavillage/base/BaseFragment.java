package ycy.ccyy.yueyavillage.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getResourceId(), container, false);
        initControll(view);
        initObserable();
        return view;
    }

    protected abstract int getResourceId();

    protected abstract void initControll(View view);

    protected abstract void initObserable();
}
