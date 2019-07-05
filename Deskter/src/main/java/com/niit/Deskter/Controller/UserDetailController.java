package com.niit.Deskter.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.Deskter.Dao.UserDetailsDao;
import com.niit.Deskter.model.UserDetails;
@RestController
public class UserDetailController {
	@Autowired
	UserDetailsDao userDao;
	@Autowired
	UserDetails user;
	
	//DISPLAY USER LIST
	
	@RequestMapping(value="/userdetails", method=RequestMethod.GET)
	public ResponseEntity<List<UserDetails>> getAllUser(){
		List<UserDetails> user=userDao.getAllUser();
		if(user.isEmpty()){
			return new ResponseEntity<List<UserDetails>>(user, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserDetails>>(user, HttpStatus.OK);
	

}
	//ADD USER LIST
	
	@RequestMapping(value="/Adduser/", method=RequestMethod.POST)
	public ResponseEntity<UserDetails> creatUser(@RequestBody UserDetails users){
		if(userDao.getUserByUserId(users.getUserId())==null){
			users.setRole("USER");
			userDao.saveUser(users);
			return new ResponseEntity<UserDetails>(users,HttpStatus.OK);
		}
		users.setErrorMessage("User already exist with id : "+users.getUserId());
		return new ResponseEntity<UserDetails>(users, HttpStatus.OK);
	}
	
	
	//LOGIN AND VERIFY USER
	@RequestMapping(value = "/login/verify/", method = RequestMethod.POST)
	public ResponseEntity<UserDetails> UserAuthentication(@RequestBody UserDetails users, HttpSession session){
		users = userDao.UserAuthentication(users.getUserId(), users.getPassword());
		if(users == null){
			users = new UserDetails();
			users.setErrorMessage("Invalid userId or password...");
		}
		else {
			session.setAttribute("loggedInUser", users);
			System.out.println("logged session set ");
			session.setAttribute("loggedInUserID", users.getUserId());
			users.setIsOnline("YES");
			users.setStatus("ACTIVE");
			userDao.updateUser(users);
		}
		return new ResponseEntity<UserDetails>(users, HttpStatus.OK);
	}
	
	//DISPLAY USER PROFILE BY ID
	@RequestMapping(value= "/Displayuser/{id}",method=RequestMethod.GET)
	public ResponseEntity<UserDetails>getUserByUserId(@PathVariable("id")String id){
		UserDetails users = userDao.getUserByUserId(id);
		if (users == null){
			users = new UserDetails();
			users.setErrorMessage("User does not exist with id : " + id);
				return new ResponseEntity<UserDetails>(users, HttpStatus.NOT_FOUND);
				
		}
		return new ResponseEntity<UserDetails>(users, HttpStatus.OK);
	}
	
	//UPDATE USER PROFILE BY ID
	@RequestMapping(value="/Updateuser/{id}", method=RequestMethod.PUT)
	public ResponseEntity<UserDetails> updateuser(@PathVariable("id") String id, @RequestBody UserDetails users){
		if(userDao.getAllUser()==null){
			users =new UserDetails();
			users.setErrorMessage("User does not exist with id : " +users.getUserId());
			return new ResponseEntity<UserDetails>(users, HttpStatus.NO_CONTENT);
		}
		userDao.updateUser(users);
		return new ResponseEntity<UserDetails>(users, HttpStatus.OK);
	}

	
	// logout user 

	@RequestMapping(value= "/logout/{id}",method=RequestMethod.PUT)
	public ResponseEntity<UserDetails>logout(@PathVariable("id") String id, @RequestBody UserDetails users,HttpSession session){
		System.out.println("hello  "+users.getName());	
		users.setIsOnline("No");
		userDao.updateUser(users);	
			session.invalidate();
			 
			
				return new ResponseEntity<UserDetails>(new UserDetails(), HttpStatus.OK);
				

}
	
}