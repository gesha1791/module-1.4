package chaplinskiy.crud.repository;

import chaplinskiy.crud.model.Region;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

public class RegionRepositoryImpl implements RegionRepository {
    private final Gson mapper = new Gson();


    @Override
    public Region getById(Long aLong) {
        return null;
    }


    @Override
    public void createRegion(String name) {
        try (FileWriter fileWriter = new FileWriter("region.json", true)) {
            Region region = new Region(name);
            String regionJsonGormat = mapper.toJson(region);

            fileWriter.write(regionJsonGormat);
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
