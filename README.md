# Outfit7-Back-end


1. Clone this repository


2. In the same folder as .yaml in terminal write `docker compose up`


3. All request are already setup in "Outfit7 - backend.postman_collection.json" file, which is imported in POSTMAN where u can make REQUESTS.


3. There are 2 REST APIS:

    POST - Check servcies API
    
       3 QUERY PARAMS (timeZone, countryCode, userId)
       Requires username and password for Basic auth
                Example:  http://localhost:8080/user?timeZone=Europe/Ljubljana&countryCode=SL&userId=aljaz123
                Username (for basic auth): fun7user
                Password:(for basic auth): fun/pass
    
    
    
    ADMIN API
    
        Username (for basic auth)- admin
        Password (for basic auth)- kj98Sch5859/asd
        
        Get User IDs    
            Requires username and password for Basic auth which is available only to admins
            Example: http://localhost:8080/user_list/
                       
        Get User's Details
            1 PATH VARIABLE (userId)
            Requires username and password for Basic auth which is available only to admins
            Example: http://localhost:8080/user_list/aljaz123
            
        Delete User
            1 PATH VARIABLE (userId)
            Requires username and password for Basic auth which is available only to admins
            Example: http://localhost:8080/user_list/aljaz123
            
            
4. All avaiable values for QUERY PARAMS (timeZone and countryCode) are written in files "AvaiableTimeZones" and "AvaiableCountryCodes"


5. You can also use phpMyAdmin in URL "http://localhost:8081" Where you can see the database with values.


6. If you want to run this app localy (not on docker) you need to set in application.properties DATABASE_URL=localhost 
And then configure other properties for database especially if you dont use MySql.


          
      
    
