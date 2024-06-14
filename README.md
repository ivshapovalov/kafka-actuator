## build 
- mvn clean package -DskipTests=true
## execute local
- producer service: java -jar -Dspring.profiles.active=dev ./kafka-producer/target/kafka-producer.jar 
- customer service: java -jar -Dspring.profiles.active=dev ./kafka-consumer/target/kafka-consumer.jar    
## deploy in docker
- bash run.sh
