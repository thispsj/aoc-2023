/*
 * This is the main class which would execute programs throughout Advent of Code 2023.
 */
package com.thispsj.aoc23;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    private static final String root = Thread.currentThread().getContextClassLoader().getResource("").getPath().replace("%20", " ")+"\\com\\thispsj\\aoc23\\inputfiles.properties";
    public static Properties inpProps = new Properties();

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, FileNotFoundException, IOException {
        inpProps.load(new FileInputStream(root));
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the day number: ");
        int day = in.nextInt();
        if (day < 1 || day > 25) {
            System.out.println("Invalid day number");
            in.close();
            System.exit(1);
        }
        Day dayObj = Class.forName("com.thispsj.aoc23.day" + day + ".Day" + day).asSubclass(Day.class)
                .getDeclaredConstructor().newInstance();
        dayObj.Part1();
        dayObj.Part2();
        in.close();
    }
}
