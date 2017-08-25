var app = angular.module('collaboration', [ 'ngRoute', 'ngCookies' ]);
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

	}).when('/myProfile', {
		templateUrl : 'c_user/myProfile.html',
		controller : 'FriendController'

	}).when('/myJobs', {
		templateUrl : 'c_user/myProfile.html',
		controller : 'JobController'
	})

	.when('/upload', {
		templateUrl : 'c_user/fileUpload.html',
	})

	.when('/addBlogs', {
		templateUrl : 'c_blog/blogs.html',
		controller : 'BlogController'

	})

	.when('/viewBlogs', {
		templateUrl : 'c_blog/blogs.html',
		controller : 'BlogController'
	}).when('/viewBlog', {
		templateUrl : 'c_blog/viewBlog.html',

	})

	.when('/cmred', {
		templateUrl : 'c_blog/comment-redirect.html',
	}).when('/viewForums', {
		templateUrl : 'c_forum/forums.html',
		controller : 'ForumController'
	}).when('/addForum', {
		templateUrl : 'c_forum/forums.html',
		controller : 'ForumController'

	}).when('/viewForum', {
		templateUrl : 'c_forum/viewForum.html',
		controller : 'ForumController'

	}).when('/fmred', {
		templateUrl : 'c_forum/forum-redirect.html',
		controller : 'ForumController'

	})

	.when('/admin', {
		templateUrl : 'c_admin/adminHome.html',
	}).when('/manageJobs', {
		templateUrl : 'c_admin/manageJobs.html',
		controller : 'AdminController'

	}).when('/addJobs', {
		templateUrl : 'c_admin/manageJobs.html',
		controller : 'JobController'

	}).when('/viewJobs', {
		templateUrl : 'c_job/jobs.html',
		controller : 'JobController'
	}).when('/appliedJobs', {
		templateUrl : 'c_admin/manageJobs.html',
		controller : 'AdminController'
	})

	.when('/jred', {
		templateUrl : 'c_admin/jred.html',
	})

	.when('/manageEvents', {
		templateUrl : 'c_admin/manageEvents.html',
		controller : 'AdminController'
	}).when('/addEvents', {
		templateUrl : 'c_admin/manageEvents.html',
		controller : 'EventController'

	}).when('/ered', {
		templateUrl : 'c_admin/ered.html',
	})

	.when('/viewEvents', {
		templateUrl : 'c_event/viewEvents.html',
		controller : 'EventController'
	})
	.when('/manageBlogs', {
		templateUrl : 'c_admin/manageBlogs.html',
		controller : 'AdminController'
	})
	.when('/manageForums', {
		templateUrl : 'c_admin/manageForums.html',
		controller : 'AdminController'
	})
	.when('/manageUsers', {
		templateUrl : 'c_admin/manageUsers.html',
		controller : 'AdminController'
	})
	.when('/viewUsers', {
		templateUrl : 'c_user/userList.html',
		controller : 'UserListController'
	})
	
	.when('/pendingRequests',
			{
				templateUrl : 'c_user/myProfile.html',	
				controller : 'FriendController'
			})
			.when('/myFriends',
			{
				templateUrl : 'c_user/myProfile.html',	
				controller : 'FriendController'
			})	
			.when('/sentRequests',
			{
				templateUrl : 'c_user/myProfile.html',	
				controller : 'FriendController'
			})
			
			.when('/crred', {
		templateUrl : 'c_friend/request-redirect.html',
	})

	.when('/Chat',
			{
				templateUrl : 'c_chat/chat.html',
				controller : 'ChatController'	
			})
			
			.when('/viewProfile',
		{
			templateUrl : 'c_friend/viewProfile.html',
		})		

	.otherwise({
		redirectTo : '/'
	});

});

app.run(function($rootScope, $location, $cookieStore, $http) {
	$rootScope.$on('$locationChangeStart', function(event, next, current) {
		console.log("$locationChangeStart")

		var userPages = [ '/myProfile', 'myFriends', 'pendingRequests',
				'sentRequests', '/upload', '/viewUsers', '/addBlogs',
				'/addForum', '/viewProfile', '/viewBlogs', '/viewBlog',
				'/viewForum', '/viewForums' ];
		var adminPages = [ '/admin', '/manageUsers', '/manageJobs',
				'/manageEvents', '/manageForums', '/manageBlogs', '/addEvents',
				'/addJobs', '/jred', '/ered', '/appliedJobs' ];

		var currentPage = $location.path();

		var isUserPage = $.inArray(currentPage, userPages);
		var isAdminPage = $.inArray(currentPage, adminPages);

		var isLoggedIn = $rootScope.currentUser.username;

		console.log("isLoggedIn:" + isLoggedIn)
		console.log("isUserPage:" + isUserPage)
		console.log("isAdminPage:" + isAdminPage)

		if (!isLoggedIn) {

			if (isUserPage != -1 || isAdminPage != -1) {
				console.log("Navigating to login page:")
				alert("You need to Login first!")
				$location.path('/login');
			}
		}

		else // logged in
		{

			var role = $rootScope.currentUser.role;
			if (isAdminPage != -1 && role != 'ADMIN') {
				alert("You cannot view this page as a " + role)
				$location.path('/');
			}
		}
	});

	// to keep the user logged in after page refresh
	$rootScope.currentUser = $cookieStore.get('currentUser') || {};
	if ($rootScope.currentUser) {
		$http.defaults.headers.common['Authorization'] = 'Basic'
				+ $rootScope.currentUser;
	}
});