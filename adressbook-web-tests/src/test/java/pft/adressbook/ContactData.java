package pft.adressbook;

public class ContactData {
    private final String name;
    private final String family;
    private final String address;
    private final String telephone;
    private final String email;

    public ContactData(String name, String family, String address, String telephone, String email) {
        this.name = name;
        this.family = family;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
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
}
