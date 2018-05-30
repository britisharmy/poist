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

# Using Poist

Install Java JRE 

```
sudo apt-get install default-jre
```

Install Maven

```
sudo apt-get install maven
```

Install Rabbit MQ

## Step 1 - Install Erlang

First, use the following commands to add erlang apt repository on your system. You can simply download erlang repository package from its official website and install on your system.

```
wget https://packages.erlang-solutions.com/erlang-solutions_1.0_all.deb
sudo dpkg -i erlang-solutions_1.0_all.deb
```
Now, you can install erlang package on your system using the following command. This will install all of its dependencies as well.

```
sudo apt-get update
sudo apt-get install erlang erlang-nox
```

## Step 2 – Install RabbitMQ Server

After installing requirements, now enable RabbitMQ apt repository on your system. Also you need to import rabbitmq signing key on your system. Use the following commands to do this.

```
echo 'deb http://www.rabbitmq.com/debian/ testing main' | sudo tee /etc/apt/sources.list.d/rabbitmq.list
wget -O- https://www.rabbitmq.com/rabbitmq-release-signing-key.asc | sudo apt-key add -
```

After that update apt cache and install RabbitMQ server on your system.

```
sudo apt-get update
sudo apt-get install rabbitmq-server
```

## Step 3 – Manage RabbitMQ Service

After completing installations, enable the RabbitMQ service on your system. Also, start the RabbitMQ service. Use one the below methods sysvinit for older systems or systemctl for the latest operating system.

Using Init –

```
sudo update-rc.d rabbitmq-server defaults
sudo service rabbitmq-server start
sudo service rabbitmq-server stop
```

Using Systemctl –

```
sudo systemctl enable rabbitmq-server
sudo systemctl start rabbitmq-server
sudo systemctl stop rabbitmq-server
```

## Step 4 – Create Admin User in RabbitMQ


By default rabbitmq creates a user named “guest” with password “guest”. You can also create your own administrator account on RabbitMQ server using following commands. Change password with your own password.

```
sudo rabbitmqctl add_user admin password 
sudo rabbitmqctl set_user_tags admin administrator
sudo rabbitmqctl set_permissions -p / admin ".*" ".*" ".*"
```

## Step 5 – Setup RabbitMQ Web Management Console


RabbitMQ also provides and web management console for managing the entire RabbitMQ. To enable web management console run following command on your system. The web management console helps you for managing RabbitMQ server.

```
sudo rabbitmq-plugins enable rabbitmq_management
```

RabbitMQ dashboard starts on port 15672. Access your server on the port to get dashboard. Use the username and password created in step 4

## Step 6 - Install Stomp Plugins

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

# Next Steps

Install Mysql (this is only needed to demonstrate how to connect to any database that is supported by Hibernate ORM)

```
sudo apt-get install mysql-server
```
Install Mysql driver for java

```
apt-get install libmysql-java
```

Once you have installed everything,simply clone poist 

```
git clone https://github.com/britisharmy/poist.git
```

and adjust your database connection details here https://github.com/britisharmy/poist/blob/master/amqp/src/main/resources/application.properties


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

## License

MIT License (c) Geoffrey 
