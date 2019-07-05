app.factory('JobService', ['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
			console.log("JobService...")
			var BASE_URL = 'http://localhost:8081/Deskter'
				return {
				
				
				// Show all Job List...
				
				fetchAllJobs : function() {
					console.log("--> JobService : calling 'fetchAllJobs' method.");
									return $http
									.get(BASE_URL + '/jobs')
									.then(function(response) {
									return response.data;
										}, 
									function(errResponse) {
									console.error('Error while fetching Jobs');
									return $q.reject(errResponse);
										});
									},
									
							
									
									
									  // Selected Job Details................				
									
									getSelectedJob : function(id) {
										console.log("-->JobService : calling getSelectedJob() method with id : " + id);
										return $http
											.get(BASE_URL+'/jobDetails/'+ id)
											.then(function(response) {
									    	$rootScope.selectedJob = response.data;
											return response.data;
												},
											function(errResponse) {
											console.error('Error while Fetching job.');
											return $q.reject(errResponse);
												});
										},		
									
																	
									
									
									//Create New Job.......
									
									createJob : function(job) {
									    console.log("--> JobService : calling 'createJob' method.");
										return $http
										.post(BASE_URL + '/newJob/', job)
										.then(function(response) {
										return response.data;
											}, 
										function(errResponse) {
										console.error('Error while creating job');
										return $q.reject(errResponse);
											});
								},
								
								
								
								//Update Job Details...............
								
								updateJob : function(job, id) {
									console.log("--> JobService : calling 'updateJob' method.");
									return $http
									.put(BASE_URL+'/updateJobDetails/'+id, job)
									.then(function(response) {
									return response.data;
										},
									function(errResponse) {
									console.error('Error while updating Job...');
									return $q.reject(errResponse);
										});
									},
								
								
								
								
								
//############################################### JOB APPLICATION #############################################
								
								
								fetchAllJobApplications : function()
								{
									console.log("-->JobService : calling 'fetchAllJobApplications' method ");
									return $http
									.get(BASE_URL + '/jobApplications')
									.then(function(response) {
													
													return response.data;
											}, 
												function(errResponse) {
													console.error('Error while fetching JobApplications');
													return $q.reject(errResponse);
												});
								},

								
								// Approved Job Application by ADMIN........................
								
								approveJobApplication : function(jobApplication, id) 
								{
									console.log("-->JobService : calling approveJobApplication() method : getting job application with job application id : " + id);
									return $http.put(BASE_URL+'/selectedJobApplication/'+ id, jobApplication).then
												(function(response)
												{
													return response.data;
												},
												function(errResponse)
												{
													console.log("Error while approving job application");
													return $q.reject(errResponse);
												}
											    );
								},
								
								
								
								
					
								//Apply for Job...........
								
								applyForJob : function(job,jobId) {
									console.log("-->JobService : calling 'applyForJob' method with jobId:"+jobId);
									return $http
												.post(BASE_URL+'/newJobApplication/'+jobId,job)
												.then(function(response) {
													return response.data;
												},
												function(errResponse) {
													console.error('Error while applying for Job...');
													return $q.reject(errResponse);
												});
				
					},
								
								
								
								
								
								
				// Rejected Job Application by ADMIN........................
								
								rejectJobApplication : function(jobApplication, id) 
								{
									console.log("-->JobService : calling rejectJobApplication() method : getting job application with job application id : " + id);
									return $http.put(BASE_URL+'/rejectedJobApplication/'+ id, jobApplication).then
												(function(response)
												{
													return response.data;
												},
												function(errResponse)
												{
													console.log("Error while rejecting job application");
													return $q.reject(errResponse);
												}
											    );
								},
											

								
								
									
									
									
			}
}]);