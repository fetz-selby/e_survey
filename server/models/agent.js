var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var agentSchema = new Schema({
  f_name:  String,
  l_name: String,
  email:   String,
  password : String,
  msisdn : String,
  created_by : String,
  created_date : { type: Date},
  modified_date: { type: Date, default: Date.now },
  status : String
});

module.exports = mongoose.model('Agent', agentSchema);