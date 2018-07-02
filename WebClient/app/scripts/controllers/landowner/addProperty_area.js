'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:LandownerAddPropertyCtrl_Area
 * @description
 * # LandownerAddPropertyCtrl_Area
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
  .controller('LandownerAddPropertyCtrl_Area', function ($timeout,$state, $scope,serviceCalls) {
	  var accessToken = 'pk.eyJ1IjoibWFkaHVtb3Rpdml0eSIsImEiOiJjajdyYmo4cGwwcnIxMzducXNtbmxyb2dpIn0.20dxjsGIN8G6gChj5MFDtg';
	   // Replace 'mapbox.streets' with your map id.
	   var mapboxTiles = L.tileLayer('https://api.mapbox.com/styles/v1/mapbox/satellite-streets-v10/tiles/256/{z}/{x}/{y}?access_token=' + accessToken, {
	       attribution: ''
	   });
	    $scope.map = L.map('outlinemap')
	       .addLayer(mapboxTiles)
	       .setView([33.123927, -96.821845], 15);
	   
	   	
	    if($scope.property&&$scope.property.outLine){
	 		 
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
	    //create an empty area for newly added
	    $scope.addAreaToProperty=function(){
	    	 var area={};
	    	 area.slots=[];
	    	 area.dates=[];
	    	 
	    	
		  	    area.propertyHiddenAreaName=generateAreaName();
		  	  $scope.property.areas.push(area);
		  	  
		  	sessionStorage.toRemoveArea='true';
		  	sessionStorage.areaToRemove=$scope.property.areas.length-1;
		  	sessionStorage.property=JSON.stringify($scope.property);
		  	 $scope.changeParentScope();
		  	$state.go("landownerAddProperty.area.singleArea");
	    };
	    $scope.editAreaofProperty=function(index){
	    	 //
	    	var areaUUID=$scope.property.areas[index].propertyAreaUuid;
	    	/*serviceCalls.getReservationStatusOfArea(areaUUID).then(function(response){
				if(response.data.status==true){
					 swal(
	    		    		    'Error!',
	    		    		    'Unable to edit. It has bookings.',
	    		    		    'error'
	    		    		  );	
				}else{*/
					sessionStorage.toRemoveArea='false';
				  	sessionStorage.areaToRemove=index;
				  	
				  	$state.go("landownerAddProperty.area.singleArea");
				/*}
    			
    			 
    		 }).catch(function(err){
    			 console.log(err);
    			 swal(
    		    		    'Error!',
    		    		    'Unable to edit Area. It has bookings',
    		    		    'error'
    		    		  );
    		 });*/
	    	//
		  	  
		  
	    };
	    
	    
	    //if data is not added and came to back page it should be remove from property object
	    if($scope.property&&$scope.property.areas){
	    	if(sessionStorage.toRemoveSlot=="true")
	    		{
	    		$scope.property.areas[Number(sessionStorage.areaOfSlotLength)].slots.splice(Number(sessionStorage.slotLength), 1);
	    		 sessionStorage.toRemoveSlot="false";
 		    	sessionStorage.removeItem('slotLength');
 		    	sessionStorage.property=JSON.stringify($scope.property);
	    		}
	    	if(sessionStorage.toRemoveArea=='true'){
	    		$scope.property.areas.splice(Number(sessionStorage.areaToRemove),1);
	    		sessionStorage.toRemoveArea="false";
		    	sessionStorage.removeItem('areaToRemove');
		    	sessionStorage.property=JSON.stringify($scope.property);
			}
			if(sessionStorage.toRemoveMultipleSlots == "true"){
				sessionStorage.toRemoveMultipleSlots = "false";
				var length = Number(sessionStorage.totalSlotLength);
				var area = $scope.property.areas[Number(sessionStorage.areaOfSlotLength)];
				var currLength = area.slots.length;
				$scope.property.areas[Number(sessionStorage.areaOfSlotLength)].slots.splice(length, (currLength-length));
				sessionStorage.removeItem('multipleSlotLength');
				sessionStorage.property=JSON.stringify($scope.property);
			}
	    	 angular.forEach($scope.property.areas, function(area) {
	 	    	angular.forEach(area.slots, function(slot) {
	 	    		if(slot.outLine)
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
						 if(slot.radioValue){
							slot.radioValue = false;
						 }
	 	    		
	 	    	});
	 	    	
	 	    });
	    	
	    }
	    else{
	    	  $scope.property.areas=[];
	    	  
	    	  
	  	   
	    }
	    
	   
	    
	  
	    //generate hiden area name which is used to generate area
	    function generateAreaName(){
	    	if($scope.property.areas.length>0){
	    		var propertyHiddenAreaName = $scope.property.areas[$scope.property.areas.length-1].propertyHiddenAreaName;
	    		var splitName=propertyHiddenAreaName.split(" ");
	    		return 'AREA 0'+(Number(splitName[1]) +1);
	    		
	    		
	    	}
	    	
	    	
	    	return  'AREA 0'+($scope.property.areas.length +1);
	    };
	    
	    
	  
	  
  });
	