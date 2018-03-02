/*

A simple interface for calculation of bill and printing it for a cab ride
Written by Shreyan Dey.
Copyright 2018 AUDE labs.
All rights reserved.
v1.1
Compiled on 1/3/2018
*/

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class taxi
{
	public static void main(String args[])
	{
		String name = "";
		String taxino = "0";
        int lineNo;
        int km = 0;
        int amt = 0;
        int rate = 0;

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        String time = df.format(dateobj);

		Scanner in = new Scanner(System.in);
		utilities ut = new utilities();

		try {
			FileReader fr = new FileReader("info.txt");
			BufferedReader br = new BufferedReader(fr);

			for (lineNo = 1; lineNo < 3; lineNo++) {
				if (lineNo == 1) {
					name = br.readLine();
				} else if (lineNo == 2) {
					taxino = br.readLine();
				}
				else{
					br.readLine();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Enter kilometers travelled");
		km = in.nextInt();
		ut.newLine();

        rate = ut.rateTracker(km);
        amt = rate * km;

		System.out.println("Amount: Rs "+amt);
		ut.newLine();
		System.out.println("Do you want to print the bill? : ");
        char ch = in.next().charAt(0);
		if(ch == 'y')
		{
			ut.print(name, taxino, time, rate, amt, km);
		}
	}
	
}



class utilities
{

    public static void drawLine()
    {
    	for(int i = 0; i < 36; i++)
		{
			System.out.print("-");
		}
		    newLine();
    }

    static void newLine()
    {

	System.out.println();

    }

    public static int rateTracker(int k)
    {
    	int res = 0;

    	if(k <= 10)
        {
        	res = 5;
        }
        else if(k > 10 && k <= 20)
        {
        	res = 7;
        }
        else if(k > 20 && k <= 30) {
        	res = 9;
        }
        else if(k > 30 && k <= 40 )
        {
        	res = 11;
        }
        else if(k > 40)
        {
        	res = 13;
        }
        return res;
    }

    public static void print(String a, String b, String c, int d, int e, int f)
    {
    	newLine();
    	System.out.println("TAXI INVOICE");
    	newLine();
    	System.out.println("Driver: "+a);
    	newLine();
    	System.out.println("Date & Time: "+c);
    	newLine();
    	System.out.println("Taxi"+"\t"+"km"+"\t"+"Rate"+"\t"+"Amount");
    	drawLine();
    	System.out.println(b+"\t"+f+"\t"+d+"\t"+e);
    }
}