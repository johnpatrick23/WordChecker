package com.onecliquezone.wordchecker;

import com.google.gson.Gson;
import com.onecliquezone.oxford_v1.Word;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.onecliquezone.wordchecker.Auth.app_id;
import static com.onecliquezone.wordchecker.Auth.app_key;
import static java.lang.System.*;

public class WordChecker {
    public static void main(String[] args) {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        out.println("Current relative path is: " + s);
        String wordList = readFile(s + "\\src\\com\\onecliquezone\\wordchecker\\Words.json");
        Gson gson = new Gson();
        Words words = gson.fromJson(wordList, Words.class);
        List<String> stringList = new ArrayList<>();
        out.println(netIsAvailable());

        for (String str : words.getCommonWords()) {
            stringList.add(str.toLowerCase());
        }
        while(true){

            Scanner sc = new Scanner(in);
            String word = "";
            String oxford;
            Gson gson1 = new Gson();
            Word word1;
            boolean isValid = true;

            while(isValid){
                out.print("Input word: ");
                word = sc.nextLine();

                out.println("Please wait. . .");
                oxford = getRequest(word);
                try{
                    word1 = gson1.fromJson(oxford, Word.class);
                    if(!word1.getResults().isEmpty()){
                        if(isAbbreviation(oxford)){
                            out.println("Invalid");
                        }
                        else if(word.isEmpty()){
                            out.println("Invalid");
                        }
                        else if(word.length() < 3){
                            out.println("Invalid");
                        }
                        else isValid = false;
                    }else {
                        isValid = false;
                        out.println("Invalid");
                    }
                }catch (Exception e){
                    out.println("Invalid");
                }

            }


            if(stringList.contains(word.toLowerCase())){
                out.println("Meron");
                //isWordExist = true;
            }else {
                out.println("Wala talaga");
            }
        }
    }


    private static boolean isAbbreviation(String json){
        return json.contains("\"text\": \"Abbreviation\"") || json.contains("\"type\": \"abbreviation\"");
    }
    private static boolean netIsAvailable() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }
    private static String readFile(String filePath) {
        StringBuilder myData = new StringBuilder();
        File myExternalFile = new File(filePath);
        try {
            FileInputStream fis = new FileInputStream(myExternalFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;
            while ((strLine = br.readLine()) != null) {
                myData.append(strLine).append("\n");
            }
            br.close();
            in.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myData.toString();
    }

//
//    private boolean writeFile(String value, String absolutePath) {
//        File filePath = new File(absolutePath);
//        FileOutputStream fOut;
//        OutputStreamWriter myOutWriter;
//        try{
//            fOut = new FileOutputStream(filePath);
//            myOutWriter = new OutputStreamWriter(fOut);
//            myOutWriter.append(value);
//            myOutWriter.close();
//            fOut.close();
//            return true;
//        }catch (IOException e){
//            return false;
//        }
//    }



    private static String getRequest(String word) {
        final String word_id = word.toLowerCase();
        final String link = "https://od-api.oxforddictionaries.com/api/v1/entries/en/" + word_id;
        try {
            URL url = new URL(link);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            //TODO: replace with your own app id and app key
            // get your app_id and app_key here -> https://developer.oxforddictionaries.com/
            urlConnection.setRequestProperty("app_id", app_id);
            urlConnection.setRequestProperty("app_key", app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            return stringBuilder.toString();

        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
