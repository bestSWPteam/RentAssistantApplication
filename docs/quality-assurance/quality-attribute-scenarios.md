# Quality Attribute Scenarios

## Reliability

### Availability

**Why it is important:**  
Our Telegram bot and application are key interfaces for user interaction. If the system is down — even for a short time — users won’t be able to ask questions, get assistance, or complete important tasks.  
Since our project simulates a helpful assistant, availability directly impacts user trust, experience, and perceived quality.

**Scenario:**

- **Source of stimulus**: User
- **Stimulus**: Attempts to access the bot or app
- **Environment**: Normal operational conditions
- **Stimulated artifact**: Backend API and Telegram bot
- **Response**: The system responds and performs the intended operation
- **Response measure**: Uptime ≥ 99.5% per 30-day period (max ~3h 39min of downtime)

**How we plan to test it:**  
Use external monitoring services to send pings to backend and bot endpoints every 5 minutes. Track uptime/downtime and configure alerts and self-recovery if outages occur.

---

## Interaction Capability

### User Assistance

**Why it is important:**  
Many users interact with our service in a rush or while multitasking. They should not waste time figuring out how to create a task or access documents. Clear guidance increases user satisfaction and reduces friction.

**Scenario:**

- **Source of stimulus**: First-time user
- **Stimulus**: Tries to create a task using the bot/app
- **Environment**: Initial use, no prior instructions
- **Stimulated artifact**: Telegram bot interface (menu buttons, messages)
- **Response**: The user successfully completes task creation
- **Response measure**: Task submitted in ≤ 1 minute using only provided UI, without external help

**How we plan to test it:**  
Conduct usability testing with 3 new users. Ask them to create a task with no prior explanation. Measure time and record confusion points. If the average completion time >1 minute or usability issues appear, we’ll improve the flow.

---

## Flexibility

### Adaptability

**Why it is important:**  
RentAssistant is an evolving service. Clients may request new task types (e.g., “Translate a document”) or additional features such as multi-language support. The system must be able to respond to these changes quickly to stay relevant and scalable.

**Scenario:**

- **Source of stimulus**: Product manager
- **Stimulus**: Requests a new task type to be added
- **Environment**: Design and development phase
- **Stimulated artifact**: Backend task routing, Telegram bot, Android app UI
- **Response**: Feature is implemented, tested, and deployed
- **Response measure**: Time-to-deployment ≤ 1 working day for new task type to appear and function in production

**How we plan to test it:**  
Simulate a real client request. Develop and test a new task category in a feature branch. Measure total time from coding start to availability in both UIs (bot and app). Include test coverage and deployment validation.

