import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator
{
    public static void main(String[] args)
    {
        ArrayList<String> recs = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        boolean doneInput = false;

        String ID = "";
        String fName = "";
        String lName = "";
        String title = "";
        String rec = "";
        int yob = 0;

        String fileName = "";


        do
        {
            ID = SafeInput.getNonZeroLenString(in, "Enter your ID");
            fName = SafeInput.getNonZeroLenString(in, "Enter your First Name");
            lName = SafeInput.getNonZeroLenString(in, "Enter your Last Name");
            title = SafeInput.getNonZeroLenString(in, "Enter your Title");
            yob = SafeInput.getRangedInt(in, "Enter the year of your birth", 1000, 9999);

            rec = ID + ", " + fName + ", " + lName + ", " + title + ", " + yob;

            recs.add(rec);


            doneInput = SafeInput.getYNConfirm(in, "Are you done entering records?");

        } while(!doneInput);

        fileName = SafeInput.getNonZeroLenString(in, "Enter desired file name");

        // uses a fixed known path:
        //  Path file = Paths.get("c:\\My Documents\\data.txt");

        // use the toolkit to get the current working directory of the IDE
        // will create the file within the Netbeans project src folder
        // (may need to adjust for other IDE)
        // Not sure if the toolkit is thread safe...
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName + ".txt");

            try
            {
                // Typical java pattern of inherited classes
                // we wrap a BufferedWriter around a lower level BufferedOutputStream
                OutputStream out =
                        new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                BufferedWriter writer =
                        new BufferedWriter(new OutputStreamWriter(out));

                // Finally can write the file LOL!

                for(String r : recs)
                {
                    writer.write(r, 0, r.length());  // stupid syntax for write rec
                    // 0 is where to start (1st char) the write
                    // r. length() is how many chars to write (all)
                    writer.newLine();  // adds the new line

                }
                writer.close(); // must close the file to seal it and flush buffer
                System.out.println("Data file written!");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
    }
}
