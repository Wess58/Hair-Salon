# Hair-Salon Java App

### By wesley Mbate

## Description

  This is a simple Hair-Salon management system that stores stylists and clients successfully because the App is integrated with a Database,therefore, the owner is be able to add a list of the stylists, and for each stylist, add clients who are attended to by that stylist. The stylists work independently, so each client only belongs to a single stylist.


 Here's the link to Live site :<  >

### Prerequisites

 * An up-to-date browser, preferably chrome or mozilla
 * Internet connection !


### Technologies used

   * JAVA
   * Gradle
   * Spark
   * Postgres SQL
   * HTML
   * CSS & Bootstrap
   * Junit

### Installation guide

  If you want to use this as your template, here's how to go about it:

  * Install Git on you machine
  * Maneouver to 'clone or download' button and get the link
  * --Linux Users-- open your terminal and run the 'git clone ...' command in a directory of your choice
  * --for Windows Users-- Navigate to the location you'd want the repository located, right click and select "git bash here"
  * Clone the repository
  * Upon completion, navigate to the cloned repository
  Feel free to edit the files to your prefered taste

  * Now build the Database to enable storing of created instances
  * Install Postgres SQL
  * run the following commands in your terminal

        CREATE DATABASE hair_salon;

        CREATE TABLE stylists (id serial PRIMARY KEY, firstName varchar, secondName varchar, lastName varchar, phoneNo varchar, idNo varchar, email varchar);

        CREATE TABLE clients (id serial PRIMARY KEY, clientFirstName varchar,  clientLastName varchar, clientPhoneNo varchar, clientIdNo varchar, clientEmail varchar);

        CREATE DATABASE hair_salon_test;

         CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;


### BUGS
No Bugs found, If any are experienced contact via Email wesleymbate@gmail.com and the issue will be sorted.


### LICENSE
 This website is under the MIT license which can be found [HERE](LICENSE).
 Copyright (c) 2018 wesleymbate
