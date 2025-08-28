Created a micro service on quizapp application
   --two services question service and quiz service are working as seperate modules with seperate databases.
   --communication between these two microservices is made possible through eureka server.
   --eureka server discovery client dependency allows all the microservices to be registered with itself.
   --so these microservices can be discoverable to each other through eureka server
   --used open feign dependency which allows the services to communicate with each other
   --Load balancing is not need as it is available in open feign client which means only those instance of service 
     who has less request is approached to take requests.
   --going to design api gateway so that client dont face difficulties while making request and getting responses.
   
