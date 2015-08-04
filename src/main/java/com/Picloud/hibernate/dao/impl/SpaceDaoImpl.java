package com.Picloud.hibernate.dao.impl;

import java.util.List;

import org.apache.hadoop.hbase.client.ResultScanner;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Picloud.hbase.service.impl.BeanMapping;
import com.Picloud.hbase.service.impl.HbaseOperationImpl;
import com.Picloud.hbase.service.impl.ListMapping;
import com.Picloud.hibernate.dao.ISpaceDao;
import com.Picloud.hibernate.entities.Space;
import com.Picloud.web.model.Image;
@Repository
public class SpaceDaoImpl extends BaseDao implements ISpaceDao {
        
        @Autowired
        private HbaseOperationImpl mHbaseOperationImpl;
        
        @Autowired
        private BeanMapping mBeanMapping;
        
        @Autowired
        private ListMapping mListMapping;

        @Override
        public void add(Space space) {
                save(space);
        }

        @Override
        public void delete(Space space) {
                delete(space);
        }

        @Override
        public void delete(int sid) {
                String hql = "delete Space as space where space.sid = ?";
                Query query = query(hql).setInteger(0, sid);
                query.executeUpdate();
        }

        @Override
        public Space find(int sid) {
                return get(Space.class, sid);
        }

        @Override
        public List<Space> load(int uid) {
                String hql = "from Space as space where space.user.uid = ?";
                Query query = query(hql).setInteger(0, uid);
                return query.list();
        }

        @Override
        public void update(Space space) {
                update(space);
        }

        @Override
        public List<Image> search(int uid, String space, String subStr) {
                ResultScanner rs = mHbaseOperationImpl.imageNameMatching(String.valueOf(uid), space, subStr);
                return mListMapping.imageListMapping(rs);
        }

        @Override
        public Space getByName(String name) {
                String hql = "from Space as space where space.name = ?";
                Query query = query(hql).setString(0, name);
                List<Space> spaces = query.list();
                if(spaces.size() == 0 || spaces == null)
                        return null;
                return spaces.get(0);
        }

        @Override
        public void delete(String name, int uid) {
                String hql = "delete Space as space where space.name = ? and space.user.uid = ?";
                Query query = query(hql).setString(0, name).setInteger(1, uid);
                query.executeUpdate();
        }

        @Override
        public void addStorage(int key, double imageSize) {
                String hql = "update Space as space set space.number = space.number + 1, space.storage = space.storage + ? where space.sid = ?";
                Query query = query(hql).setDouble(0, imageSize).setInteger(1, key);
                query.executeUpdate();
        }

        @Override
        public void reduceStorage(int key, double imageSize) {
                String hql = "update Space as space set space.number = space.number - 1, space.storage = space.storage - ? where space.sid = ?";
                Query query = query(hql).setDouble(0, imageSize).setInteger(1, key);
                query.executeUpdate();
        }

}
