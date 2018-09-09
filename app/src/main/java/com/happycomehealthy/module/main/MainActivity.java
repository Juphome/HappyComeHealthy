package com.happycomehealthy.module.main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.happycomehealthy.R;
import com.happycomehealthy.adapter.SectionsPagerAdapter;
import com.happycomehealthy.bean.Constants;
import com.happycomehealthy.module.base.BaseActivity;
import com.happycomehealthy.module.base.BasePresenter;
import com.happycomehealthy.module.edit.EditFragment;
import com.happycomehealthy.module.mine.MineFragment;
import com.happycomehealthy.module.search.SearchActivity;
import com.happycomehealthy.module.sign.SignFragment;
import com.happycomehealthy.module.statistics.StatisticsFragment;
import com.happycomehealthy.net.ApiHelper;
import com.happycomehealthy.net.pojo.BaseResponse;
import com.happycomehealthy.net.pojo.Guest;
import com.happycomehealthy.utils.ToastUtils;
import com.happycomehealthy.widget.bottombar.BottomNavigationViewHelper;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity
        implements MainContract.IMainView, ViewPager.OnPageChangeListener,
        SearchView.OnQueryTextListener{



    private Unbinder unbinder;

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    private EditFragment editFragment;
    private SignFragment signFragment;
    private StatisticsFragment statisticsFragment;
    private ViewPager viewPager;
    private List<Fragment> fragments;


    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private MainPresenter mainPresenter;

    @Override
    public int loadLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this);

    }

    @Override
    public void initData() {

        initToolBar();
        initBottomNavigationBar();
        initViewPager();



    }

    @Override
    public BasePresenter setPresenter() {
        mainPresenter = new MainPresenter(this);
        return mainPresenter;
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//这句代码使启用Activity回退功能，并显示Toolbar上的左侧回退图标
    }

    /**
     * bottomNavigation 设置
     */
    private void initBottomNavigationBar() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
//                            case R.id.item_sign:
//                                viewPager.setCurrentItem(0);
//                                break;
                            case R.id.item_edit:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.item_statistics:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.item_mime:
                                viewPager.setCurrentItem(2);
                                break;
                        }
                        return false;
                    }
                });
//        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
//
//        /** 导航基础设置 包括按钮选中效果 导航栏背景色等 */
//        bottomNavigationBar.setTabSelectedListener(this);
//        bottomNavigationBar.clearAll();
//        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
//        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
//
//        /** 添加导航按钮 */
//        bottomNavigationBar
//                .addItem(new BottomNavigationItem(R.drawable.ic_sign_press, R.string.register)
//                        //.setInactiveIconResource(R.drawable.ic_sign_default)
//                        .setActiveColorResource(R.color.red))
//                .addItem(new BottomNavigationItem(R.drawable.ic_edit_press, R.string.edit)
//                        //  .setInactiveIconResource(R.drawable.ic_edit_default)
//                        .setActiveColorResource(R.color.red))
//                .addItem(new BottomNavigationItem(R.drawable.ic_statistic_press, R.string.statistics)
//                        //   .setInactiveIconResource(R.drawable.ic_statistic_default)
//                        .setActiveColorResource(R.color.red))
//                .addItem(new BottomNavigationItem(R.drawable.ic_mine_press, R.string.mime)
//                        //   .setInactiveIconResource(R.drawable.ic_statistic_default)
//                        .setActiveColorResource(R.color.red))
////                .addItem(new BottomNavigationItem(R.drawable.ic_sign_default, "签到"))
////                .addItem(new BottomNavigationItem(R.drawable.ic_edit_default, "编辑"))
////                .addItem(new BottomNavigationItem(R.drawable.ic_statistic_default, "统计"))
//                .setFirstSelectedPosition(lastSelectedPosition)
//                .initialise(); //initialise 一定要放在 所有设置的最后一项

    }

    private void initViewPager(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        fragments = new ArrayList<>();
//        fragments.add(SignFragment.newInstance("签到"));
        fragments.add(EditFragment.newInstance("编辑"));
        fragments.add(StatisticsFragment.newInstance("统计"));
        fragments.add(MineFragment.newInstance("我"));
        viewPager.addOnPageChangeListener(this);


        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), fragments));
//        viewPager.setCurrentItem(0);
    }



    public void testGet() {
        ToastUtils.showToast("test get");

        Call<BaseResponse<Guest>> user =
                ApiHelper.getInstance().getApiService().getGuest("15976191502");
        user.enqueue(new Callback<BaseResponse<Guest>>() {
            @Override
            public void onResponse(Call<BaseResponse<Guest>> call, Response<BaseResponse<Guest>> response) {
                Logger.i(response.toString());
            }

            @Override
            public void onFailure(Call<BaseResponse<Guest>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        // Assumes current activity is the searchable activity
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(this);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId() == android.R.id.home){
//            Log.i(TAG,"back");
//        }
//        return true;
//    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        if (presenter != null) {
            presenter.onDestroy();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }


    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        bottomNavigationBar.selectTab(position);
        if (menuItem != null) {
            menuItem.setChecked(false);
        } else {
            bottomNavigationView.getMenu().getItem(0).setChecked(false);
        }
        menuItem = bottomNavigationView.getMenu().getItem(position);
        menuItem.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {


        Logger.i(query);
        mainPresenter.searchGuestInfo(query);



        return false;
    }

    private void startSearchActivity() {
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void showGuestInfo(Guest guest) {
        Intent intent = new Intent(this,SearchActivity.class);
        intent.putExtra(Constants.GUEST,guest);
        startActivity(intent);
    }
}
