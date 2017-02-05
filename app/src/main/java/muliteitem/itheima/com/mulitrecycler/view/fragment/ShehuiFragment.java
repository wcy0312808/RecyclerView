package muliteitem.itheima.com.mulitrecycler.view.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import muliteitem.itheima.com.mulitrecycler.interfaces.OnCallbacklistener;
import muliteitem.itheima.com.mulitrecycler.mode.Bean;
import muliteitem.itheima.com.mulitrecycler.mode.OkhttpManage;
import muliteitem.itheima.com.mulitrecycler.view.activity.MainActivity;
import muliteitem.itheima.com.mulitrecycler.view.adapter.MulitItemTypeSupport;
import muliteitem.itheima.com.mulitrecycler.view.adapter.MulitItemTypeaAdapter;
import muliteitem.itheima.com.mulitrecycler.view.adapter.ViewHolder;
import mulititem.itheima.com.mulititemrecycler.R;


/**
 * 作者：杨玉安 on 2017/2/4 0004 19:53
 * <p>
 * 邮箱：837513007@qq.com
 */

public class ShehuiFragment extends BaseFragment {
    private PullLoadMoreRecyclerView mRecyclerView;
    private MainActivity mMainActivity;
    private int num = 1;
    private String url = "http://ic.snssdk.com/2/article/v25/stream/?count=20&min_behot_time=1465264149&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1465265107&loc_mode=5&lac=4527&cid=28883&iid=4464422609&device_id=17258390367&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SAMSUNG&os_api=19&os_version=4.4.4&uuid=353040417908000&openudid=84c1c7b192991cc6";
    private List<Bean.DataBean> dataAll = new ArrayList<>();
    private MulitItemTypeaAdapter mMulitItemTypeAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int type = msg.arg1;
            if (msg.obj != null) {
                if (type == 2) {
                    dataAll.clear();
                }
                dataAll.addAll((List<Bean.DataBean>) msg.obj);
            }
            switch (type) {
                case 1:
                    MulitItemTypeSupport mulitItemTypeSupport = new MulitItemTypeSupport<Bean.DataBean>() {
                        @Override
                        public int getLayoutID(int ItemType) {
                            int layoutId = -1;
                            switch (ItemType) {
                                case 0:
                                    layoutId = R.layout.item_text;
                                    break;
                                case 1:
                                    layoutId = R.layout.item_three;
                                    break;
                                case 2:
                                    layoutId = R.layout.item_large;
                                    break;
                                case 3:
                                    layoutId = R.layout.item_one;
                                    break;
                            }
                            return layoutId;
                        }

                        @Override
                        public int getItemViewType(int position, Bean.DataBean o) {
                            int Type = 0;
                            Log.e("TAG", "getItemViewType:================= " + o.getTitle());
                            if (o.getImage_list() != null && o.getImage_list().size() >= 3) {
                                Type = 1;
                            } else if (o.getLarge_image_list() != null && o.getLarge_image_list().size() >= 1) {
                                Type = 2;
                            } else if (o.getMiddle_image() != null && o.getMiddle_image().getUrl() != null) {
                                Type = 3;
                            }
                            return Type;
                        }
                    };
                    mMulitItemTypeAdapter = new MulitItemTypeaAdapter<Bean.DataBean>(mMainActivity, dataAll, mulitItemTypeSupport) {
                        @Override
                        public void conver(ViewHolder viewHodler, Bean.DataBean o) {
                            int itemViewType = viewHodler.getItemViewType();
                            Log.e("TAG", "conver: ==========================" + itemViewType);
                            switch (itemViewType){
                                case 0:
                                    viewHodler.setText(R.id.item_tv,o.getTitle());
                                    break;
                                case 1:
                                    viewHodler.setText(R.id.item_tv,o.getTitle());
                                    viewHodler.setimageResouse(R.id.ll_iamge1,o.getImage_list().get(0).getUrl());
                                    viewHodler.setimageResouse(R.id.ll_iamge2,o.getImage_list().get(1).getUrl());
                                    viewHodler.setimageResouse(R.id.ll_iamge3,o.getImage_list().get(2).getUrl());
                                    break;
                                case 2:
                                    viewHodler.setText(R.id.item_tv,o.getTitle());
                                    viewHodler.setimageResouse(R.id.ll_large,o.getLarge_image_list().get(0).getUrl());
                                    break;
                                case 3:
                                    viewHodler.setText(R.id.item_tv,o.getTitle());
                                    viewHodler.setimageResouse(R.id.item_image_view,o.getMiddle_image().getUrl());
                                    break;
                            }
                        }
                    };
                    mRecyclerView.setAdapter(mMulitItemTypeAdapter);
                    break;
                case 2:

                    break;
                case 3:

                    break;
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMainActivity = (MainActivity) context;
    }

    @Override
    View createView() {
        return View.inflate(mMainActivity, R.layout.text_layout, null);
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
                if (bean != null) {
                    List<Bean.DataBean> data = bean.getData();
                    Message msg = new Message();
                    msg.arg1 = num;
                    msg.obj = data;
                    handler.sendMessage(msg);
                }
            }
        });
    }
}
