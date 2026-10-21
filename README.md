# Java Task Tracker

A beginner-friendly command-line task manager built with Core Java. It lets you create, view, complete, and delete tasks. Tasks are saved to a local text file so they remain available after you close and reopen the application.

## Features

- Add tasks with a description
- View all tasks and their status (`TODO` or `DONE`)
- Mark a task as completed
- Delete a task
- Save and load tasks using file handling
- Validate menu choices and task IDs

## Technologies

- Java 11 or later (Java 17 recommended)
- OOP: classes, objects, fields, constructors, and methods
- Collections: `ArrayList` and `List`
- File handling: `java.nio.file` and buffered readers/writers
- Exception handling

## Project structure

```text
java-task-tracker/
├── src/
│   ├── Main.java
│   ├── Task.java
│   └── TaskManager.java
├── .gitignore
└── README.md
```

The application creates `tasks.txt` in the directory from which it is run. This file is local app data and is intentionally excluded from Git.

## Requirements

Install a JDK (Java Development Kit), then verify that Java and the compiler are available in your terminal:

```bash
java -version
javac -version
```

## Run in VS Code or a terminal

Open the `java-task-tracker` project folder in VS Code and open its terminal at the project root.

Compile the source files:

```bash
javac -d out src/Task.java src/TaskManager.java src/Main.java
```

Run the program:

```bash
java -cp out Main
```

Choose an option from the menu. To verify persistence, add a task, choose `5` to exit, and run the application again. The task should still be listed.

## Example session

```text
=== Java Task Tracker ===

1. Add task
2. View all tasks
3. Mark task as done
4. Delete task
5. Exit
Choose an option (1-5): 1
Enter task description: Practice Java arrays
Task added successfully.
```

## Possible future improvements

- Add due dates and task priorities
- Search tasks by keyword
- Filter pending and completed tasks
- Add unit tests with JUnit
- Build a REST API with Spring Boot

## Learning goals

This project is designed to practise object-oriented programming, collections, CRUD operations, input validation, and file persistence before moving to databases and Spring Boot.
