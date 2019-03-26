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
import ycy.ccyy.yueyavillage.bean.MyWorkBean;

public class MyWorksAdapter extends RecyclerView.Adapter<MyWorksAdapter.MyWorksHolder> {
    private List<MyWorkBean> list;
    private Context context;

    public MyWorksAdapter(Context context, List<MyWorkBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyWorksAdapter.MyWorksHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_my_works, parent, false);
        MyWorksHolder holder = new MyWorksHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyWorksAdapter.MyWorksHolder holder, int position) {
        MyWorkBean bean = list.get(position);
        holder.svMyWorkIcon.setImageURI(Uri.parse(bean.icon));
        holder.tvMyWorkTitle.setText(bean.title);
        holder.tvMyWorkDate.setText(bean.date);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyWorksHolder extends RecyclerView.ViewHolder {
        public TextView tvMyWorkTitle;
        public TextView tvMyWorkDate;
        public SimpleDraweeView svMyWorkIcon;

        public MyWorksHolder(View itemView) {
            super(itemView);
            tvMyWorkTitle = itemView.findViewById(R.id.tv_my_work_title);
            tvMyWorkDate = itemView.findViewById(R.id.tv_my_work_date);
            svMyWorkIcon = itemView.findViewById(R.id.sv_my_work_icon);
        }
    }
}
