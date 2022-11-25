import mysql.connector
import pyodbc
import pandas as pd
import requests
from statsmodels.tsa.ar_model import AutoReg
from flask import Flask
from markupsafe import escape

#Criando o app
app = Flask(__name__)

#Conectando com o banco
# mydb = mysql.connector.connect(
#   host="bpserver-analytics.database.windows.net",
#   user="bp",
#   password="r00t!analytics",
#   database="bpdb-analytics"
# )
server='bpserver-analytics.database.windows.net'
database="bpdb-analytics"
username="bp"
password="r00t!analytics"
cnxm=pyodbc.connect('DRIVER={ODBC Driver 17 for SQL Server};SERVER='+server+';DATABASE='+database+';ENCRYPT=yes;UID='+username+';PWD='+ password)
cursor = cnxm.cursor()

#Chave da api de temperatura
API_KEY = "1e6b5af5c2d0e4098d273243fa6871c7"

#Permitir acesso do endpoint pelo front
@app.after_request
def after_request(response):
    response.headers["Access-Control-Allow-Origin"] = "*" 
    response.headers["Access-Control-Allow-Credentials"] = "true"
    response.headers["Access-Control-Allow-Methods"] = "POST, GET, OPTIONS, PUT, DELETE"
    response.headers["Access-Control-Allow-Headers"] = "Accept, Content-Type, Content-Length, Accept-Encoding, X-CSRF-Token, Authorization"
    return response

#Endpoint para gerar a previsao do valor da cesta basica nos proximos 4 meses
@app.route("/Alimento/<estado>")
def hello_world(estado):
    resultado = pd.read_sql(f"select * from alimento where estado = '{estado}'", cnxm)
    if len(resultado)>0:
        resultado.set_index('mes', inplace=True)
        data = resultado['preco']
        model = AutoReg(data, lags=1)
        model_fit = model.fit()
        yhat = model_fit.predict(len(data), 36)
        i=0
        vetorMeses=[]
        while i<4:
            diaAtual = str(yhat.index[i])[0:10]
            vetorMeses.append([diaAtual[0:5]+diaAtual[8:10]+diaAtual[4:7] , round(yhat[i],2)])
            i+=1
        return vetorMeses    
    else:
        return []

#Endpoint para gerar a previsao da temperatura nos proximos 7 dias
@app.route("/Clima/<cidade>")
def clima(cidade):
    link = f"https://api.openweathermap.org/data/2.5/forecast?q={escape(cidade)}&appid={API_KEY}&lang=pt_br"
    requisicao = requests.get(link)
    requisicao_dic = requisicao.json()
    vetorClima=[]
    vetorData=[]
    vetorTemperatura=[]
    for teste in requisicao_dic['list']:
        vetorClima.append(round(teste['main']['temp'] - 273.15,2 ))
        vetorData.append(teste['dt_txt'])
        vetorTemperatura.append({"data":teste['dt_txt'], "temp":round(teste['main']['temp'] - 273.15,2)})
    data = {
        'temp': vetorClima
    }
    df = pd.DataFrame(data, index=vetorData)
    model = AutoReg(df, lags=1)
    model_fit = model.fit()
    yhat = model_fit.predict(len(df), len(df)+11)
    i=0
    while i<12:
        vetorTemperatura.append({"data":str(yhat.index[i]), "temp":round(yhat[i],2)})
        i+=1
    vetorDados=[]
    vetorDiasTemp=[]
    for dado in vetorTemperatura:
        if not dado['data'][0:10] in vetorDiasTemp:
            vetorDiasTemp.append(dado['data'][0:10])
    for dia in vetorDiasTemp:
        soma=0
        contador=0
        for dado2 in vetorTemperatura:
            if dia==dado2['data'][0:10]:
                contador+=1
                soma+=dado2['temp']
        vetorDados.append([dia, round(soma/contador,2)])
    return vetorDados