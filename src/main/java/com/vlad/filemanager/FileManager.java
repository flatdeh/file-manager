package com.vlad.filemanager;

import java.io.*;

public class FileManager {
    private static final int BUFFER_SIZE = 2048;

    public static int calculateFiles(String path) {
        File calculatePath = new File(path);
        int count = 0;

        if (calculatePath.exists()) {
            for (File file : calculatePath.listFiles()) {
                if (file.isDirectory()) {
                    count += calculateFiles(file.getPath());
                } else {
                    count++;
                }
            }
        } else {
            count = -1;
        }
        return count;
    }

    public static int calculateDirs(String path) {
        File calculatePath = new File(path);
        int count = 0;
        if (calculatePath.exists()) {
            for (File file : calculatePath.listFiles()) {
                if (file.isDirectory()) {
                    count += calculateDirs(file.getPath());
                    count++;
                }
            }
        } else {
            count = -1;
        }
        return count;
    }

    public static void copy(String from, String to) throws Exception {
        File fileFrom = new File(from);
        File fileTo = new File(to + "/" + fileFrom.getName());

        if (fileFrom.exists()) {
            if (fileFrom.isDirectory()) {
                fileTo.mkdirs();
                for (File file : fileFrom.listFiles()) {
                    if (file.isDirectory()) {
                        copy(file.getPath(), fileTo + "/");
                    } else {
                        copyFile(file, new File(fileTo + "/" + file.getName()));
                    }
                }
            } else {
                copyFile(fileFrom, fileTo);
            }
        }

    }

    private static void copyFile(File fileFrom, File fileTo) throws IOException {
        try (InputStream inputStream = new FileInputStream(fileFrom);
             OutputStream outputStream = new FileOutputStream(fileTo)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int count;
            while ((count = inputStream.read(buffer)) != -1) {
                String value = new String(buffer, 0, count);
                outputStream.write(value.getBytes());
            }
        }
    }

    public static void move(String from, String to) {
        File fileFrom = new File(from);
        File fileTo = new File(to);

        if (fileFrom.exists()) {
            if (!fileTo.exists()) {
                fileTo.mkdirs();
            }
            fileFrom.renameTo(new File(fileTo + "/" + fileFrom.getName()));
        }
    }
}