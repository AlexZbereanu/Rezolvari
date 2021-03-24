from data.data import Data
from interfaces.ICheltuieli import ICheltuieli


class Cheltuieli(ICheltuieli):
    def __init__(self):
        self.cheltuieli = {}

    def add_cheltuiala(self, cheltuiala):
        if cheltuiala in self.cheltuieli:
            return False
        self.cheltuieli[cheltuiala.id_c] = cheltuiala
        return True

    def get_cheltuiala(self, id_c):
        if id_c in self.cheltuieli.keys():
            return self.cheltuieli[id_c]
        else:
            return None

    def get_all_cheltuieli(self):
        result = []
        if self.cheltuieli is None:
            return False
        for cheltuiala in self.cheltuieli:
            result = self.cheltuieli.values()
            return result

    def delete_cheltuiala(self, id_c):
        if self.cheltuieli[id_c] is None:
            return False
        else:
            self.cheltuieli.pop(id_c)
            return True

    def update_cheltuiala(self, id_c, pret, tip, descriere):
        if self.cheltuieli[id_c] == "":
            return False
        data = Data(id_c, pret, tip, descriere)
        self.cheltuieli[id_c] = data
        return True
