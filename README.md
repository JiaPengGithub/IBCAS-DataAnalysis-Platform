# IBCAS DataAnalysis Admin System

An open-source Java web application built on top of the JeeSite 4.x stack. The
project provides a modular management backend with CMS capabilities and a
Spring Boot based web entry point.

> This repository was originally a self-developed small project maintained on
> another Git hosting platform. It has now been migrated here and released as an
> open-source project for public use, learning, and further improvement.

## Features

- Spring Boot web application packaged as a WAR archive
- JeeSite-based admin framework and system module
- CMS module with site, category, template, article, comment, report, tag, and
  visit-log management
- Database initialization scripts for MySQL, Oracle, SQL Server, PostgreSQL,
  DB2, and H2
- Built-in static assets and server-side view templates
- Helper scripts for local running, packaging, deployment, Docker packaging,
  and database initialization

## Tech Stack

- Java 8 compatible source level
- Spring Boot 2.7.4
- Maven multi-module build
- MyBatis
- Apache Shiro
- Beetl templates
- Druid connection pool
- MySQL by default, with SQL scripts for other supported databases

## Repository Layout

```text
.
├── common/       Shared utilities, codecs, media helpers, web helpers, and static assets
├── modules/
│   ├── core/     Core JeeSite system module
│   └── cms/      CMS module, mappings, views, static resources, and database scripts
├── parent/       Maven parent configuration and dependency/plugin management
├── root/         Aggregator POM for the complete multi-module build
├── web/          Spring Boot web application entry point and runtime configuration
├── py/           Python helper scripts
├── pom.xml       Top-level Maven entry
├── LICENSE       Apache License 2.0
└── terms.md      Additional project terms
```

## Requirements

- JDK 8, 11, or 17
- Maven 3.6+
- MySQL 5.7/8.0 or another supported database

## Configuration

The main runtime configuration is located at:

```text
web/src/main/resources/config/application.yml
```

Before running the application, update the `jdbc` section for your own local or
server database:

```yaml
jdbc:
  type: mysql
  driver: com.mysql.cj.jdbc.Driver
  url: jdbc:mysql://127.0.0.1:3306/jeesite?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
  username: your_user
  password: your_password
```

Do not reuse development credentials in production. Prefer environment-specific
configuration files or deployment-time overrides for real deployments.

## Database Initialization

Database scripts are provided under:

```text
web/db/
modules/core/db/
modules/cms/db/
```

For a first-time local setup, configure the database connection first, then run:

```bash
cd web
sh bin/init-data.sh
```

On Windows, use:

```bat
cd web
bin\init-data.bat
```

## Run Locally

After configuring the database and initializing data, start the web application:

```bash
cd web
sh bin/run-tomcat.sh
```

The default server port is:

```text
http://127.0.0.1:8980/
```

The project also includes a package-and-run script:

```bash
cd web
sh bin/run-web.sh
```

## Build

Build the full Maven project from the repository root:

```bash
mvn clean package -Dmaven.test.skip=true
```

Or build through the web module helper script:

```bash
cd web
sh bin/package.sh
```

The web module is packaged with the final name `web`.

## Development Notes

- The top-level Maven project delegates to `root/`, which aggregates `parent`,
  `common`, `modules/core`, `modules/cms`, and `web`.
- The application entry point is
  `web/src/main/java/com/jeesite/modules/Application.java`.
- CMS database creation and upgrade scripts are available under
  `modules/cms/src/main/resources/db/`.
- Tests are skipped by default in the parent Maven configuration. Enable them
  explicitly when needed.

## Git Line Ending Tips

Recommended global Git settings for this codebase:

```bash
git config --global core.autocrlf false
git config --global core.safecrlf true
```

## License

This project is released under the Apache License 2.0. See `LICENSE` for the
full license text.
