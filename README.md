## Project Build
1. Run `mvn clean install`

## Running the application
1. Run `src/main/java/PriceFormatterApplication` in your favorite IDE
or run `java -jar [build jar]`
2. Access Swagger via http://localhost:8080/swagger-ui.html#/
- @POST /api/v1/price/formatTrimZero - This is to cater  for “Additional rule(s)” in the exam.
- @POST /api/v1/price/formatZero - This formats with trailing zero
3. Sample JSON request and response

- Note on diplayFormat
  - DECIMAL=0, 
  - PERCENTAGE=1, 
  - PERMILE=2, 
  - BASISPOINT=3
- JSON request
```json
{
  "config": {
    "displayFormat": 0, 
    "dpl": 2,
    "fpl": 3,
    "scale": 4
  },
  "price": 47.92
}
```
- JSON response
```json
{
  	"bigFigure": "16.7",
  	"dealingPrice": "5",
  	"fractionalPips": "40"
}
```
