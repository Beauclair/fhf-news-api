# First Help Financial News API

This is a simple REST API framework built with Java and the Spring framework. It interacts with a public news API in this case GNews API to fetch news articles. 
The API provides endpoints to fetch N news articles, find articles by title or author, and search by keywords. 
Additionally, caching is implemented to enhance performance by storing previously fetched articles.


## Approach

This project follows the hexagonal architecture pattern, also known as ports and adapters architecture. 
In this architecture, the application core is decoupled from external dependencies such as databases, APIs, or UIs, allowing for better testability, maintainability, and flexibility. 

In our implementation, we have structured the project into three distinct modules:

* Core: This module serves as the repository for our API's business logic. It encapsulates the fundamental functionality and rules governing our system's behavior.

* API: This module houses the implementation of our API endpoints. The API layer can be generated based on open-api schemas and acts as the interface through which external systems interact with our application.

* Application: The Application module contains all configuration settings and serves as the entry point for our system. 

By organizing our project in this manner, we achieve a clear separation of concerns, making it easier to manage and evolve individual components independently. Additionally, this architectural approach promotes scalability and adaptability, allowing us to accommodate future changes and enhancements with minimal disruption.


## Getting Started

### Prerequisites

- Java Development Kit (JDK) version 8 or higher
- Docker (optional, for running the application as a container)
- Gradle (used for dependency management) - This project utilizes the Gradle wrapper for managing dependencies.

### Guides

The following guides illustrate how to use some features concretely:

* [Producing a SOAP web service](https://spring.io/guides/gs/producing-web-service/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

### Docker Compose support

This project contains a Docker Compose file named `compose.yaml`.

However, no services were found. As of now, the application won't start!

Please make sure to add at least one service in the `compose.yaml` file.

