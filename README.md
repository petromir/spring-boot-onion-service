# Spring Boot project using Onion architecture

A production-ready example of a Spring Boot project using Onion architecture. 

> [!WARNING]
> Under active development 🚧

## Domain
HomeOps is a household management platform focusing on invoices, warranties, maintenance services, and vehicle tracking. Built as a modular monolith using Onion architecture, it demonstrates production-ready Spring Boot patterns.

## Design

### Architecture
- **Style:** Modular Monolith with Onion Architecture
- **Framework:** Spring Modulith for module boundaries
- **Target Audience:** Junior to Senior Java developers

### Modules

| Module                    | Responsibility                                            |
|---------------------------|-----------------------------------------------------------|
| `user-management`         | Authentication, user profiles, household membership       |
| `asset-management`        | Appliances, categories, merchants                         |
| `vehicle-management`      | Vehicles and fuel logs                                    |
| `warranty-management`     | Warranty tracking and expiry alerts                       |
| `invoice-management`      | Purchase and service invoices with line items             |
| `service-management`      | Service records, maintenance schedules, service providers |
| `notification-management` | Notification generation and delivery                      |
| `document-management`     | File upload/download via S3                               |

### Core Entities

#### User Management

| Entity      | Description                            |
|-------------|----------------------------------------|
| `User`      | Account, authentication, profile       |
| `Household` | Family/unit grouping with member roles |

#### Asset Management

| Entity      | Description                                                              |
|-------------|--------------------------------------------------------------------------|
| `Appliance` | Physical asset (make, model, serial, purchase date) linked to a merchant |
| `Category`  | Classification (kitchen, HVAC, plumbing, electrical)                     |
| `Merchant`  | Seller/retailer where appliances are purchased                           |

#### Vehicle Management

| Entity    | Description                                                |
|-----------|------------------------------------------------------------|
| `Vehicle` | Type, year, make, model, plate, VIN, nickname              |
| `FuelLog` | Gas refills: date, station, volume, cost, odometer reading |

#### Warranty Management

| Entity     | Description                                                           |
|------------|-----------------------------------------------------------------------|
| `Warranty` | Coverage linked to an appliance with start/end dates, terms, provider |

#### Invoice Management

| Entity    | Description                                                              |
|-----------|--------------------------------------------------------------------------|
| `Invoice` | Purchase or service invoice linked to appliance/merchant with line items |

#### Service Management

| Entity            | Description                                                           |
|-------------------|-----------------------------------------------------------------------|
| `ServiceRecord`   | Maintenance/repair event: date, provider, cost, notes, parts replaced |
| `ServiceSchedule` | Recurring maintenance plan with frequency and conditions              |
| `ServiceProvider` | External company or contractor                                        |

#### Notification Management

| Entity         | Description                                                       |
|----------------|-------------------------------------------------------------------|
| `Notification` | Generated from ServiceSchedule triggers (due, overdue, completed) |

#### Document Management

| Entity     | Description                                                        |
|------------|--------------------------------------------------------------------|
| `Document` | Uploaded files linked to any entity (manuals, receipts, contracts) |

### Domain Events (Kafka)

| Event                                                                            | Trigger                |
|----------------------------------------------------------------------------------|------------------------|
| `WarrantyCreated`, `WarrantyExpiring`, `WarrantyExpired`                         | Warranty lifecycle     |
| `InvoiceCreated`, `InvoiceUploaded`, `InvoiceProcessed`                          | Invoice lifecycle      |
| `ServiceRecordCreated`, `ServiceScheduled`, `ServiceCompleted`, `ServiceOverdue` | Maintenance lifecycle  |
| `NotificationCreated`, `NotificationSent`, `NotificationRead`                    | Notification lifecycle |
| `FuelLogCreated`                                                                 | Gas refill recorded    |
| `VehicleAdded`, `VehicleDecommissioned`                                          | Vehicle lifecycle      |
| `ApplianceAdded`, `ApplianceDecommissioned`                                      | Asset lifecycle        |
| `DocumentUploaded`, `DocumentLinked`                                             | File management        |
| `HouseholdMemberAdded`, `HouseholdMemberRemoved`                                 | Membership changes     |

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
| Modular Boundaries | Spring Modulith                      |

## Support my work

<a href="https://ko-fi.com/petromirdzhunev" target="_blank"><img src="https://raw.githubusercontent.com/petromir/petromir/refs/heads/master/assets/kofi-button.svg" alt="Buy Me A Ko-fi" style="height: 45px !important;width: 163px !important;" ></a>
<a href="https://www.buymeacoffee.com/petromirdzhunev" target="_blank"><img src="https://raw.githubusercontent.com/petromir/petromir/refs/heads/master/assets/bmc-button.svg" alt="Buy Me A Coffee" style="height: 45px !important;width: 163px !important;" ></a>
<a href="https://github.com/sponsors/petromir" target="_blank"><img src="https://raw.githubusercontent.com/petromir/petromir/refs/heads/master/assets/github-sponsor-button.svg" alt="GitHub Sponsor" style="height: 45px !important;width: 163px !important;" ></a>
