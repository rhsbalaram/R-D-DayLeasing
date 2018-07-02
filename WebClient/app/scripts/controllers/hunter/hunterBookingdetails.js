'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:hunterBookingDetails
 * @description
 * # hunterBookingDetails
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
  .controller('hunterBookingDetails', function ($timeout,$state, $scope,HunterServiceCalls,$stateParams,$rootScope,$filter,$window,$q,$location) {
	  if(!sessionStorage.Authorization){
		  $state.go("login");
		  throw new Error("Something went badly wrong!");
	  }
	  //
	  $scope.getRoutesOfBookings=function(bookings){
			 var differed=$q.defer();
			 
			 angular.forEach(bookings, function(booking,index) {
				 HunterServiceCalls.getSlotForBooking(booking.propertyAreaUuid,booking.propertySlotsUuid).then(function(response){
			    	  if(response.data.outLine){
			    		  var slotOutline=JSON.parse(response.data.outLine);
			    		  var latlan=slotOutline[0][0];
			    		  booking.routeurl='https://maps.google.com/maps?q='+latlan.lat+','+latlan.lng;
			    		  //$window.open('https://maps.google.com/maps?q='+latlan.lat+','+latlan.lng, '_blank');
			    	  }
			    	  if(bookings.length==index+1){
			    		  differed.resolve(bookings);
			    	  }
			    	
			      }).catch(function(){
			    	  differed.reject(bookings);
			      });
			 });
				return differed.promise;
		 };
		 //
	 
	   
	  HunterServiceCalls.getReservationBooking()
		 .then(function(response){
			 var Bookings=response.data;
			 var filteredBookings1= $filter('filter')(Bookings, function(booking){
				 var fromdate=moment(booking.reservationFrom, 'MM/DD/YYYY');
	                return moment().isSameOrBefore(fromdate,'day');
	            });
			 var filteredBookings2 = $filter('orderBy')(filteredBookings1, function(booking){
		           
		                return moment(booking.reservationFrom, 'MM/DD/YYYY').toDate();
		            },false);
			 //getting all properties
			 HunterServiceCalls.getPropertiesForHunter("ALL")
			      .then(function(response){
				
				    var properties=response.data;
				    angular.forEach(properties, function(property) {
				    	
				    	angular.forEach(filteredBookings2, function(booking) {
				    	if(booking.propertyUuid==property.propertyUuid){
				    		booking.property=property;
				    		booking.formattedDate=moment(booking.reservationFrom,'MM/DD/YYYY').format('MMM DD, YYYY');
				    	}	
				    	});
				    	
				    });
				    $scope.getRoutesOfBookings(filteredBookings2).then(function(bookings){
				    	$scope.Bookings=bookings;
				    });
				   // $scope.Bookings=filteredBookings2;
				 
				
			})
			.catch(function(err){
				
				   });
			 
		})
		.catch(function(err){
			
			});
	  
	  
	  
	  $scope.showRoutes=function(url){
		 
	    		  $window.open(url, '_blank');
	    	
	  };
	  
	  $scope.gotoLocationTracking=function(propertyUuid,slotUuid){
		  HunterServiceCalls.getSinglePropertyForHunter(propertyUuid).then(function(response){
				 var property=response.data;
				 sessionStorage.trackProperty=JSON.stringify(property);
				 sessionStorage.trackLocation=slotUuid;
		   $state.go("LocationTracking");
	   });
	  };
	  
	   
	  
  });