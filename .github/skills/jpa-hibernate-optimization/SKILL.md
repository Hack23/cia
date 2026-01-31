---
name: jpa-hibernate-optimization
description: Optimize JPA/Hibernate: entity design, query performance, N+1 prevention, caching strategies
license: Apache-2.0
---

# JPA/Hibernate Optimization Skill

## Purpose
Optimize database access with proper entity design, query optimization, and caching.

## When to Use
- ✅ Designing entity relationships
- ✅ Optimizing slow queries
- ✅ Preventing N+1 queries
- ✅ Implementing caching

## Entity Optimization
```java
@Entity
@Table(name = "politician", indexes = {
    @Index(name = "idx_politician_party", columnList = "party"),
    @Index(name = "idx_politician_district", columnList = "district")
})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Politician {
    @Id
    private String id;
    
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;
    
    @OneToMany(mappedBy = "politician", cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 20)
    private List<VotingRecord> votingRecords;
}
```

## Query Optimization
```java
// Use JOIN FETCH to prevent N+1
@Query("SELECT p FROM Politician p JOIN FETCH p.party WHERE p.district = :district")
List<Politician> findByDistrictWithParty(@Param("district") String district);

// Use DTOs for read-only queries
@Query("SELECT new com.hack23.cia.dto.PoliticianSummary(p.id, p.firstName, p.lastName) " +
       "FROM Politician p WHERE p.active = true")
List<PoliticianSummary> findActivePoliticiansSummary();

// Pagination for large result sets
Page<Politician> findByParty(String party, Pageable pageable);
```

## Second-Level Cache
```yaml
spring:
  jpa:
    properties:
      hibernate:
        cache:
          use_second_level_cache: true
          region:
            factory_class: org.hibernate.cache.jcache.JCacheRegionFactory
        javax:
          cache:
            provider: org.ehcache.jsr107.EhcacheCachingProvider
```

## References
- Hibernate: https://hibernate.org/
- DATA_MODEL.md