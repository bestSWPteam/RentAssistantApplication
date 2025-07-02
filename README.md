The application to rent an assistant, created by Innopolis University students within the Software Project course.

Used stack:
- Kotlin
- Jetpack Compose

## Development

### Kanban board
We use a GitHub Project (Kanban board) to manage all development tasks.  
🔗 [View our Kanban board](https://github.com/orgs/bestSWPteam/projects/2)

#### Entry Criteria per Column
#### 🟣 ToDo: Ass
Tasks related to assignment logistics and reporting.
**Entry criteria:** Task is created for sprint tracking, reporting, quality, or documentation.

#### 🔴 ToDo: UX/UI
UI/UX specific improvements.
**Entry criteria:** Feature/design-related task that needs UX research, prototyping or UI implementation.

#### 🔵 ToDo: App Backend
Backend-related tasks including APIs, auth, messaging, deployment.
**Entry criteria:** Task is backend-focused, clearly scoped, assigned, and ready to begin.

#### 🟡 ToDo: App Frontend
Frontend-related tasks for the app UI (Android).
**Entry criteria:** Task is frontend-focused, design is approved (if applicable), and it’s ready for implementation.

#### 🟢 ToDo: Bot
Bot feature development.
**Entry criteria:** Task is related to bot logic, requirements are clarified, and it’s ready to be picked up.

#### 🟣 Done 
Tasks that are fully completed and meet the [Definition of Done](https://github.com/bestSWPteam/RentAssistantApplication/blob/main/CONTRIBUTING.md).

**Entry criteria:**  
The task must satisfy **all** of the following:
- All features work as intended.  
- No critical bugs or issues remain after testing.  
- Code is committed and pushed to the repository.  
- Discussed and approved by the team lead in Telegram.  
- Moved manually to the **Close** column on the board.

### Git workflow

We chose **GitHub Flow** because it is:
- Simple and developer-friendly.
- Suitable for short sprints with many small tasks.
- Focused on quality through pull requests and reviews.
- Fully supported by GitHub’s interface.

It allows us to move fast without sacrificing stability or collaboration.

#### Creating Issues from Templates

We use our issue templates to ensure consistency and clarity across all tasks.

Our templates:
- [Bug Report](https://github.com/bestSWPteam/RentAssistantApplication/blob/main/.github/ISSUE_TEMPLATE/bug-report.yml)  
  Used to report bugs with clear reproduction steps and expected behavior.
- [Technical Task](https://github.com/bestSWPteam/RentAssistantApplication/blob/main/.github/ISSUE_TEMPLATE/technical-task.yml)  
  For backend, refactoring, or implementation-related work broken down into subtasks.
- [User Story](https://github.com/bestSWPteam/RentAssistantApplication/blob/main/.github/ISSUE_TEMPLATE/user-story.yml)  
  Describes user-facing functionality with acceptance criteria.

All team members are required to use one of these templates when creating new issues.

#### Labelling Issues

We use a set of predefined GitHub issue labels to help organize and filter tasks.

Common labels include:
- **app**, **bot**, **backend**, **frontend** — to indicate the area or component.
- **ux/ui**, **customer feedback** — to highlight design or user-reported issues.
- **assX** — corresponds to assignment number (e.g., `ass3`, `ass5`).
- **sp:X** — indicates story points for planning (e.g., `sp:2`, `sp:5`).

Labels are chosen from an existing list during issue creation or triage.  
Adding labels is **optional but recommended** to keep the board organized and to improve team visibility.

Note: label colors are assigned automatically and may vary.

#### Assigning Issues to Team Members

Issue assignment is handled by the **Team Lead** during sprint planning.

- Each issue must be assigned to a responsible developer before implementation begins.
- No work should be started on unassigned issues.
- If an issue is unassigned and someone wants to work on it, they must first confirm it with the team lead.

This ensures clear responsibility, prevents duplication, and keeps sprint progress visible.

#### Creating, Naming and Merging Branches

We follow a consistent branching strategy to keep the repository organized and readable.

**Base branch:**
- [TBD]

**Branch types and naming conventions:**
- `feature/<...>` — [TBD]
- `fix/<...>` — [TBD]
- `hotfix/<...>` — [TBD]
- `refactor/<...>` — [TBD]
- Other types (if any): [TBD]

**Branching rules:**
- All branches must be created from [TBD].
- Branch names should be short, lowercase, and use dashes if needed.

**Merging rules:**
- Merges are done via pull requests.
- Merge method: [TBD: squash / rebase / merge commit]
- Who can merge: [TBD]
- Branches are [TBD: automatically / manually] deleted after merge.

#### Commit Message Format (TBD)


