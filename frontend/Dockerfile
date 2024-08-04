FROM node:16.13-alpine3.15 AS builder
WORKDIR /app
COPY package*.json ./
RUN apk --no-cache add --virtual .builds-deps build-base python3
RUN npm config set legacy-peer-deps true
# slow internet connection
RUN npm config set fetch-retry-mintimeout 20000 
RUN npm config set fetch-retry-maxtimeout 120000 
#
RUN npm install   && npm rebuild bcrypt --build-from-source && npm cache clean --force 
#RUN npm install --production && npm rebuild bcrypt --build-from-source && npm cache clean --force 
COPY . .

RUN npm run build


FROM nginx:1.25.2-alpine-slim

COPY --from=builder /app/build /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]









