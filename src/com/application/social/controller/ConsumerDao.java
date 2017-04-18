package com.application.social.controller;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.application.social.model.Consumer;
import com.application.social.util.DbUtil;
import com.application.social.util.exception.ZException;

public class ConsumerDao extends BaseDao{
	public static final String LOGGER = "Consumer.class";

	public ConsumerDao() {
		super(ConsumerDao.LOGGER);
	}
	
	public boolean checkClient(String clientId,String appType) {

		boolean retValue = false;
		Session session = null;
//		info("checkClient enter");
		try {

			session = DbUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			String sql = "SELECT * FROM Consumer WHERE ClientId = :clientId and AppType = :appType";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Consumer.class);
			query.setParameter("clientId", clientId);
			query.setParameter("appType", appType);
			
			java.util.List results = (java.util.List) query.list();

			for (Iterator iterator = ((java.util.List) results).iterator(); iterator.hasNext();) {
				retValue = true;
				break;
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
//		info("checkClient exit");
		return retValue;

	}
	
	public String getClientId(String clientId,String appType) {

		int retValue = 0;
		Session session = null;
//		info("checkClient enter");
		try {

			session = DbUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			String sql = "SELECT * FROM Consumer WHERE ClientId = :clientId and AppType = :appType";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Consumer.class);
			query.setParameter("clientId", clientId);
			query.setParameter("appType", appType);
			
			java.util.List results = (java.util.List) query.list();

			if(results!=null && results.size()>0){
				retValue =  ((Consumer)results.get(0)).getId(); 
			}

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
			}
//			error("Hibernate exception: " + e.getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
//		info("checkClient exit");
		return retValue+"";

	}
}
