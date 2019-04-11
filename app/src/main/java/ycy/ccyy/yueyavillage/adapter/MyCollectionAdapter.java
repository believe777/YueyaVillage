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
import ycy.ccyy.yueyavillage.bean.CollectionBean;

public class MyCollectionAdapter extends RecyclerView.Adapter<MyCollectionAdapter.MyCollectionHolder> {
    private List<CollectionBean> list;
    private Context context;
    private OnCollectionItemClickListener listener;

    public MyCollectionAdapter(List<CollectionBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setOnCollectionItemClickListener(OnCollectionItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MyCollectionAdapter.MyCollectionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_my_collection, parent, false);
        MyCollectionHolder holder = new MyCollectionHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyCollectionAdapter.MyCollectionHolder holder, int position) {
        CollectionBean bean = list.get(position);
        holder.tvDate.setText(bean.date);
        holder.tvPublisherName.setText(bean.publisherName);
        holder.tvTitle.setText(bean.title);
        holder.svPublishIcon.setImageURI(Uri.parse(bean.publisherIcon));
        holder.svIcon.setImageURI(Uri.parse(bean.icon));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(list.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyCollectionHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView svIcon, svPublishIcon;
        private TextView tvTitle, tvPublisherName, tvDate;

        public MyCollectionHolder(View itemView) {
            super(itemView);
            svIcon = itemView.findViewById(R.id.sv_my_collection_icon);
            svPublishIcon = itemView.findViewById(R.id.sv_my_collection_publisher_icon);
            tvTitle = itemView.findViewById(R.id.tv_my_collection_title);
            tvPublisherName = itemView.findViewById(R.id.sv_my_collection_publisher_name);
            tvDate = itemView.findViewById(R.id.tv_my_collection_date);
        }
    }

    public interface OnCollectionItemClickListener {
        void onClick(CollectionBean bean);
    }
}
