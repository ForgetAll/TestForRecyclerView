package com.example.luo_pc.testforrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.luo_pc.testforrecyclerview.bean.ItemBean;

import java.util.ArrayList;

/**
 * Created by luo-pc on 2016/6/1.
 */
public class GridActivity extends AppCompatActivity {

    ArrayList<ItemBean> imgList;
    private RecyclerView rv_test;
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_test = (RecyclerView) findViewById(R.id.rv_test);
//        iv_item_img = (ImageView) findViewById(R.id.iv_item_img);
//        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams)iv_item_img.getLayoutParams();
//        linearParams.height = 100;
//        iv_item_img.setLayoutParams(linearParams);
        context = GridActivity.this;
        initData();
        mAdapter myAdapter = new mAdapter();
        myAdapter.setData(imgList);

        rv_test.setLayoutManager(new GridLayoutManager(this,3));
        rv_test.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Context mContext = getApplicationContext();
        switch (item.getItemId()){
            case R.id.jump_first:
                Intent intent1 = new Intent(mContext,MainActivity.class);
                startActivity(intent1);
                break;

            case R.id.jump_second:
                Intent intent2 = new Intent(mContext,LinearLayout.class);
                startActivity(intent2);
                break;

            case R.id.jump_third:
                Toast.makeText(mContext,"这就是第三个了！",Toast.LENGTH_LONG).show();
                break;

            default:

                break;
        }
        return true;
    }


    private void initData(){
        imgList = new ArrayList<ItemBean>();
        imgList.add(new ItemBean("http://ww1.sinaimg.cn/large/7a8aed7bgw1f3j8jt6qn8j20vr15owvk.jpg","美女1"));
        imgList.add(new ItemBean("http://ww1.sinaimg.cn/large/7a8aed7bgw1f3damign7mj211c0l0dj2.jpg","美女2"));
        imgList.add(new ItemBean("http://ww2.sinaimg.cn/large/7a8aed7bjw1f3c7zc3y3rj20rt15odmp.jpg","美女3"));
        imgList.add(new ItemBean("http://ww2.sinaimg.cn/large/7a8aed7bjw1f3azi5zoysj20dw0kuabb.jpg","美女4"));
        imgList.add(new ItemBean("http://ww4.sinaimg.cn/large/7a8aed7bjw1f37vhovzlnj20f00evabt.jpg","美女5"));
        imgList.add(new ItemBean("http://ww2.sinaimg.cn/large/7a8aed7bjw1f340c8jrk4j20j60srgpf.jpg","美女6"));
        imgList.add(new ItemBean("http://ww4.sinaimg.cn/large/7a8aed7bjw1f32d0cumhkj20ey0mitbx.jpg","美女7"));
        imgList.add(new ItemBean("http://ww2.sinaimg.cn/large/7a8aed7bjw1f30sgi3jf0j20iz0sg40a.jpg","美女8"));
        imgList.add(new ItemBean("http://ww4.sinaimg.cn/large/610dc034jw1f3zen8idmkj20dw0kun0i.jpg","美女9"));
        imgList.add(new ItemBean("http://ww1.sinaimg.cn/large/7a8aed7bjw1f3y998rv5uj20m80vxq6c.jpg","美女10"));
        imgList.add(new ItemBean("http://ww4.sinaimg.cn/large/610dc034jw1f3x32bd1hcj20d90k03zx.jpg","美女11"));
        imgList.add(new ItemBean("http://ww2.sinaimg.cn/large/7a8aed7bjw1f3tkjebzzpj20kg0v7q9h.jpg","美女12"));
        imgList.add(new ItemBean("http://ww3.sinaimg.cn/large/7a8aed7bjw1f3rdepqtnij21kw2dc1cx.jpg","美女13"));
        imgList.add(new ItemBean("http://ww2.sinaimg.cn/large/610dc034jw1f3rbikc83dj20dw0kuadt.jpg","美女14"));
        imgList.add(new ItemBean("http://ww2.sinaimg.cn/large/610dc034jw1f3q5semm0wj20qo0hsacv.jpg","美女15"));
        imgList.add(new ItemBean("http://ww3.sinaimg.cn/large/610dc034jw1f3ozv0wqywj20qo0em0vt.jpg","美女16"));

    }

    class mAdapter extends RecyclerView.Adapter {
        //数据
        private ArrayList<ItemBean> itemList = new ArrayList<ItemBean>();

        //设置数据
        private void setData(ArrayList<ItemBean> itemList) {
            this.itemList = itemList;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //得到item的view
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item2, parent, false);
            //返回viewholder
            return new mViewHolder(view);
        }

        /**
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ItemBean it = itemList.get(position);
            mViewHolder mholder = (mViewHolder) holder;
            //设置imageView的图片
//            mholder.iv_item_img.setImageResource(it.getImg());
            //缓存整张图片
            Glide.with(getApplicationContext()).load(it.getUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(mholder.iv_item_img);
//            设置textView的文字
            mholder.tv_item_desc.setText(it.getDesc());
        }


        @Override
        public int getItemCount() {
            return itemList.size();
        }

        class mViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_item_img;
            TextView tv_item_desc;

            public mViewHolder(View itemView) {
                super(itemView);
                iv_item_img = (ImageView) itemView.findViewById(R.id.iv_item_img1);
                tv_item_desc = (TextView) itemView.findViewById(R.id.tv_item_desc1);
            }
        }
    }

}
