var express = require('express'),
    event = require('events').EventEmitter;

var routes = function(model){

    //User Model Instance
    var users = model.user();


    var usersRouter = express.Router(),
        EventEmitter = new event();


    usersRouter.route('/')
        .get(function(req, res){  
          //Return all users
           users.find(function (err, users) {
              if (err) return console.error(err);
                res.status(200).json(users);
            })
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