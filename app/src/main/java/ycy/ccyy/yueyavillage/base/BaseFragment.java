package ycy.ccyy.yueyavillage.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getResourceId(), null);
        initControll(view);
        initObserable();
        return view;
    }

    abstract int getResourceId();

    abstract void initControll(View view);

    abstract void initObserable();
}
