package chaplinskiy.jdbccrud.controller;

import chaplinskiy.jdbccrud.model.Region;
import chaplinskiy.jdbccrud.service.RegionService;

import java.util.List;

public class RegionController {
    private final RegionService regionService;

    public RegionController(){
        regionService = new RegionService();
    }


    public List<Region> getAllRegions() {
        return regionService.getAll();
    }

    public void createRegion(String name) {
        regionService.createRegion(name);
    }

    public void deleteRegionById(Long id) {
        regionService.deleteRegionById(id);
    }

    public Region getRegionById(Long id) {
        return regionService.getRegionById(id);
    }

    public void updateRegion(Region nameUpdateRegion) {
        regionService.updateRegion(nameUpdateRegion);
    }
}
