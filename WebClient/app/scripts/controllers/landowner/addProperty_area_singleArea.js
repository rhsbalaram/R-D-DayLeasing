
/**
 * @ngdoc function
 * @name dayLeasingApp.controller:LandownerAddPropertyCtrl_Area_SingleArea
 * @description
 * # LandownerAddPropertyCtrl_Area_SingleArea
 * Controller of the dayLeasingApp
 */
angular.module('dayLeasingApp').controller('LandownerAddPropertyCtrl_Area_SingleArea', function ($filter,$timeout,$state, $scope,serviceCalls) {

	  $scope.area_submitted=false;
  var today=new Date().toJSON().split("T")[0];
  
  function getCurrentDate(){
    // console.log("inside date field");
    // // var date={};
    $scope.date={};
    // var dateformat=new Date();
    // var dateString="";
    //
    // if(dateformat.getMonth()<10){
    //   dateString+='0'+dateformat.getMonth();
    // }
    // dateString+="/"+(dateformat.getDate()+1);
    // dateString+="/"+dateformat.getFullYear();
    $scope.date.fromDate=new Date();
    $scope.date.toDate='';
    return $scope.date;
  }
//add date input fields
  $scope.addDateField=function(){
	  var date={};
 	 date.id='';
	  $scope.area.dates.push(date);
  };
  //remove date input fields
	  $scope.removeDateField=function(index){
		  if(index!=0)
		  $scope.area.dates.splice(Number(index), 1);
	  };
	  //if it is contains area
	  if(sessionStorage.areaToRemove)
		  {
		  $scope.area=  angular.copy($scope.property.areas[Number(sessionStorage.areaToRemove)]);
		  if($scope.area.dates.length<1){
			  $scope.addDateField();
		  }



		  }
	  //else move to previous page
	  else{
		  $state.go("landownerAddProperty.area.allAreas");
	  }

//adding  area
	  $scope.addSlotToArea=function(prop) {

      ///////////////////////////
      $scope.area_submitted = true;
     // validations
      if (prop.$valid&&checkValidNumbers()&&$scope.checkValidDates()) {
    	  //to check unique area name

      var checkUniqueAreaName = [];
      angular.forEach($scope.property.areas, function (area) {
        if (area.propertyAreaName)
          checkUniqueAreaName.push(area.propertyAreaName);
      });
      if (sessionStorage.toRemoveArea == "false")
        checkUniqueAreaName.splice([Number(sessionStorage.areaToRemove)]);

      if (checkUniqueAreaName.indexOf($scope.area.propertyAreaName.trim()) == -1) {

        serviceCalls.addArea($scope.area, $scope.property.propertyUuid)
          .then(function (response) {

            $scope.area.propertyAreaUuid = response.data.area.propertyAreaUuid;
            $scope.area.dates = response.data.area.dates;
            $scope.property.areas[Number(sessionStorage.areaToRemove)] = $scope.area;

            sessionStorage.property = JSON.stringify($scope.property);
            $scope.changeParentScope();
            sessionStorage.toRemoveArea = "false";
            sessionStorage.removeItem('areaToRemove');

            $state.go("landownerAddProperty.area.allAreas");
    	    //set focus to this area id of page
  	  	  $timeout(function(){
  				 document.getElementById($scope.area.propertyAreaUuid).focus();
  				 },200);
          })
          .catch(function (err) {
            alert('failed');
          });
      }
      else {
        alert('Area Name should be unique');
      }
      //////////
      //  sessionStorage.toRemoveArea="false";

      //	$scope.addSlot($scope.area,Number(sessionStorage.areaToRemove));
      //	sessionStorage.removeItem('areaToRemove');
    }
	  };
	  
	  

	  
	  
	  function checkValidNumbers(){
		  $scope.isValidNumber=true;
		  angular.forEach($scope.area.dates,function(date,innerkey){
			  if(parseFloat(date.cost)<=0||parseFloat(date.cost)% 1 != 0||parseFloat(date.cost)>=1000){
					  $scope.isValidNumber=false;
		    		 
		    	  }
				 
				
			});
		  return $scope.isValidNumber;
	  }
	  
	  $scope.checkValidDates=function(){
		  
		  $scope.validDates=true;
		  angular.forEach($scope.area.dates,function(date,key){
			  var fromDate=moment(date.fromDate,'MM/DD/YYYY');
			  var toDate=moment(date.toDate,'MM/DD/YYYY');
			angular.forEach($scope.area.dates,function(innerDate,innerkey){
				if(innerkey!=key){
					 var innerfromDate=moment(innerDate.fromDate,'MM/DD/YYYY');
					  var innertoDate=moment(innerDate.toDate,'MM/DD/YYYY');
					
					 if(innerfromDate.isBetween(fromDate,toDate,'day','[]')||innertoDate.isBetween(fromDate,toDate,'day','[]')){
						$scope.validDates=false;
						
					 }
				}
				
			});
			  
			
	        	 
		  });
		  
		  return $scope.validDates;
	  }
	  
	  


  });
