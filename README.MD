### Trainer API

Implémentation de l'API Trainer.

N'oubliez pas de set vos variables d'environnement : 

```
${POSTGRESQL_ADDON_HOST}
${POSTGRESQL_ADDON_PORT}
${POSTGRESQL_ADDON_DB}
${POSTGRESQL_ADDON_USER}
${POSTGRESQL_ADDON_PASSWORD}
${POSTGRESQL_ADDON_URI}
```

### Lancer le projet

Via un IDE ou via cmd 
```
mvn clean install && mvn spring-boot:run
```

Port par défaut : 8085

### Routes

Liste des routes dispo sur le swagger (/swagger-ui.html)

GET /trainers/ : Liste des trainers

POST /trainers/{name} :  Créer un trainer 

PUT /trainers/ : Update un trainer

GET /trainers/{name} : Récupére un trainer

DELETE /trainers/{name}: Supprime un trainer


### Security

J'ai mis une couche de base de sécurité 
voici les identifiants: 

NDC :``user``

PWD:``b9f6a7a2-1c37-4949-8043-3ca1ebc4b050``

###Notes

J'ai mis de base les urls en local dans mes applications.properties
