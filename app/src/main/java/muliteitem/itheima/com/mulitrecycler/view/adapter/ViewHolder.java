package muliteitem.itheima.com.mulitrecycler.view.adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * @作者 : 杨玉安
 * @日期 : 2017/2/5 0005 09:42
 *
 */
public class ViewHolder extends RecyclerView.ViewHolder{
    /**
     * 使用集合来存储item上的控件
     */
    private SparseArray<View> mViewList;

    /**
     * 加载item的布局
     */
    private View mConvertView;

    /**
     * 记录改变背景的ViewID
     */
    private int mViewID;

    /**
     * 背景颜色的资源ID
     */
    private int mResID;

    /**
     * 当前ViewHolder所关联的Adapter
     */
    private CommonAdapter mAdapter;

    /**
     * 记录是否 进行背景颜色更改的监听
     */
    boolean isChange = false;

    /**
     * 初始id是4
     *
     * @param itemView
     */
    private ViewHolder(View itemView) {
        super(itemView);
        this.mConvertView = itemView;
        mViewList = new SparseArray<View>();
    }

//    private List<SubItemBean.DatasBean.SubItemListBean> subItemListDatas;

    /**
     * 获取ViewHolder的方法
     *
     * @param context  上下文
     * @param layoutID 布局ID
     * @param parent   父控件
     * @return 返回当前的布局ID所对应的ViewHolder的实例
     */
    public static ViewHolder getViewHolder(Context context, int layoutID, ViewGroup parent) {
        //将布局ID转化为视图
        View itemView = LayoutInflater.from(context).inflate(layoutID, parent, false);
        //实例化当前ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemView);
        //返回
        return viewHolder;
    }

    /**
     * 通过ID取控件的方法 对ItemView的重用已经进行了缓存
     *
     * @param viewID 控件的ID
     * @return 返回id所对应的控件
     */
    public <T extends View> T getView(int viewID) {
        //从集合中取控件
        View view = mViewList.get(viewID);
        //如果没有就通过findViewById创建一个,并缓存到集合中
        if (view == null) {
            view = mConvertView.findViewById(viewID);
            mViewList.put(viewID, view);
        }
        return (T) view;
    }

    /**
     * 辅助方法
     */
    public ViewHolder setText(int viewID, String string) {
        TextView textView = getView(viewID);
        textView.setText(string);
        return this;
    }

    public ViewHolder setimageResouse(int viewID, int resID) {
        ImageView imageView = getView(viewID);
        imageView.setImageResource(resID);
        return this;
    }

    public ViewHolder setButtpn(int viewID, String string) {
        Button button = getView(viewID);
        button.setText(string);
        return this;
    }

    public ViewHolder setimageResouse(int viewID, String uri) {
        Uri uri1 = Uri.parse(uri);
        SimpleDraweeView imageView = getView(viewID);
        imageView.setImageURI(uri1);
        return this;
    }


    public ViewHolder delGoods(int viewId)
    {

        return this;
    }





    public ViewHolder setOnItemClickLisenter(final OnItemClickLisenter listener) {
        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isChange) {
                    mAdapter.changeItemBackground(mViewID, mResID, getLayoutPosition());
                    mAdapter.notifyDataSetChanged();
                }
                listener.onItemClickLisenter(itemView, getLayoutPosition());
            }
        });
        return this;
    }

    public ViewHolder setOnClickLisenter(int viewID, View.OnClickListener listener) {
        View view = getView(viewID);
        view.setOnClickListener(listener);
        return this;
    }

    public interface OnItemClickLisenter {
        void onItemClickLisenter(View view, int position);
    }


    public ViewHolder setTextViewClickColor(final int viewID, final int colorID) {
        Log.d("TAG", "colorID :" + colorID);
        isChange = true;
        this.mViewID = viewID;
        this.mResID = colorID;
        return this;
    }


    public void regist(CommonAdapter adapter) {
        this.mAdapter = adapter;
    }
}
