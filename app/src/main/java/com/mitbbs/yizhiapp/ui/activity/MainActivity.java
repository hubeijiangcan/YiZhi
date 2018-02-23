package com.mitbbs.yizhiapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.mitbbs.sdk.base.activity.BaseCompatActivity;
import com.mitbbs.sdk.helper.BottomNavigationViewHelper;
import com.mitbbs.sdk.widgets.MovingImageView;
import com.mitbbs.yizhiapp.R;
import com.mitbbs.yizhiapp.ui.fragment.book.BookRootFragment;
import com.mitbbs.yizhiapp.ui.fragment.gankio.GankIoRootFragment;
import com.mitbbs.yizhiapp.ui.fragment.home.HomeRootFragment;
import com.mitbbs.yizhiapp.ui.fragment.home.child.HomeFragment;
import com.mitbbs.yizhiapp.ui.fragment.movie.MovieRootFragment;
import com.mitbbs.yizhiapp.ui.fragment.personal.PersonalRootFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;
import de.hdodenhof.circleimageview.CircleImageView;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * data:2017/12/19.
 *
 * @author:jc
 */
public class MainActivity extends BaseCompatActivity implements HomeFragment.
        OnOpenDrawerLayoutListener {


    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;

    @BindView(R.id.bnv_bar)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.nv_menu)
    NavigationView nvMenu;
    @BindView(R.id.dl_root)
    DrawerLayout dlRoot;

    private SupportFragment[] mFragments = new SupportFragment[5];

    private MovingImageView mivMenu;
    private CircleImageView civHead;

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mFragments[0] = HomeRootFragment.newInstance();
            mFragments[1] = GankIoRootFragment.newInstance();
            mFragments[2] = MovieRootFragment.newInstance();
            mFragments[3] = BookRootFragment.newInstance();
            mFragments[4] = PersonalRootFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_container, FIRST, mFragments[0],
                    mFragments[1],
                    mFragments[2],
                    mFragments[3],
                    mFragments[4]);
        } else {
            /**
             * 这里库已经做了Fragment恢复,不需要额外的处理了, 不会出现重叠问题
             *这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()
             *自行进行判断查找(效率更高些),用下面的方法查找更方便些
             */
            mFragments[FIRST] = findFragment(HomeRootFragment.class);
            mFragments[SECOND] = findFragment(GankIoRootFragment.class);
            mFragments[THIRD] = findFragment(MovieRootFragment.class);
            mFragments[FOURTH] = findFragment(BookRootFragment.class);
            mFragments[FIFTH] = findFragment(PersonalRootFragment.class);
        }

//        NavigationUtils.disableNavigationViewScrollbars(nvMenu)
        NavigationView nvMenu = findViewById(R.id.nv_menu);
        mivMenu =  nvMenu.getHeaderView(0).findViewById(R.id.miv_menu);
        civHead =  nvMenu.getHeaderView(0).findViewById(R.id.civ_head);

        /**此处实际应用中替换成服务器拉取图片
        Uri headUri = Uri.fromFile(new File(getCacheDir(), HEAD_IMAGE_NAME + ".jpg"))
        if (headUri != null) {
       String cropImagePath = FileUtils.getRealFilePathFromUri(AppUtils.getContext(), headUri)
            Bitmap bitmap = BitmapFactory.decodeFile(cropImagePath)
          if (bitmap != null) {
                civHead.setImageBitmap(bitmap)
           }
       }*/

        civHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlRoot.closeDrawer(GravityCompat.START);
                bottomNavigationView.setSelectedItemId(R.id.menu_item_personal);
            }
        });
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_home:
                        showHideFragment(mFragments[FIRST]);
                        break;
                    case R.id.menu_item_gank_io:
                        showHideFragment(mFragments[SECOND]);
                        break;
                    case R.id.menu_item_movie:
                        showHideFragment(mFragments[THIRD]);
                        break;
                    case R.id.menu_item_book:
                        showHideFragment(mFragments[FOURTH]);
                        break;
                    case R.id.menu_item_personal:
                        showHideFragment(mFragments[FIFTH]);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void onOpen() {
        if (!dlRoot.isDrawerOpen(GravityCompat.START)){
            dlRoot.openDrawer(GravityCompat.START);
        }
    }



}
