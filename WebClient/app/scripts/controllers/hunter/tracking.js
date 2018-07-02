'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:hunterBookingDetails
 * @description
 * # hunterBookingDetails
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
  .controller('tracking', function ($timeout,$state, $scope,HunterServiceCalls,$stateParams,$rootScope,$filter,$window,$interval) {
	  if(!sessionStorage.Authorization){
		  $state.go("login");
		  throw new Error("Something went badly wrong!");
	  }
	  
	  var accessToken = 'pk.eyJ1IjoiYmFsYXJhbWEiLCJhIjoiY2owZHB5YmE4MDBmaDMybnFwbndlOXMyNyJ9.h1rT5NxNRmge0EvD4MiwhA';
	   // Replace 'mapbox.streets' with your map id.
	   var mapboxTiles = L.tileLayer('https://api.mapbox.com/styles/v1/mapbox/satellite-streets-v10/tiles/256/{z}/{x}/{y}?access_token=' + accessToken, {
	       attribution: ''
	   });
	    $scope.map = L.map('outlinemap')
	       .addLayer(mapboxTiles)
	       .setView([33.123927, -96.821845], 15);
	    var circleLayer = new L.circleMarker([0, 0], {
	    	radius:7,
 	 		color: '#ff0',
	        weight: 2,
	        opacity:1,
	 		fill:true,
	 		fillColor:'#0000ff',
	 		fillOpacity:1
	 		
        });
	    $scope.map.addLayer(circleLayer);
	    if(sessionStorage.trackProperty){
	    	var property=JSON.parse(sessionStorage.trackProperty);
	 		//adding outline 
	 	var layerPolygon = new L.Polygon(JSON.parse(property.outLine),{
               color: '#ff0',
               weight: 2,
               opacity:1,	
				fill:false
           });
	 		$scope.map.addLayer(layerPolygon);
	 		
           $timeout(function(){
	 			
           	$scope.map.fitBounds(layerPolygon._latlngs);
	 		},0);
           ///////adding slots
           angular.forEach(property.areas, function(area) {
	 	    	angular.forEach(area.slots, function(slot) {
	 	    		if(slot.outLine&&sessionStorage.trackLocation==slot.propertySlotsUuid)
	 	    			{
	 	    			slot.layerPolygon="";
	 	    			var layerPolygon = new L.Polygon(JSON.parse(slot.outLine),{
	 	    	 			color: '#FF0000',
	 	    		        weight: 2,
	 	    		        opacity:1,
	 	    		 		fill:true,
	 	    		 		fillOpacity:0.2
	 	                }).bindPopup('<div class="pop-font">Area: &nbsp;</div><div class="pop-orange-hed">'+area.propertyAreaName+'</div><br/><div class="pop-font">Slot: &nbsp;</div> <div class="pop-orange-hed">'+slot.slotName+'</div><br/><div class="pop-font">Slot Details: &nbsp;</div><div class="pop-orange-hed">'+slot.maxHunter+'</div>');
	 	    	 		$scope.map.addLayer(layerPolygon);
	 	    			}
	 	    		else if(slot.outLine)
 	    			{
 	    			slot.layerPolygon="";
 	    			var layerPolygon = new L.Polygon(JSON.parse(slot.outLine),{
 	    	 			color: '#0758FA',
 	    		        weight: 2,
 	    		        opacity:1,
 	    		 		fill:true,
 	    		 		fillOpacity:0.2
 	                }).bindPopup('<div class="pop-font">Area: &nbsp;</div><div class="pop-orange-hed">'+area.propertyAreaName+'</div><br/><div class="pop-font">Slot: &nbsp;</div> <div class="pop-orange-hed">'+slot.slotName+'</div>');
 	    	 		$scope.map.addLayer(layerPolygon);
 	    			}
	 	    		
	 	    		
	 	    	});
	 	    	
	 	    });
           getLocation();
	 	 }
	    else{
	    	$state.go("hunterProperties",{propertyId:'ALL'});
	    }
	 
	    //using accurate position plugin of leaflet
	    function onAccuratePositionProgress (e) {
	        
	    }

	    function onAccuratePositionFound (e) {
	    	console.log(e.accuracy);
	        circleLayer.setLatLng(e.latlng);
	        
	    }

	    function onAccuratePositionError (e) {
	        
	    }

	    $scope.map.on('accuratepositionprogress', onAccuratePositionProgress);
	    $scope.map.on('accuratepositionfound', onAccuratePositionFound);
	    $scope.map.on('accuratepositionerror', onAccuratePositionError);
	    $scope.stop=$interval(function(){
	    	getLocation();
	    },5000);
	    $scope.$on('$destroy',function(){
	        if($scope.stop){
	        	 $interval.cancel($scope.stop);
	        }
	            
	    });
	    
           function getLocation(){
	    $scope.map.findAccuratePosition({
	        maxWait: 2000, // defaults to 10000
	        desiredAccuracy: 20 // defaults to 20
	    });
	    
	   
	    
	    
         }
	 	  
  });