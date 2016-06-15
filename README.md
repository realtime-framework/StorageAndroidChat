## The Realtime Cloud Storage Group Chat example for Android (Java) 
This example uses the Realtime Cloud Storage Android SDK to save and retrieve group chat messages from the cloud. This sample uses the Mobile Push Notifications feature to engage offline users when new messages are available.

This application can be used to chat with users using the iOS app available at [https://github.com/realtime-framework/Storage/tree/master/group-chat-ios](https://github.com/realtime-framework/Storage/tree/master/group-chat-ios)

![Group chat](http://storage-public.realtime.co/screenshots/groupchat-android-1.png)
![Group chat](http://storage-public.realtime.co/screenshots/groupchat-android-2.png)

## The example table schema
This example uses a table named RTCSChat with the following key schema:

- Primary key: ChatRoom (string)
- Secondary key: timeStamp (string)

## About the Realtime Cloud Storage Service
Part of the [The Realtime® Framework](http://framework.realtime.co), the Realtime Cloud Storage Service is a highly-scalable backend-as-a-service powered by Amazon DynamoDB. We've added real-time notifications to keep data synchronized between users of your application.


## Security note
This samples uses a public unauthenticated demonstration key. If you want to keep your chat messages private, please get your free Realtime Cloud Storage application key [here](https://accounts.realtime.co/signup/) and change the key used in the sample. 
 
## Documentation
The complete Realtime Cloud Storage API reference is available [here](http://framework.realtime.co/storage/#documentation)