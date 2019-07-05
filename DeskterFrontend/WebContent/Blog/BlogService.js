

app.factory('BlogService', ['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
			console.log("BlogService...")

			var BASE_URL = 'http://localhost:8081/Deskter'
				return {
				
				//All Blog List............................
				fetchAllBlogs : function() {
					console.log("--> BlogService : calling 'fetchAllblogs' method.");
									return $http
									.get(BASE_URL + '/blog')
									.then(function(response) {
									return response.data;
										}, 
									function(errResponse) {
									console.error('Error while fetching blogs');
									return $q.reject(errResponse);
										});
				                   },
				
			

			
			
			  //ADD Blog..............................			
			createBlog : function(blog) {
				console.log("--> UserService : calling 'createBlog' method.");
				return $http
				.post(BASE_URL + '/Addblog/', blog)
				.then(function(response) {
				return response.data;
						}, 
				function(errResponse) {
					console.error('Error while creating blog');
					return $q.reject(errResponse);
						});
				
			},

			
			
			
			
			//Blog Details..............................			
			getSelectedBlog : function(id) {
				console.log("-->UserService : calling getSelectedUser() method with id : " + id);
				return $http
							.get(BASE_URL+'/blog/'+ id)
							.then(function(response) {
								$rootScope.selectedBlog = response.data;
								return response.data;
							},
							function(errResponse) {
								console.error('Error while Fetching User.');
								return $q.reject(errResponse);
							});
		
			},

			
			
//	fetchAllApprovedBlogs......................	
			
			fetchAllApprovedBlogs : function() {
				console.log("--> BlogService : calling 'fetchAllApprovedBlogs' method.");
				return $http
				.get(BASE_URL + '/approvedblog')
				.then(function(response) 
				{
				return response.data;
				}, 
				function(errResponse) 
				{
				console.error('Error while fetching Uss');
				return $q.reject(errResponse);
				});
				},

			
			
				likeBlog : function(blog, id) 
				{
					console.log("-->BlogService : calling likeBlog() method : getting blog with id : " + id);
					return $http.put(BASE_URL+'/BlogLike/'+id, blog).then
								(function(response) 
								{
									return response.data;
								},
								function(errResponse)
								{
									console.log("Error while liking Blog.");
									return $q.reject(errResponse);
								});
								
				},
				
				
				fetchAllBlogLikes : function(id)
				{
					console.log("-->BlogService : calling 'fetchAllBlogLikes' method for id : " + id);
					return $http.get(BASE_URL + '/likeBlog/'+id).then
					(function(response) 
							{
									$rootScope.selectedBlogLikes = response.data;
									return response.data;
							}, 
								function(errResponse) {
									console.error('Error while fetching BlogLikes');
									return $q.reject(errResponse);
								});
				},
			
			
			
			
			
			 //Edit Blog...............		
			updateBlog : function(blog, id) {
		     console.log("--> BlogService : calling 'updateBlog' method.");
			return $http
		    .put(BASE_URL+'/UpdateBlog/'+id, blog)
			.then(function(response) {
		     return response.data;
			 },
			function(errResponse) {
		    console.error('Error while updating Blog...');
	    	return $q.reject(errResponse);
		     });
			},
			
			
//create BlogComment by blogId
			
			createBlogComment : function(blogComment)
			{
				console.log("-->BlogService : calling 'createBlogComment' method.");
				return $http.post(BASE_URL + '/blogComment/', blogComment)
				.then(function(response){
				return response.data;
						},
				function(errResponse) {
				console.error('Error while creating blogComment');
				return $q.reject(errResponse);
						}
				);
			},
			

			
			
			
			
			};
}]);
