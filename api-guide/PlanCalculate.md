
**Plan Calculate**
----
  Returns success message or error message.

* **URL**

  'api/v1/plan/calculate'

* **Method:**

  `PUT`
  
*  **URL Params **
   
   **Required:**
   None
   
* **Data Params**

```json
{
  "planId": 2,
  "jsonArray" : [
      {
          “userId”:1,“lateState”:0
      },
      {
          “userId”:2,“lateState”:2
      }
  ]
}
```



* **Success Response:**

```json
{
	"status" : 200,
	"data": true,
	"message" :"Success"
}
```

* **Error Response:**

```json
{
	"status" : 400,
	"data" : NULL,
	"message" :exception.getMessage()
}
```

  OR

  ```json
{
	"status" : 500,
	"data" : NULL,
	"message" :exception.getMessage()
}
  ```

