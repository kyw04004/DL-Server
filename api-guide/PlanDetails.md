
**Show Plan**
----
  Returns json data about a single plan.

* **URL**

  `api/v1/plans/{planId}`

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:** `planId=[Long]`

* **Data Params**

  None

* **Success Response:**

```java
{
    "status" : 200,
    "data" : {
        "planId" : planId,
        "admin" : adminUserId,
        "deposit" : deposit,
        "latePercent" : latePercent(0-100),
        "absentPercent" : absentPercent(0-100),
        "title" : title,
        "location" : location,
        "detailLocation" : detailLocation,
        "date" : date,
        "done" : false or true,
        "planStateList" : [
            {
                "planStateId" : planStateId,
                "planId" : planId,
                "userId" : userId,
                "user" : {
                    "userId" : userId,
                    "nickName" : nickName,
                    "email" : email,
                    "point" : point,
                    "planStateList" : null,
                    "paymentList" : null
                },
                "plan" : {
                    "planId" : planId,
                    "admin" : adminUserId,
                    "deposit" : deposit,
                    "latePercent" : latePercent(0-100),
                    "absentPercent" : absent(0-100),
                    "title" : title,
                    "location" : location,
                    "detailLocation" : detailLocation,
                    "date" : date,
                    "done" : false or true,
                    "planStateList" : null
                },
                "lateState" : 0 or 1 or 2
            },
            ...
        ]
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

