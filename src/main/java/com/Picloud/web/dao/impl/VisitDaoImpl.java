package com.Picloud.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Picloud.hbase.service.impl.BeanMapping;
import com.Picloud.hbase.service.impl.HbaseOperationImpl;
import com.Picloud.hbase.service.impl.ListMapping;
import com.Picloud.web.dao.IVisitDao;
import com.Picloud.web.model.Visit;

@Repository
public class VisitDaoImpl implements IVisitDao {

        @Autowired
        private HbaseOperationImpl mHbaseOperationImpl;
        
        @Autowired
        private BeanMapping mBeanMapping;
        
        @Autowired
        private ListMapping mListMapping;
        
        @Override
        public void add(Visit visit) {
                if(visit == null){
                        return;
                }
                mHbaseOperationImpl.insertData("cloud_visits", visit.getKey(), "attr", "space", visit.getSpace());
                mHbaseOperationImpl.insertData("cloud_visits", visit.getKey(), "attr", "image", visit.getImage());
                mHbaseOperationImpl.insertData("cloud_visits", visit.getKey(), "attr", "time", visit.getTime());
                mHbaseOperationImpl.insertData("cloud_visits", visit.getKey(), "attr", "ip", visit.getIp());
        }

        @Override
        public List<Visit> get(int sid, String space) {
                return mListMapping.visitListMapping(mHbaseOperationImpl.visit(sid, space));
        }

}
