-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 18 avr. 2025 à 15:58
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `rdv_specialiste`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

DROP TABLE IF EXISTS `administrateur`;
CREATE TABLE IF NOT EXISTS `administrateur` (
  `id_admin` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_admin`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `administrateur`
--

INSERT INTO `administrateur` (`id_admin`, `nom`, `prenom`, `email`, `mot_de_passe`) VALUES
(1, 'Fondaneiche', 'Roméo', 'admin1@mail.com', 'admin123'),
(2, 'Leitao', 'Mathis', 'admin2@mail.com', 'admin456');

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

DROP TABLE IF EXISTS `lieu`;
CREATE TABLE IF NOT EXISTS `lieu` (
  `id_lieu` int NOT NULL AUTO_INCREMENT,
  `nom_etablissement` varchar(100) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `ville` varchar(100) DEFAULT NULL,
  `code_postal` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_lieu`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `lieu`
--

INSERT INTO `lieu` (`id_lieu`, `nom_etablissement`, `adresse`, `ville`, `code_postal`) VALUES
(1, 'Clinique Saint-Jean', '12 rue du Bac', 'Paris', '75007'),
(2, 'Centre Médical Lyon Sud', '8 avenue des Frères Lumière', 'Lyon', '69008'),
(3, 'Hôpital Saint-Augustin', '25 rue Pasteur', 'Bordeaux', '33000'),
(4, 'Polyclinique de Strasbourg', '10 rue des Vosges', 'Strasbourg', '67000'),
(5, 'Centre de Santé Marseille', '15 boulevard Baille', 'Marseille', '13005'),
(6, 'Clinique Lille Europe', '3 rue du Molinel', 'Lille', '59800');

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

DROP TABLE IF EXISTS `note`;
CREATE TABLE IF NOT EXISTS `note` (
  `id_note` int NOT NULL AUTO_INCREMENT,
  `note` int DEFAULT NULL,
  `commentaire` text,
  `id_rdv` int DEFAULT NULL,
  PRIMARY KEY (`id_note`),
  UNIQUE KEY `id_rdv` (`id_rdv`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `note`
--

INSERT INTO `note` (`id_note`, `note`, `commentaire`, `id_rdv`) VALUES
(1, 5, 'Très bon accueil et professionnel.', 1),
(2, 4, 'Un peu de retard mais bon suivi.', 2),
(3, 3, 'Consultation rapide.', 3),
(4, 5, 'Excellent spécialiste.', 4),
(5, 4, 'Rien à redire.', 5),
(6, 2, 'Pas satisfait du traitement proposé.', 6);

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `id_patient` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `type_patient` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_patient`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id_patient`, `nom`, `prenom`, `email`, `mot_de_passe`, `type_patient`) VALUES
(1, 'Dupont', 'Jean', 'jean.dupont@mail.com', 'mdp123', 'ancien'),
(2, 'Martin', 'Sophie', 'sophie.martin@mail.com', 'mdp456', 'nouveau'),
(3, 'Durand', 'Paul', 'paul.durand@mail.com', 'mdp789', 'ancien'),
(4, 'Lemoine', 'Claire', 'claire.lemoine@mail.com', 'mdp321', 'nouveau'),
(5, 'Nguyen', 'Thierry', 'thierry.nguyen@mail.com', 'mdp654', 'ancien'),
(6, 'Roux', 'Julie', 'julie.roux@mail.com', 'mdp987', 'nouveau'),
(8, '', '', '', '', 'nouveau'),
(9, 'test', 'test', 'test@mail.com', 'dsdh', 'nouveau');

-- --------------------------------------------------------

--
-- Structure de la table `rendezvous`
--

DROP TABLE IF EXISTS `rendezvous`;
CREATE TABLE IF NOT EXISTS `rendezvous` (
  `id_rdv` int NOT NULL AUTO_INCREMENT,
  `date_heure` datetime DEFAULT NULL,
  `id_patient` int DEFAULT NULL,
  `id_specialiste` int DEFAULT NULL,
  `id_lieu` int DEFAULT NULL,
  PRIMARY KEY (`id_rdv`),
  KEY `id_patient` (`id_patient`),
  KEY `id_specialiste` (`id_specialiste`),
  KEY `id_lieu` (`id_lieu`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `rendezvous`
--

INSERT INTO `rendezvous` (`id_rdv`, `date_heure`, `id_patient`, `id_specialiste`, `id_lieu`) VALUES
(1, '2025-04-22 09:00:00', 1, 1, 1),
(2, '2025-04-22 10:00:00', 2, 2, 2),
(3, '2025-04-22 11:00:00', 3, 3, 3),
(4, '2025-04-23 09:30:00', 4, 4, 4),
(5, '2025-04-23 10:30:00', 5, 5, 5),
(6, '2025-04-24 14:00:00', 6, 6, 6),
(7, '2025-04-30 10:30:00', 1, 1, 5),
(8, '2025-04-30 11:30:00', 1, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `specialiste`
--

DROP TABLE IF EXISTS `specialiste`;
CREATE TABLE IF NOT EXISTS `specialiste` (
  `id_specialiste` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `specialisation` varchar(100) DEFAULT NULL,
  `qualification` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_specialiste`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `specialiste`
--

INSERT INTO `specialiste` (`id_specialiste`, `nom`, `prenom`, `specialisation`, `qualification`) VALUES
(1, 'Benoit', 'Marc', 'Cardiologue', 'Diplôme Paris VI'),
(2, 'Fontaine', 'Elise', 'Dermatologue', 'Diplôme Lyon I'),
(3, 'Garcia', 'Pedro', 'Ophtalmologue', 'Diplôme Bordeaux'),
(4, 'Klein', 'Emma', 'ORL', 'Diplôme Strasbourg'),
(5, 'Legrand', 'Nicolas', 'Gynécologue', 'Diplôme Lille II'),
(6, 'Masson', 'Alice', 'Pédiatre', 'Diplôme Marseille');

-- --------------------------------------------------------

--
-- Structure de la table `specialiste_lieu`
--

DROP TABLE IF EXISTS `specialiste_lieu`;
CREATE TABLE IF NOT EXISTS `specialiste_lieu` (
  `id_specialiste` int NOT NULL,
  `id_lieu` int NOT NULL,
  PRIMARY KEY (`id_specialiste`,`id_lieu`),
  KEY `id_lieu` (`id_lieu`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `specialiste_lieu`
--

INSERT INTO `specialiste_lieu` (`id_specialiste`, `id_lieu`) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
