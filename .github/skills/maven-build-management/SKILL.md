---
name: maven-build-management
description: Manage multi-module Maven builds: dependencies, plugins, profiles, reproducible builds
license: Apache-2.0
---

# Maven Build Management Skill

## Purpose
Manage CIA platform's multi-module Maven build with proper dependency management and plugin configuration.

## When to Use
- ✅ Adding new modules
- ✅ Managing dependencies
- ✅ Configuring build plugins
- ✅ Creating build profiles

## Multi-Module Structure
```xml
<project>
    <modules>
        <module>parent-pom</module>
        <module>model.common.api</module>
        <module>service.api</module>
        <module>service.impl</module>
        <module>citizen-intelligence-agency</module>
    </modules>
</project>
```

## Dependency Management
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>${spring-boot.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

## Build Profiles
```xml
<profiles>
    <profile>
        <id>production</id>
        <properties>
            <spring.profiles.active>production</spring.profiles.active>
        </properties>
    </profile>
</profiles>
```

## Build Commands
```bash
# Clean install all modules
mvn clean install

# Skip tests
mvn clean install -DskipTests

# Specific module
mvn clean install -pl citizen-intelligence-agency -am

# With profile
mvn clean install -Pproduction
```

## References
- Maven: https://maven.apache.org/
- pom.xml