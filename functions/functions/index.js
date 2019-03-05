const functions = require('firebase-functions');
const admin = require('firebase-admin');
const serviceAccount = require('./service-account.json');

admin.initializeApp({
	credential : admin.credential.cert(serviceAccount),
	databaseURL : 'https://vkarma-5f051.firebaseio.com'
});

exports.generateSignInToken = functions.https.onCall((data) => {
    const username = data.username;
    const password = data.password;

    if (username.length < 5) {
        throw new functions.https.HttpsError('invalid-argument', 'The username is too short.');
    }
	
	if (username === null || password === null){
		throw new functions.https.HttpsError('invalid-argument', 'username in null');
	}

    return admin.auth().createCustomToken(username)
        .then(function (customToken) {
            // Send token back to client
            return customToken.toString();
        })
        .catch(function (error) {
            console.log("Error creating custom token:", error);
        });
});