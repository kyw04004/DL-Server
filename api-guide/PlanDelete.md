
**Plan Delete**
----
  Returns success message or error message.

* **URL**

  'api/v1/plan/delete/{planId}'

* **Method:**

  `DELETE`
  
*  **URL Params **
   
   **Required:**
   `planId=[Long]`
   
* **Data Params**

​		None  

* **Success Response:**

```json
{
	"status" : 200,
	"data": "delete coplete!!",
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

