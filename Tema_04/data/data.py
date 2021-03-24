class Data:

    def __init__(self, id_c, pret, tip, descriere):
        self.id_c = id_c
        self.pret = pret
        self.tip = tip
        self.descriere = descriere

    def getId(self):
        return self.id_c

    def setId(self, id_c):
        self.id_c = id_c

    Id = property(getId, setId)

    def getPret(self):
        return self.pret

    def setPret(self, pret):
        self.pret = pret

    Pret = property(getPret, setPret)

    def getTip(self):
        return self.tip

    def setTip(self, tip):
        self.tip = tip

    Tip = property(getTip, setTip)

    def getDescriere(self):
        return self.descriere

    def setDescriere(self, descriere):
        self.descriere = descriere

    Descriere = property(getDescriere, setDescriere)
