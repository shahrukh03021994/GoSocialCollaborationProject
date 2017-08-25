app.controller('JobController', function($scope,$location,JobService)
{
	console.log("Entering Job Controller")
	$scope.jobs;
	$scope.job={job_id:'', title:'', username:'', status:'', position:'', company:'', description:'', salary:'', location:'', q_10:'', q_12:'', q_ug:'', date:'', vacancy:''};
	$scope.message;
	$scope.myJobs;

	
	listJob=function()
	{
		console.log("Entering List Job Method")
		JobService.listJob()
		.then
		(
				function(response)
				{
					console.log("List Job Success "+response.status)
					$scope.jobs=response
				}
		)}
		listJob();
	
	$scope.addJobs=function()
	{
		console.log("Add Job Started")
		JobService.addJob($scope.job)
		.then
		(
				function(response)
				{
					console.log("Add Job Success "+response.status)
					alert("Job has been added")
					$location.path("/admin")
				}
		);
	}
	
	
	
	//user applied job in job controller
	getAppliedJobs = function()
	{
		console.log("Getting Applied Jobs")
		JobService.getAppliedJobs()
		.then
		(
			function(response)
			{
				console.log("Applied Jobs Recieved")
				$scope.myJobs = response.data;
			}
		

	)}
	getAppliedJobs();
})