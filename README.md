# Apache Ignite PoC (Java 17)

Apache Ignite proof-of-concept showing read-through caching with two independent data source modules:

- Salesforce module (`com.example.ignitepoc.salesforce.*`)
- Postgres module (`com.example.ignitepoc.postgres.*`)

## Prerequisites

- Java 17
- Maven

## Run

- Build: `mvn clean package`
- Run: `mvn -q -DskipTests exec:java`

## What it demonstrates

- Ignite client node startup and cache operations (`myCache`).
- Salesforce read-through cache (`userDetails`).
- Postgres read-through cache (`postgresUserDetails`, currently mock-backed for demo stability).
- Compute task broadcast to server nodes.

## Configuration

### Salesforce

- `SF_BASE_URL`
- `SF_API_VERSION` (default: `v59.0`)
- `SF_AUTH_TOKEN`
- `SF_USER_ID` (default: `005000000000000`)

If Salesforce vars are missing, the client returns mock data.

### Postgres

- `POSTGRES_HOST` (default: `localhost`)
- `POSTGRES_PORT` (default: `5432`)
- `POSTGRES_DB` (default: `ignite_demo`)
- `POSTGRES_USER` (default: `ignite_user`)

Current Postgres client is mock-based for demo purposes.

## Documentation

- Demo flow and presenter notes: [docs/DEMO_GUIDE.md](docs/DEMO_GUIDE.md)
