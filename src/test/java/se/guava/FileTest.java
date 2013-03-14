package se.guava;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.google.common.io.Resources;

public class FileTest {

    @Test
    public void filesOld() {
        System.out.println("filesOld");

        try {
            File file = new File(getClass().getClassLoader().getResource("movies.txt").toURI());
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            List<String> lines = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
            process(lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void process(List<String> lines) {
        System.out.println(lines);
    }

    @Test
    public void files() {
        System.out.println("files");
        try {
            File file = new File(getClass().getClassLoader().getResource("movies.txt").toURI());
            File from = null;
            File to = null;
            Files.touch(file);
            Files.move(from, to);
            Files.append("data", file, Charsets.UTF_8);
            List<String> lines = Files.readLines(file, Charsets.UTF_8);
            process(lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void resources() {
        System.out.println("resources");

        try {
            List<String> lines = Resources.readLines(Resources.getResource("movies.txt"), Charsets.UTF_8);
            process(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void oldByteStream() {
        System.out.println("oldByteStream");
        int bufferSize = 4;
        try {

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("movies.txt");
            StringBuilder stringBuilder = new StringBuilder();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] buffer = new byte[bufferSize];
            int readBytes;
            while ((readBytes = bufferedInputStream.read(buffer)) > 0) {
                String string = new String(buffer, Charsets.UTF_8);
                stringBuilder.append(string.substring(0, readBytes));
            }
            inputStream.close();
            String result = stringBuilder.toString();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bytestreams() {
        System.out.println("bytestreams");
        try {
            InputStream inputstream = getClass().getClassLoader().getResourceAsStream("movies.txt");
            byte[] bytes = ByteStreams.toByteArray(inputstream);
            String result = new String(bytes, Charsets.UTF_8);
            inputstream.close();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
