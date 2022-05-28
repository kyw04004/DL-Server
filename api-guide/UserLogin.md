
**User Login**
----
  Returns json data about a userId.

* **URL**

  `api/v1/users/login`

* **Method:**

  `POST`
  
*  **URL Params**

   **Required:**

   None

* **Data Params**

```java
{
    "nickName" : nickName,
    "email" : email
}
```

  

* **Success Response:**

```java
{
    "status" : 200,
    "data" : userId,
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

