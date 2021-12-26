
**User Login**
----
  Returns json data about a userId.

* **URL**

  'api/v1/user/login'

* **Method:**

  `POST`
  
*  **URL Params**

   **Required:**

   None

* **Data Params**

```json
{
    "nickName" : nickName,
    "email" : email
}
```

  

* **Success Response:**

```json
{
	"status" : 200,
	"data": userId,
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

