'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:ForgetPassword
 * @description # ForgetPassword of the dayLeasingApp
 */
angular.module('dayLeasingApp')
.controller('ForgetPassword',function ($scope,$state,$location,serviceCalls,$rootScope,vcRecaptchaService) {
	
      $scope.submitted=false;
      

     $scope.validate = function (registration) {
    	 $scope.submitted=true;
    	 if(registration.$valid){
    		
    		 serviceCalls.sendVerification($scope.user.emailId)
   			.then(function(response){
    				console.log('success '+response);
    				if(response.data.status==true){
    					swal(
        		    		    'Success!',
        		    		    'Please check your registered email to change password.',
        		    		    'success'
        		    		  );
    					$state.go("login");
    				}
    				else{
    					swal(
        		    		    'Error',
        		    		    'User not registered. Please register',
        		    		    'error'
        		    		  );
    					$state.go("registration");
    					
    				}
    				
    			}).catch(function(response){
   				
    				
   	      swal(
	    		    'OOPS!',
	    		    'Something went worng.',
	    		    'error'
	    		  );
    			});

                      
         
    	 }
        };
     
});
