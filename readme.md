# Movie Ticket App

## Built with:
<div>
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/React-icon.svg/2300px-React-icon.svg.png" width=180 height=150>
<img src="https://miro.medium.com/max/500/1*AbiX4LwtSNozoyfypcKvEg.png" width=150 height=150>
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Postgresql_elephant.svg/1985px-Postgresql_elephant.svg.png" width=150 height=150>
</div>

## Getting Started
### <ins>Frontend</ins>
Change into frontend directory: ```cd frontend```   
 
Install dependencies: ```yarn install```   

Start frontend: ```yarn start```   

### <ins>Backend</ins>
1. Download docker: https://www.docker.com/products/docker-desktop/
2. Open a terminal
3. Run the following:   
   1. ```docker run --name postgres-spring -e POSTGRES_PASSWORD=admin -d -p 5432:5432 postgres:alpine```
   2. ```docker ps```
   3. ```docker exec -it <container-id from the docker ps command> bin/bash```
   4. ```psql -U postgres```
   5. ```CREATE DATABASE springdb;```
   6. ```\c springdb```
4. Database is now fully setup
5. Change into backend directory: ```cd backend```
6. Run the following:
   1. ```brew install maven```
   2. ```mvn spring-boot:run```
7. Server should now be started on localhost:8080

## Screenshots
<center>Home Page</center>

![home page](screenshots/homepage.png)

<center>Movies Page</center>

![search](screenshots/searchresults.png)

<center>Seat Selection</center>

![seat selection](screenshots/seatselection.png)

<center>Guest Checkout</center>

![guest checkout](screenshots/guestcheckout.png)

<center>Registration</center>

![registration](screenshots/register.png)

<center>Log In</center>

![log in](screenshots/login.png)