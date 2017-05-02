var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var agentSchema = new Schema({
    firstname       : String,
    surname         : String,
    othernames      : String,
    phone           : String,
    email           : String,
    password        : String,
    district        : { type: Schema.Types.ObjectId, ref: 'District' },
    identification  : [{
        type	    : String,
        number 		: String,
        picture		: String
    }],
    createdBy       : { type: Schema.Types.ObjectId, ref: 'User' },
    createdDate     : { type: Date, default: Date.now },
    modifiedDate    : { type: Date, default: Date.now },
    status          : String
});

module.exports = mongoose.model('Agent', agentSchema);