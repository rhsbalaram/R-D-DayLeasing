'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:LandownerDashboardCtrl
 * @description
 * # LandownerDashboardCtrl
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
  .controller('LandownerDashboardCtrl', function ($state, $scope,serviceCalls) {
	  $scope.property="";
	  sessionStorage.removeItem('property');
	  //if not authorized logout
	  if(!sessionStorage.Authorization){
		  $state.go("login");
		  throw new Error("Something went badly wrong!");
	  }
	  //to add new property
    $scope.addProperty=function(){
    	$state.go("landownerAddProperty.outline");
    };
   
    
    
    
    //edit property
    $scope.editProperty=function(propertyId){
    	
    	 serviceCalls.getSingleProperty(propertyId).then(function(response){
			 $scope.property=response.data;
			 sessionStorage.property=JSON.stringify($scope.property);
			 
		    	
		    	
		    	$state.go("landownerAddProperty.outline");
			 
		 }).catch(function(err){
			 console.log(err);
		 });
    	
    };
    //delete property
    $scope.deleteProperty=function(propertyId){
    	//
    	
    	serviceCalls.getReservationStatusOfProperty(propertyId).then(function(response){
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
		    			
		    			serviceCalls.deleteProperty(propertyId).then(function(response){
		    				
			    			 
			    			
			    			  swal(
			    		    		    'Deleted!',
			    		    		    'Your Property has been deleted.',
			    		    		    'success'
			    		    		  );
			    			  $state.reload();
			    		 }).catch(function(err){
			    			 console.log(err);
			    			 swal(
			    		    		    'Error!',
			    		    		    'Unable to Delete Property.',
			    		    		    'error'
			    		    		  );
			    		 });

		    		})
			}
			
			 
		 }).catch(function(err){
			 console.log(err);
			 swal(
		    		    'Error!',
		    		    'Unable to Delete Property.',
		    		    'error'
		    		  );
		 });
    	//
    	;
    }
    //get all properties to dashboard
    serviceCalls.getProperty()
	 .then(function(response){
		
		 $scope.properties=response.data;
		 
		
	})
	.catch(function(err){
		
		});
 
 	
  });
