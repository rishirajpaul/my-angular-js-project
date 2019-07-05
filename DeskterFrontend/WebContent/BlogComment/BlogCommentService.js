app.factory('BlogCommentService', ['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
			console.log("BlogCommentService...")

			var BASE_URL = 'http://localhost:8081/Deskter'
				return {
				//All BlogComment List............................
				fetchAllBlogComments : function(id) {
					console.log("--> BlogCommentService : calling 'fetchAllBlogcomments' method.");
									return $http
									.get(BASE_URL + '/blogCommentByBlogId/'+id)
									.then(function(response) {
										$rootScope.selectedBlogComment = response.data;
									return response.data;
										}, 
									function(errResponse) {
									console.error('Error while fetching comments');
									return $q.reject(errResponse);
										});
				                   },
				                   
				                   
				                   
				                   
									//  Create New Blog Comment......
										
										createBlogComment : function(blogComment) {
											console.log("--> CommentService : calling 'createBlogComment' method.");
											return $http
											.post(BASE_URL + '/blogComment/', blogComment)
											.then(function(response) {
											return response.data;
													}, 
											function(errResponse) {
												console.error('Error while creating blogComment');
												return $q.reject(errResponse);
													});
													
										},
										
										
										
										
										 getSelectedBlogComment : function(id) {
												console.log("-->BlogCommentService : calling getSelectedBlogComment() method with id : " + id);
												return $http
													.get(BASE_URL+'/blogCommentByBlogId/'+ id)
													.then(function(response) {
														alert(response.data);
													$rootScope.selectedBlogComment = response.data;
													
													return response.data;
														},
													function(errResponse) {
													console.error('Error while Fetching blogcomment.');
													return $q.reject(errResponse);
																});
													},
				                   
				                   
				                   
				                   
				                   
				                   
				                   
				                   
				                   
				                   
				                   
				                   
};
			                     										     
				                   
						
					
				
			
}]);