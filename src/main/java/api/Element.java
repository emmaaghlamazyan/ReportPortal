package api;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Element{
    private ArrayList<Condition> conditions;
    private String description;
    private String id;
    private String name;
    private ArrayList<Order> orders;
    private boolean share;
    private String type;

    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public boolean isShare() {
        return share;
    }

    public String getType() {
        return type;
    }

    public static class ElementBuilder {
        private ArrayList<Condition> conditions;
        private String description;
        private String id;
        private String name;
        private ArrayList<Order> orders;
        private boolean share;
        private String type;

        public ElementBuilder setConditions(ArrayList<Condition> conditions) {
            this.conditions = conditions;
            return this;
        }

        public ElementBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ElementBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public ElementBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ElementBuilder setOrders(ArrayList<Order> orders) {
            this.orders = orders;
            return this;
        }

        public ElementBuilder setShare(boolean share) {
            this.share = share;
            return this;
        }

        public ElementBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public Element build() {
            Element element = new Element();
            element.conditions = this.conditions;
            element.name = this.name;
            element.id = this.id;
            element.description = this.description;
            element.orders = this.orders;
            element.share = this.share;
            element.type = this.type;
            return element;
        }
    }
}