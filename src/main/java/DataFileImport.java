import tech.tablesaw.api.Table;

import java.io.IOException;

public class DataFileImport {

    public static void main(String[] args) {
        try {
            Table t = Table.read().csv("facilities.csv");
            System.out.println(t.structure());
            System.out.println(t.first(3));
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
