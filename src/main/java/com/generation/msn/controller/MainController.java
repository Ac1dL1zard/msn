package com.generation.msn.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.generation.msn.model.entities.Friendship;
import com.generation.msn.model.entities.Message;
import com.generation.msn.model.entities.User;
import com.generation.msn.model.repository.FriendshipRepository;
import com.generation.msn.model.repository.MessageRepository;
import com.generation.msn.model.repository.UserRepository;

@Controller
@SessionAttributes("user")
public class MainController 
{
	@Autowired
	UserRepository userRepo;
	@Autowired
	FriendshipRepository friendRepo;
	@Autowired
	MessageRepository messRepo;
	
	@GetMapping("/")
	public String initialPage(Model model)
	{
		User currentUser = (User) model.getAttribute("user");
		if(currentUser==null || currentUser.getMail()==null)
		{
			return "loginform";
		}
		return "redirect:/homepage";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("credentials") User user, Model model)
	{
		User onDb = userRepo.findByMail(user.getMail());
		if(onDb==null || !onDb.getPassword().equals(user.getPassword()))
		{	
			model.addAttribute("errormessage", "problemi con username o password");
			return "loginform";
		}
		model.addAttribute("user", onDb);
		return "redirect:/";
	}
	
	@GetMapping("/homepage")
	public String homepage()
	{
		return "homepage";
	}
	
	@GetMapping("/openchat")
	public String openchat(@RequestParam int id, Model model)
	{
		User current = (User)model.getAttribute("user");
		User friend = userRepo.findById(id).get();
		Friendship f = friendRepo.findByUser1AndUser2(current,friend);
		List<Message> messages = f.getMessages();
		model.addAttribute("messages", messages);
		return "homepage";
	}
	
	@PostMapping("/sendmessage")
	public String sendmessage(@ModelAttribute("message") Message message, Model model)
	{
		messRepo.save(message);
		model.addAttribute("id", message.getFriendship().getUser2());
		return "redirect:/openchat";
	}
	
	@GetMapping("/addfriend")
	public String addFriend()
	{
		return "addform";
	}
	
	@PostMapping("/addfriend")
	public String saveFriend(@RequestParam String mail, Model model)
	{
		User newFriend = userRepo.findByMail(mail);
		if(newFriend==null)
		{
			model.addAttribute("errormessage", "Non esiste uno user con questa mail");
			return "addform";
		}
		User current = (User)model.getAttribute("user");
		for(User u : newFriend.getAllFriends())
		{
			if(current.getId()==u.getId())
			{
				model.addAttribute("errormessage", "Siete già amici");
				return "addform";
			}
		}
		Friendship f = new Friendship();
		f.setStart_date_time(LocalDateTime.now());
		f.setUser1(current);
		f.setUser2(newFriend);
		friendRepo.save(f);
		model.addAttribute("successmessage", "Amico aggiunto con successo");
		current.getFriendshipsSender().add(f);
		return "redirect:/";
	}
	
	@GetMapping("/register")
	public String register()
	{
		return "registration";
	}
	
	@PostMapping("/addnewuser")
	public String addNewUser(@ModelAttribute("newuser") User user, Model model)
	{
		User onDb = userRepo.findByMail(user.getMail());
		if(onDb!=null)
		{
			model.addAttribute("errormessage", "Profilo già esistente");
			return "registration";
		}
		user = userRepo.save(user);
		User u=userRepo.findById(user.getId()).get();
		System.out.println(user.toString());
		model.addAttribute("user", u);
		return "redirect:/";
	}
}
