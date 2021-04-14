import os
import multiprocessing
import time

bidders = [
    {"nume":"Alex", "email":"alex@yahoo.com","telefon":"0123456789"},
    {"nume":"Andrei", "email":"andrei@yahoo.com","telefon":"9876543210"},
    {"nume":"Alex", "email":"alex@yahoo.com","telefon":"0123456789"},
    {"nume":"Andrei", "email":"andrei@yahoo.com","telefon":"9876543210"},
    {"nume":"Iulian", "email":"iulian@yahoo.com","telefon":"0918273645"},
    {"nume":"Razvan", "email":"alex@yahoo.com","telefon":"0123456789"},
    {"nume":"Vio", "email":"andrei@yahoo.com","telefon":"9876543210"},
    {"nume":"Cosmin", "email":"alex@yahoo.com","telefon":"0123456789"},
    {"nume":"Mike", "email":"andrei@yahoo.com","telefon":"9876543210"},
    {"nume":"Mihaita", "email":"alex@yahoo.com","telefon":"0123456789"},
    {"nume":"Alin", "email":"andrei@yahoo.com","telefon":"9876543210"},
    {"nume":"Simona", "email":"alex@yahoo.com","telefon":"0123456789"},
    {"nume":"Benzema", "email":"alex@yahoo.com","telefon":"0123456789"},
    {"nume":"Ronaldo", "email":"andrei@yahoo.com","telefon":"9876543210"},
    {"nume":"Frederic", "email":"iulian@yahoo.com","telefon":"0918273645"},
    {"nume":"Nicolae", "email":"alex@yahoo.com","telefon":"0123456789"},
    {"nume":"Nicusor", "email":"andrei@yahoo.com","telefon":"9876543210"},
    {"nume":"Basescu", "email":"alex@yahoo.com","telefon":"0123456789"},
    {"nume":"Obama", "email":"andrei@yahoo.com","telefon":"9876543210"},
    {"nume":"Florin", "email":"andrei@yahoo.com","telefon":"9876543210"}
]

def bidder(nume, email, telefon):
    os.system("java -jar BidderMicroservice_jar/BidderMicroservice.jar {} {} {}".format(nume,email,telefon))

def startMessageProcessing():
    os.system("java -jar MessageProcessorMicroservice_jar/MessageProcessorMicroservice.jar")

def startBidderProcessing():
    os.system("java -jar BiddingProcessorMicroservice_jar/BiddingProcessorMicroservice.jar")

def startAuctioneer():
    os.system("java -jar AuctioneerMicroservice_jar/AuctioneerMicroservice.jar")

if __name__ == "__main__":
    multiprocessing.Process(target=startBidderProcessing).start()
    multiprocessing.Process(target=startMessageProcessing).start()
    multiprocessing.Process(target=startAuctioneer).start()
    for i in range(0,len(bidders)):
        #time.sleep(0.1)
        multiprocessing.Process(target=bidder, args=(bidders[i]["nume"], bidders[i]["email"], bidders[i]["telefon"],)).start()