# Projekt_Java

## Opis
Projekt Java to aplikacja napisana w Java z użyciem Spring Boot, która pozwala na pobieranie/wyświetlanie informacji o repozytoriach GitHub dla danego użytkownika. Aplikacja zwraca listę repozytoriów, które nie są forkami, wraz z informacjami o gałęziach i ostatnich commitach.

## Technologie
- Java 21
- Spring Boot 3.3.2 (WebFlux, Lombok, Reactor)

## Wymagania
- Java 21
- Maven 3.6+

## Instalacja
1. Sklonuj repozytorium:
```
bash
git clone https://github.com/P3CZRI6/Projekt_Java.git
```

2. Skonfiguruj właściwości aplikacji:
Jeśli plik `application.properties` nie znajduje się w folderze `src/main/resources/`, to należy do utworzyć i dodać właściwość:
```
github.api.url=https://api.github.com
```

3. Zainstaluj zależności i zbuduj projekt:
```
mvn clean install
```

## Uruchomienie
Aby uruchomić aplikację lokalnie, wykonaj następujące polecenie:
```
mvn spring-boot:run
```
Aplikacja uruchomi się na domyślnym porcie 8080. 
Możesz zmienić port w pliku `application.properties` dodając:
```
server.port=8081
```

## Użycie
Otwórz przeglądarkę i wpisz ten adres URL
```
http://localhost:8080/api/users/{username}/repositories
```
**{username}-nazwa użytkownika GitHub**
### Przykład:
```
http://localhost:8080/api/users/P3CZRI6/repositories
```

## Obsługa błędów
Jeśli użytkownik GitHub nie istnieje, API zwróci odpowiedź 404:
```
{
  "status": 404,
  "message": "User not found"
}
```

## Struktura Projektu
- `src/main/java/com/projectpj/projektpj/`
  - `GitHubController.java`: Kontroler obsługujący zapytania HTTP.
  - `GitHubClient.java`: Klient komunikujący się z API GitHub.
  - `GitHubProperties.java`: Klasa konfiguracyjna dla właściwości aplikacji.
  - `Branch.java`: Model danych reprezentujący gałąź repozytorium.
  - `Repository.java`: Model danych reprezentujący repozytorium.
- `src/main/resources/`
  - `application.properties`: Plik konfiguracyjny aplikacji.

## Autor
### Patryk Jędrzejczyk
[Profil GitHub](https://github.com/P3CZRI6/)


