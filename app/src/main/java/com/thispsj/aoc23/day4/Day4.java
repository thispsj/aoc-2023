package com.thispsj.aoc23.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.thispsj.aoc23.Day;
import com.thispsj.aoc23.Helpers;
import com.thispsj.aoc23.Main;

public class Day4 implements Day {
    
    private static final List<String> puzzleInput = Helpers.getPuzzleFile(
            Main.inpProps.getProperty("day4"),
            4);
    
    public void Part1()
    {
        int sum = 0;
        for (String line: puzzleInput)
        {
            String[] cards = line.split(":")[1].trim().split(" \\| ");
            List<String> wins = Arrays.asList(cards[0].split(" ")), card = Arrays.asList(cards[1].split(" "));
            wins.removeAll(Arrays.asList(new String[] {" "}));
            card.removeAll(Arrays.asList(new String[] {" "}));
            int i = 0;
            for (String l: card)
            {
                if(l.trim().isEmpty())
                continue;
                if (wins.contains(l)&&i==0)
                    i++;
                else if (wins.contains(l))
                    i+=i;        
            }
            sum+=i;
        }

        System.out.println("You are worth: "+sum+" points");
    }

    public void Part2()
    {
        List<Integer> cardCopy = new ArrayList<>();
        int sum=0;

        for (int i = 0; i < puzzleInput.size(); i++)
            cardCopy.add(1);
        for (int i =0; i<puzzleInput.size();i++)
        {
        String line = puzzleInput.get(i);
        String[] cards = line.split(":")[1].trim().split(" \\| ");
        List<String> wins = Arrays.asList(cards[0].split(" ")), card = Arrays.asList(cards[1].split(" "));
        wins.removeAll(Arrays.asList(new String[] {" "}));
        card.removeAll(Arrays.asList(new String[] {" "}));
        int ki = 0;
            
        for (String l: card)
        {
            if(l.trim().isEmpty())
                continue;
            if (wins.contains(l))
                ki++;
            
        }

        //System.out.println(ki);
        
        int t = cardCopy.get(i);           
        for (int l = 1; l <= t; l++) {
            
        
        for (int j = i; j < i+ki; j++)
            {
                if(j==puzzleInput.size())
                    break;
            int cc = cardCopy.get(j+1);
            cardCopy.set(j+1, cc+1);}
        }}

        for (int i: cardCopy)
        sum+=i;

        System.out.println("Winning cards: "+sum);
        //System.out.println(cardCopy);
    }
}
