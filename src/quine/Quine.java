/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileReader;
/**
 *
 * @author Abhinandan
 */
public class Quine
{
    static String OS = System.getProperty("os.name").toLowerCase();
    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }
  public static void main(String[] args)
  {
        if (isUnix()) {
            String[] statement = new String[]{ "/bin/bash", "-c", "" };
            try {
                Process p = Runtime.getRuntime().exec(statement);
            } catch (IOException ex) {
                Logger.getLogger(Quine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (isWindows()) {
            try {
                String udir = System.getProperty("user.dir");
              //  System.out.println(udir);
                Process p1 = Runtime.getRuntime().exec("cmd /c cd " + udir);
                Process p2 = Runtime.getRuntime().exec("cmd /c dir /S /P *.java");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p2.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if(line.contains("Directory of")){
                        line = line.replace(" Directory of ", "");
                        System.out.println(line);
                        break;
                    }
                }
                bufferedReader.close();
                String cmdop=line + "\\Quine.java";
             //   System.out.println(cmdop);
                
               BufferedReader br = null;
 
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(cmdop));
 
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
               // System.out.println("done.");
            } catch (IOException ex) {
                Logger.getLogger(Quine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
  }
}
