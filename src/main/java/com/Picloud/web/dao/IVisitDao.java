package com.Picloud.web.dao;

import java.util.List;

import com.Picloud.web.model.Visit;

public interface IVisitDao {
        
        public void add(Visit visit);
        
        public List<Visit> get(int sid, String space);

}
