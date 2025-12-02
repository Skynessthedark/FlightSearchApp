# Flight Search Service Application

Flight Search Service Application is a RESTful application to search flights and find the cheapest ones, which are provided by two SOAP services.

**Technologies:** Java 17, Spring Boot 4, Spring Webservices, Spring Data JPA, PostgreSQL, Docker, Git.

## SOAP Services

### Provider A : http://<domain_name>:8081/ws/
Generates 9 random flights each time a request is made.

**Sample Request:**
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:flig="http://flightsearch.com/provider/a">
   <soapenv:Header/>
   <soapenv:Body>
      <flig:SearchRequest>
         <flig:origin>IST</flig:origin>
         <flig:destination>ANK</flig:destination>
         <flig:departureDate>2025-12-15T10:30:00</flig:departureDate>
      </flig:SearchRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### Provider B : http://<domain_name>:8082/ws/
Generates 9 random flights each time a request is made.

**Sample Request:**
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:flig="http://flightsearch.com/provider/b">
   <soapenv:Header/>
   <soapenv:Body>
      <flig:SearchRequest>
         <flig:departure>IST</flig:departure>
         <flig:arrival>ANK</flig:arrival>
         <flig:departureDate>2025-12-15T10:30:00</flig:departureDate>
      </flig:SearchRequest>
   </soapenv:Body>
</soapenv:Envelope>
```
## REST Services

### 1. Find All Flights : http://<domain_name>:8080/flights/all
Finds all flights provided by Provider A and Provider B.

**Sample Request:**
```
{
    "departure": "IST",
    "arrival": "ANK",
    "departureDate": "2025-12-15T10:30:00"
}
```

### 2. Find Cheapest of Flights : http://<domain_name>:8080/flights/cheapest
Finds all flights and lists the cheapest ones.

**Sample Request:**
```
{
    "departure": "IST",
    "arrival": "ANK",
    "departureDate": "2025-12-15T10:30:00"
}
```
