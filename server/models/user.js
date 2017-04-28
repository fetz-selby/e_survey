var mongoose = require('mongoose'),
	Schema = mongoose.Schema,
	bcrypt   = require('bcrypt-nodejs');

// user & agent
var userSchema = new Schema({
	firstname       : { type: String, trim: true },
    surname        	: { type: String, trim: true },
    othernames      : { type: String, trim: true },
    email           : { type: String, unique: true },
    dob 			: Date,
    password 		: String,
    phone		    : { type: String, unique: true },
    address 		: String,
    picture 		: String,
    district 		: String,
    status			: String,
    type 			: String, //admin | agent
    identification 	: [{
    	type		: String,
    	number 		: String,
    	picture		: String
    }],
    createdDate		: { type: Date, default: Date.now },
    modifiedDate	: { type: Date, default: Date.now }
});

// methods ======================
// generating a hash
userSchema.methods.generateHash = function(password) {
    return bcrypt.hashSync(password, bcrypt.genSaltSync(8), null);
};

// checking if password is valid
userSchema.methods.validPassword = function (password) {
    return bcrypt.compareSync(password, this.password);
};


module.exports = mongoose.model('User', userSchema);