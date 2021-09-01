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