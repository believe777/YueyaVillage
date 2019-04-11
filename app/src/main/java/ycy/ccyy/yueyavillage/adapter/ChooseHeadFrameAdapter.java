package ycy.ccyy.yueyavillage.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import ycy.ccyy.yueyavillage.R;

public class ChooseHeadFrameAdapter extends RecyclerView.Adapter<ChooseHeadFrameAdapter.ChooseHeadFrameHolder> {
    private Context context;
    private List<BorderInfoBean> list;
    private OnItemClickListener listener;

    public ChooseHeadFrameAdapter(Context context, List<BorderInfoBean> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ChooseHeadFrameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_choose_head_item, parent, false);
        ChooseHeadFrameHolder holder = new ChooseHeadFrameHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ChooseHeadFrameHolder holder, int position) {
        BorderInfoBean bean = list.get(position);
        holder.ivBorderIcon.setImageURI(Uri.parse(bean.borderResource));
        holder.tvBorderUseCount.setText(String.format("已有%s人使用", bean.useCount));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && holder.getAdapterPosition() > -1) {
                    listener.onItemClick(list.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ChooseHeadFrameHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView ivBorderIcon;
        private TextView tvBorderUseCount;

        public ChooseHeadFrameHolder(View itemView) {
            super(itemView);
            ivBorderIcon = itemView.findViewById(R.id.iv_border_icon);
            tvBorderUseCount = itemView.findViewById(R.id.tv_border_use_count);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(BorderInfoBean borderInfoBean);
    }
}
