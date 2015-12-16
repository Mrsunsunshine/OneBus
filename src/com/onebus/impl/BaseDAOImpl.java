package com.onebus.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onebus.dao.BaseDAO;

@Repository("baseDAO")
@Transactional
@SuppressWarnings("all")
public class BaseDAOImpl<T> implements BaseDAO<T> {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Serializable save(T o) {
		
		return this.getCurrentSession().save(o);
		
	}

	public void delete(T o) {
		
		Session session = this.getCurrentSession();
		Transaction tran =session.beginTransaction();
		session.delete(o);
		tran.commit();
	}

	public void update(T o) {
		Session session = this.getCurrentSession();
		Transaction tran =session.beginTransaction();
		session.update(o);
		tran.commit();
		
	}

	public List<T> find(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}
	
	public List<T> find(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}

	
	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

}
