
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class Main {
    public static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args)throws IOException {
        firstTask();
        firstTaskChar();
    }

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

    public static void bufferTask() throws IOException {
        long startTime = System.currentTimeMillis();
        InputStream inputStream = new FileInputStream("test2.rtf");
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
        long time = (endTime - startTime )/ 1000;
        logger.info("Time: " + time);
    }

}
