var express = require('express'),
    event = require('events').EventEmitter;

var routes = function(sql){
    var districtsRouter = express.Router(),
        EventEmitter = new event();


    districtsRouter.route('/')
                .get(function(req, res){  
                  //Return all districts

                });   

    districtsRouter.route('/:id')
                .get(function(req, res){
                   //Return a specific district
                             
                }); 

    districtsRouter.route('/region/:id')
                .get(function(req, res){
                    var region_id = req.params.id;
                    //Return all district in a region
                             
                }); 

    districtsRouter.route('/:id')
                .put(function(req, res){
                    
                             
                });

    districtsRouter.route('/:id')
                .delete(function(req, res){
                    
                             
                });

    districtsRouter.route('/')
                .post(function(req, res){
                    
                             
                });
    
    return {router: districtsRouter, event: EventEmitter};
};

module.exports = routes;