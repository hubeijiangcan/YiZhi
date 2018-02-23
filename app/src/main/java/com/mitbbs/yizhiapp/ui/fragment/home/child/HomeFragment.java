package com.mitbbs.yizhiapp.ui.fragment.home.child;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mitbbs.sdk.adapter.FragmentAdapter;
import com.mitbbs.sdk.anim.ToolbarAnimManager;
import com.mitbbs.sdk.base.BasePresenter;
import com.mitbbs.sdk.base.fragment.BaseMVPCompatFragment;
import com.mitbbs.yizhiapp.R;
import com.mitbbs.yizhiapp.constant.TabFragmentIndex;
import com.mitbbs.yizhiapp.contract.home.HomeMainContract;
import com.mitbbs.yizhiapp.presenter.home.HomeMainPresenter;
import com.mitbbs.yizhiapp.ui.fragment.home.child.tabs.WangyiFragment;
import com.mitbbs.yizhiapp.ui.fragment.home.child.tabs.WeixinFragment;
import com.mitbbs.yizhiapp.ui.fragment.home.child.tabs.ZhiHuFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * data:2017/12/26.
 *
 * @author:jc
 */
public class HomeFragment extends BaseMVPCompatFragment<HomeMainContract.HomeMainPresenter,
        HomeMainContract.IHomeMainModel> implements HomeMainContract.IHomeMainView {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;
    @BindView(R.id.fab_download)
    FloatingActionButton fabDownload;

    protected OnOpenDrawerLayoutListener onOpenDrawerLayoutListener;
    private List<Fragment> fragments;
    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOpenDrawerLayoutListener){
            onOpenDrawerLayoutListener = (OnOpenDrawerLayoutListener) context;
        }
        fragments = new ArrayList<>();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onOpenDrawerLayoutListener = null;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getTabList();
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return HomeMainPresenter.newInstance();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_;
    }

    @Override
    protected void initUI(View mRootView, Bundle savedInstanceState) {
        toolbar.setTitle("首页");
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOpenDrawerLayoutListener != null){
                    onOpenDrawerLayoutListener.onOpen();
                }
            }
        });

        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    fabDownload.show();
                } else {
                    fabDownload.hide();
                }
            }
        });

        toolbar.inflateMenu(R.menu.toolbar_menu);
        ToolbarAnimManager.animIn(mContext, toolbar);
    }

    @Override
    public void showTabList(String[] tabs) {

        for (int i = 0;i < tabs.length;i++){
            tlTabs.addTab(tlTabs.newTab().setText(tabs[i]));
            switch (i) {
                case TabFragmentIndex.TAB_ZHIHU_INDEX:
                    fragments.add(ZhiHuFragment.newInstance());
                    break;
                case TabFragmentIndex.TAB_WANGYI_INDEX:
                    fragments.add(WangyiFragment.newInstance());
                    break;
                case TabFragmentIndex.TAB_WEIXIN_INDEX:
                    fragments.add(WeixinFragment.newInstance());
                    break;
                default:
                    fragments.add(ZhiHuFragment.newInstance());
                    break;
            }
        }
        vpFragment.setAdapter(new FragmentAdapter(getChildFragmentManager(),fragments));
        //要设置到viewpager.setAdapter后才起作用
        vpFragment.setCurrentItem(TabFragmentIndex.TAB_ZHIHU_INDEX);
        tlTabs.setupWithViewPager(vpFragment);
        tlTabs.setVerticalScrollbarPosition(TabFragmentIndex.TAB_ZHIHU_INDEX);
        //tlTabs.setupWithViewPager方法内部会remove所有的tabs，这里重新设置一遍tabs的text，否则tabs的text不显示
        for (int i = 0;i < tabs.length;i++){
            tlTabs.getTabAt(i).setText(tabs[i]);
        }

    }





    /**
     * fragment打开DrawerLayout监听
     */
    public interface OnOpenDrawerLayoutListener {
        void onOpen();
    }
}
