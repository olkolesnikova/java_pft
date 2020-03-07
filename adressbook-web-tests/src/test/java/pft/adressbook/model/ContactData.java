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

