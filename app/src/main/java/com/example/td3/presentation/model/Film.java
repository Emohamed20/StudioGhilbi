package com.example.td3.presentation.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Film {
        private String id;

        @SerializedName("title")
        private String titre;

        @SerializedName("description")
        private String description;

        @SerializedName("director")
        private String directeur;

        @SerializedName("producer")
        private String prodcuteur;

        @SerializedName("release_date")
        private String date_sortie;

        @SerializedName("rt_score")
        private String score;

        @SerializedName("people")
        private List<String> personnages = new ArrayList<String>();

        @SerializedName("species")
        private List<String> especes  = new ArrayList<String>();

        @SerializedName("locations")
        private List<String> emplacements  = new ArrayList<String>();

        @SerializedName("vehicles")
        private List<String> vehicules   = new ArrayList<String>();
        private String url;

        public Film(String id, String titre, String description, String directeur, String prodcuteur, String date_sortie, String score, List<String> personnages, List<String> especes, List<String> emplacements, List<String> vehicules, String url) {
            this.id = id;
            this.titre = titre;
            this.description = description;
            this.directeur = directeur;
            this.prodcuteur = prodcuteur;
            this.date_sortie = date_sortie;
            this.score = score;
            this.personnages = personnages;
            this.especes = especes;
            this.emplacements = emplacements;
            this.vehicules = vehicules;
            this.url = url;
        }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<String> getPersonnages() {
        return personnages;
    }

    public void setPersonnages(List<String> personnages) {
        this.personnages = personnages;
    }

    public List<String> getEspeces() {
        return especes;
    }

    public void setEspeces(List<String> especes) {
        this.especes = especes;
    }

    public List<String> getEmplacements() {
        return emplacements;
    }

    public void setEmplacements(List<String> emplacements) {
        this.emplacements = emplacements;
    }

    public List<String> getVehicules() {
        return vehicules;
    }

    public void setVehicules(List<String> vehicules) {
        this.vehicules = vehicules;
    }

    public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }



    public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDirecteur() {
            return directeur;
        }

        public void setDirecteur(String directeur) {
            this.directeur = directeur;
        }

        public String getProdcuteur() {
            return prodcuteur;
        }

        public void setProdcuteur(String prodcuteur) {
            this.prodcuteur = prodcuteur;
        }


        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDate_sortie() {
            return date_sortie;
        }

        public void setDate_sortie(String date_sortie) {
            this.date_sortie = date_sortie;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }


