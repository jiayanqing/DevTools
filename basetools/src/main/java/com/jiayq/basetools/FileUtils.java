package com.jiayq.devtools.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文件工具类
 */
public class FileUtils {

    /**
     * Copy the application database files to external storage
     */
    public static void copyAppDatabaseFiles(Context context, String packageName) {
        try {
            File dbDirectory = new File("/data/data/" + packageName + "/databases/");
            if (dbDirectory.exists()) {
                File[] dbFiles = dbDirectory.listFiles();
                for (int i = 0; i < dbFiles.length; i++) {
                    copyFileToExternalStorage(context, dbFiles[i]);
                }
            }
        } catch (IOException e) {
            Log.e("FileUtil", e.getLocalizedMessage());
        }
    }

    private static void copyFileToExternalStorage(Context context, File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        String outFileName = context.getExternalFilesDir(null) + "/" + file.getName();
        OutputStream output = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fis.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        output.flush();
        output.close();
        fis.close();
    }

}
