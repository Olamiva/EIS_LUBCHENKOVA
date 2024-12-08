package com.labsfrom6to8.Controller;

import com.labsfrom6to8.Model.Railway;
import com.labsfrom6to8.Service.RailwayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/railways")
@Tag(name = "Railway API", description = "API для управления железнодорожными путями")
public class RailwayController {

    private final RailwayService railwayService;

    public RailwayController(RailwayService railwayService) {
        this.railwayService = railwayService;
    }

    @PostMapping
    @Operation(summary = "Добавить новый железнодорожный путь", description = "Добавляет новый железнодорожный путь в систему")
    public void addRailway(@RequestBody Railway railway) {
        railwayService.addRailway(railway);
    }

    @PutMapping
    @Operation(summary = "Обновить железнодорожный путь", description = "Обновляет информацию о железнодорожном пути")
    public void updateRailway(@RequestBody Railway railway) {
        railwayService.updateRailway(railway);
    }

    @GetMapping
    @Operation(summary = "Получить список всех железнодорожных путей", description = "Возвращает список всех железнодорожных путей в системе")
    public List<Railway> getAllRailways() {
        return railwayService.getAllRailways();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить железнодорожный путь", description = "Удаляет железнодорожный путь по его идентификатору")
    public void deleteRailway(@PathVariable Long id) {
        railwayService.deleteRailwayById(id);
    }
}
