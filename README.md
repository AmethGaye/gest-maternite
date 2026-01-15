# GestMaternite

**Version** : 1.0  
**Date** : 10/01/2026

Le Système d’Information d’une Maternité (SIM) est une solution centralisée dédiée à la gestion complète d'un service de maternité. Ce système vise à remplacer les supports papier et les outils hétérogènes par une plateforme unique, fiable et structurée, améliorant ainsi la qualité des soins et facilitant la prise de décision médicale.

## Fonctionnalités Principales (MVP)

### Gestion des Patientes

- Création et mise à jour des dossiers médicaux complets
- Enregistrement des antécédents médicaux, chirurgicaux et obstétricaux
- Suivi des consultations prénatales (examens, échographies, analyses biologiques)
- Enregistrement du déroulement de la grossesse (poids, tension, mesures)

### Gestion des Accouchements

- Planification des accouchements programmés
- Enregistrement en temps réel du déroulement de l'accouchement
- Gestion des accouchements d'urgence et des césariennes
- Attribution et suivi de l'occupation des salles d'accouchement

### Gestion des Nouveau-nés

- Création automatique du dossier néonatal lors de la naissance
- Enregistrement de l'état civil et des données anthropométriques
- Suivi des examens néonataux obligatoires
- Gestion du carnet de santé électronique et des vaccinations

### Gestion du Personnel

- Création et gestion des plannings du personnel médical
- Visualisation en temps réel de la disponibilité des équipes
- Affectation du personnel aux salles et aux patientes

### Gestion des Prescriptions et Soins

- Prescription des médicaments informatisée
- Gestion du plan de soins
- Enregistrement des constantes vitales (mère et nouveau-né)
- Gestion des examens complémentaires et de leurs résultats

### Gestion Administrative

- Gestion des admissions, transferts et sorties
- Génération automatique des documents médicaux et administratifs
- Gestion des rendez-vous de consultation
- Statistiques d'activité et tableau de bord

## Technologies Utilisées

### Backend

- **Framework** : Spring Boot
- **Base de données** : PostgreSQL
- **Architecture** : Client/Serveur

### Frontend

- **Framework** : React.js

### Méthodologie

- **Gestion de projet** : Méthode Agile avec Framework Scrum

## Installation

### Prérequis

- Java JDK 17 ou supérieur
- Node.js
- PostgreSQL
- Maven 3.x

### Configuration Backend

```bash
# Cloner le repository
git clone https://github.com/AmethGaye/systeme-information-maternite.git

# Accéder au dossier backend
cd backend

# Configurer la base de données dans application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/sim_db
spring.datasource.username=votre_username
spring.datasource.password=votre_password

# Lancer l'application
mvn spring-boot:run
```

### Configuration Frontend

```bash
# Accéder au dossier frontend
cd frontend

# Installer les dépendances
npm install

# Lancer l'application
npm start
```

## Contact

- mail : amethgaye1401@gmail.com
