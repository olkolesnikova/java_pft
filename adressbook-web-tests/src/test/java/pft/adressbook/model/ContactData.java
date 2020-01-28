package pft.adressbook.model;

public class ContactData {
        private final String id;
        private final String name;
        private final String family;
        private final String address;
        private final String telephone;
        private final String email;
        private String group;

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public ContactData(String name, String family, String address, String telephone, String email, String group) {
        this.id = null;
        this.name = name;
        this.family = family;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.group = group;


    }

    public ContactData(String id, String name, String family, String address, String telephone, String email, String group) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
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



}

