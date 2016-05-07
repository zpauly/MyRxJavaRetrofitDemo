package com.zpauly.myrxandroidretrofitdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.zpauly.myrxandroidretrofitdemo.R;
import com.zpauly.myrxandroidretrofitdemo.adapter.ProgramsRecyclerViewAdapter;
import com.zpauly.myrxandroidretrofitdemo.entity.Program;
import com.zpauly.myrxandroidretrofitdemo.network.program.ProgramMethod;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscriber;

/**
 * Created by root on 16-5-6.
 */
public class ProgramsActivity extends AppCompatActivity {
    private AppBarLayout mAppBar;

    private Toolbar mToolbar;

    private SwipeRefreshLayout mRefreshLayout;

    private RecyclerView mRecyclerView;

    private ProgramsRecyclerViewAdapter adapter;

    private Subscriber<Program> mSubscriber;

    private ProgramMethod programMethod;

    private String date;

    private String code;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);

        programMethod = ProgramMethod.getInstance();
        mAppBar = (AppBarLayout) findViewById(R.id.appbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        adapter = new ProgramsRecyclerViewAdapter(this);
        code = getIntent().getStringExtra("CODE");

        initView();
    }

    private void initView() {
        mToolbar.setTitle("PROGRAMS");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        mRefreshLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        /*mRefreshLayout.setRefreshing(true);*/
        loadData();
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    private void loadData() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.format(new Date());

        mSubscriber = new Subscriber<Program>() {
            @Override
            public void onCompleted() {
                if (mRefreshLayout.isRefreshing())
                mRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(ProgramsActivity.this, "FAIL TO GET DATA", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Program program) {
                if (program.getError_code() == 0) {
                    adapter.addAllData(program.getResult());
                }
            }
        };

        programMethod.getPrograms(mSubscriber, getIntent().getStringExtra("CODE"), date);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unsubscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unsubscribe();
    }

    private void unsubscribe() {
        if (mSubscriber.isUnsubscribed()) {
            mSubscriber.unsubscribe();
        }
    }
}
