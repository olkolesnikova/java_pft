package pft.adressbook.model;

public class ContactData {
        private int id;
        private final String name;
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

    public ContactData(String name, String family, String address, String telephone, String email, String group) {
        this.id = 0;
        this.name = name;
        this.family = family;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.group = group;


    }

        public ContactData(int id, String name, String family, String address, String telephone, String email, String group) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}

