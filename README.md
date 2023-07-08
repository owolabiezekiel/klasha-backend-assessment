# klasha-backend-assessment
### How to run
Pull this code  
go to the root of the repository  
If you prefer to run it locally, make sure you have maven installed and run ```mvn spring-boot:run```  
If you prefer to run it on docker, there is a docker file that contains the build process.  Open the contents because you might need to change it if you are not on a mac running apple silicon.  
The ```FROM --platform=linux/arm64 openjdk:17-jdk-slim``` command can be changed to ```FROM openjdk:17-jdk-slim``` if you are on another machine that is not running apple silicon. The rest of the file can be left as is.  
  
  Still at the root of the project directory,  
type ```docker build -t klasha .``` make sure to copy the (.) at the end of the command as well. this builds the project image in docker. You can replace the ```klasha``` at the end of that command to any name you want.
After the build, run ```docker run -p 8080:8080 klasha```. this will run the app in the docker container  

  Once the service is up, navigate your browser to [Swagger docs here](http://localhost:8000/swagger-ui/index.html#/assessment-controller)  
  The live version can be found [here](https://klasha-assessment-a8c6bbc6dd45.herokuapp.com/swagger-ui/index.html#/assessment-controller)  
