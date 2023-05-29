package api;

import java.util.ArrayList;

public class Root {
    private ArrayList<Condition> conditions;
    private String description;
    private String name;
    private ArrayList<Order> orders;
    private boolean share;
    private String type;
    private ArrayList<Content> content;
    private Page page;

    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    public String getDescription() {
        return description;
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

    public ArrayList<Content> getContent() {
        return content;
    }

    public Page getPage() {
        return page;
    }

    public static class RootBuilder {

        private ArrayList<Condition> conditions;
        private String description;
        private String name;
        private ArrayList<Order> orders;
        private boolean share;
        private String type;

        public RootBuilder setConditions(ArrayList<Condition> conditions) {
            this.conditions = conditions;
            return this;
        }

        public RootBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public RootBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public RootBuilder setOrders(ArrayList<Order> orders) {
            this.orders = orders;
            return this;
        }

        public RootBuilder setShare(boolean share) {
            this.share = share;
            return this;
        }

        public RootBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public Root build() {
            Root root = new Root();
            root.conditions = this.conditions;
            root.name = this.name;
            root.description = this.description;
            root.orders = this.orders;
            root.share = this.share;
            root.type = this.type;
            return root;
        }
    }
}