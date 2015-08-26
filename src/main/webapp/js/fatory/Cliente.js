angular.module('app.factory').factory('Cliente', function(HOSTNAME, $resource) {
    return $resource(HOSTNAME + 'cliente');
});