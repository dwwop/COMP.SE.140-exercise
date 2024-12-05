import requests
import os
import pytest

BASE_URL = "http://" + os.getenv("BASE_HOST", "localhost") + ":8197"

def test_request():
    response = requests.get(f"{BASE_URL}/request")
    assert response.status_code == 200, f"Unexpected status code: {response.status_code}"
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

@pytest.mark.run(order=0)
def test_get_state():
    response = requests.get(f"{BASE_URL}/state")
    assert response.status_code == 200, f"Unexpected status code: {response.status_code}"
    assert 'text/plain' in response.headers['Content-Type'], "Expected Content-Type is text/plain"

    response_data = response.text
    assert "INIT" == response_data, f"Unexpected response body: {response_data}"

@pytest.mark.run(order=1)
def test_put_state():
    response = requests.put(f"{BASE_URL}/state", json={'state': 'RUNNING'})
    assert response.status_code == 409, f"Unexpected status code: {response.status_code}"
    try:
        response_data = response.json()
    except ValueError:
        assert False, "Response is not a valid JSON"
    
    assert response_data["errorMessage"] == "The system cannot transition directly from INIT to RUNNING"

    response = requests.post(f"{BASE_URL}/state/running")
    assert response.status_code == 204, f"Unexpected status code: {response.status_code}"

    response = requests.get(f"{BASE_URL}/state")
    response_data = response.text
    assert "RUNNING" == response_data, f"Unexpected response body: {response_data}"

    response = requests.put(f"{BASE_URL}/state", json={'state': 'PAUSED'})
    assert response.status_code == 204, f"Unexpected status code: {response.status_code}"

    response = requests.get(f"{BASE_URL}/state")
    response_data = response.text
    assert "PAUSED" == response_data, f"Unexpected response body: {response_data}"
    
    response = requests.put(f"{BASE_URL}/state", json={'state': 'INVALID'})
    assert response.status_code == 400, f"Unexpected status code: {response.status_code}"

@pytest.mark.run(order=2)
def test_get_log():
    response = requests.get(f"{BASE_URL}/run-log")
    assert response.status_code == 200, f"Unexpected status code: {response.status_code}"
    assert 'text/plain' in response.headers['Content-Type'], "Expected Content-Type is text/plain"

    response_data = response.text
    assert "INIT->RUNNING" in response_data
    assert "RUNNING->PAUSED" in response_data
