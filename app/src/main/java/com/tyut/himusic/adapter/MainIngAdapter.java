package com.tyut.himusic.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyut.himusic.R;
import com.tyut.himusic.bean.MainIngListData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author luqingchuan on 16/5/22 22:29
 * @email luqingchuan@foxmail.com
 */
public class MainIngAdapter extends RecyclerView.Adapter<MainIngAdapter.ViewHolder>
{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private List<MainIngListData> datas;
    private Context context;
    private View mHeaderView = null;

    public MainIngAdapter(List<MainIngListData> datas, Context context)
    {
        super();
        this.datas = datas;
        this.context = context;
    }

    public void setDatas(List<MainIngListData> datas)
    {
        this.datas = datas;
    }

    public View getHeaderView()
    {
        return mHeaderView;
    }

    public void setHeaderView(View headerView)
    {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    @Override
    public int getItemViewType(int position)
    {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public void onViewAttachedToWindow(MainIngAdapter.ViewHolder holder)
    {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams)
        {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(holder.getLayoutPosition() == 0);
        }
    }

    @Override
    public int getItemCount()
    {

        return mHeaderView == null ? datas.size() : datas.size() + 1;
    }

    public int getRealPosition(int position)
    {
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new ViewHolder(mHeaderView);
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_frag_main_ing, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position)
    {
        // 建立起ViewHolder中视图与数据的关联
        if (getItemViewType(position) == TYPE_HEADER) return;
        viewHolder.txtTitle.setText(datas.get(getRealPosition(position)).getTitle());
        viewHolder.imgPisture.setImageURI(Uri.parse(datas.get(getRealPosition(position)).getImgurl()));
        viewHolder.txtDate.setText(datas.get(getRealPosition(position)).getData());
        viewHolder.txtInformation.setText(datas.get(getRealPosition(position)).getIntroduction());

    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        @Bind(R.id.item_frag_main_ing_title)
        TextView txtTitle;
        @Bind(R.id.item_frag_main_ing_img)
        SimpleDraweeView imgPisture;
        @Bind(R.id.item_frag_main_ing_date)
        TextView txtDate;
        @Bind(R.id.item_frag_main_ing_information)
        TextView txtInformation;
        @Bind(R.id.item_frag_main_ing_share)
        ImageView imgShare;
        @Bind(R.id.item_frag_main_ing_dianzan)
        ImageView imgDianzan;

        public ViewHolder(View convertView)
        {
            super(convertView);
            if (convertView == mHeaderView) return;
            convertView.setOnClickListener(this);
            ButterKnife.bind(this, convertView);
        }

        // 通过接口回调来实现RecyclerView的点击事件
        @Override
        public void onClick(final View v)
        {

        }
    }


}

