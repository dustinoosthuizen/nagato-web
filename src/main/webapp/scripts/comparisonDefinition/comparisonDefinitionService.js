'use strict';

nagatoApp.factory('ComparisonDefinition', function ($resource) {
        return $resource('app/rest/comparisonDefinition/:action/:id', {}, {
            'findAll': { method: 'GET', isArray: true,params:{action:'findAll'}},
            'findById': { method: 'GET',params:{action:'findById'}},
            'findByName': { method: 'GET',params:{action:'findByName'}},
            'doesNameExist': { method: 'GET',params:{action:'doesNameExist'}},
            'create':   {method:'POST',params:{action:'create'}},
            'edit':   {method:'POST',params:{action:'edit'}},
            'deleteById':   {method:'DELETE',params:{action:'deleteById'}}
        });
    });
