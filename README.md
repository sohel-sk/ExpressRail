# ExpressRail - Train Ticket Booking System

## Overview
ExpressRail is a terminal-based train ticket booking system built using Java and Object-Oriented Programming (OOP) principles. It allows users to manage train schedules, book tickets, and handle user data through a simple command-line interface.

## Features
- **User Management**: Register and manage user profiles.
- **Train Management**: View and manage train schedules.
- **Ticket Booking**: Book and manage train tickets.
- **Local Data Storage**: Stores train and user data in JSON files.

## Project Structure
```
├── README.md
├── app/
│   ├── build.gradle
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   │   └── com/expressrail/
│       │   │       ├── App.java                # Main application entry point
│       │   │       ├── entities/               # Entity classes
│       │   │       │   ├── Ticket.java
│       │   │       │   ├── Train.java
│       │   │       │   └── User.java
│       │   │       ├── localDb/               # Local JSON data storage
│       │   │       │   ├── trains.json
│       │   │       │   └── users.json
│       │   │       ├── services/               # Business logic services
│       │   │       │   ├── TrainService.java
│       │   │       │   └── UserBookingService.java
│       │   │       └── util/                  # Utility classes
│       │   │           └── UserServiceUtil.java
│       │   └── resources/                     # Resource files
│       └── test/
│           ├── java/
│           │   └── com/expressrail/
│           │       └── AppTest.java           # Unit tests
│           └── resources/
├── gradle/
│   ├── libs.versions.toml                     # Dependency version management
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradle.properties
├── gradlew
├── gradlew.bat
└── settings.gradle
```

## Prerequisites
- Java Development Kit (JDK) 11 or higher
- Gradle 7.0 or higher
- Git (for cloning the repository)

## Setup Instructions
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/expressrail.git
   cd expressrail
   ```

2. **Build the Project**:
   ```bash
   ./gradlew build
   ```

3. **Run the Application**:
   ```bash
   ./gradlew run
   ```

## Usage
- Launch the application using the above command.
- Follow the terminal prompts to:
  - Register or log in as a user.
  - View available trains.
  - Book tickets or manage existing bookings.

## Dependencies
- Managed via `libs.versions.toml` for consistent versioning.
- Key dependencies include:
  - JSON processing (e.g., Jackson for handling JSON files).
  - JUnit for testing.

## Testing
Run unit tests with:
```bash
./gradlew test
```

## Contributing
1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/your-feature`).
3. Commit changes (`git commit -m "Add your feature"`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a pull request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact
For issues or suggestions, please open an issue on GitHub or contact [sohelsheikh9623@gmail.com](mailto:sohelsheikh9623@gmail.com).