import requests
import os
import pytest
import time
import subprocess

API_GATEWAY_URL = "http://" + os.getenv("BASE_HOST", "localhost") + ":8197"
BROWSER_URL =  "http://" + os.getenv("BASE_HOST", "localhost") + ":8198"

@pytest.mark.run(order=0)
def test_request():
    response = requests.get(f"{API_GATEWAY_URL}/request")
    assert response.status_code == 200, f"Unexpected status code: {response.status_code}"
    assert 'text/plain' in response.headers['Content-Type'], "Expected Content-Type is text/plain"
    try:
        response_data = response.json()
    except ValueError:
        assert False, "Response is not a valid JSON"
    
    assert isinstance(response_data, list), f"Expected a list, got {type(response_data).__name__}"
    
    assert len(response_data) == 2, f"Expected array length 2, got {len(response_data)}"

    assert "ipAddress" in response_data[0]
    assert "processes" in response_data[0]
    assert "diskSpace" in response_data[0]
    assert "lastBootTime" in response_data[0]

@pytest.mark.run(order=1)
def test_get_state():
    response = requests.get(f"{API_GATEWAY_URL}/state")
    assert response.status_code == 200, f"Unexpected status code: {response.status_code}"
    assert 'text/plain' in response.headers['Content-Type'], "Expected Content-Type is text/plain"

    response_data = response.text
    assert "INIT" == response_data, f"Unexpected response body: {response_data}"

@pytest.mark.run(order=2)
def test_put_state():
    response = requests.put(f"{API_GATEWAY_URL}/state", data="RUNNING")
    assert response.status_code == 409, f"Unexpected status code: {response.status_code}"
    response_data = response.text

    assert response_data == "The system cannot transition directly from INIT to RUNNING"

    response = requests.post(f"{API_GATEWAY_URL}/state/running")
    assert response.status_code == 204, f"Unexpected status code: {response.status_code}"

    response = requests.get(f"{API_GATEWAY_URL}/state")
    response_data = response.text
    assert "RUNNING" == response_data, f"Unexpected response body: {response_data}"

    response = requests.put(f"{API_GATEWAY_URL}/state", data="PAUSED")
    assert response.status_code == 204, f"Unexpected status code: {response.status_code}"

    response = requests.get(f"{API_GATEWAY_URL}/state")
    response_data = response.text
    assert "PAUSED" == response_data, f"Unexpected response body: {response_data}"
    
    response = requests.put(f"{API_GATEWAY_URL}/state", data="INVALID")
    assert response.status_code == 400, f"Unexpected status code: {response.status_code}"

@pytest.mark.run(order=3)
def test_get_log():
    response = requests.get(f"{API_GATEWAY_URL}/run-log")
    assert response.status_code == 200, f"Unexpected status code: {response.status_code}"
    assert 'text/plain' in response.headers['Content-Type'], "Expected Content-Type is text/plain"

    response_data = response.text
    assert "INIT->RUNNING" in response_data
    assert "RUNNING->PAUSED" in response_data


@pytest.mark.run(order=4)
def test_request_count():
    response = requests.get(f"{API_GATEWAY_URL}/request-count/api")
    assert response.status_code == 200, f"Unexpected status code: {response.status_code}"
    assert 'text/plain' in response.headers['Content-Type'], "Expected Content-Type is text/plain"

    response_data = response.text
    assert "1" == response_data, f"Unexpected response body: {response_data}"

    response = requests.get(f"{API_GATEWAY_URL}/request-count/browser")
    assert response.status_code == 200, f"Unexpected status code: {response.status_code}"
    assert 'text/plain' in response.headers['Content-Type'], "Expected Content-Type is text/plain"

    response_data = response.text
    assert "0" == response_data, f"Unexpected response body: {response_data}"

    response = requests.put(f"{API_GATEWAY_URL}/request-count/browser")
    assert response.status_code == 204, f"Unexpected status code: {response.status_code}"
    
    response = requests.get(f"{API_GATEWAY_URL}/request-count/browser")
    response_data = response.text
    assert "1" == response_data, f"Unexpected response body: {response_data}"
    
    response = requests.get(f"{API_GATEWAY_URL}/request-count/api")
    response_data = response.text
    assert "1" == response_data, f"Unexpected response body: {response_data}"

@pytest.mark.run(order=5)
def test_shutdown_service():
    response = requests.put(f"{API_GATEWAY_URL}/state", data="SHUTDOWN")
    assert response.status_code == 204, f"Unexpected status code: {response.status_code}"

    time.sleep(5)
    
    try:
        result = subprocess.run(
            ['docker', 'ps', '--filter', 'name=compse140-exercise-', '--format', '{{.Names}}'],
            stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True
        )
        
        running_containers = result.stdout.strip().splitlines()
        assert not running_containers, f"Containers are still running: {running_containers}"
    
    except subprocess.CalledProcessError as e:
        assert False, f"Error checking running containers: {e}"
