
**Plan Calculate**
----
  Returns success message or error message.

* **URL**

  `api/v1/plan/calculate`

* **Method:**

  `PUT`
  
* **URL Params**
   
   **Required:**

   None
   
* **Data Params**

```java
{
  "planId" : 2,
  "jsonArray" : [
      {
          "userId" : 1,
          "lateState" : 0
      },
      {
          "userId" : 2,
          "lateState" : 2
      }
  ]
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

