var express = require('express'),
    event = require('events').EventEmitter,
    People = require('../models/people'),
    Property = require('../models/property'),
    peopleService = require('../services/people_service');

var routes = function(){

    var propertyRouter = express.Router(),
        EventEmitter = new event();


    propertyRouter.route('/')
        .get(function(req, res){  
            //Return all people [limit this request]
            var page = req.query.page || 1;
            var limit = 100;
//            var offset = page == 0 ? limit : limit * page;
        
            Property.paginate({}, {limit: limit, page: page, sort:'-createdAt'})
            .then(function(result){
                result.properties = result.docs;
                delete result.docs;
                res.send(result);
            })
            .catch(function(err){
                res.status(500).send(err)
            })
        });   

    propertyRouter.route('/:id')
        .get(function(req, res){
           //Return a specific person
            Property.findOne({_id: req.params.id})
            .populate('owners')
            .then(function(property){
                res.json({property: property});
            })
            .catch(function(err){
                res.status(500).send(err)
            })
        });
    propertyRouter.route('/:id/people')
        .get(function(req, res){
           //Return a specific person
            Property.findOne({_id: req.params.id}, 'owners')
            .populate('owners')
            .then(function(propery){
                res.json({people: propery.owners});
            })
            .catch(function(err){
                res.status(500).send(err)
            })
        });

    propertyRouter.route('/region/:id')
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

    propertyRouter.route('/district/:id')
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

    propertyRouter.route('/:id')
        .put(function(req, res){
            People.findOneAndUpdate(
                { _id: req.params.id },
                { $set: {
                    
                }},
                { safe: true },
                function(err, doc){
                    if(err) return res.status(500).json({success: false, error: err});
                    return res.json({success: true, message: 'Update successful'});
                }
            )    
        
        });

    propertyRouter.route('/:id')
        .delete(function(req, res){
            People.remove({_id: req.params.id})
            .then(function(){
                res.json({success: true})
            })
            .catch(function(err){
                res.json({success: false, error: err});
            })
        });

    propertyRouter.route('/')
        .post(function(req, res){
            // validation
//            req.checkBody('firstname', 'firstname field is required').notEmpty();
//            req.checkBody('surname', 'surname field is required').notEmpty();

//            var errors = req.validationErrors();
//            if (errors) return res.status(422).json({success: false, errors: errors});
            
        
            peopleService.saveOwners(req.body.owners)
            .then(function(resp){
                var newProp = new Property;
                newProp.pins = req.body.pins;
                newProp.propertyType = req.body.propertyType;
                newProp.classification = req.body.classification;
                newProp.ownershipType = {
                    status: req.body.ownershipType,
                    registered: req.body.registered,
                    titleNumber: req.body.titleNumber,
                    indentureNumber: req.body.indentureNumber
                }
                newProp.location = {
                    gps     : { lat: req.body.gpsLat, lng: req.body.gpsLng },
                    region  : req.body.region,
                    district: req.body.district
                }
                newProp.tin = owner.tin;
                newProp.address = req.body.address;
                newProp.familyUnits = req.body.familyUnits;
                newProp.electricitySource = req.body.electricitySource;
                newProp.partnershipType = req.body.partnershipType;
                newProp.emergency = {
                    name    : req.body.emergencyName,
                    address : req.body.emergencyAddress,
                    city    : req.body.emergencyCity,
                    phone   : req.body.emergencyPhone,
                    email   : req.body.emergencyEmail
                };
                newProp.authorizedAgent = {
                    name 	: req.body.agentName,
                    address	: req.body.agentAddress,
                    city	: req.body.agentCity,
                    phones	: req.body.agentPhone,
                    email	: req.body.agentEmail
                };
                newProp.propertyManager = {
                    name    : req.body.managerName,
                    address : req.body.managerAddress,
                    city    : req.body.managerCity,
                    phone   : req.body.managerPhone,
                    email   : req.body.managerEmail

                };
                newProp.trustBeneficiaries = {
                    name    : req.body.beneficiaryName,
                    address : req.body.beneficiaryAddress,
                    city    : req.body.beneficiaryCity,
                    phone   : req.body.beneficiaryPhone,
                    email   : req.body.beneficiaryEmail
                };
                newProp.owners = resp.owners;

                newProp.save(function(err){
                    return res.json({status: true, message: 'Property registered'});
                })
            })
        });
    
    return {router: propertyRouter, event: EventEmitter};
};

module.exports = routes;