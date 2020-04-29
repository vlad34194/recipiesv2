package ro.siit.recipiesv2.model;

public enum Category {
    SOUP(0), SALAD(1), MAINDISH(2), DESSERT(3), MISCELLANEOUS(4);

    private int category;

    private Category(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }
}
