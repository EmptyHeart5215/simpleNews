package com.example.mysimplenew.entity;

import java.util.List;

/**
 * Created by 红超 on 2017/3/28.
 */

public class PhotoEntity {
    @Override
    public String toString() {
        return "PhotoEntity{" +
                "tag=" + tag +
                '}';
    }

    private List<TagBean> tag;

    public List<TagBean> getTag() {
        return tag;
    }

    public void setTag(List<TagBean> tag) {
        this.tag = tag;
    }

    public static class TagBean {
        @Override
        public String toString() {
            return "TagBean{" +
                    "clientcover1='" + clientcover1 + '\'' +
                    ", cover='" + cover + '\'' +
                    ", createdate='" + createdate + '\'' +
                    ", datetime='" + datetime + '\'' +
                    ", desc='" + desc + '\'' +
                    ", imgsum='" + imgsum + '\'' +
                    ", pvnum='" + pvnum + '\'' +
                    ", replynum='" + replynum + '\'' +
                    ", scover='" + scover + '\'' +
                    ", setid='" + setid + '\'' +
                    ", setname='" + setname + '\'' +
                    ", seturl='" + seturl + '\'' +
                    ", tcover='" + tcover + '\'' +
                    ", topicname='" + topicname + '\'' +
                    ", clientcover='" + clientcover + '\'' +
                    ", pics=" + pics +
                    '}';
        }

        /**
         * clientcover1 : http://img4.cache.netease.com/photo/0096/2017-03-28/CGL7D3J654GI0096.jpg
         * cover : http://img4.cache.netease.com/photo/0096/2017-03-28/CGL7D3J654GI0096.jpg
         * createdate : 2017-03-28 21:55:11
         * datetime : 2017-03-28 21:55:17
         * desc : 日本北海道有一个古老的车站，列车公司原本计划关掉这座车站。却发现还有个女高中生需要乘坐它去上学，于是便保留下车站，直到女生毕业。“一个人的车站”感动了许多人，而在重庆綦江区赶水镇太公山，也有这样一所鲜为人知“一个人的学校”。陪伴这唯一的一个学生的，是一个60岁的老教师。
         * imgsum : 10
         * pics : ["http://img4.cache.netease.com/photo/0096/2017-03-28/CGL7D3J354GI0096.jpg","http://img4.cache.netease.com/photo/0096/2017-03-28/CGL7D3J454GI0096.jpg","http://img4.cache.netease.com/photo/0096/2017-03-28/CGL7D3J554GI0096.jpg"]
         * pvnum :
         * replynum : 11968
         * scover : http://img4.cache.netease.com/photo/0096/2017-03-28/s_CGL7D3J654GI0096.jpg
         * setid : 121712
         * setname : 图片故事：一个人的学校
         * seturl : http://help.3g.163.com/photoview/54GI0096/121712.html
         * tcover : http://img4.cache.netease.com/photo/0096/2017-03-28/t_CGL7D3J654GI0096.jpg
         * topicname :
         * clientcover :
         */

        private String clientcover1;
        private String cover;
        private String createdate;
        private String datetime;
        private String desc;
        private String imgsum;
        private String pvnum;
        private String replynum;
        private String scover;
        private String setid;
        private String setname;
        private String seturl;
        private String tcover;
        private String topicname;
        private String clientcover;
        private List<String> pics;

        public String getClientcover1() {
            return clientcover1;
        }

        public void setClientcover1(String clientcover1) {
            this.clientcover1 = clientcover1;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImgsum() {
            return imgsum;
        }

        public void setImgsum(String imgsum) {
            this.imgsum = imgsum;
        }

        public String getPvnum() {
            return pvnum;
        }

        public void setPvnum(String pvnum) {
            this.pvnum = pvnum;
        }

        public String getReplynum() {
            return replynum;
        }

        public void setReplynum(String replynum) {
            this.replynum = replynum;
        }

        public String getScover() {
            return scover;
        }

        public void setScover(String scover) {
            this.scover = scover;
        }

        public String getSetid() {
            return setid;
        }

        public void setSetid(String setid) {
            this.setid = setid;
        }

        public String getSetname() {
            return setname;
        }

        public void setSetname(String setname) {
            this.setname = setname;
        }

        public String getSeturl() {
            return seturl;
        }

        public void setSeturl(String seturl) {
            this.seturl = seturl;
        }

        public String getTcover() {
            return tcover;
        }

        public void setTcover(String tcover) {
            this.tcover = tcover;
        }

        public String getTopicname() {
            return topicname;
        }

        public void setTopicname(String topicname) {
            this.topicname = topicname;
        }

        public String getClientcover() {
            return clientcover;
        }

        public void setClientcover(String clientcover) {
            this.clientcover = clientcover;
        }

        public List<String> getPics() {
            return pics;
        }

        public void setPics(List<String> pics) {
            this.pics = pics;
        }
    }
}