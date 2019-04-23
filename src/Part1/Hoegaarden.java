package Part1;

import java.util.Objects;

public class Hoegaarden extends LightBeer{
    private String typeOfBottle;
    private String taste;

    public Hoegaarden() {
    }


    public Hoegaarden(String name, int alcoholPercentage, String country, String rawMaterials, String manufacturer, String typeOfBottle, String taste) {
        super(name, alcoholPercentage, country, rawMaterials, manufacturer);
        this.typeOfBottle = typeOfBottle;
        this.taste = taste;
    }

    public String getTypeOfBottle() {
        return typeOfBottle;
    }

    public void setTypeOfBottle(String typeOfBottle) {
        this.typeOfBottle = typeOfBottle;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hoegaarden that = (Hoegaarden) o;
        return typeOfBottle.equals(that.typeOfBottle) &&
                taste.equals(that.taste);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), typeOfBottle, taste);
    }

    @Override
    public String toString() {
        return "Hoegaarden{" +
                "typeOfBottle='" + typeOfBottle + '\'' +
                ", taste='" + taste + '\'' +
                '}';
    }
}
