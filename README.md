[![Build Status](https://travis-ci.com/ChristofferKrogh/SoftwareEngineeringExamProject.svg?token=zvbHMxD5bkzRoESesxKQ&branch=master)](https://travis-ci.com/ChristofferKrogh/SoftwareEngineeringExamProject)
[![Known Vulnerabilities](https://snyk.io//test/github/ChristofferKrogh/SoftwareEngineeringExamProject/badge.svg?targetFile=pom.xml)](https://snyk.io//test/github/ChristofferKrogh/SoftwareEngineeringExamProject?targetFile=pom.xml)

# SoftwareEngineeringExamProject

Planning app exam project for 02161 Software Engineering 1


# Authors

Christoffer Krogh, s163959@student.dtu.dk  
Clara Chini Nielsen, s164213@student.dtu.dk  
Emil Ørup Kristensen, s154304@student.dtu.dk  
Thina Lundsgaard Thøgersen, s165541@student.dtu.dk


# Automatic testing

The code is tested upon each push to out git repository and then Travis CI have checked everything automatically as a quality control. Code coverage is only tested manually on the development teams computers.


# Pre requirements

Environment, tested on Mac, Windows and Linux (only tests, not GUI).

* Java 8 update 202, 64-bit JDK or later updates, see note
* Eclipse version 2019-03
* Maven 3.5.2 or later

Note: Java version is OpenJDK distributed by AdoptOpenJDK. Use either HotSpot or OpenJ9 JVM. Oracle Java is fine as well. We assume later updates will work fine as well. Later versions of java may give problems

# How to run

Please note: The Eclipse project does not contain any run configurations by default. Use the guides below to get started.


## Run tests with Maven

1. Be sure to fulfil the pre requirements above
2. Check that the "JAVA_HOME" environment variable is set to the root of the JDK, and Maven is added to your enviroment
3. Run `mvn test` in your console
4. Inspect result. Every JUnit test and Cucumber acceptance test will run automatically.


## Import project into Eclipse - tested on Windows
1. Be sure to fulfil the pre requirements above
2. Open a fresh and empty workspace
3. Select from menu: "File -> Import..."
4. The import wizard opens
5. Unzip the project files, remember what folder you used
6. Select "General -> Existing Project into Workspace"
7. Select root directory: The folder you just unzipped the project to. Be sure that "Exam Project" is checked in the "Projects" window
8. Click "Finish" and wait for a few minutes.


## Run tests with Eclipse - tested on Windows

1. Complete "Import project into Eclipse" if you haven't done that already
2. Navigate to "test/dtu.planning.acceptance_tests"
3. Right click "dtu.planning.acceptance_tests" and select "Run as -> JUnit Test". If JUnit test is missing, follow the steps to run the GUI first. This will download JUnit.
4. Inspect the result of the tests


## Run GUI with Eclipse - tested on Mac

1. Complete "Import project into Eclipse" if you haven't done that already
2. Navigate to "src/dtu.planning.gui" and select "MainScreen.java"
3. Right click "Mainscreen.java" and select "Run as -> Java Application"
4. Wait for Maven to download dependencies. Internet connection is required.
5. The GUI opens
