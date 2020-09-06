/* Save this in a file called Main.java to compile and test it */

/* Do not add a package declaration */
import java.util.*;
import java.io.*;

/* DO NOT CHANGE ANYTHING ABOVE THIS LINE */
/* You may add any imports here, if you wish, but only from the 
   standard library */

/* Do not add a namespace declaration */

public class Main {
    private static class Library {  //Custom Data strucutre/Class thtat will hold latest version of library and apps using it
        private int version;
        private List<String> appsUsingThisVersion;
        public Library() {
            appsUsingThisVersion = new ArrayList<>();
        }
        public Library(int version, String appName) {
                appsUsingThisVersion = new ArrayList<>();
                this.version = version;
                appsUsingThisVersion.add(appName);
        }
        public int getVersion() {
            return this.version;
        }
        public void setVersion( int version) {
            this.version = version;
        }
        public void addApp(String appName ) {
            appsUsingThisVersion.add(appName);
        }
        public List<String> getAppsUsingThisVersion() {
            return appsUsingThisVersion;
        }

    }
    public static List<String> processData(ArrayList<String> array) {
        /* 
         * Modify this method to process `array` as indicated
         * in the question. At the end, return a List containing the
         * appropriate values
         *
         * Please create appropriate classes, and use appropriate
         * data structures as necessary.
         *
         * Do not print anything in this method.
         *
         * Submit this entire program (not just this method)
         * as your answer
         */
       Map<String, Library> libraryMap = new LinkedHashMap<>();
       Iterator<String> itr = array.iterator(); //Iterate over each input line
       while(itr.hasNext()) {
           String line = itr.next();
           String lineDetails[];
           lineDetails = line.split(", ");
           String appName = lineDetails[0];
           String libraryName = lineDetails[1];
           int version = Integer.parseInt(lineDetails[2].substring(1)); //Skip first character 'v'
           if(libraryMap.get(libraryName)  == null) {
                Library newLibrary = new Library(version, appName);
                libraryMap.put(libraryName, newLibrary);
           }
            else {
                Library currentLibraray = libraryMap.get(libraryName);
                int currentLibraryVersion  = currentLibraray.getVersion();
                if(version > currentLibraryVersion) {
                    Library newLibrary = new Library(version, appName);
                    libraryMap.put(libraryName, newLibrary);
                }
                else if(version == currentLibraryVersion) {
                        currentLibraray.addApp(appName);
                }
                
            }
           
       }
        Set<String> appNamesUsingLatestLibraries = new LinkedHashSet<>();
       libraryMap.forEach((k, v) -> {
            appNamesUsingLatestLibraries.addAll(v.getAppsUsingThisVersion());
           
       });
       List<String> retVal = new ArrayList<String>();
       retVal.addAll(appNamesUsingLatestLibraries);
       return retVal;
    }

    public static void main (String[] args) {
        ArrayList<String> inputData = new ArrayList<String>();
        String line;
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine())
            inputData.add(in.nextLine());
        List<String> retVal = processData(inputData);
        PrintWriter output = new PrintWriter(System.out);
        for(String str: retVal)
            output.println(str);
        output.close();
    }
}
