<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html ng-app="app" class="h-100">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title ng-bind="title + ' | ' + siteTitle"></title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" href="/assets/css/style.css">

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
    integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous">
  </script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular-route.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular-animate.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular-aria.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous">
  </script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous">
  </script>

  <script src="app.module.js"></script>
  <script src="app.controller.js"></script>
  <script src="shared/setting/setting.service.js"></script>
  <script src="components/home/home.controller.js"></script>
  <script src="components/home/home.directive.js"></script>

  <script src="components/profile/profile.controller.js"></script>
  <script src="components/profile/profile.directive.js"></script>
  <script src="components/profile/edit-profile.controller.js"></script>
  <script src="components/profile/edit-profile.directive.js"></script>
  <script src="components/profile/profile.service.js"></script>

  <script src="components/credit/credit.controller.js"></script>
  <script src="components/credit/credit.directive.js"></script>
  <script src="components/credit/credit.service.js"></script>

  <script src="components/search/search.controller.js"></script>
  <script src="components/search/search.directive.js"></script>
  <script src="components/search/search.service.js"></script>

  <script src="components/category/category.controller.js"></script>
  <script src="components/category/category.directive.js"></script>
  <script src="components/category/category.service.js"></script>

  <script src="components/product/product.controller.js"></script>
  <script src="components/product/product.directive.js"></script>
  <script src="components/product/product.service.js"></script>

  <script src="components/cart/cart.controller.js"></script>
  <script src="components/cart/cart.directive.js"></script>
  <script src="components/cart/cart.service.js"></script>

  <script src="shared/auth/signin.controller.js"></script>
  <script src="shared/auth/signin.directive.js"></script>
  <script src="shared/auth/signin-modal.directive.js"></script>
  <script src="shared/auth/signup.controller.js"></script>
  <script src="shared/auth/signup.directive.js"></script>
  <script src="shared/auth/signup-modal.directive.js"></script>
  <script src="shared/auth/auth.service.js"></script>
  <script src="shared/navbar/navbar.controller.js"></script>
  <script src="shared/navbar/navbar.directive.js"></script>

  <script src="shared/pagination/pagination.controller.js"></script>
  <script src="shared/pagination/pagination.directive.js"></script>

  <script src="shared/category-list/category-list.controller.js"></script>
  <script src="shared/category-list/category-list.directive.js"></script>
  <script src="shared/category-list/category-list.service.js"></script>

  <script src="components/order/order.controller.js"></script>
  <script src="components/order/order.directive.js"></script>
  <script src="components/order/single-order.controller.js"></script>
  <script src="components/order/single-order.directive.js"></script>
  <script src="components/order/order.service.js"></script>
</head>

<security:authorize access="hasAnyAuthority('USER', 'ADMIN')">
  <security:authentication var="principal" property="principal" />

  <body ng-cloak ng-controller="AppCtrl"
    ng-init="init('${principal.username}', '${principal.email}', '${principal.role}')">
</security:authorize>
<security:authorize access="!hasAnyAuthority('USER', 'ADMIN')">
  <security:authentication var="principal" property="principal" />

  <body ng-cloak class="d-flex flex-column h-100">
</security:authorize>
<security:authorize access="hasAuthority('ADMIN')">
  <navbar site-title="{{siteTitle}}" is-admin="true" admin-portal="admin"></navbar>
</security:authorize>
<security:authorize access="!hasAuthority('ADMIN')">
  <navbar site-title="{{siteTitle}}"></navbar>
</security:authorize>
<div ng-view></div>
<footer class="footer mt-auto py-3 bg-light">
    <div class="container">
      <span class="text-muted">{{siteTitle}} 2019</span>
    </div>
  </footer>
</body>

</html>