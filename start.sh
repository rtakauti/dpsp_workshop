sudo service mysql start
echo "create database if not exists school" | mysql -p
./mvnw spring-boot:run