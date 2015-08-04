package com.Picloud.hibernate.dao;

import java.util.List;

import com.Picloud.hibernate.entities.Space;
import com.Picloud.web.model.Image;

public interface ISpaceDao {
	public void add(Space space);
	public void delete(Space space);
	public void delete(int sid);
	public Space find(int sid);
	public List<Space> load(int uid);
	public List<Space> load(int uid, int num);
	public void update(Space space);
	public List<Image> search(int uid, String space, String subStr);
	public Space getByName(String name);
	public void delete(String name, int uid);
        public void addStorage(int key, double imageSize);
        public void reduceStorage(int key, double imageSize);
}
