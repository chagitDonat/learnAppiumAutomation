# learnAppiumAutomation

Mobile automation (Appium/Selenium) test project with an AI-powered test-analysis agent.

## About this project

This project was created as a submission for an Appium course.

I later added AI-based test analysis to it, as a way to learn how to work with
`agents`, `tools`, and `skills`.

## Requirements

- Java 21
- Maven

## Required configuration

Secrets and certificates are **not** stored in this repository and must be supplied locally.

Configuration is resolved in this order (first non-blank value wins):

1. Environment variable
2. JVM system property (e.g. `-DVERISOFT_API_KEY=...`)
3. `application.properties` or `local.properties` (project root, or on the classpath)

This project is plain Maven/JUnit (not Spring Boot), so the properties file is read by
[`AppConfig`](src/main/java/ai/config/AppConfig.java).

### Option A - local.properties (recommended)

Copy the template and fill in your own values:

```bash
cp local.properties.example local.properties
```

```properties
VERISOFT_API_KEY=your-verisoft-api-key
VERISOFT_BASE_URL=https://llm.verisoft.io/v1
VERISOFT_TRUSTSTORE_PATH=certs/verisoft-truststore.jks
VERISOFT_TRUSTSTORE_PASSWORD=changeit
```

`local.properties` is git-ignored and must never be committed.

### Option B - environment variable

PowerShell:

```powershell
$env:VERISOFT_API_KEY = "your-key"
```

Bash / Git Bash:

```bash
export VERISOFT_API_KEY="your-key"
```

### TLS truststore

The tests load the corporate truststore from `VERISOFT_TRUSTSTORE_PATH`
(defaults to `certs/verisoft-truststore.jks`). The whole `certs/` directory is git-ignored
and must be supplied locally.

## Build and test

```bash
mvn clean test
```
