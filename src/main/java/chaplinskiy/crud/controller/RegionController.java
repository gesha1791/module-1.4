package chaplinskiy.crud.controller;

import chaplinskiy.crud.model.Region;
import chaplinskiy.crud.repository.RegionRepository;
import chaplinskiy.crud.repository.RegionRepositoryImpl;

import java.util.List;

public class RegionController {
    private RegionRepository regionRepository = new RegionRepositoryImpl();;

    public RegionController() {
    }

    public Region getById(Long id){
        return regionRepository.getById(id);
    }

    public List<Region> getAll() {
        return regionRepository.getAll();
    }


    public Region create(Region region) {
        return regionRepository.create(region);
    }

    public void deleteById(Long id) {
        regionRepository.deleteById(id);
    }

    public Region update(Region region) {
        return regionRepository.update(region);
    }

    public Region checkRegion(String region) {
        return regionRepository.checkExistRegion(region);
    }
}
