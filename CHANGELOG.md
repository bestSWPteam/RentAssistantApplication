# Changelog

All notable changes to this project will be documented in this file.

## [2.5.0] - 2024-07-11

### Features

-   Created TasksList component
-   Added visual improvements to TasksScreen.kt and connected it to MainActivity
-   Updated SubscriptionChoosingScreen.kt

### Bug Fixes

-   Fixed a bug in function calling
-   Reloaded an old, stable version of SubscriptionChoosingScreen.kt

### CI/CD

-   Added and configured the main CI workflow
-   Introduced pull request checks
-   Added Docker support for the project
-   Integrated a checker for security vulnerabilities
-   Added the deployment step to the pipeline
-   Performed multiple fixes and improvements to the CI/CD pipeline

### Documentation & Project Structure

-   Created `CONTRIBUTING.md`
-   Created `pull_request_template.md` and issue templates (`user-story.yml`, `bug-report.yml`, `technical-task.yml`)
-   Created `quality-attribute-scenarios.md`
-   Created and detailed the architecture documentation (static, dynamic, and deployment views)
-   Added component and sequence diagrams (UML) to architecture docs
-   Added user acceptance tests to the QA documentation
-   Performed numerous updates to `README.md`

### Tests

-   Added initial files for unit and integration tests

---

## [2.0.1] - 2025-07-03

### Features

-   Created SuccessPurchaseScreen.kt with interactive elements
-   Created a scrollable UsersScreen
-   Created the main menu screen with all necessary functionality
-   Created and integrated a BottomNavigationBar with main screens
-   Created a Filter menu for dynamic task rendering
-   Implemented initial support for Telegram OAuth authorization
-   Added payment integration with YuKassa

### Bug Fixes

-   Resolved issues with file locations and naming collisions in preview functions

### Refactoring

-   Reorganized UI files structure
-   Refactored Composable functions to accept logic as parameters
-   Excluded redundant imports

### Tests

-   Added tests covering the 5 most critical unit scenarios and 5 key navigation paths

---

## [1.0.1] - 2025-06-22

### Features

-   Initial project commit and setup
-   Created WelcomeScreen.kt with a logotype
-   Created a raw version of the Starting Screen with a clickable "Enter" button
-   Created a valid and scrollable Subscription Screen
-   Created a ConfirmationSubscriptionMenu.kt with clickable buttons
-   Resolved several UI issues on the Subscription Screen
