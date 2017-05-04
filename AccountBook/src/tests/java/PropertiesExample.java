package tests.java;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Daeyoung Han on 2017. 4. 27..
 */
public class PropertiesExample {
    private static String defaultPropertiesPath = "/Users/1001235/Documents/example.properties";

    public static String getDefaultPropertiesPath() {
        return defaultPropertiesPath;
    }

    public static void setDefaultPropertiesPath(String defaultPropertiesPath) {
        PropertiesExample.defaultPropertiesPath = defaultPropertiesPath;
    }

    public static String getKey(String key) throws Exception {

        // ClassLoader.getResourceAsStream("some/pkg/resource.properties");
        // Class.getResourceAsStream("/some/pkg/resource.properties");
        // ResourceBundle.getBundle("some.pkg.resource");
        String value = null;
        InputStream is = new FileInputStream(defaultPropertiesPath);
        Properties p = null;
        try {
            p = new Properties();
            p.load(is);
            value = p.getProperty(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {is.close();} catch (IOException e) {}
        }
        return value;
    }

    /**
     * 프로퍼티 파일에 사용자 값을 넣는다.
     */
    public static void putPropertie(Map paramMap)
            throws FileNotFoundException, IOException {
        // 프로퍼티 파일 경로 key
        String propertiesKey = "properties.file.path";
        Properties proper = null;
        FileOutputStream output = null;
        try {
            String comment = (String) paramMap.get("properties.comment");
            // 사용자가 프로퍼티 파일 경로를 넘기지 않을 경우 default properties로 셋팅하다.
            if (!paramMap.containsKey(propertiesKey)) {
                paramMap.put(propertiesKey, defaultPropertiesPath);
            }
            output = new FileOutputStream((String) paramMap.get(propertiesKey));
            // paramMap.remove(propertiesKey);
            proper = new Properties();
            proper.putAll(paramMap);
            proper.store(output, comment);
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException("properties 파일을 찾을수 없습니다.");
        } catch (IOException ioe) {
            throw new IOException("putPropertie Exception!", ioe);
        } finally {
            try {
                output.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Map paramMap = new HashMap();
        paramMap.put("properties.file.path", "/Users/1001235/Documents/example12.properties");
        paramMap.put("name", "홍길동");
        paramMap.put("age", "31");
        paramMap.put("phone", "0111234567");
        PropertiesExample.putPropertie(paramMap);

        PropertiesExample.setDefaultPropertiesPath((String) paramMap.get("properties.file.path"));

        System.out.println(PropertiesExample.getDefaultPropertiesPath());
        System.out.println(PropertiesExample.getKey("name"));
    }
}
