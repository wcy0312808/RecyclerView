package muliteitem.itheima.com.mulitrecycler.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import muliteitem.itheima.com.mulitrecycler.view.fragment.ShehuiFragment;
import muliteitem.itheima.com.mulitrecycler.view.fragment.TuijianFragment;
import muliteitem.itheima.com.mulitrecycler.view.view.SimpleViewPagerIndicator;
import mulititem.itheima.com.mulititemrecycler.R;


/**
 * @作者 : 杨玉安
 * @日期 : 2017/2/4 0004 14:47
 *
 */
public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private SimpleViewPagerIndicator mSimpleViewPagerIndicator;
    private String[] titleDatas = new String[]{"推荐","社会"};
    private List<Fragment> mFragmentDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(MainActivity.this);
        setContentView(R.layout.activity_main);
        initViews();
        initDatas();
    }

    private void initDatas() {
        mFragmentDatas = new ArrayList<>();
        mFragmentDatas.add(new ShehuiFragment());
        mFragmentDatas.add(new TuijianFragment());
        mSimpleViewPagerIndicator.setTitles(titleDatas);
        mSimpleViewPagerIndicator.setViewPager(mViewPager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentDatas.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentDatas.size();
            }
        });
    }

    private void initViews() {
        mViewPager= (ViewPager) findViewById(R.id.ViewPager);
        mSimpleViewPagerIndicator = (SimpleViewPagerIndicator) findViewById(R.id.SimpleViewPagerIndicator);
    }
}
