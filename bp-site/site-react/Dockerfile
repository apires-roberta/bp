from mhart/alpine-node:14

WORKDIR /usr/src/bp

COPY package*.json ./

#RUN npm install

COPY . . 

EXPOSE 3000

CMD ["npm", "start"]