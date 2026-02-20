# Ignite PoC High-Level Block Diagram

This diagram gives a clean overview of the PoC architecture for presentations.

```mermaid
flowchart LR
    A[Application Entry
IgniteQuickStart] --> B[Ignite Client Node]

    B --> R1[Cache Operations]
    B --> R2[Compute Broadcast]

    subgraph CLUSTER[Ignite Server Cluster]
        subgraph J1[Ignite Server Node 1]
            J1A[myCache]
            J1B[userDetails Cache]
            J1C[postgresUserDetails Cache]
            J1D[Salesforce CacheStore]
            J1E[Postgres CacheStore]
            J1F[Compute Engine]
            J1B --- J1D
            J1C --- J1E
        end

        subgraph J2[Ignite Server Node 2]
            J2A[myCache]
            J2B[userDetails Cache]
            J2C[postgresUserDetails Cache]
            J2D[Salesforce CacheStore]
            J2E[Postgres CacheStore]
            J2F[Compute Engine]
            J2B --- J2D
            J2C --- J2E
        end

        subgraph J3[Ignite Server Node 3]
            J3A[myCache]
            J3B[userDetails Cache]
            J3C[postgresUserDetails Cache]
            J3D[Salesforce CacheStore]
            J3E[Postgres CacheStore]
            J3F[Compute Engine]
            J3B --- J3D
            J3C --- J3E
        end
    end

    R1 --> CLUSTER
    R2 --> CLUSTER

    J1D --> F[Salesforce Module]
    J2D --> F
    J3D --> F
    F --> G[Salesforce API or Mock]

    J1E --> H[Postgres Module]
    J2E --> H
    J3E --> H
    H --> I[Mock Postgres Client]
```

## Presenter Notes

- The app runs as an Ignite client node.
- The client node only sends cache requests and compute broadcasts.
- All server-owned components are inside the Ignite Server Cluster.
- Each server node contains myCache, read-through caches, cache stores, and compute engine.
- Salesforce and Postgres modules are reached through server-side cache stores.
