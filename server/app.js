var express = require('express'),
    cookieParser = require('cookie-parser'),
    bodyParser = require('body-parser'),
    multer = require('multer'),
    session = require('express-session'),
    port = process.env.PORT || 8001,
    mongoose = require('mongoose'),
    modelInitializer = require('./services/model_service'),
    districtService = require('./services/district_service'),
    logger = require('morgan'),
    expressValidator = require('express-validator'),
    dbConfig = require('./config');



var app = express(),

    //Define Mongo Instance
    pool = {};
mongoose.Promise = global.Promise;
//Init DB instance
mongoose.connect('mongodb://'+dbConfig.config.db_instance);

//Init Schema Models
modelInitializer.initModels();

//uncomment to reload district to db
// districtService.loadDistricts();

//Instantiating all routes
var agentsRoute     = require('./routes/agents_router')(pool),
    regionsRoute    = require('./routes/regions_router')(pool),
    districtsRoute  = require('./routes/districts_router')(pool),
    usersRoute      = require('./routes/users_router')(pool),
    peopleRoute     = require('./routes/people_router')(pool);
    authRoute       = require('./routes/auth_router')(pool);

  

//Set middlewares
//app.use(bodyParser.urlencoded({extended: true}));
//app.use(bodyParser.json());
app.use(bodyParser.json({limit: '50mb'}));
app.use(bodyParser.urlencoded({limit: '50mb', extended: true}));
app.use(cookieParser());
app.use(expressValidator([]));
app.use(session({resave:true, saveUninitialized: true, 
                secret: 'thequickbrownfoxjumpedoverthelazydogs',
                cookieName: 'session',
                duration: 30*60*1000, 
                activeDuration: 5*60*1000, 
                httpOnly: true, 
                cookie: {secure: false }}));

//CORS enabling
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  res.header("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
  next();
});

//logging
app.use(logger('dev'));

//Disable cache
app.use(function (req, res, next) {
    res.header('Cache-Control', 'private, no-cache, no-store, must-revalidate');
    res.header('Expires', '-1');
    res.header('Pragma', 'no-cache');
    next();
});



app.use('/eghana/esurvey/api/auth', authRoute.router);
app.use('/eghana/esurvey/api/agents', agentsRoute.router);
app.use('/eghana/esurvey/api/regions', regionsRoute.router);
app.use('/eghana/esurvey/api/districts', districtsRoute.router);
app.use('/eghana/esurvey/api/users', usersRoute.router);
app.use('/eghana/esurvey/api/people', peopleRoute.router);

app.get('/eghana', function(req, res){
    res.status(200).send('Please check API documentation');
});

app.get('/', function(req, res){
    res.status(200).send('Please check API documentation');
});

app.get('/eghana/esurvey', function(req, res){
    res.status(200).send('Please check API documentation');
});

app.get('/eghana/esurvey/api', function(req, res){
    res.status(200).send('Please check API documentation');
});

app.listen(port, function(){
    console.log('Running on PORT '+port);

    //Init all events
    initAllEvents();
});



var initAllEvents = function(){
    
}