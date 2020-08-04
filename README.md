![Zdjęcie](./images/run.jpg)

# Lista Startowa

Spring Boot application to register members of the run, also allows manage the list:

* application logger (SLF4J & Logback) configured by `application.properties` file, 
allows you to select the log storage place and the logging lever for each part of the app, available logging levels: OFF, ERROR, WARN, INFO, DEBUG, TRACE,

* application is divided into layers: model, repository, services, controllers; they correspond to the code packages,

* views are HTML templates powered by Thymeleaf, visualization and layout are provided by Bootstrap,

* the Apache POI project and the iText library were used to generate PDF and XLS files,

* data entered into the form are validated (via Bean Validating),

* homepage http://localhost:8080/

## Technologies

* [Java 8](https://www.java.com/)
* [Spring Boot 2](https://spring.io/)
* [Gradle](https://gradle.org/)
* [Thymeleaf](https://www.thymeleaf.org/)
* [SLF4J](http://www.slf4j.org/) & [LOGBack](http://logback.qos.ch/)
* [Bootstrap](https://getbootstrap.com/)
* HTML5 & CSS3
* [Apache POI](https://poi.apache.org/)
* [iText PDF](https://itextpdf.com/)
* `.gitignore` file created by [gitignore.io](https://www.gitignore.io)

## Running

```
	$ ./gradlew bootRun
```

or

```
	$ ./gradlew bootJar	
```

```
	$ ./java -jar build/libs/lista-startowa-1.0.0.jar
```

***

# Lista Startowa

Przykładowa aplikacja Spring Boot służąca do rejestracji uczestników biegu. 
Umożliwia zarządzanie listą uczestników:

* logger aplikacji (SLF4J & Logback) konfigurowany jest w pliku `application.properties`, 
umożliwia wybór miejsca przechowywania dzienników oraz poziomu logowania dla każdej z części aplikacji, dostępne poziomy logowania: OFF, ERROR, WARN, INFO, DEBUG, TRACE,

* aplikacja podzielona na warstwy: model, repozytorium, serwisy, kontrolery; odpowiadają one pakietom w projekcie,

* widoki to szablony HTML uzupełniane przy pomocy Thymeleaf, wizualizacja i układ zapewniają
elementy Bootrstrap'a.  

* do generowania zestawień PDF i XLS wykorzystano projekt Apache POI oraz bibliotekę iText,

* dane wpisywane do formularza są walidowane (Bean Validating),

* strona główna aplikacji http://localhost:8080/

## Użyte technologie

* [Java 8](https://www.java.com/)
* [Spring Boot 2](https://spring.io/)
* [Gradle](https://gradle.org/)
* [Thymeleaf](https://www.thymeleaf.org/)
* [SLF4J](http://www.slf4j.org/) & [LOGBack](http://logback.qos.ch/)
* [Bootstrap](https://getbootstrap.com/)
* HTML5 & CSS3
* [Apache POI](https://poi.apache.org/)
* [iText PDF](https://itextpdf.com/)
* Plik `.gitignore` wygenerowano przy pomocy witryny [gitignore.io](https://www.gitignore.io)

## Uruchamianie

```
	$ ./gradlew bootRun
```

lub

```
	$ ./gradlew bootJar	
```

```
	$ ./java -jar build/libs/lista-startowa-1.0.0.jar
```