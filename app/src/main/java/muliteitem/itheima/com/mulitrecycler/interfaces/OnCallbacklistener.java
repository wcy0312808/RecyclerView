package muliteitem.itheima.com.mulitrecycler.interfaces;

/**
 * 作者：杨玉安 on 2017/1/23 0023 16:09
 * <p>
 * 邮箱：837513007@qq.com
 */

public interface OnCallbacklistener<T> {
    void onFial();

    void onSuccess(T bean);
}
