
**Plan Allocate**
----
  Returns success message or error message.

* **URL**

  'api/v1/finance/payment/allocate'

* **Method:**

  `POST`
  
*  **URL Params**

   **Required:**

   None

* **Data Params**

```json
{
    "planId" : planId,
    "option" : 0(1/n) or 1(to only one) or 2(to half)
}
```

  

* **Success Response:**

```json
{
	"status" : 200,
	"data": "allocate complete",
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

