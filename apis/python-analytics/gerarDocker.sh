docker stop py-analytics-cont
docker rm py-analytics-cont
docker image rm py-analytics-img
docker image build -t py-analytics-img .
docker container run --name py-analytics-cont -p 5000:5000 -d -t py-analytics-img sleep infinity
docker start py-analytics-cont
