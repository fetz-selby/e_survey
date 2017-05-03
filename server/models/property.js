var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var propertySchema = new Schema({
	pin 			: String,
	propertyType	: String,
	classification 	: String,
	ownershipType 	: {
		status 		: { type: String, enum: ['Legal', 'Illegal'] },
		registered  : { type: Boolean },
		titleNumber : String,
		indentureNumber: String
	},
	location		: {
		gps 		: { lat: Number, lng: Number },
		region 		: String,
		town 		: String,
		district	: String,
		what3words	: String
	},
	address 		: String,
	familyUnitsNumber: String,
	electricitySource: String,
	partnership		:[{
		name 		: String,
		percentage	: Number
	}],
	partnershipType : String,

	emergency		: {
		name 		: String,
		address		: String,
		city		: String,
		phones		: String,
		email		: String
	},
	authorizedAgent : {
		name 		: String,
		address		: String,
		city		: String,
		phones		: String,
		email		: String
	},
	propertyManager	: {
		name 		: String,
		address		: String,
		city		: String,
		phones		: [String],
		email		: String,
		licenseNumber: String
	},
	landTrust		: Boolean,
	trustBeneficiaries: [{
		name 		: String,
		percentage 	: Number,
		address 	: String,
		city 		: String,
		phone 		: String
	}],
    owners 			: [{ type: Schema.Types.ObjectId, ref: 'People' }],

    createdDate		: { type: Date, default: Date.now },
    modifiedDate	: { type: Date, default: Date.now },
    status			: String
});



module.exports = mongoose.model('Property', propertySchema);