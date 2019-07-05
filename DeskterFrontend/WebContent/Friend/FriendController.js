app.controller('FriendController', [ 
         'FriendService', 'UserService', '$scope','$location', '$rootScope',
		function(FriendService, UserService, $scope, $location, $rootScope) {
			console.log("FriendController...");

			var self = this;
			self.friend = {
				id : '',
				userId : '',
				friendId : '',
				status : '' ,
				isOnline : ''
				
			};
			self.friends = [];
			self.Users = [];
			//get my all frnd
			self.getMyFriends = function() {
				console.log("--> MyFriendsList");
				var currentUser = $rootScope.currentUser
				if (typeof currentUser == 'undefined') {
					alert("Please Sign in to check Friend List...")
					console.log('User not logged In  to check Friend List...');
					$location.path('/Login');
				};
				FriendService.getMyFriends().then(
						function(d) {
							self.friends = d;
							console.log("My Friendlist.");
						},
						function(errResponse) {
							console.error("Error while fetching Friends.");
						}
					);
			};
			
			self.getMyFriends();
			
			
			self.getSelectedFriend = function(id) {
				console.log("-->FriendController : calling getSelectedFriend method : getting friend with id : " + id);
				FriendService.getSelectedFriend(id).then(
						function(d) {
						self.friend = d;
						self.user = d;
						console.log('id '+ self.friend.friendId);
						$rootScope.friend= self.friend;
						$rootScope.user= self.user ;
						console.log(' r id '+ $rootScope.friend.friendId);
						$location.path('/friendDetails');
							}, 
						function(errResponse) {
						console.error('Error while fetching Friend...');
							});
								};
			
			
			
								self.sendFriendRequest = function sendFriendRequest(friendId) {
									console.log("--> sendFriendRequest : "+friendId);
									FriendService.sendFriendRequest(friendId).then(
									function(d) {
									self.friend = d;
									alert('Friend Request Sent...')
									},
									function(errResponse) {
									console.error('Error while friends...');
									});
									
											
										
								};
			
			
			
			
								self.getNewFriendRequests = function() {
									console.log("--> My Friend Requests...");
									var currentUser = $rootScope.currentUser
									if (typeof currentUser == 'undefined') {
										alert("Please Sign in to check Friend List...")
										console.log('User not LoggedIn  to check Friend List...');
										$location.path('/Login');
									};
									FriendService.getNewFriendRequests().then(
											function(d) {
												self.newFriendRequests = d;
												$rootScope.newFriendRequests=self.newFriendRequests;
												console.log("Got the Friendlist.");
											},
											function(errResponse) {
												console.error("Error while fetching Friends.");
											}
										);
								};
			
								self.getNewFriendRequests();
			
			
								self.acceptFriend = function(f, id) {
									console.log("--> FriendController : calling nnnn  'acceptFriend' method with id : "+id);
									console.log("--> FriendController",f);
									FriendService
													.acceptFriend(f, id)
													.then(function(d) {
														self.friend = d;
														self.getMyFriends();
														alert('Friend request accepted successfully... ');
														$location.path('/index');
														
													},
													function(errResponse) {
														console.error("Error while updating friend.");
													});
								};
								
								
								  self.rejectFriend = function(f, id) {
										console.log("--> FriendController : calling   'rejectFriend' method with id : "+id);
										console.log("--> FriendController",f);
										FriendService
														.rejectFriend(f, id)
														.then(function(d) {
															self.friend = d;
															self.getMyFriends();
															alert('Friend request rejected successfully... ');
															$location.path('/index');
															
														},
														function(errResponse) {
															console.error("Error while updating friend.");
														});
									};	
								
								self.unFriend = function(friend, id) {
									console.log("--> FriendController : calling 'unFriend' method with id : "+id);
									console.log("--> FriendController",self.friend);
									FriendService
													.unFriend(friend, id)
													.then(function(d) {
														self.friend = d;
														self.getMyFriends();
														$location.path('/index');
													},
													function(errResponse) {
														console.error("Error while updating friend.");
													});
								};
			
			
			
			
			
			
			
			
			
			
			
} ]);