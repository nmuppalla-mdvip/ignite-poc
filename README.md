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
- `SF_OAUTH_TOKEN` (used to fetch `Id,Name`)
- `SF_SERVICE_TOKEN` (used to fetch `Email`)
- `SF_AUTH_TOKEN` (legacy fallback for `SF_OAUTH_TOKEN`)
- `SF_MOCK_MODE` (default: `true`; set to `false` to use real Salesforce HTTP calls)
- `SF_USER_ID` (default: `005000000000000`)

By default, Salesforce calls are mocked for demo stability while still showing split-token flow (`Id,Name` via OAuth path and `Email` via service-token path).

### Postgres

- `POSTGRES_HOST` (default: `localhost`)
- `POSTGRES_PORT` (default: `5432`)
- `POSTGRES_DB` (default: `ignite_demo`)
- `POSTGRES_USER` (default: `ignite_user`)

Current Postgres client is mock-based for demo purposes.

## Documentation

- Demo flow and presenter notes: [docs/DEMO_GUIDE.md](docs/DEMO_GUIDE.md)
- Leadership decision framework and presentation flow: [docs/LEADERSHIP_DECISION_PLAYBOOK.md](docs/LEADERSHIP_DECISION_PLAYBOOK.md)
