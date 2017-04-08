import flask
import socket
import urllib3


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
svetlo4 = Light("3")
svetlo5 = Light("4")

lights = []
lights.extend([svetlo1, svetlo2, svetlo3, svetlo4, svetlo5])

app = flask.Flask(__name__)


@app.route("/lights/API", methods=(["GET", "POST"]))
def light():
    if flask.request.method == "GET":
        hashmap = ({"id": svetlo1.get_id(), "state": svetlo1.get_state()},
                   {"id": svetlo2.get_id(), "state": svetlo2.get_state()},
                   {"id": svetlo3.get_id(), "state": svetlo3.get_state()},
                   {"id": svetlo4.get_id(), "state": svetlo4.get_state()},
                   {"id": svetlo5.get_id(), "state": svetlo5.get_state()})
        jsonobj = flask.json.dumps(hashmap)
        return jsonobj

    elif flask.request.method == "POST":
        slovnik = flask.json.dumps(flask.request.form)
        slovnik = flask.json.loads(slovnik)
        print(slovnik)
        id_svetla = int(slovnik["id"][0])
        zmena = slovnik["state"]
        print(zmena)
        if zmena[0] == "ON":
            zmena[0] = "ON "
        lights[id_svetla].change_state(zmena[0])
        """
        http = urllib3.PoolManager()
        url = 'http://192.168.1.123:80'
        fields = str(svetlo1.get_state()) + " " + str(svetlo2.get_state()) + " " + str(
            svetlo3.get_state()) + " "
        response = http.request('POST', url, headers={"Content-Type":"text/plain"}, fields="sdfjhd")
        """
        http = urllib3.PoolManager()
        if zmena[0] == "ON ":
            status = "on"
        else:
            status = "off"
        url = 'http://10.10.4.127:8080/api/action?token=easy_token&type=light&status=' + status + '&find_by=id&find_v=' + str(
            id_svetla + 1)
        response = http.request('GET', url)
        return "status světla byl změněn"


@app.route("/user/API", methods=(["GET", "POST"]))
def user():
    pass


@app.route("/door/api", methods=(["GET", "POST"]))
def door():
    if flask.request.method == "GET":
        http = urllib3.PoolManager()
        url = 'http://10.10.4.127:8080/api/get?token=easy_token&type=door&find_by=type'
        hashmap = http.request('GET', url)
        return hashmap

    elif flask.request.method == "POST":
        slovnik = flask.json.dumps(flask.request.form)
        slovnik = flask.json.loads(slovnik)
        print(slovnik)
        if slovnik["state"][0] == "ON":
            zmena = "on"
        else:
            zmena = "off"

        print(zmena)
        http = urllib3.PoolManager()
        url = 'http://10.10.4.127:8080/api/action?token=easy_token&type=light&status=' + zmena + '&find_by=id&find_v=' + \
              str(slovnik["id"][0])
        response = http.request('GET', url)
        return "změna dvěři"


@app.route("/", methods=(["GET", "POST"]))
def get():
    return str(svetlo1.get_state()) + " " + str(svetlo2.get_state()) + " " + str(
        svetlo3.get_state()) + " " + str(svetlo4.get_state()) + " " + str(svetlo5.get_state()) + " "


@app.route("/api/action", methods=(["GET", "POST"]))
def apiget():
    id = flask.request.args.get("id")
    state = flask.request.args.get("state")
    if state == "on":
        state = "ON "
    else:
        state = "OFF"
    lights[int(id) - 1].change_state(state)
    resp = flask.Response("OK")
    resp.headers['Access-Control-Allow-Origin'] = '*'
    return resp
    return


app.run(get_ip_address())
