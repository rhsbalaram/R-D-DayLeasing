'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:LandownerAddPropertyCtrl_Description
 * @description
 * # LandownerAddPropertyCtrl_Description
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
  .controller('LandownerAddPropertyCtrl_Description', function ($timeout,$state, $scope,serviceCalls) {
	  
	  var accessToken = 'pk.eyJ1IjoibWFkaHVtb3Rpdml0eSIsImEiOiJjajdyYmo4cGwwcnIxMzducXNtbmxyb2dpIn0.20dxjsGIN8G6gChj5MFDtg';
	   // Replace 'mapbox.streets' with your map id.
	   var mapboxTiles = L.tileLayer('https://api.mapbox.com/styles/v1/mapbox/satellite-streets-v10/tiles/256/{z}/{x}/{y}?access_token=' + accessToken, {
	       attribution: ''
	   });
	    $scope.map = L.map('outlinemap')
	       .addLayer(mapboxTiles)
	       .setView([33.123927, -96.821845], 15);
	    
	    $scope.setFocus=function(index){
	    	if(index!="feature_0"){
	    		 $timeout(function(){
		    		 document.getElementById(index).focus(); 
		    	 },1);
	    	}
	    	
	    	
	    };
	    $scope.hasBookings=true;
	    
	    
	    
	   
	   	
	    if($scope.property&&$scope.property.outLine){
	    	serviceCalls.getReservationStatusOfProperty($scope.property.propertyUuid).then(function(response){
				
				if(response.data.status!=true){
					$scope.hasBookings=false;
				}
			});
	    	
	 		 //add outline
	 	var layerPolygon = new L.Polygon(JSON.parse($scope.property.outLine),{
               color: '#ff0',
               weight: 2,
               opacity:1,	
				fill:false
           });
	 		$scope.map.addLayer(layerPolygon);
	 		
           $timeout(function(){
	 			
           	$scope.map.fitBounds(layerPolygon._latlngs);
	 		},0);
	 	 
	 	 }
	    else{
	    	$state.go("landownerAddProperty.outline");
	    }
  });