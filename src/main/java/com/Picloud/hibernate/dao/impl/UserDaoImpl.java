package com.Picloud.hibernate.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.Picloud.hibernate.dao.IUserDao;
import com.Picloud.hibernate.entities.User;

@Repository
public class UserDaoImpl extends BaseDao implements IUserDao {

        @Override
        public void add(User user) {
                save(user);
        }

        @Override
        public void update(User user) {
               update(user);
        }

        @Override
        public void delete(int uid) {
               String hql = "delete User as user where user.uid = ?";
               Query query = query(hql).setInteger(0, uid);
               query.executeUpdate();
        }

        @Override
        public User find(int uid) {
                return get(User.class, uid);
        }

        @Override
        public User validate(String email) {
               String hql = "from User as user where user.email = ?";
               Query query = query(hql).setString(0, hql);
               List<User> users = query.list();
               if(users != null || users.size() != 0)
                       return users.get(0);
               return null;
        }

}
