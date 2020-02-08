package pft.adressbook.model;

import java.io.File;

public class ContactData {
        private int id = Integer.MAX_VALUE;
        private String name;
        private String family;
        private String address;
        private String telephone;
        private String mobile;
        private String work;
        private String email;

    public File getPhoto() {
        return photo;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    private String group;
        private String allPhones;
        private String email2;
        private String email3;
        private String allEmail;
        private File photo;

    public String getEmail2() {
        return email2;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withAllEmail(String allEmail) {
        this.allEmail = allEmail;
        return this;
    }

    public String getAllEmail() {
        return allEmail;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withFamily(String family) {
        this.family = family;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withMobile(String mobile) {

        this.mobile = mobile;
        return this;
    }

    public ContactData withWork(String work){
        this.work = work;
        return this;
    }

    public int getId() {
        return id;
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

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return family != null ? family.equals(that.family) : that.family == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (family != null ? family.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                '}';
    }



}

