app.factory('ForumCommentService', ['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
			console.log("ForumCommentService...")

			var BASE_URL = 'http://localhost:8081/Deskter'
				return {
				//All ForumComment List............................
				getSelectedForumComment : function(id) {
					console.log("-->ForumCommentService : calling getSelectedForumComment() method with id : " + id);
					return $http
						.get(BASE_URL+'/ForumIdlist/'+ id)
						.then(function(response) {
							alert(response.data);
						$rootScope.selectedForumComment = response.data;
						
						return response.data;
							},
						function(errResponse) {
						console.error('Error while Fetching forumcomment.');
						return $q.reject(errResponse);
									});
						},
				                   
				          //Create ForumComment..............................			
									createForumComment : function(forumComment) {
										console.log("--> ForumCommentService : calling 'createForumComment' method.");
										return $http
										.post(BASE_URL + '/AddComment/', forumComment)
										.then(function(response) {
											alert(response.data);
										return response.data;
												}, 
										function(errResponse) {
											console.error('Error while creating blogComment');
											return $q.reject(errResponse);
												});
										
									},
									
									
									
									fetchAllComments : function(id) {
										console.log("--> ForumCommentService : calling 'fetchAllcomments' method with id :" +id);
												return $http
												.get(BASE_URL + '/ForumIdlist/'+ id)
												.then(function(response) {
												$rootScope.selectedForum = response.data;
												return response.data;
													}, 
												function(errResponse) {
												console.error('Error while fetching comments');
												return $q.reject(errResponse);
													});
									 },
									
									
									 		
									    
										     
											    	}
			
																		     
				                   
						
					
				
			
}]);