#!/bin/bash
# Script to help identify and fix circular dependencies

echo "=== Circular Dependency Analysis Tool ==="
echo ""

cd citizen-intelligence-agency/src/main/java || exit 1

echo "1. FINDING CONSTANTS FILES IN USER PACKAGES..."
echo "================================================"
USER_CONSTANTS=$(find . -name "*Constants.java" -path "*/user/*" -type f | sort)
if [ -z "$USER_CONSTANTS" ]; then
    echo "No constants files found in user packages"
else
    echo "$USER_CONSTANTS"
fi
echo ""

echo "2. FINDING CONSTANTS FILES IN ADMIN PACKAGES..."
echo "================================================"
ADMIN_CONSTANTS=$(find . -name "*Constants.java" -path "*/admin/*" -type f | sort)
if [ -z "$ADMIN_CONSTANTS" ]; then
    echo "No constants files found in admin packages"
else
    echo "$ADMIN_CONSTANTS"
fi
echo ""

echo "3. CHECKING WHICH ARE IMPORTED BY COMMON CODE..."
echo "================================================"
COMMON_DIR="com/hack23/cia/web/impl/ui/application/views/common"

if [ ! -z "$USER_CONSTANTS" ]; then
    echo "User constants imported by common:"
    while IFS= read -r file; do
        classname=$(basename "$file" .java)
        imports=$(grep -r "import.*\.$classname" "$COMMON_DIR" 2>/dev/null | grep -v ".class")
        if [ ! -z "$imports" ]; then
            echo "  ⚠️  $classname IS IMPORTED BY COMMON:"
            echo "$imports" | sed 's/^/      /'
        fi
    done <<< "$USER_CONSTANTS"
fi

if [ ! -z "$ADMIN_CONSTANTS" ]; then
    echo ""
    echo "Admin constants imported by common:"
    while IFS= read -r file; do
        classname=$(basename "$file" .java)
        imports=$(grep -r "import.*\.$classname" "$COMMON_DIR" 2>/dev/null | grep -v ".class")
        if [ ! -z "$imports" ]; then
            echo "  ⚠️  $classname IS IMPORTED BY COMMON:"
            echo "$imports" | sed 's/^/      /'
        fi
    done <<< "$ADMIN_CONSTANTS"
fi
echo ""

echo "4. CHECKING FOR OTHER USER IMPORTS IN COMMON..."
echo "================================================"
echo "All user package imports in common code:"
grep -r "import com\.hack23\.cia\.web\.impl\.ui\.application\.views\.user\." "$COMMON_DIR" \
    2>/dev/null | grep -v ".class" | cut -d: -f2 | sort -u | head -20
echo ""

echo "5. CHECKING FOR OTHER ADMIN IMPORTS IN COMMON..."
echo "================================================"
echo "All admin package imports in common code:"
grep -r "import com\.hack23\.cia\.web\.impl\.ui\.application\.views\.admin\." "$COMMON_DIR" \
    2>/dev/null | grep -v ".class" | cut -d: -f2 | sort -u | head -20
echo ""

echo "6. RUNNING ARCHITECTURE TEST TO COUNT CYCLES..."
echo "================================================"
cd /home/runner/work/cia/cia
CYCLE_COUNT=$(mvn test -Dtest=ArchitectureRuleTest -pl citizen-intelligence-agency 2>&1 | \
    grep "^Package:" | wc -l)
echo "Current number of packages with cycles: $CYCLE_COUNT"
echo ""

if [ "$CYCLE_COUNT" -eq "0" ]; then
    echo "🎉 SUCCESS! No cycles detected!"
else
    echo "To see details of all cycles, run:"
    echo "  mvn test -Dtest=ArchitectureRuleTest -pl citizen-intelligence-agency 2>&1 | grep -A 1000 'CIRCULAR DEPENDENCIES'"
fi

echo ""
echo "=== Analysis Complete ==="
echo ""
echo "NEXT STEPS:"
echo "1. For each constants file imported by common (marked with ⚠️ above):"
echo "   - Move it to common/constants/ directory"
echo "   - Update its package declaration"
echo "   - Update all imports"
echo "2. Run this script again to track progress"
echo "3. When cycle count reaches 0, you're done!"
echo ""
