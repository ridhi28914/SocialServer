package com.application.social.controller;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.application.social.controller.BaseDao;
import com.application.social.model.Pinterest;
import com.application.social.model.User;
import com.application.social.util.DbUtil;
import com.application.social.util.exception.ZException;

public class PinterestDao extends BaseDao {
	public static final String LOGGER = "ConsumerDao.class";

	public PinterestDao() {
		super(PinterestDao.LOGGER);
	}
	public Pinterest addPinterestDetails(Pinterest pinterestParam) {
		Pinterest pinterest;
		Session session = null;
		info("addPinterestDetails enter");
		try {
			session = DbUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			pinterest= pinterestParam;
			session.save(pinterest);

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
			}
			error("Hibernate exception: " + e.getMessage());
			pinterest = null;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		info("addUserDetails exit");
		return pinterest;

	}
	public Pinterest getPinterestDetails(String platformId) {

		Pinterest pinterest= null;
		Session session = null;
		info("getTwitterDetails enter");
		try {

			session = DbUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			String sql = "SELECT * FROM Pinterest WHERE platformId = :platformId";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Pinterest.class);
			query.setParameter("platformId", platformId);
			java.util.List results = (java.util.List) query.list();

			for (Iterator iterator = ((java.util.List) results).iterator(); iterator.hasNext();) {
				pinterest = (Pinterest) iterator.next();
			}

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
			}
			error("Hibernate exception: " + e.getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		info("getTwitterDetails exit");
		return pinterest;

	}
}
