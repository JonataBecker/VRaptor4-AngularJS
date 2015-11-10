angular.module('app.factory').factory('Datatable', function(HOSTNAME, $resource, $q) {
    // Retorna hostname
    var getHostName = function () {
        return HOSTNAME + 'datatable/:entity/:info';
    };
    return {
        getTitle: function (entity) {
            var deferred = $q.defer();
            var titles = $resource(getHostName(), 
                {entity:entity, info:'title'}
            );
            titles.get(function (data) {
                deferred.resolve(data);
            });
            return deferred.promise;
        },
        getData: function (entity) {
            var deferred = $q.defer();
            var titles = $resource(getHostName(), 
                {entity:entity, info:'data'},
                {'query': { method:'GET', cache: false, isArray:true }}
            );
            titles.query(function (data) {                
                deferred.resolve(data);
            });
            return deferred.promise;
        }        
    };
});