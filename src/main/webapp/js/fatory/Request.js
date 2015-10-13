/**
 * Objeto responsavel por funçoes de controle de requisiçoes
 */
angular.module('app.factory').factory('Request', function() {
    var request = {};
    request.ACTION_ALTERAR = "alterar";
    request.ACTION_CONSULTAR = "consultar";
    request.ACTION_INCLUIR = "incluir";
    
    /**
     * Retorna veradeiro se açao for alterar
     * 
     * @param {String} action
     * @returns {Boolean}
     */
    request.isAlterar = function (action) {
        return action === request.ACTION_ALTERAR;
    };

    /**
     * Retorna veradeiro se açao for incluir
     * 
     * @param {String} action
     * @returns {Boolean}
     */
    request.isIncluir = function (action) {
        return action === request.ACTION_INCLUIR;
    };

    /**
     * Retorna veradeiro se açao for consultar
     * 
     * @param {String} action
     * @returns {Boolean}
     */
    request.isConsultar = function (action) {
        return action === request.ACTION_CONSULTAR;
    };
    
    /**
     * Retorna veradeiro se for açao de alteraçao/consulta
     * 
     * @param {String} action
     * @returns {Boolean}
     */
    request.isAlterarConsultar = function (action) {
        return request.isAlterar(action) || request.isConsultar(action);
    };        
    
    return request;
});