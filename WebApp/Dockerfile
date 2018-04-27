FROM node:8.10.0
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
#COPY server.js /usr/src/app
COPY dist /usr/src/app/dist
COPY package-docker.json /usr/src/app/package.json
#RUN touch package.json
RUN npm install http-server --save
expose 4200
CMD ["./node_modules/.bin/http-server","./dist","-p","4200"]
#RUN npm install express --save
#expose 4200
#CMD ["node","server.js"]s
