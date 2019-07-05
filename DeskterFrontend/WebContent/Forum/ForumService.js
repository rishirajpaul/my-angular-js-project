app.factory('ForumService', ['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
			console.log("ForumService...")
			var BASE_URL = 'http://localhost:8081/Deskter'
				return {
				
			//Show all Forums .....
			
				fetchAllForums : function() {
					console.log("--> ForumService : calling 'fetchAllForums' method.");
					     return $http
						 .get(BASE_URL + '/forum')
						 .then(function(response) {
						 return response.data;
								}, 
						 function(errResponse) {
						 console.error('Error while fetching Forums');
						 return $q.reject(errResponse);
								});
						
				},
				
					   //Create New Forum.....By User....
							
					     createForum : function(forum) {
								console.log("--> ForumService : calling 'createForum' method.");
								return $http
								.post(BASE_URL + '/Addforum/', forum )
								.then(function(response) {
								return response.data;
										}, 
								function(errResponse) {
									console.error('Error while creating forum');
									return $q.reject(errResponse);
										});
								
					     },
					   
					     
					     
					     
					     //fetchAllApprovedForums......................	
							
					     fetchAllApprovedForum : function() {
								console.log("--> ForumService : calling 'fetchAllApprovedForums' method.");
								return $http
								.get(BASE_URL + '/approvedforum')
								.then(function(response) 
								{
								return response.data;
								}, 
								function(errResponse) 
								{
								console.error('Error while fetching Forums');
								return $q.reject(errResponse);
								});
								},
							
					     
					     
					     
								
								
								//Blogs Approved by ADMIN...........
								approveForum : function(forum, id)
								{
									console.log("-->ForumService : calling approveForum() method : getting forum with id : " + id);
									return $http.put(BASE_URL+'/approveForum/'+ id, forum).then
												(function(response) 
												{
													return response.data;
												},
												function(errResponse) 
												{
													console.log("Error while approving Forum");
													return $q.reject(errResponse);
												}
												);
								},
								
								rejectForum : function(forum, id) 
								{
									console.log("-->ForumService : calling rejectForum() method : getting forum with id : " + id);
									return $http.put(BASE_URL+'/unapproveForum/'+ id, forum).then
												(function(response)
												{
													return response.data;
												},
												function(errResponse)
												{
													console.log("Error while rejecting Blog");
													return $q.reject(errResponse);
												}
											    );
								},
								

								
								
								
								
								
								
					     
					   //Forum Details of a Single Forum....
							
							getSelectedForum : function(id) {
								console.log("-->ForumService : calling getSelectedForum() method with id : " + id);
								return $http
											.get(BASE_URL+'/forum/'+ id)
											.then(function(response) {
												$rootScope.selectedForum = response.data;
												return response.data;
											},
											function(errResponse) {
												console.error('Error while Fetching Forum.');
												return $q.reject(errResponse);
											});
						
							},
					     
					     
					     
					     
				};
			}]);