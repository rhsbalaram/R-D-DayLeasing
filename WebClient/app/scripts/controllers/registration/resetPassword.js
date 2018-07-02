'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:changePassword
 * @description # changePassword of the dayLeasingApp
 */
angular.module('dayLeasingApp')
.controller('changePassword',function ($scope, $timeout,$state,$location,serviceCalls,$rootScope,$stateParams) {
	var tokenId=$stateParams.tokenId;
	 serviceCalls.emailVerification(tokenId)
		.then(function(response){
				console.log('success '+response);
				if(response.data.status==true){
					 
				}else{
					swal(
    		    		    'Error',
    		    		    'Password Expired. Try forget/reset password',
    		    		    'error'
    		    		  );
					$state.go("login");
					 $timeout(function(){
			   				location.reload();
							 
						 		},3000);
				}
				
				
			}).catch(function(response){
				swal(
		    		    'Error',
		    		    'Password Expired. Try forget/reset password',
		    		    'error'
		    		  );
				$state.go("login");
				 $timeout(function(){
		   				location.reload();
						 
					 		},3000);
			});
	 
	 
	 $scope.submitted=false;
     $scope.valid=false;

    $scope.validate = function (registration) {
   	 $scope.submitted=true;
   	 if(registration.$valid&&$scope.user.password==$scope.user.repassword){
   		
   		 serviceCalls.changePassword(tokenId,$scope.user.password)
  			.then(function(response){
   				console.log('success '+response);
   				if(response.data.status==true){
   					swal(
       		    		    'Success!',
       		    		    'Password Changed.',
       		    		    'success'
       		    		  );
   				}
   				else{
   					swal(
       		    		    'Error',
       		    		    'Password Expired',
       		    		    'error'
       		    		  );
   					
   				}
   				$state.go("login");
   			 $timeout(function(){
   				location.reload();
				 
			 		},3000);
   				
   				
   			}).catch(function(response){
  				
   				
  	      swal(
	    		    'OOPS!',
	    		    'Password Expired.',
	    		    'error'
	    		  );
  	    $state.go("login");
   			});

          

          
        
   	 }
       };
	
});
