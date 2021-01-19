# WeatherApp
#### Minutillo Alessandro, Scaraggi Vito, Nunin Davide

## Descrizione
L'applicazione consiste in un RESTful web service che implementa un servizio di monitoraggio del meteo mediante l'utilizzo dell'API OpenWeatherMap. 

## Software raccomandati per l'esecuzione
1) IDE Java (ambiente di sviluppo)
2) Suite Spring Tools for Java
3) JVM (Java Virtual Machine) (possibilmente la versione più recente)
4) Postman "lato client" (per eseguire le rotte implementate nel progetto)
5) Git/GitBash

## Istruzioni per l'uso
1) Aprire il terminale nella cartella desiderata e digitare il seguente comando "git clone https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin"
2) Importare il progetto nell'IDE 
3) Avviare la Springboot Application (che sarà in ascolto sulla porta 8080)
4) Eseguire le rotte possibili su Postman

## Diagramma dei casi d'uso
Mediante il nostro applicativo l'utente potrà: 
1) Ricercare il valore della temperatura e della pressione attuale mediante l'utilizzo di coordinate GPS
2) Effettuare delle statistiche riguardanti pressione e temperatura su range di tempo personalizzabili
3) Ricercare coordinate GPS di una determinata città tramite stringa o sottostringa

![Casi d'uso](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/use_case_diagram.jpg)

## Rotte utilizzabili

|Rotta|Metodo|Descrizione|Campo|
|---------|------------|-------|---------|
|  "/metadata" | GET | Rotta che restituisce tutti gli alias utilizzati||
|  "/data/city"      | GET  | Rotta che restituisce un'arraylist contenente tutte le città disponibili sull'applicazione||
|  "/data/sampled"    | GET | Rotta che restituisce l'elenco delle città utilizzate per il campionamento dei dati ||
|  "/data/meteo"     | GET | Rotta che restituisce tutti i campionamenti meteo salvati dall'applicazione ||
|  "/data/meteo/filtered" | POST  | Rotta che restituisce tutti i campionamenti salvati dall'applicazione filtrati mediante i parametri contenuti nel JSONBody| "name", "coord", "period", "freq", "temp", "press"|
|  "/dictionary"     | GET | Rotta che restituisce le coordinate di una città mediante l'inserimento di una stringa (che rappresenta il nome della città) o di una sottostringa (contenuta nel nome della città) |"string" |
|  "/now"     | GET | Rotta che permette di visualizzare la situazione meteo corrente di una città date le sue coordinate geografiche |"lat", "lon"|
|  "/stats/temp"     | POST | Rotta che permette di visualizzare le statistiche sulla temperatura dei dati meteo campionati dall'applicazione, filtrati mediante i parametri contenuti nel JSON body |"name", "coord", "period", "freq", "temp", "press"|
|  "/stats/press"     | POST | Rotta che permette di visualizzare le statistiche sulla pressione dei dati meteo salvati dall'applicazione, filtrati mediante i parametri contenuti nel JSON body |"name", "coord", "period", "freq", "temp", "press"|

## Filtri
I filtri sono implementati utilizzando il costrutto java Predicate della libreria java.util.function. La classe Filter, contenuta nel package msn.weather_app.util.filter, è una classe generica avente come unico attributo un oggetto di tipo Predicate, un metodo buildLogic() che setta l'oggetto di tipo Predicato, i metodi andCat() e orCat() che restituiscono un oggetto Predicate corrispondente alla concatenazione rispettivamente in AND e in OR di due oggetti Predicate. Il parametro T rappresenta il tipo di dato (City o RecordMeteo) corrispondente alla Collection alla quale è applicato il filtro. Le ulteriori classi presenti nel package Filter estendono la classe Filter, sovrascrivendo il metodo buildLogic() che costruisce l'oggetto di tipo Predicate in base alla funzionalità del filtro specifico.
L'applicazione del filtro a una Collection di oggetti City e RecordMeteo è sviluppata dalla classe generica FilterService, avente come parametro il tipo di dato T. FilterService contiene il metodo applyFilter che, dato un ArrayList di oggetti del tipo T e un filtro per oggetti del tipo T, restituisce l'ArrayList filtrato. In particolare il metodo applyFilter utilizza il metodo stream().filter(Predicate).collect(Collectors.toList()) della classe List, importato da java.util.stream.Collectors.

I filtri possono essere utilizzati nelle rotte POST dell'applicativo. La seguente immagine mostra un esempio di JSONObject contenente i campi che costituiscono un filtro generico. I campi del filtro presenti nel JSONObject, "name", "coord","period", "freq", "temp", "press" sono tutti opzionali e sono concatenati di default in AND.

Struttura di un filtro generico:
![Generic_Filter](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/es_filter.JPG)

- nel campo "name" è indicato il nome ( o sottostringa) della città cercata o di più città separate dal carattere ";" ;
- nel campo "coord" sono indicate le coordinate cercate: latitudine (lat) e longitudine (lon). È necessario specificare sia la latitudine che la longitudine;
- nel campo "period" è indicato il periodo di interesse: è possibile selezionare i dati dell'ultima ora, giorno, settimana, mese o di un intervallo personalizzato di ore, giorni, settimane o mesi rispettando la sintassi illustrata nell'immagine. Se il campo "period" non è indicato, vengono considerati tutti i dati meteo presenti nel database;
- nel campo "freq" è indicata la periodicità con cui si vogliono analizzare i dati meteo filtrati: è possibile suddividere il periodo di interesse selezionato in intervalli di una o più ore, uno o più giorni, una o più settimane, uno o più mesi rispettando la sintassi illustrata nell'immagine. Se il campo "freq" non è indicato, i dati meteo relativi al periodo di interesse sono analizzati tutti insieme (chiave "total");
- nel campo "temp" sono indicate opzioni di filtraggio relative alla temperatura: è possibile filtrare rispetto alla temperatura corrente, minima, massima o percepita specificando un range numerico. Filtri relativi a più tipologie di temperatura sono concatenati di default in AND;
- nel campo "press" è indicato un range numerico in base al quale si vogliono filtrare i valori di pressione.

Esempio di filtro - rotta "data/meteo/filtered"

JSON della richiesta HTTP:

![Filter_Example1](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/json_data.JPG)

Spiegazione del filtro:

Il filtro seleziona tutti i dati meteo della città "Milan" nell'ultima ora.

JSON in output:

![Filter_Example2](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/es_data.JPG)

La struttura dati restituita è un HashMap<String,ArrayList<RecordMeteo>>.

## Statistiche
Le statistiche relative a pressione e temperatura sono visualizzabili separatamente con le rotte POST "stats/temp" e "stats/press". Entrambe le rotte restituiscono un JSONObject contenente le statistiche di temperatura e pressione filtrate, inerenti il periodo (campo "period") selezionato e suddivise in base alla periodicità richiesta (campo "freq"). A livello implementativo, ogni statistica è rappresentata da un oggetto di tipo HashMap<String,HashMap<String,Double>> costruito dalla classe StatsCalculator contenuta nel package stats.

Esempio statistiche - rotta "stats/temp"

JSON della richiesta HTTP:

![Stats_Example2](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/json_stat.JPG)

Spiegazione del filtro:

Il filtro seleziona tutti i dati meteo della città "Milan", negli ultimi 28 giorni, con temperatura corrente compresa tra 0°C e 20°C, analizzati con periodicità settimanale.

JSON in output:

![Stats_Example1](https://github.com/AlessandroMinutillo/minutillo-scaraggi-nunin/blob/master/img/es_stat.JPG)

La struttura dati restituita è un HashMap<String,HashMap<String,Double>>.

## Diagrammi delle classi

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

## Diagrammi delle sequenze

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
- UML Designer
- Git/GitBash
- Maven
- SpringBoot
- JUnit
- Postman
