from __future__ import print_function
from botocore.exceptions import ClientError
import json
import datetime
import boto3
import os
def handler(event, context):
  
  print("log -- Event: %s " % json.dumps(event))
  
  response = "Error auto-remediating the finding."

  try:
    
    # Set Clients
    ec2 = boto3.client('ec2')

    # Current Time
    time = datetime.datetime.utcnow().isoformat()

    # Send Response Email
    response = "Macie Remediation"     
    sns = boto3.client('sns')
    sns.publish(
      TopicArn='macie_response',    
      Message=response
    )
  except ClientError as e:
    print(e)

  print("log -- Response: %s " % response)
  return response