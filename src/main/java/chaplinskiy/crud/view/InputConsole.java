package chaplinskiy.crud.view;

import chaplinskiy.crud.controller.RegionController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputConsole {
    public void readMessage() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        RegionController regionController = new RegionController();
        String str;

        do {
            str = br.readLine();

            if (str.equals("Да")){
                System.out.println("Введите название города");
                String city = br.readLine();
                regionController.createRegion(city);


            }



        } while (!str.equals("стоп"));

    }
}
