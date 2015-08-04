package com.Picloud.web.model;

import com.Picloud.utils.EncryptUtil;
import com.Picloud.utils.JspUtil;

public class Visit {
        
        public String key;
        public String space;
        public String image;
        public String time;
        public String ip;
        public Visit() {
                super();
        }
        //务必使用此构造方法
        public Visit(int sid, String space, String image, 
                        String ip) {
                super();
                try {
                        String max = "99999999999999999";
                        double d1 =   Double.parseDouble(max);
                        double d2 = Double.parseDouble(JspUtil.getCurrentDateMS());
                        this.key = EncryptUtil.spaceEncryptKey(sid, space)+String.valueOf(d1-d2);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                this.space = space;
                this.image = image;
                this.time = JspUtil.getCurrentDateStr();
                this.ip = ip;
        }
        public String getKey() {
                return key;
        }
        public void setKey(String key) {
                this.key = key;
        }
        public String getSpace() {
                return space;
        }
        public void setSpace(String space) {
                this.space = space;
        }
        public String getImage() {
                return image;
        }
        public void setImage(String image) {
                this.image = image;
        }
        public String getTime() {
                return time;
        }
        public void setTime(String time) {
                this.time = time;
        }
        public String getIp() {
                return ip;
        }
        public void setIp(String ip) {
                this.ip = ip;
        }
}
