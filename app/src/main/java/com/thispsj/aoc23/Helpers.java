package com.thispsj.aoc23;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains helper methods which would be used throughout the Advent of Code 2023.
 * Will add as and when required.
 */

public class Helpers
{
    public static List<String> getPuzzleFile(String puzzlePath, int day)
    {
        try {
        return Files.readAllLines(Path.of(puzzlePath));
        } catch (Exception e) {
            System.out.println("Error reading puzzle file for day " + day + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}