# 📌 PixelWorld

PixelWorld è un gioco RPG con nemici e funzionalità basilari, che permette di giocare aumentando di livello e di statistiche, infatti
ogni nemico alla morte "droppa" dei punti esperienza che si accumulano al player, arrivato alla soglia massima per quel livello sale di livello e di prestazioni.

--- 

## 🚀 Come eseguire il progetto

### Prerequisiti
- Java 25
- Gradle

### Istruzioni

```bash
git clone https://github.com/Astrotto/RPGPixelWorld.git
cd RPGPixelWorld
```

### Build del progetto
```bash
./gradlew build
```

### Esecuzione
```bash
./gradlew run
```

---

## 🤖 Uso di strumenti di AI
L'uso dell'AI è stato limitato, e principalmente a scopi di apprendimento su JavaFx, code-review, nessuna parte di codice del progetto è stata generata da AI.

* Utilizzato Gemini per:
  * idee su possibili funzionalità del gioco
  * comprendere al meglio l'utilizzo del file FXML
  * suggerimenti su struttura del codice

* Utilizzato Claude per:
  * verificare che tutti i principi SOLID siano stati rispettati
 
---


## ⚠️ Nota
Gli sprite delle entità per questo gioco sono stati presi da [zerie.itch.io/](https://zerie.itch.io/tiny-rpg-character-asset-pack) che fornisce
un tileset avanzato a pagamento perfetto per l'idea del gioco.

Mentre gli assets per la mappa di gioco (Alberi, pietre, pozioni, strutture, ...) sono stati presi da [craftpix.net](https://craftpix.net/s/top+down/) che fornisce 
un sacco di tileset diversi per tutti gli usi, sia gratis che a pagamento. Però sono stato io ad unire il tutto, creando un ambiente di gioco 
uniforme, usando Photoshop.

Infine alcune componenti (Barra della vita/esperienza, inventario, tasti di azione) li ho disegnati a mano usando [dinopixel.com/](https://dinopixel.com/) .

---

## Funzionalità Presenti

- Movement System basilare da tastiera [(W | UP), (A | LEFT), (S | DOWN), (D | RIGHT)]
- Sistema di Attaco a turni (Prima il player e poi il nemico)
- Possibilità di curarsi (tramite pozioni ottenibili compiendo missioni in gioco)
- Chance di scappare graduale (ad ogni click di SCAPPA il giocatore aumenta la sua probabilità di scappare finchè non ci riesce)
- Richiesta di Missioni (Che se completate danno un premio)

---
## Note Tecniche

Per questo progetto è stato fatto uso del pattern MVC (Model | View | Controller)

---

## TODO

### In Sviluppo
- Meccanismo di salvataggio e restore usando JSON

### Pianificati
- Main Menu / Pause Menu
- Animazione dei personaggi alla morte
- Nuova area di gioco (Cimitero, con nuove creature ostili)

### Possibili Miglioramenti Futuri
- Nuove classi di personaggi (Mago, arciere, ...)
