package com.Picloud.web.model;

public class UploadInfo {
        
        private int status;
        private String info;
        public UploadInfo() {
                super();
                // TODO Auto-generated constructor stub
        }
        public UploadInfo(int status, String info) {
                super();
                this.status = status;
                this.info = info;
        }
        public int getStatus() {
                return status;
        }
        public void setStatus(int status) {
                this.status = status;
        }
        public String getInfo() {
                return info;
        }
        public void setInfo(String info) {
                this.info = info;
        }
        
}
