package com.niit.Deskter.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Deskter.Dao.JobDao;
import com.niit.Deskter.model.Job;
import com.niit.Deskter.model.JobApplication;

@Repository("jobDao")
@Transactional

public class JobDaoImpl implements JobDao {

private SessionFactory sessionFactory;
	
	public JobDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}
	
	public boolean saveJob(Job job) {
		try
		{
			Session session = getSession();

			session.save(job);

			session.flush(); 

			session.close();
			
			return true;
		}
		catch(HibernateException e)
		{
			return false;
		}
	}

	public boolean update(Job job) {
		try
		{
			Session session = getSession();

			session.update(job);

			session.flush(); 

			session.close();
			
			return true;
		}
		catch(HibernateException e)
		{
			return false;
		}
	}

	public boolean delete(Job job) {
		try
		{
			Session session = getSession();

			session.delete(job);

			session.flush(); 

			session.close();
			
			return true;
		}
		catch(HibernateException e)
		{
			return false;
		}
	}

	public Job getJobByJobId(int id) {
		Session session = getSession();
		Query query=session.createQuery("FROM Job a where jobId=:jobId");
		query.setParameter("jobId", id);
		Job a =(Job)query.uniqueResult();
		session.close();
		return a;
	}

	public List<Job> list() {
		Session session = getSession();
		Query query=session.createQuery("from Job");
		List<Job> list=query.list();
		return list;
	}

	public List<Job> getMyAppliedJobs(String userid) {
Session session=getSession();
		
		try{
			Query query=session.createQuery("from JobApplication where userId = ?");
			query.setString(0, userid);
			
			return query.list();
			
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public JobApplication get(String userid, int jobid) {
		Session session = getSession();
		Query query=session.createQuery("FROM JobApplication where userId=:userId and jobId=:jobId");
		query.setParameter("userId", userid );
		query.setParameter("jobId", jobid );
		JobApplication s=(JobApplication)query.uniqueResult();
		session.close();
		return s;
	}

	public boolean updateJobApplication(JobApplication jobApplication) {
		try
		{
			Session session = getSession();

			session.update(jobApplication);

			session.flush(); 

			session.close();
			
			return true;
		}
		catch(HibernateException e)
		{
			return false;
		}
	}

	public boolean applyForJob(JobApplication jobApplication) {
		try
		{
			Session session = getSession();

			session.save(jobApplication);

			session.flush(); 

			session.close();
			
			return true;
		}
		catch(HibernateException e)
		{
			return false;
		}
	}

	public List<JobApplication> listJobApplications() {
		Session session = getSession();
		Query query=session.createQuery("from JobApplication");
		List<JobApplication> jobApplicationList=query.list();
		session.close();
		return jobApplicationList;
	}

	public List<Job> listVacantJobs() {
		Session session = getSession();
		Query query=session.createQuery("from Job where status=:status");
		query.setParameter("status", "Open" );
		List<Job> jobApplicationList=query.list();
		session.close();
		return jobApplicationList;
	}

	public List<Job> getMyAppliedJobs() {
		// TODO Auto-generated method stub
		return null;
	}

}
