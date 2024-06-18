## kafka-actuator-example

Система учета и передачи метрик с возможностью получения истории метрики ([Задача](/docs/task.txt))

## Запуск standalone kafka+zookeeper
- docker-compose -f ./docker/docker-compose-kafka.yaml up --force-recreate --remove-orphans

## Билд обоих сервисов 
- mvn clean package -DskipTests=true

## Запуск каждого микросервиса локально 
- producer service: java -jar -Dspring.profiles.active=dev ./kafka-producer/target/kafka-producer.jar 
- customer service: java -jar -Dspring.profiles.active=dev ./kafka-consumer/target/kafka-consumer.jar    

## Пересоздать все контейнеры полностью (kafka+zookeper+postgres+consumer+producer)
- bash run.sh

### Использование swagger-ui
- Producer
  * api http://localhost:8082/v3/api-docs
  * swagger-ui http://localhost:8082/swagger-ui/index.html
- Consumer
    * api http://localhost:8083/v3/api-docs
    * swagger-ui http://localhost:8083/swagger-ui/index.html
  
При успешной работе swagger должен отобразиться графический интерфейс swagger-ui со списком запросов.

### Описание работы
- Дергаем ендпойнт 
    - POST http://localhost:8082/metrics- будут считаны и переданы в кафка метрики producer, которые затем будут считаны consumer и записаны в postgres
- Дергаем ендпойнты
    - GET http://localhost:8083/metrics?moment=2024-06-25T16:54:30Z - вывод последний на выбранный момент времени метрик
    - GET http://localhost:8083/metrics/jvm.buffer.memory.used?momentFrom=2024-06-17T14:32:12.303Z&momentTo=2024-06-19T14:32:13.303Z - вывод истории значений конкретной метрики
