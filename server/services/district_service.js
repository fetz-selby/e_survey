var	fs = require('fs'),
	JSONStream = require('JSONStream'),
	es = require('event-stream'),
	District = require('../models/district');


exports.loadDistricts = function () {
	var file 	= __dirname+'/../data/districts.json',
	 	stream 	= fs.createReadStream(file, {encoding: 'utf8'}),
    	parser 	= JSONStream.parse('*');

    stream.pipe(parser)
	.pipe(es.mapSync(function(data) {
		//clear collection
		// District.remove({})

		District.findOne({name: data.District})
		.then(function (district) {
			if(!district){
				var newDistrict = new District;
				newDistrict.name = data.District;
				newDistrict.region = data.Region;
				newDistrict.type = data.Type;

				newDistrict.save();
			}
		})
		.catch(function (err) {
			console.log(err)
		})
	}))
}