package ru.task.processing.json.preproccesing;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class LoadingPath {
    public static String loadingPath(String path) throws IOException {
        Properties prop = new Properties();
        String propFileName = "config.properties";
        InputStream inputStream = LoadingPath.class.getClassLoader().getResourceAsStream(propFileName);
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
        prop.load(inputStream);
        HashMap<String, String> pathMap = new HashMap<String, String>();
        pathMap.put("path_file", prop.getProperty("local_src_file"));
        pathMap.put("dir_datasets", prop.getProperty("local_target_dir"));
        pathMap.put("path_datasets_reg", new File(prop.getProperty("local_target_dir") , "registered\\").toString());
        pathMap.put("path_datasets_app", new File(prop.getProperty("local_target_dir"), "app_loaded\\").toString());
        return pathMap.get(path);

    }
}