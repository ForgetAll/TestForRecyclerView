package com.example.luo_pc.testforrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luo_pc.testforrecyclerview.bean.ItemBean;
import com.example.luo_pc.testforrecyclerview.utils.BitmapMemoryCache;
import com.example.luo_pc.testforrecyclerview.utils.ImageResizer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_test;
    private ArrayList<ItemBean> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        rv_test = (RecyclerView) findViewById(R.id.rv_test);
        //设置layoutManager
        rv_test.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        myAdapter mAdapter = new myAdapter();
        mAdapter.setItemList(itemList);
        rv_test.setAdapter(mAdapter);

    }

    private void initData() {
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

    class myAdapter extends RecyclerView.Adapter {
        private ArrayList<ItemBean> ItemList;
        ImageResizer imgResizer = new ImageResizer();
        BitmapMemoryCache lruBitmapCache = new BitmapMemoryCache();

        public void setItemList(ArrayList<ItemBean> ItemList) {
            this.ItemList = ItemList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);

            return new myViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            myViewHolder mholder = (myViewHolder) holder;
            ItemBean it = itemList.get(position);
            if(lruBitmapCache.getBitmapFromMemory(it.getDesc()) != null){
                mholder.iv_item_img.setImageBitmap(lruBitmapCache.getBitmapFromMemory(it.getDesc()));

            }else{
                //缓存图片
                lruBitmapCache.addBitmapToMemory(it.getDesc(),imgResizer.decodeSampledBitmapFromResource(getResources(),
                        it.getImg()));

                mholder.iv_item_img.setImageBitmap(imgResizer.decodeSampledBitmapFromResource(getResources(),
                        it.getImg()));
            }

            mholder.tv_item_desc.setText(it.getDesc());
        }

        @Override
        public int getItemCount() {
            return ItemList.size();
        }

        class myViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_item_img;
            TextView tv_item_desc;

            public myViewHolder(View itemView) {
                super(itemView);
                iv_item_img = (ImageView) itemView.findViewById(R.id.iv_item_img);
                tv_item_desc = (TextView) itemView.findViewById(R.id.tv_item_desc);
            }

        }

    }
}
