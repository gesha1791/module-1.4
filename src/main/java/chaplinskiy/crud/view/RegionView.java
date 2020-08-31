package chaplinskiy.crud.view;

import chaplinskiy.crud.controller.RegionController;
import chaplinskiy.crud.model.Region;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class RegionView {
    RegionController regionController = new RegionController();
    BufferedReader bufferedReader;
    public void readMessage(String s, BufferedReader br) throws IOException {
        bufferedReader = br;
            if (s.equals("1")) {
                System.out.println("Введите имя региона");
                s = br.readLine();
                Region newRegion = new Region();
                newRegion.setName(s);
                Region region = regionController.create(newRegion);

                System.out.println("Регион создан");
                System.out.println(region.toString());

            } else if (s.equals("2")) {
                System.out.println("Введите id региона");
                s = br.readLine();
                Region regionById = regionController.getById(Long.valueOf(s));

                System.out.println("Введите имя региона");
                s = br.readLine();
                regionById.setName(s);
                Region regionUpdate = regionController.update(regionById);
                System.out.println("Регион обновлен");
                System.out.println(regionUpdate.toString());
            }  else if (s.equals("3")) {
                List<Region> all = regionController.getAll();

                System.out.println("Все регионы");

                if (all.size() == 0){
                    System.out.println("Нет созданных регионов\n");
                }

                all.forEach(System.out::println);
            } else if (s.equals("4")) {
                System.out.println("Введите id региона");
                s = br.readLine();
                regionController.deleteById(Long.valueOf(s));
            } else if (s.equals("5")) {
                System.out.println("Введите id региона");
                s = br.readLine();

                Region byId = regionController.getById(Long.valueOf(s));
                System.out.println(byId.toString());
            }
        }
}
