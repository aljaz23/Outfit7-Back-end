# Outfit7-Back-end

1. Clone this repository


2. In the same folder as .yaml in terminal write docker compose up


3. There are 2 REST APIS:

    POST - Check servcies API
       3 QUERY PARAMS (timeZone, countryCode, userId)
       Requires username and password for Basic auth
                Example:  http://localhost:8080/user?timeZone=Europe/Ljubljana&countryCode=SL&userId=aljaz123
                Username: fun7user
                Password: fun/pass
    
    
    
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

          
      
    
