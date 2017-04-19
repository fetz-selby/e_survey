var express = require('express'),
    event = require('events').EventEmitter;

var routes = function(sql){
    var usersRouter = express.Router(),
        EventEmitter = new event();


    usersRouter.route('/')
                .get(function(req, res){  
                  //Return all users

                });   

    usersRouter.route('/:id')
                .get(function(req, res){
                   //Return a specific user
                             
                }); 

    usersRouter.route('/email/:email')
                .get(function(req, res){
                    var region_id = req.params.email;
                    //Return user
                             
                }); 

    usersRouter.route('/msisdn/:msisdn')
                .get(function(req, res){
                    var region_id = req.params.msisdn;
                    //Return user 
                             
                }); 

    usersRouter.route('/:id')
                .put(function(req, res){
                    
                             
                });

    usersRouter.route('/')
                .post(function(req, res){
                    
                             
                });
    
    return {router: usersRouter, event: EventEmitter};
};

module.exports = routes;