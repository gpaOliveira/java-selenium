[![CI Status](https://github.com/gpaOliveira/java-selenium/actions/workflows/main.yml/badge.svg)](https://github.com/gpaOliveira/java-selenium/actions/workflows/main.yml)

# Java Selenium Template

For quick tests with java and selenium (chromedriver only), build with graddle and github actions

# Filesystem structure

```
.
+-- pom.xml                     	# our project is built with Maven, so here are all information about our dependencies
+-- src/main/java
    +-- selenium.config         	# package that allows us to read Properties from config files or the bash environment - based on Owner. 
    +-- selenium.context         	# package to carry a Context class, a singleton that holds the current Logger, Driver, and more global information if needed
+-- src/test/java
    +-- selenium.actions         	# package to isolate complex actions done with Page Objects in order to take that burden out of that and the test cases.
    +-- selenium.base	         	# package with the BaseTest class
    +-- selenium.pages         	        # package with Page Objects from important components in the application under test 
    +-- selenium.tests         	        # package our classes carrying test cases - all based on JUnit 4
+-- src/test/resources		
	+-- application.properties	# the properties file with our variables - if it doesn't exists, they are taken from the bash environment
+-- scripts
    +-- InstallChrome.sh		# install chrome browser to help our Github actions
+-- github
    +-- workflows					
        main.yml			# the file with our CI/CD definition that will run on Github Actions
```

# Properties

Our config package is [Owner](http://owner.aeonbits.org/), mostly due to the easy to define default values.

Parameters can be set on `src/test/resources/application.properties` and if not they will be taken from the bash environment. 

That file is **not** version controlled to avoid having passwords and emails in git. 

Currently those are the entries supported:

* timeout: implicit wait time in seconds (defaults to 3);
* headless: true or false (defaults to true);
* user_good_username: the email of a good user to login successfully;
* user_good_password: the password for a good user to login successfully; 

# Logs

Logging is generated in the traditional maven path `target/surefire-reports` for each test run. A `test-output.log` is generated on the main folder.

They are accessible as artifacts during the Github Actions run. A future step would be to integrate that log with Allure somehow.

# Report

With [Allure](https://docs.qameta.io/allure/) we generate reports in the traditional maven path `target/allure-results` and on `target/site/allure-maven-plugin`.

This should also be accessible using Github Pages on [https://gpaoliveira.github.io/java-selenium/](https://gpaoliveira.github.io/java-selenium/). 

# Spiritual references

A quick search on github reveal other similar java-selenium-template projects from where I took ideas with a certain grain of salt.

* [Ardesco/Selenium-Maven-Template](https://github.com/Ardesco/Selenium-Maven-Template/)
* [sebarmeli/Selenium2-Java-QuickStart-Archetype](https://github.com/sebarmeli/Selenium2-Java-QuickStart-Archetype)
* [eliasnogueira/selenium-java-lean-test-achitecture](https://github.com/eliasnogueira/selenium-java-lean-test-achitecture)

# Future ideas

## Improve Allure Reports

Currently we only have the pre-generated report which, as much as it is pretty, could be customized to [carry our context variables](https://dev.to/eliasnogueira/add-more-information-to-your-allure-report-using-java-27n5) or even [attachments with screenshots and payloads](https://blog.issart.com/practical-guide-for-making-tests-execution-result-reports-more-comprehensible/).

## JUnit custom listener

In order to have log messages (and maybe even later modifying the Allure report) we could have a listener for when the test cases start/stop their execution. See [here an implementation idea](https://howtodoinjava.com/junit/how-to-add-listner-in-junit-testcases/) and [here the maven line to add](https://stackoverflow.com/questions/4968475/using-junit-runlistener-with-maven).
