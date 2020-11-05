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
                new File("src/test/resources/DataProviderUserRegistration.csv")));

        return readUsersSignInFromFile(reader);
    }

/*    @DataProvider
    public Iterator<Object[]>invalidUserSignInFromFile() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(
                new File("src/test/resources/DataProviderInvalidUserRegistration.csv")));

        return readUsersSignInFromFile(reader);
    }*/

    private Iterator<Object[]> readUsersSignInFromFile(BufferedReader reader) throws IOException {
        List<Object[]> list = new ArrayList<>();
        String line = reader.readLine();

        while (line != null) {

            String[] split = line.split(";");

            list.add(new Object[]{new User()
                    .setEmail(split[0])
                    .setPassword(split[1])});
            line = reader.readLine();
        }

        return list.iterator();
    }

    //User Registration
    private Iterator<Object[]> readUsersSignUpFromFile(BufferedReader reader) throws IOException {
        List<Object[]> list = new ArrayList<>();
        String line = reader.readLine();

        while (line != null) {

            String[] split = line.split(";");

            list.add(new Object[]{new User()
                    .setName(split[0])
                    .setEmail(split[1])
                    .setPassword(split[2])
                    .setPasswordConfirm(split[3])});
            line = reader.readLine();
        }

        return list.iterator();
    }
}
