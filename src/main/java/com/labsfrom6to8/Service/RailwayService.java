package com.labsfrom6to8.Service;

import com.labsfrom6to8.Mapper.RailwayMapper;
import com.labsfrom6to8.Model.Railway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RailwayService {

    private final RailwayMapper railwayMapper;

    public RailwayService(RailwayMapper railwayMapper) {
        this.railwayMapper = railwayMapper;
    }

    public void addRailway(Railway railway) {
        railwayMapper.insertRailway(railway);
    }

    public void updateRailway(Railway railway) {
        railwayMapper.updateRailway(railway);
    }

    public List<Railway> getAllRailways() {
        return railwayMapper.selectAllRailways();
    }

    public void deleteRailwayById(Long id) {
        railwayMapper.deleteRailway(id);
    }
}
