![alt text](https://github.com/britisharmy/poist/blob/master/poist.jpg)


# Poist ![alt text](https://travis-ci.org/britisharmy/poist.svg?branch=master)
An easy way to perform background job processing in your applications.

# Overview

Poist is a reliable application to perform backgrouund jobs. Currently it has the following features.

1. Has a scheduler that supports cron jobs.
2. Has web sockets support.
3. Supports multiple threads in a scheduler
4. Supports multiple threads in a consumer.
5. Supports Hibernate ORM
6. Poist is cross platform
7. Makes use of Rabbit MQ and Spring AMQP
8. Its easy to modify and use compared to other solutions such as Resque, Sidekiq, delayed_job, Celery,Quartz Scheduler,Hangfire,Gearman

# Connecting

Install the stomp plugin https://www.rabbitmq.com/stomp.html using this command

```
rabbitmq-plugins enable rabbitmq_stomp
```

and also the web stomp plugin

https://www.rabbitmq.com/web-stomp.html

```
rabbitmq-plugins enable rabbitmq_web_stomp
```

Check if you have the plugins using this command

```
rabbitmq-plugins list
```

Once you have all the plugins,simply clone poist

```
git clone https://github.com/britisharmy/poist.git

```

and start poist

```
mvn jetty:run
```

and connect to poist using the code below.

```
<script src="//cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.2/sockjs.min.js"></script>
<script src="stomp.js"></script>
<script type="text/javascript">
 var ws = new SockJS("http://your-ip:15674/stomp");
 var client = Stomp.over(ws);
 
 client.heartbeat.outgoing = 0;
 client.heartbeat.incoming = 0;
 
 var onConnect = function() {
   client.subscribe("/topic/messages", function(d) {
   var str = d.body
   var res = str.match(/Body:\'(.+)\'/);
   console.log("I control this",res[1]);
   });
 };
 
 var onError = function(e) {
   console.log("ERROR", e);
 };
 
 client.connect("admin", "adminpassword", onConnect, onError, "/");
 
</script>
```


# Use Cases

## Chat

Build powerful and feature-rich realtime chat apps. Cross-device and cross-platform means they work across web, iOS, Android, and the rest.

## IoT Device Control

Securely monitor, control, provision and stream data between smart devices, sensor networks, hubs, and more. Trigger device action, monitor meta data, or stream and process incoming and outgoing data. 

## Realtime Updates

Keep everyone, and every device realtime. From mapping, geotracking and dispatch, to broadcasting push notifications and up-to-the-millisecond alerts, when something happens in the real world, it's reflected in your app.

## Collaboration

Build realtime collaborative tools and environments for apps that allow teams to work together better
