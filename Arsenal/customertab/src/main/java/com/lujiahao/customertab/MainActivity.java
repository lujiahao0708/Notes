package com.lujiahao.customertab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lujiahao.customertab.fragment.ContactsFragment;
import com.lujiahao.customertab.fragment.DiscoverFragment;
import com.lujiahao.customertab.fragment.ProfileFragment;
import com.lujiahao.customertab.fragment.WechatFragment;
import com.lujiahao.customertab.tab.TabItem;
import com.lujiahao.customertab.tab.LfTabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LfTabLayout.OnTabClickListener{

    private LfTabLayout mLfTabLayout;
    private ViewPager mViewPager;
    private ArrayList<TabItem> tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mLfTabLayout = (LfTabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void initData() {
        tabs = new ArrayList<>();
        tabs.add(new TabItem(R.mipmap.ic_launcher,R.string.app_name,ContactsFragment.class));
        tabs.add(new TabItem(R.mipmap.ic_launcher,R.string.app_name, DiscoverFragment.class));
        tabs.add(new TabItem(R.mipmap.ic_launcher,R.string.app_name, ProfileFragment.class));
        tabs.add(new TabItem(R.mipmap.ic_launcher,R.string.app_name, WechatFragment.class));
        mLfTabLayout.initData(tabs,this);
        mLfTabLayout.setCurrentTab(0);

        mViewPager.setAdapter(new FragAdapter(getSupportFragmentManager(), tabs));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mLfTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onTabClick(TabItem tabItem) {
        mViewPager.setCurrentItem(tabs.indexOf(tabItem));
//        try {
//            BaseFragment fragment = tabItem.tagFragmentClz.newInstance();
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fragment).commitAllowingStateLoss();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }

    public class FragAdapter extends FragmentPagerAdapter{
        private ArrayList<TabItem> tabItems;

        public FragAdapter(FragmentManager fm, ArrayList<TabItem> tabItems) {
            super(fm);
            this.tabItems = tabItems;
        }

        public FragAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            try {
                return tabItems.get(position).tagFragmentClz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabItems.size();
        }
    }
}
