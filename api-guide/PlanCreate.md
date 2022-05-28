
**Plan Create**
----
   Returns success message or error message.

* **URL**

  `api/v1/plans`

* **Method:**

  `POST`
  
*  **URL Params**

   **Required:** None

* **Data Params**

```java
{
    "admin" : adminUserId,
    "deposit" : deposit,
    "latePercent" : latePercent(0-100),
    "absentPercent" : absentPercent(0-100),
    "title" : title,
    "location" : location,
    "detailLocation" : detailLocation,
    "date" : date,
    "done" : false or true
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
    "message" :exception.getMessage()
}
```

