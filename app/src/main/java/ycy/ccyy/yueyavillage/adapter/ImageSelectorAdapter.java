package ycy.ccyy.yueyavillage.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.bean.ImageSelectorBean;

public class ImageSelectorAdapter extends RecyclerView.Adapter<ImageSelectorAdapter.ImageSelectorHolder> {
    private List<ImageSelectorBean> imagePath;
    private Context context;
    private OnImageSelectedListener listener;

    public ImageSelectorAdapter(List<ImageSelectorBean> imagePath, Context context) {
        this.imagePath = imagePath;
        this.context = context;
    }

    public void setOnImageSelectedListener(OnImageSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public ImageSelectorAdapter.ImageSelectorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_image_selector, parent, false);
        ImageSelectorHolder holder = new ImageSelectorHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ImageSelectorAdapter.ImageSelectorHolder holder, int position) {
        ImageSelectorBean bean = imagePath.get(position);
        holder.ivItem.setImageURI(Uri.parse(bean.imagePath));
        if (bean.isSelected) {
            holder.tvIndex.setBackground(context.getResources().getDrawable(R.drawable.image_selected));
            holder.tvIndex.setText(String.valueOf(bean.index));
        } else {
            holder.tvIndex.setText("");
            holder.tvIndex.setBackground(context.getResources().getDrawable(R.drawable.image_unselected));
        }
        holder.ivItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onImagePreview(holder.getAdapterPosition());
                }
            }
        });
        holder.llChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onImageSelected(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagePath == null ? 0 : imagePath.size();
    }

    public class ImageSelectorHolder extends RecyclerView.ViewHolder {
        private ImageView ivItem;
        private TextView tvIndex;
        private LinearLayout llChoose;

        public ImageSelectorHolder(View itemView) {
            super(itemView);
            ivItem = itemView.findViewById(R.id.iv_image_selector_item);
            tvIndex = itemView.findViewById(R.id.tv_image_selector_index);
            llChoose = itemView.findViewById(R.id.ll_image_selector_choose);
        }
    }

    public interface OnImageSelectedListener {
        void onImageSelected(int position);

        void onImagePreview(int position);
    }
}
