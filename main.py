import requests
import json

def testCreate(balance = 5):
	url = "http://localhost:8080/mProj_war/accCreate?balance=" + str(balance) + "&first=Simon&last=Simonovic"
	print(url)
	r = requests.get(url=url)
	j = json.loads(r.text)
	print(j)
	return j["Id"]

def doTran(sender, receiver, sum = 5):
	url = "http://localhost:8080/mProj_war/accAccSumTransaction?from=" + str(sender) + "&to=" + str(receiver) + "&sum=" + str(sum)
	print(url)
	r = requests.get(url=url)
	j = json.loads(r.text)
	print(j)
	if j["result"] == "wrong":
		raise Exception("shoud be good")
	
	
def doTranErr(sender, receiver, sum = 5):
	url = "http://localhost:8080/mProj_war/accAccSumTransaction?from=" + str(sender) + "&to=" + str(receiver) + "&sum=" + str(sum)
	print(url)
	r = requests.get(url=url)
	j = json.loads(r.text)
	print(j)
	if j["result"] == "good":
		raise Exception("Should be wrong")
	
def accBalance(id, balance = 5):
	url = "http://localhost:8080/mProj_war/acc?id=" + str(id)
	print(url)
	r = requests.get(url=url)
	j = json.loads(r.text)
	print(j)
	if not j["balance"] == balance:
		raise Exception("Balace is not eq")
	
def accTrend(id, trend = 5):
	url = "http://localhost:8080/mProj_war/accTrend?id=" + str(id)
	print(url)
	r = requests.get(url=url)
	j = json.loads(r.text)
	print(j)
	if not j[0]["totalManipulation"] == trend:
		raise Exception("Trend is not eq")
	
def accConnected(source, destination, connected = True, path = 1):
	url = "http://localhost:8080/mProj_war/accConnected?from=" + str(source) + "&to=" + str(destination)
	print(url)
	r = requests.get(url=url)
	j = json.loads(r.text)
	print(j)
	if not j["Connected"] == connected:
		raise Exception("Connected is not eq")
	if not j["Path"] == path:
		raise Exception("Path is not eq")
	
def accConnections(id, connections = 0, neighbor = 0):
	url = "http://localhost:8080/mProj_war/accConnections?id=" + str(id)
	print(url)
	r = requests.get(url=url)
	j = json.loads(r.text)
	print(j)
	if not j["Connections"] == connections:
		raise Exception("Connections is not eq")
	if not j["Neighbor"] == neighbor:
		raise Exception("Neighbor is not eq")


if __name__ == '__main__':
	accA = testCreate(5) #77
	accB = testCreate(0) #78
	accC = testCreate(0) #79
	
	accBalance(accA, 5)
	accBalance(accB, 0)
	doTran(accA, accB)
	accBalance(accA, 0)
	accBalance(accB, 5)
	doTranErr(accA, accB)
	
	accTrend(accA, 5)
	accTrend(accB, 5)
	
	accConnected(accA, accB, True, 1)
	accConnected(accB, accC, False, 0)
	doTran(accB, accC, 3)
	accConnected(accA, accC, True, 2)
	
	accConnections(accA, 1, 1)
	accConnections(accB, 2, 2)
	doTran(accB, accA, 2)
	accConnections(accB, 3, 2)
