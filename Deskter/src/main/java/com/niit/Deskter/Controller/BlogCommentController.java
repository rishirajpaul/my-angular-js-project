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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.Deskter.Dao.BlogCommentDao;
import com.niit.Deskter.Dao.BlogDao;
import com.niit.Deskter.model.Blog;
import com.niit.Deskter.model.BlogComment;
import com.niit.Deskter.model.UserDetails;

@RestController
public class BlogCommentController {

    @Autowired
	BlogCommentDao blogCommentDao;
	@Autowired
	BlogComment blogComment;
	@Autowired
	UserDetails userdetails;
	@Autowired
	Blog blog;
	@Autowired
	BlogDao blogDao;
	
	
	
	@RequestMapping(value="/blogComments", method=RequestMethod.GET)
	@ResponseBody
	
	public ResponseEntity<List<BlogComment>> getAllBlogs(){
		List<BlogComment> blogs=blogCommentDao.getAllBlogComments();
		if(blogs.isEmpty()){
			blogComment.setErrorMessage("BlogComment does not exist at all" );
			return new ResponseEntity<List<BlogComment>>(blogs,HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<BlogComment>>(blogs,HttpStatus.OK);
	}
	
	
	@RequestMapping(value= "/blogCommentByBlogId/{id}", method=RequestMethod.GET)
	
	public ResponseEntity<List<BlogComment>> getAllBlogComments(@PathVariable("id")int id){
		List<BlogComment> blogComments=blogCommentDao.listByBlogId(id);
		if(blogComments.isEmpty()){
			blogComment.setErrorMessage("BlogComment does not exist with id : " +id);
			return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(value="/blogComment/", method=RequestMethod.POST)
	public ResponseEntity<BlogComment> creatBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
		UserDetails user=(UserDetails) session.getAttribute("loggedInUser");
		System.out.println("blog id is "+blogComment.getBlogId());
		//checking if the user doesnt exist or the blog doesnt exist
		Blog blog=blogDao.getBlogByBlogId(blogComment.getBlogId());
		if((user==null)||
			(blog==null)	
				)
		{
			blogComment.setErrorCode("404");
			blogComment.setErrorMessage("BlogComment Not Created");
			return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		}
		
		Date blogCommentDate=new Date(System.currentTimeMillis());
		
		blogComment.setBlogCommentDate(blogCommentDate);
		blogComment.setUserId(user.getUserId());
		blogComment.setUserName(user.getName());
		blogComment.setBlogId(blogComment.getBlogId());
		blogCommentDao.save(blogComment);
		blog.setBlogCommentCount((blog.getBlogCommentCount())+1);
		System.out.println("at blog comment count**");
		blogDao.updateBlog(blog);
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value="/updateBlogComment/{id}", method=RequestMethod.PUT)
	public ResponseEntity<BlogComment> updateBlogComment(@PathVariable("id") int id, @RequestBody BlogComment blogComment,HttpSession session){
	
		UserDetails user=(UserDetails) session.getAttribute("loggedInUser");
		
		if((user==null)||(blogCommentDao.getByBlogCommentId(blogComment.getBlogCommentId())==null)){
			blogComment =new BlogComment();
			blogComment.setErrorMessage("Blog does not exist with id : " +id);
			return new ResponseEntity<BlogComment>(blogComment, HttpStatus.NO_CONTENT);
		}
		Date blogCommentDate=new Date(System.currentTimeMillis());
		blogComment.setBlogCommentDate(blogCommentDate);
		blogComment.setUserId(user.getUserId());
		blogComment.setUserName(user.getName());
		blogCommentDao.update(blogComment);
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}
	
	
	//*********************************
	@RequestMapping(value="/saveOrUpdateBlogComment/{id}", method=RequestMethod.PUT)
	public ResponseEntity<BlogComment> updateOrSaveBlog(@PathVariable("id") int id, @RequestBody BlogComment blogComment){
		if(blogCommentDao.getByBlogCommentId(blogComment.getBlogCommentId())==null){
			blogComment =new BlogComment();
			blogComment.setErrorMessage("Blog does not exist with id : " +id);
			return new ResponseEntity<BlogComment>(blogComment, HttpStatus.NO_CONTENT);
		}
		blogComment.setBlogCommentDate(new Date());
		blogCommentDao.saveOrUpdate(blogComment);
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}
	
	
	
	//*********************************
	
	
	
	
	@RequestMapping(value="/deleteBlogComment/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<BlogComment>deleteBlog(@PathVariable("id")int id){
		BlogComment blogComment=blogCommentDao.getByBlogCommentId(id);
		if(blogComment== null){
			blogComment = new BlogComment();
			blogComment.setErrorMessage("BlogComment does not exist with id : " + id);
			return new ResponseEntity<BlogComment>(blogComment, HttpStatus.NOT_FOUND);
			
		}
		blogCommentDao.delete(blogComment);
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}
	
	
	
	
	
	//*********************************
	
	
	

	@RequestMapping(value= "/blogCommentbyblogId/{id}", method=RequestMethod.GET)
	
	public ResponseEntity<List<BlogComment>> getAllBlogs(@PathVariable("id")int id){
		List<BlogComment> blogComments=blogCommentDao.listByBlogId(id);
		if(blogComments.isEmpty()){
			blogComment.setErrorMessage("BlogComment does not exist with id : " +id);
			return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
	}
	
	
	
		//*******************************************
	
	
	
	
@RequestMapping(value= "/blogCommentsByUserId/{id}", method=RequestMethod.GET)
	
	public ResponseEntity<List<BlogComment>> getBlogCommentsByUserId(@PathVariable("id")String id){
		List<BlogComment> blogComments=blogCommentDao.listByUserId(id);
		if(blogComments.isEmpty()){
			blogComment.setErrorMessage("BlogComment does not exist with id : " + id);
			return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
	}

}

