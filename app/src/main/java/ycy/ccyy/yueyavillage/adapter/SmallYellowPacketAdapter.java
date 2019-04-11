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
import ycy.ccyy.yueyavillage.bean.SmallYellowPacketBean;

public class SmallYellowPacketAdapter extends RecyclerView.Adapter<SmallYellowPacketAdapter.SmallYellowPacketHolder> {
    public List<SmallYellowPacketBean> list;
    public Context context;

    public SmallYellowPacketAdapter(Context context, List<SmallYellowPacketBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SmallYellowPacketAdapter.SmallYellowPacketHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_small_yellow_packet, parent, false);
        SmallYellowPacketHolder holder = new SmallYellowPacketHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SmallYellowPacketAdapter.SmallYellowPacketHolder holder, int position) {
        SmallYellowPacketBean bean = list.get(position);
        if (bean.collection == 0) {
            holder.ivCollection.setVisibility(View.VISIBLE);
            holder.ivUnCollection.setVisibility(View.GONE);
        } else if (bean.collection == 1) {
            holder.ivCollection.setVisibility(View.GONE);
            holder.ivUnCollection.setVisibility(View.VISIBLE);
        }
        holder.tvTitle.setText(bean.title);
        holder.tvDate.setText(bean.date);
        holder.svIcon.setImageURI(Uri.parse(bean.icon));
        holder.tvPublishName.setText(bean.publishName);
        holder.svPublishIcon.setImageURI(Uri.parse(bean.publishIcon));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class SmallYellowPacketHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView svIcon, svPublishIcon;
        public TextView tvTitle, tvPublishName, tvDate;
        public ImageView ivCollection, ivUnCollection;

        public SmallYellowPacketHolder(View itemView) {
            super(itemView);
            svIcon = itemView.findViewById(R.id.sv_small_yellow_packet_icon);
            svPublishIcon = itemView.findViewById(R.id.sv_small_yellow_packet_publishicon);
            tvDate = itemView.findViewById(R.id.tv_small_yellow_packet_date);
            tvPublishName = itemView.findViewById(R.id.tv_small_yellow_packet_publishname);
            tvTitle = itemView.findViewById(R.id.tv_small_yellow_packet_title);
            ivCollection = itemView.findViewById(R.id.iv_small_yellow_packet_collection);
            ivUnCollection = itemView.findViewById(R.id.iv_small_yellow_packet_uncollection);
        }
    }
}
