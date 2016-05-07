package com.zpauly.myrxandroidretrofitdemo.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zpauly.myrxandroidretrofitdemo.R;
import com.zpauly.myrxandroidretrofitdemo.adapter.MainFragmentAdapter;
import com.zpauly.myrxandroidretrofitdemo.entity.Category;
import com.zpauly.myrxandroidretrofitdemo.network.tvstation.CategoryMethod;
import com.zpauly.myrxandroidretrofitdemo.network.tvstation.CategoryService;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {
    private AppBarLayout mAppbarLayout;

    private Toolbar mToolbar;

    private TabLayout mTabs;

    private ViewPager mViewPager;

    private MainFragmentAdapter adapter;

    private Subscriber<Category> mSubscriber;

    private CategoryMethod categoryMethod;

    private MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryMethod = CategoryMethod.getInstance();

        mAppbarLayout = (AppBarLayout) findViewById(R.id.appbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabs = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        adapter = new MainFragmentAdapter(getSupportFragmentManager());

        initView();
    }

    private void initView() {
        mToolbar.setTitle("MainAcivity");
        setSupportActionBar(mToolbar);

        loadData();
        dialog = new MaterialDialog.Builder(this)
                .title("loading")
                .content("loading")
                .progress(true, 0)
                .show();
        mViewPager.setAdapter(adapter);

        mTabs.setupWithViewPager(mViewPager);
    }

    private void loadData() {
        mSubscriber = new Subscriber<Category>() {
            @Override
            public void onCompleted() {
                dialog.dismiss();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Category category) {
                if (category.getError_code() == 0) {
                    List<Category.ResultBean> data = category.getResult();
                    for (int i = 0; i < data.size(); i++) {
                        Fragment fragment = new MainFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("ID", data.get(i).getId());
                        fragment.setArguments(bundle);
                        adapter.addFragment(fragment, data.get(i).getName());
                        adapter.notifyDataSetChanged();
                    }

                }
            }
        };

        categoryMethod.getCategorys(mSubscriber);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unsubscribe();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unsubscribe();
    }

    private void unsubscribe() {
        if (mSubscriber.isUnsubscribed()) {
            mSubscriber.unsubscribe();
        }
    }
}
