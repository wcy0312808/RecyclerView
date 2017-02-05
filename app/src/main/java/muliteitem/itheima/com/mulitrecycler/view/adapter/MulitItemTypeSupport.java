package muliteitem.itheima.com.mulitrecycler.view.adapter;

/**
 * @作者 : 杨玉安
 * @日期 : 2017/2/5 0005 09:42
 *
 */

public interface MulitItemTypeSupport<T> {

    int getLayoutID(int ItemType);

    int getItemViewType(int position, T o);
}
