var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var peopleSchema = new Schema({
	firstname		: String,
	surname			: String,
	othernames 		: String,
	dob 			: Date,
	birthPlace 		: String,
	nationality 	: String,
	nationalityType : String,
	dualCitizenship	: [String],
	ethnicity 		: String,
	maritalStatus 	: String,
	languages 		: {
		spoken 		: [String],
		written 	: [String]
	},
	phones			: [String],
	email 			: String,
	address 		: {
		residential : String,
		postal 		: String,
		work 		: String
	},
	region 			: String,
	districtType 	: String,
	gps				: {
		work		: {
			lat		: String,
			lng		: String
		},
		home		: {
			lat		: String,
			lng		: String
		}
	},
	what3words 		: {
		home		: String,
		work 		: String
	},
	photo 			: String,
	employer 		: String,
	employmentStatus: String,
	employer 		: String,
	occupation 		: String,
	commencementDate: Date,
	position 		: String,
	employmentSector: String,
	disability		: [String],
	identification	: {
		type  		: String,
		number		: String,
		picture		: String
	},
	
	createdBy 		: String,
	createdDate 	: { type: Date, default: Date.now },
	modifiedDate	: { type: Date, default: Date.now }
});

module.exports = mongoose.model('People', peopleSchema);



/*

Property collection

2. Photo of Building

3. Photo of Certificate of Indenture

4. Finger Print(s)

*/








// 