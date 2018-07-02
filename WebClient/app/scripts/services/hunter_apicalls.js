'use strict';

/**
 * @ngdoc function
 * @name dayLeasingApp.controller:AboutCtrl
 * @description # AboutCtrl Controller of the dayLeasingApp
 */
console.log('services');
angular.module('dayLeasingApp').service('HunterServiceCalls', function ($q, $rootScope, $http) {

	this.getPropertiesForHunter = function (filter) {
		var differed = $q.defer();
		$rootScope.config.headers.Authorization = sessionStorage.Authorization;

		$http.get($rootScope.URL + '/hunter/' + filter + '/property', $rootScope.config)
			.then(function (response) {
				console.log('property service success');

				differed.resolve(response);
			}).catch(function (response) {

				differed.reject(response);
			});
		return differed.promise;
	};

	this.getSinglePropertyForHunter = function (propertyId) {
		var differed = $q.defer();
		$rootScope.config.headers.Authorization = sessionStorage.Authorization;
		$http.get($rootScope.URL + '/hunter/property/' + propertyId, $rootScope.config)
			.then(function (response) {
				console.log('property service success');

				differed.resolve(response);
			}).catch(function (response) {

				differed.reject(response);
			});
		return differed.promise;
	};



	this.getReservationForDate = function (selectedDate, propertyUuid) {
		var differed = $q.defer();
		$rootScope.config.headers.Authorization = sessionStorage.Authorization;
		$http.get($rootScope.URL + '/hunter/reservation/date/' + propertyUuid + '?date=' + selectedDate, $rootScope.config)
			.then(function (response) {
				console.log('reservation service success');

				differed.resolve(response);
			}).catch(function (response) {

				differed.reject(response);
			});
		return differed.promise;
	};

	this.getCouponCode = function (couponCode) {

		var differed = $q.defer();
		$rootScope.config.headers.Authorization = sessionStorage.Authorization;
		$http.get($rootScope.URL + '/hunter/' + sessionStorage.userId + '/coupon/' + couponCode, $rootScope.config)
			.then(function (response) {
				console.log('reservation service success');

				differed.resolve(response);
			}).catch(function (response) {

				differed.reject(response);
			});
		return differed.promise;
	};


	this.blockBooking = function (booking) {
		var differed = $q.defer();
		$rootScope.config.headers.Authorization = sessionStorage.Authorization;


		$http.post($rootScope.URL + '/hunter/reservation', booking, $rootScope.config)
			.then(function (response) {
				console.log('booking service success');

				differed.resolve(response);
			}).catch(function (response) {

				differed.reject(response);
			});

		return differed.promise;
	};



	this.confirmBooking = function (cart, cartId) {
		var differed = $q.defer();
		$rootScope.config.headers.Authorization = sessionStorage.Authorization;


		$http.post($rootScope.URL + '/hunter/' + sessionStorage.userId + '/reservation/cart/' + cartId, cart, $rootScope.config)
			.then(function (response) {
				console.log('booking service success');

				differed.resolve(response);
			}).catch(function (response) {

				differed.reject(response);
			});

		return differed.promise;
	};

	this.removeFromCart = function (reservationId) {
		var differed = $q.defer();
		$rootScope.config.headers.Authorization = sessionStorage.Authorization;


		$http.get($rootScope.URL + '/hunter/reservation/delete/' + reservationId, $rootScope.config)
			.then(function (response) {
				console.log('booking service success');

				differed.resolve(response);
			}).catch(function (response) {

				differed.reject(response);
			});

		return differed.promise;
	};

	this.getReservationBooking = function () {
		var differed = $q.defer();
		$rootScope.config.headers.Authorization = sessionStorage.Authorization;

		var data = {};
		$http.post($rootScope.URL + '/hunter/' + sessionStorage.userId + '/reservation', data, $rootScope.config)
			.then(function (response) {
				console.log('booking service success');

				differed.resolve(response);
			}).catch(function (response) {

				differed.reject(response);
			});

		return differed.promise;
	};

	this.getSlotForBooking = function (areaId, slotId) {
		var differed = $q.defer();
		$rootScope.config.headers.Authorization = sessionStorage.Authorization;


		$http.get($rootScope.URL + '/hunter/area/' + areaId + '/slot/' + slotId, $rootScope.config)
			.then(function (response) {
				console.log('booking service success');

				differed.resolve(response);
			}).catch(function (response) {

				differed.reject(response);
			});
		return differed.promise;
	};


});


