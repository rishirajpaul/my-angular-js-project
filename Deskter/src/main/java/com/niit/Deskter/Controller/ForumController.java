package com.niit.Deskter.Controller;

import java.util.Date;
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

import com.niit.Deskter.Dao.ForumDao;
import com.niit.Deskter.model.Blog;
import com.niit.Deskter.model.Forum;
import com.niit.Deskter.model.UserDetails;

@RestController
public class ForumController {
	@Autowired
	ForumDao forumDao;
	@Autowired
	Forum forum;
	@Autowired
	UserDetails userdetails;
	
	//DISPLAY LIST..
	@RequestMapping(value="/forum", method=RequestMethod.GET)
	public ResponseEntity<List<Forum>> getAllForums(){
		List<Forum> forum=forumDao.getAllForums();
		if(forum.isEmpty()){
			return new ResponseEntity<List<Forum>>(forum, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Forum>>(forum, HttpStatus.OK);
	}
	
	//ADD NEW FORUM
	@RequestMapping(value="/Addforum/", method=RequestMethod.POST)
	public ResponseEntity<Forum> creatForum(@RequestBody Forum forum,HttpSession session )
	{
		UserDetails user=(UserDetails) session.getAttribute("loggedInUser");
		
		if(forumDao.getForumByForumId(forum.getForumId())==null){
			forum.setForumCreationDate(new Date(System.currentTimeMillis()));
			forum.setForumStatus("Pending");
			forum.setUserId(user.getUserId());
			forum.setUserName(user.getName());
			forumDao.saveForum(forum);
			return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		}
		forum.setErrorMessage("Forum already exist with id : "+forum.getForumId());
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	
}

	//DISPLAY FORUM DETAILS BY ID
	@RequestMapping(value= "/forum/{id}",method=RequestMethod.GET)
	public ResponseEntity<Forum>getForum(@PathVariable("id")int forumId){
		Forum forum = forumDao.getForumByForumId(forumId);
		if (forum == null){
			forum = new Forum();
			forum.setErrorMessage("User does not exist with id : " + forumId);
				return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
				
		}
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	//UPDATE FORUM DETAIL
	@RequestMapping(value="/UpdateForumDetailID/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Forum> updateuser(@PathVariable("id") String id, @RequestBody Forum forum){
		if(forumDao.getAllForums()==null){
			forum =new Forum();
			forum.setErrorMessage("User does not exist with id : " +forum.getUserId());
			return new ResponseEntity<Forum>(forum, HttpStatus.NO_CONTENT);
		}
		forumDao.updateForum(forum);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}

	// APPROVED FORUM.....

		@RequestMapping(value="/approveForum/{id}", method=RequestMethod.PUT)
		public ResponseEntity<Forum>approveForum(@PathVariable("id") int id,HttpSession session)
		{	
			System.out.println("Rishi want to approve");
			Forum forum=forumDao.getForumByForumId(id);
				if(((UserDetails)session.getAttribute("loggedInUser")).getRole().equals("ADMIN")&&
						(forum!=null))
				{
					forum.setForumStatus("Approved");
							forumDao.updateForum(forum);
							return new ResponseEntity<Forum>(forum,HttpStatus.OK);
						}
				else
				{
							
							return new ResponseEntity<Forum>(forum,HttpStatus.NOT_FOUND);
						}
				
			}
	  
		// UNAPPROVED BLOG.....
		@RequestMapping(value="/unapproveForum/{id}", method=RequestMethod.PUT)
		public ResponseEntity<Forum>unapproveForum(@PathVariable("id") int id,HttpSession session)
		{	
			
				Forum forum=forumDao.getForumByForumId(id);
				if(((UserDetails)session.getAttribute("loggedInUser")).getRole().equals("ADMIN")&&
						(forum!=null))
				{
					forum.setForumStatus("Rejected");
					forumDao.updateForum(forum);
							return new ResponseEntity<Forum>(forum,HttpStatus.OK);
						}
				else
				{
							
							return new ResponseEntity<Forum>(forum,HttpStatus.NOT_FOUND);
						}
				
		}
		
		//DISPLAY APPROVED FORUM LIST...........

		@RequestMapping(value="/approvedforum", method=RequestMethod.GET)
		public ResponseEntity<List<Forum>> getAllApprovedForums(){
			List<Forum> forum=forumDao.getAllApprovedForums();
			if(forum.isEmpty()){
				return new ResponseEntity<List<Forum>>(forum, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Forum>>(forum, HttpStatus.OK);
		}
		
		
		//DISPLAY UNAPPROVED FORUM LIST...........
		@RequestMapping(value="/unapprovedforum", method=RequestMethod.GET)
		public ResponseEntity<List<Forum>> getUnApprovedForums(){
			List<Forum> forum=forumDao.getAllUnApprovedForums();
			if(forum.isEmpty()){
				return new ResponseEntity<List<Forum>>(forum, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Forum>>(forum, HttpStatus.OK);
		}
		


		
		
		
		
		//FORUM DELETE
		
		@RequestMapping(value="/deleteforum/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<Forum>deleteForum(@PathVariable("id")int id){
			Forum forum=forumDao.getForumByForumId(id);
			if(forum == null){
				forum = new Forum();
				forum.setErrorMessage("forum does not exist with id : " + id);
				return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
				
			}
			forumDao.deleteForum(forum);
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
}	
