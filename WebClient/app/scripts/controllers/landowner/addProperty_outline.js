'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:LandownerAddPropertyCtrl_Outline
 * @description
 * # LandownerAddPropertyCtrl_Outline
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
  .controller('LandownerAddPropertyCtrl_Outline', function ($timeout,$state, $scope,serviceCalls) {
	 
	  if(!sessionStorage.Authorization){
		  $state.go("login");
	  }
	   var accessToken = 'pk.eyJ1IjoibWFkaHVtb3Rpdml0eSIsImEiOiJjajdyYmo4cGwwcnIxMzducXNtbmxyb2dpIn0.20dxjsGIN8G6gChj5MFDtg';
	   // Replace 'mapbox.streets' with your map id.
	   var mapboxTiles = L.tileLayer('https://api.mapbox.com/styles/v1/mapbox/satellite-streets-v10/tiles/256/{z}/{x}/{y}?access_token=' + accessToken, {
	       attribution: ''
	   });
	   var map = L.map('outlinemap')
	       .addLayer(mapboxTiles)
	       .setView([33.123927, -96.821845], 15);
	   //can find in 
	 var searchControl=  new L.Control.Search({
	   		url: 'https://nominatim.openstreetmap.org/search?format=json&q={s}',
	   		jsonpParam: 'json_callback',
	   		propertyName: 'display_name',
	   		propertyLoc: ['lat','lon'],
	   		marker: false,
	   		autoCollapse: true,
	   		autoType: true,
	   		minLength: 2
	   	});
	   	map.addControl( searchControl );
	   	
	   	///
	 
	  //geocode to get the address of the location intially with the searched address 	
	   var	geocontrol = L.Control.geocoder();
	   	
	  	searchControl.on('search:locationfound', function (e, title, layer) {
	  		
	  		geocontrol.options.geocoder.reverse(e.latlng,map.options.crs.scale(map.getZoom()),function(response){
	  			 if(response){
	  				getAddress(response);
	  			 }
	  		});
	   		
	    	
	    });
	    //adding draw controls to map
	     var drawControl = new L.Control.Draw({
		  draw: {
	             polygon:{
	            	 shapeOptions: {
		        	 color: '#ff0',
		                weight: 2,
		                opacity:1,
						fill:false
		 		}
	     },
	             marker: false,
				 polyline:false,
				 rectangle:false,
				 circle:false
	         }
	        
	     });
	     map.addControl(drawControl);
	   	$scope.disableButton=true;
	   	 $scope.layerPolygon={};
	 	var layers=[];
	 	//dummy funtion to make the button enable able the polygon completed
	 	 $scope.click=function(){
	 		$timeout(function(){
	 			
	 			console.log($scope.disableButton);
	 		},1000);
	 		
	 	 }
	 	
	 	 // check if session is already containg the property
	 	 if($scope.property&&$scope.property.outLine){
	 		drawControl.remove();
	 		searchControl.remove();
	 		//add layer
	 		$scope.property.layerPolygon = new L.Polygon(JSON.parse($scope.property.outLine),{
                color: '#ff0',
                weight: 2,
                opacity:1,
				fill:false
            });
	 		map.addLayer($scope.property.layerPolygon);
	 		$scope.property.layerPolygon.editing.enable();
            $timeout(function(){
	 			
            	map.fitBounds($scope.property.layerPolygon._latlngs);
	 		},0);
	 		
	 		/*
	 	 	

 	 		
 	 		
 	 		*/
 	 		$scope.disableButton=false;
 	 		console.log($scope.disableButton);
 	 		$scope.click();
 	 		
	 		 
	 	 }
	 	//else create a new polygon with controls
	 	 else{
	 	
	 		
	 	 // Assumming you have a Leaflet map accessible
	 	 map.on('draw:created', function (e) {
	 	     var type = e.layerType,
	 	         layer = e.layer;
	 	     layers.push(layer);
	 	 console.log('done 1st step')
	 	     // Do whatever you want with the layer.
	 	     // e.type will be the type of layer that has been draw (polyline, marker, polygon, rectangle, circle)
	 	     // E.g. add it to the map
	 	   //  layer.addTo(map);
	 	 //layer
	 	 	var geojson=layer.toGeoJSON();
	 	 	console.log(geojson);
	 	 	//map.fitBounds(layer);
	 	 	
	 	 	$scope.property.layerPolygon = new L.Polygon(layer._latlngs,{
                color: '#ff0',
                weight: 2,
                opacity:1,
				fill:false
            });

	 	 	$scope.property.layerPolygon.editing.enable();
	 	 	
	 	 	
	 	 	geocontrol.options.geocoder.reverse(layer._latlngs[0][0],map.options.crs.scale(map.getZoom()),function(response){
	  			
	  			 if(response){
	  				getAddress(response);
	  			 }
	  		});
	 	   
	 	 

 	 		map.addLayer($scope.property.layerPolygon);
 	 		map.fitBounds($scope.property.layerPolygon._latlngs);
 	 		
 	 		$scope.disableButton=false;
 	 		console.log($scope.disableButton);
 	 		$scope.click();
 	 		drawControl.remove();
 	 		searchControl.remove();
	 		
 	 		//$scope.property.polygonlatLan=JSON.stringify($scope.property.layerPolygon._latlngs);
 	 		
	 	 /*	map.removeLayer(layer);
	 	 	
	 	 	
	 	 	var polyPush=JSON.parse(sessionStorage.latLan) ;
	 	 	
	 	 	var polygon = new L.Polygon(polyPush);

	 	 		polygon.editing.enable();

	 	 		map.addLayer(polygon);
	 	 		polygon.on('edit', function() {
	 	 		console.log(polygon);
	 	 			console.log('Polygon was edited!');
	 	 		});*/

	 	 });
	 	 }
	 	 
	 	 //getting address with geolocater response
	 	 function getAddress(response){
	 		 if(response){
	 		   		$scope.property.region=response["0"].properties.address.state;
	 		   		if(!$scope.property.region)
	 		   			{
	 		   		$scope.property.region=response["0"].properties.address.county;
	 		   			}
	 		 	 	$scope.property.subRegion=response["0"].properties.address.city;
	 		 	 	if(!$scope.property.subRegion){
	 		 	 		$scope.property.subRegion=response["0"].properties.address.town;
	 		 	 	}
	 		 	 	if(!$scope.property.subRegion){
	 		 	 		$scope.property.subRegion=response["0"].properties.address.county;
	 		 	 	}
	 		 	 	$scope.property.country=response["0"].properties.address.country;
	 		 	 	$scope.property.address=response["0"].properties.display_name;
	 		   		  }
	 	 }
	 	
	  
  });