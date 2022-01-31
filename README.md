# Getting Started

### Remote Zugriff auf das Backend
Das Backend ist auf einem Server installiert, sodass die beiden Clients auf das gleiche zugreifen können. Es ist erreichbar unter: 
https://p-frei.de/ also z.B. GET https://p-frei.de/avatar

### Voraussetzung für die lokale Installation
Bevor das Backend gestartet werden kann, müssen die folgenden Vorraussetzungen auf dem System gegeben sein:

* Java 11
* Maven
* Docker und Docker-compose (letzteres ist im Normalfall bei der normalen Docker Installation inkludiert)

### Server starten
Der Server kann mittels `mvn clean install && docker-compose build && docker-compose up` gestartet werden

### Server aufrufen
* Unter `localhost:8085` kann die Applikation aufgerufen werden
* Unter `localhost:8081` kann eine Oberfläche zur Datenbank aufgerufen werden (Mongo Express)

### Endpunkte testen
Auf oberster Ebene des Projekts ist eine Postman Collection hinterlegt, mit der die REST Endpunkte der Applikation direkt getestet werden können. 
Die Web Socket Connection muss separat getestet werden.