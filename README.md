![Zdjęcie](./images/run.jpg)

# Lista Startowa

Przykładowa aplikacja Spring Boot służąca do rejestracji uczestników biegu. 
Umożliwia zarządzanie listą uczestników.

* logger aplikacji (SLF4J & Logback) konfigurowany jest w pliku `application.properties`, 
umożliwia wybór miesja przechowywania dzienników oraz poziomu logowania dla każdej z części aplikacji, dostępne poziomy logowania: OFF, ERROR, WARN, INFO, DEBUG, TRACE,

* aplikacja podzielona na warstwy: model, repozytorium, serwisy kontrolery; odpowiadają pakietom w projekcie.

* widoki to szablony HTML uzupełniane przy pomocy Thymeleaf, wizualizacja i układ zapewniają
elementy Bootrstrap'a.  

* do generowania zestawień PDF i XLS wykorzystano projekt Apache POI oraz bibliotekę iText.

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