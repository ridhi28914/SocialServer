package com.application.social.controller;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.application.social.model.User;
import com.application.social.util.DbUtil;
import com.application.social.util.exception.ZException;

public class UserDao extends BaseDao {
	public static final String LOGGER = "ConsumerDao.class";

	public UserDao() {
		super(UserDao.LOGGER);
	}
	
	public User addUserDetails(User userParam) {
		User user;
		Session session = null;
		info("addUserDetails enter");
		try {
			session = DbUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			user = userParam;
			session.save(user);

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
			}
			error("Hibernate exception: " + e.getMessage());
			user = null;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		info("addUserDetails exit");
		return user;

	}
	/**
	 * Get user details based on the facebook id
	 */
	public User getUserDetails(String fbGoId) {

		User user = null;
		Session session = null;
		info("getUserDetails enter");
		try {

			session = DbUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			String sql = "SELECT * FROM User WHERE fbGoId = :fbGoId";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(User.class);
			query.setParameter("fbGoId", fbGoId);
			java.util.List results = (java.util.List) query.list();

			for (Iterator iterator = ((java.util.List) results).iterator(); iterator.hasNext();) {
				user = (User) iterator.next();
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
		info("getUserDetails exit");
		return user;

	}
	
	public User getUserDetailsFromEmail(String email) {

		User user = null;
		Session session = null;
		info("getUserDetails enter");
		try {

			session = DbUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			String sql = "SELECT * FROM User WHERE Email = :email";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(User.class);
			query.setParameter("email", email);
			java.util.List results = (java.util.List) query.list();

			for (Iterator iterator = ((java.util.List) results).iterator(); iterator.hasNext();) {
				user = (User) iterator.next();
			}

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			try {
				throw new ZException("Error in", e);
			} catch (ZException e1) {
				e1.printStackTrace();
			}
			error("Hibernate exception: " + e.getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		info("getUserDetails exit");
		return user;

	}
	
	public User userActive(String accessToken) {

		User user = null;
		Session session = null;
		info("userActive enter");
		try {
			session = DbUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();

			String sql = "SELECT * FROM User Where User.UserId = (Select Session.UserId From Session WHERE AccessToken = :access_token)";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(User.class);
			query.setParameter("access_token", accessToken);
			java.util.List results = (java.util.List) query.list();

			if (results != null && results.size() > 0) {
				user = (User) results.get(0);
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
		info("userActive exit");
		return user;
	}
}
