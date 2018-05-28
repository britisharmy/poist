package com.boilerplate.scheduler;


import java.util.Date;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Async;

import com.boilerplate.services.MessageListenerService;
import java.util.Random;

//Posts Table Create Code

/**

CREATE TABLE `posts` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`firstname` VARCHAR(50) NULL DEFAULT NULL,
	`legendname` VARCHAR(50) NULL DEFAULT NULL,
	`age` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;


*/

//Hibernate

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Configuration
@EnableAsync
@EnableScheduling
public class Scheduler {
//Database read and update and delete
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MessageListenerService.class);
	
	@Autowired 
	private SessionFactory sessionFactory;
	
	@Async
	@Scheduled(cron="*/5 * * * * *")
	public void doSomething() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Random rand = new Random();
		Random f = new Random();
		Random l = new Random();
		Random a = new Random();
		
		int n = rand.nextInt(5000) + 1;
		int firstname = f.nextInt(5000) + 98;
		int legendname = l.nextInt(5000) + 54;
		int age = a.nextInt(5000) + 23;
		
		rabbitTemplate.convertAndSend("bottomlesspit", n);
		LOGGER.info("this connected....");
		LOGGER.info("Running at " + new Date());
		LOGGER.info("Current Thread : {}", Thread.currentThread().getName());		
		
		@SuppressWarnings({"rawtypes","deprecation"})
	    Query theQuery = currentSession.createSQLQuery("INSERT INTO posts(firstname,legendname,age) values(:firstname,:legendname,:age)");
	    theQuery.setParameter("firstname", firstname);
		theQuery.setParameter("legendname", legendname);
		theQuery.setParameter("age",age);
		theQuery.executeUpdate();

	}
	
	@Async
	@Scheduled(cron="*/7 * * * * *")
	public void doTest() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Random rand = new Random();
		Random f = new Random();
		Random l = new Random();
		Random a = new Random();
		
		int n = rand.nextInt(10) + 1;
		int firstname = f.nextInt(30) + 43;
		int legendname = l.nextInt(40) + 84;
		int age = a.nextInt(50) + 23;
		
		rabbitTemplate.convertAndSend("deeppit", rand);
		LOGGER.info("this is in deep pit....");	
		LOGGER.info("Current Thread : {}", Thread.currentThread().getName());
		
		
		@SuppressWarnings({"rawtypes","deprecation"})
	    Query theQuery = currentSession.createSQLQuery("update posts set firstname = ?1, legendname = ?2, age = ?3 where id=?4");
	    theQuery.setParameter(1, firstname);
		theQuery.setParameter(2, legendname);
		theQuery.setParameter(3, age);
		theQuery.setParameter(4, 1);
		theQuery.executeUpdate();
		
	}
	
	

}
