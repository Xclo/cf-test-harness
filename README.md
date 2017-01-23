# pcf-test-harness
Test Harness for PCF Platform for App Developers using Concourse

## Objective
Create a set of test apps, targeting specific buildpacks, services in PCF and test the platform's availability for app Developers

## List of Apps (add to this list)
- Java
- Spring Boot
- Nodejs
- PHP
- Python
- Ruby
- Go
- Spring with my-SQL
- Spring with Redis
- Spring with RabbitMQ
- Spring with SSO
- Add to the list...


## Test Methodology

Add your test app to this repository and update the Concourse Pipeline. The Concourse pipeline can be targeted to a specific PCF Deployment and it will report back for each of the app if the test passed or failed using a uniform rest endpoint /cftest/{test-type}

For e.g.
- Java App - able to deploy the java app and run the app using the buildpack specified in the Manifest /cftest/status -> Up/Down
- Java App - able to deploy the java app and scale up and down the app /cftest/scale
- mySQL CRUD - /cftest/crud
