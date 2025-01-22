package com.wixsite.mupbam1.controllers;

import java.util.Objects;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wixsite.mupbam1.models.Pics;
import com.wixsite.mupbam1.services.PhotoService;
import com.wixsite.mupbam1.utils.HibernateUtil;

@Controller
@RequestMapping("/album")
public class PhotoController {
	@Autowired
	private PhotoService photoService;
	//private OAuth2UserUtil oauth2User;
	
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Pics pic;
	
	@GetMapping("/")
	public String getIndex(Model model) {
		model.addAttribute("photos", photoService.getAllPhotos());		
		return "album/index";
	}
	@GetMapping()
	public String getAlbums() {
		return "album/album";
	}

	@GetMapping("/img4")
	public String getAllTogether(Model model, Authentication authentication) {
		model.addAttribute("photos", photoService.getAllPhotos());
		String username = authentication.getPrincipal() instanceof OAuth2User oauth2User
				? photoService.getOwner(oauth2User)
						: "unknown_user";
		model.addAttribute("username", username);
		model.addAttribute("pic", new Pics());
		
		return "album/img4";
	}
    @PostMapping("/img4")
    public String addPhoto(
    		/*@RequestParam String url, @RequestParam String description, */ 
    		Authentication authentication, @ModelAttribute("pic") Pics pic) {
        //pic.add(new Pics(url, description));
    	String owner = authentication.getPrincipal() instanceof OAuth2User oauth2User
				? photoService.getOwner(oauth2User)
						: "unknown_user";
    	System.out.println("********************" + pic.getDescription());
    	pic.setDescription(pic.getDescription());
    	pic.setUrl(pic.getUrl());
    	pic.setOwner_key(owner);
    	
    	photoService.savePhoto(pic);
    	
        return "redirect:/album"; // перенаправление на главную страницу
    }
	@GetMapping("/all-together1")
	public String getAllTogether1() {
		return "album/all_together1";
	}
	@GetMapping("/mom")
	public String getMomsPage() {
		return "album/mom";
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
		
	    return "album/user";
	}
	@GetMapping("/secured")
	public String getSecured() {
		return "album/secured";
	}
	@GetMapping("/save")
	public String savePhoto(Authentication authentication, Model model) {
		Pics newPhoto = new Pics();
		if (authentication.getPrincipal() instanceof OAuth2User oauth2User) {
        newPhoto.setDescription("в лесу.");
        newPhoto.setUrl("https://iili.io/2WzifmG.jpg");
        newPhoto.setOwner_key(photoService.getOwner(oauth2User));
        
        try {
        	Pics pic = photoService.getPhotoById(10000001L);
            System.out.println(pic.toString());
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			//session.persist(newPhoto);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
        
        System.out.println(newPhoto.toString());
        
		}
        
        return "album/saved";
	}
	
	@GetMapping("/test3")
	public String getTest3(Model model) {
		model.addAttribute("photos", photoService.getAllPhotos().stream().filter(Objects::nonNull).toList());
		model.addAttribute("newPhoto", new Pics());
		return "album/test3";
	}
	@GetMapping("/test4")
	public String getTest4(Model model) {
		model.addAttribute("photos", photoService.getAllPhotos().stream().filter(Objects::nonNull).toList());
		model.addAttribute("newPhoto", new Pics());
		return "album/test4";
	}
	/*
	@ModelAttribute("principal")
	public String populatePrincipalName(Authentication authentication) {
		return (authentication.getPrincipal() instanceof OAuth2User oauth2User)
				? photoService.getOwner(oauth2User) :"unknown_owner";
	}
	*/
	@GetMapping("/test7")
	public String getTest5(Model model) {
		model.addAttribute("photos", photoService.getAllPhotos().stream().filter(Objects::nonNull).toList());
		model.addAttribute("newPhoto", new Pics());
		return "album/test7";
	}
	@GetMapping("/collage")
	public String getCollage(Model model, Authentication authentication) {
		/*
		model.addAttribute("photos",
				photoService.getAlbumByUserKey((authentication.getPrincipal() instanceof OAuth2User oauth2User)
						? photoService.getOwner(oauth2User)
								:"unknown_owner")
				);
				*/
		model.addAttribute("photos", photoService.getAllPhotos());
		String username = authentication.getPrincipal() instanceof OAuth2User oauth2User
				? photoService.getOwner(oauth2User)
						: "unknown_user";
		model.addAttribute("username", username);
		return "album/collage";
	}
	//@PostMapping("/add-photo")
	public String createNewPhoto(@ModelAttribute("newPhoto") Pics newPhoto, Authentication authentication) {
		String owner = authentication.getPrincipal() instanceof OAuth2User oauth2User
				? photoService.getOwner(oauth2User)
						: "unknown_user";
		newPhoto.setOwner_key(owner);
		photoService.savePhoto(newPhoto);
		return "redirect:/album/collage";
	}
    @PostMapping("/edit-photo")
    public String editPhoto(@RequestParam("originalUrl") String originalUrl, @RequestParam("url") String url, @RequestParam("description") String description) {
    	System.out.println("EEEEEEEEEEEEDDDDDIIIIIIIIIITTTTTTTTTTEEEEEDDDDDDDDD!");
        return "redirect:/album/collage";
    }
	 @DeleteMapping("/delete-photo")
	    public String deletePhoto(@PathVariable("id") Long id) {
		 System.out.println("DDDDDEEEEEEELLLLLLLLLLLEEEEETTTTTTTTTTEEEEEDDDDDDDDD!" + id);
		 // photoService.deleteUser(id);
	     /*   
		 photos = photos.stream()
	                .filter(photo -> !photo.getUrl().equals(url))
	                .collect(Collectors.toList());
	                */
	        return "redirect:/album/collage";
	    }
	

}
