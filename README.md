**Microservice Project Backend**

This repository contains a small Spring Boot microservices backend built with Spring Cloud and Maven. It includes a service registry, configuration server, API gateway and three microservices (user, hotel, rating). The services use Spring Cloud features for discovery, configuration and routing.

**Architecture**
- **Project:**: `microservice_project` (root)
- **Modules:**: `ServiceRegistory`, `ConfigServer`, `ApiGateway`, `UserService`, `HotelService`, `RatingService`
- **Discovery:**: Eureka running in `ServiceRegistory` (default port `8762`).
- **Config:**: Spring Cloud Config Server in `ConfigServer` (default port `8085`) — configuration is loaded from the git repo configured in `ConfigServer`.
- **Gateway:**: Spring Cloud Gateway in `ApiGateway` (default port `8084`) routes requests to backend services.

**Services & Ports**
- **Service Registry (Eureka):**: `ServiceRegistory` — `server.port: 8762`
- **Config Server:**: `ConfigServer` — `server.port: 8085`
- **API Gateway:**: `ApiGateway` — `server.port: 8084`
- **User Service:**: `UserService` — `server.port: 8081`
- **Hotel Service:**: `HotelService` — `server.port: 8082`
- **Rating Service:**: `RatingService` — `server.port: 8083`

Note: Ports are defined in each service's `src/main/resources/application.yml`. If you change a port there, update any scripts or documentation accordingly.

**Prerequisites**
- **Java:**: JDK 21 (see `<module>/pom.xml` for `<java.version>`; this repo uses Java 21)
- **Maven wrapper:**: `mvnw` / `mvnw.cmd` are included — you can use the wrapper to run builds without a local Maven install.
- **Databases:**: MySQL server running locally (or update connection strings in each service's `application.yml`).

Databases used by services (default credentials are set in code/config):
- `UserService` datasource: `jdbc:mysql://localhost:3306/microservice` (user: `root`, password: `cdac`)
- `HotelService` datasource: `jdbc:mysql://localhost:3306/hotelservicedatabase` (user: `root`, password: `cdac`)
- `RatingService` datasource: `jdbc:mysql://localhost:3306/ratingservicedatabase` (user: `root`, password: `cdac`)

Minimal SQL to create the databases (run in MySQL):

```sql
CREATE DATABASE microservice;
CREATE DATABASE hotelservicedatabase;
CREATE DATABASE ratingservicedatabase;
-- Optionally create a user and grant privileges
-- CREATE USER 'svcuser'@'localhost' IDENTIFIED BY 'password';
-- GRANT ALL PRIVILEGES ON microservice.* TO 'svcuser'@'localhost';
```

**Run order (recommended)**
1. Start `ServiceRegistory` (Eureka) so services can register.
2. Start `ConfigServer` so services can read centralized configuration.
3. Start backend services: `UserService`, `HotelService`, `RatingService` (any order).
4. Start `ApiGateway` last (it routes traffic to services through discovery).

You can run each module from PowerShell using the Maven wrapper. Open separate terminals for each service and run:

```powershell
cd .\ServiceRegistory
.\mvnw.cmd spring-boot:run

cd ..\ConfigServer
.\mvnw.cmd spring-boot:run

cd ..\UserService
.\mvnw.cmd spring-boot:run

cd ..\HotelService
.\mvnw.cmd spring-boot:run

cd ..\RatingService
.\mvnw.cmd spring-boot:run

cd ..\ApiGateway
.\mvnw.cmd spring-boot:run
```

Or build jars and run them:

```powershell
cd .\UserService
.\mvnw.cmd clean package
java -jar target\UserService-0.0.1-SNAPSHOT.jar
```

**Gateway routes (from `ApiGateway/src/main/resources/application.yml`)**
- Requests are proxied by the gateway to services using logical names (via load-balanced URIs):
- `/users/**` -> `User-Service` (UserService)
- `/hotels/**` and `/staffs/**` -> `Hotel-Service` (HotelService)
- `/ratings/**` -> `Rating-Service` (RatingService)

So, to get a user via the gateway you can call:

```powershell
curl http://localhost:8084/users/{id}
```

Or call a service directly (example for UserService):

```powershell
curl http://localhost:8081/users/{id}
```

**Configuration**
- `ConfigServer` pulls configs from the Git repository configured in `ConfigServer/src/main/resources/application.yml` (see `spring.cloud.config.server.git.uri`).
- Individual services also have local `application.yml` under `src/main/resources` which can override or provide defaults.

**Service discovery & health**
- Eureka dashboard: `http://localhost:8762` (browse to view registered instances)
- Actuator endpoints are enabled in some services (check `management` config in `application.yml`). Example: `http://localhost:8081/actuator/health`.

**Development notes**
- The projects use Spring Cloud dependencies and Spring Boot 4.x.
- Resilience4j is configured in `UserService` for circuit breaking and retries (see `resilience4j` section in `application.yml`).
 

**Common troubleshooting**
- If services fail to start because of config: verify `ConfigServer` is reachable and the Git repo contains correct config files.
- If services cannot register with Eureka: verify `ServiceRegistory` is running on `8762` and services point to the correct Eureka URL in their `application.yml`.
- Database connection errors: ensure MySQL is running and credentials in `application.yml` are correct.

**Authors & Links**
- Project owner: repository configured by `faisalkhan-pathan-0100` in the `ConfigServer` git settings.
- Config Git: check `ConfigServer/src/main/resources/application.yml` for the configured Git repository URL.

 
 
