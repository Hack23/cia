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

guardduty_response = __import__('guardduty_response')

class TestCompliance(unittest.TestCase):

    def test_scenario0(self):

# https://docs.aws.amazon.com/guardduty/latest/ug/guardduty_finding-types-active.html        
# findings = https://docs.aws.amazon.com/guardduty/latest/ug/get-findings.html#get-findings-response-syntax
        
                  
#           {
#         "version": "0",
#         "id": "cd2d702e-ab31-411b-9344-793ce56b1bc7",
#         "detail-type": "GuardDuty Finding",
#         "source": "aws.guardduty",
#         "account": "111122223333",
#         "time": "1970-01-01T00:00:00Z",
#         "region": "us-east-1",
#         "resources": [],
#         "detail": {COMPLETE_GUARDDUTY_FINDING_JSON}
#        }   
                
        event = "{}"
        eventData = json.loads (event)
        context = ""
        
        guardduty_response.handler(eventData,context)
 