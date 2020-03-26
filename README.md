# My Retail Restful Service(Target case study)

 This project contain following functionalities:

 Restful API services to send JSON response for GET request and send a GET resquest to external api and recieve JSON respose and return name of the peoduct. When PUT method requested update the product price data.

In addition to that you can perform POST request to add one product at a time and DELETE request to delete a existing product.

Also there is a GET method to get all products form database. This might helpful for manual testing to see what exists in database and what not.


## Tools and Technologies Used

```
Java - 8
Spring Boot -- 2.2.5.RELEASE
Spring Framework -- 5.2.4.RELEASE
MongoDB -- 2.2.5.RELEASE
Maven -- 3.6.1
Swagger2 -- 2.9.2
Swagger-ui -- 2.9.2
Junit -- 5(Jupitor)
IDE -- Eclipse
Postman -- For manual testing

```

## Installation(Command Line)

```bash
1. Install Git -- https://www.atlassian.com/git/tutorials/install-git
2. Clone the Repository -- https://github.com/kosurukompany/target-myretail-restful-service.git
3. Install MongoDB -- https://docs.mongodb.com/manual/administration/install-on-linux/
4. Install Maven -- sudo apt update && sudo apt install maven(Ubuntu), sudo yum install maven (CentOS 7) for more options check -- http://maven.apache.org/install.html
5. Run mongoDB -- sudo mongod (default port 27017)
6. Create Database -- use targetcasestudy
7. Copy the host, port and db name to /src/main/resources/instance-based.properties
8. Go to root of the cloned repository -- cd {path}
9. Run maven to install dependencies -- sudo mvn -U clean install (U is for Snapshots to be updated)
10. Run unit tests -- sudo mvn test
11. Create application as service and run -- 
	sudo touch /etc/systemd/system/myretailAPI.service    //Create the service file
	sudo vim  /etc/systemd/system/myretailAPI.service add below
	[Unit]
	Description=myretail Rest API
	After=syslog.target

	[Service]
	User=root
	ExecStart={path}/target/myretail-restapi-service-{version}-SNAPSHOT.jar   // this will be location of jar file in the project
	SuccessExitStatus=143

	[Install]
	WantedBy=multi-user.target

	sudo systemctl enable myretailAPI.service       // Enable the service
	sudo systemctl start myretailAPI.service         // start the service
	sudo systemctl stop myretailAPI.service         // stop the service
	sudo systemctl restart myretailAPI.service    // restart the service
	sudo systemctl status myretailAPI.service    // will give the status 

```
Application is up and tunning on default port or port defined in /src/main/resources/instance-based.properties.

## Setup(IDE)
```
1. Clone the Repository -- https://github.com/kosurukompany/target-myretail-restful-service.git
2. Install MongoDB -- https://docs.mongodb.com/manual/installation/
3. Run MongoDB -- Run the command 'mongod.exe'
4. Create Database -- use targetcasestudy
5. Copy the host, port and db name to /src/main/resources/instance-based.properties(create the properties file if not exists)
6. Now in eclipse Rightclick on project explorer -> imports -> maven - > Existing Maven Projetcs -> browse for root directory where the project cloned and click 'Finish'.
7. Right click on project and select Run as -> maven clean and then maven install.
8. To run test cases go to src/test/java/ and right click on java file and select JUnit Test
9. Now go to src/main/java/com/target/casestudy/MyretailRestapiServiceApplication.java and right click Run as -> Java Application.
```
Application is up and tunning on default port or port defined in /src/main/resources/instance-based.properties.

## API Documentation(/v1/myretail/products)
```
	GET(/v1/myretail/products) -- return complete list of products from database

	GET(/v1/myretail/products/{id}) -- return requested product from database based on ID

	GET(/v1/myretail/products/external/{id}) -- return requested product combined from external API data(id and name) and data from database(current_price) based on ID

	GET(/v1/myretail/products/external/{id}?condition=nameonly) -- return requested product name from external API data based on ID

	POST(/v1/myretail/products/add) -- Add a new product to database

	PUT(/v1/myretail/products/{id}) -- Required valid product data in JSON format. This method update the existing product price

	DELETE(/v1/myretail/products/{id}) -- Delete the existing product

	For more detailed explanation on API methods please refer to Swagger documentation at {host}/swagger-ui.html (ex http://localhost:8088/swagger-ui.html)
```
## Testing
```
	Please refer installation and setup steps to find different procedures to run the test cases. You can find the test cases in `src/test/java/{tests}`.
```

## License
[MIT](https://github.com/kosurukompany/target-myretail-restful-service/blob/master/LICENSE)

