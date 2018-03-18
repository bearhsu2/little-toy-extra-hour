package idv.kuma;

import com.google.gson.Gson;

import java.time.format.DateTimeFormatter;

public class Constants {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static Gson gson = new Gson();

}
