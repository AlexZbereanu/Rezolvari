import abc


class ICheltuieli:
    @abc.abstractmethod
    def add_cheltuiala(self, cheltuiala):
        pass

    @abc.abstractmethod
    def get_cheltuiala(self, id_c):
        pass

    @abc.abstractmethod
    def get_all_cheltuieli(self):
        pass

    @abc.abstractmethod
    def delete_cheltuiala(self, id_c):
        pass

    @abc.abstractmethod
    def update_cheltuiala(self, id_c, pret, tip, descriere):
        pass
