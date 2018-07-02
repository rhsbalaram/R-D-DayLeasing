'use strict';

angular.module('dayLeasingApp')
.directive('header',function($location,$timeout,$state){
	//directive to header
	  return {
			templateUrl: '../views/header.html',
			 link: function(scope) {


				 scope.goToCartPage=function(){
					 if(sessionStorage.property&&sessionStorage.property!=""&&sessionStorage.cart&&sessionStorage.cart!=""){
						 var cart=JSON.parse(sessionStorage.cart);
						 var property=JSON.parse(sessionStorage.property);
						 
						 if(cart.bookings.length>0){
							 $state.go('hunterBookSlotCartDetails',{propertyId:property.propertyUuid})
				            }else{
						  		 swal(
			        					   '',
			        					   'Please add Slots to Cart!',
			        					   ''
			        					 )
						  	}
					  		
					  		
					  	}else{
					  		 swal(
		        					   '',
		        					   'Please add Slots to Cart!',
		        					   ''
		        					 )
					  	}
				 };
				

		    }
		};
	})
	.directive('mainHeader',function($location,$timeout,$cookies){
	  return {
			templateUrl: '../views/mainheader.html',
			 link: function(scope) {
				 if(sessionStorage.userFname){
					 scope.userFname = sessionStorage.userFname; 
				 }
				 else{

				 $timeout(function(){
					 scope.userFname = sessionStorage.userFname;
					 },4000);
				 }

				
					 scope.logout=function(){
						 $cookies.remove('DayleasingAuthorization');
						 $location.path('/logout');
					 }
					

			    }
		};
	}).directive('hunterHeader',function($location,$timeout,$state,$cookies){
		  return {
				templateUrl: '../views/hunterheader.html',
				 link: function(scope) {
					 if(sessionStorage.userFname){
						 scope.userFname = sessionStorage.userFname; 
					 }
					 else{
					 $timeout(function(){
						 scope.userFname = sessionStorage.userFname;
						 },4000);
					 }

				
					 //
					 scope.goToCartPage=function(){
						 if(sessionStorage.property&&sessionStorage.property!=""&&sessionStorage.cart&&sessionStorage.cart!=""){
							 var cart=JSON.parse(sessionStorage.cart);
							 var property=JSON.parse(sessionStorage.property);
							 
							 if(cart.bookings.length>0){
								 $state.go('hunterBookSlotCartDetails',{propertyId:property.propertyUuid})
					            }else{
							  		 swal(
				        					   '',
				        					   'Please add Slots to Cart!',
				        					   ''
				        					 )
							  	}
						  		
						  		
						  	}else{
						  		 swal(
			        					   '',
			        					   'Please add Slots to Cart!',
			        					   ''
			        					 )
						  	}
					 };
					 scope.logout=function(){
						 $cookies.remove('DayleasingAuthorization');
						 $location.path('/logout');
					 }
					 //


						 
						 $timeout(function(){
							 document.getElementById("Focus").focus();
							 },1000);

				    }
			};
		})
.directive('sidebarActivate',function($location,$timeout){
	//slidebar on mobile with collapse and close
		return {
		link : function(scope, element, attrs,$location) {
			$timeout(function(){
	           

	            	$(".navbar-collapse").prepend( "<span class='menuClose'></span>" );
	            	$(".navbar-default .navbar-toggle").click(function(e){
	            		e.preventDefault();
	            		slideMenu();
	            	});
	            	$(".menuClose").click(function(e){
	            		e.preventDefault();
	            		slideMenuClose();
	            	});
	            	function slideMenu(){
	            	var p = $( "div.navbar-collapse" );
	            		var position = p.position();
	            		var left=p.css('left');
	            		//alert(right);
	            			if(left=="-270px"){
	            			p.animate({left:"0"});
	            				}
	            	}
	            	function slideMenuClose(){
		            	var p = $( "div.navbar-collapse" );
		            		var position = p.position();
		            		var left=p.css('left');
		            		//alert(right);
		            			
		            				p.animate({left:"-270px"});
		            			
		            	}
			},10);
	            
	            

		}
	};
	}).directive('propertyLocationSlide',function($location,$timeout){
		//sllide down when clicking on more details in properties listing
		return {
			link : function(scope, element, attrs,$location) {
				$timeout(function(){
					$(".dashbord-item").on("click",".propertiesReadMore.closed",function(){
						var data = $(this).closest(".propInfo").find(".propDesc").html();
						$(this).html("Less <i class='fa fa-chevron-up'></i>").removeClass("closed").addClass("open");
						$(this).closest(".dashbord-item").find(".propInfoDataIpad").html(data).promise().done(function(){
						        $(this).slideDown();
						    });
						});

						$(".dashbord-item").on("click",".propertiesReadMore.open",function(){
						$(this).html("More <i class='fa fa-chevron-down'></i>").removeClass("open").addClass("closed");
						$(this).closest(".dashbord-item").find(".propInfoDataIpad").slideUp();
						});

				},1000);

			}
		};
		})


.directive("leaf", function($timeout) {
	//leaflet for list properties
    return {
        restrict: 'E',
        scope:'@',
       replace:'true',
       template: "<div></div>",
        link: function(scope, element, attrs) {
            //scope.id = attrs.id;
            var  accessToken = 'pk.eyJ1IjoiYmFsYXJhbWEiLCJhIjoiY2owZHB5YmE4MDBmaDMybnFwbndlOXMyNyJ9.h1rT5NxNRmge0EvD4MiwhA';
            // Replace 'mapbox.streets' with your map id.
            var mapboxTiles = L.tileLayer('https://api.mapbox.com/styles/v1/mapbox/satellite-streets-v10/tiles/256/{z}/{x}/{y}?access_token=' + accessToken, {
                attribution: ''
            });
            $timeout(function(){
            	if(!scope.property)
            		{
            		scope.property=scope.booking.property;
            		scope.property.propertyUuid=scope.booking.propertyReservationUuid
            		}
            var map = L.map(scope.property.propertyUuid,{ zoomControl:false,attributionControl:false,scrollWheelZoom: false,dragging: false, tap: false})
                .addLayer(mapboxTiles)
                .setView([42.3610, -71.0587]);
            var polyLatLan=JSON.parse(scope.property.outLine) ;

         	var polygon = new L.Polygon(polyLatLan, {
                color: '#ff0',
                weight: 2,
                opacity:1,
				fill:false
            });



         		map.addLayer(polygon);
         		map.fitBounds(polygon._latlngs);
            });
        }
    };
})
.directive('onFinishRender', function () {
    return {
        restrict: 'A',
        link: function (scope, element, attr) {
            if (scope.$last) {
            	//scope.$emit('leaf');
            }
        }
    };
})
.directive('footer',function(){
	  return {
		templateUrl: '../views/footer.html'
	};
}).directive('mapHeight', function ($timeout) {
//dynamically change the map height when the inputs increased
    return {
        restrict: 'A',

        link: function (scope, elem, attrs) {


        	scope.$watch(
        		    function () {
        		    	var element = angular.element(document.querySelector('.loginForm2'));
                	return element[0].offsetHeight;
                	},
        		    function (newValue, oldValue) {

                    	if(newValue>550)
                    		{
                    		elem.css('height', newValue + 'px');
                    		$timeout(function(){
                    			scope.map.invalidateSize();
                    		},1000);

                    		}
                    	else{
                    		elem.css('height',  '550px');
                    		$timeout(function(){
                    			scope.map.invalidateSize();
                    		},1000);
                    	}

        		    }
        		  );




        }




        };
    })
  .directive('todaysDate',function ($timeout) {
	  //date comparision and setting min and max date in calendar
    return{
      restrict:'E',
      scope:{
        date:'='
      },
      link:function (scope,element,attrs) {
    	  $timeout(function(){
    		  
        		  var today = moment().format('MM/DD/YYYY');
        		  scope.date.minDate=today;
        	        
        	  
    		  
    		  //
    		  scope.$watch(function () {

    	            return scope.date.fromDate;
    	          },
    	          function (newValue, oldValue) {
    	        	 
    	        	 
    	        	  if(scope.date.fromDate){
    	        		  var fromdate=moment(newValue, 'MM/DD/YYYY');
        	        	  var todate=moment(scope.date.toDate,'MM/DD/YYYY');
        	        	  if(fromdate.isAfter(todate,'day'))
        	        		  {
        	        		  scope.date.toDate=newValue;
        	        		  }
    	        		  scope.date.maxDate=newValue;
    	        	  }
    	           
    	          }
    	        );
    		  
    	  },100);
    	  
        
        
        // c[0].setAttribute('min',today);
        
        // disable();
        // scope.$watch(scope.date);
        // console.log(scope.date);
        // var date=scope.date;
        // date.toDate=new Date(date.toDate);// for converting html5 date to js date.
        // date.fromDate=new Date(date.fromDate);
        // /*
        //  * for date validation*/
        // if(date.fromDate!='' && date.fromDate!=undefined && date.to!='' && date.fromDate!=undefined )
        // {
        //
        //   var to=date.toDate.getTime();
        //   if(to && from>to)
        //     $scope.invalid_date=true;
        // }


      }
    }

  }).directive('backhistory', function($window) {
	  return {
		    
		    link: function(scope, element, attrs) {
		    	element.on('click', function() {
		            $window.history.back();
		        });
		    }
		  };
		})
    .directive('stringToNumber', function() {
  return {
    require: 'ngModel',
    link: function(scope, element, attrs, ngModel) {
      ngModel.$parsers.push(function(value) {
    	  



        return '' + value;
      });
      ngModel.$formatters.push(function(value) {
    	  
        return parseFloat(value);
      });
    }
  };
})
  .directive('stringToDate', function($filter) {
    return{

      require: 'ngModel',
      link: function(scope, element, attrs, ngModel) {
        ngModel.$parsers.push(function(value) {
          console.log(moment(value).format('MM/DD/YYYY'));
          if(value)
          return moment(value).format('MM/DD/YYYY');



        });
        ngModel.$formatters.push(function(value) {
         
          console.log(moment(value, 'MM/DD/YYYY').toDate());
          if(value)
         return moment(value, 'MM/DD/YYYY').toDate();



        });


      }
  }})

;





