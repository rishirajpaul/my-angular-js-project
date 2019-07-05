package com.niit.Deskter.Dao;

import java.util.List;

import com.niit.Deskter.model.BlogLike;

public interface BlogLikeDao {

	public List<BlogLike>getBlogLikesByBlogId(int blogId);
	public boolean saveBlogLike(BlogLike blogLike);
	public boolean isExist(int blogId,String userId);
}
