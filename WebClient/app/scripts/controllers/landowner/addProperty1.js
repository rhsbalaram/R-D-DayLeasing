'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:LandownerAddPropertyCtrl
 * @description
 * # LandownerAddPropertyCtrl
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
  .controller('LandownerAddPropertyCtrl', function ($scope,serviceCalls,$state) {
    
	  if(!sessionStorage.Authorization){
		  $state.go("login");
	  }
    
   var accessToken = 'pk.eyJ1IjoiYmFsYXJhbWEiLCJhIjoiY2owZHB5YmE4MDBmaDMybnFwbndlOXMyNyJ9.h1rT5NxNRmge0EvD4MiwhA';
 // Replace 'mapbox.streets' with your map id.
 var mapboxTiles = L.tileLayer('https://api.mapbox.com/styles/v1/mapbox/satellite-streets-v10/tiles/256/{z}/{x}/{y}?access_token=' + accessToken, {
     attribution: ''
 });
 var map = L.map('addProperty')
     .addLayer(mapboxTiles)
     .setView([42.3610, -71.0587], 15);
 	map.addControl( new L.Control.Search({
 		url: 'http://nominatim.openstreetmap.org/search?format=json&q={s}',
 		jsonpParam: 'json_callback',
 		propertyName: 'display_name',
 		propertyLoc: ['lat','lon'],
 		marker: false,
 		autoCollapse: true,
 		autoType: false,
 		minLength: 2
 	}) );
 	
 	var layerPolygon={};
 	var layers=[];
 	
 	
 	var polygonDrawer = new L.Draw.Polygon(map,{shapeOptions: {
 		color: '#ff0',
        weight: 2,
        opacity:1,
 		fill:false
                 }});
 	

 // Assumming you have a Leaflet map accessible
 map.on('draw:created', function (e) {
     var type = e.layerType,
         layer = e.layer;
     layers.push(layer);
 console.log('done 1st step')
     // Do whatever you want with the layer.
     // e.type will be the type of layer that has been draw (polyline, marker, polygon, rectangle, circle)
     // E.g. add it to the map
     layer.addTo(map);
 //layer
 	var geojson=layer.toGeoJSON();
 	console.log(geojson);
 	map.fitBounds(layer);
 	layerPolygon.latLan=JSON.stringify(layer._latlngs);
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
 
 $scope.editProperty=function(){
	 layers[0].editing.enable();
	 layers[0].on('edit', function() {
 		console.log(layers[0]);
 			console.log('Polygon was edited!');
 		});
 };
 $scope.saveProperty=function(){
	var property={};
	 property.outLine=JSON.stringify(layers[0]._latlngs);
	 serviceCalls.addProperty(property)
	 .then(function(response){
		 $state.go("landownerDashboard");
	})
	.catch(function(err){
		console.log(err);
		});
	
 };


 // Click handler for you button to start drawing polygons
 $('#draw_poly').click(function() {
     polygonDrawer.enable();
    
 	
 	
 });
    
 
 	
  });
