# User Acceptance Tests

## Table of Contents

- [Test 1: Quick Registration](#test-1-quick-registration)
- [Test 2: Service Info & Terms Pinned](#test-2-service-info--terms-pinned)
- [Test 3: Navigation Menu](#test-3-navigation-menu)
- [Test 4: Handling User Questions with Confirmation Message](#test-4-handling-user-questions-with-confirmation-message)
- [Test 5: Submitting Tasks to Assistant](#test-5-submitting-tasks-to-assistant)

---

## Test 1: Quick Registration

**Link to Issue:** [GitLab Issue #1](https://gitlab.pg.innopolis.university/a.goncharov/rentassistant/-/issues/1)

### Acceptance Criteria (AC)

- **GIVEN** a user presses the “Start” button (/start)  
  **WHEN** they are not yet registered  
  **THEN** the bot asks them to provide their name and phone number  
  **AND** saves their Telegram ID, name, and phone number to the database

- **GIVEN** a user presses “Start” again  
  **WHEN** they are already registered  
  **THEN** the bot recognizes them  
  **AND** skips registration without creating a duplicate entry

- **GIVEN** a registered user accesses the bot  
  **WHEN** they use features that require account data  
  **THEN** the system correctly uses their stored Telegram ID, name, and phone number

### Task

“Please open the bot and press /start. If you're asked to register, enter your name and phone number. Then close and reopen the bot to check if you’re still recognized without registering again.”

### ✅ Live Result: Passed

**Notes:** Registration and session persistence worked correctly.

---

## Test 2: Service Info & Terms Pinned

**Link to Issue:** [GitLab Issue #17](https://gitlab.pg.innopolis.university/a.goncharov/rentassistant/-/issues/17)

### Acceptance Criteria (AC)

- **GIVEN** a user opens the bot for the first time  
  **WHEN** the welcome message appears  
  **THEN** a message with service info and terms is displayed above the chat  
  **AND** this message cannot be deleted by the user  
  **AND** it includes a “Документы” button to view the attached files

- **GIVEN** a user continues using the bot  
  **WHEN** they scroll or send messages  
  **THEN** the service info message remains accessible and is not accidentally removed

- **GIVEN** a user wants to review the service info again  
  **WHEN** they enter /policy  
  **THEN** the bot immediately re-sends the documents with the “Документы” button

### Task

“Please open the bot and check the first message that appears. Try scrolling, sending a message, and then type /policy to view the documents again.”

### ⚠️ Live Result: Partially passed

**Notes:** The pinned message was shown, but the /policy command didn’t trigger the document resend.

---

## Test 3: Navigation Menu

**Link to Issue:** [GitLab Issue #8](https://gitlab.pg.innopolis.university/a.goncharov/rentassistant/-/issues/8)

### Acceptance Criteria (AC)

- **GIVEN** the user opens the bot  
  **WHEN** the start message is shown  
  **THEN** the main inline menu appears with buttons for "О нас", "Тарифы", "Кейсы", "Свой вопрос"

- **GIVEN** the user clicks on any menu button  
  **WHEN** the bot receives the click  
  **THEN** the corresponding section is opened  
  **AND** the user sees the relevant content immediately

- **GIVEN** the user is inside any section  
  **WHEN** they click the "Back" button  
  **THEN** they are returned to the main menu without delay

### Task

“Please try using the menu buttons: ‘О нас’, ‘Тарифы’, ‘Кейсы’, and ‘Свой вопрос’. Then use the Back button to return to the main menu from each section.”

### ✅ Live Result: Passed

**Notes:** All menu buttons worked as expected, including Back navigation.

---

## Test 4: Handling User Questions with Confirmation Message

**Link to Issue:** [GitHub Issue #35](https://github.com/bestSWPteam/RentAssistantApplication/issues/35)

### Acceptance Criteria (AC)

- **GIVEN** a user sends a free-form message (question or request)  
  **WHEN** the message is received by the bot  
  **THEN** the bot replies with a message like “✅ Ваш вопрос получен! Мы скоро ответим.” within 1 second

- **GIVEN** the confirmation message is shown  
  **WHEN** the user is waiting  
  **THEN** the message makes it clear the request is passed to a human assistant

- **GIVEN** the system is idle  
  **WHEN** the user sends another question  
  **THEN** the confirmation logic is triggered again without duplication or spam

### Task

“Please send a free-form message in the bot, such as a question like ‘Can I change my subscription?’. Check if the bot replies right away with a confirmation message. Then try sending another similar question.”

### ✅ Live Result: Passed

**Notes:** The bot responded to free-form messages with immediate confirmation.

---

## Test 5: Submitting Tasks to Assistant

**Link to Issue:** [GitLab Issue #4](https://gitlab.pg.innopolis.university/a.goncharov/rentassistant/-/issues/4)

### Acceptance Criteria (AC)

- **GIVEN** a user wants to submit a task  
  **WHEN** they interact with the bot and follow the expected format  
  **THEN** the message is parsed and passed to the backend for validation

- **GIVEN** the user input is incomplete or invalid  
  **WHEN** the bot receives it  
  **THEN** the bot replies with an error message and instructions to correct the input

- **GIVEN** the task passes validation  
  **WHEN** it is stored in the database  
  **THEN** it is available for assistants to view in their task inbox

- **GIVEN** a task is successfully submitted  
  **WHEN** the process completes  
  **THEN** the user receives a confirmation message (e.g. “Ваша задача принята в обработку ✅”)

### Task

“Please try to submit a task like ‘Book a table for 2 tomorrow at 7PM’. If possible, try submitting an incomplete task too (e.g., just 'Book a table') and see what happens.”

### ✅ Live Result: Passed

**Notes:** The bot correctly handled valid and invalid task formats, with appropriate confirmations.
