angular
    .module('app')
    .controller('userListCtrl', ['$scope', '$location', function ($scope, $location) {
        $scope.searchByUsername = function (search) {
            var username = search ? search.username : "";
            $location.path("/users/user_list/search/" + username + "/1");
        }
        $scope.cancelSearch = function () {
            $location.path("/users/user_list");
        }
    }]);
angular
    .module('app').config(function ($routeProvider) {
        $routeProvider
            .when("/users/user_list/:page?", {
                controller: "userListCtrl",
                templateUrl: function (params) {
                    var url = "/admin/users/user_list?";
                    if (params.page)
                        url += "page=" + params.page;
                    return url;
                }
            }).when("/users/user_list/search/:q?/:page?", {
                controller: "userListCtrl",
                templateUrl: function (params) {
                    var q = params.q ? params.q : "";
                    var page = params.page ? params.page : "1";
                    var url = "/admin/users/user_list/search?q=" + q + "&page=" + page;
                    console.log(url);
                    return url;
                }
            });
    });