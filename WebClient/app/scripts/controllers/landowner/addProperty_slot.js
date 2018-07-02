'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:LandownerAddPropertyCtrl_Slot
 * @description
 * # LandownerAddPropertyCtrl_Slot
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
  .controller('LandownerAddPropertyCtrl_Slot', function ($timeout,$state, $scope,serviceCalls) {
	  //which copy slot the slot should be just behind it which some space for that we have a formula
    function copyLatLan(latlng,circleRadius){
      var dx=circleRadius*Math.sin(90);
      var dy=circleRadius*Math.cos(90);
      var delta_longitude = dx/(111320*Math.sin(latlng.lat));
      var delta_latitude = dy/110540;
      latlng.lat=latlng.lat+delta_latitude;
      latlng.lng=latlng.lng+delta_longitude;
    }
	  if(!sessionStorage.Authorization){
		  $state.go("login");
		 throw new Error("Something went badly wrong!");
	  }
	  var layerPolygon;
	  var accessToken = 'pk.eyJ1IjoibWFkaHVtb3Rpdml0eSIsImEiOiJjajdyYmo4cGwwcnIxMzducXNtbmxyb2dpIn0.20dxjsGIN8G6gChj5MFDtg';
	   // Replace 'mapbox.streets' with your map id.
	   var mapboxTiles = L.tileLayer('https://api.mapbox.com/styles/v1/mapbox/satellite-streets-v10/tiles/256/{z}/{x}/{y}?access_token=' + accessToken, {
	       attribution: ''
	   });
	   var map = L.map('outlinemap')
	       .addLayer(mapboxTiles)
	       .setView([33.123927, -96.821845], 15);
		$scope.disableButton=true;

	 	var layers=[];
	 	 $scope.click=function(){
	 		$timeout(function(){

	 			console.log($scope.disableButton);
	 		},1000);

	 	 }

	    if($scope.property&&$scope.property.outLine){

	 	layerPolygon = new L.Polygon(JSON.parse($scope.property.outLine),{
                color: '#ff0',
                weight: 2,
                opacity:1,
				fill:false
            });
	 		map.addLayer(layerPolygon);

            $timeout(function(){

            	map.fitBounds(layerPolygon._latlngs);
	 		},0);

	 	 }
	    else{
	    	$state.go("landownerAddProperty.outline");
	    }
	    if(!sessionStorage.slotLength)
	    	{
	    	$state.go("landownerAddProperty.area.allAreas");
	    	}

	    else if($scope.property&&$scope.property.areas){

	    		$scope.slot=angular.copy($scope.property.areas[Number(sessionStorage.areaOfSlotLength)].slots[Number(sessionStorage.slotLength)]);
	    		//draw existing slots to map except new slot
	    		angular.forEach($scope.property.areas, function(area) {
	    		    	angular.forEach(area.slots, function(slot) {
	    		    		if(slot.outLine&&slot.slotName!=$scope.slot.slotName)
	    		    			{
	    		    			var layerPolygon = new L.Polygon(JSON.parse(slot.outLine),{
	    		    	 			color: '#0758FA',
	    		    		        weight: 2,
	    		    		        opacity:1,
	    		    		 		fill:true,
	    		    		 		fillOpacity:0.2
	    		                }).bindPopup('<div class="pop-font">Area: &nbsp;</div><div class="pop-orange-hed">'+area.propertyAreaName+'</div><br/><div class="pop-font">Slot: &nbsp;</div> <div class="pop-orange-hed">'+slot.slotName+'</div>');
	    		    	 		map.addLayer(layerPolygon);
	    		    			}

	    		    	});

	    		    });
	    		
	    		//When slot get resized on map

	    		 $scope.resizeTheSlot=function(sizeofSlot){
	    			var size = Number(sizeofSlot);
	    			if(isNaN(size)){
	    				//do validation
	    			}
	    			else{
	    				if(size>5){
	    					//getting the coordinate using circle on map with a center

	    					var circleCenter=$scope.layerPolygon.getBounds().getCenter();
	    					$scope.layerPolygon.transform.enable({rotation: false, scaling: false});


	    					map.removeLayer($scope.layerPolygon);
		    		 		var circleRadius=convertToYardRadius(size);
		    		 		var circleLayer = new L.Circle(circleCenter, circleRadius, {
		    		 	 		color: '#FF0000',
		    			        weight: 2,
		    			        opacity:1,
		    			 		fill:true,
		    			 		fillOpacity:0.2
		    	            });
		    		 		map.addLayer(circleLayer);
		    		 		var rectangle=new L.Rectangle(circleLayer.getBounds(), {
		    		 	 		color: '#FF0000',
		    			        weight: 2,
		    			        opacity:1,
		    			 		fill:true,
		    			 		fillOpacity:0.2,

		    	            }).addTo(map);
		    		 		$scope.layerPolygon =new L.Polygon(rectangle._latlngs, {
		    		 	 		color: '#FF0000',
		    			        weight: 2,
		    			        opacity:1,
		    			 		fill:true,
		    			 		fillOpacity:0.2,
		    			 		transform: true,draggable: true
		    	            }).addTo(map);
		    		 		map.removeLayer(circleLayer);
		    		 		map.removeLayer(rectangle);

		    		 		$scope.layerPolygon.transform.enable({rotation: true, scaling: true});
		    				//var timer=setInterval(function(){ map.dragging.disable();}, 100);

		    		 		$scope.layerPolygon.on('scaleend', function (e) {
		    		 			updateSlotSize();

		    		 		});

	    				}
	    				else{
	    					//do validation
	    				}
	    			}
	    		 };

	    		 if($scope.slot&&$scope.slot.outLine){


	    	 	 		//////

	    	 	 		$scope.layerPolygon =new L.Polygon(JSON.parse($scope.slot.outLine), {
	    		 	 		color: '#FF0000',
	    			        weight: 2,
	    			        opacity:1,
	    			 		fill:true,
	    			 		fillOpacity:0.2,
	    			 		transform: true,draggable: true
	    	            });
	    	 	 		//check if it is new or copy slot

	    	 	 		if($scope.slot.copy=="true")
	    	 	 			{
	    	 	 			var circleRadius=convertToYardRadius($scope.slot.slotSize);
	    	 	 			angular.forEach($scope.layerPolygon._latlngs[0],function(latlng){
	    	 	 				copyLatLan(latlng,circleRadius);

	    	 	 			});
	    	 	 			$scope.layerPolygon =new L.Polygon($scope.layerPolygon._latlngs, {
		    		 	 		color: '#FF0000',
		    			        weight: 2,
		    			        opacity:1,
		    			 		fill:true,
		    			 		fillOpacity:0.2,
		    			 		transform: true,draggable: true
		    	            });
	    	 	 			}

	    	 	 		$scope.layerPolygon.addTo(map);
	    		 		$scope.layerPolygon.transform.enable({rotation: true, scaling: true});
	    				//var timer=setInterval(function(){ map.dragging.disable();}, 100);
	    		 		//update the input field after it is completed scaling on map

	    		 		$scope.layerPolygon.on('scaleend', function (e) {
	    		 			updateSlotSize();

	    		 		});


	    		 	 }
	    		 //for newly added slot

	    		 	 else{
	    		 		var circleCenter=layerPolygon.getBounds().getCenter();
	    		 		var circleRadius=convertToYardRadius($scope.slot.slotSize);

	    		 		var circleLayer = new L.Circle(circleCenter, circleRadius, {
	    		 	 		color: '#FF0000',
	    			        weight: 2,
	    			        opacity:1,
	    			 		fill:true,
	    			 		fillOpacity:0.2
	    	            });
	    		 		map.addLayer(circleLayer);
	    		 		var rectangle=new L.Rectangle(circleLayer.getBounds(), {
	    		 	 		color: '#FF0000',
	    			        weight: 2,
	    			        opacity:1,
	    			 		fill:true,
	    			 		fillOpacity:0.2,

	    	            }).addTo(map);
	    		 		$scope.layerPolygon =new L.Polygon(rectangle._latlngs, {
	    		 	 		color: '#FF0000',
	    			        weight: 2,
	    			        opacity:1,
	    			 		fill:true,
	    			 		fillOpacity:0.2,
	    			 		transform: true,draggable: true
	    	            }).addTo(map);
	    		 		map.removeLayer(circleLayer);
	    		 		map.removeLayer(rectangle);

	    		 		$scope.layerPolygon.transform.enable({rotation: true, scaling: true});
	    				//var timer=setInterval(function(){ map.dragging.disable();}, 100);

	    		 		$scope.layerPolygon.on('scaleend', function (e) {
	    		 			updateSlotSize();

	    		 		});
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

	    		 	 	$scope.layerPolygon = new L.Polygon(layer._latlngs,{
	    		 	 		color: '#FF0000',
	    			        weight: 2,
	    			        opacity:1,
	    			 		fill:true,
	    			 		fillOpacity:0.2
	    	            });

	    		 	 	$scope.layerPolygon.editing.enable();

	    	 	 		map.addLayer($scope.layerPolygon);
	    	 	 	  $timeout(function(){

	    	 	 		 var sqmts=L.GeometryUtil.geodesicArea($scope.layerPolygon.getLatLngs());
	    	 	 		$scope.slot.slotSize=sqmtsToSqyartd(sqmts);
	    	 	 		},100);
	    	 	 	     $scope.layerPolygon.on('edit', function() {

	    			       $timeout(function(){

	    			    	   var sqmts=L.GeometryUtil.geodesicArea($scope.layerPolygon.getLatLngs());
	    				 		$scope.slot.slotSize=sqmtsToSqyartd(sqmts);
	    				 		},100);


	    			 	 		});
	    	 	 		$scope.disableButton=false;
	    	 	 		console.log($scope.disableButton);
	    	 	 		$scope.click();
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





	    		   if($scope.layerPolygon) {
	    			   $scope.layerPolygon.on('edit', function() {

	    			       $timeout(function(){

	    			    	   var sqmts=L.GeometryUtil.geodesicArea($scope.layerPolygon.getLatLngs());
	    				 		$scope.slot.slotSize=sqmtsToSqyartd(sqmts);


	    				 		},100);




	    			 	 		});
	    		   }



	    }
	    else{


	    }






  $scope.slot_submit=false;
  //save the slot to server

         $scope.slotAdded=function(prop) {
           $scope.slot_submit = true;
           if(prop.$valid&&!$scope.checkValidNumber(prop.length.$viewValue)){
           $scope.slot.outLine = JSON.stringify($scope.layerPolygon._latlngs);

           $scope.layerPolygon = "";


           sessionStorage.property = JSON.stringify($scope.property);
           var propertyAreaUuid = $scope.property.areas[Number(sessionStorage.areaOfSlotLength)].propertyAreaUuid;

           serviceCalls.addSlot($scope.slot, propertyAreaUuid)
             .then(function (response) {
               $scope.property.areas[Number(sessionStorage.areaOfSlotLength)].slots[Number(sessionStorage.slotLength)] = response.data.slot;

               $scope.getTheSlotsList();
               sessionStorage.property = JSON.stringify($scope.property);
               $scope.changeParentScope();
               sessionStorage.toRemoveSlot = "false";
               sessionStorage.removeItem('slotLength');
               sessionStorage.removeItem('areaOfSlotLength');
               serviceCalls.updatePropertyBoundaries($scope.property).then(function (response) {
            	  
                 $state.go("landownerAddProperty.area.allAreas");
               //set focus to this area id of page
         	  	  $timeout(function(){
         				 document.getElementById(propertyAreaUuid).focus();
         				 },200);

               });


             })
             .catch(function (err) {
               console.log(err);
             });
         }

	    };

	    function convertToYardRadius(slotSize){

	    	var InYards=Number(slotSize);
	    	if(isNaN(InYards))
    		{
	    		InYards=10;
    		}

	    	var areaLength=InYards;
	    	var circleRadius=areaLength/2;
	    	return (circleRadius)*0.9144;

	    };

	    function updateSlotSize()
	    {


	    	 $timeout(function(){

		    	   var sqmts=L.GeometryUtil.geodesicArea($scope.layerPolygon._latlngs[0]);
			 		$scope.slot.slotSize=sqmtsToSqyartd(sqmts);


			 		},100);


	    }
	    //to convert sqmts to length in yards
	    function sqmtsToSqyartd(sqmts){
	    	var mts=Math.sqrt(sqmts);
	    	var sqyards=mts*1.09361;
	    	return sqyards.toFixed(0);
	    };






  });
