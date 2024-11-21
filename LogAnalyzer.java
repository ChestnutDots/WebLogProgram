
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         
         records= new ArrayList<LogEntry>();
         
     }
        
     public void readFile(String filename) {
         
         FileResource fileR = new FileResource();
         WebLogParser parser = new WebLogParser();
         
         for (String line : fileR.lines()){
            
             // parse the log entry into single elements:
            LogEntry Log= parser.parseEntry(line);
            
            // add the elements to the arrayList:
            records.add(Log);
            
            }
         
     }
     
     public int countUniqueIPs(){
        
         int nrUniqueIPs=0;
         
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         
         // go through each log entry (le) and read out the IP addresses:
         for (LogEntry le : records){
             
             String currentIP=le.getIpAddress();
             
             // if it does not contain this IP address, add it
             if (!uniqueIPs.contains(currentIP)){
                    
                     uniqueIPs.add(currentIP);
                 
                }
            
            }
         
            nrUniqueIPs=uniqueIPs.size();
            
         return nrUniqueIPs;
        
        }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum(int num){
        
         for (LogEntry le : records){
            
             int currentStatusCode=le.getStatusCode();
             
             if (currentStatusCode>num){
                
                 System.out.println("Higher status code than "+num+": "+le);
                
                }
            
            }
        
        }
        
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        
        ArrayList<String> uniqueVisits = new ArrayList<String>();
        
        // go through each log entry
        for (LogEntry le : records){
        
            Date currentDay = le.getAccessTime();
            // make Date into a string:
            String thisDay=currentDay.toString();
            // take away the time zone and year, only report month and date:
            String testDay=thisDay.substring(4,10);
            
            //System.out.println("String day is "+testDay);
            
            // see if the entry happened on that particular date:
            
            if (testDay.equals(someday)){
                
                // if the dates match, get the IP address of that log entry:
                String currentIP=le.getIpAddress();
                
                //check if the uniqueVisits already has an entry of this IP address
                if (!uniqueVisits.contains(currentIP)){
                    
                    // if not, add it to the uniqueVisits stringArray
                    uniqueVisits.add(currentIP);
                
                }
                
            }
            
        }
        
        return uniqueVisits;
    
    }
    
    public int countUniqueIPsInRange(int low, int high){
    
        int count=0;
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        
        for (LogEntry le : records){
            
            int currentStatusCode=le.getStatusCode();
            
            //check that the status code fits within limits:
            if (currentStatusCode>=low && currentStatusCode<=high){
                
                //if so, get the IP address:
                String currentIP=le.getIpAddress();
                
                // if it is a unique IP address (not in the list already), add to count
                if(!uniqueIPs.contains(currentIP)){
                    
                    //update the list of unique IPs:
                    uniqueIPs.add(currentIP);
                    count+=1;
                }
            
            }
        
        }
        
        return count;
    
    }
    
    public HashMap<String, Integer> countVisitsPerIP(){
    
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        
        //loop through each log entry and read out the IP address:
        for (LogEntry le : records){
        
            String currentIP=le.getIpAddress();
            
            // check if counts already has an entry of this IP address:
            if (!counts.containsKey(currentIP)){
                
                //if it does not, add a new entry (int 1 because first occurence of this ip):
                counts.put(currentIP,1);
            
            }else{
                //if the list already contains the IP address, update the count:
                // you need to use the .get method to access the value of Integer
                counts.put(currentIP, counts.get(currentIP)+1);
            }
            
        
        }
    
        return counts;
    }
    
    public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
    
        int mostVisits=0;
        
        //go through every entry of the hashmap counts:
    
        for (String currentIP : counts.keySet()){
            
            if (counts.get(currentIP)>mostVisits){
            
                mostVisits=counts.get(currentIP);
                String mostVisitIP=currentIP;
            }

        }
        
        return mostVisits;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
    
        ArrayList<String> mostVisitIPs = new ArrayList<String>();
        
        // first figure out what the max number of visits were:
        int mostVisits=mostNumberVisitsByIP(counts);
        
        // now find which IPs had this many visits:
        // loop through each IP:
        for (String currentIP : counts.keySet()){
        
            // if the number of visits matches the max nr, add the IP to the ArrayList:
            if (counts.get(currentIP)==mostVisits){
                
                mostVisitIPs.add(currentIP);
            
            }
        
        }
        
        
        return mostVisitIPs;
    
    }
     
    public HashMap<String,ArrayList<String>>iPsForDays(){
    
        HashMap<String, ArrayList<String>> daysIP = new HashMap <String,ArrayList<String>>();
    
        // loop through each log entry and read out the day:
        for (LogEntry le : records){
        
            Date currentDay = le.getAccessTime();
            // make Date into a string:
            String thisDay=currentDay.toString();
            // take away the time zone and year, only report month and date:
            String testDay=thisDay.substring(4,10);
            
            // see if the hashmap already has test day in the list:
            if (!daysIP.containsKey(testDay)){
                
                //if not, add it:
                
                //get the IP Address:
                String thisIP=le.getIpAddress();
                //make a new ArrayList:
                ArrayList<String> thisList=new ArrayList<String>();
                // add the IP Address to the List
                thisList.add(thisIP);
                // add the List to that day in the HashMap
                daysIP.put(testDay, thisList);
            }else{
            
                String thisIP=le.getIpAddress();   
                // if there already is an entry of that day in the hashmap, add only the IP address:
                
                // fetch the corresponding list:
                ArrayList<String> thisList=daysIP.get(testDay);
                // add the IP Address to the List
                thisList.add(thisIP);
                // add the List to that day in the HashMap
                daysIP.put(testDay, thisList);                
            }
            
        }
        
        return daysIP;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> daysIP){
    
        String dayMost="";
        int mostVisits=0;
        
        for (String thisDay : daysIP.keySet()){
            // fetch the list of IPs for each day:
            ArrayList<String> currentList=daysIP.get(thisDay);
            // get the number of visits from the list:
            int currentCount=currentList.size();
            
            if (currentCount>mostVisits){
                // if number of visits exceeds previous max, update the count and mark the day
                dayMost=thisDay;
                mostVisits=currentCount;
                
            }
            
        
        }
    
        return dayMost;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> daysIP, String day){
    
        ArrayList<String> mostIP = new ArrayList<String>();
        
        // first get the ArrayList from HashMap of the day of the Interest:
        
        ArrayList<String> IPsThatDay=daysIP.get(day);
        
        // make a HashMap with how many IP Adresses were used:
        
        HashMap <String, Integer> numIPday= new HashMap<String, Integer>();
        
        for (String currentIP : IPsThatDay){
        
            // check if the list already has an entry of this IP address:
            if (!numIPday.containsKey(currentIP)){
                
                //if it does not, add a new entry (int 1 because first occurence of this ip):
                numIPday.put(currentIP,1);
            
            }else{
                //if the list already contains the IP address, update the count:
                // you need to use the .get method to access the value of Integer
                numIPday.put(currentIP, numIPday.get(currentIP)+1);
            }
            
        
        }
        
        
        // now figure out which IPs got the most accesses that day:
        
        mostIP=iPsMostVisits(numIPday);

        return mostIP;
    }
}
