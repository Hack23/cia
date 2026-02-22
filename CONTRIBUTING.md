<p align="center">
  <img src="https://hack23.com/icon-192.png" alt="Hack23 Logo" width="192" height="192">
</p>

<h1 align="center">🤝 Contributing — Citizen Intelligence Agency</h1>

<p align="center">
  <strong>🛡️ Secure Contribution Guidelines</strong><br>
  <em>🎯 Building Democratic Transparency Through Collaborative Excellence</em>
</p>

<p align="center">
  <a href="#"><img src="https://img.shields.io/badge/Owner-CEO-0A66C2?style=for-the-badge" alt="Owner"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Version-1.0-555?style=for-the-badge" alt="Version"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Effective-2026--02--20-success?style=for-the-badge" alt="Effective Date"/></a>
  <a href="#"><img src="https://img.shields.io/badge/Review-Quarterly-orange?style=for-the-badge" alt="Review Cycle"/></a>
</p>

**📋 Document Owner:** CEO | **📄 Version:** 1.0 | **📅 Last Updated:** 2026-02-20 (UTC)  
**🔄 Review Cycle:** Quarterly | **⏰ Next Review:** 2026-05-20

---

## 🎯 **Purpose Statement**

This contributing guide establishes secure contribution procedures for the Citizen Intelligence Agency, implementing [Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md) and [Change Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Change_Management.md) from Hack23 AB's ISMS framework.

We believe in **security through transparency** and **continuous improvement**, welcoming contributions that enhance the project while maintaining our high security standards.

*— James Pether Sörling, CEO/Founder*

---

## Contributing

[fork]: /fork
[pr]: /compare
[code-of-conduct]: CODE_OF_CONDUCT.md

Hi there! We're thrilled that you'd like to contribute to this project. Your help is essential for keeping it great.

Please note that this project is released with a [Contributor Code of Conduct][code-of-conduct]. By participating in this project you agree to abide by its terms.

## Issues and PRs

If you have suggestions for how this project could be improved, or want to report a bug, open an issue! We'd love all and any contributions. If you have questions, too, we'd love to hear them.

We'd also love PRs. If you're thinking of a large PR, we advise opening up an issue first to talk about it, though! Look at the links below if you're not sure how to open a PR.

## Submitting a pull request

1. [Fork][fork] and clone the repository.
1. Configure and install the dependencies: `mvn clean install`.
1. Make sure the tests pass on your machine: `mvn test`.
1. Create a new branch: `git checkout -b my-branch-name`.
1. Make your change, add tests, and make sure the tests still pass.
1. Push to your fork and [submit a pull request][pr].
1. Pat yourself on the back and wait for your pull request to be reviewed and merged.

Here are a few things you can do that will increase the likelihood of your pull request being accepted:

- Write and update tests.
- Keep your changes as focused as possible. If there are multiple changes you would like to make that are not dependent upon each other, consider submitting them as separate pull requests.
- Write a [good commit message](http://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html).

Work in Progress pull requests are also welcome to get feedback early on, or if there is something blocked you.

### Test Naming Conventions

This project follows strict test naming conventions to separate unit tests from integration tests:

- **Unit Tests**: Use `*Test.java` suffix
  - Pure unit tests with mocked dependencies
  - No database access, no external API calls
  - Fast execution (< 1 second per test)
  - Example: `RiksdagenDateUtilTest`, `ApiDtoSanityTest`

- **Integration Tests**: Use `*ITest.java` suffix  
  - Tests that require database access
  - Tests that call external APIs
  - Tests that use Spring application context
  - Slower execution, require infrastructure
  - Example: `WorldbankTopicApiImplITest`, `DataDAOITest`

**Why this matters**: The build system excludes `**ITest*` from unit test runs to keep CI fast and avoid external dependencies. Always use the correct suffix based on whether your test has external dependencies.

## 🔐 Security Guidelines

Contributing to this project requires adherence to Hack23 AB's security standards and ISMS policies.

### Security Requirements for Contributors

All contributions must comply with the following security policies from our [ISMS-PUBLIC framework](https://github.com/Hack23/ISMS-PUBLIC):

- **[Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md)**: Follow secure coding practices
  - No hardcoded credentials or secrets in code
  - Proper input validation and output encoding
  - Use parameterized queries for database access
  - Follow OWASP secure coding guidelines

- **[Acceptable Use Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Acceptable_Use_Policy.md)**: Responsible use of resources
  - Respect project infrastructure and resources
  - Use development/test environments appropriately
  - Report security vulnerabilities privately via [SECURITY.md](SECURITY.md)

- **[Access Control Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Access_Control_Policy.md)**: Account and credential management
  - Use strong authentication for your GitHub account
  - Enable 2FA on your GitHub account
  - Protect your development environment and credentials

### Automated Security Checks

All pull requests are automatically scanned for security issues:
- ✅ **CodeQL Analysis** - Static application security testing (SAST)
- ✅ **Dependency Review** - Software composition analysis (SCA)
- ✅ **Secret Scanning** - Credential leak detection
- ✅ **OSSF Scorecard** - Security best practices verification

See [WORKFLOWS.md](WORKFLOWS.md) for details on CI/CD security controls.

### Reporting Security Vulnerabilities

**Do not open public issues for security vulnerabilities.** Instead, follow our [Security Policy](SECURITY.md) to report vulnerabilities privately.

### Security Resources

- 🔐 [ISMS Compliance Mapping](ISMS_COMPLIANCE_MAPPING.md) - Complete security framework
- 🛡️ [Security Architecture](SECURITY_ARCHITECTURE.md) - Platform security controls
- 🎯 [Threat Model](THREAT_MODEL.md) - Known threats and mitigations
- 📋 [CRA Assessment](CRA-ASSESSMENT.md) - Regulatory compliance

## Resources

- [How to Contribute to Open Source](https://opensource.guide/how-to-contribute/)
- [Using Pull Requests](https://help.github.com/articles/about-pull-requests/)
- [GitHub Help](https://help.github.com)

---

## 📚 Related Documents

### 🛠️ Development & Security Policies
- [🛠️ Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md) - Development security standards
- [📝 Change Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Change_Management.md) - Change control procedures
- [🔐 Information Security Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md) - Overall security governance
- [🔍 Vulnerability Management](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Vulnerability_Management.md) - Security testing and remediation

### 📝 Testing & Documentation
- [🧪 Unit Test Plan](./UnitTestPlan.md) - Unit testing requirements
- [🌐 E2E Test Plan](./E2ETestPlan.md) - End-to-end testing standards
- [🔄 CI/CD Workflows](./WORKFLOWS.md) - Security-hardened CI/CD pipelines

### 📋 Project Governance
- [📜 Code of Conduct](./CODE_OF_CONDUCT.md) - Community standards
- [🔐 Security Policy](./SECURITY.md) - Vulnerability reporting
- [📋 README](./README.md) - Project overview

---

**📋 Document Control:**  
**✅ Approved by:** James Pether Sörling, CEO  
**📤 Distribution:** Public  
**🏷️ Classification:** [![Confidentiality: Public](https://img.shields.io/badge/C-Public-lightgrey?style=flat-square)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md#confidentiality-levels) [![Integrity: Moderate](https://img.shields.io/badge/I-Moderate-yellow?style=flat-square&logo=check-circle&logoColor=black)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md#integrity-levels) [![Availability: Standard](https://img.shields.io/badge/A-Standard-lightgreen?style=flat-square&logo=server&logoColor=white)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md#availability-levels)  
**📅 Effective Date:** 2026-02-20  
**⏰ Next Review:** 2026-05-20  
**🎯 Framework Compliance:** [![ISO 27001](https://img.shields.io/badge/ISO_27001-2022_Aligned-blue?style=flat-square&logo=iso&logoColor=white)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md) [![NIST CSF 2.0](https://img.shields.io/badge/NIST_CSF-2.0_Aligned-green?style=flat-square&logo=nist&logoColor=white)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md) [![CIS Controls](https://img.shields.io/badge/CIS_Controls-v8.1_Aligned-orange?style=flat-square&logo=cisecurity&logoColor=white)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md) [![AWS Well-Architected](https://img.shields.io/badge/AWS-Well_Architected-orange?style=flat-square&logo=amazon-aws&logoColor=white)](https://github.com/Hack23/ISMS-PUBLIC/blob/main/CLASSIFICATION.md)
