[![CI Status](https://github.com/gpaOliveira/java-selenium/actions/workflows/main.yml/badge.svg)](https://github.com/gpaOliveira/java-selenium/actions/workflows/main.yml)

# Java Selenium Template

For quick tests with java and selenium (chromedriver only), build with graddle and github actions

# Properties

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