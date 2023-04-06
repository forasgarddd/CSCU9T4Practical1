// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;




import java.util.*;


public class TrainingRecord {
    private List<Entry> tr;
    
    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor
    
    // add a record to the list
   public void addEntry(Entry e){
       for(Entry en : tr) {
           if(Objects.equals(en.getName(), e.getName()) && en.getYear() == e.getYear() &&
           en.getMonth() == e.getMonth() && en.getDay() == e.getDay()) {
               return;
           }
       }
       tr.add(e);
   } // addClass

    public void removeEntry(Entry e) {
        tr.remove(e);
    }
   
   // look up the entry of a given day and month
   public String lookupEntry (int d, int m, int y) {
       ListIterator<Entry> iter = tr.listIterator();
       String result = "No entries found";
       while (iter.hasNext()) {
          Entry current = iter.next();
          if (current.getDay()==d && current.getMonth()==m && current.getYear()==y) 
             result = current.getEntry();
            }
       return result;
   } // lookupEntry

    public String findAllEntries (int d, int m, int y) {
        ListIterator<Entry> iter = tr.listIterator();
        String result = "";
        //String result = "Sorry couldn't find anything for this date";
        if(!iter.hasNext()) {
            return result = "Sorry couldn't find anything for this date";
        }
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getDay()==d && current.getMonth()==m && current.getYear()==y) {
                result += current.getEntry();
            } else {
                result = "Sorry couldn't find anything for this date";
            }

        }
        return result;
    }


    // Count the number of entries
   public int getNumberOfEntries(){
       return tr.size();
   }
   // Clear all entries
   public void clearAllEntries(){
       tr.clear();
   }

    public List<Entry> getTr() {
        return tr;
    }

    public void setTr(List<Entry> tr) {
        this.tr = tr;
    }
} // TrainingRecord