---
name: spring-framework-patterns
description: Apply Spring Framework best practices: dependency injection, transaction management, AOP for CIA services
license: Apache-2.0
---

# Spring Framework Patterns Skill

## Purpose
Apply Spring Framework best practices for dependency injection, transactions, and aspect-oriented programming.

## When to Use
- ✅ Creating Spring services and components
- ✅ Managing transactions
- ✅ Implementing cross-cutting concerns
- ✅ Configuring application context

## Dependency Injection Patterns
```java
@Service
public class PoliticianService {
    private final PoliticianRepository repository;
    private final AuditLogger auditLogger;
    
    // Constructor injection (preferred)
    public PoliticianService(
            PoliticianRepository repository,
            AuditLogger auditLogger) {
        this.repository = repository;
        this.auditLogger = auditLogger;
    }
}
```

## Transaction Management
```java
@Service
@Transactional(readOnly = true)
public class VotingService {
    
    @Transactional
    public void recordVote(Vote vote) {
        voteRepository.save(vote);
        statisticsService.updateStats(vote);
        auditLogger.log("VOTE_RECORDED", vote.getId());
    }
}
```

## Aspect-Oriented Programming
```java
@Aspect
@Component
public class PerformanceMonitoringAspect {
    
    @Around("@annotation(Monitored)")
    public Object monitor(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long duration = System.currentTimeMillis() - start;
            log.info("Method {} took {}ms", 
                joinPoint.getSignature().getName(), duration);
        }
    }
}
```

## References
- Spring Framework: https://docs.spring.io/spring-framework/reference/
- ARCHITECTURE.md