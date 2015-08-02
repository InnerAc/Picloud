package com.Picloud.hibernate.dao;

import com.Picloud.hibernate.entities.User;

public interface IUserDao {
        public void add(User user);
        public void updateSpaceNum(int uid);
        public void delete(int uid);
        public User find(int uid);
        public User validate(String email);
        public void updateImage(int uid, int imageNum, double imageSize);
}
