# Ignite PoC High-Level Block Diagram

This diagram gives a clean overview of the PoC architecture for presentations.

```mermaid
flowchart LR
    A[Application Entry
IgniteQuickStart] --> B[Ignite Client Node]

    B --> C[Core Cache
myCache]
    B --> D[Salesforce Cache
userDetails]
    B --> E[Postgres Cache
postgresUserDetails]

    D --> F[Salesforce Module]
    F --> G[Salesforce API or Mock]

    E --> H[Postgres Module]
    H --> I[Mock Postgres Client]

    B --> J[Ignite Server Nodes]
    J --> K[Compute Task Broadcast]
```

## Presenter Notes

- The app runs as an Ignite client node.
- `myCache` shows in-memory cache behavior.
- `userDetails` and `postgresUserDetails` show read-through cache integration.
- Provider-specific logic is isolated in separate Salesforce and Postgres modules.
- Compute tasks run on Ignite server nodes.
