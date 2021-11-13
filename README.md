# Testing branch

## Reason for this branch?

This branch is for files that are still clunky and messy.
Or reference files in case I accidentally erase something or get stuck.
Not everything might work since this is a testing branch, at least some things will work.

## Todo Lists

- [x] Try to use JavaFX control and layouts
  - [x] Receive input from fields and add event listener to "enter" key.
  - [x] Connect to JDBC driver for postgresql.
  - [x] Setup postgresql tables.
  - [x] Authenticate user or get message that user has been found.
  - [x] Read tables.
  - [ ] Insert tables.
  - [ ] Delete tables.
  - [ ] Update tables.
  - [x] Export tables to excel document.
   
    - [ ] Possibly add an active menu to export to other formats such as PDF. Possibly fix the printer script in future as well.

## Urgent
- [ ] Figure out a way to ask for DB connection and select type of database from the beginning.
## Scenes

- [ ] If user has been found take user to another screen.
  - [ ] Create second stage to display data.
  - [ ] Create another stage to edit data.
## Notes/Warnings
- The sqlite database in the folder com/schoolAdmin/database/sqlite.
- Is for demonstration purposes and does not represent any real world information.
- Sqlite does not use the file when packaged in jar format. It instead copies it and works outside the jar file

