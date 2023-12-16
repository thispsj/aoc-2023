package com.thispsj.aoc23.day5;

import java.util.ArrayList;
import java.util.List;

import com.thispsj.aoc23.Day;
import com.thispsj.aoc23.Helpers;
import com.thispsj.aoc23.Main;

public class Day5 implements Day {
    
    private static final List<String> puzzleInput = Helpers.getPuzzleFile(
            Main.inpProps.getProperty("day5"),
            5);
    private List<Almanac> almanacs = processAlmanac();
    
    public void Part1()
    {
        final List<Long> seeds = getSeeds();
        System.out.println(almanacs.size());
        List<Long> locations = new ArrayList<>();
        for (long seed : seeds) {
            long m = seed;
            for (Almanac almanac: almanacs) {
                m = almanac.transform(m);
            }
            locations.add(m);
        }
        locations.sort(null);
        System.out.println(locations.get(0));
    }

    private List<Almanac> processAlmanac()
    {
        List<Almanac> alm = new ArrayList<>();
        String ruleSet = "";
        for (int i = 0; i < puzzleInput.size(); i++) {
            String s = puzzleInput.get(i);
            if (s.contains(":")||i==1)
            continue;

            if (s.isBlank())
                {
                    alm.add(new Almanac(ruleSet.split(";")));
                    ruleSet = "";
                    continue;
                }
            if(!ruleSet.isBlank())
             ruleSet+=";"+s;
            else
             ruleSet+=s;
        }
        return alm;
    }

    private List<Long> getSeeds()
    {
        List<Long> seeds = new ArrayList<>();
        String[] seedString = puzzleInput.get(0).split(":")[1].trim().split(" ");
        for(String s: seedString)
        {
            seeds.add(Long.parseLong(s));
        }
        return seeds;
    }

    public void Part2()
    {
        // No way left but we are going to bruteforce all the seeds which will require a good time. (Approx 5 mins on a standard machine)
        long min = Long.MAX_VALUE;
        List<Long> seeds = getSeeds();
        

        for (int i = 0; i < seeds.size(); i+=2) {
            long iseed = seeds.get(i);
            long l = seeds.get(i+1);
            for (int j = 0; j < l; j++,iseed++) {
                long loc = 0;
                for (Almanac almanac: almanacs)
                {
                    loc = almanac.transform(iseed);
                }
                if (loc<min)
                 min = loc;
            }
        }
        System.out.println(min);
    }


    public class Almanac{
        private List <RangeRule> rules = new ArrayList<>();
        
        public Almanac (String[] ruleSet)
        {
            this.rules = processRuleSet(ruleSet);
        }

        public List<RangeRule> processRuleSet (String[] ruleSet)
        {
            List<RangeRule> listRuleSet = new ArrayList<>();
            for (String line: ruleSet)
            {
                String [] nums = line.split(" ");
                long d = Long.parseLong(nums[0]), s = Long.parseLong(nums[1]), l = Long.parseLong(nums[2]);
                listRuleSet.add(new RangeRule(d, s, l));
            }
            return listRuleSet;
        }

        public long transform (long srcNum)
        {
            for (RangeRule rule: rules)
            {
                if (rule.inRange(srcNum))
                    return srcNum + (rule.dest()-rule.src());
            }
            return srcNum;
        }

    }

    public record RangeRule(long dest, long src, long end, long length)
    { 
        public RangeRule(long dest, long src, long length)
        {
            this (dest,src,src+length-1,length);
        }

        public boolean inRange (long num)
        {
            return num >= this.src && num <= this.end;
        }
    }
}
