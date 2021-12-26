
**User Delete**
----
  Returns success message or error message.

* **URL**

  'api/v1/user/delete/{userId}'

* **Method:**

  `DELETE`
  
*  **URL Params**

   **Required:**

   `userId=[Long]`

* **Data Params**

​		None

  

* **Success Response:**

```json
{
	"status" : 200,
	"data": "Delete user",
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

