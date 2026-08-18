# Contributing

Thank you for your interest in contributing to IBCAS DataAnalysis Admin System.

## Development Setup

1. Fork and clone the repository.
2. Install JDK 8, 11, or 17 and Maven 3.6+.
3. Configure your local database in `web/src/main/resources/config/application.yml`
   or by using environment variables.
4. Initialize the database with `web/bin/init-data.sh` or
   `web/bin/init-data.bat`.
5. Run the application with `web/bin/run-tomcat.sh` or
   `web/bin/run-tomcat.bat`.

## Pull Requests

- Keep changes focused and describe the reason for the change.
- Do not commit secrets, real credentials, private endpoints, generated
  packages, logs, or local upload files.
- Follow the existing project style.
- Add or update tests when changing behavior.
- Run relevant build or validation commands before opening a pull request.

## Reporting Issues

Please include reproduction steps, environment details, expected behavior, and
actual behavior. Remove secrets and personal data from logs or screenshots.
