
**Plan Delete**
----
  Returns success message or error message.

* **URL**

  `api/v1/plans/{planId}`

* **Method:**

  `DELETE`
  
* **URL Params**
   
   **Required:**
   `planId=[Long]`
   
* **Data Params**

    None

* **Success Response:**

```java
{
    "status" : 200,
    "data" : "delete coplete!!",
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