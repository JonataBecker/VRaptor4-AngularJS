angular.module('app.controllers', []);
angular.module('app', ['app.controllers', 'ui.router'])
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
                            'details': {
                                templateUrl: "template/home.html",
                                controller: 'HomeController'
                            }
                        }
                    })
                    .state('app.cliente', {
                        url: "/cliente",
                        views: {
                            'details': {
                                templateUrl: "template/cliente/grid.html",
                                controller: 'ClienteController'
                            }
                        }
                    });
        });


//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        topOffset = 50;
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    var element = $('ul.nav a').filter(function() {
        return this.href == url || url.href.indexOf(this.href) == 0;
    }).addClass('active').parent().parent().addClass('in').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
});


angular.module('app.controllers').controller('AppController', function ($scope) {
    $scope.$on('$viewContentLoaded', function (event) {
        $('#side-menu').metisMenu();
    });
});
angular.module('app.controllers').controller('ClienteController', function ($scope) {

});
angular.module('app.controllers').controller('HomeController', function ($scope) {




});
angular.module('app.controllers').controller('LoginController', function ($scope) {

});