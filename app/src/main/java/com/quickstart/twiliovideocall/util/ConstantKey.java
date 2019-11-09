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
FRIENDLY NAME
TwilioApiKeyTest

SID
SK7b0f47547393b1df0e9a279e87863b0c

KEY TYPE
Standard

SECRET
fuNt95cfGU7qo5r5bnd2Z2Ijyi8BPr8z
================================================
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