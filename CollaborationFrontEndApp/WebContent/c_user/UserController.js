'use strict';
app.controller('UserController',function($scope, UserService, $location, $rootScope,$cookieStore, $http) {
					console.log('-----starting controller')

					$scope.user={
						username:'', 
						first_name:'',
						last_name:'', 
						dob:'', 
						gender:'',
						mail_id:'', 
						password:'', 
						status:'',
						role:'', 
						isOnline:'',
						birthdate:'',
						last_seen:'',
						errorMsg:'',
						errorCode:'',};

					$scope.message;
			
					
					$scope.registerUser=function()
					{
						console.log("Entering Register User")
						UserService.registerUser($scope.user)
						.then(
								function(d) {
									$location.path("/")
									$scope.user=d;
								},
								function(errResponse) {
									console.error('Error while creating user');
								});
					};
					
					
					
					
					
					
					
					
					
					
					
					$scope.authenticate = function(user)
					{
						console.log("Authenticate Function");
						UserService.authenticate(user)
						.then 
						(
							function(d)
							{
								$scope.user = d;
								console.log("User ErrorCode - "+$scope.user.errorCode)
								console.log(" status "+$scope.user.status)
								console.log(" uservv"+$scope.user.username)
								if($scope.user.status == 'R')
									{
										alert("Your Registeration is Rejected. Please Contact ADMIN");
										user.setErrorCode("404");
										user.setErrorMessage("Your Registeration is Rejected");
									}
								if($scope.user.status == 'N')
								{
									alert("Your Registeration is Not Yet Approved. Please wait for some time.");
									user.setErrorCode("404");
									user.setErrorCode("Your Registeration is Not Approved");
								}
								if($scope.user.username ==null && $scope.user.password==null)
								{
									alert("Invalid Username or Password");
									console.log("Invalid Username or Password")
									$location.path("/login");
								}
								
								else
								{
									console.log("Valid Credentials, Navigating to home page "+$scope.user.status)
									$scope.message1="Successfully Logged in as ";
									$rootScope.currentUser = 
										{
											username: $scope.user.username,
											first_name: $scope.user.first_name,
											last_name: $scope.user.last_name,
											dob: $scope.user.dob,
											gender: $scope.user.gender,
											mail_id: $scope.user.mail_id,
											status: $scope.user.status,
											role: $scope.user.role,
											birthdate: $scope.user.birthdate,
											isOnline: $scope.user.isOnline,
											last_seen: $scope.user.last_seen
										};
									
									//$rootScope.currentUser = $scope.user
									
									$http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser;
									$cookieStore.put('currentUser', $rootScope.currentUser)
									var cookie = $cookieStore.get('currentUser');
                                    console.log(cookie)
                                    

									$location.path("/");
								}
							}, 	function(errResponse)
							{
								console.error("Error Authenticating User");
								$scope.message = "Invalid username or password.";
								$location.path("/login");
							}
						);
					};
					
					$scope.login= function()
					{
						console.log("Validating Login "+$scope.user);
						$scope.authenticate($scope.user);
					};
					
					
					$scope.logout= function()
					{
						console.log("Entering Logout Function");
						$rootScope.currentUser = {};
						$cookieStore.remove('currentUser');
						
						console.log("Calling Session Logout");
						UserService.logout()
                        alert("Successfully Logout...");

						$location.path('/login');
					};
					
					
					
					
				});