
/**
 * @ngdoc function
 * @name dayLeasingApp.controller:LandownerAddPropertyCtrl_Area_AllAreas
 * @description
 * # LandownerAddPropertyCtrl_Area_AllAreas
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
  .controller('LandownerAddPropertyCtrl_Area_AllAreas', function ($timeout,$state, $scope,serviceCalls) {
	  
	  
	
	   //remove areas which are not save using the index
	   if(sessionStorage.toRemoveArea=='true'){
	  		$scope.property.areas.splice(Number(sessionStorage.areaToRemove),1);
	  		sessionStorage.toRemoveArea="false";
	  		sessionStorage.property=JSON.stringify($scope.property);
		    	sessionStorage.removeItem('areaToRemove');
		    	
	  	}
	  
	
  });