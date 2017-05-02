var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var districtSchema = new Schema({
    name: String,
    region: String,
    type: String,
    createdDate: { type: Date, default: Date.now },
    modifiedDate: { type: Date, default: Date.now },
    status : String
});


module.exports = mongoose.model('District', districtSchema);
