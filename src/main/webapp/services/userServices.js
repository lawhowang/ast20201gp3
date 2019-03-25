angular
    .module('app')
    .service('userService', ['$http', function ($http) {
        var factory = {};
        factory.signIn = function (usernameOrEmail, password) {
            var data = { usernameOrEmail: usernameOrEmail, password: password };
            return $http.post("/api/user/signin", data);
        }
        factory.signOut = function () {
            return $http.post("/api/user/signout");
        }
        factory.signUp = function (username, password, email) {
            var data = {
                username: username,
                password: password,
                email: email
            };
            return $http.post('/api/user/', data);
        }
        factory.test = function () {
            var data = {};
            return $http.post('/api/hello/', data);
        }
        return factory;
    }]);
