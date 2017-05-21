package com.application.social.controller;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.application.social.model.Facebook;
import com.application.social.util.DbUtil;
import com.application.social.util.exception.ZException;

public class FacebookDao extends BaseDao {
	public static final String LOGGER = "ConsumerDao.class";

	public FacebookDao() {
		super(FacebookDao.LOGGER);
	}
	public Facebook addFacebookDetails(Facebook facebookParam) {
		Facebook facebook;
		Session session = null;
		info("addFacebookDetails enter");
		try {
			session = DbUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			facebook= facebookParam;
			session.save(facebook);

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
			}
			error("Hibernate exception: " + e.getMessage());
			facebook = null;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		info("addUserDetails exit");
		return facebook;

	}
	public Facebook getFacebookDetails(String platformId) {

		Facebook facebook= null;
		Session session = null;
		info("getFacebookDetails enter");
		try {

			session = DbUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			String sql = "SELECT * FROM Facebook WHERE platformId = :platformId";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Facebook.class);
			query.setParameter("platformId", platformId);
			java.util.List results = (java.util.List) query.list();

			for (Iterator iterator = ((java.util.List) results).iterator(); iterator.hasNext();) {
				facebook = (Facebook) iterator.next();
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
		info("getFacebookDetails exit");
		return facebook;

	}
}
