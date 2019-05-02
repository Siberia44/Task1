package Task1.part1;

import java.util.Objects;

public class LightBeer extends Beer {
    private String rawMaterials;
    private String manufacturer;

    public LightBeer() {
    }

    public LightBeer(String name, int alcoholPercentage, String country, String rawMaterials, String manufacturer) {
        super(name, alcoholPercentage, country);
        this.rawMaterials = rawMaterials;
        this.manufacturer = manufacturer;
    }

    public String getRawMaterials() {
        return rawMaterials;
    }

    public void setRawMaterials(String rawMaterials) {
        this.rawMaterials = rawMaterials;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LightBeer lightBeer = (LightBeer) o;
        return rawMaterials.equals(lightBeer.rawMaterials) &&
                manufacturer.equals(lightBeer.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rawMaterials, manufacturer);
    }

    @Override
    public String toString() {
        return "LightBeer{" +
                "rawMaterials='" + rawMaterials + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
