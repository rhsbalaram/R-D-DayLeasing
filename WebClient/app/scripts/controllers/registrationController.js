//'use strict';
//
///**
// * @ngdoc function
// * @name dayLeasingApp.controller:AboutCtrl
// * @description
// * # AboutCtrl
// * Controller of the dayLeasingApp
// */
//angular.module('dayLeasingApp')
//.controller('registrationController',function ($scope,$location,$state,serviceCalls) {
//	$scope.register=function(userInfo){
//		var user={
//			"fname":"FNAME",
//			"mname":"MNAME",
//			"lname":"LNAME",
//			"dob":"2012-04-23T18:25:43.511Z",
//			"gender": false,
//			"address1":"ADDRESS1",
//
//			"state":"STATE",
//			"city":"CITY",
//			"zipCode":"2333",
//			"country":"COUNTRY",
//
//			"memberType":"MEMBERTYPE",
//			"emailId":"sreenath@gmail.com",
//			"isActive": false,
//			"userClass":"USERCLASS",
//			"address2":"ADDRESS2",
//			"userStatus":"USERSTATUS",
//			"emailValid":true,
//			"phone":"PHONE",
//			"phoneValid":true,
//			"guest of":"GUEST OFF",
//			"huntDate":"2012-04-23T18:25:43.511Z",
//				 "vehicle":"VEHICLE",
//				 "vehicleColor":"VEHICLECOLOUR",
//				 "vehicleLicense":"VEHICLELICENSE",
//				 "huntingLicense":"HUNTING COURSE",
//				 "safetyCourse":"SAFETY COURSE",
//				"startingField":"srikanth",
//				"currentField":"CurrentField",
//				"photo":"Photo",
//				"source":"Source",
//				
//			    "createdDate":"2012-04-23T18:25:43.511Z",
//			    "lastLogin":"LASTLOGIN",
//			    
//			    "password":"admin"
//			};
//		serviceCalls.register(user)
//		.then(function(response){
//			console.log('success '+response);
//			$scope.valid=true;     // mail validation.
//			$scope.enable=true;    // for disabling the textboxs  and button
//			$scope.clicked=false; //for button click
//		}).catch(function(response){
//			$scope.valid=false;
//			
//			console.log('error in registration');
//		});
//	};
//});
//	
//
//
//    
//	
//	        
//	   
//
//	   
//  
//  
//
//
//
//
