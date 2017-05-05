var express = require('express'),
    event = require('events').EventEmitter,
    People = require('../models/people'),
    Property = require('../models/property');

var routes = function(model){

    var peoplesRouter = express.Router(),
        EventEmitter = new event();


    peoplesRouter.route('/')
        .get(function(req, res){  
            //Return all people [limit this request]
            var page = req.query.page || 1;
            var limit = 20;
            var offset = page == 0 ? limit : limit * page;
        
            People.find({})
            .limit(limit)
            .skip(offset)
            .sort('-createdAt')
            .then(function(person){
                res.json({people: people});
            })
            .catch(function(err){
                res.status(500).send(err)
            })
        });   

    peoplesRouter.route('/:id')
        .get(function(req, res){
           //Return a specific person
            People.findOne({_id: req.params.id})
            .then(function(person){
                res.json({person: person});
            })
            .catch(function(err){
                res.status(500).send(err)
            })
        }); 

    peoplesRouter.route('/region/:id')
        .get(function(req, res){
            var regionId = req.params.id;
            //Return all people in a region
            Property.find({'location.region': regionId}, 'owners')
            .populate('owners')
            .then(function(properties){
                var people = [];
                for(var prop of properties){
                    people.concat(prop.owners)
                }
                res.json({people: people});
            })
            .catch(function(err){
                res.status(500).send(err)
            })
        }); 

    peoplesRouter.route('/district/:id')
        .get(function(req, res){
            var districtId = req.params.id;
            //Return all people in a districts
            Property.find({'location.district': districtId}, 'owners')
            .populate('owners')
            .then(function(properties){
                var people = [];
                for(var prop of properties){
                    people.concat(prop.owners)
                }
                res.json({people: people});
            })
            .catch(function(err){
                res.status(500).send(err)
            })
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