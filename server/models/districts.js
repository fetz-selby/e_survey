var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var districtSchema = new Schema({
  name:  String,
  region: String,
  created_date : { type: Date},
  modified_date: { type: Date, default: Date.now },
  status : String
});

module.exports = {model : districtSchema};