# Circular Dependencies - Quick Summary

## Current Status
- **50 packages** have circular dependencies
- **Root cause identified:** Inheritance hierarchy between user/admin and common packages
- **Test available:** Shows all cycles in detail
- **Tools provided:** Helper script and complete fix guide

## The Problem

```
┌─────────────────────────────────────────────────┐
│                                                 │
│  user.common.AbstractUserView                  │
│         ↓ extends                               │
│  common.AbstractView                            │
│         ↑ imported by                           │
│  common.menufactory.impl                        │
│         ↓ references                            │
│  user.* views                                   │
│         ↓ extend                                │
│  user.common.AbstractUserView ← CYCLE!         │
│                                                 │
└─────────────────────────────────────────────────┘
```

## The Solution

**Move abstract classes to common package:**

### Before (has cycles):
```
user.common/
  ├── AbstractUserView.java       ← Here is the problem
  
admin.common/
  ├── AbstractAdminView.java      ← Here is the problem

common/
  ├── AbstractView.java
  └── menufactory/
      └── impl/
          └── (references user classes) ← Creates cycle
```

### After (no cycles):
```
common/
  ├── AbstractView.java
  ├── abstracts/
  │   ├── AbstractUserView.java    ← Moved here
  │   └── AbstractAdminView.java   ← Moved here
  └── menufactory/
      └── impl/
          └── (references common classes) ← No cycle!

user.common/
  └── (delete - now empty)

admin.common/
  └── (delete - now empty)
```

## Quick Fix Steps

### 1. Create new package
```bash
mkdir -p citizen-intelligence-agency/src/main/java/com/hack23/cia/web/impl/ui/application/views/common/abstracts
```

### 2. Move AbstractUserView
```bash
cd citizen-intelligence-agency/src/main/java
mv com/hack23/cia/web/impl/ui/application/views/user/common/AbstractUserView.java \
   com/hack23/cia/web/impl/ui/application/views/common/abstracts/
```

### 3. Update package in moved file
Edit `AbstractUserView.java`:
```java
// Change FROM:
package com.hack23.cia.web.impl.ui.application.views.user.common;

// Change TO:
package com.hack23.cia.web.impl.ui.application.views.common.abstracts;
```

### 4. Update all imports
```bash
# Find all files importing the old location
grep -r "import com.hack23.cia.web.impl.ui.application.views.user.common.AbstractUserView" . \
  --include="*.java"

# Update each file (or use IDE refactor):
# FROM: import com.hack23.cia.web.impl.ui.application.views.user.common.AbstractUserView;
# TO:   import com.hack23.cia.web.impl.ui.application.views.common.abstracts.AbstractUserView;
```

### 5. Repeat for AbstractAdminView
Same steps for `admin.common.AbstractAdminView`

### 6. Test
```bash
# Compile
mvn clean compile -pl citizen-intelligence-agency

# Run architecture test
mvn test -Dtest=ArchitectureRuleTest -pl citizen-intelligence-agency
```

### 7. Track progress
```bash
# Use helper script
./find-cycles.sh

# Should see cycle count drop from 50 to ~5-10
```

## Tools Available

### 1. Architecture Test
Shows all cycles:
```bash
mvn test -Dtest=ArchitectureRuleTest -pl citizen-intelligence-agency 2>&1 | \
  grep -A 1000 "CIRCULAR DEPENDENCIES"
```

### 2. Helper Script
Analyzes and tracks progress:
```bash
./find-cycles.sh
```

### 3. Complete Guide
Detailed instructions:
```bash
cat CIRCULAR_DEPENDENCY_FIX_GUIDE.md
```

## Expected Timeline

| Task | Time | Cycles Remaining |
|------|------|------------------|
| Initial state | - | 50 |
| Move AbstractUserView | 30 min | ~25 |
| Move AbstractAdminView | 30 min | ~10 |
| Fix remaining cycles | 1-2 hours | 0 |
| **Total** | **2-3 hours** | **0** ✅ |

## Success Criteria

```bash
mvn test -Dtest=ArchitectureRuleTest -pl citizen-intelligence-agency
```

**Success:**
```
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## Files to Move

### Primary (will fix most cycles):
1. `user/common/AbstractUserView.java` → `common/abstracts/`
2. `admin/common/AbstractAdminView.java` → `common/abstracts/`

### If needed (after checking remaining cycles):
- Any other classes in user.common or admin.common
- Extract interfaces if inheritance isn't the only issue

## Why This Fixes Everything

**Current (bad):**
- user.* → depends on → user.common
- user.common → depends on → common
- common → depends on → user.common (via menu factories)
- **CYCLE!**

**After fix (good):**
- user.* → depends on → common.abstracts
- common.abstracts → depends on → common
- common → depends on → common.abstracts
- **NO CYCLE!** (all dependencies point to common)

## Bottom Line

**Problem:** Circular dependencies in view hierarchy  
**Solution:** Move abstract view classes to common package  
**Time:** 2-3 hours of systematic work  
**Difficulty:** MEDIUM (mostly file moves and import updates)  
**Risk:** LOW (refactoring, no logic changes)

**You can definitely do this yourself with the tools provided!**
