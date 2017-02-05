package muliteitem.itheima.com.mulitrecycler.mode;

import java.util.List;

/**
 * 作者：杨玉安 on 2017/2/4 0004 18:57
 * <p>
 * 邮箱：837513007@qq.com
 */
public class DataBean {
    private String rid;
    private int bury_count;
    private String title;
    private MiddleImageBean middle_image;
    private List<?> image_list;
    private List<?> filter_words;
    private List<ActionListBean> action_list;
    private List<?> large_image_list;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public int getBury_count() {
        return bury_count;
    }

    public void setBury_count(int bury_count) {
        this.bury_count = bury_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MiddleImageBean getMiddle_image() {
        return middle_image;
    }

    public void setMiddle_image(MiddleImageBean middle_image) {
        this.middle_image = middle_image;
    }

    public List<?> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<?> image_list) {
        this.image_list = image_list;
    }

    public List<?> getFilter_words() {
        return filter_words;
    }

    public void setFilter_words(List<?> filter_words) {
        this.filter_words = filter_words;
    }

    public List<ActionListBean> getAction_list() {
        return action_list;
    }

    public void setAction_list(List<ActionListBean> action_list) {
        this.action_list = action_list;
    }

    public List<?> getLarge_image_list() {
        return large_image_list;
    }

    public void setLarge_image_list(List<?> large_image_list) {
        this.large_image_list = large_image_list;
    }

    public static class MiddleImageBean {
    }

    public static class ActionListBean {
        /**
         * action : 1
         * extra : {}
         * desc :
         */

        private int action;
        private ExtraBean extra;
        private String desc;

        public int getAction() {
            return action;
        }

        public void setAction(int action) {
            this.action = action;
        }

        public ExtraBean getExtra() {
            return extra;
        }

        public void setExtra(ExtraBean extra) {
            this.extra = extra;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public static class ExtraBean {
        }
    }
}
