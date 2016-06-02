
package com.tyut.himusic.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.tyut.himusic.R;


/**
 * @author luqingchuan on 16/2/29 11:09
 * @email luqingchuan@foxmail.com
 */
public class SharePopWindow extends PopupWindow implements View.OnClickListener
{
//    private IShareView iShareView;

    public SharePopWindow(Context context/*, IShareView iShareView*/)
    {
//        this.iShareView = iShareView;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.pop_share, null,
                false);
        contentView.findViewById(R.id.view_share_wechat).setOnClickListener(this);
        contentView.findViewById(R.id.view_share_friend_circle).setOnClickListener(this);
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
            case R.id.view_share_wechat:
                this.dismiss();
//                iShareView.shareToWechat(0);
                break;
            case R.id.view_share_friend_circle:
                this.dismiss();
//                iShareView.shareToWechat(1);
                break;
            default:
                break;
        }
    }
}
