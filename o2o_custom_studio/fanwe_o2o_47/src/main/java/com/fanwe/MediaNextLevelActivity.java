package com.fanwe;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanwe.constant.Constant;
import com.fanwe.dao.LocalUserModelDao;
import com.fanwe.fragment.MediaNextLevelFragment;
import com.fanwe.model.LocalUserModel;
import com.fanwe.o2o.newo2o.R;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edison on 2016/7/31.
 * 我的下级
 */
public class MediaNextLevelActivity extends BaseActivity {

    @ViewInject(R.id.user_name)
    private TextView user_name;

    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    //private List<View> mViewList = new ArrayList<>();//页卡视图集合
    private List<Fragment> mFragmentList = new ArrayList<>();//页卡视图集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setmTitleType(Constant.TitleType.TITLE);
        setContentView(R.layout.act_media_next_level);
        initView();
    }

    private void initView() {
        mTitle.setMiddleTextTop("自媒体");

        ViewPager mViewPager = (ViewPager) findViewById(R.id.vp_view);
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabs);

        //添加页卡视图
        /*LayoutInflater mInflater = LayoutInflater.from(this);
        mViewList.add(mInflater.inflate(R.layout.act_apply_hyd, mTabLayout, false));//页卡视图1
        mViewList.add(mInflater.inflate(R.layout.act_apply_person_hhr, mTabLayout, false));//页卡视图2
        mViewList.add(mInflater.inflate(R.layout.act_apply_person_hhr, mTabLayout, false));//页卡视图2*/
        mFragmentList.add(MediaNextLevelFragment.getInstance(1));
        mFragmentList.add(MediaNextLevelFragment.getInstance(2));
        mFragmentList.add(MediaNextLevelFragment.getInstance(3));

        //添加页卡标题
        mTitleList.add("一级");
        mTitleList.add("二级");
        mTitleList.add("三级");

        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(2)));

        //MyPagerAdapter mAdapter = new MyPagerAdapter(mViewList);
        FragmentAdapter mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。

        LocalUserModel localUserModel = LocalUserModelDao.queryModel();
        user_name.setText(localUserModel.getUser_name());
    }

    class FragmentAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<>();

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);//页卡标题
        }
    }
}
