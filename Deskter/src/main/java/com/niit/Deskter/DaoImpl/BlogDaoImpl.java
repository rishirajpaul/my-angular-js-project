package com.niit.Deskter.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Deskter.Dao.BlogDao;
import com.niit.Deskter.model.Blog;


@Repository("blogDao")
@Transactional
public class BlogDaoImpl implements BlogDao {
	

	private SessionFactory sessionFactory;
	
	public BlogDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public List<Blog> getAllBlogs() {
		Session session = getSession();
		Query query=session.createQuery("from Blog");
		List<Blog> bloglist=query.list();
		return bloglist;
	}

	public boolean saveBlog(Blog blog) {
		try
		{
			Session session = getSession();

			session.save(blog);

			session.flush(); 

			session.close();
			
			return true;
		}
		catch(HibernateException e)
		{
			return false;
		}
	}

	public boolean deleteBlog(Blog blog) {
		try
		{
			Session session = getSession();

			session.delete(blog);

			session.flush(); 

			session.close();
			
			return true;
		}
		catch(HibernateException e)
		{
			return false;
		}
	}

	public boolean updateBlog(Blog blog) {
		try
		{
			Session session = getSession();

			session.update(blog);

			session.flush(); 

			session.close();
			
			return true;
		}
		catch(HibernateException e)
		{
			return false;
		}
	}

	public Blog getBlogByBlogId(int blogId) {
		Session session = getSession();
		Query query=session.createQuery("FROM Blog s where blogId=:blogId");
		query.setParameter("blogId", blogId);
		Blog  blog =(Blog)query.uniqueResult();
		session.close();
		return blog;
		
	}

	public List<Blog> getAllApproveBlogs() {
		Session session = getSession();
		Query query=session.createQuery("FROM Blog s where blogStatus=:blogStatus");
		query.setParameter("blogStatus", "Approved");
		List<Blog> bloglist=query.list();
		session.close();
		return bloglist;
		
	}

	public List<Blog> getAllUnApprovedBlogs() {
		Session session = getSession();
		Query query=session.createQuery("from Blog where blogStatus=:blogStatus");
		query.setParameter("blogStatus", "Rejected");
		List<Blog> bloglist=query.list();
		return bloglist;
	}

	

}