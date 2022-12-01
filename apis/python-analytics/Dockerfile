# syntax=docker/dockerfile:1

FROM appsvc/azure-functions-python:3.0-python3.9-appservice

WORKDIR /python-docker

CMD ["gunzip", "unixODBC*.tar.gz"]
CMD ["tar", "xvf", "unixODBC*.tar"]

COPY requirements.txt requirements.txt
RUN pip3 install -r requirements.txt

COPY . .

WORKDIR unixODBC-2.3.11/

CMD ["./configure", "--prefix=/usr/local/unixODBC"]

CMD ["make"]

CMD ["make", "install"]

#CMD [ "python3", "-m" , "flask", "run", "--host=0.0.0.0"]

CMD ["flask", "--app", "Analytics", "run"]
