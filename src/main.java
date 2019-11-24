import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class main {

    public static void main(String[] args) throws IOException {
        HashSet<String> set=createNames();
        System.out.println(CountSpecificString("Abb"));
    }

    private static HashSet<String> createNames() throws IOException {
        HashSet<String> names = new HashSet<>();
        BufferedReader csv = new BufferedReader(new FileReader("src\\names.csv"));

        String row;
        while ((row = csv.readLine()) != null) {
            String name = row;
            name=fixNameTLowerCase(name);
            names.add(name);
        }
        return names;
    }

    private static String fixNameTLowerCase(String name) {
        char firstLetter=name.charAt(0);
        String restOfTheString=name.substring(1,name.length());
        restOfTheString=restOfTheString.toLowerCase();
        return firstLetter+restOfTheString;
    }

    private static int CountSpecificString(String subString) throws IOException {
        HashSet<String> names=createNames();
        int countAppearanceOfSubString=0;

        for(String name:names){
            countAppearanceOfSubString+=checkSingleName(name,subString);
        }
        return countAppearanceOfSubString;
    }

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
}
