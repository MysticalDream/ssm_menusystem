package com.mysticaldream.spider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.mysticaldream.common.utils.IdWorkerUtil;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: GetTest
 * @date: 2022/6/14 21:25
 * @author: MysticalDream
 */
public class GetTest {

    String[] userAgent = {"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11",
            "User-Agent:Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11",
            "Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0",
            " Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1",
            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1",
            " Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:2.0.1) Gecko/20100101 Firefox/4.0.1",};

    @Test
    public void test() throws Exception {
        for (int i = 1; i <= 50; i++) {
            URL url = new URL("https://home.meishichina.com/ajax/ajax.php?ac=recipe&op=getMoreDiffStateRecipeList&classid=0&orderby=hot&page=" + i);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", userAgent[getRandomIndex()]);
            connection.setRequestProperty("Host", "home.meishichina.com");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Referer", "https://home.meishichina.com/recipe.html");
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String temp;
            StringBuilder builder = new StringBuilder(16);
            while ((temp = reader.readLine()) != null) {
                builder.append(temp);
            }
            handle(builder);
            Thread.sleep(500);
        }
        writeListJson();

    }

    ObjectMapper objectMapper = new ObjectMapper();

    public void handle(StringBuilder builder) throws IOException {

        ObjectReader reader1 = objectMapper.reader();
        Map<Object, Object> map = reader1.readValue(builder.toString(), Map.class);
        List<Object> data = (List<Object>) map.get("data");
        data.forEach(e -> {
            Map<String, String> e1 = (Map<String, String>) e;
            createOne(e1);
        });
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data));
    }

    List<Object> dataList = new ArrayList<>();

    IdWorkerUtil idWorkerUtil = new IdWorkerUtil();

    NumberFormat instance = NumberFormat.getInstance();

    int i = 1;

    public void createOne(Map<String, String> map) {
        String mpic = map.get("mpic");
        String imgName = idWorkerUtil.nextId() + ".jpg";
        //写入图片
        writerImg(mpic, imgName);
        Map<String, Object> data = new HashMap<>();
        data.put("id", i++);
        data.put("menu_name", map.get("title"));
        data.put("intro", map.get("mainingredient"));
        data.put("cover_url", imgName);
        data.put("cid", ((int) (Math.random() * 24)) + 1);
        data.put("create_time", "2022/5/28 19:56:31");
        data.put("update_time", "2022/5/28 19:56:31");
        data.put("create_uid", 1);
        data.put("update_uid", 1);
        data.put("is_deleted", 0);
        instance.setMaximumFractionDigits(1);
        data.put("score", instance.format(Math.random() * 9 + 1));
        data.put("score_count", ((int) (Math.random() * 100)) + 1);
        dataList.add(data);
        System.out.println(data);
    }


    public void writeListJson() {
        try {
            File file = new File("D:/桌面/test.json");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataList);
            bufferedWriter.write(s, 0, s.length());
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int getRandomIndex() {
        return (int) (Math.random() * userAgent.length);
    }


    public void writerImg(String url, String imgName) {
        try {
            URL url1 = new URL(url);
            URLConnection connection = url1.openConnection();
            connection.setRequestProperty("User-Agent", userAgent[getRandomIndex()]);
            connection.setRequestProperty("referer", "https://home.meishichina.com/");
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            OutputStream outputStream = new FileOutputStream("C:\\menu_system_resources\\menu_img\\" + imgName);
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
