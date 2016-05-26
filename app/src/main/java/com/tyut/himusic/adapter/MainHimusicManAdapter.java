package com.tyut.himusic.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyut.himusic.R;
import com.tyut.himusic.bean.MainHimusicListData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainHimusicManAdapter extends RecyclerView.Adapter<MainHimusicManAdapter.ViewHolder>
{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private List<MainHimusicListData> datas;
    private Context context;
    private View mHeaderView = null;

    public MainHimusicManAdapter(List<MainHimusicListData> datas, Context context)
    {
        super();
        this.datas = datas;
        this.context = context;
    }

    public void setDatas(List<MainHimusicListData> datas)
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
    public void onViewAttachedToWindow(MainHimusicManAdapter.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if(lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
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
                R.layout.item_frag_man_himusic, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position)
    {
        // 建立起ViewHolder中视图与数据的关联
        if (getItemViewType(position) == TYPE_HEADER) return;
        viewHolder.txtTitle.setText(datas.get(getRealPosition(position)).getTitle());

        viewHolder.imgPisture1.setImageURI(Uri.parse(datas.get(getRealPosition(position)).getImgurl()));
        viewHolder.txtTitle1.setText(datas.get(getRealPosition(position)).getTitle1());
        viewHolder.imgPisture2.setImageURI(Uri.parse(datas.get(getRealPosition(position)).getImgur2()));
        viewHolder.txtTitle2.setText(datas.get(getRealPosition(position)).getTitle2());
        viewHolder.imgPisture3.setImageURI(Uri.parse(datas.get(getRealPosition(position)).getImgur3()));
        viewHolder.txtTitle3.setText(datas.get(getRealPosition(position)).getTitle3());
        viewHolder.imgPisture4.setImageURI(Uri.parse(datas.get(getRealPosition(position)).getImgur4()));
        viewHolder.txtTitle4.setText(datas.get(getRealPosition(position)).getTitle4());
        viewHolder.imgPisture5.setImageURI(Uri.parse(datas.get(getRealPosition(position)).getImgur4()));
        viewHolder.txtTitle5.setText(datas.get(getRealPosition(position)).getTitle5());

    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        @Bind(R.id.item_frag_main_ing_title)
        TextView txtTitle;

        @Bind(R.id.himusic_circleIMG1)
        SimpleDraweeView imgPisture1;
        @Bind(R.id.himusic_title1)
        TextView txtTitle1;
        @Bind(R.id.himusic_circleIMG2)
        SimpleDraweeView imgPisture2;
        @Bind(R.id.himusic_title2)
        TextView txtTitle2;
        @Bind(R.id.himusic_circleIMG3)
        SimpleDraweeView imgPisture3;
        @Bind(R.id.himusic_title3)
        TextView txtTitle3;
        @Bind(R.id.himusic_circleIMG4)
        SimpleDraweeView imgPisture4;
        @Bind(R.id.himusic_title4)
        TextView txtTitle4;
        @Bind(R.id.himusic_circleIMG5)
        SimpleDraweeView imgPisture5;
        @Bind(R.id.himusic_title5)
        TextView txtTitle5;

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

