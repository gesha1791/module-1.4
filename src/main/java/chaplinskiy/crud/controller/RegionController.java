package chaplinskiy.crud.controller;

import chaplinskiy.crud.repository.RegionRepositoryImpl;

public class RegionController {
    private final RegionRepositoryImpl regionRepository = new RegionRepositoryImpl();

    public void createRegion(String name){
        regionRepository.createRegion(name);
    }

}
