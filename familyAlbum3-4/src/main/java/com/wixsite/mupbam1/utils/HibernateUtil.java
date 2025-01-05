package com.wixsite.mupbam1.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.wixsite.mupbam1.models.Pics;


public class HibernateUtil {
	private static SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Pics.class)
					.buildSessionFactory();
		} catch (Exception e) {
			System.out.println("EXCEPTION " + e);			
		}
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
