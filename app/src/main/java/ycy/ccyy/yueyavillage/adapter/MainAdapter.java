package ycy.ccyy.yueyavillage.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.bean.PageInfoBean;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {
    private Context context;
    private List<PageInfoBean> list;
    private OnItemClickListener listener;

    public MainAdapter(Context context, List<PageInfoBean> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_main, parent, false);
        MainHolder holder = new MainHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MainHolder holder, int position) {
        PageInfoBean bean = list.get(position);
        holder.svPageIcon.setImageURI(Uri.parse(bean.imageResource));
        holder.tvPageName.setText(bean.pageName);
        if (bean.canUse) {
            holder.tvPageUseCount.setText(String.format("%s人正在使用", bean.pageUse));
        } else {
            holder.tvPageUseCount.setText("正在施工");
        }
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

    public class MainHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView svPageIcon;
        private TextView tvPageName;
        private TextView tvPageUseCount;

        public MainHolder(View itemView) {
            super(itemView);
            svPageIcon = itemView.findViewById(R.id.sv_main_page_icon);
            tvPageName = itemView.findViewById(R.id.tv_main_page_name);
            tvPageUseCount = itemView.findViewById(R.id.tv_main_page_use_count);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(PageInfoBean pageInfoBean);
    }
}
