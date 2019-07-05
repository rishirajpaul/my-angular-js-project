package com.niit.Deskter.Dao;

import java.util.List;

import com.niit.Deskter.model.Notification;

public interface NotificationDao {
	
	
		void addNotification(Notification notification);
		
		//select * from notification where userToBeNotified.userId=? and viewed=0
		List<Notification> getAllNotifications(String userId);//glyphicon globe
		
		//select * from notification where notificationId=?
		Notification getNotification(int notificationId);
		
		//update notification set viewed=1 where notificationId=?
		void updateNotificactionViewedStatus(int notificationId);

}
