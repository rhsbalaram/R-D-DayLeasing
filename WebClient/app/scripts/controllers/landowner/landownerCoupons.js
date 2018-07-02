'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:landownerCoupons
 * @description
 * # landownerCoupons
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
  .controller('landownerCoupons', function ($state,$timeout, $scope,serviceCalls,$filter) {
	  $scope.property="";
	  sessionStorage.removeItem('property');
	  if(!sessionStorage.Authorization){
		  $state.go("login");
		  throw new Error("Something went badly wrong!");
	  }
	  
	  serviceCalls.getCoupons()
		 .then(function(response){
			 var reservations=response.data;
			 angular.forEach(reservations, function(coupon) {
				 coupon.formattedFromDate=moment(coupon.fromDate,'MM/DD/YYYY').format('MMM DD, YYYY');
				 coupon.formattedToDate=moment(coupon.toDate,'MM/DD/YYYY').format('MMM DD, YYYY');
			 });
			 $scope.coupons=reservations;
			 
		 }).catch(function(err){
			 
		 });
	  
	  $scope.editCoupon=function(coupon){
		  $scope.submitted=false;
		  $scope.CouponData=coupon;
          $('#myCoupons').modal('show');
		  
	  };
	  $scope.addCoupon=function(){
		  $scope.submitted=false;
		  $scope.CouponData={};
          $('#myCoupons').modal('show');
		  
	  };
	  
	  
	  $scope.submitted=false;
	  $scope.CouponData={};
	  $scope.amountrequired=true;
	  
	  $scope.changeOR=function(){
		  if(Number($scope.CouponData.percentageDiscount)>0||Number($scope.CouponData.amountDiscount)>0){
			  $scope.amountrequired=false;
		  }else{
			  $scope.amountrequired=true;
		  }
	  }
	 
	  $scope.addData=function(prop){
		  $scope.submitted=true; 
		  if(Number($scope.CouponData.percentageDiscount)>0||Number($scope.CouponData.amountDiscount)>0){
			  if(prop.$valid){
				  $('#myCoupons').modal('hide');
				  if($scope.CouponData.sno){
					  
					  serviceCalls.updateCoupon($scope.CouponData)
						 .then(function(response){
							 var reservations=response.data;
							 
							 $scope.coupons.push($scope.CouponData);
							 $scope.submitted=false;
							  $scope.CouponData={};
							  $scope.amountrequired=false;
							  $timeout(function(){
						 	 		$state.reload();
						 			},1000);
							//  $('#myCoupons').modal('hide');
							  
						 }).catch(function(err){
							 
						 });
					  
				  }else{
					  
					  serviceCalls.addCoupon($scope.CouponData)
						 .then(function(response){
							 var reservations=response.data;
							 
							 $scope.coupons.push($scope.CouponData);
							 $scope.submitted=false;
							  $scope.CouponData={};
							  $scope.amountrequired=false;
							  $timeout(function(){
						 	 		$state.reload();
						 			},1000);
							 // $('#myCoupons').modal('hide');
						 }).catch(function(err){
							 
						 });
					  
				  }
				  
			  }
			  
		  }else{
			  $scope.amountrequired=true;
		  }
		  
		  
	  };
	  
	  $scope.deleteCoupon=function(sno,index){
		  
		  swal({
    		  title: 'Are you sure?',
    		  text: "You won't be able to revert this!",
    		  type: 'warning',
    		  showCancelButton: true,
    		  confirmButtonColor: '#3085d6',
    		  cancelButtonColor: '#d33',
    		  confirmButtonText: 'Yes, delete it!'
    		}).then(function () {
    			
    			serviceCalls.deleteCoupon(sno).then(function(response){
    				$scope.coupons.splice(index, 1);
	    			
	    			 
	    			
	    			  swal(
	    		    		    'Deleted!',
	    		    		    'Your Coupons has been deleted.',
	    		    		    'success'
	    		    		  );
	    			  $state.reload();
	    		 }).catch(function(err){
	    			 console.log(err);
	    			 swal(
	    		    		    'Error!',
	    		    		    'Unable to Delete Coupon.',
	    		    		    'error'
	    		    		  );
	    		 });

    		});
		  
	  };
	  
  });