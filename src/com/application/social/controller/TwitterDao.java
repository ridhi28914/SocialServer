package com.application.social.controller;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.application.social.model.Twitter;
import com.application.social.model.User;
import com.application.social.util.DbUtil;
import com.application.social.util.exception.ZException;

public class TwitterDao extends BaseDao {


	public static final String LOGGER = "ConsumerDao.class";

	public TwitterDao() {
		super(TwitterDao.LOGGER);
	}
	public Twitter addTwitterDetails(Twitter twitterParam) {
		Twitter twitter ;
		Session session = null;
		info("addTwitterDetails enter");
		try {
			session = DbUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			twitter = twitterParam;
			session.save(twitter);

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
			}
			error("Hibernate exception: " + e.getMessage());
			twitter = null;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		info("addUserDetails exit");
		return twitter;

	}
	public Twitter getTwitterDetails(String platformId) {

		Twitter twitter = null;
		Session session = null;
		info("getTwitterDetails enter");
		try {

			session = DbUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			String sql = "SELECT * FROM Twitter WHERE platformId = :platformId";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Twitter.class);
			query.setParameter("platformId", platformId);
			java.util.List results = (java.util.List) query.list();

			for (Iterator iterator = ((java.util.List) results).iterator(); iterator.hasNext();) {
				twitter = (Twitter) iterator.next();
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
		return twitter;

	}
		
}
