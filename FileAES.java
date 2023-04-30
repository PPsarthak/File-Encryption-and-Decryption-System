import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



public class FileAES {


    private static SecretKeySpec secretkey;
    private static byte[] key;

    //secret key
    public static void setKey(String mykey) {

        try {
            key = mykey.getBytes();  //pass "UTF-8" argument if this doesnt work

            /*key concepts - Checksum
             * Hash function - to produce checksum
             * hash val is a numeric val that is unique for every unique data
             * Message digest - A message digest is a fixed size numeric representation of the contents of a message, computed by a hash function.
             * In java MessageDigest class provies digest using algo like SHA-256
             *
             * */

            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); //creating 16 copies of key
            secretkey = new SecretKeySpec(key, "AES");

        }
        catch (Exception e) {
            System.out.print(e);
        }

    }


    public static void encrypt(String inputFilePath, String EncKey, String outputFilePath){
        //VERY VERY IMP: Pass the inputFile as "File" object, DO NOT pass the path to the file to the
        // FileInputStream() function even tho it accepts that! Passing the path
        // as string to FileInputStream() will lead to currouption of data!!!
        try {

            setKey(EncKey);
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, secretkey);

            //my code for filestream

            File inputFile = new File(inputFilePath);
//            System.out.println(inputFile.length());

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte inputBytes[] = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes); //reads upto inputBytes.length and stores it into the same byte array
//			System.out.print(inputBytes.length);

            byte outputBytes[] = c.doFinal(inputBytes);




            File outFile = new File(outputFilePath);
            if(outFile.createNewFile()) {
                System.out.println("File successfully created at: "+outputFilePath);
            }
            else {
                SwingGUI.l.setText("File already exists, overwriting existing file");
                SwingGUI.fpath.setText("File Overwritten and saved");
                System.out.println("File already exists, overwriting existing file");
            }




            FileOutputStream outputStream = new FileOutputStream(outFile);
            outputStream.write(outputBytes);

            outputStream.close();
            inputStream.close();
        }
        catch (Exception e) {
            System.out.print(e);
        }


    }



    public static void decrypt(String inputFilePath, String EncKey, String outputFilePath){

        //VERY VERY IMP: Pass the inputFile as "File" object, DO NOT pass the path to the file to the
        // FileInputStream() function even tho it accepts that! Passing the path
        // as string to FileInputStream() will lead to currouption of data!!!

        try {

            setKey(EncKey);
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, secretkey);

            //my code for filestream

            //VERY VERY IMP: Pass the inputFile as "File" object, DO NOT pass the path to the file to the
            // FileInputStream() function even tho it accepts that! Passing the path
            // as string to FileInputStream() will lead to currouption of data!!!
            File inputFile = new File(inputFilePath);
            System.out.println(inputFile.length());

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte inputBytes[] = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes); //reads upto inputBytes.length and stores it into the same byte array
//			System.out.print(inputBytes.length);

            byte outputBytes[] = c.doFinal(inputBytes);




            File outFile = new File(outputFilePath);
            if(outFile.createNewFile()) {
                System.out.println("File sucessfully created at: "+outputFilePath);
            }
            else {
                System.out.println("File already exists, overwrinting existing file");
            }




            FileOutputStream outputStream = new FileOutputStream(outFile);
            outputStream.write(outputBytes);

            outputStream.close();
            inputStream.close();
        }
        catch (Exception e) {
            System.out.print(e);
        }
    }


}
