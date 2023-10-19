## Prerequisites

Before you get started, ensure that the following prerequisites are met:

- [Java 17](https://www.oracle.com/java/technologies/downloads/#java17) is installed. You can download and install it from the official Oracle website.

- [Gradle](https://gradle.org/install/) is installed. You can install it using the provided link or via a package manager.

Once you have Java 17 and Gradle installed, you're ready to proceed with the installation and usage of this project.

## Getting Started

Follow these steps to get the project up and running:

1. Clone the repository:

```bash
git clone https://github.com/psytechno604/mp4info.git
```
2. Navigate to the project directory:
```bash
cd mp4info
```
2. Install Gradle dependencies:
```bash
gradle build
```
3. Run the application:
```bash
./gradlew bootRun
```
Server will start on port 8085. If you want to change it, set the desired valut in environment variable SERVER_PORT.

Application works with mp4 files, that are available via HTTP or HTTPS protocols. For test purposes you may use this sample - https://demo.castlabs.com/tmp/text0.mp4.

4. Make your request:
```bash
curl "http://localhost:8085/analyze-mp4?url=https://demo.castlabs.com/tmp/text0.mp4"
```