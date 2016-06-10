package com.tyut.himusic.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.tyut.himusic.R;
import com.tyut.himusic.util.SDCardUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author luqingchuan on 16/6/10 19:44
 * @email luqingchuan@foxmail.com
 */
public class VideoPopWindow extends PopupWindow implements View.OnClickListener
{
    Activity activity;

    public VideoPopWindow(Activity activity)
    {
        this.activity = activity;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.pop_video, null,
                false);
        contentView.findViewById(R.id.pop_video_record).setOnClickListener(this);
        contentView.findViewById(R.id.pop_video_local_list).setOnClickListener(this);
        contentView.findViewById(R.id.pop_video_cancel).setOnClickListener(this);
        this.setContentView(contentView);
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明0xb0000000
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
//        this.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.scan_mask));

        this.setAnimationStyle(R.style.popwindow_bottom_anim_style);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.pop_video_record:
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".3gp";

               File out = new File(SDCardUtil.getVideoPath(), fileName);
                Uri uri = Uri.fromFile(out);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                activity.startActivityForResult(intent,1111);
                this.dismiss();
                break;
            case R.id.pop_video_local_list:
                this.dismiss();
                break;
            case R.id.pop_video_cancel:
                this.dismiss();
                break;
            default:
                break;
        }
    }
}
