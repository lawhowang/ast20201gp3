<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html ng-app="app">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>EShop</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" href="/assets/css/style.css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
    integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular-route.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular-animate.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular-aria.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
    crossorigin="anonymous"></script>

  <script src="app.module.js"></script>
  <script src="components/home/home.controller.js"></script>
  <script src="components/home/home.directive.js"></script>
  <script src="shared/auth/auth.controller.js"></script>
  <script src="shared/auth/auth.directive.js"></script>
  <script src="shared/auth/auth.service.js"></script>
  <script src="shared/signup/signup.controller.js"></script>
  <script src="shared/signup/signup.directive.js"></script>
  <script src="shared/signup/signup.service.js"></script>
  <script src="shared/navbar/navbar.controller.js"></script>
  <script src="shared/navbar/navbar.directive.js"></script>
</head>

<body ng-cloak>
  <div ng-view></div>
</body>
<security:authorize access="hasAuthority('ADMIN')">
  <script>
    var t = setTimeout(function () {
      if (!$('.admin-panel').length) {
        $('<a class="dropdown-item admin-panel" href="/admin">Admin Panel</a>').insertBefore('.navbar-user>.dropdown>.dropdown-menu>.dropdown-divider');
      } else {
        clearTimeout(t);
      }
    }, 500);
  </script>
</security:authorize>

</html>