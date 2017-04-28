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
        	// validation
        	req.checkBody('firstname', 'firstname field is required').notEmpty();
        	req.checkBody('surname', 'surname field is required').notEmpty();
        	req.checkBody('email', 'email field is required').notEmpty();
			req.checkBody('phone', 'phone field is required').notEmpty();
			req.checkBody('password', 'password field is required').notEmpty();
			req.checkBody('district', 'district field is required').notEmpty();

		    var errors = req.validationErrors();
			if (errors) return res.status(422).json({success: false, errors: errors});
            
			//check if phone or email exist
            User.findOne({$or: [{phone: req.body.phone},{email: req.body.email}]} )
            .then(function(user){
            	if(user) return res.json({success: false, message: 'signup failed. User already registered'});

            	var newUser = new User;
	            newUser.firstname = req.body.firstname;
	            newUser.lastname = req.body.lastname;
	            newUser.othernames = req.body.othernames;
	            newUser.email = req.body.email;
	            newUser.phone = req.body.phone;
	            newUser.district = req.body.district;
	            newUser.password = newUser.generateHash(req.body.password);
	            newUser.role = 'agent';
	            
	            newUser.save(function(err){
	            	if(err) return res.json({success: false, message: 'Error signing up. try again'});
	            	return res.json({success: true, message: 'signup successful', user: newUser})
	            });
	        });
        });

    return { router: authRouter };
};
module.exports = routes;