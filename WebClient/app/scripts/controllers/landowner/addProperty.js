'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:LandownerAddPropertyCtrl
 * @description
 * # LandownerAddPropertyCtrl
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
  .controller('LandownerAddPropertyCtrl', function ($state, $scope,serviceCalls) {
	  if(!sessionStorage.Authorization){
		  $state.go("login");
		  throw new Error("Something went badly wrong!");
	  }
//create an empty property obj
	  $scope.property={};
	  //add one empty feature obj
	  $scope.property.propertyFeatures=[{"data":""}];
	  //to add ffeatures
	  $scope.addFeaturetype=function(){
		  $scope.property.propertyFeatures.push({"data":""});
	    };
	    //if session is already having property, assign it
	    if(sessionStorage.property&&sessionStorage.property!="")
	    	{
	    	$scope.property=JSON.parse(sessionStorage.property);

	    	}
	    //to remove feature
	    $scope.removeFeaturetype=function(index){
	    	if(index!=0)
	    	$scope.property.propertyFeatures.splice(Number(index), 1);
	    };
      //to add description after outline
	  $scope.addOutline=function(){


		  $scope.property.outLine=JSON.stringify($scope.property.layerPolygon._latlngs);
	    	$scope.property.layerPolygon="";
	    	sessionStorage.property=JSON.stringify($scope.property);
	    	$state.go("landownerAddProperty.description");


	    };
          $scope.submitted=false;
    // $scope.propertyname=false;
    // $scope.propertydescription=false;
    // $scope.propertytype=false;
          //to save description to server
	    $scope.adddescription=function(prop){
			      // console.log(isvalid);
        $scope.submitted=true;
        // $scope.prop=$scope.form.prop;
        // console.log($scope.prop);
        // if(prop.propertyName.$error.required &&  prop.propertyName.$invalid){
        //   $scope.propertyname=true;
        // }
        // else{
        //   $scope.propertyname=false;
        // }
        // if(prop.propertyDescription.$error.required && prop.propertyDescription.$invalid){
        //   $scope.propertydescription=true;
        // }
        // else{
        //   $scope.propertydescription=false;
        // }
        // if(prop.propertyType.$error.required){
        //   $scope.propertytype=true;
        // }
        // else{
        //   $scope.propertytype=false;
        // }//validations
        if(prop.$valid) {
          serviceCalls.addProperty($scope.property)
            .then(function (response) {

              $scope.property.propertyUuid = response.data.property.propertyUuid;
              sessionStorage.property = JSON.stringify($scope.property);
              $state.go("landownerAddProperty.area.allAreas");


            })
            .catch(function (err) {
              alert('Property Name should be unique');
            });

        }



		    };


	    $scope.gotoDashboard=function(){
	    	$state.go("landownerDashboard");
	    };
	    $scope.gotoDescription=function(){
	    	$state.go("landownerAddProperty.description");
	    };
	    $scope.gotooutline=function(){
	    	$state.go("landownerAddProperty.outline");
	    };
	    $scope.gotoarea=function(){
	    	$state.go("landownerAddProperty.area.allAreas");
	    };

	   /* $scope.addArea=function(){
	    	$scope.property.outLine=JSON.stringify($scope.property.layerPolygon._latlngs);
	    	$scope.property.layerPolygon="";
	    	sessionStorage.property=JSON.stringify($scope.property);
	    	$state.go("landownerAddProperty.area");
	    };*/
	    
	    //add slot remember index of that slot

	    $scope.addSlot=function(area,index){
	    	var slot={};
	    	slot.slotName=generateSlotName(area,index);
	    	slot.slotSize='10';
	    	area.slots.push(slot);

	    	sessionStorage.property=JSON.stringify($scope.property);
	    	sessionStorage.toRemoveSlot="true";
	    	sessionStorage.slotLength=(area.slots.length-1);
	    	sessionStorage.areaOfSlotLength=index;
	    	sessionStorage.slotToEdit=slot.slotName;
	    	$state.go("landownerAddProperty.slot");
	    };

	    $scope.editSlot=function(parentIndex,index){
	    	///
	    	var slotUUID=$scope.property.areas[parentIndex].slots[index].propertySlotsUuid;
	    	serviceCalls.getReservationStatusOfSlot(slotUUID).then(function(response){
				/*if(response.data.status==true){
					 swal(
	    		    		    'Error!',
	    		    		    'Unable to edit.It has bookings.',
	    		    		    'error'
	    		    		  );	
				}else{*/
					sessionStorage.property=JSON.stringify($scope.property);
			    	sessionStorage.toRemoveSlot="false";
			    	sessionStorage.slotLength=index;
			    	sessionStorage.areaOfSlotLength=parentIndex;

			    	$state.go("landownerAddProperty.slot");
				/*}*/
    			
    			 
    		 }).catch(function(err){
    			 console.log(err);
    			 swal(
    		    		    'Error!',
    		    		    'Unable to edit Slot.',
    		    		    'error'
    		    		  );
    		 });
	    	///

	    	
	    };

	    $scope.copySlot=function(area,parentIndex,index){
	    	var slot={};
	    	angular.copy(area.slots[index], slot);
	    	slot.slotName=generateSlotName(area,parentIndex);
	    	slot.copy="true";
	    	delete slot.propertySlotsUuid;
	    	area.slots.push(slot);

	    	sessionStorage.property=JSON.stringify($scope.property);
	    	sessionStorage.toRemoveSlot="true";
	    	sessionStorage.slotLength=(area.slots.length-1);
	    	sessionStorage.areaOfSlotLength=parentIndex;
	    	sessionStorage.slotToEdit=slot.slotName;
	    	$state.go("landownerAddProperty.slot");

	    };
	    ///delete the slot using api call and also in the session and property object
	    $scope.deleteSlot=function(areaIndex,slotIndex){
	    	var slotUUID=$scope.property.areas[areaIndex].slots[slotIndex].propertySlotsUuid;
	    	serviceCalls.getReservationStatusOfSlot(slotUUID).then(function(response){
				if(response.data.status==true){
					 swal(
	    		    		    'Error!',
	    		    		    'Unable to delete.It has bookings.',
	    		    		    'error'
	    		    		  );	
				}else{
					swal({
			    		  title: 'Are you sure?',
			    		  text: "You won't be able to revert this!",
			    		  type: 'warning',
			    		  showCancelButton: true,
			    		  confirmButtonColor: '#3085d6',
			    		  cancelButtonColor: '#d33',
			    		  confirmButtonText: 'Yes, delete it!'
			    		}).then(function () {
			    			var areaUUID=$scope.property.areas[areaIndex].propertyAreaUuid;
			    			var slotUUID=$scope.property.areas[areaIndex].slots[slotIndex].propertySlotsUuid;
			    			serviceCalls.deleteSlot(areaUUID,slotUUID).then(function(response){
			    				$scope.property.areas[Number(areaIndex)].slots.splice(Number(slotIndex), 1);
				    			 sessionStorage.property=JSON.stringify($scope.property);
				    			 $scope.getTheSlotsList();
				    			 serviceCalls.updatePropertyBoundaries($scope.property).then(function(response){


				    			 });
				    			 swal(
				    		    		    'Deleted!',
				    		    		    'Your Slot has been deleted.',
				    		    		    'success'
				    		    		  );
				    			 $state.reload();
				    		 }).catch(function(err){
				    			 console.log(err);
				    			 swal(
				    		    		    'Error!',
				    		    		    'Unable to Delete Slot.',
				    		    		    'error'
				    		    		  );
				    		 });

			    		});
				}
    			
    			 
    		 }).catch(function(err){
    			 console.log(err);
    			 swal(
    		    		    'Error!',
    		    		    'Unable to Delete Slot.',
    		    		    'error'
    		    		  );
    		 });
	    	
	    }

	    //////delete area 
	    $scope.deleteArea=function(areaIndex){
	    	//
	    	var areaUUID=$scope.property.areas[areaIndex].propertyAreaUuid;
	    	serviceCalls.getReservationStatusOfArea(areaUUID).then(function(response){
				if(response.data.status==true){
					 swal(
	    		    		    'Error!',
	    		    		    'Unable to delete. It has bookings.',
	    		    		    'error'
	    		    		  );	
				}else{
					swal({
			    		  title: 'Are you sure?',
			    		  text: "You won't be able to revert this!",
			    		  type: 'warning',
			    		  showCancelButton: true,
			    		  confirmButtonColor: '#3085d6',
			    		  cancelButtonColor: '#d33',
			    		  confirmButtonText: 'Yes, delete it!'
			    		}).then(function () {
			    			var areaUUID=$scope.property.areas[areaIndex].propertyAreaUuid;
			    			var propertyUuid=$scope.property.propertyUuid;
			    			serviceCalls.deleteArea(propertyUuid,areaUUID).then(function(response){
			    				$scope.property.areas.splice(Number(areaIndex), 1);
				    			 sessionStorage.property=JSON.stringify($scope.property);
				    			 $scope.getTheSlotsList();
				    			 serviceCalls.updatePropertyBoundaries($scope.property).then(function(response){


				    			 });
				    			  swal(
				    		    		    'Deleted!',
				    		    		    'Your Area has been deleted.',
				    		    		    'success'
				    		    		  );
				    			  $state.reload();
				    		 }).catch(function(err){
				    			 console.log(err);
				    			 swal(
				    		    		    'Error!',
				    		    		    'Unable to Delete Area.',
				    		    		    'error'
				    		    		  );
				    		 });

			    		});
				}
    			
    			 
    		 }).catch(function(err){
    			 console.log(err);
    			 swal(
    		    		    'Error!',
    		    		    'Unable to Delete Area.',
    		    		    'error'
    		    		  );
    		 });
	    	//
	    	
	    }
        
	    //generate auto slot name and with eg A1-1,A2-1...
      function generateSlotName(area,index){
    	  var splitHiddenAreaName=area.propertyHiddenAreaName.split(" ");
  		

    	  if(area.slots.length>0){
    		  var slotName=area.slots[area.slots.length-1].slotName;
    		  var splitedName=slotName.split("-");
    		
    		 return 'A'+(Number(splitHiddenAreaName[1]))+'-'+(Number(splitedName[1].trim())+1);
    	  }

	    	
	    	return  'A'+(Number(splitHiddenAreaName[1]))+'-'+(area.slots.length+1);
	    };
	    
	    //using this when child scope got changed and need parent scope object also to be same

	    $scope.changeParentScope=function(){
	    	$scope.property=$scope.property=JSON.parse(sessionStorage.property);
	    };
	    
	    //getting all slot and generating a html string to save it to database

	    $scope.getTheSlotsList=function(){
	    	 var slotboundaries="";
		      var propertySlots=0;
		    	angular.forEach($scope.property.areas, function(area) {
		    		if(area.slots.length>0){
		    			propertySlots=propertySlots+area.slots.length;
		    		var startslot=area.slots[0].slotName;
		    		var endslot=area.slots[area.slots.length-1].slotName;
		    		slotboundaries=slotboundaries+startslot+" to "+endslot+"<br>";
		    		}


		    	});
		    	$scope.property.propertyBorder=slotboundaries;
		    	$scope.property.propertySlots=propertySlots;
	    };
	    
	    //to check valid number
	    $scope.checkValidNumber=function(value){
			  
			  if(parseFloat(value)<=0||parseFloat(value)% 1 != 0||parseFloat(value)>=1000){
				 // $scope.isValidNumber=false;
	    		  return true;
	    	  }
			  else 
	    		  {
				 
	    		  return false;
	    		  }
		  }







  });
