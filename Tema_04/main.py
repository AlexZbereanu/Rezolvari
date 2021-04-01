import os

from flask import *

from data.data import Data
from services.Cheltuieli import Cheltuieli

service = Cheltuieli()
cheltuiala1 = Data("1", "13", "haine", "pantofi")
service.add_cheltuiala(cheltuiala1)
cheltuiala1 = Data("2", "12", "haine", "sapca")
service.add_cheltuiala(cheltuiala1)
cheltuiala1 = Data("3", "11", "haine", "blug")
service.add_cheltuiala(cheltuiala1)

app = Flask(__name__)
app._static_folder = os.path.abspath("templates/static")
app.config['TEMPLATES_AUTO_RELOAD'] = True
app.config['SEND_FILE_MAX_AGE_DEFAULT'] = 0


@app.route('/', methods=['GET'])
def index():
    return render_template('/index.html')


@app.route('/afiseaza', methods=['GET', 'PUT'])
def afiseaza():
    cheltuieli = service.get_all_cheltuieli()
    total_cheltuieli = 0
    code = 200
    if cheltuieli is None:
        code = 204
        return "Nimic de afisat<br><br><br><a href=""/"">Inapoi la pagina principala</a>"
    for cheltuiala in cheltuieli:
        total_cheltuieli += int(cheltuiala.Pret)
    return render_template('/afiseaza.html', cheltuieli=cheltuieli, total=total_cheltuieli), code


@app.route('/adauga', methods=['POST', 'GET'])
def adauga():
    if request.method == 'GET':
        return render_template('/adauga.html')
    elif request.method == 'POST':
        id = request.form['id']
        pret = request.form['pret']
        tip = request.form['tip']
        descriere = request.form['descriere']
        cheltuiala = Data(id, pret, tip, descriere)
        message = ''
        if service.add_cheltuiala(cheltuiala):
            code = 200
            message = 'Succes adaugare'
        else:
            code = 400
            message = "Insucces adaugare"
        return render_template('/index.html', message=message), code


@app.route('/actualizare_cheltuiala', methods=['PUT'])
def actualizare():
    data = request.get_json()
    cheltuieli = service.get_all_cheltuieli()
    total_cheltuieli = 0
    for cheltuiala in cheltuieli:
        total_cheltuieli += int(cheltuiala.Pret)
    if service.update_cheltuiala(data['id_c'], data['pret'], data['tip'], data['descriere']):
        return redirect('/afiseaza')
    else:
        abort(404)


@app.route('/sterge', methods=['POST'])
def sterge():
    id = request.form['id']
    if service.delete_cheltuiala(id):
        code = 200
    else:
        code = 400

    return redirect('/afiseaza')


if __name__ == "__main__":
    app.run(debug=True)
