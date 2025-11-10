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
1. Pat your self on the back and wait for your pull request to be reviewed and merged.

Here are a few things you can do that will increase the likelihood of your pull request being accepted:

- Write and update tests.
- Keep your changes as focused as possible. If there are multiple changes you would like to make that are not dependent upon each other, consider submitting them as separate pull requests.
- Write a [good commit message](http://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html).

Work in Progress pull requests are also welcome to get feedback early on, or if there is something blocked you.

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
