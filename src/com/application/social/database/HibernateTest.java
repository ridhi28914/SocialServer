package com.application.social.database;

import org.hibernate.Session;
import com.application.social.model.User;
import com.application.social.util.DbUtil;

public class HibernateTest {
	
	public static void main(String[] args) {
		
		User user = new User();
		user.setUserName("First user");
		User user2 = new User();
		user2.setUserName("Second user");
		
		DbUtil dbu= new DbUtil();
		Session session = dbu.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(user);
		session.save(user2);
		session.getTransaction().commit();
		
//		session.close();
//		
//		Session sesssion= dbu.getSessionFactory().openSession();
//		session.beginTransaction();
		user=(User) session.get(User.class, 1);
		System.out.println(user.getUserName());
		session.delete(user);
		session.close();
	}
}