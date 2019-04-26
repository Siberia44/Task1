package part1;

import java.util.Objects;

public class DarkBeer extends Beer{

    private int percentageRoastedHop;
    private String type;

    public DarkBeer() {
    }

    public DarkBeer(String name, int alcoholPercentage, String country, int percentageRoastedHop, String type) {
        super(name, alcoholPercentage, country);
        this.percentageRoastedHop = percentageRoastedHop;
        this.type = type;
    }

    public int getPercentageRoastedHop() {
        return percentageRoastedHop;
    }

    public void setPercentageRoastedHop(int percentageRoastedHop) {
        this.percentageRoastedHop = percentageRoastedHop;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DarkBeer darkBeer = (DarkBeer) o;
        return percentageRoastedHop == darkBeer.percentageRoastedHop &&
                type.equals(darkBeer.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), percentageRoastedHop, type);
    }

    @Override
    public String toString() {
        return "DarkBeer{" +
                "percentageRoastedHop=" + percentageRoastedHop +
                ", type='" + type + '\'' +
                '}';
    }
}
