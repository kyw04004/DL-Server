
**Plan Allocate**
----
  Returns success message or error message.

* **URL**

  `api/v1/plans/allocate`

* **Method:**

  `POST`
  
*  **URL Params**

   **Required:**

   None

* **Data Params**

```java
{
    "planId" : planId,
    "option" : 0(1/n) or 1(to only one) or 2(to half)
}
```

  

* **Success Response:**

```java
{   
    "status" : 200,
    "data" : "allocate complete",
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