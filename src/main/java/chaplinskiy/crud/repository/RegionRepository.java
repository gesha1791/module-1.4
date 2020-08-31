package chaplinskiy.crud.repository;

import chaplinskiy.crud.model.Region;

public interface RegionRepository extends GenericRepository<Region, Long> {
    Region checkExistRegion(String region);
}
