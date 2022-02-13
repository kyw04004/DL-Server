
**Plan Join**
----
  Returns success message or error message.

* **URL**

  `api/v1/plan/join`

* **Method:**

  `POST`
  
* **URL Params**
   
   **Required:**
   
   None
   
* **Data Params**

```java
{
    "planId" : planId,
    "userId" : userId,
    "point" : point,
    "money" : money
}
```



* **Success Response:**

```java
{
    "status" : 200,
    "data" : true,
    "message" : "Success"
}
```

* **Error Response:**

```java
{
    "status" : 400,
    "data" : NULL,
    "message" : exception.getMessage()
}
```

OR

```java
{
    "status" : 500,
    "data" : NULL,
    "message" : exception.getMessage()
}
```

