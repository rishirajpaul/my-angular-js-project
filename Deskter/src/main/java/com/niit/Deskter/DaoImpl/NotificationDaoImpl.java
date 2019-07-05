package com.niit.Deskter.DaoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Deskter.Dao.NotificationDao;
import com.niit.Deskter.model.Notification;
@Repository("notificationDao")
@Transactional

public class NotificationDaoImpl implements NotificationDao {
private SessionFactory sessionFactory;
	
	public NotificationDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public void addNotification(Notification notification) {
		// TODO Auto-generated method stub
		
	}

	public List<Notification> getAllNotifications(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Notification getNotification(int notificationId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateNotificactionViewedStatus(int notificationId) {
		// TODO Auto-generated method stub
		
	}

}
