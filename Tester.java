
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        System.out.println("-----------test log analyzer------------");
        
        LogAnalyzer logAnalyse = new LogAnalyzer();
        
        logAnalyse.readFile("short-test_log");
        
        logAnalyse.printAll();
        
        //int uniqueIPs=logAnalyse.countUniqueIPs();
        //System.out.println(uniqueIPs+ " unique IPs were found.");
        
        System.out.println("----------End of test-----------");
    }
    
    public void testUniqueIP(){
    
        LogAnalyzer logAnalyse = new LogAnalyzer();
        
        logAnalyse.readFile("weblog2_log");
        
        int uniqueIPs=logAnalyse.countUniqueIPs();
        System.out.println(uniqueIPs+ " unique IPs were found.");
        
    
    }
    
    public void testPrintAllHigherThanNum(){
        
        LogAnalyzer logAnalyse = new LogAnalyzer();
        
        logAnalyse.readFile("short-test_log");
        
        logAnalyse.printAllHigherThanNum(400);
    
    }
    
    public void testUniqueIPVisitsOnDay(){
    
        LogAnalyzer logAnalyse = new LogAnalyzer();
        
        logAnalyse.readFile("weblog2_log");
        
        ArrayList<String> visitsThisDay=logAnalyse.uniqueIPVisitsOnDay("Sep 24");
        
        for (String s : visitsThisDay){
        
            System.out.println("Visits on this day were from: "+s);
        }
        
        System.out.println("Size of arrayList is " +visitsThisDay.size());
    }
    
    public void testCountUniqueIPsInRange(){
        
        LogAnalyzer logAnalyse = new LogAnalyzer();
        
        logAnalyse.readFile("weblog2_log");
        
        int countUniqueIPs=logAnalyse.countUniqueIPsInRange(200,299);
        
        System.out.println("Unique IPs in range are "+countUniqueIPs);
    
    }
    
    public void testCountVisitsPerIP(){
        
        System.out.println("********test contVisitsPerIP********");
        
        LogAnalyzer logAnalyse = new LogAnalyzer();
        
        logAnalyse.readFile("short-test_log");
        
        HashMap <String,Integer> counts = logAnalyse.countVisitsPerIP();
        
        System.out.println(counts);
    
    }
    
    public void testMostNumberVisitsByIP(){
    
        System.out.println("***********test MostNumberVisitsByIP************");
        
        LogAnalyzer logAnalyse = new LogAnalyzer();
        
        logAnalyse.readFile("weblog2_log");
        
        HashMap <String,Integer> counts = logAnalyse.countVisitsPerIP();
        
        int mostVisits=logAnalyse.mostNumberVisitsByIP(counts);
        
        System.out.println("Most visits per IP were: "+mostVisits);
    }
    
    public void testIPsMostVisits(){
    
        System.out.println("***********test IPs Most Visits************");
        
        LogAnalyzer logAnalyse = new LogAnalyzer();
        
        logAnalyse.readFile("weblog2_log");
        
        HashMap <String,Integer> counts = logAnalyse.countVisitsPerIP();
        
        ArrayList<String> mostIPs = logAnalyse.iPsMostVisits(counts);
        
        System.out.println("Most visited IPs were: "+mostIPs);
    }
    
    public void testIPsForDays(){
        
        System.out.println("****** test IPs for that day********");
        
        LogAnalyzer logAnalyse = new LogAnalyzer();
        
        logAnalyse.readFile("web3-short_log");
        
        HashMap<String, ArrayList<String>> myList = logAnalyse.iPsForDays();
        
        System.out.println(myList);
    
    }
    
    public void testDayWithMostIPVisits(){
        
        System.out.println("********test day with most IPs*******");
        
        LogAnalyzer logAnalyse = new LogAnalyzer();
        
        logAnalyse.readFile("weblog2_log");
        
        HashMap<String, ArrayList<String>> myList = logAnalyse.iPsForDays();
        
        String popularDay=logAnalyse.dayWithMostIPVisits(myList);
        
        System.out.println("The most popular day is "+popularDay);
    
    }
    
    public void testIPsWithMostVisitsOnDay(){
    
        System.out.println("********** test IPs with most visits that day********");
        
        LogAnalyzer logAnalyse = new LogAnalyzer();
        
        logAnalyse.readFile("weblog2_log");
        
        HashMap<String, ArrayList<String>> myList = logAnalyse.iPsForDays();
        
        String thatDay="Sep 29";
        
        ArrayList<String> IPsDay=logAnalyse.iPsWithMostVisitsOnDay(myList, thatDay);
        
        System.out.println(IPsDay);
    
    }
}
