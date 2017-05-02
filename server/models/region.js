var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var regionSchema = new Schema({
    name: String,
    status : String
});

module.exports = mongoose.model('Region', regionSchema);
