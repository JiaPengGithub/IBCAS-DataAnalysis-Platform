# IBCAS DataAnalysis Admin System

A lightweight Java admin/CMS web application based on JeeSite 4.x and Spring
Boot.

> This project was originally a self-developed small project hosted on another
> Git platform. It has now been migrated to GitHub and released as an
> open-source project.

## Overview

- Modular Maven project
- Spring Boot web application, packaged as WAR
- JeeSite-based admin framework
- CMS module for site, category, template, article, comment, report, tag, and
  visit-log management
- Database scripts for MySQL, Oracle, SQL Server, PostgreSQL, DB2, and H2

## Tech Stack

- Java 8+
- Spring Boot 2.7.4
- Maven 3.6+
- MyBatis
- Apache Shiro
- Beetl
- Druid

## Project Structure

```text
common/       Shared utilities and static assets
modules/core/ Core system module
modules/cms/  CMS module
parent/       Maven parent configuration
root/         Multi-module aggregator
web/          Web application entry point and runtime configuration
```

## Quick Start

Update your database settings in:

```text
web/src/main/resources/config/application.yml
```

You can also use environment variables:

```bash
export JDBC_URL="jdbc:mysql://127.0.0.1:3306/jeesite?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai"
export JDBC_USERNAME="jeesite"
export JDBC_PASSWORD="change-me"
```

Initialize the database:

```bash
cd web
sh bin/init-data.sh
```

Run locally:

```bash
cd web
sh bin/run-tomcat.sh
```

Default address:

```text
http://127.0.0.1:8980/
```

## Build

```bash
mvn clean package -Dmaven.test.skip=true
```

Or:

```bash
cd web
sh bin/package.sh
```

## Notes

- Main application class:
  `web/src/main/java/com/jeesite/modules/Application.java`
- Main configuration:
  `web/src/main/resources/config/application.yml`
- Do not commit real credentials, private endpoints, logs, generated packages,
  or local upload files.
- Security reports should follow `SECURITY.md`.
- Contributions should follow `CONTRIBUTING.md`.

## License

Apache License 2.0. See `LICENSE` for details.
