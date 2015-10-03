angular.module('app.service', []);
angular.module('app.factory', []);
angular.module('app.controllers', []);
angular.module('app.directive', []);
angular.module('app', ['app.service', 'app.factory', 'app.controllers', 'app.directive', 'ui.router', 'ngResource'])
        .constant('HOSTNAME', 'http://localhost:8080/VRaptor4-AngularJS/')
        .config(function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise('/');
            $stateProvider
                    .state('login', {
                        url: '/',
                        templateUrl: 'template/login.html',
                        controller: 'LoginController'
                    })
                    .state('app', {
                        url: '/app',
                        abstract: true,
                        templateUrl: 'template/app.html',
                        controller: 'AppController'
                    })
                    .state('app.home', {
                        url: "/home",
                        views: {
                            'container': {
                                templateUrl: "template/home.html",
                                controller: 'HomeController'
                            }
                        }
                    })
                    .state('app.cliente', {
                        url: "/cliente",
                        views: {
                            'container': {
                                templateUrl: "template/cliente/grid.html",
                                controller: function ($scope, Cliente) {
                                    $scope.itens = {};
                                    Cliente.query({}, function (data) {
                                        $scope.itens = data;
                                    });
                                }
                            }
                        }
                    })
                    .state('app.clienteform', {
                        url: "/cliente/{action:incluir|alterar|consultar}",
                        views: {
                            'container': {
                                templateUrl: "template/cliente/form.html",
                                controller: 'ClienteController'
                            }
                        }
                    });
        });

