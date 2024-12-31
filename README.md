# Veterinary Hotel (Object-Oriented Programming Project)

A terminal-based application for managing a veterinary hotel. The interface and documentation are in Portuguese.

## Features

### UML Design
- Includes a UML design for the initial phase of the application.  
- The design provides a foundational overview but does not represent the entire system.

### Code Structure
1. **App Module**  
   - Handles the terminal interface and menu interactions.  
   - Communicates with the core module to process user actions.

2. **Core Module**  
   - Performs searches and manages the creation of requested instances.  

3. **Po-uilib**  
   - A library that supports the application’s functionality.

## How to Run the Application

1. **Compile the executables**  
   ```bash
   make
   ```

2. **Set the classpath**  
   Replace `/path_to_the_directory` with the appropriate directory paths:  
   ```bash
   export CLASSPATH=/path_to_the_directory/po-uilib/po-uilib.jar:/path_to_the_directory/hva-core/hva-core.jar:/path_to_the_directory/hva-app/hva-app.jar
   ```

3. **Run the application**  
   ```bash
   java hva.app.App
   ```

## Credits

This project was developed by [Miguel Barbosa](https://github.com/MiguelCBar/) and [Martim Afonso]().