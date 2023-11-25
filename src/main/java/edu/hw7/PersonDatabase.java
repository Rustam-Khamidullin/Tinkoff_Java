package edu.hw7;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PersonDatabase {
    private final Map<String, List<Person>> byName;
    private final Map<String, List<Person>> byAddress;
    private final Map<String, List<Person>> byPhone;
    private final Map<Integer, Person> byId;

    public PersonDatabase() {
        this.byName = new HashMap<>();
        this.byAddress = new HashMap<>();
        this.byPhone = new HashMap<>();
        this.byId = new HashMap<>();

    }

    void add(Person person) {
        synchronized (byId) {
            synchronized (byName) {
                synchronized (byAddress) {
                    synchronized (byPhone) {
                        byId.put(person.id(), person);
                        byName.computeIfAbsent(person.name(), (k) -> new LinkedList<>()).add(person);
                        byAddress.computeIfAbsent(person.address(), (k) -> new LinkedList<>()).add(person);
                        byPhone.computeIfAbsent(person.phoneNumber(), (k) -> new LinkedList<>()).add(person);
                    }
                }
            }
        }
    }

    void delete(int id) {
        synchronized (byId) {
            synchronized (byName) {
                synchronized (byAddress) {
                    synchronized (byPhone) {
                        var person = byId.get(id);
                        if (person == null) {
                            return;
                        }

                        byId.remove(id);
                        byName.remove(person.name());
                        byAddress.remove(person.address());
                        byPhone.remove(person.phoneNumber());
                    }
                }
            }
        }
    }

    List<Person> findByName(String name) {
        synchronized (byName) {
            return byName.get(name);
        }
    }

    List<Person> findByAddress(String address) {
        synchronized (byAddress) {
            return byAddress.get(address);
        }
    }

    List<Person> findByPhone(String phone) {

        synchronized (byPhone) {
            return byPhone.get(phone);

        }
    }
}
