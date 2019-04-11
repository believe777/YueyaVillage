package ycy.ccyy.yueyavillage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PublishAdapter extends RecyclerView.Adapter {
    private List<View> imageList;
    private Context context;

    public PublishAdapter(Context context, List<View> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return imageList == null ? 1 : imageList.size() + 1;
    }
}
