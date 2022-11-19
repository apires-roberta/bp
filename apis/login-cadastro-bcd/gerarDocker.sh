docker stop login-jdbc-container
docker rm login-jdbc-container
docker image rm login-jdbc
docker image build -t login-jdbc . -f /home/ubuntu/jars/loginCadastro/Dockerfile
docker container run --network apis-mysql --name login-jdbc-container -p 8080:8080 -d --restart unless-stopped login-jdbc
docker start login-jdbc-container

