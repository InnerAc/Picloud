package com.Picloud.web.model;

public class Visit {
        
        public String key;
        public String space;
        public String image;
        public String time;
        public String ip;
        public Visit() {
                super();
                // TODO Auto-generated constructor stub
        }
        public Visit(String key, String space, String image, String time,
                        String ip) {
                super();
                this.key = key;
                this.space = space;
                this.image = image;
                this.time = time;
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
