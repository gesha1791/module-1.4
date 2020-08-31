package chaplinskiy.crud.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputConsole {
    RegionView regionView = new RegionView();
    UserView userView = new UserView();
    PostView postView = new PostView();

    public void readMessage() throws IOException {
        String s = null;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
            System.out.println("Добрый день с какой из сущностей вы хотите работать??");
            do{
                System.out.println("1. Города");
                System.out.println("2. Пользователи");
                System.out.println("3. Сообщения");
                System.out.println("Введите нужную цифру");
                System.out.println("Для выхода введите stop");

                s = br.readLine();

                if (s.equals("1")){
                    System.out.println("Вы можете создать регион (1), обновить регион (2), " +
                            "вывести список всех регинов (3), удалить регион (4), получить регион по id (5)");
                    s = br.readLine();
                    regionView.readMessage(s, br);
                }
                if (s.equals("2")) {
                    System.out.println("Вы можете создать нового юзера (1), обновить юзера (2), " +
                            "вывести список всех юзеров (3), удалить юзера (4), получить юзера по id (5)");
                    s = br.readLine();
                    userView.readMessage(s, br);
                }

                if (s.equals("3")) {
                    System.out.println("Вы можете создать новый пост (1), обновить пост (2), " +
                            "вывести все посты (3), удалить пост (4), получить пост по id (5)");
                    s = br.readLine();
                    postView.readMessage(s, br);
                }


            } while (!"stop".equals(s));


        } catch (Exception e){
            System.out.println(e);
        }
    }
}
