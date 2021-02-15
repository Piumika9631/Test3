package mobileview.com.android.bitzean.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import mobileview.com.android.bitzean.R;
import mobileview.com.android.bitzean.data.GroupItem;

public class GroupsAdaptor extends RecyclerView.Adapter<GroupsAdaptor.GroupsHolder> {

    private LayoutInflater mInflater;
    private List<GroupItem> mGroups;
    private Context mContext;
    private OnBottomReachedListener mOnBottomReachedListener;
    private OnClickListener mOnClickListener;

    public void addGroups(List<GroupItem> groups) {
        mGroups.addAll(groups);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public GroupsAdaptor(Context context, List<GroupItem> groups) {
        mInflater = LayoutInflater.from(context);
        this.mGroups = groups;
        this.mContext = context;
    }

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener) {
        this.mOnBottomReachedListener = onBottomReachedListener;
    }

    @NonNull
    @Override
    public GroupsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item_groups, parent, false);
        return new GroupsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupsHolder holder, int position) {
        if (mOnBottomReachedListener != null) {
            if (position == mGroups.size() - 1) {
                mOnBottomReachedListener.onBottomReached();
            }
        }
        GroupItem group = this.mGroups.get(position);

        String imageUrl = group.getCover();

        if (imageUrl != null) {
            Glide.with(mContext)
                    .load(imageUrl.trim())
                    .into(holder.coverImageView);
        }
        holder.titleTextView.setText(group.getGroupTitle());
        holder.categoryTextView.setText(group.getCategory());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGroups.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mGroups.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGroups.size();
    }

    class GroupsHolder extends RecyclerView.ViewHolder {
        ImageView coverImageView;
        TextView titleTextView;
        TextView categoryTextView;
        CardView cardView;

        public GroupsHolder(@NonNull View itemView) {
            super(itemView);
            coverImageView = itemView.findViewById(R.id.li_groups_imageView_cover);
            titleTextView = itemView.findViewById(R.id.li_groups_textView_title);
            categoryTextView = itemView.findViewById(R.id.li_groups_textView_category);
            cardView = itemView.findViewById(R.id.li_groups_card_view);
        }
    }

    public interface OnBottomReachedListener {
        void onBottomReached();
    }

    public interface OnClickListener {
        void onItemClick(int position);
    }
}
