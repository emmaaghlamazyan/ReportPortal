package api;

public class Order {
    private boolean isAsc;
    private String sortingColumn;

    public boolean isAsc() {
        return isAsc;
    }

    public String getSortingColumn() {
        return sortingColumn;
    }

    public static class OrderBuilder {

        private boolean isAsc;
        private String sortingColumn;

        public OrderBuilder setAsc(boolean asc) {
            isAsc = asc;
            return this;
        }

        public OrderBuilder setSortingColumn(String sortingColumn) {
            this.sortingColumn = sortingColumn;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.isAsc = this.isAsc;
            order.sortingColumn = this.sortingColumn;
            return order;
        }
    }
}