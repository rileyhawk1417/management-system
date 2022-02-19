# Main branch

## Description

The project is a template meant to be flexible in any area.
The current template is focused on dealing with school management.

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

### Database Options:
- Sqlite: Makes it portable but less security.
- Mysql: Secure just needs configuring.

## Notes/Warnings

- The sqlite database in the folder com/schoolAdmin/database/sqlite.
- Is for demonstration purposes and does not represent any real world information.
- Sqlite does not use the file when packaged in jar format. It instead copies it and works outside the jar file

