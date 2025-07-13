# Tech Stack

This document describes the technologies used in the system: the Telegram Bot, Android App, and FastAPI backend.

---

## 1. **Backend API (FastAPI)**

- **Framework:** FastAPI
- **Language:** Python 3.11+
- **Database:** PostgreSQL (cloud-managed)
- **ORM:** SQLAlchemy 2.x (async support)
- **DB driver:** asyncpg
- **Environment config:** pydantic-settings + `.env`
- **HTTP Client:** httpx
- **Payments:** YooKassa SDK (`yookassa`)
- **Auth:**
  - JWT tokens for user sessions
  - `X-Auth-Key` header for admin-only routes
- **Endpoints:**
  - `/users/`, `/tasks/`, `/subscriptions/`, `/auth/`, `/assistant-statistics/`, `/yookassa/`
- **Shared packages:**
  - `pydantic` 2.x for schema validation
  - `PyYAML` for optional config parsing

---

## 2. **Telegram Bot**

- **Framework:** aiogram 3.20 (async, modern)
- **Language:** Python 3.11+
- **Bot Runner:** Polling (no webhooks)
- **HTTP Communication:** httpx (to backend)
- **No database connection:** all persistent logic is handled via backend
- **Environment config:** pydantic-settings
- **Payment flow:** via backend endpoints (`/yookassa/create-payment`)
- **Admin features:** available through the bot with admin key usage

---

## 3. **Android App**

- **Language:** Kotlin
- **Framework:** Jetpack Compose
- **Networking:**
  - `Retrofit` for REST API communication
  - `OkHttp` for low-level HTTP
- **Auth:**
  - Telegram WebApp login
  - Receives JWT token from backend (`/auth/status/{code}`)
- **Architecture Components:**
  - `ViewModel` for logic/state
  - `LiveData` or `StateFlow` for UI updates
  - `Navigation-Compose` for screen transitions
- **Image loading:** `Coil` (optional)
- **DI (if any):** likely `Hilt` or manual DI
- **Async:** Kotlin coroutines
- **Local Storage:** minimal or none — all data comes from the backend(SharedPreferences)

---

## 4. **Database (PostgreSQL)**

- **Connected only to:** FastAPI backend
- **Access via:** SQLAlchemy + asyncpg
- **Models include:**
  - `User`, `Task`, `Subscription`, `AssistantStatistics`, `PaymentLog`
- **All access is async** and performed in service/crud layers

---

## 5. **Payment Gateway (YooKassa)**

- **SDK:** yookassa 3.5
- **Flow:**
  - API sends HTTPS request to YooKassa
  - YooKassa handles confirmation and redirect
- **Integration used in both:**
  - Telegram bot (through backend)
  - Android App (via backend as well)

---

## 6. **Common Python Libraries**

These libraries are used in both bot and backend:

| Library           | Purpose                                |
|-------------------|----------------------------------------|
| `aiogram`         | Async Telegram bot framework           |
| `httpx`           | Async HTTP client                      |
| `SQLAlchemy 2.x`  | Async ORM                              |
| `asyncpg`         | High-performance PostgreSQL driver     |
| `pydantic`        | Data validation and typing             |
| `pydantic-settings` | Config via `.env`                    |
| `yookassa`        | Payment gateway SDK                    |
| `PyYAML`          | Config parsing (optional)              |

---



---

## Summary
The system consists of four main components that work together to provide task delegation, subscription management, and payment processing:

## Telegram Bot
A fully interactive interface written in Python using the aiogram framework. It serves as a primary user and admin access point, communicating with the backend API via httpx. It processes commands, validates input, handles flow logic, and performs privileged operations using a secure X-Auth-Key header. The bot does not interact with the database directly, instead delegating all persistent operations to the backend.

## Android Application
Developed in Kotlin with Jetpack Compose, the Android app provides an alternative client interface. It communicates with the backend via Retrofit over HTTPS. Authentication is handled via Telegram-based JWT login. While the app is designed to mimic the bot’s capabilities, it focuses more on user interaction and payment workflows.

## Backend API (FastAPI)
Acts as the central logic layer. Built with FastAPI and asynchronous SQLAlchemy, it manages user data, tasks, subscriptions, and payment sessions. It exposes secured endpoints for both the app and the bot, validates and executes business logic, and connects to external services such as YooKassa. Admin-level access is controlled via an X-Auth-Key, while standard clients use JWTs.

## PostgreSQL Database
A managed cloud database accessed asynchronously from the backend via asyncpg. It stores all persistent data — including users, tasks, subscriptions, assistant stats, and payment records. All read/write operations are encapsulated in a modular CRUD layer to ensure consistency and maintainability.

