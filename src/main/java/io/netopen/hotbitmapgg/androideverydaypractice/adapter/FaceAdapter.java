package io.netopen.hotbitmapgg.androideverydaypractice.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.netopen.hotbitmapgg.androideverydaypractice.R;
import io.netopen.hotbitmapgg.androideverydaypractice.model.ExpressionPackage;
import io.netopen.hotbitmapgg.androideverydaypractice.recycleview.AbsRecyclerViewAdapter;

/**
 * Created by 11 on 2016/4/5.
 */
public class FaceAdapter extends AbsRecyclerViewAdapter
{
    private List<ExpressionPackage> datas = new ArrayList<>();

    public FaceAdapter(RecyclerView recyclerView, List<ExpressionPackage> datas)
    {
        super(recyclerView);
        this.datas = datas;
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        bindContext(parent.getContext());
        return new ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.card_item_face, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position)
    {
        if (holder instanceof ItemViewHolder)
        {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            Glide.with(getContext()).load(datas.get(position).image_url).into(itemViewHolder.mImg);
            itemViewHolder.mTv.setText(datas.get(position).description);
        }

        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount()
    {
        return datas == null ? 0 : datas.size();
    }

    public class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder
    {
        @Bind(R.id.item_img)
        ImageView mImg;

        @Bind(R.id.item_tv)
        TextView mTv;

        public ItemViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
