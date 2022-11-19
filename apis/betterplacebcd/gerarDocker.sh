docker stop betterplace-jdbc-container
docker rm betterplace-jdbc-container
docker image rm betterplace-jdbc
docker image build -t betterplace-jdbc . 
docker container run --network apis-mysql --name betterplace-jdbc-container -p 8080:8080 -d --restart unless-stopped betterplace-jdbc
docker start betterplace-jdbc-container