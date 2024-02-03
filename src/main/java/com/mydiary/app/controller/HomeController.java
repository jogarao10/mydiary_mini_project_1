package com.mydiary.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mydiary.app.entity.Entry;
import com.mydiary.app.entity.User;
import com.mydiary.app.service.EntryService;
import com.mydiary.app.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	

	@Autowired
	private EntryService entryService;
	
	
	

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public EntryService getEntryService() {
		return entryService;
	}

	public void setEntryService(EntryService entryService) {
		this.entryService = entryService;
	}

	@Autowired
	HttpSession session;
	


	@GetMapping("home")
	public String homepage() {

		String model = new String("loginpage");

		return model;
	}

	@GetMapping("register")
	public String registartionpage() {

		String model = new String("registrationpage");

		return model;
	}

	@PostMapping(value = "saveuser")
	public String saveUser(@ModelAttribute("user") User user) {

		String model = new String("registersuccess");

		userService.saveUser(user);

		return model;
	}

	@PostMapping(value = "/authenticate")
	public String authenticateuser(@ModelAttribute("user") User user, Model model) {

		String viewname = "loginpage";

		User user1 = userService.findByUserName(user.getUsername());

		if (user1 != null && user.getPassword().equals(user1.getPassword())) {

			viewname = "loginhomepage";
			model.addAttribute("user", user1);

			session.setAttribute("user", user1);

			List<Entry> entries = null;

			try {
				entries = entryService.findByUserId(user1.getId());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			model.addAttribute("entrieslist", entries);

		}

		return viewname;

	}

	@GetMapping("addentry")
	public String addentry() {
		String model = new String("addentryform");

		return model;
	}

	@GetMapping("saveentry")
	public String saveentry(@ModelAttribute("entry") Entry entry, Model model) {
		String viewname = "userhomepage";

		entryService.saveEntry(entry);

		User user1 = (User) session.getAttribute("user");

		List<Entry> entries = null;

		try {
			entries = entryService.findByUserId(user1.getId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		model.addAttribute("entrieslist", entries);

		return viewname;
	}

	@GetMapping("viewentry")
	public String viewentry(@RequestParam("id") Long id, Model model) {
		String viewname = "displayentry";

		Entry entry = entryService.findById(id);

		model.addAttribute("entry", entry);

		return viewname;
	}

	@GetMapping("userhome")
	public String userhomepage(Model model) {

		String viewname = "userhomepage";

		User user1 = (User) session.getAttribute("user");

		List<Entry> entries = null;

		try {
			entries = entryService.findByUserId(user1.getId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		model.addAttribute("entrieslist", entries);

		return viewname;
	}

	@GetMapping("updateentry")
	public String updateentry(@RequestParam("id") Long id, Model model) {
		String viewname = "displayupdateentry";

		Entry entry = entryService.findById(id);

		model.addAttribute("entry", entry);

		User user1 = (User) session.getAttribute("user");
		if (user1 == null)
			viewname = "loginpage";

		return viewname;
	}

	@GetMapping("processentryupdate")
	public String processentryupdate(@ModelAttribute("entry") Entry entry, Model model) {
		String viewname = "userhomepage";

		entryService.updateEntry(entry);

		User user1 = (User) session.getAttribute("user");
		List<Entry> entries = null;

		try {
			entries = entryService.findByUserId(user1.getId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		model.addAttribute("entrieslist", entries);

		return viewname;
	}

	@GetMapping("deleteentry")
	public String deleteentry(@RequestParam("id") Long id, Model model) {
		String viewname = "userhomepage";

		User user1 = (User) session.getAttribute("user");

		Entry entry = entryService.findById(id);

		if (user1 == null)
			viewname = "loginpage";
		else
			entryService.deleteEntry(entry);

		List<Entry> entries = null;

		try {
			entries = entryService.findByUserId(user1.getId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		model.addAttribute("entrieslist", entries);

		return viewname;
	}

	@GetMapping("signout")
	public String signout() {
		String model = new String("loginpage");

		session.invalidate();

		return model;
	}

}
