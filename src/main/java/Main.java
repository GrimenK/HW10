
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;

public class Main {
    public static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args)throws IOException {
        //TASK 1
        firstTask();
        firstTaskChar();

        //TASK 2
        bufferTask();
        withoutBuffer();

        //TASK 4
        displayContents("/Users/oleh/Desktop/Work/HW10/.idea");

        //TASK 5
        User user = new User("Alex", 228, new Credentials("1","2"));
        serialize(user);
        deserialize();
    }


    //TASK 1
    public static void firstTask(){
        try(OutputStream outputStream = new FileOutputStream("test2.rtf",true)){
            String text = "hello";
            outputStream.write(text.getBytes());
        }
        catch (IOException e){
            logger.info("Exception");
            e.printStackTrace();
        }

        try(InputStream inputStream = new FileInputStream("test2.rtf")){
           byte[] bytes = inputStream.readAllBytes();
           String result = new String(bytes);
           logger.info(result);
        }
        catch (IOException e){
            logger.info("Exception");
            e.printStackTrace();
        }

    }

    public static void firstTaskChar() throws IOException {
        Writer writer = new FileWriter("test2.rtf",true);
        String s = "string";
        writer.write(s);
        writer.close();

        Reader reader = new FileReader("test2.rtf");
        int intValueOfChar;
        String targetString = "";
        while ((intValueOfChar = reader.read()) != -1) {
            targetString += (char) intValueOfChar;
        }
        reader.close();
    }

    //TASK 2
    public static void bufferTask() throws IOException {
        long startTime = System.currentTimeMillis();
        InputStream inputStream = new FileInputStream("20mb.pdf");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);

        int data = inputStream.read();
        try{
            while (true){
                byte b = dataInputStream.readByte();
            }
        }catch (EOFException e){
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        logger.info("Time: " + time);
    }


    public static void withoutBuffer(){
        long startTime = System.currentTimeMillis();
        try(InputStream inputStream = new FileInputStream("20mb.pdf")){
            byte[] bytes = inputStream.readAllBytes();
            String result = new String(bytes);

        }
        catch (IOException e){
            logger.info("Exception");
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        logger.info("Time: " + time);
    }

    //TASK 4
    public static void displayContents(String path) throws IOException {
        String dirName = path;
        File fileName = new File(dirName);
        File[] fileList = fileName.listFiles();

        for (File file : fileList
             ) {
            logger.info("File Name: " + file.getName());
            logger.info("File Size in bytes: " + file.length());
        }

    }

    //TASK 5
    public static void serialize(User user) {
        try(OutputStream outputStream = new FileOutputStream("user.rtf",false)){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(user);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserialize() {
        try(InputStream inputStream = new FileInputStream("user.rtf");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)){
            User user = (User) objectInputStream.readObject();
            logger.info(user);
        }
        catch (IOException e){
            logger.info("Exception: ");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            logger.info("Exception: ");
            e.printStackTrace();
        }
    }
}
