package muliteitem.itheima.com.mulitrecycler.mode;

import java.util.List;

/**
 * 作者：杨玉安 on 2017/2/4 0004 19:23
 * <p>
 * 邮箱：837513007@qq.com
 */

public class MiddleImageBean {

    /**
     * url : http://p2.pstatp.com/list/150c00055653843fab30
     * width : 530
     * url_list : [{"url":"http://p2.pstatp.com/list/150c00055653843fab30"},{"url":"http://p4.pstatp.com/list/150c00055653843fab30"},{"url":"http://p.pstatp.com/list/150c00055653843fab30"}]
     * uri : list/150c00055653843fab30
     * height : 298
     */

    private String url;
    private int width;
    private String uri;
    private int height;
    private List<UrlListBean> url_list;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<UrlListBean> getUrl_list() {
        return url_list;
    }

    public void setUrl_list(List<UrlListBean> url_list) {
        this.url_list = url_list;
    }

    public static class UrlListBean {
        /**
         * url : http://p2.pstatp.com/list/150c00055653843fab30
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
