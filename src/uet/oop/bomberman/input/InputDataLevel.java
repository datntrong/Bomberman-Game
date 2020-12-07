package uet.oop.bomberman.input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputDataLevel {
    public void readData(String DATA_FILE_PATH){
        try {

            FileReader fis = new FileReader(DATA_FILE_PATH);
            BufferedReader br = new BufferedReader(fis);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
