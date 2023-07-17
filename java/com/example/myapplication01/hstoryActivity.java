package com.example.myapplication01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Objects;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import cn.ycbjie.ycstatusbarlib.bar.YCAppBar;

public class hstoryActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter mMyAdapter ;
    List<News> mNewsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        YCAppBar.translucentStatusBar(this, true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hstory);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        mRecyclerView = findViewById(R.id.recyclerview);
        ArrayList exesList = getIntent().getStringArrayListExtra("exesList");
        ArrayList resultList = getIntent().getStringArrayListExtra("resultList");

        for (int i = 0; i < exesList.size(); i++) {
            News news = new News();
            news.title = "" + (i + 1) + ":    " + exesList.get(i).toString();
            news.content = "      "+resultList.get(i).toString();
            mNewsList.add(news);
        }//将链表遍历存到recycleview里面；

        mMyAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mMyAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(hstoryActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
//        RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
//                mNewsList.clear();
//                for (int i = 0; i < 10; i++) {
//                    News news = new News();
//                    news.title = "标题 新内容" + i;
//                    news.content = "内容" + i;
//                    mNewsList.add(news);
//                }
//                mMyAdapter.notifyDataSetChanged();
//            }
//        });
//        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshlayout) {
//                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
//                for (int i = 0; i < 10; i++) {
//                    News news = new News();
//                    news.title = "标题 新内容" + i;
//                    news.content = "内容" + i;
//                    mNewsList.add(news);
//                }
//                mMyAdapter.notifyDataSetChanged();
//            }
//        });
    }
        //显示所有题目
    class MyAdapter extends RecyclerView.Adapter<MyViewHoder> {

        @NonNull
        @Override
        public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(hstoryActivity.this, R.layout.item_list, null);
            MyViewHoder myViewHoder = new MyViewHoder(view);
            return myViewHoder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHoder holder, int position) {
            News news = mNewsList.get(position);
            holder.mTitleTv.setText(news.title);
            holder.mTitleContent.setText(news.content);
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        TextView mTitleTv;
        TextView mTitleContent;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            mTitleTv = itemView.findViewById(R.id.textView);
            mTitleContent = itemView.findViewById(R.id.textView2);
        }
    }
}
