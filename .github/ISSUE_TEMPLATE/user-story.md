name: User Story
description: Describe a user feature or requirement.
title: "[Story] "
labels: [user-story]
assignees: []

body:
  - type: markdown
    attributes:
      value: |
        ## User Story
        Describe the user need and motivation.

  - type: textarea
    id: story
    attributes:
      label: As a ...
      description: "Example: As a user, I want to log in, so that I can access my dashboard"
      placeholder: As a ...
    validations:
      required: true

  - type: markdown
    attributes:
      value: |
        ## Acceptance Criteria (GIVEN / WHEN / THEN)
        Define what must be true for the story to be considered complete.

  - type: textarea
    id: acceptance
    attributes:
      label: Acceptance Criteria
      placeholder: |
        GIVEN ...
        WHEN ...
        THEN ...
    validations:
      required: true
