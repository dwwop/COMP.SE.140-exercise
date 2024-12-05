import requests
import os

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


BASE_URL = "http://" + os.getenv("BASE_HOST", "localhost") + ":8197"

def test_get_state():
    response = requests.get(f"{BASE_URL}/state")
    assert response.status_code == 200, f"Unexpected status code: {response.status_code}"
    assert response.headers['Content-Type'] == 'text/plain', "Expected Content-Type is text/plain"

    response_data = response.text
    assert "INIT" == response_data, f"Unexpected response body: {response_data}"

