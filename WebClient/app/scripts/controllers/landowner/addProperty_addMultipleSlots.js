'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:LandownerAddPropertyCtrl_Slot
 * @description
 * # LandownerAddPropertyCtrl_Slot
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
  .controller('addProperty_addMultipleSlots', function ($timeout,$state, $scope,serviceCalls) {
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
	    if(!sessionStorage.multipleSlotLength)
	    	{
	    	$state.go("landownerAddProperty.area.allAreas");
	    	}

	    else if($scope.property&&$scope.property.areas){

            var length = Number(sessionStorage.totalSlotLength);
            var area = $scope.property.areas[Number(sessionStorage.areaOfSlotLength)];
            var latlngs = [];
            angular.forEach(area.slots, function(slot, index){
                if(index < length){
                var layerPolygon = new L.Polygon(JSON.parse(slot.outLine),{
                    color: '#0758FA',
                   weight: 2,
                   opacity:1,
                    fill:true,
                    fillOpacity:0.2
               }).bindPopup('<div class="pop-font">Area: &nbsp;</div><div class="pop-orange-hed">'+area.propertyAreaName+'</div><br/><div class="pop-font">Slot: &nbsp;</div> <div class="pop-orange-hed">'+slot.slotName+'</div>');
                map.addLayer(layerPolygon);
                }else{
                    latlngs.push(JSON.parse(slot.outLine)[0]); 
                }
            });
            angular.forEach(latlngs, function(latLan){
                angular.forEach(latLan,function(ltlg){
                    copyLatLan(ltlg,10);

                });
            });
            $scope.layerPolygon = new L.Polygon((latlngs),{
                color: '#FF0000',
               weight: 2,
               opacity:1,
                fill:true,
                fillOpacity:0.2,
                transform: true,draggable: true
		   }).addTo(map);
		   $scope.layerPolygon.transform.options.boundsOptions.opacity = 1;
		   $scope.layerPolygon.transform.options.boundsOptions.weight = 1;
           $scope.layerPolygon.transform.enable({rotation: true, scaling: false});	

	    }
	    else{


	    }






  $scope.disableButton=false;
  //save the slot to server

  $scope.addMultipleSlots = function(){
	var length = Number(sessionStorage.totalSlotLength);
	var area = $scope.property.areas[Number(sessionStorage.areaOfSlotLength)];
	var propertyAreaUuid = area.propertyAreaUuid;
	var currLength = area.slots.length;
	var newslots = [];
	var a = 0;
	for(var i = length; i < currLength; i++){
		var tempslot = area.slots[i];
		var latlng = [$scope.layerPolygon._latlngs[a]]
		tempslot.outLine = JSON.stringify(latlng);
	   newslots.push(tempslot);
	   a++;
	}

	serviceCalls.addMultipleSlots(newslots, propertyAreaUuid).
	then(function (response) {

		$scope.property.areas[Number(sessionStorage.areaOfSlotLength)].slots[Number(sessionStorage.slotLength)] = response.data;
		
					   $scope.getTheSlotsList();
					   sessionStorage.property = JSON.stringify($scope.property);
					   $scope.changeParentScope();
					   sessionStorage.toRemoveMultipleSlots = "false";
					   sessionStorage.removeItem('multipleSlotLength');
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
  };


  });
