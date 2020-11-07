package com.qa25.propets.Tests;

import com.qa25.propets.Model.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    //User Login
    @DataProvider
    public Iterator<Object[]>validUserSignInFromFile() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(
                new File("src/test/resources/DataProviderValidUserSignIn.csv")));

        return readUsersSignInFromFile(reader);
    }

    @DataProvider
    public Iterator<Object[]>invalidUserSignInFromFile() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(
                new File("src/test/resources/DataProviderInvalidUserSignIn.csv")));

        return readUsersSignInFromFile(reader);
    }

    private Iterator<Object[]> readUsersSignInFromFile(BufferedReader reader) throws IOException {
        List<Object[]> list = new ArrayList<>();
        String line = reader.readLine();

        while (line != null) {

            String[] split = line.split(";");

            list.add(new Object[]{new User()
                    .setName(split[0])
                    .setEmail(split[1])
                    .setPassword(split[2])});
            line = reader.readLine();
        }

        return list.iterator();
    }

    //User Registration
    @DataProvider
    public Iterator<Object[]>validUserSignUpFromFile() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(
                new File("src/test/resources/DataProviderValidUserSignUp.csv")));

        return readUsersSignUpFromFile(reader);
    }

    @DataProvider
    public Iterator<Object[]>invalidUserSignUpFromFile() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(
                new File("src/test/resources/DataProviderInvalidUserSignUp.csv")));

        return readUsersSignUpFromFile(reader);
    }

    private Iterator<Object[]> readUsersSignUpFromFile(BufferedReader reader) throws IOException {
        List<Object[]> list = new ArrayList<>();
        String line = reader.readLine();
        int i = 0;

        while (line != null) {

            String[] split = line.split(";");

            String email = new String();
            email = split[1];
            if(!email.contains("gmail") && !email.equals("")){
                email = email + System.currentTimeMillis() + i + "@gmail.com";
            }

            list.add(new Object[]{new User()
                    .setName(split[0])
                    .setEmail(email)
                    .setPassword(split[2])
                    .setPasswordConfirm(split[3])});
            line = reader.readLine();
            i++;
        }

        return list.iterator();
    }
}
