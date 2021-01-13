#%%
import requests
import json
import pandas as pd

# %%
url = 'https://webcat-web.gva.es/webcat_web/datosOnlineRvvcca/obtenerTablaPestanyaConsulta'
datos = {'fecha':'01-2021'}
headers = {'content-type': 'application/json',
           'charset':'utf-8'}

# %%

res = requests.post(url,
                  data=json.dumps(datos),
                  headers=headers)

enJson = res.json()

dataframe = pd.DataFrame.from_dict(enJson,orient='index')

print(dataframe)



