import requests
import json
import pandas as pd
from datetime import datetime
import csv

session = requests.Session()

url = 'https://webcat-web.gva.es/webcat_web/datosOnlineRvvcca/obtenerEstacionesPorMunicipio'
datos = {'codMunicipio': "131", 'codProvincia': "46"}
headers = {'content-type': 'application/json', 'charset': 'utf-8'}

res1 = requests.post(url, data=json.dumps(datos), headers=headers)

url2 = 'https://webcat-web.gva.es/webcat_web/datosOnlineRvvcca/obtenerEstacionById' 
datos2 = {'idEstacion': 5}
res2 = requests.post(url2, data=json.dumps(datos2), headers=headers)

today = datetime.today()
mes = today.month
if len(str(mes)) == 1:
    mes = f'0{mes}'

datos3 = {'fecha':f'{mes}-{today.year}'}
url3 = 'https://webcat-web.gva.es/webcat_web/datosOnlineRvvcca/obtenerTablaPestanyaConsulta'
res3 = requests.post(url3, data=json.dumps(datos3), headers=headers)

dic = dict(res3.json())

# %%
df = pd.DataFrame(dic['listPromediosDiarios'])
df_final = df.pivot(
    index=['dia', 'mes', 'ejercicio'],
    columns=['nombreMagnitud'],
    values='valor').reset_index()
df_final = df_final.rename_axis(None, axis=1)

try:
    df_no2 = df_final['Dióxido de Nitrógeno']
    last_value = list(df_no2)[-1]

    with open('last_NO2.csv', mode='w') as file:
        file = csv.writer(file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
        file.writerow([str(last_value)])
    print('success!')
except:
    pass