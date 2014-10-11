'use strict';

nagatoApp.factory('Datasource', function ($resource) {
        return $resource('app/rest/datasource/:action/:id', {}, {
            'findAll': { method: 'GET', isArray: true,params:{action:'findAll'}},
            'findById': { method: 'GET',params:{action:'findById'}},
            'deleteById': { method: 'DELETE',params:{action:'deleteById'}},
            'edit': { method: 'GET',params:{action:'edit'}},
            'create':   {method:'POST',params:{action:'create'}}
        });
    });
