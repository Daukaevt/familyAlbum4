package com.wixsite.mupbam1.controllers;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.wixsite.mupbam1.models.Pics;
import com.wixsite.mupbam1.services.PhotoService;
import com.wixsite.mupbam1.utils.HibernateUtil;

@Controller
public class PhotoController {
	@Autowired
	private PhotoService photoService;
	
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@GetMapping("/")
	public String getIndex(Model model) {
		model.addAttribute("photos", photoService.getAllPhotos());
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
	@GetMapping("/user-info")
	public String getUserInfo(Authentication authentication, Model model) {
		 if (authentication.getPrincipal() instanceof OAuth2User oauth2User) {
	            String uniqueUser;
	            if (photoService.googleUser(oauth2User)) {
	            	uniqueUser = oauth2User.getAttribute("sub");
	            	model.addAttribute("sub", uniqueUser);
	            	model.addAttribute("name", oauth2User.getAttribute("name"));
	            	model.addAttribute("given_name", oauth2User.getAttribute("given_name"));
	            	model.addAttribute("family_name", oauth2User.getAttribute("family_name"));
	            	model.addAttribute("picture", oauth2User.getAttribute("picture"));
	            	model.addAttribute("email", oauth2User.getAttribute("email"));
	            	model.addAttribute("email_verified", oauth2User.getAttribute("email_verified"));
	            	model.addAttribute("locale", oauth2User.getAttribute("locale"));
	            } else if (photoService.githubUser(oauth2User)) {
	            	Integer id = oauth2User.getAttribute("id");
	            	uniqueUser = id.toString();
	            	model.addAttribute("login", oauth2User.getAttribute("login"));
	            	model.addAttribute("id", uniqueUser);
	            	model.addAttribute("avatar_url", oauth2User.getAttribute("avatar_url"));
	            	model.addAttribute("name", oauth2User.getAttribute("name"));
	            	model.addAttribute("email", oauth2User.getAttribute("email"));
	            	model.addAttribute("bio", oauth2User.getAttribute("bio"));
	            } else {
	            	return "/logout";
	            }
	        } else {
	        	return "/logout";
	        }
		
	    return "user";
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
        	Pics pic = photoService.getPhotoById(10000000000L);
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
