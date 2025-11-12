## 🎯 Objective

Upgrade the web server from Jetty 10 to Jetty 12 to extend platform support lifecycle until 2028 while maintaining javax.servlet compatibility.

## 📋 Background

According to the End-of-Life Strategy, the CIA project currently uses Jetty 10, which reaches EOL in 2026. Jetty 12 supports both `javax.servlet` and Jakarta namespaces and has an EOL of 2028. This migration allows the platform to remain compatible with future JVMs while avoiding architectural transition to Jakarta, aligning with the project's strategy to maintain the current `javax.*` stack.

**Current Status:**
- Using: Jetty 10.x (EOL 2026)
- Target: Jetty 12.x (EOL 2028)
- Impact: Extends platform viability by 2 years

**References:**
- [End-of-Life Strategy](https://github.com/Hack23/cia/blob/master/End-of-Life-Strategy.md#jetty-10-to-jetty-12-transition-plan)
- [Jetty EOL Timeline](https://endoflife.date/eclipse-jetty)

## ✅ Acceptance Criteria

- [ ] Update all Jetty dependencies from 10.x to 12.x in parent-pom
- [ ] Verify javax.servlet compatibility (not Jakarta)
- [ ] Update web.xml and servlet configurations if needed
- [ ] All existing integration tests pass with Jetty 12
- [ ] Application starts and runs successfully on Jetty 12
- [ ] Update End-of-Life Strategy documentation with new EOL dates
- [ ] Document any configuration changes in CHANGELOG

## 🛠️ Implementation Guidance

### Files to Modify

1. **`parent-pom/pom.xml`** - Update Jetty version properties
   ```xml
   <cia.project.versions.jetty>12.0.x</cia.project.versions.jetty>
   ```

2. **`citizen-intelligence-agency/pom.xml`** - Verify Jetty plugin configuration

3. **Web configuration files** - Check for Jetty 10-specific configurations

4. **`End-of-Life-Strategy.md`** - Update EOL dates and current version info

### Approach

1. **Research Phase**
   - Review [Jetty 12 migration guide](https://eclipse.dev/jetty/documentation/jetty-12/)
   - Check breaking changes between Jetty 10 and 12
   - Verify javax.servlet support in Jetty 12

2. **Update Dependencies**
   - Update parent POM Jetty version property
   - Run `mvn dependency:tree` to verify all Jetty artifacts updated
   - Check for transitive dependency conflicts

3. **Configuration Updates**
   - Review and update Jetty-specific configuration files
   - Update web.xml if needed (should remain compatible)
   - Verify security configuration compatibility

4. **Testing**
   - Build project: `mvn clean install`
   - Run all tests: `mvn test`
   - Start application locally and verify functionality
   - Test key user flows (login, dashboard viewing, data loading)

5. **Documentation**
   - Update End-of-Life-Strategy.md with new timeline
   - Add migration notes to CHANGELOG
   - Update README if deployment instructions change

### Technical Considerations

- **javax.servlet Compatibility**: Jetty 12 supports both javax.servlet (EE8) and Jakarta (EE9+). Ensure we're using the EE8 variant.
- **Configuration Migration**: Jetty 12 has modernized configuration format but maintains backward compatibility.
- **SSL/TLS**: Verify SSL configuration remains compatible.
- **WebSocket**: If used, verify WebSocket implementation compatibility.

### Testing Strategy

```bash
# Build and test
mvn clean install

# Run specific integration tests
mvn verify -Dit.test=*IT

# Start application locally
cd citizen-intelligence-agency
mvn jetty:run
```

## 🔗 Related Resources

- [Jetty 12 Documentation](https://eclipse.dev/jetty/documentation/jetty-12/)
- [End-of-Life Strategy](https://github.com/Hack23/cia/blob/master/End-of-Life-Strategy.md)
- [Project Architecture](https://github.com/Hack23/cia/blob/master/ARCHITECTURE.md)
- [Jetty Migration Guide](https://eclipse.dev/jetty/documentation/jetty-12/operations-guide/index.html#migration)

## 📊 Metadata

**Priority:** High  
**Effort:** Medium (1-2 days)  
**Labels:** `enhancement`, `infrastructure`, `dependencies`  
**Milestone:** Next Release  
**Impact:** Extends platform support lifecycle by 2 years (2026 → 2028)
