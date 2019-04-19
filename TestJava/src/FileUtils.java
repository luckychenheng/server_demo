import java.util.Arrays;

public class FileUtils {

    public static void main (String[] params) {
//        try {
//
//            System.out.println("Test ==> " + FileUtils.appendQiNuiFileUrl("user/20190418/a7/0b158f39853746afafe9930c230b21a7.jpeg"));
//        } catch (Exception e){
//
//            e.printStackTrace();
//        }
        String str = "user/20190418/a7/0b158f39853746afafe9930c230b21a7.jpeg,user/20190418/a7/0b158f39853746afafe9930c230b21a7.jpeg,user/20190418/a7/0b158f39853746afafe9930c230b21a7.jpeg";
        String[] strs = str.split(",");
        for (int i = 0;i < strs.length; i ++) {
            System.out.println(strs[i]);
        }
    }

    private static String [] imageTypes = new String[] {"jpg","png","jpeg"};
    private static String [] fileTypes = new String[] {"rar","zip","doc","docx","xls","xlxs"};
    private static String [] videoTypes = new String[] {"mp4"};

    /**
     * 获取字符串 (/xxx/xxx/xxx.jpg) 文件类型
     * @param urlSuffix /xxx/xxx/xxx.jpg
     * @return
     */
    public static String getFileType(String urlSuffix) throws Exception {
        if (urlSuffix != null) {
            int dotIndex = urlSuffix.lastIndexOf(".");
            urlSuffix = urlSuffix.substring(dotIndex + 1,urlSuffix.length());
        }
        return urlSuffix;
    }

    /**
     * 拼接七牛的URL
     * @param url
     * @return
     */
    public static String appendQiNuiFileUrl(String url) throws Exception {
        String imagePrefix = "https://image.seecsee.com/";
        String videoPrefix = "https://video.seecsee.com/";
        String filePrefix = "https://static.seecsee.com/";
        String result = "";
        String suffix = FileUtils.getFileType(url);
        if (Arrays.asList(imageTypes).contains(suffix)) {
            result = imagePrefix + url;
        } else if (Arrays.asList(fileTypes).contains(suffix)) {
            result = filePrefix + url;
        } else if (Arrays.asList(videoTypes).contains(suffix)) {
            result = videoPrefix + url;
        }
        return result;
    }


}
