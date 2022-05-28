
**User PaymentList**
----
  Returns json data about a user paymentList.

* **URL**

  `api/v1/users/{userId}/payments`

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**

   `userId=[Long]`

* **Data Params**

  None



* **Success Response:**

```java
{
    "status" : 200,
    "data" : {
        "paymentList" : [
            {
            	"userId" : userId,
                "paymentId" : paymentId,
                "money" : money,
                "point" : point,
                "date" : date
            },	
    		...
        ],
    },
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

