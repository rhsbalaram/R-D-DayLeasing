'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:AboutCtrl
 * @description # AboutCtrl Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
.controller('loginController',function ($scope,$state,$location,serviceCalls,$rootScope,$cookies) {
	
	
	//if session is having cart data without authentication then retrieve it to get cart details
	if(sessionStorage.cart&&sessionStorage.cart!=""&&!$rootScope.config.headers.Authorization){
  		$scope.cart=JSON.parse(sessionStorage.cart);
  		if(sessionStorage.property&&sessionStorage.property!=""){
  		$scope.cart.property=JSON.parse(sessionStorage.property);
  		}
  	}
	sessionStorage.clear();
	if($scope.cart){
		sessionStorage.cart=JSON.stringify($scope.cart);
		if($scope.cart.property){
			sessionStorage.property=JSON.stringify($scope.cart.property);
		}
	}//for input type to text
	$scope.inputType="password";
	$scope.showpassword=function(){
		if($scope.inputType=='password'){
			$scope.inputType='text';
		}
		else{
			$scope.inputType='password';
		}
	}
	

	
	//validate the login
	$scope.validate=function(user){
	$scope.enable=true;
		serviceCalls.validateLogin(user)
		.then(function(userDetails){
			$scope.valid=false;
			if($scope.staySignedIn==true){
				$cookies.putObject('DayleasingAuthorization', userDetails.data);
			}
			//getting user details
			serviceCalls.getUserDetails(userDetails)
			.then(function(response){
				//if its hunter go to hunter dashboard else landowner
				if(response.data.memberType=="Hunter"){
					//if session is having cart
					if($scope.cart)
						{
						sessionStorage.cart=JSON.stringify($scope.cart);
						sessionStorage.property=JSON.stringify($scope.cart.property);
						$state.go('hunterBookSlotCartDetails',{propertyId:$scope.cart.property.propertyUuid});
						}
					//else normal dashboard
					else{
					$state.go("hunterProperties",{propertyId:'ALL'});
					}
				}
				else{
					$state.go("landownerDashboard");
				}
				
			})
			.catch(function(err){
				console.log('error in getting user details '+err);
				});
			
//			$state.go("addProperty");
		}).catch(function(err){
			$scope.enable=false;
			$scope.valid=true;
			console.log('error in login '+err);
		});
	};
});
			
		
		
		
	


    
	
	        
	   

	   
  
  




