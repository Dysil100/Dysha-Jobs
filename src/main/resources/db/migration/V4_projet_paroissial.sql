-- Table donnateurs
CREATE TABLE IF NOT EXISTS donateurs (
                            id_donneur SERIAL PRIMARY KEY,
                            nom VARCHAR(100) NOT NULL,
                            prenom VARCHAR(100) NOT NULL,
                            sexe CHAR(1),
                            adresse_telephonique VARCHAR(200),
                            croyant BOOLEAN,
                            visiteur BOOLEAN,
                            institution VARCHAR(100), --si disponible
                            photo VARCHAR(200) -- chemin vers le fichier de la photo
);

-- Table donations
CREATE TABLE IF NOT EXISTS donations (
                           id_donation SERIAL PRIMARY KEY,
                           id_donneur INTEGER,
                           type VARCHAR(100) NOT NULL,
                           categorie VARCHAR(100) NOT NULL,
                           montant NUMERIC, -- Pour les dons financiers
                           description TEXT, -- Description de la donation
                           nom_materiel VARCHAR(100), -- Pour les dons matériels si disponible
                           valeur_materiel NUMERIC, -- Valeur du matériel
                           photo_materiel VARCHAR(200), -- chemin vers le fichier de la photo du matériel si disponible
                           date_donation TIMESTAMP,
                           valide BOOLEAN, -- Pour indiquer si la donation a été validée par le curé
                           date_validation TIMESTAMP, -- Date de validation par le curé
                           utilisateur_validation VARCHAR(100) -- Nom de l'utilisateur ayant validé la donation
);

-- Table quete
CREATE TABLE IF NOT EXISTS quete (
                       id_quete SERIAL PRIMARY KEY,
                       date DATE,
                       libelle VARCHAR(100) NOT NULL, -- la quete cest a quelle occasion; messe du diamche , messe en
                                                        -- semaine, une fete, un ceremonie quelquonque.
                       description TEXT,
                       date_quete DATE
);

-- Table groupe_mouvement
CREATE TABLE IF NOT EXISTS groupe_mouvement (
                                  id_groupe SERIAL PRIMARY KEY,
                                  libelle VARCHAR(100) NOT NULL,
                                  responsable VARCHAR(100) -- Nom du responsable du groupe ou mouvement
);

-- Table archives
CREATE TABLE IF NOT EXISTS archives (
                          id_archive SERIAL PRIMARY KEY,
                          type_archivage VARCHAR(100) NOT NULL, -- Par exemple, "Corp de l'eglise", "Historique"
                          id_entite INTEGER, -- Référence vers l'id du donateur, du groupe, du mouvement ou de l'événement archivé
                          nom VARCHAR(100),
                          prenom VARCHAR(100),
                          age INTEGER,
                          sexe CHAR(1),
                          profession VARCHAR(100),
                          photo VARCHAR(200), -- chemin vers le fichier de la photo de l'entité archivée
                          ceb VARCHAR(100),
                          adresse VARCHAR(200),
                          contact VARCHAR(100) -- Email et ou téléphone
);

-- Table evenements
CREATE TABLE IF NOT EXISTS evenements (
                            id_evenement SERIAL PRIMARY KEY,
                            type_evenement VARCHAR(100) NOT NULL, -- Par exemple, "Création de l'église", "Mariage", etc.
                            date_evenement DATE,
                            responsable VARCHAR(100), -- Nom du responsable de l'événement
                            description TEXT,
                            photo VARCHAR(200) -- chemin vers le fichier de la photo de l'événement
);
