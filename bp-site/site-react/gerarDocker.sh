docker stop front-bp-container
docker rm front-bp-container
docker image rm front-bp-image
docker image build -t front-bp-image .
npm install
docker container run -p 80:3000 -d --name front-bp-container --restart unless-stopped front-bp-image
docker start front-bp-container
