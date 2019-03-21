angular
    .module('app')
    .service('userService', ['$http', function ($http) {
        var factory = {};
        factory.login = function (usernameOrEmail, password) {
            var data;
            var re = /\S+@\S+\.\S+/;
            if (re.test(usernameOrEmail))
                data = { email: usernameOrEmail, password: password };
            else
                data = { username: usernameOrEmail, password: password };
            return $http.post('/api/user/login', data);
        }
        return factory;
    }]);
