var express = require('express'),
    event = require('events').EventEmitter,
    Agent = require('../models/agent');

    var routes = function() {
        var agentsRouter = express.Router(),
            EventEmitter = new event();


        agentsRouter.route('/')
            .get(function(req, res) {
                //Return all Agents
                Agent.find({})
                    .then(function(agents) {
                        res.json({agents: agents});
                    })
                    .catch(function (err) {
                        res.send(err)
                    })
            });

        agentsRouter.route('/:id')
            .get(function(req, res) {
                //Return a specific agent
                Agent.findOne({_id: req.params.id})
                    .then(function(agent) {
                        res.json({agent: agent});
                    })
                    .catch(function (err) {
                       res.send(err)
                    })
            });

        agentsRouter.route('/email/:email')
            .get(function(req, res) {
                var email = req.params.email;
                Agent.findOne({email: email})
                    .then(function(agent) {
                        res.json({agent: agent});
                    })
                    .catch(function (err) {
                       res.send(err)
                    })
            });

        //update agent
        agentsRouter.route('/:id')
            .put(function(req, res) {
                Agent.findOneAndUpdate(
                    {_id: req.params.id},
                    { },
                    {safe: true}
                
                , function(err, agent){
                    if(!err) return res.json({success: true, message: 'Update successful'});
                })

            });

        //delete agent
        agentsRouter.route('/:id')
            .delete(function(req, res) {
                Agent.remove({_id: req.params.id})
                .then(function(){
                    res.json({success: true, message: 'Agent removed successfully'});
                })

            });

        //save agent
        agentsRouter.route('/')
            .post(function(req, res) {
                // validation
                req.checkBody('firstname', 'firstname field is required').notEmpty();
                req.checkBody('surname', 'surname field is required').notEmpty();
                req.checkBody('phone', 'phone field is required').notEmpty();
                req.checkBody('district', 'district field is required').notEmpty();
                req.checkBody('password', 'password field is required').notEmpty();

                var errors = req.validationErrors();
                if (errors) return res.status(422).json({success: false, errors: errors});

                //check if phone or email exist
                Agent.findOne({$or: [{phone: req.body.phone},{email: req.body.email}]} )
                .then(function(agent){
                    if(agent) return res.json({success: false, message: 'registration failed. Agent already registered'});

                    var newAgent = new Agent;
                    newAgent.firstname = req.body.firstname;
                    newAgent.surname = req.body.surname;
                    newAgent.othernames = req.body.othernames;
                    newAgent.phone = req.body.phone;
                    newAgent.email = req.body.email;
                    newAgent.password = newAgent.generateHash(req.body.password);
                    newAgent.district = req.body.district;
                    

                    newAgent.save(function(err){
                        if(err) return res.json({success: false, message: 'Error registering. try again'});
                        return res.json({success: true, message: 'registration successful', agent: newAgent})
                    });
                });
            });

        return { router: agentsRouter, event: EventEmitter };
    };

module.exports = routes;
