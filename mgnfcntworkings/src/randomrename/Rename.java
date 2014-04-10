//This package is triggered by shell(regedit) command when user select any file and
//right clicked it user sees Random Rename option.Put this jar file under C directly 
//Please run reg file before fire this jar file
package randomrename;
 
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
 
 
public class Rename {
 
	public static void main(String[] args) {
		File oldfile;
		File newfile;
		try {
			String loc = null;
			for (String s : args) {
				loc = s;
			}
 
			String path = loc;
 
			File oldFile = new File(path);
 
			String fileName = oldFile.getName();
			String filepath = oldFile.getParent();
			String extension = "";
 
			String newFileName = "AutoNamedFile_";
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
			Date date = new Date();
			newFileName += dateFormat.format(date);
 
			int i = fileName.lastIndexOf('.');
			int p = Math.max(fileName.lastIndexOf('/'),
					fileName.lastIndexOf('\\'));
 
			if (i > p) {
				extension = fileName.substring(i + 1);
			}
			extension = extension.equals("") ? extension : ("." + extension);
			oldfile = new File(path); 
			newfile = new File(filepath + "\\" + newFileName + extension);
			oldfile.renameTo(newfile);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Rename failed  \n" + ex);
		}
 
	}
 
}