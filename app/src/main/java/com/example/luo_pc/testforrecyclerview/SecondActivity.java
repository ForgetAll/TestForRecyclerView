package com.example.luo_pc.testforrecyclerview;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luo_pc.testforrecyclerview.bean.ItemBean;
import com.example.luo_pc.testforrecyclerview.utils.ImageResizer;

import java.util.ArrayList;

/**
 * Created by luo-pc on 2016/5/30.
 */
public class SecondActivity extends AppCompatActivity{
    private ArrayList<ItemBean> itemList;
    private RecyclerView rv_test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_test = (RecyclerView) findViewById(R.id.rv_test);
        initData();

        rv_test.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mAdapter myAdapter = new mAdapter();
        myAdapter.setData(itemList);
        rv_test.setAdapter(myAdapter);

    }

    private void initData(){
        itemList = new ArrayList<ItemBean>();
        itemList.add(new ItemBean(R.drawable.bird, "南小鸟1"));
        itemList.add(new ItemBean(R.drawable.xiyangyang, "喜羊羊"));
        itemList.add(new ItemBean(R.drawable.bird3, "南小鸟2"));
        itemList.add(new ItemBean(R.drawable.blackcat, "黑猫"));
        itemList.add(new ItemBean(R.drawable.dog, "狗与剪刀"));
        itemList.add(new ItemBean(R.drawable.nike3, "妮可妮可"));
        itemList.add(new ItemBean(R.drawable.paojie, "炮姐"));
        itemList.add(new ItemBean(R.drawable.saber1, "吾王1"));
        itemList.add(new ItemBean(R.drawable.saber2, "吾王2"));
    }

    class mAdapter extends RecyclerView.Adapter{
        //数据
        private ArrayList<ItemBean> itemList = new ArrayList<ItemBean>();
        private ImageResizer imgResizer = new ImageResizer();
        //设置数据
        private void setData(ArrayList<ItemBean> itemList){
            this.itemList = itemList;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //得到item的view
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
            //返回viewholder
            return new mViewHolder(view);
        }

        /**
         *
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ItemBean it = itemList.get(position);
            mViewHolder mholder = (mViewHolder)holder;
            //设置imageView的图片
//            mholder.iv_item_img.setImageResource(it.getImg());
            mholder.iv_item_img.setImageBitmap(imgResizer.decodeSampledBitmapFromResource(getResources(),it.getImg()));
            //设置textView的文字
            mholder.tv_item_desc.setText(it.getDesc());
        }



        @Override
        public int getItemCount() {
            return itemList.size();
        }

        class mViewHolder extends RecyclerView.ViewHolder{
            ImageView iv_item_img;
            TextView tv_item_desc;

            public mViewHolder(View itemView) {
                super(itemView);
                iv_item_img = (ImageView) itemView.findViewById(R.id.iv_item_img);
                tv_item_desc = (TextView)itemView.findViewById(R.id.tv_item_desc);
            }
        }
    }

//    public static Bitmap decodeSampleBitmapFromResource(Resources res,int resId,int reqWidth,int reqHeight){
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        //可以只获取宽高而不加载
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(res,resId,options);
//
//        //计算压缩比例
//        options.inSampleSize = calculateInSampleaSize(options,reqWidth,reqHeight);
//
//        //解码
//        options.inJustDecodeBounds = false;
//        return BitmapFactory.decodeResource(res,resId,options);
//    }
//
//    private static int calculateInSampleaSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
//        // 图片的高宽
//        final int height = options.outHeight;
//        final int width = options.outWidth;
//        //默认不压缩
//        int inSampleSize = 1;
//
//        if (height > reqHeight || width > reqWidth) {
//            final int halfHeight = height / 2;
//            final int halfWidth = width / 2;
//            while ((halfHeight / inSampleSize) > reqHeight
//                    && (halfWidth / inSampleSize) > reqWidth) {
//                //这里设置inSampleSize为2的幂是因为解码器最终还是会对非2的幂的数进行向下处理，
//                // 获取到最靠近2的幂的数
//                inSampleSize *= 2;
//            }
//        }
//
//        return inSampleSize;
//    }
}
