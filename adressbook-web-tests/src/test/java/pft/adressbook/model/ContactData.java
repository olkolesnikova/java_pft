package pft.adressbook.model;

public class ContactData {
        private int id;
        private final String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return family != null ? family.equals(that.family) : that.family == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (family != null ? family.hashCode() : 0);
        return result;
    }

    private final String family;
        private final String address;
        private final String telephone;

    public void setId(int id) {
        this.id = id;
    }

    private final String email;
        private String group;

    public int getId() {
        return id;
    }

    public ContactData(String family, String name, String address, String telephone, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.family = family;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.group = group;


    }

        public ContactData(int id, String family, String name, String address, String telephone, String email, String group) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.group = group;


    }

        public String getName() {
            return name;
        }

        public String getFamily() {
            return family;
        }

    public String getAddress() {
            return address;
        }

        public String getTelephone() {
            return telephone;
        }

        public String getEmail() {
            return email;
        }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                '}';
    }

}

