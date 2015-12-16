package com.onebus.dao;

import java.io.Serializable;
import java.util.List;

import com.onebus.beans.User;

public interface BaseDAO<T> {

	public Serializable save(T o);

	public void delete(T o);

	public void update(T o);

	public List<T> find(String hql, Object[] param);
	
	public List<T> find(String hql);

	public T get(Class<T> c, Serializable id);

}
