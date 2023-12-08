package com.thispsj.aoc23.day3;

import java.util.List;

import com.thispsj.aoc23.Day;
import com.thispsj.aoc23.Helpers;
import com.thispsj.aoc23.Main;

public class Day3 implements Day {
    
    private static final List<String> puzzleInput = Helpers.getPuzzleFile(
            Main.inpProps.getProperty("day3"),
            3);

    public void Part1()
    {
        int sum = 0;

        for (int i = 0; i < puzzleInput.size(); i++) {
            String prev = i!=0? puzzleInput.get(i-1):"", s = puzzleInput.get(i), next = (i+1)!=puzzleInput.size()?puzzleInput.get(i+1):"", build = "";    

            for (int j = 0; j < s.length(); j++) {
                char ch = s.charAt(j);
                if((!Character.isDigit(ch))&&build.equals(""))
                 continue;
                if ((!Character.isDigit(ch))|| (j+1==s.length()&&!build.equals("")))
                {
                    int part = Integer.parseInt(build);
                    if (isPart(j-build.length(),j,s,prev,next))
                        sum+=part;
                    build="";
                    continue;
                }
                build+=ch;
            }
        }

        System.out.println("Sum of part numbers: "+sum);
    }

    public void Part2()
    {
        int gRatio=0;

        for (int i = 0; i < puzzleInput.size(); i++) {
            if(!puzzleInput.get(i).contains("*"))
            continue;

            String prev = i!=0? puzzleInput.get(i-1):"", s = puzzleInput.get(i), next = (i+1)!=puzzleInput.size()?puzzleInput.get(i+1):"";
            for (int j=0; j<s.length();j++)
            {
                if (s.charAt(j)=='*')
                    gRatio+=getGearRatio(j, s, prev, next);
            }
           
        }

        System.out.println("Gear Ratio: "+gRatio);
    }

    private boolean isPart(int start, int finish, String og, String prev, String next)
    {
        if (start>0)
        {
            if(og.charAt(start-1)!='.'&&(!Character.isDigit(og.charAt(start-1))))
             return true;
            if (!prev.equals(""))
            {   
                if(prev.charAt(start-1)!='.'&&(!Character.isDigit(prev.charAt(start-1))))
                 return true;
            }

            if (!next.equals(""))
            {
                if(next.charAt(start-1)!='.'&&(!Character.isDigit(next.charAt(start-1))))
                 return true;
            }
             
        }

        if (finish+1!=og.length()) {
            if(og.charAt(finish)!='.'&&(!Character.isDigit(og.charAt(finish))))
             return true;
            if (!prev.equals(""))
            {   
                if(prev.charAt(finish)!='.'&&(!Character.isDigit(prev.charAt(finish))))
                 return true;
            }

            if (!next.equals(""))
            {
                if(next.charAt(finish)!='.'&&(!Character.isDigit(next.charAt(finish))))
                 return true;
            }
        }

        for (int i = start; i < finish+1; i++) {
            if (prev.equals(""))
                break;
            char ch=prev.charAt(i);

            if(ch=='.'||Character.isDigit(ch))
                continue;
            return true;
        }

        for (int i = start; i < finish+1; i++) {
            if (next.equals(""))
                break;
            char ch=next.charAt(i);

            if(ch=='.'||Character.isDigit(ch))
                continue;
            return true;
        }

        return false;
    }

    private int getGearRatio(int pos, String og, String prev, String next)
    {
        if (!isGear(pos, og, prev, next))
        return 0;

        int numbers[] = {0,0};
        int numdex[] = {0,0}, ni =0;
        String sloc[] = {"",""};

        boolean prevFlag=prev.equals(""), nextFlag=next.equals("");

        if (!prevFlag) {
            if (Character.isDigit(prev.charAt(pos))) {
                numdex[ni] = pos;
                sloc[ni] = "p";
                ni++;
            }
            else
            {
                if(pos!=0)
                {
                    if(Character.isDigit(prev.charAt(pos-1)))
                    {numdex[ni] = pos-1;
                    sloc[ni] = "p";
                    ni++;    }
                }
                
                if(pos+1!=prev.length())
                {
                    if (Character.isDigit(prev.charAt(pos+1)))
                    {numdex[ni] = pos+1;
                     sloc[ni]  = "p";
                    ni++;                    }
                }
            }
        }


        if (!(ni>1))
        {
            if(pos!=0)
                {
                    if(Character.isDigit(og.charAt(pos-1)))
                    {numdex[ni] = pos-1;
                    sloc[ni] = "s";
                    ni++; 
                    }    
                }
                
                if(pos+1!=og.length()&&!(ni>1))
                {
                    if (Character.isDigit(og.charAt(pos+1)))
                    {   numdex[ni] = pos+1;
                        sloc[ni]  = "s";
                        ni++;
                    }
                }
        }

        if (!(ni>1) && !nextFlag)
        {
            if (Character.isDigit(next.charAt(pos))) {
                numdex[ni] = pos;
                sloc[ni] = "n";
                ni++;
            }
            else
            {
                if(pos!=0)
                {
                    if(Character.isDigit(next.charAt(pos-1)))
                    {numdex[ni] = pos-1;
                    sloc[ni] = "n";
                    ni++;                    }
                }
                
                if(pos+1!=next.length()&&!(ni>1))
                {
                    if (Character.isDigit(next.charAt(pos+1)))
                    {numdex[ni] = pos+1;
                     sloc[ni]  = "n";
                    ni++;                    }
                }
            }
        }

 
        for (int i = 0; i < numdex.length; i++) {
            int nums = numdex[i];
            String left = "", right = "", mid = "";
            switch (sloc[i]) {
                case "p":
                    if (Character.isDigit(prev.charAt(nums)))
                     mid= prev.charAt(nums) + "";
                    for (int j = nums-1; j>=0; j--)
                    {
                        if(!Character.isDigit(prev.charAt(j)))
                            break;
                        left = prev.charAt(j) + left;                        
                    }
                    
                    for (int j = nums+1; j<prev.length(); j++)
                    {
                        if(!Character.isDigit(prev.charAt(j)))
                            break;
                        right = right + prev.charAt(j);                        
                    }
                    numbers[i] = Integer.parseInt(left+mid+right);
                    break;
                
                case "s":
                    if (Character.isDigit(og.charAt(nums)))
                        mid = "" + og.charAt(nums);
                    for (int j = nums-1; j>=0; j--)
                    {
                        
                        if(!Character.isDigit(og.charAt(j)))
                            break;
                        left = og.charAt(j) + left;                        
                    }
                    
                    for (int j = nums+1; j<og.length(); j++)
                    {
                        if(!Character.isDigit(og.charAt(j)))
                            break;
                        right = right + og.charAt(j);                        
                    }
                    numbers[i] = Integer.parseInt(left+mid+right);
                    break;
                case "n":
                    if (Character.isDigit(next.charAt(nums)))
                        mid = "" + next.charAt(nums);
                    for (int j = nums-1; j>=0; j--)
                    {
                        if(!Character.isDigit(next.charAt(j)))
                            break;
                        left = next.charAt(j) + left;                        
                    }
                    
                    for (int j = nums+1; j<next.length(); j++)
                    {
                        if(!Character.isDigit(next.charAt(j)))
                            break;
                        right = right + next.charAt(j);                        
                    }
                    numbers[i] = Integer.parseInt(left+mid+right);
                default:
                    break;
            }
        }

        return numbers[0]*numbers[1];
    }

    private boolean isGear (int pos, String og, String prev, String next)
    {
        int i=0;
        if(pos>0)
        {
            if(Character.isDigit(og.charAt(pos-1)))
             i++;
            if (!prev.equals(""))
            {
                if (Character.isDigit(prev.charAt(pos-1))||Character.isDigit(prev.charAt(pos))) i++;
            }
            if (!next.equals(""))
            {
                if (Character.isDigit(next.charAt(pos-1))||Character.isDigit(next.charAt(pos))) i++;
            }
        }
        if(pos+1!=og.length())
        {
            if(Character.isDigit(og.charAt(pos+1)))
             i++;
            if (!prev.equals(""))
            {
                if (Character.isDigit(prev.charAt(pos+1))) i++;
            }
            if (!next.equals(""))
            {
                if (Character.isDigit(next.charAt(pos+1))) i++;
            }
        }

        if (i>1)
         return true;
        
        return false;
    }
}
