package edu.hw7;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PersonDatabaseReadWriteLock {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<String, List<Person>> byName;
    private final Map<String, List<Person>> byAddress;
    private final Map<String, List<Person>> byPhone;
    private final Map<Integer, Person> byId;

    public PersonDatabaseReadWriteLock() {
        this.byName = new HashMap<>();
        this.byAddress = new HashMap<>();
        this.byPhone = new HashMap<>();
        this.byId = new HashMap<>();

    }

    void add(Person person) {
        lock.writeLock().lock();
        try {
            byId.put(person.id(), person);
            byName.computeIfAbsent(person.name(), (k) -> new LinkedList<>()).add(person);
            byAddress.computeIfAbsent(person.address(), (k) -> new LinkedList<>()).add(person);
            byPhone.computeIfAbsent(person.phoneNumber(), (k) -> new LinkedList<>()).add(person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    void delete(int id) {
        lock.writeLock().lock();
        try {
            var person = byId.get(id);
            if (person == null) {
                return;
            }

            byId.remove(id);
            byName.remove(person.name());
            byAddress.remove(person.address());
            byPhone.remove(person.phoneNumber());
        } finally {
            lock.writeLock().unlock();
        }
    }

    List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return byName.get(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return byName.get(address);
        } finally {
            lock.readLock().unlock();
        }
    }

    List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return byName.get(phone);
        } finally {
            lock.readLock().unlock();
        }
    }
}
