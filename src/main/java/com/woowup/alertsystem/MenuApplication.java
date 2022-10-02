package com.woowup.alertsystem;

import com.woowup.alertsystem.application.AlertFinder;
import com.woowup.alertsystem.application.AlertSenderService;
import com.woowup.alertsystem.application.AlertReader;
import com.woowup.alertsystem.application.InformativeAlertNotifier;
import com.woowup.alertsystem.application.TopicCreator;
import com.woowup.alertsystem.application.TopicFinder;
import com.woowup.alertsystem.application.TopicSubscriptor;
import com.woowup.alertsystem.application.UrgentAlertNotifier;
import com.woowup.alertsystem.application.UserFinder;
import com.woowup.alertsystem.application.UserRegister;
import com.woowup.alertsystem.domain.topic.Topic;
import com.woowup.alertsystem.domain.topic.TopicRepository;
import com.woowup.alertsystem.domain.subcription.TopicSubscriptionsRepository;
import com.woowup.alertsystem.domain.user.User;
import com.woowup.alertsystem.domain.user.UserRepository;
import com.woowup.alertsystem.infrastructure.persistence.InMemoryTopicRepository;
import com.woowup.alertsystem.infrastructure.persistence.InMemoryTopicSubscriptionRepository;
import com.woowup.alertsystem.infrastructure.persistence.InMemoryUserRepository;
import java.time.LocalDateTime;
import java.util.Scanner;

public class MenuApplication {

  private final UserRepository userRepository;
  private final TopicRepository topicRepository;
  private final TopicSubscriptionsRepository topicSubscriptionsRepository;
  private final UserRegister userRegister;
  private final TopicCreator topicCreator;
  private final TopicSubscriptor topicSubscriptor;
  private final TopicFinder topicFinder;

  private final AlertSenderService alertSenderService;
  private final InformativeAlertNotifier informativeAlertNotifier;
  private final UrgentAlertNotifier urgentAlertNotifier;
  private final AlertFinder alertFinder;
  private final AlertReader alertReader;

  private final UserFinder userFinder;
  private Scanner scanner;
  private boolean exit = false;

  public MenuApplication() {
    this.userRepository = new InMemoryUserRepository();
    this.topicRepository = new InMemoryTopicRepository();
    this.topicSubscriptionsRepository = new InMemoryTopicSubscriptionRepository();
    this.userRegister = new UserRegister(userRepository);
    this.topicCreator = new TopicCreator(topicSubscriptionsRepository, topicRepository);
    this.topicSubscriptor = new TopicSubscriptor(topicSubscriptionsRepository, userRepository);
    this.topicFinder = new TopicFinder(topicRepository);
    this.alertSenderService = new AlertSenderService();
    this.informativeAlertNotifier = new InformativeAlertNotifier(topicSubscriptionsRepository);
    this.urgentAlertNotifier = new UrgentAlertNotifier(userRepository);
    this.alertFinder = new AlertFinder(userRepository);
    this.alertReader = new AlertReader(userRepository);
    this.userFinder = new UserFinder(userRepository);
  }

  public void init() {
    while (!exit) {
      try {
        printMenu();
        input();
      } catch (Exception ex) {
        System.out.println("something bad happened");
        System.out.println(ex.getMessage());
      }
    }
  }

  private void printMenu() {
    System.out.println("[1] Register user");
    System.out.println("[2] Registrar Topic");
    System.out.println("[3] Show Topics");
    System.out.println("[4] Subscribe user in topic");
    System.out.println("[5] Send Informative Alert");
    System.out.println("[6] Send Urgent Alert");
    System.out.println("[7] Show username alerts");
    System.out.println("[8] Show topic alerts");
    System.out.println("[9] Mark alert as read ");
    System.out.println("[10] Exit ");
  }

  private void input() {
    scanner = new Scanner(System.in);
    int option = scanner.nextInt();
    if(option == OptionMenu.REGISTER_USER) {
      registerUser();
    }
    if(option == OptionMenu.REGISTER_TOPIC) {
      registerTopic();
    }
    if(option == OptionMenu.FIND_ALL_TOPICS) {
      findAllTopics();
    }
    if(option == OptionMenu.SUBSCRIBE) {
      subscribe();
    }
    if(option == OptionMenu.SEND_INFORMATIVE_ALERT) {
      sendAlertInformative();
    }
    if(option == OptionMenu.SEND_URGENT_ALERT) {
      sendUrgentAlert();
    }
    if(option == OptionMenu.FIND_USERNAME_ALERTS) {
      findAlertByUsername();
    }
    if(option == OptionMenu.FIND_TOPIC_ALERTS) {
      findAlertsByTopic();
    }
    if(option == OptionMenu.READ_ALERT) {
      readAlert();
    }
    if(option == OptionMenu.EXIT) {
      exit = true;
    }
  }

  private void registerUser() {
    System.out.println("------ Register menu ------");
    System.out.println("Write your username");
    String username = scanner.next();
    User newUser = new User(username);
    userRegister.register(newUser);
    System.out.println("Successful registration!");
  }

  private void registerTopic() {
    System.out.println("------ Register Topic ------");
    System.out.println("Write topic title");
    String topicTitle = scanner.next();
    System.out.println("Write topic description");
    String topicDescription = scanner.next();
    Topic newTopic = new Topic(topicTitle, topicDescription);
    topicCreator.create(newTopic);
    System.out.println("Successful registration!");
  }

  private void findAllTopics() {
    topicFinder.findAll()
        .forEach(System.out::println);
  }

  private void subscribe() {
    System.out.println("------- Subscription Menu -------");
    System.out.println("Write ID topic");
    Long idTopic = scanner.nextLong();
    System.out.println("Write username");
    String username = scanner.next();
    topicSubscriptor.subscribe(username, idTopic);
    System.out.println("Successful subscription");
  }

  private void sendAlertInformative() {
    System.out.println("------- Informative Alert Menu -------");
    System.out.println("will send alerts to users who are subscribed to a specific topic");
    System.out.println("Write id topic");
    Topic topic = topicFinder.find(scanner.nextLong());
    LocalDateTime expiration = buildLocalDate();
    System.out.println("[1] Send informative alert to all users");
    System.out.println("[2] Send informative alert to one user");
    int option = scanner.nextInt();
    if(option == 1) {
      alertSenderService.sendAlert(topic, expiration, informativeAlertNotifier);
    }
    if(option == 2) {
      System.out.println("write username");
      User user = userFinder.find(scanner.next());
      alertSenderService.sendAlert(topic, expiration, user, informativeAlertNotifier);
    }
  }

  private void sendUrgentAlert() {
    System.out.println("------- Urgent Alert Menu -------");
    System.out.println("will send alerts to users even if they are not subscribed to a topic");
    System.out.println("Write id topic");
    Topic topic = topicFinder.find(scanner.nextLong());
    LocalDateTime expiration = buildLocalDate();
    System.out.println("[1] Send Urgent alert to all users");
    System.out.println("[2] Send Urgent alert to one user");
    int option = scanner.nextInt();
    if(option == 1) {
      alertSenderService.sendAlert(topic, expiration, urgentAlertNotifier);
    }
    if(option == 2) {
      System.out.println("write username");
      User user = userFinder.find(scanner.next());
      alertSenderService.sendAlert(topic, expiration, user, urgentAlertNotifier);
    }
  }


  private LocalDateTime buildLocalDate() {
    System.out.println("write expiration date ");
    System.out.println("write year");
    int year = scanner.nextInt();
    System.out.println("write month");
    int month = scanner.nextInt();
    System.out.println("write day");
    int day = scanner.nextInt();
    System.out.println("write hour");
    int hour = scanner.nextInt();
    System.out.println("write minute");
    int minute = scanner.nextInt();
    return LocalDateTime.of(year,month,day,hour, minute);
  }

  private void findAlertByUsername() {
    System.out.println("Write username");
    alertFinder.findAlerts(scanner.next())
        .forEach(System.out::println);
  }

  private void findAlertsByTopic() {
    System.out.println("Write topic id");
    alertFinder.findAlertByTopic(scanner.nextLong())
        .forEach(System.out::println);
  }

  private void readAlert() {
    System.out.println("Write username");
    String username = scanner.next();
    System.out.println("Write Alert ID");
    Long alertId = scanner.nextLong();
    alertReader.readAlert(username, alertId);
  }



}
