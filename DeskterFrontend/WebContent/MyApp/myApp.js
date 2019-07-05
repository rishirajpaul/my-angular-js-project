var app = angular.module("myApp", ['ngRoute']);
app.config(['$routeProvider',function($routeProvider) {
	$routeProvider   
    .when("/", {
        templateUrl : 'User/test.html',
    })
  // ******************************* User **************************************************************************//
    .when('/UserView', {
			templateUrl: 'User/UserView.html',
			controller: 'UserController as ct',
		
    
    })
    
    .when('/DisplayUser', {
			templateUrl: 'User/DisplayUser.html',
			controller: 'UserController as ct',
    })
    
    
    
      .when('/MyProfile', {
			templateUrl: 'User/MyProfile.html',
			controller: 'UserController as ct',
    })
    
    
    
    
    
    
     .when('/Login', {
			templateUrl: 'User/Login.html',
			controller: 'UserController as ct',
    })
    
    
    .when('/NewUser', {
			templateUrl: 'User/NewUser.html',
			controller: 'UserController as ct',
    }) 
    
    
    
    
    .when('/EditUser', {
			templateUrl: 'User/EditUser.html',
			controller: 'UserController as ct',
    })
    
  //******************************************************BLOG*************************************************  //
  
    .when('/BlogList', {
			templateUrl: 'Blog/BlogList.html',
			controller: 'BlogController as ct'
	
    })
			
			 .when('/AddBlog', {
					templateUrl: 'Blog/AddBlog.html',
					controller: 'BlogController as ct',
			 })
					
		
					 .when('/EditBlog', {
							templateUrl: 'Blog/EditBlog.html',
							controller: 'BlogController as ct',
					 })
					 
					
							 .when('/BlogDetails', {
									templateUrl: 'Blog/BlogDetails.html',
									controller: 'BlogController as ct',
								
							 })
							
									
							 
							 
							  .when('/ApproveBlog', {
									templateUrl: 'Blog/ApproveBlog.html',
									controller: 'BlogController as ct',
								
							 })
							
							 
							 
							 
							 
									
										
//*****************************************FORUM*****************************************************//
										.when('/Forum', {
											templateUrl: 'Forum/Forum.html',
											controller: 'ForumController as ct',
										
										
										})
											
											
											
									
								.when('/AddForum', {
											templateUrl: 'Forum/AddForum.html',
											controller: 'ForumController as ct',
										
										
										})	

										
										
										
										
										.when('/ForumDetails', {
											templateUrl: 'Forum/ForumDetails.html',
											controller: 'ForumController as ct',
										
										
										})	

										
										
										.when('/EditForum', {
											templateUrl: 'Forum/EditForum.html',
											controller: 'ForumController as ct',
										
										
										})	

										
										.when('/ApproveForum', {
											templateUrl: 'Forum/ApproveForum.html',
											controller: 'ForumController as ct',
										
										
										})
										
										
										
										
										.when('/ApproveForumList', {
											templateUrl: 'Forum/ApproveForumList.html',
											controller: 'ForumController as ct',
										
										
										})
										
										
										
//********************************************JOB*******************************************************//
										
										
										.when('/jobList', {
											templateUrl: 'job/jobList.html',
											controller: 'JobController as ct',
										
										
										})	
										
										
										
										.when('/JobDetail', {
											templateUrl: 'job/JobDetail.html',
											controller: 'JobController as ct',
										
										
										})
										
										
										
									.when('/JobApplicationView', {
											templateUrl: 'job/JobApplicationView.html',
											controller: 'JobController as ct',
										
										
										})	
										
										
										
										.when('/EditJobDetail', {
											templateUrl: 'job/EditJobDetail.html',
											controller: 'JobController as ct',
										
										
										})
										
								
									.when('/NewJob', {
											templateUrl: 'job/NewJob.html',
											controller: 'JobController as ct',
										
										
										})
										
										
	//************************************************** FRIEND **************************************************
										
										
										.when('/ViewFriend', {
											templateUrl: 'Friend/ViewFriend.html',
											controller: 'FriendController as ct'
										})
										
										.when('/friendDetails', {
											templateUrl: 'Friend/friendDetails.html',
											controller: 'FriendController as ct'
										})
										.when('/Request', {
											templateUrl: 'Friend/Request.html',
											controller: 'FriendController as ct'
										})
										
										
	//*******************************Chat**************************************
										
										
										.when('/chat', {
											templateUrl: 'Chat/chat.html',
											controller: 'ChatController as ct'
										})
												
    
}]);

