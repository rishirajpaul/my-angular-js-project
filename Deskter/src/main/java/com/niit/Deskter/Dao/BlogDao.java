package com.niit.Deskter.Dao;

import java.util.List;

import com.niit.Deskter.model.Blog;



public interface BlogDao {
	
public List<Blog> getAllBlogs();
	
public List<Blog> getAllUnApprovedBlogs();

	public boolean saveBlog(Blog blog);
	
	public boolean deleteBlog(Blog blog);
	
	public boolean updateBlog(Blog blog);
	
	public Blog getBlogByBlogId(int blogId);
	
	public List<Blog> getAllApproveBlogs();

}
