package com.tyut.himusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tyut.himusic.R;
import com.tyut.himusic.domain.Mp3Info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/29.
 */
public class JayzhouMusicListAdapter extends BaseAdapter
{
    private List<Map<String, Object>> data;
    private LayoutInflater JayzhouMusicListAdapter = null;

    public JayzhouMusicListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }


    private List<Map<String, Object>> getData()
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for(int i=0;i<10;i++)
        {
            map = new HashMap<String, Object>();
            map.put("num", i);
            map.put("title", "晴天");
            map.put("info", "周杰伦—叶惠美");
            list.add(map);
        }
        return list;
    }

    //ViewHolder静态类
    public class ViewHolder
    {
        public TextView num;
        public TextView title;
        public TextView special;
    }


        private LayoutInflater mInflater = null;



        @Override
        public int getCount() {

            return data.size();
        }

        @Override
        public Object getItem(int position) {

            return position;
        }

        @Override
        public long getItemId(int position) {

            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            //如果缓存convertView为空，则需要创建View
            if (convertView == null) {
                holder = new ViewHolder();
                //根据自定义的Item布局加载布局
                convertView = mInflater.inflate(R.layout.item_jayzhou_musiclist, null);
                holder.num = (TextView) convertView.findViewById(R.id.jayzhou_music_num);
                holder.title = (TextView) convertView.findViewById(R.id.jayzhou_music_name);
                holder.special = (TextView) convertView.findViewById(R.id.jayzhou_music_spec);
                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.num.setText((String) data.get(position).get("num"));
            holder.title.setText((String) data.get(position).get("title"));
            holder.special.setText((String) data.get(position).get("info"));

            return convertView;
        }
    }
