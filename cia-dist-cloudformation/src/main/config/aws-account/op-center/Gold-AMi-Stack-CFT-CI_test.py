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

##############
# Parameters #
##############

#############
# Main Code #
#############

config_client_mock = MagicMock()
sts_client_mock = MagicMock()
elbv2_client_mock = MagicMock()

class Boto3Mock():
    def client(self, client_name, *args, **kwargs):
        if client_name == 'config':
            return config_client_mock
        elif client_name == 'sts':
            return sts_client_mock
        elif client_name == 'elbv2':
            return elbv2_client_mock
        else:
            raise Exception("Attempting to create an unknown client")

sys.modules['boto3'] = Boto3Mock()

class TestCompliance(unittest.TestCase):


    def test_meta_data_json_str(self):
        accountMetadataStr = "{\"Account_ID_1\": [{ \"Region\": \"region_1\", \"Key\": \"kms_arn_1\"},{ \"Region\": \"region_2\", \"Key\": \"kms_arn_2\"}]}"
        accountMetadata = json.loads (accountMetadataStr)
                        
        for accountID, regions in accountMetadata.items():                      
            print (accountID);
            for region in regions:
                print (region['Region']);
                print (region['Key']);