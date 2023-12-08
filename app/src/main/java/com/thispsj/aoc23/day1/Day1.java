package com.thispsj.aoc23.day1;

import java.util.ArrayList;
import java.util.List;
import com.thispsj.aoc23.Day;
import com.thispsj.aoc23.Helpers;
import com.thispsj.aoc23.Main;

public class Day1 implements Day {
    private static final List<String> puzzleInput = Helpers.getPuzzleFile(
            Main.inpProps.getProperty("day1"),
            1);

    @Override
    public void Part1() {
        System.out.println("Day 1 Part 1");
        int callibrationValue = 0;
        for (String line : puzzleInput) {
            int first = 0, last = 0;
            line = line.replaceAll("[a-z]", "");
            first = Integer.parseInt(line.substring(0, 1)) * 10;
            last = Integer.parseInt(line.substring(line.length() - 1));
            callibrationValue += first + last;
        }
        System.out.println("Callibration Value: " + callibrationValue);
    }

    @Override
    public void Part2() {
        System.out.println("Day 1 Part 2");
        int callibrationValue = 0;
        for (String line : puzzleInput) {
            callibrationValue += callibrate(line);
        }
        System.out.println("Callibration Value: " + callibrationValue);
    }

    private int callibrate (String s)
    {
        List<Integer> numbers = new ArrayList<>();
        String nums[] = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        for (int i = 0; i<s.length(); i++)
        {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                    numbers.add(Integer.parseInt(""+ch));
                    continue;
                }
        for (int j=0; j<9; j++)
        {
            int len = nums[j].length();
            if (i+len > s.length())
            continue;
            if (s.substring(i,i+len).equals(nums[j]))
                {numbers.add(j+1);
                }

        }
    }
        return (numbers.get(0)*10) + numbers.get(numbers.size()-1);
    }
    
}

