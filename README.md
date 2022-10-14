# Authorization_RESTAPI

### A RESTful API built using Kotlin's ktor framework, Postgresql database with JWT Authorization.

## Structure:

### Base url:

>https://salty-basin-00971.herokuapp.com/

### End points:

>/auth/register

>/auth/login

>/test

## Usage:

### Register

* The end point for registering to the API is **{{baseUrl}}/auth/register**
* On successful registration, a **Access_token/Authorization_token** will be provided.
* The token is mandatory for performing other requests on API endpoints.

>Example:

POST /auth/register

"Request URL": https://salty-basin-00971.herokuapp.com/auth/register

The request body needs to be in JSON format and include the following properties:

>Body: 

              {
                  "full_name":"M S Sandeep Kamath",
                  "profile":"my_profile_url",
                  "email":"sandeep@gmail.com",
                  "password":"test@postgres"
              }


The response body will contain the user details with access token.

>Response:

          {
              "data": {
                  "id": 8,
                  "full_name": "M S Sandeep Kamath",
                  "profile": "my_profile_url",
                  "email": "sandeep@gmail.com",
                  "auth_token": "<access_token>",
                  "create_date": "2022-10-14T20:32:42.198434"
              },
              "message": null,
              "statusCode": {
                  "value": 200,
                  "description": "OK"
              }
          }
               
> Java Okhttps:

                  OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
                  MediaType mediaType = MediaType.parse("application/json");
                  RequestBody body = RequestBody.create(mediaType, "{\r\n    \"full_name\":\"M S Sandeep Kamath\",\r\n    \"profile\":\"my_profile_url\",\r\n                     \"email\":\"sandeep@gmail.com\",\r\n    \"password\":\"test@postgres\"\r\n}");
                  Request request = new Request.Builder()
                    .url("https://salty-basin-00971.herokuapp.com/auth/register")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
                  Response response = client.newCall(request).execute();
                  
                  
 >Python http.client

                
                import http.client
                import json

                conn = http.client.HTTPSConnection("salty-basin-00971.herokuapp.com")
                payload = json.dumps({
                  "full_name": "M S Sandeep Kamath",
                  "profile": "my_profile_url",
                  "email": "sandeep@gmail.com",
                  "password": "test@postgres"
                })
                headers = {
                  'Content-Type': 'application/json'
                }
                conn.request("POST", "/auth/register", payload, headers)
                res = conn.getresponse()
                data = res.read()
                print(data.decode("utf-8"))
                
                
>cUrl

                curl --location --request POST 'https://salty-basin-00971.herokuapp.com/auth/register' \
                --header 'Content-Type: application/json' \
                --data-raw '{
                    "full_name":"M S Sandeep Kamath",
                    "profile":"my_profile_url",
                    "email":"sandeep@gmail.com",
                    "password":"test@postgres"
                }'


### Login

* The end point for Logging in to the API is **{{baseUrl}}/auth/login**
* For logging in the mandatory header is **access_token** obtained during registration.
* On successful login, a **Access_token/Authorization_token** will be provided.
* The token is mandatory for performing other requests on API endpoints.

>Example:

POST /auth/login

"Request URL": https://salty-basin-00971.herokuapp.com/auth/login

The request body needs to be in JSON format and include the following properties:

>Body: 

              {
                  "email":"sandeep@gmail.com",
                  "password":"test@postgres"
              }
              

>Header: 

            Authorization: Bearer <access_token>


The response body will contain the user details and access token too.

>Response:

                  {
              "data": {
                  "id": 9,
                  "full_name": "M S Sandeep Kamath",
                  "profile": "my_profile_url",
                  "email": "sandeep@gmaiil.com",
                  "auth_token": "<access_token>",
                  "create_date": "2022-10-14T20:48:42.588865"
              },
              "message": "Login Successful",
              "statusCode": {
                  "value": 200,
                  "description": "OK"
                } 
              }
               
> Java Okhttps:

                        OkHttpClient client = new OkHttpClient().newBuilder()
                          .build();
                        MediaType mediaType = MediaType.parse("application/json");
                        RequestBody body = RequestBody.create(mediaType, "{\r\n\"email\":\"sandeep@gmaiil.com\",\r\n\"password\":\"test@postgres\"\r\n}");
                        Request request = new Request.Builder()
                          .url("https://salty-basin-00971.herokuapp.com/auth/login")
                          .method("POST", body)
                          .addHeader("Authorization", "Bearer <access_token>")
                          .addHeader("Content-Type", "application/json")
                          .build();
                        Response response = client.newCall(request).execute();

                  
 >Python http.client


                      import http.client
                      import json

                      conn = http.client.HTTPSConnection("salty-basin-00971.herokuapp.com")
                      payload = json.dumps({
                        "email": "sandeep@gmaiil.com",
                        "password": "test@postgres"
                      })
                      headers = {
                        'Authorization': 'Bearer <access_token>',
                        'Content-Type': 'application/json'
                      }
                      conn.request("POST", "/auth/login", payload, headers)
                      res = conn.getresponse()
                      data = res.read()
                      print(data.decode("utf-8"))
                  
                
>cUrl


                        curl --location --request POST 'https://salty-basin-00971.herokuapp.com/auth/login' \
                        --header 'Authorization: Bearer <access_token>' \
                        --header 'Content-Type: application/json' \
                        --data-raw '{
                        "email":"sandeep@gmaiil.com",
                        "password":"test@postgres"
                        }'


### Test

Get /test

"Request URL": https://salty-basin-00971.herokuapp.com/test

>Header: 

            Authorization: Bearer <access_token>
            
>Response:

             hi bro 🤣
             


>Java okhttp:

                OkHttpClient client = new OkHttpClient().newBuilder()
                  .build();
                MediaType mediaType = MediaType.parse("text/plain");
                RequestBody body = RequestBody.create(mediaType, "");
                Request request = new Request.Builder()
                  .url("https://salty-basin-00971.herokuapp.com/test")
                  .method("GET", body)
                  .addHeader("Authorization", "<access_token>")
                  .build();
                Response response = client.newCall(request).execute();
                
>Python http.client

                                    import http.client

                                    conn = http.client.HTTPSConnection("salty-basin-00971.herokuapp.com")
                                    payload = ''
                                    headers = {
                                      'Authorization': 'Bearer <access_token>'
                                    }
                                    conn.request("GET", "/test", payload, headers)
                                    res = conn.getresponse()
                                    data = res.read()
                                    print(data.decode("utf-8"))
                                    
>Curl

              
             curl --location --request GET 'https://salty-basin-00971.herokuapp.com/test' \
          --header 'Authorization: Bearer <access_token>'
