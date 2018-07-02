'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:landownerCoupons
 * @description
 * # landownerCoupons
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
  .controller('hunterDetails', function ($state, $scope,serviceCalls,$filter) {
	  $scope.property="";
	  sessionStorage.removeItem('property');
	  if(!sessionStorage.Authorization){
		  $state.go("login");
		  throw new Error("Something went badly wrong!");
	  }
	  
	  serviceCalls.getAllHunterDetails()
		 .then(function(response){
			 $scope.hunters=response.data;
			
	
			 
		 }).catch(function(err){
			 
		 });
	  
	
  });