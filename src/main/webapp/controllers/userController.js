(function () {
    'use strict';

    angular
        .module('app')
        .controller('userCtrl', UserController);

    UserController.$inject = ['UserService'];
    function UserController(UserService) {
        
    }

})();