package ru.butorin;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

@Component
public class Client {

        public void communicationSession(Parameters parameters) {
            Socket soc;
            try {
                soc = new Socket(parameters.getHostAddress(), parameters.getPortParam());
                System.out.println("Вы подключены к серверу: " + soc.getInetAddress().getHostName()+", порт: "+soc.getPort());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));

                Scanner scanner = new Scanner(System.in);
                System.out.print("Введите свое имя: ");
                String name = scanner.nextLine();
                System.out.print("Введите фамилию: ");
                String secondName = scanner.nextLine();

                while (true) {
                    System.out.print("Введите сообщение: ");
                    String massage = scanner.nextLine();
                    //Формируем JSON для отправки на сервер
                    JSONObject massageObject = new JSONObject();
                    massageObject.put("Request", new JSONObject()
                            .put("User", new JSONObject().put("Name", name).put("SecondName", secondName))
                            .put("Massage", new JSONObject()
                                    .put("Body", massage)
                                    .put("Timestamp", Parameters.getFormatForDateNow().format(new Date()))));

                    //Отправляем на сервер сообщение
                    writer.write(massageObject + "\n");
                    writer.flush();
                    //Выходим если ввели \exit
                    if (massage.contains("\\exit")) {
                        break;
                    }
                    //Получаем ответ от сервера
                    JSONObject response = new JSONObject(reader.readLine());
                    System.out.println("Сервер: " + response.getJSONObject("Response").getJSONObject("Massage").get("Body"));
                }
                System.out.println("Мы отключились от сервера.");
            } catch (IOException e) {
//                e.printStackTrace();
                System.out.println("Произошла ошибка, ваш сеанс будет завершен");
                System.exit(0);
            }
        }
    }





