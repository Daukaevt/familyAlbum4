package com.wixsite.mupbam1.controllers;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.wixsite.mupbam1.models.Pics;
import com.wixsite.mupbam1.utils.HibernateUtil;

@Controller
public class PhotoController {
	
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}
	@GetMapping("/secured")
	public String getSecured() {
		return "secured";
	}
	@GetMapping("/save")
	public String savePhoto() {
		Pics newPhoto = new Pics();
        newPhoto.setDescription("testDescription");
        newPhoto.setUrl("testUrl");
        
        try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.persist(newPhoto);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
        
        System.out.println(newPhoto.toString());
        
        
        
        return "saved";
	}


}
