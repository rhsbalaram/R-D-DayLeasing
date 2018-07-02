'use strict';

describe('Controller: LandownerDashboardCtrl', function () {

  // load the controller's module
  beforeEach(module('dayLeasingApp'));

  var LandownerDashboardCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    LandownerDashboardCtrl = $controller('LandownerDashboardCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(LandownerDashboardCtrl.awesomeThings.length).toBe(3);
  });
});
