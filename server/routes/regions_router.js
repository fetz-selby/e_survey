var express = require('express'),
    event = require('events').EventEmitter;

var routes = function(sql){
    var regionsRouter = express.Router(),
        EventEmitter = new event();


    regionsRouter.route('/')
                .get(function(req, res){  
                  //Return all regions

                });   

    regionsRouter.route('/:id')
                .get(function(req, res){
                   //Return a specific region
                             
                });  

    regionsRouter.route('/')
                .post(function(req, res){
                             
                }); 

    regionsRouter.route('/:id')
                .delete(function(req, res){
                             
                }); 

    
    return {router: regionsRouter, event: EventEmitter};
};

module.exports = routes;