package Lab4_Validation.Model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CodeValidatedRailway {

    @NotNull(message = "Название железной дороги не должно быть пустым")
    @Pattern(regexp = "^[А-Яа-яA-Za-z\\s]+$", message = "Название может содержать только буквы и пробелы")
    private String name;

    @NotNull(message = "Страна не должна быть пустой")
    @Pattern(regexp = "^[А-Яа-яA-Za-z\\s]+$", message = "Страна может содержать только буквы и пробелы")
    private String country;

    @Min(value = 1, message = "Количество станций должно быть больше 0")
    @Max(value = 1000, message = "Количество станций не может превышать 1000")
    private int stationCount;

    @Min(value = 10, message = "Протяженность должна быть больше 10 км")
    @Max(value = 10000, message = "Протяженность не может превышать 10000 км")
    private int lengthKm;

    @NotNull(message = "Тип пути не должен быть пустым")
    @Pattern(regexp = "^(электрифицированный|неэлектрифицированный)$", message = "Тип пути должен быть 'электрифицированный' или 'неэлектрифицированный'")
    private String trackType;
}
