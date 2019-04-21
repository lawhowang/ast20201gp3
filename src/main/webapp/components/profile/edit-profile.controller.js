(function () {
    'use strict';

    angular
        .module('app')
        .controller('EditProfileCtrl', EditProfileCtrl);

    EditProfileCtrl.$inject = ['profileService', '$routeParams', '$location', '$window'];

    function EditProfileCtrl(profileService, $routeParams, $location, $window) {
        var vm = this;
        vm.getProfile = function () {
            profileService.getProfile().then(
                function successCallback(response) {
                    console.log(response);
                    vm.profile = response.data;
                },
                function errorCallback(response) {
                    console.log(response);
                });
        };
        vm.getProfile();

        vm.back = function () {
            $window.history.back();
        };

        vm.submit = function () {
            profileService.updateProfile(vm.profile).then(
                function successCallback(response) {
                    console.log(response);
                    vm.success = true;
                    delete vm.errors;
                    vm.profile = response.data;
                },
                function errorCallback(response) {
                    vm.errors = response.data.errors;
                    delete vm.success;
                    console.log(response);
                });
        };
    }
})();