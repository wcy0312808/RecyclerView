package muliteitem.itheima.com.mulitrecycler.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import muliteitem.itheima.com.mulitrecycler.mode.Bean;
import mulititem.itheima.com.mulititemrecycler.R;


/**
 * 作者：杨玉安 on 2017/2/4 0004 15:10
 * <p>
 * 邮箱：837513007@qq.com
 */

public class TuijianAdapter extends RecyclerView.Adapter<TuijianAdapter.MyViewHolder> {
    private List<Bean.DataBean> data;
    private Context mContext;

    public TuijianAdapter(List<Bean.DataBean> data, Context context) {
        this.data = data;
        mContext = context;
    }

    //通过viewType来初始化布局
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =null;
        if (viewType == 0) {
            view = View.inflate(mContext, R.layout.item_text, null);
        }
        else if (viewType == 1) {
            view = View.inflate(mContext, R.layout.item_three, null);
        }
        else if (viewType == 2) {
            view = View.inflate(mContext, R.layout.item_large, null);
        }
        else if (viewType == 3) {
            view = View.inflate(mContext, R.layout.item_one, null);
        }
        return new MyViewHolder(view, viewType);
    }

    //绑定ViewHolder并赋值
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int typeView = holder.getItemViewType();
        if (typeView == 0) {
            holder.mTextview.setText(data.get(position).getTitle());
        }
        else if(typeView == 1)
        {
            holder.mTextview1.setText(data.get(position).getTitle());
            Uri url = Uri.parse(data.get(position).getImage_list().get(0).getUrl());
            holder.one_i1.setImageURI(url);
            Uri url1 = Uri.parse(data.get(position).getImage_list().get(1).getUrl());
            holder.one_i2.setImageURI(url1);
            Uri url2 = Uri.parse(data.get(position).getImage_list().get(2).getUrl());
            holder.one_i3.setImageURI(url2);
        }
        else if(typeView == 2)
        {
            holder.mTextview2.setText(data.get(position).getTitle());
            Uri url = Uri.parse(data.get(position).getLarge_image_list().get(0).getUrl());
            holder.two_i2.setImageURI(url);
        }
        else if(typeView == 3)
        {
            holder.mTextview3.setText(data.get(position).getTitle());
            String url1 = data.get(position).getMiddle_image().getUrl();
            Uri url = Uri.parse(url1);
            holder.three_i3.setImageURI(url);
        }


    }

    //返回一个多条目种类数
    @Override
    public int getItemCount() {
        return data.size();
    }

    //通过bean来返回ViewType
    @Override
    public int getItemViewType(int position) {
        Bean.DataBean dataBean = data.get(position);
        if (dataBean.getImage_list() != null && data.get(position).getImage_list().size() >= 3) {
            return 1;
        }
        if (dataBean.getLarge_image_list() != null && dataBean.getLarge_image_list().size() >= 1) {
            return 2;
        }
       if (dataBean.getMiddle_image() != null && dataBean.getMiddle_image().getUrl()!=null ) {
            return 3;
        }
        return  0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextview, mTextview1, mTextview2, mTextview3;
        com.facebook.drawee.view.SimpleDraweeView  one_i1,one_i2,one_i3,two_i2,three_i3;
        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == 0) {
                mTextview = (TextView) itemView.findViewById(R.id.item_tv);
            }
            else if (viewType == 1) {
                mTextview1 = (TextView) itemView.findViewById(R.id.item_tv);
                one_i1 = (com.facebook.drawee.view.SimpleDraweeView ) itemView.findViewById(R.id.ll_iamge1);
                one_i2 = (com.facebook.drawee.view.SimpleDraweeView ) itemView.findViewById(R.id.ll_iamge2);
                one_i3 = (com.facebook.drawee.view.SimpleDraweeView ) itemView.findViewById(R.id.ll_iamge3);
            }
            else if (viewType == 2) {
                mTextview2 = (TextView) itemView.findViewById(R.id.item_tv);
                 two_i2 = (com.facebook.drawee.view.SimpleDraweeView ) itemView.findViewById(R.id.ll_large);
            }
            else if (viewType == 3) {
                mTextview3 = (TextView) itemView.findViewById(R.id.item_tv);
                three_i3 = (com.facebook.drawee.view.SimpleDraweeView ) itemView.findViewById(R.id.item_image_view);
            }
        }
    }
}
