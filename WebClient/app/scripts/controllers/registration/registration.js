'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:RegistratinController
 * @description # RegistratinController of the dayLeasingApp
 */
angular.module('dayLeasingApp')
.controller('RegistrationController',function ($scope,$state,$location,serviceCalls,$rootScope,vcRecaptchaService) {
	$scope.inputType="password";
	$scope.showpassword=function(){
		if($scope.inputType=='password'){
			$scope.inputType='text';
		}
		else{
			$scope.inputType='password';
		}
	};
	
	///recapcha setting to get client side and server side verfication.
	 $scope.response = null;
     $scope.widgetId = null;
     //public key
     $scope.model = {
         key: '6LfZTCcUAAAAANAMqzqeZDzV4LAkxz3r03auydx4'
     };

     $scope.setResponse = function (response) {
         console.info('Response available');

         $scope.response = response;
         $scope.valid=true;
     };

     $scope.setWidgetId = function (widgetId) {
         console.info('Created widget ID: %s', widgetId);

         $scope.widgetId = widgetId;
     };

     $scope.cbExpiration = function() {
         console.info('Captcha expired. Resetting response object');

         vcRecaptchaService.reload($scope.widgetId);

         $scope.response = null;
      };
      
      $scope.submitted=false;
      $scope.valid=false;

     $scope.validate = function (registration) {
    	 $scope.submitted=true;
    	 if(registration.$valid&&$scope.terms){
    		
    		 serviceCalls.registration($scope.user)
   			.then(function(response){
    				console.log('success '+response);
    				if(response.data.registration==true){
    					swal(
        		    		    'Success!',
        		    		    'Please check your registered email to activate password.',
        		    		    'success'
        		    		  );
    				}
    				else{
    					swal(
        		    		    'Error',
        		    		    'User already registered. Try forget/reset password',
        		    		    'error'
        		    		  );
    					
    				}
    				$state.go("login");
    			}).catch(function(response){
   				$scope.valid=false;
   			 vcRecaptchaService.reload($scope.widgetId);

   	         $scope.response = null;
    				
   	      swal(
	    		    'OOPS!',
	    		    'Something went worng.',
	    		    'error'
	    		  );
    			});

             /**need to do server side validation with generated response.
              * SERVER SIDE VALIDATION
              *
              * You need to implement your server side validation here.
              * Send the reCaptcha response to the server and use some of the server side APIs to validate it
              * See https://developers.google.com/recaptcha/docs/verify
              */
             

           
         
    	 }
        };
     
});
