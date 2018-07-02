'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:hunterBookSlot
 * @description
 * # hunterBookSlot
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp')
  .controller('hunterBookSlot', function ($timeout,$state, $scope,HunterServiceCalls,$compile,$interval,$location,$stateParams,$rootScope,blockUI,$cookies) {
	 
	  var propertyid=$stateParams.propertyId;
	  if(!sessionStorage.Authorization){
		  $rootScope.login=false;
	  }
	  else{
		  $rootScope.login=true;
	  }
	   var accessToken = 'pk.eyJ1IjoibWFkaHVyaWd1cHRhMTYiLCJhIjoiY2o3cmJidmpxNGJ5cDJxbzJhZWljY2l2MiJ9.gBDVvx5MMVsVfzsDxGNhvQ';
	   // Replace 'mapbox.streets' with your map id.
	   var mapboxTiles = L.tileLayer('https://api.mapbox.com/styles/v1/mapbox/satellite-streets-v10/tiles/256/{z}/{x}/{y}?access_token=' + accessToken, {
	       attribution: ''
	   });
	   //default with empty cart
	   $scope.cart={};
	   $scope.cart.selectedDate=moment().format('MM/DD/YYYY');
	   $scope.cart.minDate=moment().format('MM/DD/YYYY');
	   $scope.cart.bookings=[];
	   $scope.cart.cartcount=false;
	   $scope.cart.total=0;
	   
	   $scope.map = L.map('outlinemap')
	       .addLayer(mapboxTiles)
	       .setView([42.355119, -71.065664], 30);
	   if($location.path().indexOf('hunterBookSlotCartDetails')>=0){
		   //for cart page disable dragging and pan
		   	$scope.map.dragging.disable();
		   	$scope.map.touchZoom.disable();
		   	$scope.map.doubleClickZoom.disable();
		   	$scope.map.scrollWheelZoom.disable();
		   	//
       }
	
	   
	   $scope.gotoHunterCartDetails=function(){
		   $state.go('hunterBookSlotCartDetails',{propertyId:propertyid});
	   }
	   //when the slot is added to cart its color and alert text will get changed
	   $scope.addToCart=function(propUUID,areaUUID,slotUUID){
		   $scope.map.eachLayer(function(layer) {
			    if(layer.slotUUID&&layer.slotUUID==slotUUID){
			    	var slot=$scope.property.areas[layer.areaindex].slots[layer.slotindex]
			    	$scope.map.removeLayer(layer);
			    	slot.color='#ff6c00';
			    		slot.disabled=true;
	    	 			//var popuphtml='<div class="pop-area"><div class="pop-orange-hed">Slot: '+layer.slotName+'  &nbsp;  |   &nbsp; $'+layer.cost+'/ Day</div><span>Date: <b>'+moment().format('MMM DD, YYYY')+'</b></span><span>Size: <b>'+layer.slotSize+' yards</b></span><span>Max Hunter: <b>'+layer.maxHunter+'</b></span><p>'+layer.propertyAreaNote+'</p><span ng-show="false" class="text-center"><button type="submit" ng-click="addToCart(\''+propUUID+'\' , \''+areaUUID+'\' , \''+slotUUID+'\')" class="btn update-btn pop-btn"><img src="../assets/img/cart-icon-white.png"> Add Slot</button></span></div>';
	    	 			addslotLayer(layer.areaindex,layer.slotindex,propUUID,areaUUID,slotUUID);
	    	 			 var booking={};
	    	 			 booking.propertyUuid=propUUID;
	    	 			booking.propertyAreaUuid=areaUUID;
	    	 			booking.propertySlotsUuid=slotUUID;
	    	 			booking.reservationFrom=$scope.cart.selectedDate;
	    	 			booking.formattedDate=moment(booking.reservationFrom,'MM/DD/YYYY').format('dddd, MMM DD, YYYY');
	    	 			booking.propertyName=$scope.property.propertyName;
	    	 			booking.propertyLocation=$scope.property.address;
	    	 			booking.slotName=slot.slotName;
	    	 			booking.reservationTo=$scope.cart.selectedDate;
	    	 			if($scope.cart.cartId){
	    	 				booking.cartId=$scope.cart.cartId;
	    	 			}
	    	 			
	    	 			booking.price=Number(slot.cost)*100;
	    	 			
	    	 			//blocking paticular slot at sserver
	    	 			HunterServiceCalls.blockBooking(booking).then(function(response){
	    	 				if(response.data.reservation!=null){
	    	 				$scope.cart.cartId=response.data.reservation.cartId;
	    	 				booking.propertyReservationUuid=response.data.reservation.propertyReservationUuid;
	    	 				
	    	 				$scope.cart.cartcount=true;
		    	 			if($scope.cart.bookings.length==0){
		    	 				timerTrigger();
		    	 			}
	    	 				$scope.cart.bookings.push(booking);  
	    	 				calculateTotal();
		    	 			sessionStorage.cart=JSON.stringify($scope.cart);
		    	 			
		    	 			}
	    	 				else{
	    	 					$scope.onDateChange();
	    	 				}
	    	 			}).catch(function(response){
	    	 				$scope.onDateChange();
	    	 			});
	    	 			
	    	 			 
			    }
			});
		 
	   };
	   //remove from cart and refresh the context
	   $scope.removeFromCart=function(index,propertyReservationUuid){
		   HunterServiceCalls.removeFromCart(propertyReservationUuid).then(function(response){
			   $scope.cart.bookings.splice(index, 1);
			   if($scope.cart.bookings.length==0){

		   			$scope.timer='';
		   			$scope.cart.timer=undefined;
		  	   $scope.cart.bookings=[];
		  	 $scope.cart.cartcount=false;
		  	 $scope.cart.total=0;
		  	 $scope.onDateChange();
		   		 sessionStorage.removeItem('cart');
		   		 $interval.cancel($scope.stop);
		   		$state.go("hunterBookSlot",{propertyId:propertyid});
		   	
		   		
			   }else{
				   sessionStorage.cart=JSON.stringify($scope.cart);
				   $state.reload();
			   }
			   
		   });
		   
	   };
	   //calculate the total on change of every state
	   function calculateTotal(){
	   $scope.cart.total=0
	   angular.forEach($scope.cart.bookings, function(booking) {
		   $scope.cart.total=$scope.cart.total+Number(booking.price);
	   });
	   }
	   //on date change it will get all details from server on paticular date and compare with the slot added in cart also with slot dates and show them as unblocked slots
	   $scope.onDateChange=function(){
		   $timeout(function(){
	 			
           	$scope.map.fitBounds(layerPolygonOutline._latlngs);
	 		},0);
		   if($scope.property&&$scope.property.areas){
           	
			      ////////////
		   	    		 HunterServiceCalls.getReservationForDate($scope.cart.selectedDate,$scope.property.propertyUuid)
		   	    		 .then(function(response){
		   	    			removeSlotLayers();
		   	    			
		   	    			 var bookings=response.data;
		   	    			 var cost='N/A';
		   	    			angular.forEach($scope.property.areas, function(area,areaindex) {
		   	    				
		   	    				var isBlocked=true;
		   	    				angular.forEach(area.dates, function(date) {
		   	    					if(isBlocked==true){
		   	    				 var fromdate=moment(date.fromDate, 'MM/DD/YYYY');
		       	        	  var todate=moment(date.toDate,'MM/DD/YYYY');
		       	        	  var dateToCheck=moment($scope.cart.selectedDate,'MM/DD/YYYY');
		       	        	  if(dateToCheck.isBetween(fromdate,todate,'day','[]'))
		       	        		  {
		       	        		 isBlocked=false;
		       	        		 cost=date.cost;
		       	        		 
		       	        		  }
		   	    				}
		       	        	  
		   	    				});
			    	    		 
			    	 	    	angular.forEach(area.slots, function(slot,slotindex) {
			    	 	    		if(slot.outLine)
			    	 	    			{
			    	 	    			slot.layerPolygon="";
			    	 	    			var color;
			    	 	    			var popuphtml;
			    	 	    			var disabled;
			    	 	    			if(isBlocked==true){
			    	 	    				slot.cost=cost;
			    	 	    				 color='#b7b7b7';
			    	 	    				 disabled=true;
			    	 	    			//	popuphtml='<div class="pop-area"><div class="pop-orange-hed">Slot: 3-A2  &nbsp;  |   &nbsp; $100/ Day</div><span>Date: <b>May 17, 2017</b></span><span>Size: <b>50 yards</b></span><span>Max Hunter: <b>30</b></span><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ipsum libero, vehicula et egestas quis, vulputate</p><span class="text-center"><button type="submit" ng-click="addToCart(\''+$scope.property.propertyUuid.toString()+'\' , \''+area.propertyAreaUuid.toString()+'\' , \''+slot.propertySlotsUuid.toString()+'\')" class="btn update-btn pop-btn"><img src="../assets/img/cart-icon-white.png"> Add Slot</button></span></div>';
			    	 	    			}
			    	 	    			
			    	 	    			else if(isBlocked==false){
			    	 	    				//color='#b7b7b7';
			    	 	    				//popuphtml='<div class="pop-area"><div class="pop-orange-hed">Slot: 3-A2  &nbsp;  |   &nbsp; $100/ Day</div><span>Date: <b>May 17, 2017</b></span><span>Size: <b>50 yards</b></span><span>Max Hunter: <b>30</b></span><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ipsum libero, vehicula et egestas quis, vulputate</p><span class="text-center"><button type="submit" ng-click="addToCart(\''+$scope.property.propertyUuid.toString()+'\' , \''+area.propertyAreaUuid.toString()+'\' , \''+slot.propertySlotsUuid.toString()+'\')" class="btn update-btn pop-btn"><img src="../assets/img/cart-icon-white.png"> Add Slot</button></span></div>';
			    	 	    				angular.forEach(bookings,function(booking){
			    	 	    					if(slot.propertySlotsUuid==booking.propertySlotsUuid){
			    	 	    						slot.cost=cost;
			    	 	    						 color='#b7b7b7';
			    	 	    						disabled=true;
			    		    	 	    			//	popuphtml='<div class="pop-area"><div class="pop-orange-hed">Slot: 3-A2  &nbsp;  |   &nbsp; $100/ Day</div><span>Date: <b>May 17, 2017</b></span><span>Size: <b>50 yards</b></span><span>Max Hunter: <b>30</b></span><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ipsum libero, vehicula et egestas quis, vulputate</p><span class="text-center"><button type="submit" ng-click="addToCart(\''+$scope.property.propertyUuid.toString()+'\' , \''+area.propertyAreaUuid.toString()+'\' , \''+slot.propertySlotsUuid.toString()+'\')" class="btn update-btn pop-btn"><img src="../assets/img/cart-icon-white.png"> Add Slot</button></span></div>';
			    		    	 	    			
			    	 	    					}
			    	 	    					
			    	 	    				});
			    	 	    				angular.forEach($scope.cart.bookings,function(booking){
			    	 	    					if(slot.propertySlotsUuid==booking.propertySlotsUuid&&booking.reservationFrom==$scope.cart.selectedDate){
			    	 	    						slot.cost=cost;
			    	 	    						 color='#ff6c00';
			    	 	    						disabled=true;
			    		    	 	    			//	popuphtml='<div class="pop-area"><div class="pop-orange-hed">Slot: 3-A2  &nbsp;  |   &nbsp; $100/ Day</div><span>Date: <b>May 17, 2017</b></span><span>Size: <b>50 yards</b></span><span>Max Hunter: <b>30</b></span><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ipsum libero, vehicula et egestas quis, vulputate</p><span class="text-center"><button type="submit" ng-click="addToCart(\''+$scope.property.propertyUuid.toString()+'\' , \''+area.propertyAreaUuid.toString()+'\' , \''+slot.propertySlotsUuid.toString()+'\')" class="btn update-btn pop-btn"><img src="../assets/img/cart-icon-white.png"> Add Slot</button></span></div>';
			    		    	 	    			
			    	 	    					}
			    	 	    					
			    	 	    				});
			    	 	    				if(!color){
			    	 	    					slot.cost=cost;
			    	 	    					 color='#00ff00';
			    	 	    					disabled=false;
		 		    	 	    				//popuphtml='<div class="pop-area"><div class="pop-orange-hed">Slot: 3-A2  &nbsp;  |   &nbsp; $100/ Day</div><span>Date: <b>May 17, 2017</b></span><span>Size: <b>50 yards</b></span><span>Max Hunter: <b>30</b></span><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ipsum libero, vehicula et egestas quis, vulputate</p><span class="text-center"><button type="submit" ng-click="addToCart(\''+$scope.property.propertyUuid.toString()+'\' , \''+area.propertyAreaUuid.toString()+'\' , \''+slot.propertySlotsUuid.toString()+'\')" class="btn update-btn pop-btn"><img src="../assets/img/cart-icon-white.png"> Add Slot</button></span></div>';
		 		    	 	    			
			    	 	    				}
			    	 	    			}
			    	 	    			slot.color=color;
			    	 	    			slot.disabled=disabled;
			    	 	    			slot.propertyAreaNote=area.propertyAreaNote||"";
			    	 	    			//popuphtml='<div class="pop-area"><div class="pop-orange-hed">Slot: '+slot.slotName+'  &nbsp;  |   &nbsp; $'+slot.cost+'/ Day</div><span>Date: <b>'+moment().format('MMM DD, YYYY')+'</b></span><span>Size: <b>'+slot.slotSize+' yards</b></span><span>Max Hunter: <b>'+slot.maxHunter+'</b></span><p>'+slot.propertyAreaNote+'</p><span ng-show="'+!disabled+'" class="text-center"><button type="submit" ng-click="addToCart(\''+$scope.property.propertyUuid.toString()+'\' , \''+area.propertyAreaUuid.toString()+'\' , \''+slot.propertySlotsUuid.toString()+'\')" class="btn update-btn pop-btn"><img src="../assets/img/cart-icon-white.png"> Add Slot</button></span></div>';
			    	 	    			addslotLayer(areaindex,slotindex,$scope.property.propertyUuid,area.propertyAreaUuid,slot.propertySlotsUuid);
			    	 	    			}
			    	 	    		
			    	 	    	});
			    	 	    	
			    	 	    });
		   	    			 
		   	    			 
		   	    			
		   	    		})
		   	    		.catch(function(err){
		   	    			
		   	    			});
		   	    		 ////////
			            	
			    	    	
			    	    	
			    	    	 
			    	    	
			    	    	
			    	    }
	   };
	   
	   //////////payment configuration
	   var handler = StripeCheckout.configure({
           //public key of the registered user on Stripe
           key: 'pk_test_JalvcPvMh2CFQ43xq1Xuj3HZ',
           //Can be replaced with dayleasing image.
           image: 'https://stripe.com/img/documentation/checkout/marketplace.png',
           locale: 'auto',

           token: function (token) {
               // You can access the token ID with `token.id`.
               // Get the token ID to your server-side code for use.
        	   blockUI.start("Payment is in progress..\n Please wait..") 
               console.log(token);
               if (token) {
            	   
            	   $scope.cart.paymentToken=token;
            	   $scope.cart.userName=sessionStorage.userFname;
            	   HunterServiceCalls.confirmBooking($scope.cart,$scope.cart.cartId).then(function(response){
            		   blockUI.stop();
            		   var paymentStatus = response.data.cart.paymentStatus;
            		   if(paymentStatus=="success"){
            			   swal(
            					   'Congratulations!',
            					   'Your Payment Success!',
            					   'success'
            					 )
            		   }
            		   else{
            			   if(paymentStatus=="refund"){
            				   swal(
                					   'OOPS!',
                					   'Unable to process payment. Refund will be done in 5-7 bussiness days!',
                					   'error'
                					 )
            			   }else{
            				   swal(
                					   'OOPS!',
                					   'Your transaction failed!',
                					   'error'
                					 ) 
            			   }
            			   
            		   }
            		  
            		   $scope.timer='';
       	   			$scope.cart.timer=undefined;
       	  	      $scope.cart.bookings=[];
       	  	     $scope.cart.cartcount=false;
       	  	  $scope.cart.total=0;
       	   		 sessionStorage.removeItem('cart');
       	   	 $state.go("hunterProperties",{propertyId:'ALL'});
            	   }).catch(function(error){
            		   blockUI.stop();
            		   swal(
        					   'OOPS!',
        					   'Something went wrong!',
        					   'error'
        					 )
        					  $scope.timer='';
          	   			$scope.cart.timer=undefined;
          	  	      $scope.cart.bookings=[];
          	  	     $scope.cart.cartcount=false;
          	  	  $scope.cart.total=0;
          	   		 sessionStorage.removeItem('cart');
          	   	 $state.go("hunterProperties",{propertyId:'ALL'});
            	   });


               } else {
                   console.log("error in getting token");
                   blockUI.stop();
                   swal(
    					   'OOPS!',
    					   'Something went wrong!',
    					   'error'
    					 )
    					  $scope.timer='';
      	   			$scope.cart.timer=undefined;
      	  	      $scope.cart.bookings=[];
      	  	     $scope.cart.cartcount=false;
      	  	  $scope.cart.total=0;
      	   		 sessionStorage.removeItem('cart');
      	   	 $state.go("hunterProperties",{propertyId:'ALL'});
               }
              
	
           }

       }); 
	   ///payment methods
	   $scope.doPayment=function(){
		   if(!sessionStorage.Authorization){
			   $state.go("login");
			  }
		   else{
			   if($scope.cart.total==0&&$scope.cart.bookings.length>0){
				   ///
				   swal({
			    		  title: 'Are you sure?',
			    		  text: "You want to Proceed!",
			    		  type: 'warning',
			    		  showCancelButton: true,
			    		  confirmButtonColor: '#3085d6',
			    		  cancelButtonColor: '#d33',
			    		  confirmButtonText: 'Yes, Proceed.'
			    		}).then(function () {
			    			blockUI.start("Payment is in progress..\n Please wait..");
							   var token={};
							   token.id="dummyIdToProcessRequest";
							   var card={};
							   card.name=sessionStorage.emailId;
							   token.card=card;
				               console.log(token);
				               if (token) {
				            	   
				            	   $scope.cart.paymentToken=token;
				            	   $scope.cart.userName=sessionStorage.userFname;
				            	   HunterServiceCalls.confirmBooking($scope.cart,$scope.cart.cartId).then(function(response){
				            		   blockUI.stop();
				            		   var paymentStatus = response.data.cart.paymentStatus;
				            		   if(paymentStatus=="success"){
				            			   swal(
				            					   'Congratulations!',
				            					   'Your Booking Success!',
				            					   'success'
				            					 )
				            		   }
				            		   else{
				            			   if(paymentStatus=="refund"){
				            				   swal(
				                					   'OOPS!',
				                					   'Unable to do Booking',
				                					   'error'
				                					 )
				            			   }else{
				            				   swal(
				                					   'OOPS!',
				                					   'Your Booking failed!',
				                					   'error'
				                					 ) 
				            			   }
				            			   
				            		   }
				            		  
				            		   $scope.timer='';
				       	   			$scope.cart.timer=undefined;
				       	  	      $scope.cart.bookings=[];
				       	  	     $scope.cart.cartcount=false;
				       	  	  $scope.cart.total=0;
				       	   		 sessionStorage.removeItem('cart');
				       	   	 $state.go("hunterProperties",{propertyId:'ALL'});
				            	   }).catch(function(error){
				            		   blockUI.stop();
				            		   swal(
				        					   'OOPS!',
				        					   'Something went wrong!',
				        					   'error'
				        					 )
				        					  $scope.timer='';
				          	   			$scope.cart.timer=undefined;
				          	  	      $scope.cart.bookings=[];
				          	  	     $scope.cart.cartcount=false;
				          	  	  $scope.cart.total=0;
				          	   		 sessionStorage.removeItem('cart');
				          	   	 $state.go("hunterProperties",{propertyId:'ALL'});
				            	   });


				               }
			    			
			    		});
				   /////
				   
			   }
			   
			   else{ 
		   handler.open({
               name: 'Day Leasing',
               description: 'Stripe Payment',
               amount: $scope.cart.total
           });
		   }
		  
		   }
		 
		   
	   };
	   //
	   $scope.cart.dummyCouponCode="";
	   //validating the coupon code from the server
	   $scope.validateCouponCode=function(){
		   HunterServiceCalls.getCouponCode($scope.cart.dummyCouponCode).then(function(response){
			   if(response.data!=""&&response.data.usageCount!=-1){
				   $scope.couponError="";
				   $scope.minimumAmount="";
				   var DayleasingCoupon=response.data;
				   var percentageDiscount = DayleasingCoupon.percentageDiscount;
					var amountDiscount = DayleasingCoupon.amountDiscount;
					var amountLimit = DayleasingCoupon.amountLimit;
				  var minimumAmountCriteria= DayleasingCoupon.minimumAmountCriteria;
				  //check for minimum amount cretia if not set to 0
				  if(minimumAmountCriteria==null||minimumAmountCriteria==""){
					  minimumAmountCriteria=0;
				  }
				  calculateTotal();
				  $scope.cart.couponCode=undefined;
				  //apply code if reached criteria.
				  if($scope.cart.total>(minimumAmountCriteria*100)){
					  $scope.cart.couponCode=$scope.cart.dummyCouponCode;
					  //check if it contains percentage criteria coupon
					  if(percentageDiscount!=null&&percentageDiscount!=""&&percentageDiscount!="null"){
						  var discount=$scope.cart.total*(percentageDiscount/100);
						  //check if it si amount limit criteria
						  if(amountLimit!=null&&amountLimit!=""&&amountLimit!="null"){
								
								if(discount>(amountLimit*100)){
									discount=amountLimit*100;
								}
							}
						  $scope.cart.total=$scope.cart.total-discount;
					  }else if(amountDiscount!=null&&amountDiscount!=""&&amountDiscount!="null"){
							
						  $scope.cart.total= $scope.cart.total-(amountDiscount*100);
						}
					  
					 
				  }
				  else{
					  $scope.couponError="";
					  $scope.minimumAmount="Minimum amount should be above $"+minimumAmountCriteria;
				  }
			   }
			   else{
				   if(response.data!=""&&response.data.usageCount==-1){
					   $scope.minimumAmount="";
					   $scope.couponError="This promo code has exceeded the maximum usage limit.If you feel this is an error, Please contact us at support@dayleasing.com or call us at 214-773-1300";
					  
				   }else{
					   $scope.minimumAmount="";
					   $scope.couponError="Promocode Not Applicable";
				     
				   }
				   }
			   
		   });
	   };
	   

	   /////////////////future code for if coupon code change in logic///////////////////////////////
	   /*
	   $scope.validateCouponCode=function(){
		   HunterServiceCalls.getCouponCode($scope.cart.dummyCouponCode).then(function(response){
			   if(response.data!=""&&response.data.usageCount!=-1){
				   $scope.couponError="";
				   $scope.minimumAmount="";
				   var DayleasingCoupon=response.data;
				   var percentageDiscount = DayleasingCoupon.percentageDiscount;
					var amountDiscount = DayleasingCoupon.amountDiscount;
					var amountLimit = DayleasingCoupon.amountLimit;
					var fromDateData=DayleasingCoupon.fromDateData;
					
					var toDateData=DayleasingCoupon.toDateData;
				  var minimumAmountCriteria= DayleasingCoupon.minimumAmountCriteria;
				  //check for minimum amount cretia if not set to 0
				  if(minimumAmountCriteria==null||minimumAmountCriteria==""){
					  minimumAmountCriteria=0;
				  }
				  calculateTotal();
				  $scope.cart.couponCode=undefined;
				  //apply code if reached criteria.
				  if($scope.cart.total>(minimumAmountCriteria*100)){
					  $scope.cart.couponCode=$scope.cart.dummyCouponCode;
					  //check if it contains percentage criteria coupon
					  var discount=0;
					  if(percentageDiscount!=null&&percentageDiscount!=""&&percentageDiscount!="null"){
						  //iterate all booking to validate coupon
						  
						  angular.forEach($scope.cart.bookings, function(booking) {
							  var fromdate=moment(fromDateData, 'MM/DD/YYYY');
		       	        	  var todate=moment(toDateData,'MM/DD/YYYY');
		       	        	  var dateToCheck=moment(booking.reservationFrom,'MM/DD/YYYY');
		       	        	  if(dateToCheck.isBetween(fromdate,todate,'day','[]'))
		       	        		  {
		       	        		discount=discount+(Number(booking.price)*(percentageDiscount/100));
		       	        		  }
							   
						   });
						  
						  //check if it si amount limit criteria
						  if(amountLimit!=null&&amountLimit!=""&&amountLimit!="null"){
								
								if(discount>(amountLimit*100)){
									discount=amountLimit*100;
								}
							}
						  
					  }else if(amountDiscount!=null&&amountDiscount!=""&&amountDiscount!="null"){
							
						  angular.forEach($scope.cart.bookings, function(booking) {
							  var fromdate=moment(fromDateData, 'MM/DD/YYYY');
		       	        	  var todate=moment(toDateData,'MM/DD/YYYY');
		       	        	  var dateToCheck=moment(booking.reservationFrom,'MM/DD/YYYY');
		       	        	  if(dateToCheck.isBetween(fromdate,todate,'day','[]'))
		       	        		  {
		       	        		discount=discount+(Number(booking.price)*(percentageDiscount/100));
		       	        		  }
							   
						   });
						  $scope.cart.total= $scope.cart.total-(amountDiscount*100);
						}
					  
					 
				  }
				  else{
					  $scope.couponError="";
					  $scope.minimumAmount="Minimum Amount Should be $"+minimumAmountCriteria;
				  }
			   }
			   else{
				   if(response.data!=""&&response.data.usageCount==-1){
					   $scope.minimumAmount="";
					   $scope.couponError="This promo code has exceeded the maximum usage limit.If you feel this is an error, Please contact us at support@dayleasing.com or call us at 214-773-1300";
					  
				   }else{
					   $scope.minimumAmount="";
					   $scope.couponError="Promocode Not Applicable";
				     
				   }
				   }
			   
		   });
	   };
	    DayleasingCoupons dayleasingCoupons = propertyReservationDao.getCoupon(hunterId, coupon);
		 dayleasingCoupons.setFromDateData(DateUtils.generateStringFromDate(dayleasingCoupons.getFromDate()));
		 dayleasingCoupons.setToDateData(DateUtils.generateStringFromDate(dayleasingCoupons.getToDateData()));
		 return dayleasingCoupons;
		 */
	   //////////////////////////////////////////////////////end////////////////////////////////////////////
	   
	   
	   //check whether session having property
	   if(sessionStorage.property&&sessionStorage.property!="")
	  	{
	  	$scope.property=JSON.parse(sessionStorage.property);
	  	//check whether session having cart
	  	if(sessionStorage.cart&&sessionStorage.cart!=""){
	  		$scope.cart=JSON.parse(sessionStorage.cart);
	  		timerTrigger();
	  		
	  	}else{
	  		if(!$cookies.get(sessionStorage.userId+'singleModel')){
	  			 $timeout(function(){
			 			
	 	  			$('#datemodel').modal('show');
	 		 		},3000);
		 		
		 	}
	  		
	  		 
	  	}
	  	//check url property id and current property id are same
	  	if(propertyid!=	$scope.property.propertyUuid){
	  		$state.go("hunterProperties",{propertyId:propertyid});
	  	}

	  	}
	   else{
	  		
	  		 $state.go("hunterProperties",{propertyId:propertyid});
	  		
	  	}
	   var layerPolygonOutline;//global define
	   //draw property outline
	   if($scope.property&&$scope.property.outLine){
	 		 
		 	layerPolygonOutline = new L.Polygon(JSON.parse($scope.property.outLine),{
	                color: '#ff0',
	                weight: 2,
	                opacity:1,	
					fill:false
	            });
		 		$scope.map.addLayer(layerPolygonOutline);
		 		
	            $timeout(function(){
		 			
	            	$scope.map.fitBounds(layerPolygonOutline._latlngs);
		 		},0);
	            //getting all slots and placing on map
	            $scope.onDateChange();
	            //if it is cart page check wheter it has cart amount 
	            if($location.path().indexOf('hunterBookSlotCartDetails')>=0&&$scope.cart.total==0&&$scope.cart.bookings.length==0){
	            	$state.go("hunterBookSlot",{propertyId:propertyid});
	            }
	            //else calculate the original amount with out couponcode
	            else{
	            	
	            	calculateTotal();
	            }
	            
		 	 }
	   
	   

	  
	 //adding the layer on map with given information on method call
	   	function addslotLayer(areaindex,slotindex,propertyUuid,propertyAreaUuid,propertySlotsUuid){
	   		
	   		var slot=$scope.property.areas[areaindex].slots[slotindex];
	  
	   		var layerPolygon = new L.Polygon(JSON.parse(slot.outLine),{
 	 			color: slot.color,
 		        weight: 2,
 		        opacity:1,
 		 		fill:true,
 		 		fillOpacity:0.2
             });
	   		if(slot.cost=="N/A")
	   			{
	   			slot.costtext="Not available.";
	   			}
	   		else{
	   			slot.costtext="$ "+slot.cost+"/ Day";
	   		}
	   		var latlanofSlot=layerPolygon._latlngs["0"]["0"];
	   	 	var popuphtml='<div class="pop-area"><div class="pop-orange-hed">Slot: '+slot.slotName+'  &nbsp;  |   &nbsp; '+slot.costtext+'</div><span>Date: <b>'+moment($scope.cart.selectedDate,'MM/DD/YYYY').format('MMM DD, YYYY')+'</b></span><span>Size: <b>'+slot.slotSize+' yards</b></span><span>Slot Details: <b>'+slot.maxHunter+'</b></span><p>'+slot.propertyAreaNote+'<a target="_blank" href="https://maps.google.com/maps?q='+latlanofSlot.lat+','+latlanofSlot.lng+'" class="pull-right">Show Location</a></p><span ng-show="'+!(slot.disabled)+'" class="text-center"><button type="submit" ng-click="addToCart(\''+propertyUuid.toString()+'\' , \''+propertyAreaUuid.toString()+'\' , \''+propertySlotsUuid.toString()+'\')" class="btn update-btn pop-btn"><img src="../assets/img/cart-icon-white.png"> Add Slot</button></span></div>';
 			layerPolygon.slotUUID=slot.propertySlotsUuid;
 			layerPolygon.areaindex=areaindex;
 			layerPolygon.slotindex=slotindex;
 			var binding = $compile(popuphtml)($scope);
 			layerPolygon.bindPopup(binding[0]);
 			$timeout(function(){
 	 		$scope.map.addLayer(layerPolygon);
 			},10);
 	 		layerPolygon.on('click', function (e) {
 	 		//	e.target.options.color='#ff6c00';
 	 			var layer = e.target;
 	 			/* layer.setStyle({
 	 	           
 	 	            color: '#ff6c00',
 	 	            
 	 	        });*/
	    	 });
	   	}
	   	//remove on state change ie.. added to cart or removed
	   	function removeSlotLayers(){
	   	 $scope.map.eachLayer(function(layer) {
  			    if(layer.slotUUID){
  			    	$scope.map.removeLayer(layer);
  			    	}
  			    
	   	});
	   	}
	   	//show the timer once slot is added to cart with  5mins if reached change the cart state to empty
	   	function timerTrigger(){
	   		
	   		if(!$scope.cart.timer||$scope.cart.timer==''){
	   		var timerMoment=moment().add(5, 'minutes');
	   			$scope.cart.timer=timerMoment.valueOf();
	   			sessionStorage.cart=JSON.stringify($scope.cart);
	   				}
	   	$scope.stop=$interval(function(){
	   		var duration=$scope.cart.timer-(moment().valueOf());
	   		if(duration<=0){
	   			$scope.timer='';
	   			$scope.cart.timer=undefined;
	  	   $scope.cart.bookings=[];
	  	 $scope.cart.cartcount=false;
	  	 $scope.cart.total=0;
	  	 $scope.onDateChange();
	   		 sessionStorage.removeItem('cart');
	   		 $interval.cancel($scope.stop);
	   		$state.go("hunterProperties",{propertyId:'ALL'});
	   	
	   		}else{
	   			duration = moment.duration(duration, 'milliseconds');
	   			$scope.timer=moment.utc(duration.asMilliseconds()).format('mm:ss')+' mins left!';
	   		}
	   		
	   	},1000);
	   	//kill interval on page change
	    $scope.$on('$destroy',function(){
	        if($scope.stop){
	        	 $interval.cancel($scope.stop);
	        }
	            
	    });
	   	
	   		/*var time = 7200;
	   		var duration = moment.duration(time * 1000, 'milliseconds');
	   		var interval = 1000;

	   		setInterval(function(){
	   		  duration = moment.duration(duration.asMilliseconds() - interval, 'milliseconds');
	   		  //show how many hours, minutes and seconds are left
	   		  $('.countdown').text(moment(duration.asMilliseconds()).format('h:mm:ss'));
	   		}, interval);*/
	   	};
	  
	    
	   	$scope.disableModal=function(){
	   		$cookies.put(sessionStorage.userId+'singleModel', true);
	   	};
	   	
	 	
	 	 
	 	
	  
  });