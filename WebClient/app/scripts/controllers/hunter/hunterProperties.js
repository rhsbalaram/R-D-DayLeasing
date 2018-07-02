'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:hunterProperties
 * @description
 * # hunterProperties
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
  .controller('hunterProperties', function ($timeout,$state,$window, $scope,HunterServiceCalls,$stateParams,$rootScope) {
	  var propertyid=$stateParams.propertyId;
	  if(!sessionStorage.Authorization){
		  $rootScope.login=false;
	  }
	  else{
		  $rootScope.login=true;
	  }
	 
	   //getting all hunter properties
	  HunterServiceCalls.getPropertiesForHunter(propertyid)
		 .then(function(response){
			
			 $scope.properties=response.data;
			
		})
		.catch(function(err){
			
			});
	   //getting paticular property full details/entire property object
	   $scope.getSlotsOfProperty=function(propertyUuid){
		   HunterServiceCalls.getSinglePropertyForHunter(propertyUuid).then(function(response){
				 $scope.property=response.data;
				 sessionStorage.property=JSON.stringify($scope.property);
		   $state.go("hunterBookSlot",{propertyId:propertyUuid});
	   });
	   }
	  
	 
	   	
	  
	    
	     
	   	
	 	
	 	 
	 	
	  
  });