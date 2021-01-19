# WeatherApp
#### Minutillo Alessandro, Scaraggi Vito, Nunin Davide

## Descrizione
L'applicazione da noi sviluppata consiste nell'implementazione di un servizio di monitoraggio del meteo mediante l'utilizzo dell'API OpenWeatherMap.

## Funzionamento

![Casi d'uso](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/use_case_diagram.jpg)
## Rotte utilizzabili

|Rotte|Metodo|Descrizione|Campo|
|---------|------------|-------|---------|
|  "/metadata" | Get | Rotta che restituisce tutti i possibili output che si possono incontrare andando ad utilizzare l'applicazione||
|  "/data/city"      | Get  | Rotta che restituisce un'arraylist contenente tutte le città disponibili sull'applicazione||
|  "/data/sampled"    | Get | Rotta che restituisce l'elenco delle città utilizzate per il campionamento dei dati ||
|  "/data/meteo"     | Get | Rotta che restituisce tutti i campionamenti effettuati dall'applicazione ||
|  "/data/meteo/filtered" | Post  | Rotta che restituisce tutti i campionamenti effettuati dall'applicazione filtrati mediante i parametri contenuti nel JSON body| "name", "coord", "period", "freq", "temp", "press"|
|  "/dictionary"     | Get | Rotta che permette di restituire le coordinate di una città mediante l'inserimento di una stringa (che rappresenta il nome della città) o di una sottostringa (contenuta nel nome della città) |"string" |
|  "/now"     | Get | Rotta che permette di visualizzare la situazione meteo corrente di una città date le sue coordinate geografiche |"lat", "lon"|
|  "/stats/temp"     | Post | Rotta che permette di visualizzare le statistiche sulla temperatura dei dati meteo campionati dall'applicazione, filtrati mediante i parametri contenuti nel JSON body |"name", "coord", "period", "freq", "temp", "press"|
|  "/stats/press"     | Post | Rotta che permette di visualizzare le statistiche sulla pressione dei dati meteo campionati dall'applicazione, filtrati mediante i parametri contenuti nel JSON body |"name", "coord", "period", "freq", "temp", "press"|

## Filtri

## Statistiche

## Diagramma delle classi

- msn.weather_app

![application](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/packages.jpg)

- msn.weather_app.controller

![controller](link)

- msn.weather_app.database

![database](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/database.jpg)

- msn.weather_app.exception

![exception](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/exception.jpg)

- msn.weather_app.model

![model](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/model.jpg)

- msn.weather_app.service

![service](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/service.jpg)

- msn.weather_app.util.filter

![filter](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/filter.jpg)

- msn.weather_app.util.parser

![parser](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/parser.jpg)

- msn.weather_app.util.stats

![stats](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/stats.jpg)

## Diagramma delle sequenze

- /metadata

![metadata](link)

- /data/city

![data/city](link)

- /data/sampled

![data/sampled](link)

- /data/meteo

![data/meteo](link)

- /data/meteo/filtered

![data/meteo/filtered](link)

- /dictionary

![dictionary](link)

- /now

![now](link)

- /stats/temp

![stats/temp](link)

- /stats/press

![stats/press](link)

## Suddivisione del lavoro
- Minutillo Alessandro 33,3%
- Scaraggi Vito 33,4%
- Nunin Davide 33,3%

## Softwares utilizzati
- Eclipse
- Uml Designer
- GitBash
- Maven
- SpringBoot
- JUnit
