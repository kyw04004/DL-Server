
**Show User**
----
  Returns json data about a single user.

* **URL**

  `api/v1/user/details/{userId}`

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
        "userId" : userId,
        "nickName" : nickName,
        "email" : email,
        "point" : point,
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
        ],
        "paymentList" : [
            {
                "paymentId" : paymentId,
                "money" : money,
                "point" : point,
                "date" : date,
                "userId" : userId,
                "user" : {
                    "userId" : userId,
                    "nickName" : nickName,
                    "email" : email,
                    "point" : point,
                    "planStateList" : null,
                    "paymentList" : null
                }
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

