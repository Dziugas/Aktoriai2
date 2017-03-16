package com.company.papildoma;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by User on 2017-03-16.
 */
public class Papildoma {
    private Connection connection;
    Scanner sc = new Scanner(System.in);

//asfkaksdlf
    public Papildoma() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aktoriai",
                    "root",
                    "");
        } catch (Exception klaida) {
            System.out.println(klaida);
        }
    }

    public void pasisveikink() {
        System.out.println("Sveikas, galėsime lenteles atvaizduoti, papildyti arba ištrinti");
    }

    public void paklausk() {
        System.out.println("Pasirink ką norėsi daryti.");
    }

    public void nuspresk() {
        System.out.println("Spausk '1', jei spausdinti lentelę, '2', jei papildyti ir '3', jei trinti");
        int sprendimas = sc.nextInt();
        switch (sprendimas) {
            case 1:
                atvaizduoti();
                break;
            case 2:
                prideti();
                break;
            case 3:
                trinti();
                break;
        }
    }

    public void prideti() {
//INSERT INTO `aktoriai` (`id`, `vardas`, `pavarde`, `amzius`) VALUES (NULL, '', '', '')
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `aktoriai` (`vardas`, `pavarde`, `amzius`) VALUES (?, ?, ?)");
            System.out.println("Ivesti Varda");
            String vardas = sc.next();
            System.out.println("Ivesti Pavarde");
            String pavarde = sc.next();
            System.out.println("Ivesti amziu");
            String amzius = sc.next();
            statement.setString(1, vardas);
            statement.setString(2, pavarde);
            statement.setString(3, amzius);
            statement.executeUpdate();
        } catch (Exception klaida) {
            System.out.println(klaida);
        }
    }

    public void trinti() {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `aktoriai` WHERE `aktoriai`.`id` = " + "?" + ";");
            System.out.println("Ivesti ID aktoriaus kuri norite istrinti");
            int id = sc.nextInt();
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception klaida) {
            System.out.println(klaida);
        }
    }

    public void atvaizduoti() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `aktoriai`");
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("vardas"));
                System.out.print(" ");
                System.out.print(resultSet.getString("pavarde"));
                System.out.print(" ");
                System.out.print(resultSet.getString("amzius"));
                System.out.print("");
                System.out.println();
            }
        } catch (Exception error) {
            System.out.println(error);
        }

    }
}