var express = require('express'),
    event = require('events').EventEmitter,
    User = require('../models/user');

    var routes = function() {
        var agentsRouter = express.Router(),
            EventEmitter = new event();


        agentsRouter.route('/')
            .get(function(req, res) {
                //Return all Agents
                User.find({ type: 'agent' })
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
                User.findOne({_id: req.params.id})
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
                User.findOne({email: req.email})
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


            });

        //delete agent
        agentsRouter.route('/:id')
            .delete(function(req, res) {


            });

        //save agent
        agentsRouter.route('/')
            .post(function(req, res) {


            });

        return { router: agentsRouter, event: EventEmitter };
    };

module.exports = routes;
