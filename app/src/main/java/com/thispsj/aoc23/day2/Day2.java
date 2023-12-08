package com.thispsj.aoc23.day2;

import java.util.List;

import com.thispsj.aoc23.Day;
import com.thispsj.aoc23.Helpers;
import com.thispsj.aoc23.Main;

public class Day2 implements Day {

    private static final List<String> puzzleInput = Helpers.getPuzzleFile(
            Main.inpProps.getProperty("day2"),
            2);
    private static final int[] cubes = { 12, 13, 14 };

    public void Part1() {
        System.out.println("Day 2 Part 1");
        int validGames = 0;

        for (String line : puzzleInput) {
            String GameID = line.split(":")[0];
            String[] draws = line.split(":")[1].split(";");
            if (isValidGame(draws))
                validGames += Integer.parseInt(GameID.trim().split(" ")[1]);
        }
        System.out.println("Valid Games: " + validGames);
    }

    public void Part2() {
        System.out.println("Day 2 Part 2");
        int minPower = 0;

        for (String line : puzzleInput) {
            String[] draws = line.split(":")[1].trim().replace(";", ",").split(",");
            minPower += minCubes(draws);
        }
        System.out.println("Cube Power: " + minPower);
    }

    private int minCubes(String[] draws) {
        int[] minCubes = { Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE };
        for (int i = 0; i < draws.length; i++) {
            String draw = draws[i];
            int cubes = getCubes(draw);
            Color color = getColor(draw);
            switch (color) {
                case RED:
                    if (cubes > minCubes[0])
                        minCubes[0] = cubes;
                    break;
                case GREEN:
                    if (cubes > minCubes[1])
                        minCubes[1] = cubes;
                    break;
                case BLUE:
                    if (cubes > minCubes[2])
                        minCubes[2] = cubes;
                    break;
                default:
                    break;
            }
        }
        return minCubes[0] * minCubes[1] * minCubes[2];
    }

    private boolean isValidGame(String[] draws) {

        for (int i = 0; i < draws.length; i++) {
            if (!checkDraw(draws[i].split(",")))
                return false;
        }
        return true;
    }

    private boolean checkDraw(String[] draw) {
        for (int i = 0; i < draw.length; i++) {
            int cubes = getCubes(draw[i]);
            Color color = getColor(draw[i]);
            switch (color) {
                case RED:
                    if (cubes > Day2.cubes[0])
                        return false;
                    break;
                case GREEN:
                    if (cubes > Day2.cubes[1])
                        return false;
                    break;
                case BLUE:
                    if (cubes > Day2.cubes[2])
                        return false;
                    break;
            }
        }
        return true;
    }

    private int getCubes(String line) {
        return Integer.parseInt(line.trim().split(" ")[0]);
    }

    private Color getColor(String cube) {
        switch (cube.trim().split(" ")[1]) {
            case "red":
            case "Red":
                return Color.RED;
            case "green":
            case "Green":
                return Color.GREEN;
            case "blue":
            case "Blue":
                return Color.BLUE;
        }
        return null;
    }

    enum Color {
        RED, GREEN, BLUE
    }
}
