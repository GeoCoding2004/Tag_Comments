# Tag_Comments App

## Purpose
A full-stack web application for managing tags and comments with two user roles: user and admin.

## Requirements
- Java 17+
- Maven
- Node.js + npm
- MySQL (via XAMPP)

## Setup
1. Clone the repository: https://github.com/GeoCoding2004/Tag_Comments.git


2. Navigate to backend and frontend folders:
cd backend
mvn clean install
cd ../frontend
npm install



## Running
- Backend: `mvn spring-boot:run`  
- Frontend: `ng serve`  
- Open browser at `http://localhost:4200`


## Backend Build Instructions

1. Ensure Java JDK 22 is installed.
2. Ensure Maven is installed (or use the Maven wrapper).
3. Build the backend jar:
   ./mvn clean package
4. Run the backend:
    java -jar target/Tags_comments-0.0.1-SNAPSHOT.jar



## Frontend Build Instructions

1. Ensure Node.js >= 22 and npm >= 10 are installed.
2. Install dependencies:
   npm install
3. Build for development
    ng serve
4. Build for production
    ng build --configuration production
5. Serve the production build
    npx serve dist/firstprojectangular/browser --single

### Notes:
- Backend connects to MySQL DB defined in `application.properties`.
- Frontend API URL is defined in `src/environments/environment.ts` (development) and `environment.prod.ts` (production).

