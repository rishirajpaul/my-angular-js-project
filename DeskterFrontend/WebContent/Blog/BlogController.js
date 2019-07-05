

app.controller('BlogController', [
		'$scope',
		'BlogService',
		'BlogCommentService',
		'$location',
		'$rootScope',
		'$route',
		'$window',
		function($scope, BlogService,BlogCommentService, $location, $rootScope,$route,$window) {
			console.log("BlogController...")

			var self = this;
			self.blog = {
			    errorCode : '',
				errorMessage : '',
				BlogId : '',
				blogContent : '',
				blogTitle : '',
				blogDate : '',
				userId : '',
				blogStatus : '',
				blogCountLike : '',
				blogCommentCount : '',
								}
			self.blogs = [];

			
			
			self.blogComment = {
				    errorCode : '',
					errorMessage : '',
					blogCommentId : '',
					blogId : '',
					userId : '',
					blogComment : '',
					userName : '',
					blogCommentDate : '',
					
					}
			
			
			self.blogComments=[];
		
			
			
			
			
			
			//All BlogList...................
			self.fetchAllBlogs = function() {
				console.log("--> BlogController : calling fetchAllBlogs method.");
				BlogService.fetchAllBlogs().then(
				function(e) {
				self.Blogs = e;
								}, function(errResponse) {
									console.error('Error while fetchingBlogs...');
								});
					};
					self.fetchAllBlogs();
			
			
			
			//ADD Blog.............	
			self.createBlog = function(Blog) {
				console.log("--> BlogController : calling createBlog method.");
				BlogService.createBlog(Blog).then(
						function(e) {
							self.blogs = e;
							alert('Blog Created Successfully...')
						},
						function(errResponse) {
							console.error('Error while creating blog...');
						});
			};
		
			
			//Blog Details..................		
			self.getSelectedBlog = function(id) {
				console.log("-->BlogController : calling getSelectedBlog method : getting Blog with id : " + id);
				BlogService.getSelectedBlog(id).then(
						function(e) {
							self.blog = e;
							console.log('id '+ self.blog.blogId);
							$rootScope.blog= self.blog;
							$location.path('/BlogDetails');
							self.getSelectedBlogComment(id);
						}, 
						function(errResponse) {
							console.error('Error while fetching Blog...');
						});
			};
	
			
			
			
			
			//Edit Blog..............
			self.updateBlog = function(blog,id) {
				console.log("-->BlogController : calling updateBlog method.");
				BlogService.updateBlog(blog,id).then(
		         function(e) {
						self.blog = e;
						alert('Blog updated Successfully...')
						console.log(self.blog);
						},
				function(errResponse) {
				console.error('Error while updating blog...')
					});
				};
			
						
				
//				fetchAllApprovedBlogs..........			  					
					
					self.fetchAllApprovedBlogs = function() {
						console.log("--> BlogController : calling fetchAllAprovedBlogs method.");
						BlogService.fetchAllApprovedBlogs().then(
						function(d) {
						self.approvedBlogs = d;
								}, 
						function(errResponse) {
						console.error('Error while fetching Blogs...');
								});
					};

					self.fetchAllApprovedBlogs();
				
				
					
					
					
					//********************************************** BLOG LIKE ***************************************************	    
					
					  //like blog by blog id................						
						self.likeBlog = function(blog, id)
						{
							console.log("-->BlogController : calling likeBlog() method : Blog id is : "+id);
							console.log("-->BlogController", self.blog);
							BlogService.likeBlog(blog, id).then
							( function() 
								{
								self.getSelectedBlog(id);
								self.listblogs;
								self.fetchAllBlogLikes(id);
								$location.path('/BlogDetails');
								} ,
								function(errResponse)
								{
									console.error("Error while liking the blog...");
								});
							
							
						};
						
						
//					fetchAllBlogLikes by blog id.............				
						self.fetchAllBlogLikes = function(id)
						{
							console.log("-->BlogController : calling fetchAllBlogLikes method with id : "+ id);
							BlogService.fetchAllBlogLikes(id).then
							(function(s) 
							{
								self.blogLikes = s;
							},
							function(errResponse) 
							{
								console.error('Error while fetching BlogLikes...');
							}
							);
						};
						
						

					
					
					
				//***************** BLOG COMMENT*********************
					
					
					
					
					//Show Blog Comment By Blog Id.....
					
					self.getSelectedBlogComment = function(id) {
						console.log("-->BlogCommentController : calling getSelectedBlogComment method : getting blogComment with id : " + id);
						BlogCommentService.getSelectedBlogComment(id).then(
								function(d) {
								self.blogComments = d;
								//console.log('id '+ self.blogComment.blogCommentId);
								$rootScope.blogComments= self.blogComments;
								//console.log(' r id '+ $rootScope.blogComment.blogCommentId);
								//$location.path('/BlogCommentDetails');
									}, 
								function(errResponse) {
								console.error('Error while fetching BlogComment...');
									});
										};
				
					
					
					
					// Create New Blog Comment........
					
					self.createBlogComment = function(blogComment) {
						blogComment.blogId= $rootScope.blog.blogId ;
						console.log("-->BlogController : calling 'createBlogComment' method.", blogComment);
						console.log("-->BlogController BlogId :" +blogComment.blogId);
						BlogCommentService.createBlogComment(blogComment).then
									(function(d) 
									{
										console.log('Current User :',$rootScope.currentUser.userId)
										self.blogComment = d;
										console.log(self.blogComment)
										self.getSelectedBlogComment(self.blogComment.blogId);
										$location.path('/BlogDetails');
									
									},
									function(errResponse) {
										console.error('Error while creating blogComment...');
									}
									);
					};	
					
			// Show All Comments.........................		
					self.fetchAllComments = function(id) {
						console.log("--> BlogController : calling fetchAllComments method : getting blogComment with id : " + id);
						BlogCommentService.fetchAllComments(id).then(
						function(s) {
						self.blogComments = s;
							},
						function(errResponse) {
						console.error('Error while fetchingBlogComments...');
							});
				    };
				

				    
				    
			
		
		self.reset = function() {
			console.log('submit a new Blog', self.Blog);
			self.Blog = {
					    errorCode : '',
						errorMessage : '',
						BlogId : '',
						blogContent : '',
						blogTitle : '',
						blogDate : '',
						userId : '',
						blogStatus : '',
						blogCountLike : '',
						blogCommentCount : '',
						
			};
			$scope.myForm.$setPristine(); // reset form...
		};
	} ]);
		
		
self.reset = function() {
	console.log('submit a new blog', self.blog);
	self.blog = {
    errorCode : '',
    errorMessage : '',
	blogId : '',
    blogContent : '',
    blogTitle : '',
    blogDate : '',
    userId : '',
    blogStatus : '',
    blogCountLike : '',
    blogCommentCount : '',



	};
	$scope.myForm.$setPristine(); // reset form...
};

		

	


