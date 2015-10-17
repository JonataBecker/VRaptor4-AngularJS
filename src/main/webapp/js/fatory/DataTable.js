angular.module('app.factory').factory('Datatable', function(HOSTNAME, $resource, $q) {
    // Busca informações referente ao datatabel
    var getInfo = function (entity, info) {
        var deferred = $q.defer();
        var titles = $resource(HOSTNAME + 'datatable/:entity/:info', 
            {entity:entity, info:info},
            {'query': { method:'GET', cache: false, isArray:true }}
        );
        titles.query(function (data) {
            deferred.resolve(data);
        });
        return deferred.promise;
    };
    return {
        getTitle: function (entity) {
            return getInfo(entity, 'title');
        },
        getData: function (entity) {
            return getInfo(entity, 'data');
        }        
    };
});