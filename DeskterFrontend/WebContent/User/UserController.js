app.controller('UserController', [
		'$scope',
		'UserService',
		'$location',
		'$rootScope',
		'$route',
		'$window',
		function($scope, UserService, $location, $rootScope,$route,$window) {
			console.log("UserController...")

			var self = this;
			self.Users = {
			    errorCode : '',
				errorMessage : '',
				userId : '',
				name : '',
				password : '',
				role : '',
				email : '',
				description : '',
				gender : '',
				dob : '',
				address : '',
				contactNo : '',
				IsOnline : '',
				status : ''
				}
			self.Users = [];

			//fetching all users.............
			self.fetchAllUsers = function() {
				console.log("--> UserController : calling fetchAllUser method.");
				UserService.fetchAllUsers().then(
				function(e) {
				self.Users = e;
								}, function(errResponse) {
									console.error('Error while fetching Users...');
								});
					};
				self.fetchAllUsers();
			
				//Profile Check...................		
				self.getSelectedUsers = function(id) {
					console.log("-->UserController : calling getSelectedUser method : getting user with id : " + id);
					UserService.getSelectedUsers(id).then(
							function(e) {
								self.User = e;
								$rootScope.User=self.User;
								$location.path('/DisplayUser');
							}, 
							function(errResponse) {
								console.error('Error while fetching User...');
							});
				};
		
				
			
				
				
				// LogOut User......
				
				self.logout = function(user,id) 
				{
					console.log("--> UserController : calling logout method.");
					alert(user.userId+id);
					UserService.logout(user,id);
					$rootScope.currentUser = {};
					//$localStorage.currentUser.remove('currentUser');
				    //$cookieStore.remove('currentUser');
					
					console.log("-->UserController : User Logged out.");
					$window.location.reload();
					$location.path('/index');
				}
	
				
				
				
				
				
				
				
				//Create New User..........................
				self.createUser = function(user) {
					console.log("--> UserController : calling createUser method.");
					UserService.createUser(user).then(
							function(e) {
								self.user = e;
								alert('User Created Successfully...')
							},
							function(errResponse) {
								console.error('Error while creating user...');
							});
				};
				
				
				//LogIn UserAccount.........
				
				self.authenticateUser = function(user) {
						console.log("-->UserController : calling authenticateUser method.");
						UserService.authenticateUser(user).then(
							function(d) {
								self.user = d;
					$rootScope.currentUser =self.user;
								console.log (self.user.userId);
								console.log ($rootScope.currentUser.role);
								$location.path('/test');
									}, 
							function(errResponse) {
							console.error('Error while fetching User...');
							});
						};			
						
						
						self.sendFriendRequest = function sendFriendRequest(friendId) {
							console.log("--> sendFriendRequest : "+friendId);
							UserService.sendFriendRequest(friendId).then(
							function(d) {
							self.friend = d;
							alert('Friend Request Sent...')
							},
							function(errResponse) {
							console.error('Error while friends...');
							});
						}
							
						
						
						
			
			self.reset = function() {
				console.log('submit a new User', self.user);
				self.User = {
						    errorCode : '',
							errorMessage : '',
							userId : '',
							name : '',
							password : '',
							role : '',
							email : '',
							description : '',
							gender : '',
							dob: '',
							address : '',
							contactNo : '',
							IsOnline : '',
							status : ''

				};
				$scope.myForm.$setPristine(); // reset form...
			};
		} ]);
