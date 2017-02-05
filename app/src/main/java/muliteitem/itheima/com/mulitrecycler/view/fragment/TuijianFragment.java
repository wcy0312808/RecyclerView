package muliteitem.itheima.com.mulitrecycler.view.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.List;

import muliteitem.itheima.com.mulitrecycler.interfaces.OnCallbacklistener;
import muliteitem.itheima.com.mulitrecycler.mode.Bean;
import muliteitem.itheima.com.mulitrecycler.mode.OkhttpManage;
import muliteitem.itheima.com.mulitrecycler.view.activity.MainActivity;
import muliteitem.itheima.com.mulitrecycler.view.adapter.TuijianAdapter;
import mulititem.itheima.com.mulititemrecycler.R;


/**
 * 作者：杨玉安 on 2017/2/4 0004 16:03
 * <p>
 * 邮箱：837513007@qq.com
 */

public class TuijianFragment extends BaseFragment {
    private PullLoadMoreRecyclerView mRecyclerView;
    private MainActivity mMainActivity;
    private String url="http://ic.snssdk.com/2/article/v25/stream/?count=20&min_behot_time=1465264149&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1465265107&loc_mode=5&lac=4527&cid=28883&iid=4464422609&device_id=17258390367&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SAMSUNG&os_api=19&os_version=4.4.4&uuid=353040417908000&openudid=84c1c7b192991cc6";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMainActivity = (MainActivity) context;
    }

    @Override
    View createView() {
        return View.inflate(mMainActivity, R.layout.text_layout,null);
    }

    @Override
    void initView(View view) {
        mRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.recycler_contet1);
        mRecyclerView.setLinearLayout();
    }

    @Override
    void initDatas() {
        OkhttpManage.newInstence().okHttpRequest(url, Bean.class, new OnCallbacklistener<Bean>() {
            @Override
            public void onFial() {

            }

            @Override
            public void onSuccess(Bean bean) {
                Log.e("TAG", "onSuccess: "+bean.toString() );
                List<Bean.DataBean> data = bean.getData();
                mRecyclerView.setAdapter(new TuijianAdapter(data,mMainActivity));
            }
        });
    }
}
