var express = require('express'),
    event = require('events').EventEmitter;
    
    var routes = function(model) {

        //Agent Model Instance
        var agents = model.agent();


        var agentsRouter = express.Router(),
            EventEmitter = new event();


        agentsRouter.route('/')
            .get(function(req, res) {
                //Return all Agents
                agents.find(function (err, agents) {
                    if (err) return console.error(err);
                        res.status(200).json(agents);
                })
            });

        agentsRouter.route('/:id')
            .get(function(req, res) {
                //Return a specific agent
                agents.findOne({_id: req.params.id})
                    .then(function(agent) {
                        res.status(200).json({agent: agent});
                    })
                    .catch(function (err) {
                       res.status(400).send(err)
                    })
            });

        agentsRouter.route('/email/:email')
            .get(function(req, res) {
                var email = req.params.email;
                agents.findOne({email: req.email})
                    .then(function(agent) {
                        res.status(200).json({agent: agent});
                    })
                    .catch(function (err) {
                       res.status(400).send(err)
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
