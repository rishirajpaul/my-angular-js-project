package com.niit.Deskter.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.Deskter.Dao.ForumCommentDao;
import com.niit.Deskter.model.Blog;
import com.niit.Deskter.model.BlogComment;
import com.niit.Deskter.model.ForumComment;
import com.niit.Deskter.model.UserDetails;

@RestController
public class ForumCommentController {
	@Autowired
	ForumComment forumComment;
	@Autowired
	ForumCommentDao forumCommentDao;
	
	//DISPLAY ALL FORUM COOMENT LIST
	@RequestMapping(value="/forumcomment", method=RequestMethod.GET)
	public ResponseEntity<List<ForumComment>> getAllForumComments(){
		List<ForumComment> formcom=forumCommentDao.getAllForumComments();
		if(formcom.isEmpty()){
			return new ResponseEntity<List<ForumComment>>(formcom, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ForumComment>>(formcom, HttpStatus.OK);
	}
	
	//ADD NEW FORUM COMMENT
	@RequestMapping(value="/AddComment/", method=RequestMethod.POST)
	public ResponseEntity<ForumComment> createForumComment(@RequestBody ForumComment forumComment){
		if(forumCommentDao.getByForumCommentId(forumComment.getForumCommentId())==null){
			forumComment.setForumCommentDate(new Date(System.currentTimeMillis()));
			forumCommentDao.save(forumComment);
			return new ResponseEntity<ForumComment>(forumComment,HttpStatus.OK);
		}
		forumComment.setErrorMessage("ForumComment already exist with id : "+forumComment.getForumCommentId());
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
	}

	//DISPLAY FORUM COMMENT BY ID
	
	@RequestMapping(value= "/forumcomment/{id}",method=RequestMethod.GET)
	public ResponseEntity<ForumComment>getByForumCommentId(@PathVariable("id")int id){
		ForumComment forumComment = forumCommentDao.getByForumCommentId(id);
		if (forumComment == null){
			forumComment = new ForumComment();
			forumComment.setErrorMessage("forumComment does not exist with id : " + id);
				return new ResponseEntity<ForumComment>(forumComment, HttpStatus.NOT_FOUND);
				
		}
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
	}

	//DISPLAY FORUM BY USER ID
	@RequestMapping(value= "/ForumIdlistbyuser/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<ForumComment>>listByUserId(@PathVariable("id") String id){
		List<ForumComment> forumComment=forumCommentDao.listByUserId(id);
		if (forumComment == null){
			
				return new ResponseEntity<List<ForumComment>>(forumComment, HttpStatus.NOT_FOUND);
				
		}
		return new ResponseEntity<List<ForumComment>>(forumComment, HttpStatus.OK);
	}
	
	
	
	//DISPLAY FORUM ID
	@RequestMapping(value= "/ForumIdlist/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<ForumComment>>listByForumId(@PathVariable("id") int id){
		List<ForumComment> forumComment=forumCommentDao.listByForumId(id);
		if (forumComment == null){
			
				return new ResponseEntity<List<ForumComment>>(forumComment, HttpStatus.NOT_FOUND);
				
		}
		return new ResponseEntity<List<ForumComment>>(forumComment, HttpStatus.OK);
	}

	
	
	
	
	
	
	
	//FORUMCOMMENT DELETE
	@RequestMapping(value="/deleteforuncomment/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<ForumComment>delete(@PathVariable("id")int id){
		ForumComment forumComment=forumCommentDao.getByForumCommentId(id);
		if(forumComment == null){
			forumComment = new ForumComment();
			forumComment.setErrorMessage("Blog does not exist with id : " + id);
			return new ResponseEntity<ForumComment>(forumComment, HttpStatus.NOT_FOUND);
			
		}
		forumCommentDao.delete(forumComment);
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
	}
	
	
}


	

