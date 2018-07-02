'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:LoginVerification
 * @description # LoginVerification of the dayLeasingApp
 */
angular.module('dayLeasingApp')
.controller('LoginVerification',function ($scope,$timeout,$state,$location,serviceCalls,$rootScope,$stateParams) {
	var tokenId=$stateParams.tokenId;
	 serviceCalls.emailVerification(tokenId)
		.then(function(response){
				console.log('success '+response);
				if(response.data.status==true){
					swal(
    		    		    'Success!',
    		    		    'Email Verification Success.',
    		    		    'success'
    		    		  );
				}else{
					swal(
    		    		    'Error',
    		    		    'Password Expired. Try forget/reset password',
    		    		    'error'
    		    		  );
				}
				
				
			 			
					 $state.go("login");
					 $timeout(function(){
			   				location.reload();
							 
						 		},3000);
				
				
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
	
});
