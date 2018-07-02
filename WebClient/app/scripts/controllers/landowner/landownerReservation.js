'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:landownerReservation
 * @description
 * # landownerReservation
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
  .controller('landownerReservation', function ($state, $scope,serviceCalls,$filter) {
	  $scope.property="";
	  sessionStorage.removeItem('property');
	  if(!sessionStorage.Authorization){
		  $state.go("login");
		  throw new Error("Something went badly wrong!");
	  }
   
   
    
	  
    
	  $scope.showupcoming=function(){
	    	$scope.reservations=$scope.upcomingreservations;
	    	$scope.upcoming=true;
	    };
	    
	    $scope.showhistory=function(){
	    	$scope.reservations=$scope.history;
	    	$scope.upcoming=false;
	    };
	    
	    $scope.deleteLandownerReservation=function(index,resId){
	    	///
	    	swal({
	    		  title: 'Are you sure?',
	    		  text: "You won't be able to revert this!",
	    		  type: 'warning',
	    		  showCancelButton: true,
	    		  confirmButtonColor: '#3085d6',
	    		  cancelButtonColor: '#d33',
	    		  confirmButtonText: 'Yes, Cancel it!'
	    		}).then(function () {
	    			
	    			serviceCalls.deleteLandownerReservation(resId).then(function(response){
	    	    		if(response.data.status&&response.data.status==true)
	    	    			{
	    	    			var booking = $scope.reservations[index];
	    	    			booking.paymentStatus="Cancelled";
	    	    			}
	    	    	}).catch(function(err){
		    			 console.log(err);
		    			 swal(
		    		    		    'Error!',
		    		    		    'Unable to cancel.',
		    		    		    'error'
		    		    		  );
		    		 });

	    		});
	    	//
	    	
	    }
    
    
    serviceCalls.getLandownerReservation()
	 .then(function(response){
		
		 var reservations=response.data;
		 
		 angular.forEach(reservations, function(booking) {
			 booking.formattedDate=moment(booking.reservationFrom,'MM/DD/YYYY').format('MMM DD, YYYY');
			 var parse=undefined;
			 if(booking.paymentMailId!=null||booking.paymentMailId!="null"){
			 try{
				  parse=JSON.parse(booking.paymentMailId); 
				 booking.paymentId=limitingRow(parse.name);
				 booking.payId=limitingRow(booking.paymentSuccessId);
			 }catch(err){
				 booking.paymentId=limitingRow(booking.paymentMailId);
			 }
			 }
			 
			 
			
			 
		 });
		 var filteredBookings = $filter('orderBy')(reservations, function(booking){
	           
             return moment(booking.reservationFrom, 'MM/DD/YYYY').toDate();
            
         },false);
		 
		 $scope.upcomingreservations= $filter('filter')(filteredBookings, function(booking){
			 var fromdate=moment(booking.reservationFrom, 'MM/DD/YYYY');
			 
                return moment().isSameOrBefore(fromdate,'day');
            });
		 $scope.history= $filter('filter')(filteredBookings, function(booking){
			 var fromdate=moment(booking.reservationFrom, 'MM/DD/YYYY');
                return moment().isAfter(fromdate,'day');
            });
		 
		 $scope.showupcoming();
	})
	.catch(function(err){
		
		});
    
    function limitingRow(value){
    	var splittedName=value.split("");
    	 var name="";
		 for(var i=0;i<splittedName.length;i++)
			 {
			 
			 if(i%13==0)
				 {
				 name=name+" ";
				 }
				 name=name+splittedName[i];
			 }
		 return name;
    }
 
    
 	
  });
