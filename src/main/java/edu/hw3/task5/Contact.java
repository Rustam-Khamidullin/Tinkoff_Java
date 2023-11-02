package edu.hw3.task5;

public class Contact {
    private final String firstName;
    private final String lastName;

    public Contact(String stringContact) {
        String[] parts = stringContact.split(" ");
        if (parts.length == 2) {
            firstName = parts[0];
            lastName = parts[1];
        } else if (parts.length == 1) {
            firstName = stringContact;
            lastName = "";
        } else {
            throw new RuntimeException("Incorrect contact");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Contact contact = (Contact) obj;
        return (this.firstName.equals(contact.firstName)) && (this.lastName.equals(contact.lastName));
    }

    @Override
    public int hashCode() {
        return firstName.hashCode() + lastName.hashCode();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
