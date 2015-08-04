package com.Picloud.hibernate.dao;

import com.Picloud.hibernate.entities.User;

public interface IUserDao {
        public void add(User user);
        public void addSpaceNum(int uid);
        public void reduceSpaceNum(int uid);
        public void delete(int uid);
        public User find(int uid);
        public User validate(String email);
        public void addImage(int uid, int imageNum, double imageSize);
        public void reduceImage(int uid, int imageNum, double imageSize);
        public void updateTextLogo(int uid, String text);
        public void updateImageLogo(int uid, String image);
}
