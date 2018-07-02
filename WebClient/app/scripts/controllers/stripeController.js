'use strict';
angular.module('dayLeasingApp')
    .controller('stripeController', function ($q, $state, $http, $rootScope,$scope, serviceCalls, $location) {
        var handler = StripeCheckout.configure({
            //public key of the registered user on Stripe
            key: 'pk_test_JalvcPvMh2CFQ43xq1Xuj3HZ',
            //Can be replaced with dayleasing image.
            image: 'https://stripe.com/img/documentation/checkout/marketplace.png',
            locale: 'auto',

            token: function (token) {
                // You can access the token ID with `token.id`.
                // Get the token ID to your server-side code for use.
                console.log(token);
                if (token) {
                    var differed = $q.defer();

                    $http.post($rootScope.URL + '/check', token)
                        .then(function (response) {
                            console.log('token service success');
                            differed.resolve(response);
                        }).catch(function (response) {

                            differed.reject(response);
                        });
                    return differed.promise;


                } else {
                    console.log("error in getting token");
                }
            }

        });
        $scope.enterDetails = function () {
            console.log("hai");
            // User interface can be manipulated by passing some parameters to the enterdetails function.
           


        }



    });
