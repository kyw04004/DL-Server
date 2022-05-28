
**Point Exchange**
----
  Returns success message or error message.

* **URL**

  `api/v1/users/exchange`

* **Method:**

  `POST`
  
*  **URL Params**

   **Required:**

   None

* **Data Params**

```java
{
    "userId" : userId,
    "bank" : bank,
    "account" : account,
    "point" : point,
    "name" : name
}
```

  

* **Success Response:**

```java
{
    "status" : 200,
    "data" : "Exchange request complete",
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

