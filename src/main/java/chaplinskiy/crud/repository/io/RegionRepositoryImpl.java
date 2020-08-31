package chaplinskiy.crud.repository.io;

import chaplinskiy.crud.model.Region;
import chaplinskiy.crud.repository.RegionRepository;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.nio.file.Files.*;

public class RegionRepositoryImpl implements RegionRepository {

    private final Gson mapper = new Gson();

    @Override
    public Region create(Region region) {
        Long id = generateId();
        region.setId(id);

        try (FileWriter fileWriter = new FileWriter("region.json", true)) {
            String regionJsonGormat = mapper.toJson(region);

            fileWriter.write(regionJsonGormat);
            fileWriter.write("\n");
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return region;
    }

    private Long generateId() {
        boolean exists = exists(Paths.get("region.json"));
        Long id = null;
        List<Region> regions = new ArrayList<>();
        if (exists){
            try {
                List<String> strings = readAllLines(Paths.get("region.json"));

                strings.stream().forEach(s -> {
                    regions.add(mapper.fromJson(s, Region.class));
                });
                if (regions.size() == 0) {
                    id = 1L;
                } else {
                    Long idMax = regions.stream().max(Comparator.comparing(Region::getId)).orElseThrow(NoSuchElementException::new).getId();
                    id = idMax + 1;
                }

            }catch (IOException e){
                System.out.println(e);
            }
        } else {
            id = 1L;
        }
        return id;
    }

    @Override
    public Region update(Region region) {
        Long id = region.getId();
        List<Region> regions = new ArrayList<>();
        try {
            List<String> strings = readAllLines(Paths.get("region.json"));
            strings.stream().forEach(s -> {
                regions.add(mapper.fromJson(s, Region.class));
            });

        }catch (IOException e){
            System.out.println(e);
        }

        Region regionDelete = new Region();

        for(Region r : regions){
            if (r.getId().longValue() == id.longValue()){
                regionDelete = r;
            }
        }

        regions.remove(regionDelete);

        regions.add(region);

        List<Region> regionsSort =
                regions.stream().sorted(Comparator.comparing(Region::getId)).collect(Collectors.toList());


        try (FileWriter fileWriter = new FileWriter("region.json")) {
            regionsSort.forEach(r ->{
                String regionJsonGormat = mapper.toJson(r);
                try {
                    fileWriter.write(regionJsonGormat);
                    fileWriter.write("\n");
                } catch (IOException e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        return region;
    }

    @Override
    public Region getById(Long aLong) {
        List<Region> regions = new ArrayList<>();
        try {
            List<String> strings = readAllLines(Paths.get("region.json"));
            strings.stream().forEach(s -> {
                regions.add(mapper.fromJson(s, Region.class));
            });

        }catch (IOException e){
            System.out.println(e);
        }

        Region region = new Region();
        for(Region r : regions){
            if (r.getId().longValue() == aLong.longValue()){
                region = r;
            }
        }


        return region;
    }

    @Override
    public List<Region> getAll() {
        List<Region> regions = new ArrayList<>();
        try {
            List<String> strings = readAllLines(Paths.get("region.json"));
            strings.stream().forEach(s -> {
                regions.add(mapper.fromJson(s, Region.class));
            });

        }catch (IOException e){
            System.out.println(e);
        }
        return regions;
    }

    @Override
    public void deleteById(Long aLong) {
        List<Region> regions = new ArrayList<>();
        try {
            List<String> strings = readAllLines(Paths.get("region.json"));
            strings.stream().forEach(s -> {
                regions.add(mapper.fromJson(s, Region.class));
            });

        }catch (IOException e){
            System.out.println(e);
        }

        Region regionRemove = new Region();

        for(Region r : regions){
            if (r.getId().longValue() == aLong.longValue()){
             regionRemove = r;
            }
        }
        regions.remove(regionRemove);

        try (FileWriter fileWriter = new FileWriter("region.json")) {
            regions.forEach(r ->{
                String regionJsonGormat = mapper.toJson(r);
                try {
                    fileWriter.write(regionJsonGormat);
                    fileWriter.write("\n");
                } catch (IOException e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Region checkExistRegion(String region) {
        List<Region> all = getAll();
        Region regionByName = new Region();
        for(Region r : all){
            if (r.getName().equals(region)){
                regionByName = r;
            }
        }

        if (null == regionByName.getId()){
            Region regionCreate = new Region();
            regionCreate.setName(region);
            regionByName = create(regionCreate);
        }
        return regionByName;
    }
}
