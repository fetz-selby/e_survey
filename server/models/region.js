var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var district = require('./district');

var regionSchema = new Schema({
    name: String,
    districts : [district.district],
    createdDate: { type: Date, default: Date.now },
    modifiedDate: { type: Date, default: Date.now }

});

module.exports = mongoose.model('Region', regionSchema);
