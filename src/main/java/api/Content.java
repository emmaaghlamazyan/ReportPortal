package api;

import java.util.ArrayList;

public class Content {
    private String description;
    private String owner;
    private boolean share;
    private int id;
    private String name;
    private ArrayList<Condition> conditions;
    private ArrayList<Order> orders;
    private String type;

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isShare() {
        return share;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public String getType() {
        return type;
    }

    public static class ContentBuilder {
        private String description;
        private String owner;
        private boolean share;
        private int id;
        private String name;
        private ArrayList<Condition> conditions;
        private ArrayList<Order> orders;
        private String type;

        public ContentBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ContentBuilder setOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public ContentBuilder setShare(boolean share) {
            this.share = share;
            return this;
        }

        public ContentBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ContentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ContentBuilder setConditions(ArrayList<Condition> conditions) {
            this.conditions = conditions;
            return this;
        }

        public ContentBuilder setOrders(ArrayList<Order> orders) {
            this.orders = orders;
            return this;
        }

        public ContentBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public Content build() {
            Content content = new Content();
            content.conditions = this.conditions;
            content.name = this.name;
            content.description = this.description;
            content.orders = this.orders;
            content.share = this.share;
            content.id = this.id;
            content.owner = this.owner;
            content.type = this.type;
            return content;
        }
    }
}