package muliteitem.itheima.com.mulitrecycler.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：杨玉安 on 2017/1/25 0025 18:29
 * <p>
 * 邮箱：837513007@qq.com
 */

public abstract class BaseFragment extends Fragment {
    //这是子类实例化出来的View 在父类CreateView的时候就直接赋值
    private View mChildView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mChildView = createView();
        return mChildView;
    }

    @Override
    public void onResume() {
        super.onResume();
        //将每个子类实例化出来的View再返回过去，，初始化视图
        initView(mChildView);
        initDatas();
    }

    abstract View createView();
    abstract void initView(View view);
    abstract void initDatas();
}
