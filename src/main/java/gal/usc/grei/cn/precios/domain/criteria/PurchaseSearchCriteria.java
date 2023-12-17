package gal.usc.grei.cn.precios.domain.criteria;

import java.util.Objects;

public class PurchaseSearchCriteria {
    private String date;
    private String symbol;
    private Long volumeGreater;
    private Long volumeLess;
    private Float unitGreater;
    private Float unitLess;
    private Float totalGreater;
    private Float totalLess;

    public PurchaseSearchCriteria() {
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

    public Long getVolumeGreater() {
        return volumeGreater;
    }

    public void setVolumeGreater(Long volumeGreater) {
        this.volumeGreater = volumeGreater;
    }

    public Long getVolumeLess() {
        return volumeLess;
    }

    public void setVolumeLess(Long volumeLess) {
        this.volumeLess = volumeLess;
    }

    public Float getUnitGreater() {
        return unitGreater;
    }

    public void setUnitGreater(Float unitGreater) {
        this.unitGreater = unitGreater;
    }

    public Float getUnitLess() {
        return unitLess;
    }

    public void setUnitLess(Float unitLess) {
        this.unitLess = unitLess;
    }

    public Float getTotalGreater() {
        return totalGreater;
    }

    public void setTotalGreater(Float totalGreater) {
        this.totalGreater = totalGreater;
    }

    public Float getTotalLess() {
        return totalLess;
    }

    public void setTotalLess(Float totalLess) {
        this.totalLess = totalLess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseSearchCriteria that = (PurchaseSearchCriteria) o;
        return Objects.equals(date, that.date) && Objects.equals(symbol, that.symbol) && Objects.equals(volumeGreater, that.volumeGreater) && Objects.equals(volumeLess, that.volumeLess) && Objects.equals(unitGreater, that.unitGreater) && Objects.equals(unitLess, that.unitLess) && Objects.equals(totalGreater, that.totalGreater) && Objects.equals(totalLess, that.totalLess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, symbol, volumeGreater, volumeLess, unitGreater, unitLess, totalGreater, totalLess);
    }

    @Override
    public String toString() {
        return "PurchaseSearchCriteria{" +
                "date='" + date + '\'' +
                ", symbol='" + symbol + '\'' +
                ", volumeGreater=" + volumeGreater +
                ", volumeLess=" + volumeLess +
                ", unitGreater=" + unitGreater +
                ", unitLess=" + unitLess +
                ", totalGreater=" + totalGreater +
                ", totalLess=" + totalLess +
                '}';
    }
}
