---
name: aws-cloudwatch-monitoring
description: AWS CloudWatch metrics, alarms, dashboards, log insights, and application monitoring for the CIA platform
license: Apache-2.0
---

# AWS CloudWatch Monitoring

## Purpose

This skill provides guidance for monitoring the CIA platform using AWS CloudWatch, including metrics collection, alarm configuration, dashboard creation, log analysis, and performance troubleshooting. Ensures proactive detection of issues and compliance with monitoring requirements.

## When to Use

### ✅ Use this skill when:
- Setting up monitoring for new services or features
- Creating custom metrics for business KPIs
- Configuring alarms for critical thresholds
- Building operational dashboards
- Analyzing application logs for errors
- Troubleshooting performance issues
- Implementing distributed tracing
- Meeting compliance monitoring requirements

### ❌ Don't use this skill for:
- Application code implementation (use stack-specialist)
- Security incident response (use threat-modeling)
- Database performance tuning (use postgresql-operations)
- CI/CD pipeline configuration (use github-actions-workflows)

## Patterns & Examples

### Custom Metrics for Political Data

```java
// Spring Boot - Micrometer integration with CloudWatch
@Service
public class PoliticianMetricsService {
    private final MeterRegistry meterRegistry;
    
    @Autowired
    public PoliticianMetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }
    
    public void recordRiskScoreCalculation(String politicianId, double score) {
        // Custom metric: risk score distribution
        meterRegistry.gauge("cia.politician.risk_score", 
            Tags.of("politician_id", politicianId), 
            score);
            
        // Counter: total risk calculations
        meterRegistry.counter("cia.risk_calculations.total",
            Tags.of("result", score > 70 ? "high" : "normal"))
            .increment();
    }
    
    public void recordDataSourceRefresh(String source, boolean success) {
        // Timer: data source refresh duration
        Timer.builder("cia.datasource.refresh.duration")
            .tag("source", source)
            .tag("status", success ? "success" : "failure")
            .register(meterRegistry)
            .record(() -> {
                // Refresh logic here
            });
    }
}
```

### CloudWatch Alarms Configuration

```yaml
# CloudFormation/Terraform - Critical Alarms
Resources:
  HighErrorRateAlarm:
    Type: AWS::CloudWatch::Alarm
    Properties:
      AlarmName: CIA-HighErrorRate
      AlarmDescription: Alert when error rate exceeds 5%
      MetricName: Errors
      Namespace: AWS/Lambda
      Statistic: Sum
      Period: 300
      EvaluationPeriods: 2
      Threshold: 50
      ComparisonOperator: GreaterThanThreshold
      AlarmActions:
        - !Ref SNSTopicARN
      
  DatabaseConnectionPoolExhausted:
    Type: AWS::CloudWatch::Alarm
    Properties:
      AlarmName: CIA-DBConnectionPoolExhausted
      MetricName: DatabaseConnectionsInUse
      Namespace: CIA/Database
      Statistic: Maximum
      Period: 60
      EvaluationPeriods: 1
      Threshold: 90  # 90% of max connections
      ComparisonOperator: GreaterThanThreshold
      
  RiskScoreCalculationLatency:
    Type: AWS::CloudWatch::Alarm
    Properties:
      AlarmName: CIA-RiskScoreHighLatency
      MetricName: RiskScoreCalculationDuration
      Namespace: CIA/Application
      Statistic: p99
      Period: 300
      EvaluationPeriods: 2
      Threshold: 5000  # 5 seconds
      ComparisonOperator: GreaterThanThreshold
```

### CloudWatch Dashboard

```json
{
  "widgets": [
    {
      "type": "metric",
      "properties": {
        "title": "CIA Platform Health",
        "metrics": [
          [ "CIA/Application", "RequestCount", { "stat": "Sum" } ],
          [ ".", "ErrorCount", { "stat": "Sum", "color": "#d62728" } ],
          [ ".", "ResponseTime", { "stat": "Average" } ]
        ],
        "period": 300,
        "region": "eu-north-1",
        "yAxis": {
          "left": { "min": 0 }
        }
      }
    },
    {
      "type": "metric",
      "properties": {
        "title": "Political Data Processing",
        "metrics": [
          [ "CIA/DataIngestion", "RiksdagenAPICallsTotal" ],
          [ ".", "ValDataRefreshSuccess" ],
          [ ".", "WorldBankDataSync" ]
        ]
      }
    },
    {
      "type": "log",
      "properties": {
        "title": "Recent Errors",
        "query": "SOURCE '/aws/lambda/cia-app' | fields @timestamp, @message | filter @message like /ERROR/ | sort @timestamp desc | limit 20",
        "region": "eu-north-1"
      }
    }
  ]
}
```

### Log Insights Queries

```sql
-- Top 10 slowest API endpoints
fields @timestamp, request.path, request.duration_ms
| filter request.duration_ms > 1000
| sort request.duration_ms desc
| limit 10

-- Error rate by endpoint
fields request.path, response.status_code
| filter response.status_code >= 500
| stats count() as error_count by request.path
| sort error_count desc

-- Risk score calculation performance
fields @timestamp, politician_id, calculation_duration_ms
| filter event_type = "risk_score_calculated"
| stats avg(calculation_duration_ms) as avg_duration, 
        max(calculation_duration_ms) as max_duration,
        count() as total_calculations
  by bin(5m)
```

## ISMS Compliance Mapping

### ISO 27001:2022 Annex A Controls

**A.8.16 - Monitoring activities**
- Continuous monitoring of system activities
- Log aggregation and analysis
- Anomaly detection and alerting

**A.8.15 - Logging**
- Centralized log management
- Log retention policies (90 days minimum)
- Log integrity and protection

**A.8.8 - Management of technical vulnerabilities**
- Performance degradation monitoring
- Capacity planning metrics
- Availability tracking

### NIST Cybersecurity Framework 2.0

**Detect (DE)**
- DE.CM-01: Network monitored for anomalous activity
- DE.CM-07: Monitoring for unauthorized activity
- DE.AE-03: Event data aggregated and correlated

**Respond (RS)**
- RS.AN-03: Analysis performed to establish impact
- RS.CO-02: Incidents reported per established criteria

### CIS Controls v8

**Control 8: Audit Log Management**
- 8.2: Collect audit logs
- 8.3: Standardize time synchronization
- 8.11: Conduct audit log reviews

**Control 12: Network Infrastructure Management**
- 12.8: Establish and maintain dedicated network infrastructure

## Hack23 ISMS Policy References

- [Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md) - Section on Monitoring
- [Information Security Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md) - Continuous monitoring requirements

## References

### Internal CIA Documentation
- [SECURITY_ARCHITECTURE.md](/SECURITY_ARCHITECTURE.md) - Monitoring architecture
- [ARCHITECTURE.md](/ARCHITECTURE.md) - System components to monitor

### AWS Documentation
- [CloudWatch User Guide](https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/)
- [CloudWatch Logs Insights](https://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/AnalyzingLogData.html)
- [X-Ray Developer Guide](https://docs.aws.amazon.com/xray/latest/devguide/)

## Remember

- **Proactive monitoring**: Set alarms before incidents occur
- **Context-rich metrics**: Tag metrics with relevant dimensions
- **Cost optimization**: Use metric filters to reduce costs
- **Log retention**: Comply with 90-day minimum retention
- **Dashboard visibility**: Operational dashboards for NOC
- **Alerting hygiene**: Reduce false positives, tune thresholds
