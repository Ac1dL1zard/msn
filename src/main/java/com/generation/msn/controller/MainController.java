package com.generation.msn.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.generation.msn.model.entities.Friendship;
import com.generation.msn.model.entities.GroupChat;
import com.generation.msn.model.entities.Message;
import com.generation.msn.model.entities.User;
import com.generation.msn.model.repository.FriendshipRepository;
import com.generation.msn.model.repository.GroupChatRepository;
import com.generation.msn.model.repository.MessageRepository;
import com.generation.msn.model.repository.UserRepository;

@Controller
@SessionAttributes({"user", "cmd"})
public class MainController 
{
	@Autowired
	UserRepository userRepo;
	@Autowired
	FriendshipRepository friendRepo;
	@Autowired
	MessageRepository messRepo;
	@Autowired
	GroupChatRepository groupRepo;
	
	
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
	
	@GetMapping("changechatview")
	public String chengeChatView(@RequestParam String cmd, Model model)
	{
		model.addAttribute("cmd", cmd);
		return "homepage";
	}
	
	@GetMapping("/openchat")
	public String openchat(@RequestParam int id, Model model)
	{
		User current = (User)model.getAttribute("user");
		User friend = userRepo.findById(id).get();
		Friendship f = friendRepo.findByUser1AndUser2(current,friend);
		List<Message> messages = f.getMessages();
		model.addAttribute("friendship", f);
		model.addAttribute("messages", messages);
		
 		return "homepage";
	}
	
	@GetMapping("/opengroupchat")
	public String openGroupChat(@RequestParam int id, Model model)
	{
		GroupChat group = groupRepo.findById(id).get();
		List<Message> messages = group.getGroupmessages();
		model.addAttribute("messages", messages);
		return "homepage";
	}
	
	@PostMapping("/sendmessage")
	public String sendmessage(@RequestParam("id_f") Integer id, @ModelAttribute("message") Message message, Model model)
	{
		Friendship f = friendRepo.findById(id).get();
		
		message.setFriendship(f);
		message.setSending_date_time(LocalDateTime.now());
		f.getMessages().add(message);
		
		messRepo.save(message);
		return "redirect:/openchat?id=" + message.getFriendship().getUser2().getId();
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
	
	@GetMapping("/logout")
	public String logout(Model model)
	{
		model.addAttribute("user", new User());
		return "redirect:/";
	}
	
	@GetMapping("/creategroup")
	public String createGroup()
	{
		return"groupform";
	}
	
	@PostMapping("/savegroupform")
	public String saveGroup(@ModelAttribute("group") GroupChat group ,Model model)
	{
		User user = (User)model.getAttribute("user");
		group.setCreationDate(LocalDate.now());
		boolean saved = group.addUser(user);
		if(saved)
		{
			groupRepo.save(group);
			model.addAttribute("successmessage", "Gruppo creato con successo");
		}
		else
		{
			model.addAttribute("errormessage", "Non sono riuscito a creare il gruppo :(");
		}
		
		return "redirect:/";
	}
	
	@GetMapping("addtogroup")
	public String addToGroup(@RequestParam int id, Model model)
	{
		model.addAttribute("idgroup", id);
		return "addusertogroupform";
	}
	
	@PostMapping("saveusertogroupform")
	public String saveToGroup(@RequestParam int id ,@RequestParam String mail, Model model)
	{
		User newU = userRepo.findByMail(mail);
		if(newU==null)
		{
			model.addAttribute("errormessage", "Non esiste uno user con questa mail");
			return "addusertogroupform";
		}
		GroupChat group = groupRepo.findById(id).get();
		if(group.addUser(newU))
		{
			model.addAttribute("successmessage", "Persona aggiunta con successo al gruppo");
			groupRepo.save(group); // potrebbe essere qua il problema
		}
		else
		{
			model.addAttribute("errormessage", "La persona è già presente nel gruppo");
		}
		
		return "redirect:/";
	}
}
