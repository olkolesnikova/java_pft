package pft.adressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")

public class ContactData {
        @XStreamOmitField
        @Id
        @Column(name = "id")
        private int id = Integer.MAX_VALUE;

        @Expose
        @Column(name = "firstname")
        private String name;

        @Expose
        @Column(name = "lastname")
        private String family;

        @Expose
        @Column(name = "address")
        @Type(type = "text")
        private String address;

        @Expose
        @Column(name = "home")
        @Type(type = "text")
        private String telephone;

        @Expose
        @Column(name = "mobile")
        @Type(type = "text")
        private String mobile;

        @Expose
        @Column(name = "work")
        @Type(type = "text")
        private String work;

        @Expose
        @Column(name = "email")
        @Type(type = "text")
        private String email;

        @Transient
        private String inGroup;

    public File getPhoto() {
        return new File(photo);
    }

    public String getInGroup() {
        return inGroup;
    }

    public int getNextId() {
        return id = id + 1;

    }

    public ContactData withInGroup(String inGroup) {
        this.inGroup = inGroup;
        return this;
    }

    public ContactData withInGroup(GroupData group) {
    groups.add(group);
    return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

        @Transient
        private String allPhones;

        @Transient
        private String email2;

        @Transient
        private String email3;

        @Transient
        private String allEmail;

        @Column(name = "photo")
        @Type(type = "text")
        private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public Groups getGroups() {
        return new Groups(groups);
    }

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


    public String getMobile() {
        return mobile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (family != null ? !family.equals(that.family) : that.family != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (work != null ? !work.equals(that.work) : that.work != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
        return email3 != null ? email3.equals(that.email3) : that.email3 == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (family != null ? family.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (work != null ? work.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (email3 != null ? email3.hashCode() : 0);
        return result;
    }

    public String getWork() {
        return work;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                '}';
    }



}

