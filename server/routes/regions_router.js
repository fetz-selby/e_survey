var express = require('express'),
    event = require('events').EventEmitter;

var routes = function(model){

    //Region Model Instance
    var regions = model.region();

    var regionsRouter = express.Router(),
        EventEmitter = new event();


    regionsRouter.route('/')
                .get(function(req, res){  
                  //Return all regions
                   regions.find(function (err, regions) {
                      if (err) return console.error(err);
                        res.status(200).json(regions);
                    })

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