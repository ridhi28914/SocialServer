package com.application.social.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.internal.BootstrapServiceRegistryImpl;
public class DbUtil {
	    private static SessionFactory sessionFactory;

	    private static SessionFactory configureSessionFactory() throws HibernateException{
	    	if (sessionFactory == null) {
	    		Configuration configuration = new Configuration();
	    		configuration.configure();
//	    		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
//	    				configuration.getProperties()).getBootstrapServiceRegistry();
	    		sessionFactory = configuration.buildSessionFactory();
//	    		serviceRegistry

	    	}
	    	return sessionFactory;
	    }

	    public static SessionFactory getSessionFactory() {
	    	return configureSessionFactory();
//	        if (sessionFactory == null)
//	            sessionFactory = createSessionFactory();
//
//	        return sessionFactory;
	    }
}

