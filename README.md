# WeatherApp
#### Minutillo Alessandro, Scaraggi Vito, Nunin Davide

## Descrizione
L'applicazione da noi sviluppata consiste in un restful web service che implementa un servizio di monitoraggio del meteo mediante l'utilizzo dell'API OpenWeatherMap. 

## Software richiesti
1) IDE (ambiente di sviuppo)
2) Suite Spring Tools for Java
3) JVM (Java Virtual Machine) (Possibilmente la versione più recente)
4) Postman "lato client" (per eseguire le varie rotte implementate nel progetto)

## Istruzioni
1) Installare GIT / GitBash sul prprio sistema operativo
2) Aprire il terminale nella cartella desiderata e digitare il seguente comando "git clone https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin"
3) Importare il progetto nell'IDE 
4) Avviare la Springboot Application (la quale sarà in ascolto sulla porta 8080)
5) Eseguire le possibili richieste implementate su Postman

## Diagramma dei casi d'uso
Mediante il nostro applicativo l'utente potrà: 
1) Ricercare il valore della temperatura e della pressione attuale mediante l'utilizzo di coordinate GPS
2) Effettuare delle statitstiche su range personalizzabili e non riguardanti pressione e temperatura
3) Ricercare coordinate GPS di una determinata cittò utilizzando stringhe e sottostringhe

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
I filtri sono implementati utilizzando il costrutto java Predicate della libreria java.util.function. La classe Filter, contenuta nel package msn.weather_app.util.filter, è una classe generica avente come unico attributo un oggetto di tipo Predicate, un metodo buildLogic() che setta l'oggetto di tipo Predicato, i metodi andCat() e orCat() che restituiscono un oggetto Predicate corrispondente alla concatenazione rispettivamente in AND e in OR di due oggetti Predicate. Il parametro T rappresenta il tipo di dato (City o RecordMeteo) corrispondente alla Collection alla quale è applicato il filtro. Le ulteriori classi presenti nel package Filter estendono la classe Filter, sovrascrivendo il metodo buildLogic() che costruisce l'oggetto di tipo Predicate in base alla funzionalità del filtro specifico.
L'applicazione del filtro a una Collection di oggetti City e RecordMeteo è sviluppata dalla classe generica FilterService, avente come parametro il tipo di dato T. FilterService contiene il metodo applyFilter che dato un ArrayList del tipo T e un filtro del tipo T, restituisce l'ArrayList filtrato. In particolare il metodo applyFilter utilizza il metodo stream().filter(Predicate).collect(Collectors.toList()) della classe List, importato da java.util.stream.Collectors.

I filtri possono essere utilizzati nelle rotte POST dell'applicativo. La seguente immagine mostra un esempio di JSONObject contenente i campi applicabili nel filtro.
I campi del filtro presenti nel JSONObject sono concatenati di default in AND.

<IMMAGINE>
  
<ESEMPIO FILTRO>

## Statistiche
Le statistiche relative a pressione e temperatura sono visualizzabili separatamente con le rotte POST "stats/temp" e "stats/press". Entrambe le rotte restituiscono un JSONObject contenente le statistiche di temperatura e pressione filtrate, inerenti il periodo (campo "period") selezionato e suddivise in base alla periodicità richiesta (campo "freq"). A livello implementativo, ogni statistica è rappresentata da un oggetto di tipo HashMap < String, HashMap < String, Double> > costruito dalla classe StatsCalculator contenuta nel package stats.

<ESEMPIO STATISTICHE>


## Diagramma delle classi

- msn.weather_app

![application](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/packages.jpg)

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

![metadata](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/metadata.jpg)

- /data/city

![data/city](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/data_city.jpg)

- /data/sampled

![data/sampled](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/data_sampled.jpg)

- /data/meteo

![data/meteo](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/data_meteo.jpg)

- /data/meteo/filtered

![data/meteo/filtered](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/data_meteo_filtered.jpg)

- /dictionary

![dictionary](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/dictionary.jpg)

- /now

![now](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/now.jpg)

- /stats/temp

![stats/temp](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/stats_temp.jpg)

- /stats/press

![stats/press](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/stats_press.jpg)

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
