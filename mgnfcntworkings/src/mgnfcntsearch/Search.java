package mgnfcntsearch;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Search {

    private static List<File> sourceFiles = new ArrayList();
    private static List<File> sourceFileList;

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {

        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.setDialogTitle("Please Select A Directory , You wish search");
        f.setDialogType(FileDialog.SAVE);
        f.setApproveButtonText("Open Directory");
        f.setApproveButtonToolTipText("LOAD");
        f.showSaveDialog(null);

        String programDir = "C:/Program Files (x86)/Notepad++/";
        String docDir = f.getSelectedFile().getPath();

        if (null != docDir && !docDir.isEmpty()) {
            sourceFileList = getFiles(docDir);

            String searchString = JOptionPane
                    .showInputDialog("What Do You Want to Search?");
            List paths = new ArrayList();

            if (null != searchString && !searchString.equals("")
                    && !searchString.isEmpty()) {
                boolean isFoundStringAtDirectory = false;
                for (int i = 0; i < sourceFileList.size(); i++) {
                    String fileContent = readFile(sourceFileList.get(i)
                            .getPath());
                    if (containsWord(fileContent, searchString)) {
                        String path = sourceFileList.get(i).getPath();
                        String fileName = getFileName(path);
                        ProcessBuilder builder = new ProcessBuilder(
                                new String[]{programDir + "notepad++.exe",
                                    path});
                        builder.start();
                        isFoundStringAtDirectory = true;
                    }
                }
                if (!isFoundStringAtDirectory) {
                    JOptionPane.showMessageDialog(null,
                            "Your Search String Couldnt Find the Directory = "
                            + docDir);
                }
            }
        }

    }

    private static String getFileName(String url) {

        int slashIndex = url.lastIndexOf('\\');
        int dotIndex = url.lastIndexOf('.', slashIndex);
        String filenameWithoutExtension;
        if (dotIndex == -1) {
            filenameWithoutExtension = url.substring(slashIndex + 1);
        } else {
            filenameWithoutExtension = url.substring(slashIndex + 1, dotIndex);
        }
        return filenameWithoutExtension;
    }

    private static boolean containsWord(String s, String w) {
        s = s.toLowerCase();
        w = w.toLowerCase();
        boolean result = s.contains(w);
        return result;
    }

    private static String readFile(String pathname) throws IOException {

        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int) file.length());
        Scanner scanner = new Scanner(file);
        String lineSeparator = System.getProperty("line.separator");

        try {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }

    private static void showIt() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "";
        System.out.println("Type \"x\" to exit..");
        do {
            str = br.readLine();
            System.out.println(str);
        } while (str != "x");

    }

    private static List<File> getFiles(String path) {

        File[] arrayOfFile1;
        File root = new File(path);

        File[] list = root.listFiles();

        if (list == null) {
            return null;
        }

        int j = (arrayOfFile1 = list).length;
        for (int i = 0; i < j; ++i) {
            File f = arrayOfFile1[i];

            if ((f.isDirectory()) && (!(f.getName().equals(".svn")))) {
                getFiles(f.getAbsolutePath());
            } else if (!(f.getName().equals(".svn"))) {
                sourceFiles.add(f.getAbsoluteFile());
            }
        }
        return sourceFiles;
    }

}
