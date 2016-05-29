package com.tyut.himusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tyut.himusic.R;
import com.tyut.himusic.domain.Mp3Info;

import java.util.List;

/**
 * Created by Administrator on 2016/5/29.
 */
public class MusicListAdapter extends BaseAdapter
{
    private Context context;
    private List<Mp3Info> mp3Infos;
    private Mp3Info mp3Info;
    private int pos = -1;


    public MusicListAdapter(Context context, List<Mp3Info> mp3Infos)
    {
        this.context = context;
        this.mp3Infos = mp3Infos;
    }

    public int getCount()
    {
        return mp3Infos.size();
    }

    public Object getItem(int position)
    {
        return position;
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.music_list_item_layout, null);
            viewHolder.musicTitle = (TextView) convertView.findViewById(R.id.music_title);
            viewHolder.musicArtist = (TextView) convertView.findViewById(R.id.music_Artist);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        mp3Info = mp3Infos.get(position);
        viewHolder.musicTitle.setText(mp3Info.getTitle());
        viewHolder.musicArtist.setText(mp3Info.getArtist());

        return convertView;
    }

    public class ViewHolder
    {
        public TextView musicTitle;
        public TextView musicDuration;
        public TextView musicArtist;
    }


}
