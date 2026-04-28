# Spring Boot service using Onion architecture

A production-ready example of a Spring Boot service using Onion architecture. 

> [!WARNING]
> Under active development 🚧.

## Domain
`Homie` is a household management platform focusing on invoices, warranties, maintenance services, vehicles and more.
It attempts to provide various `standard` business cases, so they could be copy-pasted easily into your projects.
Why this domain? Simple - I want to build something that I actually use, and not just hypothetical use cases.

### Architecture
The application showcases a modular monolith with Onion architecture

<img src="https://raw.githubusercontent.com/petromir/spring-boot-onion-service/refs/heads/master/docs/onion-architecture.png" alt="Onion Architecture" width="600" height="600">

## Design

### Modules

| Module        | Responsibility                                            |
|---------------|-----------------------------------------------------------|
| `users`       | Authentication, user profiles, household membership       |
| `assets`      | Appliances, categories, merchants                         |
| `vehicles`    | Vehicles and fuel logs                                    |
| `warranties`  | Warranty tracking and expiry alerts                       |
| `invoices`    | Purchase and service invoices with line items             |
| `services`    | Service records, maintenance schedules, service providers |
| `notifications` | Notification generation and delivery                    |
| `documents`   | File upload/download via S3                               |

#### Structure
root/
├── modules/
│   ├── users/
│   │   ├── domain/    # Entities, repository interfaces, domain events
│   │   ├── app/       # Use cases, port interfaces, DTOs
│   │   └── infra/     # Repository impl, Kafka, S3 clients, REST controllers
│   ├── assets/
│   │   └── ...        # Same onion layers
│   ├── vehicles/
│   ├── warranties/
│   ├── invoices/
│   ├── services/
│   ├── notifications/
│   └── documents/
└── application/       # Bootstrapping, configuration, security

### Core Entities

#### Users

| Entity      | Description                            |
|-------------|----------------------------------------|
| `User`      | Account, authentication, profile       |
| `Household` | Family/unit grouping with member roles |

#### Assets

| Entity      | Description                                                              |
|-------------|--------------------------------------------------------------------------|
| `Appliance` | Physical asset (make, model, serial, purchase date) linked to a merchant |
| `Category`  | Classification (kitchen, HVAC, plumbing, electrical)                     |
| `Merchant`  | Seller/retailer where appliances are purchased                           |

#### Vehicles

| Entity    | Description                                                |
|-----------|------------------------------------------------------------|
| `Vehicle` | Type, year, make, model, plate, VIN, nickname              |
| `FuelLog` | Gas refills: date, station, volume, cost, odometer reading |

#### Warranties

| Entity     | Description                                                           |
|------------|-----------------------------------------------------------------------|
| `Warranty` | Coverage linked to an appliance with start/end dates, terms, provider |

#### Invoices

| Entity    | Description                                                              |
|-----------|--------------------------------------------------------------------------|
| `Invoice` | Purchase or service invoice linked to appliance/merchant with line items |

#### Services

| Entity            | Description                                                           |
|-------------------|-----------------------------------------------------------------------|
| `ServiceRecord`   | Maintenance/repair event: date, provider, cost, notes, parts replaced |
| `ServiceSchedule` | Recurring maintenance plan with frequency and conditions              |
| `ServiceProvider` | External company or contractor                                        |

#### Notifications

| Entity         | Description                                                       |
|----------------|-------------------------------------------------------------------|
| `Notification` | Generated from ServiceSchedule triggers (due, overdue, completed) |

#### Documents

| Entity     | Description                                                        |
|------------|--------------------------------------------------------------------|
| `Document` | Uploaded files linked to any entity (manuals, receipts, contracts) |

### Domain Events (Kafka)

| Event                      | Trigger                |
|----------------------------|------------------------|
| `WarrantyCreated`          | Warranty created       |
| `WarrantyExpiring`         | 90 days before expiry  |
| `WarrantyExpired`          | Warranty expiry date   |
| `InvoiceCreated`           | Invoice recorded       |
| `InvoiceUploaded`          | File attached          |
| `InvoiceProcessed`         | Payment/approval       |
| `ServiceRecordCreated`     | Maintenance completed  |
| `ServiceScheduled`         | Recurring schedule hit |
| `ServiceCompleted`         | Service work done      |
| `ServiceOverdue`           | Past due date          |
| `NotificationCreated`      | Notification generated |
| `NotificationSent`         | Notification delivered |
| `NotificationRead`         | User acknowledged      |
| `FuelLogCreated`           | Gas refill recorded    |
| `VehicleAdded`             | Vehicle registered     |
| `VehicleDecommissioned`    | Vehicle removed        |
| `ApplianceAdded`           | Appliance registered   |
| `ApplianceDecommissioned`  | Appliance removed      |
| `DocumentUploaded`         | File stored in S3      |
| `DocumentLinked`           | File attached to entity|
| `HouseholdMemberAdded`     | User joined household  |
| `HouseholdMemberRemoved`   | User left household    |

### Caching Strategy (Redis)

| Cached Data                                 | Purpose                               |
|---------------------------------------------|---------------------------------------|
| Household membership & roles                | Checked on every request              |
| Active warranties (expiring within 90 days) | Dashboard widget                      |
| Upcoming service schedules (next 30 days)   | Dashboard & notifications             |
| Appliance catalog (make/model lookup)       | Infrequently changed, frequently read |
| Service provider directory                  | Lookup during service record creation |
| Invoice totals by category/month            | Dashboard aggregates                  |

### JSONB Storage (PostgreSQL)

| Entity            | Column           | Content                                          |
|-------------------|------------------|--------------------------------------------------|
| `Appliance`       | `specifications` | Dimensions, energy rating, capacity, voltage     |
| `Warranty`        | `terms`          | Coverage details, exclusions, claim process      |
| `Invoice`         | `lineItems`      | Description, quantity, unit price, tax, category |
| `Invoice`         | `metadata`       | PO number, payment method, vendor details        |
| `ServiceRecord`   | `workPerformed`  | Work description, parts used, before/after notes |
| `ServiceSchedule` | `rules`          | Recurrence frequency, conditions, checklists     |
| `Household`       | `settings`       | Currency, timezone, notification preferences     |

### File Storage (S3)

| File Type              | Linked To       |
|------------------------|-----------------|
| Purchase receipts      | Invoice         |
| Warranty certificates  | Warranty        |
| Product manuals (PDF)  | Appliance       |
| Service invoices       | ServiceRecord   |
| Before/after photos    | ServiceRecord   |
| Contracts & agreements | ServiceProvider |
| Appliance photos       | Appliance       |

### Technology Stack

| Feature            | Technology                           |
|--------------------|--------------------------------------|
| REST API           | Spring Web + OpenAPI code generation |
| Authentication     | Spring Security + JWT                |
| Database           | PostgreSQL + jOOQ + Liquibase        |
| JSONB              | jOOQ JSONB support                   |
| Caching            | Spring Cache + Redis                 |
| Messaging          | Spring Kafka                         |
| File Storage       | AWS SDK S3                           |
| Web Clients        | Spring RestClient                    |
| Modular Boundaries | Spring Modulith (enforced via `ModuleTest` at test time) |

## Support my work

<a href="https://ko-fi.com/petromirdzhunev" target="_blank"><img src="https://raw.githubusercontent.com/petromir/petromir/refs/heads/master/assets/kofi-button.svg" alt="Buy Me A Ko-fi" style="height: 45px !important;width: 163px !important;" ></a>
<a href="https://www.buymeacoffee.com/petromirdzhunev" target="_blank"><img src="https://raw.githubusercontent.com/petromir/petromir/refs/heads/master/assets/bmc-button.svg" alt="Buy Me A Coffee" style="height: 45px !important;width: 163px !important;" ></a>
<a href="https://github.com/sponsors/petromir" target="_blank"><img src="https://raw.githubusercontent.com/petromir/petromir/refs/heads/master/assets/github-sponsor-button.svg" alt="GitHub Sponsor" style="height: 45px !important;width: 163px !important;" ></a>
