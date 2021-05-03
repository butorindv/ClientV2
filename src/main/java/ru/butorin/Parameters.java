package ru.butorin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;

@Component
public class Parameters {
    private static final SimpleDateFormat FORMAT_FOR_DATE_NOW = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    @Value("${port}")
    private int portParam;
    @Value("${host}")
    public  String hostAddress;


    public static SimpleDateFormat getFormatForDateNow() {
        return FORMAT_FOR_DATE_NOW;
    }

    public int getPortParam() {
        return portParam;
    }

    public String getHostAddress() {
        return hostAddress;
    }
}
