import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {


        File myFile = new File("files/valami.txt");

        try {
            if (myFile.createNewFile()) {
                System.out.println("Fájl létrehozva");
            } else System.out.println("A fájl már létezik");
        } catch (IOException io) {
            System.out.println("IO exception");
        }



        try {
            FileWriter fw = new FileWriter(myFile); // Charset.forname "UTF-8" - megadható neki a karakterkód típusa
            fw.write("Almafa" + System.lineSeparator()); // így platform-független lesz a kód, a \n ebből a szempontból nem jó megoldás
            fw.write("Cicus" + System.lineSeparator());
            fw.write("Kutyus" + System.lineSeparator());
            fw.write("Árvíztűrőtükörfúrógép" + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("Írási hiba! " + e);
        }


        pathMethod();

        scannerMethod(myFile);

        gSonMehtod();

    }


    public static void pathMethod() throws IOException {
        Path pathToFile = Paths.get("files/cicamappa/mirci.txt");

        try {
            Files.createDirectories(pathToFile.getParent());
            if (!Files.exists(pathToFile))Files.createFile(pathToFile);
            else System.out.println("A fájl már létezik!");
        }catch (IOException io){
            System.out.println("io");
        }

    }

    public static void scannerMethod(File file){

        try {
            Scanner sc = new Scanner(file);
            int rowNumber = 1;
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                System.out.println(rowNumber++ + ". " + "Adott sor tartalma: " + line);
            }sc.close();
        }catch (Exception e){
            System.out.println("Hiba a gépezetben");
        }
    }

    public static void gSonMehtod() throws IOException {

        String content = Files.readString(Paths.get("Cats.json"));
        Gson gs = new Gson();
        List<Cats> catList = new ArrayList<>(Arrays.asList(gs.fromJson(content,Cats[].class)));

        catList.forEach(System.out::println);

        catList.add(new Cats("Cirmi",3,"Green",true,22));
        catList.forEach(System.out::println);

        Gson newCatToJson = new GsonBuilder().setPrettyPrinting().create();
        String withnewCat = newCatToJson.toJson(catList);
        System.out.println(withnewCat);


        FileWriter fileWriter = new FileWriter("files/cicamappa/mirci.txt");
        fileWriter.write(withnewCat);
        fileWriter.close();




    }


}