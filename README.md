The complete documentation can be found at http://cpe305.github.io/fall2016-project-jakep11/
 
 
 ## Purpose.
The TriTimeTracker is a web application that serves to track previous Triathlon times, provide statistics, and estimate future triathlons. The web app allows for the secure creation of multiple user accounts and will store each individuals data. This project was created as a class project for CPE 305 Individual Software Design and Development at Cal Poly San Luis Obispo.

## Walk Through
   
Home Screen
![](http://i.imgur.com/KSYzu1s.jpg)
   
Create a unique account
![](http://i.imgur.com/rcs9DQ1.jpg)
   
Add a previous triathlon
![](http://i.imgur.com/vqsbuFz.jpg)
  
View all previous triathlons
![](http://i.imgur.com/XXnhamd.jpg)
   
View stats calculated for each triathlon
![](http://i.imgur.com/1TBJ1sT.jpg)
   
Estimate your time for a future Triathlon
![](http://i.imgur.com/1LetnFO.jpg)
   
## Architecture
The software uses a Service-Oriented architecture as pictured below. The backend REST API is built with the Java Spring Framework and the frontend is built with AngularJS. A Model-View-Controller (MVC) architecture is used and the View component consists of AngularJS. AngularJS contains an MVC, so within the View of the broader MVC, there is another MVC within.
   
![](http://i.imgur.com/eJqhLVa.jpg)

Below is a more detailed Class Diagram
  
![](http://i.imgur.com/jze753C.jpg)
  
  
## Technologies/Tools Used
Technologies/languages:
* Java 8
* Spring Framework
* AngularJS
* Bootstrap

Tools:
* Eclipse
* Maven
* Sonarqube
* Travis CI [![Build Status](https://travis-ci.org/cpe305/fall2016-project-jakep11.svg?branch=master)](https://travis-ci.org/cpe305/fall2016-project-jakep11)
* Checkstyle (Google Style Guide for Java)
* GitHub (obviously if you are reading this)

### Design Patterns
* Singleton Pattern (Database instance and JPA Repositories)
    * Easily kept track of single database instance and TriathlonRepository and UserRepository
* (Spring framework and AngularJS inherently use several other patterns such as the Observer Pattern)

## Design Patterns I Should Have Used
* Decorator Pattern (To extend the attributes of a Triathlon)
* State Pattern (To save Triathlon estimates and implement various estimations based on the state of the Triathlon)


## Why My Design is Robust/What I like about my project
1. Completely Decoupled REST API and User Interface
    * The REST API can be paired with any frontend User Interface such as an Android or iOS app instead of a website. 
2. Fully secured/authenticated API.
3. Basic Framework of project is easily extendable. 

## Key Takeaways 
1. Design Patterns allow for a much higher flexibility for the future (DesignStaminaHypothesis)
    * I had a set idea of the data structure to use for a Triathlon and it made it easy to implement.
    * If I wanted to extend it in the future, it would be rather difficult since I didn't use a design pattern for the implementation of it's attributes
2. Java Spring Framework is a pain. There is a ton of "convention over configuration" that makes it easy to do a ton of "magic" with little code but makes extension from the default, very difficult. For example, setting up the database and user authentication was a nightmare.
3. The Continuous Integration workflow is a huge help in maintaining the reliability of the application. Each tool I used (listed above) was beneficial to the creation and maintainability of the project.




### Support or Contact
Having trouble with TriTimeTracker? Check out the [project](https://github.com/cpe305/fall2016-project-jakep11) or [contact support](mailto:jjpicket@calpoly.edu) and we’ll help you sort it out.

