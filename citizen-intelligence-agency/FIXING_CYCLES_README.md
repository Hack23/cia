# Fixing Circular Dependencies - START HERE

## Quick Answer

**Q:** Where are the cycles?  
**A:** In the view inheritance hierarchy (user.common ↔ common)

**Q:** Can I fix them myself?  
**A:** YES! Takes 2-3 hours. All tools provided below.

## What to Read

1. **THIS FILE** (you are here) - Overview
2. **CYCLES_SUMMARY.md** - Visual guide with commands (5 min read)
3. **../CIRCULAR_DEPENDENCY_FIX_GUIDE.md** - Detailed instructions (15 min read)

## The Problem in 30 Seconds

```
user.common.AbstractUserView extends common.AbstractView
    ↓
common.menufactory.impl references user views
    ↓
Creates CYCLE: user.common → common → user.common
```

**Result:** 50 packages affected

## The Solution in 30 Seconds

Move these 2 files:
1. `user.common.AbstractUserView` → `common.abstracts.AbstractUserView`
2. `admin.common.AbstractAdminView` → `common.abstracts.AbstractAdminView`

Update imports everywhere.

**Result:** ~40 cycles gone immediately

## Quick Start Commands

### See the Problem
```bash
# Run test to see all 50 cycles
mvn test -Dtest=ArchitectureRuleTest -pl citizen-intelligence-agency 2>&1 | \
  grep -A 1000 "CIRCULAR DEPENDENCIES"
```

### Track Progress
```bash
# Run helper script
cd ..
./find-cycles.sh
```

### Apply the Fix
```bash
# 1. Create target directory
mkdir -p src/main/java/com/hack23/cia/web/impl/ui/application/views/common/abstracts

# 2. Move first file
cd src/main/java
mv com/hack23/cia/web/impl/ui/application/views/user/common/AbstractUserView.java \
   com/hack23/cia/web/impl/ui/application/views/common/abstracts/

# 3. Update package in moved file
# Edit AbstractUserView.java:
#   FROM: package ...user.common;
#   TO:   package ...common.abstracts;

# 4. Find and update all imports
grep -r "import.*user.common.AbstractUserView" . --include="*.java"
# Update each: FROM ...user.common.AbstractUserView TO ...common.abstracts.AbstractUserView

# 5. Test
cd ../../../../../../..
mvn test -Dtest=ArchitectureRuleTest -pl citizen-intelligence-agency

# 6. Repeat for AbstractAdminView
```

## Files Available

| File | Purpose | Location |
|------|---------|----------|
| CYCLES_SUMMARY.md | Quick visual guide | This directory |
| CIRCULAR_DEPENDENCY_FIX_GUIDE.md | Detailed instructions | Parent directory |
| find-cycles.sh | Progress tracker | Parent directory |
| ArchitectureRuleTest.java | Shows all cycles | src/test/java/.../architecturerules/ |

## Expected Timeline

| Step | Time | Cycles Left |
|------|------|-------------|
| Start | - | 50 |
| Move AbstractUserView | 30 min | ~25 |
| Move AbstractAdminView | 30 min | ~10 |
| Fix remaining | 1-2 hours | 0 |
| **Total** | **2-3 hours** | **✅ DONE** |

## How to Know You're Done

```bash
mvn test -Dtest=ArchitectureRuleTest -pl citizen-intelligence-agency
```

**Success:**
```
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## Need Help?

1. Read **CYCLES_SUMMARY.md** first (visual diagrams)
2. Read **../CIRCULAR_DEPENDENCY_FIX_GUIDE.md** for details
3. Run **../find-cycles.sh** to track progress
4. The **architecture test** will show remaining cycles

## The Big Picture

Before:
```
user.common/ (bad location)
  └── AbstractUserView.java
  
admin.common/ (bad location)
  └── AbstractAdminView.java
```

After:
```
common/
  └── abstracts/ (good location)
      ├── AbstractUserView.java
      └── AbstractAdminView.java
```

**Why this fixes everything:**
- user.* depends on common.abstracts ✅
- common.* depends on common.abstracts ✅  
- No cycle! Everything points to common ✅

## Start Now

```bash
# 1. Read the visual guide
cat CYCLES_SUMMARY.md

# 2. See the cycles
mvn test -Dtest=ArchitectureRuleTest -pl citizen-intelligence-agency 2>&1 | \
  grep -A 1000 "CIRCULAR DEPENDENCIES"

# 3. Start fixing
# Follow commands above or in CYCLES_SUMMARY.md
```

**You've got this!** 💪
