package com.zhaoguanche.tankerApp.inhouse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnBannerListener {
    //通知分享字段
    private String noticeUrl = "";
    private String noticeTitle = "";
    private String noticeDescript = "";
    private ImageView iv_pic;

    private Banner banner;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_pic=this.findViewById(R.id.iv_pic);
        initView();

        /*Bitmap bitmap = Bitmap.createBitmap(240, 240, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAlpha(80);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(40);
        Path path = new Path();
        path.moveTo(30, 150);
        path.lineTo(300, 0);
        canvas.drawTextOnPath("无名科技", path, 0, 30, paint);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        bitmapDrawable.setDither(true);

        iv_pic.setBackgroundDrawable(bitmapDrawable);*/




        //Bitmap bitmap= ImageUtils.drawTextToCenter(this,iv_pic,"该证件仅用于资质认证",20,getResources().getColor(R.color.gray));
        //Bitmap bitmap2=((BitmapDrawable)iv_pic.getDrawable()).getBitmap();
        //Bitmap bitmap=ImageUtils.drawCenterLable(this,bitmap2,"该证件件仅用于资质认证");
        Bitmap bitmap= ImageUtils.drawTextsToBitmap(this,iv_pic,"该证件件仅用于资质认证",getResources().getDimension(R.dimen.watermark_big_size),getResources().getColor(R.color.gray),-30,40);
        iv_pic.setImageBitmap(bitmap);


        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noticeUrl = "http://www.zhaoguanche.com/index";
                noticeTitle = "通知";
                noticeDescript = "国庆放假七天，请各位伙伴了安排好假期行程，祝假期愉快！";
                new ShareAction(MainActivity.this)
                        .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                                ShareUtils.shareWeb(MainActivity.this, noticeUrl, noticeTitle, noticeDescript, "http://p2.so.qhmsg.com/bdr/200_200_/t01d4d1b6ffe05fd187.jpg", R.mipmap.logo, share_media);
                            }
                        }).open();

            }
        });
    }

    private void initView() {
        banner = (Banner) findViewById(R.id.banner);
        //放图片地址的集合
        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();

        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        list_title.add("好好学习");
        list_title.add("天天向上");
        list_title.add("热爱劳动");
        list_title.add("不搞对象");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
       // banner.setBannerTitles(list_title);

        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();


    }
    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第"+position+"张轮播图");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }


    private void showMessage(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //MobclickAgent.onResume(MainActivity.this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //MobclickAgent.onPause(MainActivity.this);
    }
}
