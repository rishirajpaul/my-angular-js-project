app.controller('JobController', [
		'$scope',
		'JobService',
		'$location',
		'$rootScope',
		'$route',
		'$window',
		function($scope, JobService, $location, $rootScope,$route,$window) {
			console.log("JobController...")

			var self = this;
			self.job = {
			    errorCode : '',
				errorMessage : '',
				jobId : '',
				companyName : '',
				location : '',
				description : '',
				jobDate : '',
				status : '',
				
				}
			self.jobs = [];
			
			
			   self.jobApplication = {
						
	            		jobApplicationId : '',
						userid : '',
						Jobid : '',
						status : '',
						remarks : '',
						errorCode: '',
						errorMessage: ''
				};
				
				self.jobApplications = [];
			
			
			
          
			
			
			//Show all Jobs...............
			
			self.fetchAllJobs = function() {
				console.log("--> JobController : calling fetchAllJobs method.");
				JobService.fetchAllJobs().then(
				function(s) {
				self.jobs = s;
					}, 
				function(errResponse) {
				console.error('Error while fetching jobs...');
					});
				};
				self.fetchAllJobs();
				
				

		
		
		
		//Create New Job.....................
		
				self.createJob = function(job) {
					console.log("--> JobController : calling createJob method.");
					JobService.createJob(job).then(
					function(s) {
						self.job = s;
						$location.path('/index');
						alert('Job Created Successfully...');
						},
					function(errResponse) {
					console.error('Error while creating job...');
						});
					};
				
		
					
					
					//Show Job Details by Job Id............
					
					self.getSelectedJob = function(id) {
						console.log("-->JobController : calling getSelectedJob method : getting job with id : " + id);
						JobService.getSelectedJob(id).then(
								function(s) {
								self.job = s;
								console.log('id '+ self.job.jobId);
								$rootScope.job= self.job;
								console.log(' r id '+ $rootScope.job.jobId);
								$location.path('/JobDetail');
									}, 
								function(errResponse) {
								console.error('Error while fetching Job...');
									});
										};
					
					
										
										 //Update Job Details...........		          

										 self.updateJob = function(job, id) {
											console.log("--> JobController : calling updateJob method.");
											JobService.updateJob(job, id).then(
												function(s) {
												self.jobs = s;
												$location.path('/JobDetail');
													}, 
												function(errResponse) {
											console.error('--> JobController : Error while updating Job...');
												});
											};
													
										
										
										
										
										
					
					
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ JOB aPPLICATION $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
										
										
										
										self.fetchAllJobApplications = function()
					  					{
					  						console.log("-->JobController : calling fetchAllJobApplications method : ");
					  						JobService.fetchAllJobApplications().then
					  						(function(d) 
					  						{
					  							self.jobApplications = d;
					  									
					  							
					  						},
					  						function(errResponse) 
					  						{
					  							console.error('Error while fetching BlogApplications...');
					  						}
					  						);
					  					};
									self.fetchAllJobApplications();
			
										
										
									
									
									
									//Approve Job Application by ADMIN.........
									
									self.approveJobApplication = function(jobApplication, id)
									{
										console.log("-->JobController : calling approveJobApplication() method : job application with job application id is : " + id);
										console.log("-->JobController",self.jobApplication);
										JobService.approveJobApplication(jobApplication, id).then
										(
												function(d)
												{
												alert('Accept Job Application?'),
												self.jobApplication=d,
												self.fetchAllJobApplications(id);
												},
												function(errResponse) 
												{
													console.error("Error while approving job application...")
												}
										);
									};
									
									
				// Rejected Job Application by ADMIN..................
									
									self.rejectJobApplication = function(jobApplication, id) 
									{
										console.log("-->JobController : calling rejectJobApplication() method : JobApplication jobApplicationId is : " + id);
										console.log("-->JobController",self.jobApplication);
										JobService.rejectJobApplication(jobApplication, id).then
										(
												function(d)
												{
												alert('Reject Job Application?'),
												self.jobApplication=d,
												self.fetchAllJobApplications(id);
												},
												function(errResponse) 
												{
													console.error("Error while rejecting job application...")
												}
										);
									};

				
				
				
									//Apply For Job...............Create Job Application..........				
									self.applyForJob = function(job,jobId) {
											console.log("-->JobController : calling 'applyForJob' method with jobId:"+jobId);
											JobService
														.applyForJob(job,jobId)
														.then(function(d) {
															self.jobApplication = d;
															alert("You have successfully applied for the job...");
															//self.listJobs();
															console.log("-->JobController : ", self.jobApplication);
															console.log("-->JobController : ", self.job);
															},
														function(errResponse) {
															console.error('Error while applying for job...')
														});

										};
										
										
										
										
												
									
									
							
										
										
										
										self.reset = function() {
											 console.log('submit a new JobApplication', self.jobApplications);
											 self.jobApplication = {
													 jobApplicationId : '',
													 userid : '',
													 Jobid : '',
													 status : '',
													 remarks : '',
													 errorCode: '',
													 errorMessage: ''
															
											};
										};
														
				
	
	
	self.reset = function() {
					console.log('submit a new Job', self.jobs);
					self.job = {
						    errorCode : '',
							errorMessage : '',
							jobId : '',
							companyName : '',
							location : '',
							description : '',
							jobDate : '',
							status : '',
							
							};
					$scope.myForm.$setPristine(); // reset form...
				};
	} ]);			