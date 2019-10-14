/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Duong
 */
public class MyURIResolver implements URIResolver {

    // http://www.kieutrongkhanh.net/2018/06/hien-thuc-bo-parser-html-bang-xslt.html
    public Source resolve(String href, String base) throws TransformerException {
        URL url;
        URLConnection connection = null;
        InputStream is = null;
        StreamSource ss = null;
        if (href != null && (href.indexOf("http://www.maccenter.vn") == 0 || href.indexOf("https://mac24h.vn") == 0 || href.indexOf("https://www.macstores.vn") == 0)) {
            try {
                url = new URL(href);
                connection = url.openConnection();
                connection.addRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 6.0; Windows NT 5.0)");
                connection.setReadTimeout(20 * 1000);
                connection.setConnectTimeout(20 * 1000);
                System.out.println("Connect: " + href);
                is = connection.getInputStream();
                ss = preProcessInputStream(is);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

        return ss;
    }

    // http://www.kieutrongkhanh.net/2018/06/hien-thuc-bo-parser-html-bang-xslt.html
    private StreamSource preProcessInputStream(InputStream httpResult) throws IOException {
        String textContent = getString(httpResult);

        textContent = TextUtilities.refineHtml(textContent);

        InputStream htmlResult = new ByteArrayInputStream(textContent.getBytes("UTF-8"));
        return new StreamSource(htmlResult);
    }

    private static String getString(InputStream stream) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException ex) {

        }
        return stringBuilder.toString();
    }
}
