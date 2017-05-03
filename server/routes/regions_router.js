var express = require('express'),
    event = require('events').EventEmitter;

var routes = function(){
    var regionsRouter = express.Router(),
        EventEmitter = new event();


    regionsRouter.route('/')

        .get(function(req, res){  
          //Return all regions
            Region.find({}, 'name')
            .then(function(regions){
                res.json({regions: regions});
            })
        });   

              


    regionsRouter.route('/:id')
        .get(function(req, res){
           //Return a specific region
            Region.findOne({'districts._id': req.params.id}, 'districts.$')
            .then(function(doc){
                var district = doc ? doc.districts[0]: null;
                res.json({district: district});
            })
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