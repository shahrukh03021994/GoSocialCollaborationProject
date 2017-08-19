var app = angular.module('collaboration', [ 'ngRoute' ,'ngCookies']);
app.config(function($routeProvider, $locationProvider)

{
	$locationProvider.hashPrefix('');

	$routeProvider.when('/', {
		templateUrl : 'views/home.html',
	}).when('/login', {
		templateUrl : 'views/login.html',
		

	})

	.when('/register', {
		templateUrl : 'c_user/register.html',
		controller : 'UserController'

	})
	.when('/myProfile',{
		templateUrl : 'c_user/myProfile.html',
		controller : 'UserController'

		
	})
	.when('/viewBlog',{
		templateUrl : 'c_blog/viewBlog.html',
	

		
	})

});