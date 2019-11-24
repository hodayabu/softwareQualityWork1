import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class main {

    public static void main(String[] args) throws IOException {
        System.out.println("kk");

    }
//create the names
    private static HashSet<String> createNames() throws IOException {
        HashSet<String> names = new HashSet<>();
        BufferedReader csv = new BufferedReader(new FileReader("src\\names.csv"));

        String row;
        while ((row = csv.readLine()) != null) {
            String name = row;
            name= fixNameToLowerCase(name);
            names.add(name);
        }
        return names;
    }
    private static String fixNameToLowerCase(String name) {
        char firstLetter=name.charAt(0);
        String restOfTheString=name.substring(1,name.length());
        restOfTheString=restOfTheString.toLowerCase();
        return firstLetter+restOfTheString;
    }

//ass1
    /**
     * Counts the Occurrence of a given sub string in the program names
     * @param subString- The given sub string which checked to be exist in the program names
     * @throws IOException
     */
    private static void CountSpecificString(String subString) throws IOException {
        HashSet<String> names=createNames();//create a set of all the program names who parsed from the English Names website
        int countAppearanceOfSubString=0;// An counter variable to count all of the occurrence

        //iterates the names and check for each one the number of the sub string appearance
        for(String name:names){
            countAppearanceOfSubString+=checkSingleName(name,subString);
        }
        System.out.println(countAppearanceOfSubString);
    }
    /**
     * Help method which checked for given name the number of times sub string appearance in
     * @param name- The string we check if contains sub string and how many times
     * @param subString- The string we check if exist in the name param and how many times
     * @return
     */
    private static int checkSingleName(String name,String subString) {
        int lastIndex = 0;
        int count = 0;

        while(lastIndex != -1){

            lastIndex = name.indexOf(subString,lastIndex);

            if(lastIndex != -1){
                count ++;
                lastIndex += subString.length();
            }
        }
        return count;
    }



//ass2
    /**
     *
     * @param length of substring
     * count all posible substrings in all the names and for each substring print its frequncy
     * not ignore capital letters
     */
    public static void CountAllStrings(int length) throws IOException {

        HashSet<String> names=createNames();  ////read all nameד from file
        HashMap<String,Integer> allSubStrings=new HashMap<>();  //key=subString , value=num of appearances
        for(String name:names )
            count_Every_posible_Sub_String(allSubStrings,length,name,false);

        //print the resuls
        for(HashMap.Entry<String,Integer> entry: allSubStrings.entrySet())
            System.out.println(entry.getKey()+":"+entry.getValue());
    }
    /**
     *
     * @param allSubStrings
     * @param length
     * @param name
     * @param ignoreCapitalLetters-true or false if the func shuld ignore capital letters
     */
    private static void count_Every_posible_Sub_String(HashMap<String, Integer> allSubStrings,int length,String name,boolean ignoreCapitalLetters) {
        for(int i=0;i+length<=name.length() ; i++){
            String subString=name.substring(i,i+length);

            if(ignoreCapitalLetters)
                subString=subString.toLowerCase();

            if(allSubStrings.containsKey(subString))
                allSubStrings.put(subString,allSubStrings.get(subString)+1);
            else
                allSubStrings.put(subString,1);
        }


    }



//ass3
    /**
     *
     * @param length of substring
     * print the most frequent substring from all the names(ignore capital letters)
     */
    public static void  countMaxString (int length) throws IOException {
        HashSet<String> names=createNames();   ////read all nameד from file
        HashMap<String,Integer> allSubstrings=new HashMap<>();   //key=subString , value=num of appearances

        //for each name-count every posible substring
        for(String name: names)
            count_Every_posible_Sub_String(allSubstrings,length,name,true);

        String max_frequncy_substring=find_max_frequncy_string(allSubstrings);
        System.out.println(max_frequncy_substring);

    }
    /**
     *
     * @param allSubstrings
     * @return the substring with the max frequncy
     */
    private static String find_max_frequncy_string(HashMap<String, Integer> allSubstrings) {
        int maxFrequncy=0;
        String subString="";
        for(HashMap.Entry<String,Integer> entry: allSubstrings.entrySet()){
            if(entry.getValue()>maxFrequncy){
                maxFrequncy=entry.getValue();
                subString=entry.getKey();
            }
        }
        return subString;
    }


//ass4
    /**
     * This function check if one of the program names is sub string in another string that received as parameter
     * @param str- The string in which we look for sub strings
     * @throws IOException
     */
    private static void allContainsNames(String str) throws IOException {
        HashSet<String> names=createNames();//create a set of all the program names who parsed from the English Names website
        HashSet<String> namesInStr=new HashSet<>();//A new set in order to save all of the name which are sub string of str
        str=str.toLowerCase();
        //iterates the names and check if its sub string of str
        for(String name:names){
            name=name.toLowerCase();
            if(str.contains(name)){
                namesInStr.add(name);
            }
        }
        //iterates the name found and prints them
        for(String name:namesInStr){
            System.out.println(name);
        }
    }
}
