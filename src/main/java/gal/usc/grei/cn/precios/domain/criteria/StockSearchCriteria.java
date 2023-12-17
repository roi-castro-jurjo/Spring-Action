package gal.usc.grei.cn.precios.domain.criteria;

import java.util.Objects;

public class StockSearchCriteria {
    private Double highGreater;
    private Double highLess;
    private Double lowGreater;
    private Double lowLess;
    private Long volumeGreater;
    private Long volumeLess;
    private Double closeGreater;
    private Double closeLess;
    private Double openGreater;
    private Double openLess;
    private String date; // Formato: "YYYY-MM-DD"
    private String symbol;

    public Double getHighGreater() {
        return highGreater;
    }

    public void setHighGreater(Double highGreater) {
        this.highGreater = highGreater;
    }

    public Double getHighLess() {
        return highLess;
    }

    public void setHighLess(Double highLess) {
        this.highLess = highLess;
    }

    public Double getLowGreater() {
        return lowGreater;
    }

    public void setLowGreater(Double lowGreater) {
        this.lowGreater = lowGreater;
    }

    public Double getLowLess() {
        return lowLess;
    }

    public void setLowLess(Double lowLess) {
        this.lowLess = lowLess;
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

    public Double getCloseGreater() {
        return closeGreater;
    }

    public void setCloseGreater(Double closeGreater) {
        this.closeGreater = closeGreater;
    }

    public Double getCloseLess() {
        return closeLess;
    }

    public void setCloseLess(Double closeLess) {
        this.closeLess = closeLess;
    }

    public Double getOpenGreater() {
        return openGreater;
    }

    public void setOpenGreater(Double openGreater) {
        this.openGreater = openGreater;
    }

    public Double getOpenLess() {
        return openLess;
    }

    public void setOpenLess(Double openLess) {
        this.openLess = openLess;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockSearchCriteria that = (StockSearchCriteria) o;
        return Objects.equals(highGreater, that.highGreater) && Objects.equals(highLess, that.highLess) && Objects.equals(lowGreater, that.lowGreater) && Objects.equals(lowLess, that.lowLess) && Objects.equals(volumeGreater, that.volumeGreater) && Objects.equals(volumeLess, that.volumeLess) && Objects.equals(closeGreater, that.closeGreater) && Objects.equals(closeLess, that.closeLess) && Objects.equals(openGreater, that.openGreater) && Objects.equals(openLess, that.openLess) && Objects.equals(date, that.date) && Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(highGreater, highLess, lowGreater, lowLess, volumeGreater, volumeLess, closeGreater, closeLess, openGreater, openLess, date, symbol);
    }

    @Override
    public String toString() {
        return "StockSearchCriteria{" +
                "highGreater=" + highGreater +
                ", highLess=" + highLess +
                ", lowGreater=" + lowGreater +
                ", lowLess=" + lowLess +
                ", volumeGreater=" + volumeGreater +
                ", volumeLess=" + volumeLess +
                ", closeGreater=" + closeGreater +
                ", closeLess=" + closeLess +
                ", openGreater=" + openGreater +
                ", openLess=" + openLess +
                ", date='" + date + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}