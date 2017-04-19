var express = require('express'),
    event = require('events').EventEmitter;

var routes = function(sql){
    var agentsRouter = express.Router(),
        EventEmitter = new event();


    agentsRouter.route('/')
                .get(function(req, res){  
                  //Return all Agents

                });   

    agentsRouter.route('/:id')
                .get(function(req, res){
                   //Return a specific agent
                             
                }); 

    agentsRouter.route('/email/:email')
                .get(function(req, res){
                    var email = req.params.email;
                             
                }); 

    agentsRouter.route('/:id')
                .put(function(req, res){
                    
                             
                });

    agentsRouter.route('/:id')
                .delete(function(req, res){
                    
                             
                });

    agentsRouter.route('/')
                .post(function(req, res){
                    
                             
                });
    
    return {router: agentsRouter, event: EventEmitter};
};

module.exports = routes;