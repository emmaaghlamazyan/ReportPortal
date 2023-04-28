package api;

public class Condition {

    private String condition;
    private String filteringField;
    private String value;

    public String getCondition() {
        return condition;
    }

    public String getFilteringField() {
        return filteringField;
    }

    public String getValue() {
        return value;
    }

    public static class ConditionBuilder {

        private String condition;
        private String filteringField;
        private String value;

        public ConditionBuilder setCondition(String condition) {
            this.condition = condition;
            return this;
        }

        public ConditionBuilder setFilteringField(String filteringField) {
            this.filteringField = filteringField;
            return this;
        }

        public ConditionBuilder setValue(String value) {
            this.value = value;
            return this;
        }

        public Condition build() {
            Condition conditions = new Condition();
            conditions.condition = this.condition;
            conditions.filteringField = this.filteringField;
            conditions.value = this.value;
            return conditions;
        }
    }
}