var express = require('express'),
    event = require('events').EventEmitter
	Region = require('../models/region')
	districtService = require('../services/district_service');
	flatten = require('../services/helper_service').flatten;

var routes = function(){
    var districtsRouter = express.Router(),
        EventEmitter = new event();


    districtsRouter.route('/')
        .get(function(req, res){  
          //Return all districts
            Region.aggregate([
                {'$unwind': '$districts'},
                {'$group': {
                    _id: {_id: '$districts._id', name: '$districts.name'}
                }}
            ], function(err, docs){
                if(err) return res.send(err)
                var districts = [];
                for(var doc of docs){
                    districts.push(doc._id);
                }
                res.send(districts)
            })
            
        });   

    districtsRouter.route('/:id')
        .get(function(req, res){
            //Return a specific district
            Region.findOne({'districts._id': req.params.id}, 'districts.$')
            .then(function(doc){
                var district = doc ? doc.districts[0]: null;
                res.json({district: district});
            })
        }); 

    districtsRouter.route('/region/:id')
        .get(function(req, res){
            var regionId = req.params.id;
            //Return all district in a region
            Region.findOne({_id: req.params.id}, 'districts')
            .then(function(doc){
                res.json({districts: doc.districts}); 
            })
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
    districtsRouter.route('/load')
        .post(function(req, res){
            districtService.loadDistricts()
            .then(function(data){
                res.send(send)
            })
            .catch(function(err){
                res.send(err)
            })

        });

    return {router: districtsRouter, event: EventEmitter};
};

module.exports = routes;