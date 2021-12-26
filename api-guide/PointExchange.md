
**Point Exchange**
----
  Returns success message or error message.

* **URL**

  'api/v1/finance/exchange'

* **Method:**

  `POST`
  
*  **URL Params**

   **Required:**

   None

* **Data Params**

```json
{
    "userId" : userId,
    "bank" : bank,
    "account" : account,
    "point" : point,
    "name" : name
}
```

  

* **Success Response:**

```json
{
	"status" : 200,
	"data": "Exchange request complete",
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

