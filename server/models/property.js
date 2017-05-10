var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var mongoosePaginate = require('mongoose-paginate');


var propertySchema = new Schema({
	pins 			: String,
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
		town 		: String,
		district	: { type: Schema.Types.ObjectId },
		what3words	: String
	},
	address 		: String,
	familyUnits     : Number,
	electricitySource: String,
//	partnership		:[{
//		name 		: String,
//		percentage	: Number
//	}],
	partnershipType : String,

	emergency		: {
		name 		: String,
		address		: String,
		city		: String,
		phone		: String,
		email		: String
	},
	agent           : {
		name 		: String,
		address		: String,
		city		: String,
		phone		: String,
		email		: String
	},
	propertyManager	: {
		name 		: String,
		address		: String,
		city		: String,
		phone		: String,
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
    guid            : String,
    owners 			: [{ type: Schema.Types.ObjectId, ref: 'People' }],
    createdDate		: { type: Date, default: Date.now },
    modifiedDate	: { type: Date, default: Date.now },
    status			: { type: String, default: 'A'}
});


propertySchema.plugin(mongoosePaginate);

module.exports = mongoose.model('Property', propertySchema);