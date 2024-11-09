import json
import sys

# Load the threat model file
def load_threat_model(file_path):
    with open(file_path, 'r') as file:
        return json.load(file)

# Check if all threats are mitigated in the updated structure
def check_threat_mitigation(threat_model_file):
    threat_model = load_threat_model(threat_model_file)
    unmitigated_threats = []

    # Traverse diagrams and cells to find threats
    for diagram in threat_model["detail"].get("diagrams", []):
        for cell in diagram.get("cells", []):
            threats = cell["data"].get("threats", [])
            for threat in threats:
                if threat["status"].lower() != "mitigated":
                    unmitigated_threats.append({
                        "title": threat["title"],
                        "status": threat["status"],
                        "diagram": diagram["title"],
                        "component": cell["data"]["name"]
                    })

    if unmitigated_threats:
        print("ERROR: The following threats are unmitigated and must be resolved before committing:")
        for threat in unmitigated_threats:
            print(f" - {threat['title']} (Status: {threat['status']}) in diagram '{threat['diagram']}', component '{threat['component']}'")
        sys.exit(1)  # Exit with error to block the commit

# Run mitigation check
check_threat_mitigation('threatmodel/model1.json')
