# Complete Guide to Fixing Circular Dependencies

## Summary

**Total Cycles**: ~35 packages  
**Root Cause**: User/admin packages → common packages → back to user/admin packages  
**Fix Difficulty**: EASY to MEDIUM (mostly moving files)

## Quick Start - What to Do

### Step 1: Find All Constants Files (5 minutes)

```bash
cd citizen-intelligence-agency/src/main/java

# Find constants in user packages
find . -name "*Constants.java" -path "*/user/*" -type f

# Find constants in admin packages
find . -name "*Constants.java" -path "*/admin/*" -type f
```

### Step 2: Check Which Are Used by Common Code (10 minutes)

For each constants file found, check if it's imported by common code:

```bash
# Example: Check if a constants file is imported by common
grep -r "import.*YourConstants" com/hack23/cia/web/impl/ui/application/views/common/
```

If you find imports in `common/`, that file needs to be moved!

### Step 3: Move Each Constants File (5 minutes per file)

For each constants file that's imported by common code:

1. **Move the file:**
```bash
# Example for PartyViewConstants
mkdir -p com/hack23/cia/web/impl/ui/application/views/common/constants/

mv com/hack23/cia/web/impl/ui/application/views/user/party/pagemode/PartyViewConstants.java \
   com/hack23/cia/web/impl/ui/application/views/common/constants/
```

2. **Update package declaration in the file:**
```java
// Change FROM:
package com.hack23.cia.web.impl.ui.application.views.user.party.pagemode;

// Change TO:
package com.hack23.cia.web.impl.ui.application.views.common.constants;
```

3. **Find and update all imports:**
```bash
# Find all files that import the old location
grep -r "import com.hack23.cia.web.impl.ui.application.views.user.party.pagemode.PartyViewConstants" .

# Update each file to use new import
# Change FROM: import com.hack23.cia.web.impl.ui.application.views.user.party.pagemode.PartyViewConstants;
# Change TO: import com.hack23.cia.web.impl.ui.application.views.common.constants.PartyViewConstants;
```

4. **Test the change:**
```bash
# Compile
mvn clean compile -pl citizen-intelligence-agency

# Run architecture test
mvn test -Dtest=ArchitectureRuleTest -pl citizen-intelligence-agency
```

### Step 4: Repeat Until Test Passes

Keep repeating Step 3 for each constants file until the architecture test passes!

## Detailed Analysis

### All Packages with Cycles

Run this to see the current cycles:
```bash
mvn test -Dtest=ArchitectureRuleTest -pl citizen-intelligence-agency 2>&1 | \
  grep -A 1000 "CIRCULAR DEPENDENCIES DETECTED"
```

### Cycle Patterns

**Pattern 1: Constants in wrong package (MOST COMMON)**
```
user.parliament.pagemode.ParliamentPageTitleConstants ← imported by common.menufactory.impl
```
**Fix**: Move constants to `common.constants` ✅ ALREADY DONE

**Pattern 2: View inheritance cycle**
```
user.common.AbstractUserView extends common.AbstractView
common packages import user.common classes
```
**Fix**: Extract interfaces OR move AbstractUserView to common.abstracts

**Pattern 3: Menu factory dependencies**
```
common.menufactory.impl creates user-specific menus
Imports constants/classes from user packages
```
**Fix**: Move those constants to common

## Commands Reference

### Search Commands

```bash
# Find all constants files
find . -name "*Constants.java" -type f

# Find imports in common from user
grep -r "import.*\.user\." com/hack23/cia/web/impl/ui/application/views/common/

# Find imports in common from admin
grep -r "import.*\.admin\." com/hack23/cia/web/impl/ui/application/views/common/

# Count cycles (look for "Package:" in test output)
mvn test -Dtest=ArchitectureRuleTest -pl citizen-intelligence-agency 2>&1 | \
  grep "Package:" | wc -l
```

### Fix Commands Template

```bash
# Template for moving a constants file:

# 1. Create destination directory if needed
mkdir -p com/hack23/cia/web/impl/ui/application/views/common/constants/

# 2. Move the file
mv SOURCE_PATH/YourConstants.java \
   com/hack23/cia/web/impl/ui/application/views/common/constants/

# 3. Update package in the moved file
sed -i 's/package SOURCE_PACKAGE;/package com.hack23.cia.web.impl.ui.application.views.common.constants;/' \
   com/hack23/cia/web/impl/ui/application/views/common/constants/YourConstants.java

# 4. Find files that need import updates
grep -r "import SOURCE_PACKAGE.YourConstants" . --include="*.java"

# 5. Update imports in each file (use sed or editor)
find . -name "*.java" -exec sed -i \
  's/import SOURCE_PACKAGE\.YourConstants;/import com.hack23.cia.web.impl.ui.application.views.common.constants.YourConstants;/' {} \;

# 6. Verify compilation
mvn clean compile -pl citizen-intelligence-agency

# 7. Test for cycles
mvn test -Dtest=ArchitectureRuleTest -pl citizen-intelligence-agency
```

## Expected Constants Files to Move

Based on the pattern, likely candidates:

1. `PartyViewConstants` - If exists in user.party.pagemode
2. `CommitteeViewConstants` - If exists in user.committee.pagemode
3. `DocumentViewConstants` - If exists in user.document.pagemode
4. `PoliticianViewConstants` - If exists in user.politician.pagemode
5. Any other `*ViewConstants` files
6. Any other `*PageTitleConstants` files

## How to Know You're Done

Run the architecture test:
```bash
mvn test -Dtest=ArchitectureRuleTest -pl citizen-intelligence-agency
```

**Success looks like:**
```
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

**Still have cycles:**
```
[ERROR] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0
[ERROR] ArchitectureRuleTest.testArchitectureNoCyclesAllowed:76 Project contains cycles
```

If you still have cycles, the test output will show which packages still have them.

## Alternative Fix: Extract Interfaces

If moving constants doesn't fix all cycles, you may need to extract interfaces:

### Create Interface Package

```bash
mkdir -p com/hack23/cia/web/impl/ui/application/views/contracts
```

### Create View Interfaces

Create `UserView.java` in contracts package:
```java
package com.hack23.cia.web.impl.ui.application.views.contracts;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

public interface UserView {
    void enter(ViewChangeEvent event);
}
```

### Update Abstract Classes

Update `AbstractUserView` to implement interface:
```java
public abstract class AbstractUserView 
    extends AbstractView 
    implements UserView {
    // existing code
}
```

### Update Common Code

Change common code from importing concrete classes to importing interfaces:
```java
// Change FROM:
import com.hack23.cia.web.impl.ui.application.views.user.common.AbstractUserView;

// Change TO:
import com.hack23.cia.web.impl.ui.application.views.contracts.UserView;
```

## Troubleshooting

### Problem: Can't find which constants to move

**Solution**: Run this to see which common files import from user:
```bash
grep -rn "import.*\.user\." \
  citizen-intelligence-agency/src/main/java/com/hack23/cia/web/impl/ui/application/views/common/ \
  | grep -v ".class"
```

### Problem: Moved constants but test still fails

**Solution**: Make sure you:
1. Updated the package declaration IN the moved file
2. Updated ALL imports across the codebase
3. Compiled successfully: `mvn clean compile`
4. No typos in package names

### Problem: Import updates didn't work

**Solution**: Manually check each file. Sometimes sed doesn't catch all cases. Use your IDE's refactor tool if available.

## Progress Tracking

Track your progress with this checklist:

```bash
# Get current cycle count
mvn test -Dtest=ArchitectureRuleTest -pl citizen-intelligence-agency 2>&1 | \
  grep "Package:" | wc -l
```

Record before and after each fix:
- Before: ~35 cycles
- After fix 1: ? cycles
- After fix 2: ? cycles
- Target: 0 cycles

## Conclusion

This is methodical work but not difficult:
1. Find constants files in user/admin packages
2. Check if common code imports them
3. Move them to common.constants
4. Update imports
5. Test
6. Repeat

Good luck! The architecture test will guide you - it shows exactly which packages still have cycles.
