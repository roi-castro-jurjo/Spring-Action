package gal.usc.grei.cn.precios.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("compras")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class Purchase {
    @Id
    private String id;
    private String date;
    @NotEmpty(message = "The symbol cannot be empty")
    private String symbol;
    @NotNull(message = "The volume cannot be empty")
    private Long volume;
    @NotNull(message = "The price per unit cannot be empty")
    private Float unit;
    @NotNull(message = "The total price cannot be empty")
    private Float total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Float getUnit() {
        return unit;
    }

    public void setUnit(Float unit) {
        this.unit = unit;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(id, purchase.id) && Objects.equals(date, purchase.date) && Objects.equals(symbol, purchase.symbol) && Objects.equals(volume, purchase.volume) && Objects.equals(unit, purchase.unit) && Objects.equals(total, purchase.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, symbol, volume, unit, total);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", symbol='" + symbol + '\'' +
                ", volume=" + volume +
                ", unit=" + unit +
                ", total=" + total +
                '}';
    }
}
