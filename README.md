# comoposite-task
# Analiza

Mamy tutaj do czynienia ze wzorcem Composite. Oznacza to, że lista zawarta w klasie Wall może posiadać
obiekty klasy implementującej zarówno interfejs Block jak i CompositeBlock (przez to, że interfejs CompositeBlock
rozszerza interfejs Block).
Dla metod w klasie Wall należało więc rozpatrzyć możliwości dla różnego stopnia zagnieżdżenia obiektów.
W tym celu przygotowałem metodę pomocniczą findBlockByPredicate oraz checkComposite.
Pierwsza metoda zwraca listę Block, a jako argument przyjmuje warunek Predicate. Na początku tworzona jest lista,
która będzie zawierała obiekty spełniające warunek. Pętlą for przechodzę po kolejnych elementach listy blocks.
Dla każdego elementu wywoływana jest druga metoda pomocnicza. Działa ona tak:
- jeśli obecnie sprawdzany element to Block lub CompositeBlock to ten element dodawany jest do listy rezultatów,
- jeśli obecnie sprawdzany element jest instancją CompositeBlock to w pętli for dla każdego elementu listy zawartej
w CompositeBlock sprawdzam poprzez rekurencję czy nie jest zawarte więcej instancji CompositeBlock. 

W ten sposób do listy rezultatów dodawany jest sam element typu CompositeBlock, zawarte w nim ewentualne pozostałe
elementy typu Block, oraz po prostu elementy typu Block zawarte w polu składowym Wall.

# Set-up
- Java 21,
- Maven,
- Docker,
- Spark,
- Spring,
- Gson,
- Junit,
- Lombok,
- Hamcrest,
- Jdbi.

# How to install?

Use command below to install the package into your local repository.
```
mvn clean install
```
If you don't want to create local repository use:
```
mvn clean package -DskipTests
```

# How to run?

When you have created .jar file, run application with docker:
```
docker-compose up -d --build
```
There are two files included. One of them creates necessary tables inside db, second one fills tables with example 
values. 

You can also run code from [Main](src/main/java/com/app/Main.java), just comment the `WEB` section.

# Running unit tests

To run unit tests properly go to project destination in terminal and then execute below command:
```
mvn test
```

# Endpoints

There is also postman file included.
```
http://localhost:8080/all/blocks
```
```
http://localhost:8080/all/blocks/color/:color
```
```
http://localhost:8080/all/blocks/material/:material
```
```
http://localhost:8080/all/blocks/count
```