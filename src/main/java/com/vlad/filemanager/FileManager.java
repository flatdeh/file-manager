package com.vlad.filemanager;

import java.io.*;

public class FileManager {
    private static final int BUFFER_SIZE = 2048;

    public static int calculateFiles(String path) {
        File calculatePath = new File(path);
        int count = 0;
        File[] filesList = calculatePath.listFiles();

        if (calculatePath.exists() && filesList != null) {
            for (File file : filesList) {
                if (file.isDirectory()) {
                    count += calculateFiles(file.getPath());
                } else {
                    count++;
                }
            }
        }
        return count;
    }

    public static int calculateDirs(String path) {
        File calculatePath = new File(path);
        int count = 0;
        File[] filesList = calculatePath.listFiles();

        if (calculatePath.exists() && filesList != null) {
            for (File file : filesList) {
                if (file.isDirectory()) {
                    count += calculateDirs(file.getPath());
                    count++;
                }
            }
        }
        return count;
    }

    public static void copy(String from, String to) throws Exception {
        File fileFrom = new File(from);
        File fileTo = new File(to, fileFrom.getName());
        File[] filesList = fileFrom.listFiles();

        if (fileFrom.exists()) {
            if (filesList != null) {
                fileTo.mkdirs();
                for (File file : filesList) {
                    if (file.isDirectory()) {
                        copy(file.getPath(), fileTo.getPath());
                    } else {
                        copyFile(file, new File(fileTo, file.getName()));
                    }
                }
            }
        }
    }

    private static void copyFile(File fileFrom, File fileTo) throws IOException {
        try (InputStream inputStream = new FileInputStream(fileFrom);
             OutputStream outputStream = new FileOutputStream(fileTo)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int count;
            while ((count = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
            }
        }
    }

    public static void move(String from, String to) throws Exception {
        copy(from, to);
        delete(new File(from));
    }

    private static void delete(File path) {
        File[] filesList = path.listFiles();

        if (filesList != null) {
            for (File file : filesList) {
                if (file.isDirectory()) {
                    delete(file);
                } else {
                    file.delete();
                }
            }
            path.delete();
        }
    }

}