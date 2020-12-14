import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class UploadImage {

    public static void main(String[] args) throws Exception {
        String md5 = uploadImage("http://106.15.251.138:4869/upload","C:\\Users\\admin\\Desktop\\dsafefe_y.jpg");
        System.out.println(md5);
    }

    public static String uploadImage(String imageURL,String filePath) throws Exception {
        String md5 = null;
        URL url = new URL(imageURL);
        URLConnection connection = url.openConnection();
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);
        HttpURLConnection uc = (HttpURLConnection) connection;

        uc.setRequestMethod("POST");
        uc.setRequestProperty("Connection", "Keep-Alive");
        uc.setRequestProperty("Cache-Control", "no-cache");
        uc.setRequestProperty("Content-Type", "jpg");
        uc.setRequestProperty("COOKIE", "william");
        uc.setDoOutput(true);
        uc.setDoInput(true);

        uc.connect();

        OutputStream om = uc.getOutputStream();
        FileInputStream in = new FileInputStream(filePath);
        byte[] buf = new byte[1024];
        while (true) {
            int len = in.read(buf);
            if (len <= 0)
                break;
            om.write(buf, 0, len);
        }

        InputStreamReader im = new InputStreamReader(uc.getInputStream(),
                "UTF-8");
        char[] bb = new char[1024];
        while (true) {
            int length = im.read(bb);
            if (length == -1)
                break;
            char[] bc = new char[length];
            for (int i = 0; i < length; i++)
                bc[i] = bb[i];
            md5 += new String(bc);
        }

        im.close();
        uc.disconnect();
        return md5;
    }
}
