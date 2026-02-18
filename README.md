# Apache Ignite PoC (Java 21)

Minimal Maven project to run a local Apache Ignite node and perform a cache put/get.

## Prerequisites
- Java 21
- Maven

## Run
- Build: mvn -q -DskipTests package
- Run: mvn -q -DskipTests exec:java

## Java 21 module access
The project includes [.mvn/jvm.config](.mvn/jvm.config) to open `java.nio` for Ignite.

## Salesforce cache loader
The `userDetails` cache is configured with a read-through cache loader that fetches user details from Salesforce.

Environment variables:
- `SF_BASE_URL` (example: https://yourInstance.my.salesforce.com)
- `SF_API_VERSION` (default: v59.0)
- `SF_AUTH_TOKEN` (OAuth bearer token)
- `SF_USER_ID` (default: 005000000000000)

## What it does
- Starts a local Ignite node.
- Creates/opens a cache named `myCache`.
- Puts and gets a value, then prints it.
- Loads a Salesforce user into `userDetails` cache (mocked if not configured).
