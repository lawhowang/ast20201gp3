(function () {
    'use strict';

    angular
        .module('app')
        .controller('ProfileCtrl', ProfileCtrl);

    ProfileCtrl.$inject = ['profileService', '$routeParams', '$location'];

    function ProfileCtrl(profileService, $routeParams, $location) {
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

        vm.editProfile = function () {
            $location.path('/profile/edit');
        };
    }
})();