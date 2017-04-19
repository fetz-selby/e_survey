var express = require('express'),
    cookieParser = require('cookie-parser'),
    bodyParser = require('body-parser'),
    multer = require('multer'),
    session = require('express-session'),
    port = process.env.PORT || 8001;

var app = express(),

    //Define Mongo Instance
    pool = {};

//var smsDispatcher = require('./service/sms_gateway');

//Instantiating all routes
var agentsRoute = require('./routes/agents_router')(pool),
    regionsRoute = require('./routes/regions_router')(pool),
    districtsRoute = require('./routes/districts_router')(pool),
    usersRoute = require('./routes/users_router')(pool),
    peopleRoute = require('./routes/people_router')(pool);

  

//Set middlewares
//app.use(bodyParser.urlencoded({extended: true}));
//app.use(bodyParser.json());
app.use(bodyParser.json({limit: '50mb'}));
app.use(bodyParser.urlencoded({limit: '50mb', extended: true}));
app.use(cookieParser());
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

//Disable cache
app.use(function (req, res, next) {
    res.header('Cache-Control', 'private, no-cache, no-store, must-revalidate');
    res.header('Expires', '-1');
    res.header('Pragma', 'no-cache');
    next()
});

//Middleware to check for user validity
// app.use('/eghana/esurvey/api/*', function(req, res, next){
    
//     if(req.query.email && req.query.password){
        
//     sql().query('select id,level from users where email=? and password=?',
//               [req.query.email, require('./service/utils').getHash(req.query.email+req.query.password)], 
//               function (error, results) {
//                 if (error) {
//                     console.error('error connecting: ' + err.stack);
//                     return;
//                 }

//                 if(results.length === 1 && results[0].id > 0){
//                         req.query.level = results[0].level;
//                         req.query.userId = results[0].id;
//                         next();  
//                     }else{
//                         res.status(200).json([]);
//                 }
//     });
        
        
//     }else{
//         res.status(400).send('Please provide correct email and password');
//     }
// })

app.use('/eghana/esurvey/api/agents/', agentsRoute.router);
app.use('/eghana/esurvey/api/regions/', regionsRoute.router);
app.use('/eghana/esurvey/api/districts/', districtsRoute.router);
app.use('/eghana/esurvey/api/users/', usersRoute.router);
app.use('/eghana/esurvey/api/people/', peopleRoute.router);

//app.use('/api/esoko/sessions', sessionRoute.router);

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