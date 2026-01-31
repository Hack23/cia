---
name: github-actions-workflows
description: Create secure CI/CD workflows with GitHub Actions: build, test, security scans, deployments
license: Apache-2.0
---

# GitHub Actions Workflows Skill

## Purpose
Create secure, efficient CI/CD pipelines using GitHub Actions for CIA platform.

## When to Use
- ✅ Setting up continuous integration
- ✅ Automating security scans
- ✅ Implementing deployment pipelines
- ✅ Scheduling periodic tasks

## CI/CD Pipeline
```yaml
name: CI/CD Pipeline

on:
  push:
    branches: [main, develop]
  pull_request:
    branches: [main]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      
      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'
          cache: 'maven'
      
      - name: Build with Maven
        run: mvn clean install -DskipTests
      
      - name: Run Tests
        run: mvn test
      
      - name: Upload Coverage
        uses: codecov/codecov-action@v3
        with:
          files: ./target/site/jacoco/jacoco.xml

  security:
    runs-on: ubuntu-latest
    needs: build
    steps:
      - uses: actions/checkout@v4
      
      - name: Run CodeQL Analysis
        uses: github/codeql-action/analyze@v3
      
      - name: OWASP Dependency Check
        run: mvn org.owasp:dependency-check-maven:check
      
      - name: SonarCloud Scan
        env:
          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
        run: mvn sonar:sonar

  deploy:
    runs-on: ubuntu-latest
    needs: [build, security]
    if: github.ref == 'refs/heads/main'
    steps:
      - name: Deploy to AWS
        uses: aws-actions/configure-aws-credentials@v4
        with:
          aws-access-key-id: ${{ secrets.AWS_ACCESS_KEY_ID }}
          aws-secret-access-key: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
          aws-region: eu-west-1
```

## Security Best Practices
- ✅ Use secrets for sensitive data
- ✅ Pin action versions (@v4, not @main)
- ✅ Minimize permissions
- ✅ Scan for vulnerabilities before deploy

## References
- GitHub Actions: https://docs.github.com/en/actions
- .github/workflows/