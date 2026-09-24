# learnAppiumAutomation

Mobile automation (Appium/Selenium) test project with an AI-powered test-analysis agent.

## Requirements

- Java 21
- Maven

## Required configuration

Secrets and certificates are **not** stored in this repository and must be provided locally.

### 1. API key

Set the environment variable `VERISOFT_API_KEY` before running tests.

PowerShell:

```powershell
$env:VERISOFT_API_KEY = "your-key"
```

Bash / Git Bash:

```bash
export VERISOFT_API_KEY="your-key"
```

Optionally override the endpoint with `VERISOFT_BASE_URL`
(defaults to `https://llm.verisoft.io/v1`).

### 2. TLS truststore

Place the corporate truststore at `certs/verisoft-truststore.jks`.
The entire `certs/` directory is git-ignored and must be supplied locally.

## Build and test

```bash
mvn clean test
```
