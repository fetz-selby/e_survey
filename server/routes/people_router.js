var express = require('express'),
    event = require('events').EventEmitter;

var routes = function(model){


    //People Model Instance
    var people = model.people;

    var peoplesRouter = express.Router(),
        EventEmitter = new event();


    peoplesRouter.route('/')
                .get(function(req, res){  
                  //Return all people [limit this request]

                });   

    peoplesRouter.route('/:id')
                .get(function(req, res){
                   //Return a specific person
                             
                }); 

    peoplesRouter.route('/region/:id')
                .get(function(req, res){
                    var region_id = req.params.id;
                    //Return all people in a region
                             
                }); 

    peoplesRouter.route('/district/:id')
                .get(function(req, res){
                    var region_id = req.params.id;
                    //Return all people in a districts
                             
                }); 

    peoplesRouter.route('/:id')
                .put(function(req, res){
                    
                             
                });

    peoplesRouter.route('/:id')
                .delete(function(req, res){
                    
                             
                });

    peoplesRouter.route('/')
                .post(function(req, res){
                    
                             
                });
    
    return {router: peoplesRouter, event: EventEmitter};
};

module.exports = routes;