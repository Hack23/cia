import sys
import unittest
import json
try:
    from unittest.mock import MagicMock, patch, ANY
except ImportError:
    import mock
    from mock import MagicMock, patch, ANY
import botocore
from botocore.exceptions import ClientError

ec2_client_mock = MagicMock()
sns_client_mock = MagicMock()

class Boto3Mock():
    def client(self, client_name, *args, **kwargs):
        if client_name == 'ec2':
            return ec2_client_mock        
        elif client_name == 'sns':
            return sns_client_mock
        
        else:
            raise Exception("Attempting to create an unknown client")

sys.modules['boto3'] = Boto3Mock()

macie_response = __import__('macie_response')

class TestCompliance(unittest.TestCase):

    def test_scenario0(self):
        
# alert = https://docs.aws.amazon.com/macie/latest/userguide/macie-cloudwatch.html

# https://docs.aws.amazon.com/AmazonCloudWatch/latest/events/EventTypes.html#macie-event-types
        
                  
#{
#  "version": "0",
#  "id": "CWE-event-id",
#  "detail-type": "Macie Alert",
#  "source": "aws.macie",
#  "account": "111122223333",
#  "time": "2017-04-24T22:28:49Z",
#  "region": "us-east-1",
#  "resources": [
#    "arn:aws:macie:us-east-1:111122223333:trigger/trigger_id/alert/alert_id",
#    "arn:aws:macie:us-east-1:111122223333:trigger/trigger_id"
#  ],
#  "detail": {
#    "notification-type": "ALERT_CREATED",
#    "name": "Scanning bucket policies",
#    "tags": [
#      "Custom_Alert",
#      "Insider"
#    ],
#    "url": "https://lb00.us-east-1.macie.aws.amazon.com/111122223333/posts/alert_id",
#    "alert-arn": "arn:aws:macie:us-east-1:111122223333:trigger/trigger_id/alert/alert_id",
#    "risk-score": 8,
#    "trigger": {
#      "rule-arn": "arn:aws:macie:us-east-1:111122223333:trigger/trigger_id",
#      "alert-type": "basic",
#      "created-at": "2017-01-02 19:54:00.644000",
#      "description": "Alerting on failed enumeration of large number of bucket policies",
#      "risk": 8
#    },
#    "created-at": "2017-04-18T00:21:12.059000",
#    "actor": "555566667777:assumed-role:superawesome:aroaidpldc7nsesfnheji",
#    "summary": {ALERT_DETAILS_JSON}
#  }
#}                
        event = "{}"
        eventData = json.loads (event)
        context = ""
        
        macie_response.handler(eventData,context)
 