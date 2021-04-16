package com.randomcsv;

import com.github.javafaker.Faker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

public class Main {

    private static final String SAMPLE_CSV_FILE = "randomData.csv";

    public static void main(String[] args) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader("First Name", "Last Name", "Date", "Gender", "Race", "Phone Number", "Email"))) {


            for (int i = 0; i < 100; i++) {
                Person person = generateRandomData();
                csvPrinter.printRecord(person.getFirstName(), person.getLastName(), person.getDate(),
                        person.getGender(), person.getRace(), person.getPhoneNumber(), person.getEmail());
            }

            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Person generateRandomData() {
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        Date date = faker.date().birthday();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String birthdate = calendar.get(Calendar.YEAR) + "/" + Calendar.MONTH + "/" + Calendar.DAY_OF_MONTH;
        Gender gender = Gender.values()[(int) Math.floor(Math.random() * 2)];
        Race race = Race.values()[(int) Math.floor(Math.random() * 5)];
        String phoneNumber = faker.phoneNumber().cellPhone();
        String emailAddress = faker.internet().emailAddress();

        return new Person(firstName, lastName, birthdate, gender, race, phoneNumber, emailAddress);

    }


}
