from flask import *
import socket


def get_ip_address():
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.connect(("8.8.8.8", 80))
    return s.getsockname()[0]

class Light:
    def __init__(self, id=0):
        self.id = str(id)
        self.state = "OFF"

    def change_state(self, new_state):
        self.state = new_state

    def get_state(self):
        return self.state

    def get_id(self):
        return self.id


svetlo1 = Light("0")
svetlo2 = Light("1")
svetlo3 = Light("2")

lights = []
lights.extend([svetlo1, svetlo2, svetlo3])

app = Flask(__name__)


@app.route("/lights/API", methods=(["GET", "POST"]))
def light():
    if request.method == "GET":
        hashmap = ({"id": svetlo1.get_id(), "state": svetlo1.get_state()},
                   {"id": svetlo2.get_id(), "state": svetlo2.get_state()},
                   {"id": svetlo3.get_id(), "state": svetlo3.get_state()},)
        jsonobj = json.dumps(hashmap)
        return jsonobj

    elif request.method == "POST":
        slovnik = json.dumps(request.form)
        slovnik = json.loads(slovnik)
        id_svetla = int(slovnik["id"])
        lights[id_svetla].change_state(slovnik["state"])
        return "status světla byl změněn"


@app.route("/user/API")
def user():
    pass



app.run(get_ip_address())
