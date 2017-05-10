var Seeder = require('mongoose-data-seed').Seeder;
var Property = require('../models/property');
var Region = require('../models/region');
var People = require('../models/people');
var faker = require('faker');



var PropertySeeder = Seeder.extend({
    beforeRun: function(){
        var _this = this;
        
        return Promise
            .resolve()
            .then(function () {
                return _this._loadPeople();
            })
            .then(function () {
                return _this._loadDistricts();
            });
    },
    shouldRun: function () {
        return true; //Property.count().exec().then(count => count === 0);
    },
    run: function () {
        var data = [];
        var distLen = this.districts.length;
        var peopleLen = this.people.length;
        var ptypes = ['Land','House','Kiosk','Container','Separate House unit', 'Semi-detached','Flat/Apartment','Compound','Huts','Tent','Improvised','Living quarters attached to office', 'Uncompleted building'];
        
        for(var owner of this.people){
            var prop = {
                pins : faker.random.number(),
                propertyType : faker.random.arrayElement(ptypes),
                classification : faker.random.arrayElement(['Residential','Commercial']),
                ownerShipType : {
                    status 		: 'Legal',
                    registered  : faker.random.boolean(),
                    titleNumber : faker.random.alphaNumeric(),
                    indentureNumber: faker.random.alphaNumeric()
                },
                location		: {
                    gps 		: { lat: faker.address.latitude(), lng: faker.address.longitude() },
                    town 		: faker.address.city(),
                    district	: faker.random.arrayElement(this.districts)
                },
                address: faker.address.streetAddress(),
//                familyUnits: 1,
                electricitySource: faker.random.arrayElement(['Mains', 'Private generator', 'None']),
                emergency		: {
                    name 		: faker.name.firstName() +' '+faker.name.lastName(),
                    address		: faker.address.streetAddress(),
                    city		: faker.address.city(),
                    phone		: faker.phone.phoneNumber(),
                    email		: faker.internet.email()
                },                
                owners: [owner]
            }
            
           data.push(prop); 
        }
        
        return Property.create(data);
    },
    _loadDistricts:  function(){
        var _this = this;
        return Region
            .distinct('districts._id')
            .exec()
            .then(function (districts) {
                _this.districts  = districts;
            })       
    },
    _loadPeople: function(){
        var _this = this;
        return People.distinct('_id')
                .then(function(people){
                  _this.people = people;  
                })
    }
});

module.exports = PropertySeeder;
