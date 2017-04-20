var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var userSchema = new Schema({
  created_by : String,
  created_date : { type: Date},
  modified_date: { type: Date, default: Date.now },
  status : String
});

module.exports = {model : userSchema};