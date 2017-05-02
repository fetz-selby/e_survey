var express = require('express'),
	User 	= require('../models/user');

var routes = function(){
    var authRouter = express.Router();

    /*
	 * Login
	 *
	 *
    */
    authRouter.route('/login')
	    .post(function(req, res){
	    	var email = req.body.email,
				password = req.body.password;

			req.checkBody('email', 'email field is required').notEmpty();
			req.checkBody('password', 'password field is required').notEmpty();

		    var errors = req.validationErrors();
			if (errors) return res.status(422).json({success: false, errors: errors});



			User.findOne({$or: [{phone: email},{email: email}]} )
			.then(function(user){
		        // if no user is found, return the message
		        if (!user)
		            return res.status(422).json({success: false, message:'Invalid username/password.'});

		        // if the user is found but the password is wrong
		        if (!user.validPassword(password))
		            return res.status(422).json({success: false, message:'Invalid username/password.'});


		        // login successful
		        user = user.toObject();
		        delete user.password;
		        return res.json({ success: true, message:'Login successful', user: user });
		    });

	    });


	authRouter.route('/signup')
        .post(function(req, res){
        	
        });

    return { router: authRouter };
};
module.exports = routes;