var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var districtSchema = new Schema({
    name: String,
    type: String
});

module.exports = {district : districtSchema};
//module.exports = mongoose.model('District', districtSchema);
