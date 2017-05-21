package com.application.social.controller;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.application.social.model.ConnectedAccounts;
import com.application.social.model.Pinterest;
import com.application.social.util.DbUtil;
import com.application.social.util.exception.ZException;

public class ConnectedAccountsDao extends BaseDao{
	public static final String LOGGER = "ConsumerDao.class";
	public ConnectedAccountsDao() {
		
		super(ConnectedAccountsDao.LOGGER);
		// TODO Auto-generated constructor stub
	}

	public ConnectedAccounts addConnectedAccountsDetails(ConnectedAccounts connectedAccountsParam) {
		ConnectedAccounts connectedAccounts=null;
		Session session = null;
		info("addConnectedAccountsDetails enter");
		try {
			session = DbUtil.getSessionFactory().openSession();

			Transaction transaction = session.beginTransaction();
			connectedAccounts= connectedAccountsParam;
			session.save(connectedAccounts);

			transaction.commit();
			session.close();

		} catch (HibernateException e) {
			try {
				throw new ZException("Error", e);
			} catch (ZException e1) {
				e1.printStackTrace();
			}
			error("Hibernate exception: " + e.getMessage());
			connectedAccounts = null;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		info("addConnectedAccountsDetails exit");
		return connectedAccounts;

	}
	public ConnectedAccounts getConnectedAccountsDetails(int userId){
		ConnectedAccounts connectedAccounts= null;
		Session session = null;
		info("getConnectedAccountsDetails enter");
		try {

			session = DbUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			String sql = "SELECT * FROM ConnectedAccounts WHERE userId = :userId";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(ConnectedAccounts.class);
			query.setParameter("userId", userId);
			java.util.List results = (java.util.List) query.list();

			for (Iterator iterator = ((java.util.List) results).iterator(); iterator.hasNext();) {
				connectedAccounts = (ConnectedAccounts) iterator.next();
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
		info("getConnectedAccountsDetails exit");
		return connectedAccounts;
		
	}
	public ConnectedAccounts updateConnectedAccountsDetails(ConnectedAccounts connectedAccountsParam,int source){
		ConnectedAccounts connectedAccounts= null;
		Session session = null;
		info("updateConnectedAccountsDetails enter");
		try {
			session = DbUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			
			int userId = connectedAccountsParam.getUserId(); 
			String sql = "SELECT * FROM ConnectedAccounts WHERE userId = :userId";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(ConnectedAccounts.class);
			query.setParameter("userId", userId);
			java.util.List results = (java.util.List) query.list();

			ConnectedAccounts cA = ( ConnectedAccounts ) results.get(0);
			if(cA!=null){
				if(source == 1)
					cA.setPinterestId(connectedAccountsParam.getPinterestId());
				else if(source==0)
					cA.setTwitterId(connectedAccountsParam.getTwitterId());
				else if(source==2)
					cA.setFacebookId(connectedAccountsParam.getFacebookId());
				session.update(cA);
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
		info("getConnectedAccountsDetails exit");
		return connectedAccounts;
		
	}
}
