package warehouse.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Theo on 5/9/17.
 */
public class JSONUtil {

    private static Gson gson = new GsonBuilder().create();

    public static void write(OutputStream out, Object obj) throws IOException {
        try (OutputStreamWriter osw = new OutputStreamWriter(out)) {
            osw.write(gson.toJson(obj));
        }
    }

    public static <T> T read(InputStream in, Class<T> cls) throws IOException {
        try (Scanner scanner = new Scanner(in)) {
            return gson.fromJson(scanner.useDelimiter("\\A").next(), cls);
        }
    }

    public static <T> List<T> readList(InputStream in, Class<T> cls) throws IOException {
        Type type = new TypeToken<List<T>>(){}.getType();
        try (Scanner scanner = new Scanner(in)) {
            return gson.fromJson(scanner.useDelimiter("\\A").next(), type);
        }
    }
}