'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:AboutCtrl
 * @description # AboutCtrl Controller of the dayLeasingApp
 */
console.log('services');
angular.module('dayLeasingApp') .service('serviceCalls', function ($q,$rootScope,$http)  {
	
	var User={};
	
	this.validateLogin=function(user){
		
		
// var
// xyz={"fname":"test@gminor.net","password":"nopassword","dob":"2017-04-13T19:15:25.864Z","address1":"address1","state":"state","city":"city","zipCode":"121212","country":"india","emailId":"rhsbalaram@gmail.com"};
// var config = {
// headers : {
// 'Content-Type': 'application/json'
// }
// };
   
    	var differed=$q.defer();

    	$http.post($rootScope.URL+'/login',user)
    .then(function(response) {
        console.log('registration service success');
        sessionStorage.Authorization=response.data.Authorization;
        
        sessionStorage.userId=response.data.userId;
        
        

        differed.resolve(response);
    }).catch(function(response){

    	differed.reject(response);
    });
    return differed.promise;
	
};

this.loginFacebook = function(){
	var differed=$q.defer();
	$http.post($rootScope.URL+'/social/facebook',{})
	.then(function(response) {
		console.log('registration service success');
	
		differed.resolve(response);
	}).catch(function(response){
	
		differed.reject(response);
	});
	return differed.promise;
	};

this.registration=function(user){
	var differed=$q.defer();

	$http.post($rootScope.URL+'/registration',user)
.then(function(response) {
    console.log('registration service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
return differed.promise;
};

this.emailVerification=function(token){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=token;
	
	
	$http.post($rootScope.URL+'/emailVerification',$rootScope.config,$rootScope.config)
.then(function(response) {
    console.log('booking service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});

return differed.promise;
}; 

this.verifyFbAuthentication=function(token){
	var differed = $q.defer();
	$rootScope.config.headers.Authorization = token;

	$http.post($rootScope.URL + '/fbAuthVerification', $rootScope.config, $rootScope.config)
		.then(function (response) {
			console.log('fb auth success');
			sessionStorage.Authorization=response.data.Authorization;
			
			sessionStorage.userId=response.data.userId;

			differed.resolve(response);
		}).catch(function (response) {

			differed.reject(response);
		});

	return differed.promise;
};


this.changePasswordVerification=function(token){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=token;
	
	
	$http.post($rootScope.URL+'/changePasswordVerification',$rootScope.config,$rootScope.config)
.then(function(response) {
    console.log(' service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});

return differed.promise;
};

this.changePassword=function(token,password){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=token;
	
	
	$http.post($rootScope.URL+'/changePassword',password,$rootScope.config)
.then(function(response) {
    console.log(' service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});

return differed.promise;
};


this.sendVerification=function(email){
	var differed=$q.defer();
	
	
	
	$http.get($rootScope.URL+'/sendVerification?email='+email,$rootScope.config)
.then(function(response) {
    

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});

return differed.promise;
};
//validating capcha
this.validateCapcha=function(response){
	var differed=$q.defer();
	var validation={};
	validation.secret='6LfZTCcUAAAAABlkbf4xQRNRCWzdbXXwFKJbcJ9S';
	validation.response=response;

	$http.post('https://www.google.com/recaptcha/api/siteverify',validation)
.then(function(response) {
    console.log('registration service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
return differed.promise;
};


this.getUserDetails=function(userAuth){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=userAuth.data.Authorization;
	
	$http.get($rootScope.URL+'/person/'+userAuth.data.userId,$rootScope.config)
	.then(function(response) {
		 sessionStorage.userFname=response.data.fname;
		 sessionStorage.memberType=response.data.memberType;
		 sessionStorage.emailId=response.data.emailId;
// $rootScope.config.Authorization='';

		differed.resolve(response);
	}).catch(function(response){
		console.log('get userdetails failed ' );
// $rootScope.config.Authorization='';
		differed.reject(response);
});
return differed.promise;
};


this.getAllHunterDetails=function(){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	
	$http.get($rootScope.URL+'/user/hunterdetails/All',$rootScope.config)
	.then(function(response) {
		 
		differed.resolve(response);
	}).catch(function(response){
		
		differed.reject(response);
});
return differed.promise;
};


this.addProperty=function(property){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	if(property.propertyUuid){
		$http.put($rootScope.URL+'/user/'+sessionStorage.userId+'/property/'+property.propertyUuid,property,$rootScope.config)
		.then(function(response) {
		    console.log('property service success');

		    differed.resolve(response);
		}).catch(function(response){

			differed.reject(response);
		});
	}
	else{
	$http.post($rootScope.URL+'/user/'+sessionStorage.userId+'/property',property,$rootScope.config)
.then(function(response) {
    console.log('property service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
}
return differed.promise;
};



this.addArea=function(area,propertyUuid){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	if(area.propertyAreaUuid){
		$http.put($rootScope.URL+'/user/property/'+propertyUuid+'/area/'+area.propertyAreaUuid,area,$rootScope.config)
		.then(function(response) {
		    console.log('area service success');

		    differed.resolve(response);
		}).catch(function(response){

			differed.reject(response);
		});
	}
	else{
	$http.post($rootScope.URL+'/user/property/'+propertyUuid+'/area',area,$rootScope.config)
.then(function(response) {
    console.log('area service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
}
return differed.promise;
};


this.addSlot=function(slot,propertyAreaUuid){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	if(slot.propertySlotsUuid){
		$http.put($rootScope.URL+'/user/area/'+propertyAreaUuid+'/slot/'+slot.propertySlotsUuid,slot,$rootScope.config)
		.then(function(response) {
		    console.log('area service success');

		    differed.resolve(response);
		}).catch(function(response){

			differed.reject(response);
		});
	}
	else{
	$http.post($rootScope.URL+'/user/area/'+propertyAreaUuid+'/slot',slot,$rootScope.config)
.then(function(response) {
    console.log('area service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
}
return differed.promise;
};


this.getProperty=function(){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	
	$http.get($rootScope.URL+'/user/'+sessionStorage.userId+'/property',$rootScope.config)
.then(function(response) {
    console.log('property service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
return differed.promise;
};

this.getSingleProperty=function(propertyId){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	$http.get($rootScope.URL+'/user/'+sessionStorage.userId+'/property/'+propertyId,$rootScope.config)
.then(function(response) {
    console.log('property service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
return differed.promise;
};

// /////updatePropertyBoundaries
this.updatePropertyBoundaries=function(property){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	
		$http.put($rootScope.URL+'/user/'+sessionStorage.userId+'/propertyBoundaries/'+property.propertyUuid,property,$rootScope.config)
		.then(function(response) {
		    console.log('property service success');

		    differed.resolve(response);
		}).catch(function(response){

			differed.reject(response);
		});
	
	
return differed.promise;
};
//deleteProperty
this.deleteProperty=function(PropertyUUID){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	$http.delete($rootScope.URL+'/user/'+sessionStorage.userId+'/property/'+PropertyUUID,$rootScope.config)
.then(function(response) {
    console.log('property service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
return differed.promise;
};
// /////delete area deleteProperty
this.deleteArea=function(PropertyUUID,AreaUUID){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	$http.delete($rootScope.URL+'/user/property/'+PropertyUUID+'/area/'+AreaUUID,$rootScope.config)
.then(function(response) {
    console.log('property service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
return differed.promise;
};
// ////delete slot

this.deleteSlot=function(AreaUUID,slotUUID){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	$http.delete($rootScope.URL+'/user/'+AreaUUID+'/slot/'+slotUUID,$rootScope.config)
.then(function(response) {
    console.log('property service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
return differed.promise;
};




this.getLandownerReservation=function(){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	$http.get($rootScope.URL+'/user/'+sessionStorage.userId+'/reservation',$rootScope.config)
.then(function(response) {
    console.log('property service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
return differed.promise;
};

this.deleteLandownerReservation=function(reservationId){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	$http.delete($rootScope.URL+'/user/reservation/delete/'+reservationId,$rootScope.config)
.then(function(response) {
    console.log('property service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
return differed.promise;
};


this.getReservationStatusOfProperty=function(propertyId){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	$http.get($rootScope.URL+'/user/property/reservation/'+propertyId,$rootScope.config)
.then(function(response) {
    console.log('property service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
return differed.promise;
};

this.getReservationStatusOfArea=function(areaId){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	$http.get($rootScope.URL+'/user/area/reservation/'+areaId,$rootScope.config)
.then(function(response) {
    console.log('property service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
return differed.promise;
};

this.getReservationStatusOfSlot=function(slotId){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	$http.get($rootScope.URL+'/user/slot/reservation/'+slotId,$rootScope.config)
.then(function(response) {
    console.log('property service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
return differed.promise;
};
///////////api calls for coupons///////////
this.getCoupons=function(){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	$http.get($rootScope.URL+'/user/coupon',$rootScope.config)
.then(function(response) {
    console.log('coupon service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
return differed.promise;
};

this.addCoupon=function(coupon){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	
	
	$http.post($rootScope.URL+'/user/coupon',coupon,$rootScope.config)
.then(function(response) {
    console.log('coupon service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
return differed.promise;
};

this.updateCoupon=function(coupon){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	
		$http.put($rootScope.URL+'/user/coupon',coupon,$rootScope.config)
		.then(function(response) {
		    console.log('coupon service success');

		    differed.resolve(response);
		}).catch(function(response){

			differed.reject(response);
		});
	
	
return differed.promise;
};

this.deleteCoupon=function(id){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	$http.delete($rootScope.URL+'/user/coupon/'+id,$rootScope.config)
.then(function(response) {
    console.log('coupon service success');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
return differed.promise;
};

this.addMultipleSlots=function(slots, areaId){
	var differed=$q.defer();
	$rootScope.config.headers.Authorization=sessionStorage.Authorization;
	$http.post($rootScope.URL+'/user/area/'+areaId+'/slots',slots,$rootScope.config)
.then(function(response) {
    console.log('slots added successfully');

    differed.resolve(response);
}).catch(function(response){

	differed.reject(response);
});
return differed.promise;
};

});
	

	