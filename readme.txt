Для работы приложения нужна база Postgres
database restfit
username postgres
password postgres
Либо прописать свои данные в файле src\main\resources\application.properties

Схема бд предоставлена в файле postgres.txt

Для запуска приложения нужно выполнить в cmd следующие команды:
 gradlew build
 gradlew bootRun
 
При успешном запуске можно работать с приложение по ссылке http://localhost:8080/home