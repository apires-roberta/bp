docker stop feed-jdbc-container
docker rm feed-jdbc-container
docker image rm feed-jdbc 
docker image build -t feed-jdbc . 
docker container run --network apis-mysql --name feed-jdbc-container -p 8080:8080 -d --restart unless-stopped feed-jdbc
docker start feed-jdbc-container