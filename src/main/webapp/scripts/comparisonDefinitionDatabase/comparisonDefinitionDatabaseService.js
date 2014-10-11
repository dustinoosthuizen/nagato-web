'use strict';

nagatoApp.factory('ComparisonDefinitionDatabase', function ($resource) {
        return $resource('app/rest/comparisonDefinitionDatabase/:action/:id', {}, {
            'query': { method: 'GET', isArray: true,params:{action:'findAll'}},
            'get': { method: 'GET',params:{action:'findById'}},
            'findByComparisonDefinitionId': { method: 'GET',params:{action:'findByComparisonDefinitionId'}},
            'create':   {method:'POST',params:{action:'create'}},
            'edit':   {method:'POST',params:{action:'edit'}},
            'deleteById':   {method:'DELETE',params:{action:'deleteById'}},
            'compileDatabaseQuery':   {method:'POST',params:{action:'compileDatabaseQuery'}}
        });
    });
