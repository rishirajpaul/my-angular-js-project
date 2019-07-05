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


import com.niit.Deskter.Dao.BlogDao;
import com.niit.Deskter.Dao.BlogLikeDao;
import com.niit.Deskter.model.Blog;
import com.niit.Deskter.model.BlogLike;
import com.niit.Deskter.model.UserDetails;


@RestController
public class BlogController {
	@Autowired
	BlogDao blogDao;
	@Autowired
	UserDetails userDetails;
	@Autowired
	BlogLike bloglike;
	@Autowired
	BlogLikeDao bloglikedao;
	
	//DISPLAY ALL BLOG LIST
	@RequestMapping(value="/blog", method=RequestMethod.GET)
	public ResponseEntity<List<Blog>> getAllBlogs(){
		List<Blog> blog=blogDao.getAllBlogs();
		if(blog.isEmpty()){
			return new ResponseEntity<List<Blog>>(blog, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
	}
	
	//DISPLAY APPROVED BLOG
	
	@RequestMapping(value="/approvedblog", method=RequestMethod.GET)
	public ResponseEntity<List<Blog>> getApprovedBlogs(){
		List<Blog> blog=blogDao.getAllApproveBlogs();
		if(blog.isEmpty()){
			return new ResponseEntity<List<Blog>>(blog, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
	}
	
	
	
	//DISPLAY UNAPPROVED BLOG LIST...........
	@RequestMapping(value="/unapprovedblog", method=RequestMethod.GET)
	public ResponseEntity<List<Blog>> getUnApprovedBlogs(){
		
		List<Blog> blog=blogDao.getAllUnApprovedBlogs();
		if(blog.isEmpty()){
			return new ResponseEntity<List<Blog>>(blog, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
	}
	
	
	
	//ADD NEW BLOG
	
	@RequestMapping(value="/Addblog/", method=RequestMethod.POST)
		public ResponseEntity<Blog> creatBlog(@RequestBody Blog blogs , HttpSession session){
			
				UserDetails user=(UserDetails) session.getAttribute("loggedInUser");
				blogs.setBlogDate(new Date(System.currentTimeMillis()));
				blogs.setBlogStatus("Pending");
				blogs.setUserId(user.getUserId());
				blogDao.saveBlog(blogs);
				return new ResponseEntity<Blog>(blogs,HttpStatus.OK);
			
		
		}
	
	
	//DISPLAY BLOG BY ID
	@RequestMapping(value= "/blog/{id}",method=RequestMethod.GET)
	public ResponseEntity<Blog>getBlogByBlogId(@PathVariable("id")int id){
		Blog blog = blogDao.getBlogByBlogId(id);
		if (blog == null){
			blog = new Blog();
			blog.setErrorMessage("blog does not exist with id : " + id);
				return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
				
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	//DISPLAY UPDATE BLOG
	@RequestMapping(value="/UpdateBlog/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") int id, @RequestBody Blog blog){
		if(blogDao.getAllBlogs()==null){
			blog =new Blog();
			blog.setErrorMessage("User does not exist with id : " +blog.getUserId());
			return new ResponseEntity<Blog>(blog, HttpStatus.NO_CONTENT);
		}
		blogDao.updateBlog(blog);
		blog.setBlogStatus("Pending");
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	
//DISPLAY APPROVED BLOG.....

	@RequestMapping(value="/approveBlog/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Blog>approveBlog(@PathVariable("id") int id,HttpSession session)
	{	
		
			Blog blog=blogDao.getBlogByBlogId(id);
			if(((UserDetails)session.getAttribute("loggedInUser")).getRole().equals("ADMIN")&&
					(blog!=null))
			{
						blog.setBlogStatus("Approved");
						blogDao.updateBlog(blog);
						return new ResponseEntity<Blog>(blog,HttpStatus.OK);
					}
			else
			{
						
						return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
					}
			
		}
  
	//DISPLAY UNAPPROVED BLOG.....
	@RequestMapping(value="/unapproveBlog/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Blog>unapproveBlog(@PathVariable("id") int id,HttpSession session)
	{	
		
			Blog blog=blogDao.getBlogByBlogId(id);
			if(((UserDetails)session.getAttribute("loggedInUser")).getRole().equals("ADMIN")&&
					(blog!=null))
			{
						blog.setBlogStatus("unapproved");
						blogDao.updateBlog(blog);
						return new ResponseEntity<Blog>(blog,HttpStatus.OK);
					}
			else
			{
						
						return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
					}
			
		}

	
	
	@RequestMapping(value="/rejectedBlog/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Blog>rejectedBlog(@PathVariable("id") int id,HttpSession session)
	{	
		
			Blog blog=blogDao.getBlogByBlogId(id);
			if(((UserDetails)session.getAttribute("loggedInUser")).getRole().equals("ADMIN")&&
					(blog!=null))
			{
						blog.setBlogStatus("rejected");
						blogDao.updateBlog(blog);
						return new ResponseEntity<Blog>(blog,HttpStatus.OK);
					}
			else
			{
						
						return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
					}
			
		}

	
	//BLOG DELETE
	@RequestMapping(value="/deleteblog/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Blog>deleteBlog(@PathVariable("id")int id){
		Blog blog=blogDao.getBlogByBlogId(id);
		if(blog == null){
			blog = new Blog();
			blog.setErrorMessage("Blog does not exist with id : " + id);
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
			
		}
		blogDao.deleteBlog(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	//****************************************BLOG LIKE*******************************************************//
	
	

		

	@RequestMapping(value="/likeBlog/{blogId}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<BlogLike>> getBlogLikesByblogId(@PathVariable("blogId") int blogId,HttpSession session) {
		Blog blog = blogDao.getBlogByBlogId(blogId);
		if (blog == null) {
			return new ResponseEntity<List<BlogLike>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BlogLike>>(bloglikedao.getBlogLikesByBlogId(blogId), HttpStatus.OK);
		
	}
	
	
	
	@RequestMapping(value="/BlogLike/{blogId}", method=RequestMethod.PUT)
	public ResponseEntity<Blog> likeBlog(@PathVariable("blogId") int blogId,HttpSession session) 
	{
		try
		{
			UserDetails user=(UserDetails) session.getAttribute("loggedInUser");
			
			Blog blog = blogDao.getBlogByBlogId(blogId);
			if (blog == null)
			{
				blog = new Blog();
				
				blog.setErrorMessage("No blog exist with id : " + blogId);
	
				return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		
			else if(bloglikedao.isExist(blogId, user.getUserId()))
			{
				blog = new Blog();
				
				blog.setErrorMessage("User has already liked the blog: " + blogId);

				return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
			}
			
		blog.setBlogCountLike(blog.getBlogCountLike()+1);
		blogDao.updateBlog(blog);
		BlogLike blogLike=new BlogLike();
		blogLike.setBlogId(blogId);blogLike.setUserId(user.getUserId());blogLike.setUserName(user.getName());
		blogLike.setBlogLikeDate(new Date(System.currentTimeMillis()));
		bloglikedao.saveBlogLike(blogLike);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
			Blog blog=new Blog();
			blog.setErrorMessage(" user not logged in");
			blog.setErrorCode("404");
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);

		}
		
		
	}	


}