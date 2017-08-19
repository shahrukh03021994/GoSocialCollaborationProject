'use strict';
app.service('UserService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	console.log("UserService...")
		var BASE_URL='http://localhost:8082/CollaborationControllersApp/'
			return {
		registerUser : function(user) {
				console.log("Creating user in UserService")
				return $http.post(BASE_URL+'add_User/',user)
													

				.then(
						function(response){
							alert("Regesteration Succcess. Wait for admin aproval")
							console.log(response.status)

	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while creating user');
	                        return $q.reject(errResponse);
	                    });
			},
			authenticate: function(user)
			{
				console.log("Entering Function Validate User")
				return $http.post(BASE_URL + "login", user)
				.then(
						function(response)
						{
							if(response.data.errorMessage == "Success")
								{
									$rootScope.currentUser = 
										{
											username: response.data.username,
											first_name: response.data.first_name,
											last_name: response.data.last_name,
											dob: response.data.user.dob,
											gender: response.data.gender,
											mail_id: response.data.mail_id,
											status: response.data.status,
											role: response.data.role,
											//birthdate: response.data.birthdate,
											isOnline: response.data.isOnline,
											last_seen: response.data.last_seen
										};

								}
							return response.data;
						},
						function(errResponse)
						{
							$rootScope.userLoggedIn = false;
							console.error(errResponse.status);
							console.error("Error while validating");
							return $q.reject(errResponse);
						});
			},
			
			logout: function()
			{
				console.log("Entering Logout")
				return $http.get(BASE_URL + "logout")
				.then
				(
					function(response)
					{
						return response.data;
					},
					function (errResponse)
					{
						console.log("Error Logging out");
						return $q.reject(errResponse);
					}
				);
			}
	
	
	
	
	
	
	
	}
	}]);