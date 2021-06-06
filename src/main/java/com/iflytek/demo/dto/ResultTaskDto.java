package com.iflytek.demo.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ Author     ：yylan
 * @ Date       ：Created in 17:57 2019/4/9
 */

@NoArgsConstructor
public class ResultTaskDto {
    private String name;
    private String key;
    private CarportResult result;
    private Polygon polygon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public CarportResult getResult() {
        return result;
    }

    public void setResult(CarportResult result) {
        this.result = result;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public class CarportResult{
        private boolean carport;

        public boolean isCarport() {
            return carport;
        }

        public void setCarport(boolean carport) {
            this.carport = carport;
        }
    }
    @Getter
    @Setter
    public class Polygon{
        private int y1;
        private int x1;
        private int y2;
        private int x2;
        private int y3;
        private int x3;
        private int x4;
        private int y4;

    }
}
