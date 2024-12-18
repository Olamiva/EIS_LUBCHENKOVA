package com.labsfrom6to8.Mapper;

import com.labsfrom6to8.Model.Railway;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RailwayMapper {

    @Insert("INSERT INTO railways (name, length_km, country, is_operational) VALUES (#{name}, #{lengthKm}, #{country}, #{isOperational})")
    void insertRailway(Railway railway);

    @Update("UPDATE railways SET name = #{name}, length_km = #{lengthKm}, country = #{country}, is_operational = #{isOperational} WHERE id = #{id}")
    void updateRailway(Railway railway);

    @Select("SELECT * FROM railways")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "lengthKm", column = "length_km"),
            @Result(property = "country", column = "country"),
            @Result(property = "isOperational", column = "is_operational")
    })
    List<Railway> selectAllRailways();

    @Delete("DELETE FROM railways WHERE id = #{id}")
    void deleteRailway(Long id);
}
