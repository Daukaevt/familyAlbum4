package com.wixsite.mupbam1.controllers;

import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.wixsite.mupbam1.models.Pics;
import com.wixsite.mupbam1.repository.PhotoRepository;
import com.wixsite.mupbam1.utils.HibernateUtil;

@Controller
public class PhotoController {
	@Autowired
	private PhotoRepository photoRepository;
	
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}
	@GetMapping("/album")
	public String getAlbum() {
		return "album";
	}
	@GetMapping("/all-together")
	public String getAllTogether() {
		return "all_together";
	}
	@GetMapping("/all-together1")
	public String getAllTogether1() {
		return "all_together1";
	}
	@GetMapping("/mom")
	public String getMomsPage() {
		return "mom";
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
        	Optional<Pics> pic = photoRepository.findById(10000000000L);
            System.out.println(pic.toString());
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			//session.persist(newPhoto);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
        
        System.out.println(newPhoto.toString());
        
        
        
        return "saved";
	}


}
