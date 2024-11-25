public class Wine implements Comparable<Wine> {
    private String title, capacity, grape, closure, country, characteristics, type, ABV, region, style, vintage;
    private double price;

    public Wine() {
        title = "";
        price = 0.0;
        capacity = "";
        grape = "";
        closure = "";
        country = "";
        characteristics = "";
        type = "";
        ABV = "";
        region = "";
        style = "";
        vintage = "";
    }

    public Wine(String title, double price, String capacity, String grape, String closure, String country,
                String characteristics, String type, String ABV, String region, String style, String vintage) {
        this.title = title;
        this.price = price;
        this.capacity = capacity;
        this.grape = grape;
        this.closure = closure;
        this.country = country;
        this.characteristics = characteristics;
        this.type = type;
        this.ABV = ABV;
        this.region = region;
        this.style = style;
        this.vintage = vintage;
    }

    public String toString() {
        return String.format("Title=%s, Price=%.2f, Capacity=%s, Grape=%s, Closure=%s, Country=%s, Type=%s, ABV=%s, Region=%s, Vintage=%s",
                title, price, capacity, grape, closure, country, type, ABV, region, vintage);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Wine wine = (Wine) obj;
        return Double.compare(wine.price, price) == 0 && title.equals(wine.title);
    }

    @Override
    public int compareTo(Wine other) {
        int priceComparison = Double.compare(this.price, other.price);
        if (priceComparison != 0) return priceComparison;
        return this.title.compareTo(other.title);
    }

    // Getters for attributes
    public double getPrice() {
        return price;
    }

    public String getGrape() {
        return grape;
    }

    public String getABV() {
        return ABV;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getVintage() {
        return vintage;
    }



}
