#Trading system start guide
1. open terminal
2. change to the root directory of repository
3. compile code
   - mvn -Dmaven.test.skip=true install
4. start container
   - docker-compose up --build

#Run unit test
1. open application.properties
2. use spring.datasource.url=jdbc:mysql://localhost:3306/trading_database?useSSL=false&allowPublicKeyRetrieval=true
3. command spring.datasource.url=jdbc:mysql://trading_db/trading_database?useSSL=false&allowPublicKeyRetrieval=true
4. mvn install

#CURL

1. add trade message

curl --location --request POST 'localhost:8080/tradeMessage/add' \
--header 'Content-Type: application/json' \
--data-raw '{
"userId":"123456",
"currencyFrom":"USD",
"currencyTo":"GBP",
"amountSell":1000,
"amountBuy":747.10,
"rate":0.7471,
"timePlaced":"24-AUG-18 10:27:44",
"originatingCountry":"FR"

} '

2. get trade message

curl --location --request GET 'localhost:8080/tradeMessage/get' \
--header 'Content-Type: application/json'