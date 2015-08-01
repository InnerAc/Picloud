package com.Picloud.hibernate.dao;

import com.Picloud.hibernate.entities.User;

public interface IUserDao {
        public void add(User user);
        public void update(User user);
        public void delete(int uid);
        public User find(int uid);
        public User validate(String email);
}
