package com.quickstart.twiliovideocall.util;

public class ConstantKey {

    public static final String ROOM_KEY = "kamal";
}
/*

Twilio Video Room
https://www.twilio.com/docs/video/tutorials/understanding-video-rooms

Twilio Function
https://www.twilio.com/docs/runtime/functions-assets-api/api/function?code-sample=code-delete-function&code-language=Java&code-sdk-version=7.x



Password : Gmail | Github
kamal1009@stis.com.bd

=============================================

public static final String ACCOUNT_SID = "ACf92291edf2e4e6b90e2b437452ed5eff";
public static final String AUTH_TOKEN = "3e235fad509dd22c78eebefb79aa9db8";

=============================================
import com.twilio.Twilio;
import com.twilio.rest.serverless.v1.Service;

public class TwilioService {
	// Find your Account Sid and Token at twilio.com/console
    // DANGER! This is insecure. See http://twil.io/secure
    public static final String ACCOUNT_SID = "ACf92291edf2e4e6b90e2b437452ed5eff";
    public static final String AUTH_TOKEN = "3e235fad509dd22c78eebefb79aa9db8";

    public static void main(String[] args) {
        //Service Create
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Service service = Service.creator("TwilioVideo", "TwilioVideo").setIncludeCredentials(true).create();
        System.out.println(service.getSid()); //ZS2c2fc90311ef23d917dad51489f2c332

        //Service Fetch
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Service service = Service.fetcher("ZS8e179c5fb4f5d02a1abad6de6455fe70") .fetch();
        System.out.println(service.getAccountSid() +", "+ service.getFriendlyName() +", "+ service.getSid());

        //Service Delete
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Service.deleter("ZS06d95f036e6572d64c7bdbb2ad3e7506").delete();
    }
}


import com.twilio.Twilio;
import com.twilio.rest.serverless.v1.service.Function;

// Install the Java helper library from twilio.com/docs/java/install
public class TwilioFunction {
    // Find your Account Sid and Token at twilio.com/console
    // DANGER! This is insecure. See http://twil.io/secure
    public static final String ACCOUNT_SID = "ACf92291edf2e4e6b90e2b437452ed5eff";
    public static final String AUTH_TOKEN = "3e235fad509dd22c78eebefb79aa9db8";

    public static void main(String[] args) {
		//Function Create
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Function function = Function.creator("ZS2c2fc90311ef23d917dad51489f2c332", "twilio_function").create();
        System.out.println(function.getSid()); //ZH40bb0f10d998bea891cc4b825265026e

		//Function Delete
    }
}


TwilioApiKey
------------

FRIENDLY NAME
TwilioApiKey
SID
SK31d9fa30bbd7c8a05610967484d81741
KEY TYPE
Standard
SECRET
02fFK2TyUij0QK1WigxcF2KTqr3ERCFI

-----------

kamalstis1009@gmail.com
FRIENDLY NAME
TwilioApiKey
SID
SKd8674e61706c42219ff9fd85ec3941e1
KEY TYPE
Standard
SECRET
dN3MetpP45dRagIh3UpLNhWaVAswgYa0

------------

exports.handler = function(context, event, callback) {
	const AccessToken = require('twilio').jwt.AccessToken;
    const VideoGrant = AccessToken.VideoGrant;

    // Used when generating any kind of Access Token
    //const twilioAccountSid = 'ACf92291edf2e4e6b90e2b437452ed5eff';
    //const twilioApiKey = 'SK638e9993ed26cea3fb4da9755b2101ed';
    //const twilioApiSecret = 'iR3kvfJmseBFOgA8mSIaaXEy34qAsqWU';
    const twilioAccountSid = context.ACCOUNT_SID;
    const twilioApiKey = context.API_KEY;
    const twilioApiSecret = context.API_SECRET;

    // Create an access token which we will sign and return to the client,
    // containing the grant we just created
    const token = new AccessToken(twilioAccountSid, twilioApiKey, twilioApiSecret);
    //token.identity = 'TwilioVideo';
    token.identity = event.identity;

    // Create a Video grant which enables a client to use Video
    // and limits access to the specified Room (DailyStandup)
    const videoGrant = new VideoGrant({
        room: event.room
    });

    // Add the grant to the token
    token.addGrant(videoGrant);

    // Serialize the token to a JWT string
    console.log("token: " + token.toJwt());

    console.log("ACCOUNT_SID : " + context.ACCOUNT_SID);
	console.log("API_KEY : " + context.API_KEY);
	console.log("API_SECRET : " + context.API_SECRET);

	console.log("identity : " + event.identity);
	console.log("room : " + event.room);

	callback(null, { token: token.toJwt() });
};

=====================================================
*/