---
name: typescript-strict-patterns
description: TypeScript strict mode, type safety, readonly patterns, type guards for frontend tooling used in CI/CD
license: Apache-2.0
---

# TypeScript Strict Patterns Skill

## Purpose

Ensure type-safe TypeScript development for CI/CD tooling, MCP servers, and build scripts used in the CIA platform. This skill covers strict compiler options, type guard patterns, and defensive coding practices for TypeScript components within a primarily Java/Maven project.

## When to Use

- ✅ Writing MCP server tools in TypeScript
- ✅ Creating GitHub Actions custom actions
- ✅ Building CI/CD pipeline scripts and utilities
- ✅ Developing static site generators for political data
- ✅ Writing type-safe configuration parsers

Do NOT use for:
- ❌ Vaadin UI components (Java-based, use vaadin-component-design skill)
- ❌ Backend service logic (use Java/Spring patterns)

## Strict Mode Configuration

### Recommended tsconfig.json

```json
{
  "compilerOptions": {
    "strict": true,
    "noUncheckedIndexedAccess": true,
    "noImplicitOverride": true,
    "noPropertyAccessFromIndexSignature": true,
    "exactOptionalPropertyTypes": true,
    "forceConsistentCasingInFileNames": true,
    "isolatedModules": true,
    "esModuleInterop": true,
    "moduleResolution": "node16",
    "module": "node16",
    "target": "ES2022",
    "declaration": true,
    "declarationMap": true,
    "sourceMap": true,
    "outDir": "./dist"
  }
}
```

### What Strict Mode Enables

| Flag | Purpose |
|------|---------|
| `strictNullChecks` | Prevents null/undefined assignment errors |
| `strictFunctionTypes` | Enforces contravariant function parameter types |
| `strictPropertyInitialization` | Requires class properties to be initialized |
| `noImplicitAny` | Disallows implicit `any` types |
| `noImplicitThis` | Errors on `this` with implicit `any` type |

## Type Safety Patterns

### Discriminated Unions for Political Data

```typescript
interface Politician {
  readonly type: "politician";
  readonly personId: string;
  readonly firstName: string;
  readonly lastName: string;
  readonly party: SwedishParty;
}

interface Committee {
  readonly type: "committee";
  readonly committeeId: string;
  readonly name: string;
  readonly members: readonly string[];
}

type PoliticalEntity = Politician | Committee;

// Type guard
function isPolitician(entity: PoliticalEntity): entity is Politician {
  return entity.type === "politician";
}
```

### Readonly Patterns

```typescript
// Immutable data structures for political records
interface VotingRecord {
  readonly personId: string;
  readonly votes: ReadonlyArray<Vote>;
  readonly summary: Readonly<VotingSummary>;
}

// Readonly mapped type for API responses
type Immutable<T> = {
  readonly [K in keyof T]: T[K] extends object ? Immutable<T[K]> : T[K];
};

type ImmutableApiResponse = Immutable<RiksdagApiResponse>;
```

### Type Guards for Runtime Validation

```typescript
function isValidParty(value: unknown): value is SwedishParty {
  const validParties = ["S", "M", "SD", "C", "V", "KD", "L", "MP"] as const;
  return typeof value === "string" && validParties.includes(value as SwedishParty);
}

function assertNonNull<T>(value: T | null | undefined, name: string): asserts value is T {
  if (value === null || value === undefined) {
    throw new Error(`Expected non-null value for ${name}`);
  }
}
```

### Branded Types for Domain Safety

```typescript
type PersonId = string & { readonly __brand: "PersonId" };
type CommitteeId = string & { readonly __brand: "CommitteeId" };

function createPersonId(raw: string): PersonId {
  if (!/^[0-9a-f-]{36}$/.test(raw)) {
    throw new Error(`Invalid person ID format: ${raw}`);
  }
  return raw as PersonId;
}
```

## Error Handling Patterns

```typescript
// Result type for safe error handling
type Result<T, E = Error> =
  | { readonly success: true; readonly value: T }
  | { readonly success: false; readonly error: E };

async function fetchPoliticianData(id: PersonId): Promise<Result<Politician>> {
  try {
    const response = await fetch(`https://data.riksdagen.se/person/${id}`);
    if (!response.ok) {
      return { success: false, error: new Error(`HTTP ${response.status}`) };
    }
    const data: unknown = await response.json();
    const validated = validatePoliticianData(data);
    return { success: true, value: validated };
  } catch (error) {
    return { success: false, error: error instanceof Error ? error : new Error(String(error)) };
  }
}
```

## CI/CD Integration

### Package.json Scripts

```json
{
  "scripts": {
    "build": "tsc --project tsconfig.json",
    "typecheck": "tsc --noEmit",
    "lint": "eslint src/ --ext .ts",
    "test": "vitest run"
  }
}
```

### GitHub Actions Type Checking

```yaml
- name: TypeScript Type Check
  run: npx tsc --noEmit --strict
```

## Security Considerations

- **Validate all external data** at runtime — types are erased at runtime
- **Use `unknown` over `any`** for untyped data from APIs
- **Sanitize string inputs** before interpolation into commands or queries
- **Never trust type assertions** (`as`) without validation
- **Audit dependencies** with `npm audit` before adding packages
