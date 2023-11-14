package edu.hw4;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Tasks {
    private Tasks() {
    }

    public static List<Animal> sortByHeight(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> sortByWeightKFirstDESC(List<Animal> animals, int k) {
        return animals.stream()
            .sorted((animal1, o2) -> Integer.compare(o2.weight(), animal1.weight()))
            .limit(k)
            .toList();
    }

    public static Map<Animal.Type, Long> countAnimalTypes(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public static Animal findAnimalWithLongestName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }

    public static Animal.Sex compareSexCounts(List<Animal> animals) {
        int sum = animals.stream()
            .map(animal -> {
                if (animal.sex() == Animal.Sex.M) {
                    return 1;
                } else {
                    return -1;
                }
            })
            .mapToInt(Integer::intValue)
            .sum();
        if (sum > 0) {
            return Animal.Sex.M;
        } else if (sum < 0) {
            return Animal.Sex.F;
        } else {
            return null;
        }
    }

    public static Map<Animal.Type, Animal> findHeaviestAnimals(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.maxBy(Comparator.comparing(Animal::weight))))
            .entrySet().stream()
            .filter(entry -> entry.getValue().isPresent())
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get()));
    }

    public static Animal findKOldestAnimal(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(((o1, o2) -> Integer.compare(o2.age(), o1.age())))
            .skip(k)
            .findFirst()
            .orElse(null);
    }

    public static Optional<Animal> findHeaviestAnimalBelowK(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer sumPaws(List<Animal> animals) {
        return animals.stream()
            .reduce(0, (acc, animal) -> acc + animal.paws(), Integer::sum);
    }

    public static List<Animal> findAnimalsUnequalAgeAndPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    @SuppressWarnings("MagicNumber")
    public static List<Animal> findDangerousAnimals(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.height() > 100 && animal.bites())
            .toList();
    }

    public static long countAnimalsWeightGreaterHeight(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    public static List<Animal> findAnimalsNameMoreTwo(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .toList();
    }

    public static boolean hasDogHeightGreaterK(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public static Map<Animal.Type, Integer> sumWeightOfAnimalsByType(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> sortAnimalsByTypeSexName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator
                .comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    public static boolean spidersBiteMoreThanDogs(List<Animal> animals) {
        return animals.stream()
            .mapToInt(animal -> {
                if (animal.type() == Animal.Type.SPIDER && animal.bites()) {
                    return 1;
                } else if (animal.type() == Animal.Type.DOG && animal.bites()) {
                    return -1;
                } else {
                    return 0;
                }
            })
            .sum() > 0;
    }

    public static Animal findHeaviestFish(List<List<Animal>> animalsLists) {
        return animalsLists.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight))
            .orElse(null);
    }

    public static Map<String, Set<ValidationError>> findAnimalsWithErrors(List<Animal> animals) {
        return animals.stream()
            .map(animal -> new AbstractMap.SimpleEntry<>(animal.name(), getValidationError(animal)))
            .filter(entry -> entry.getValue() != null)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static Set<ValidationError> getValidationError(Animal animal) {
        var result = new HashSet<ValidationError>();

        if (animal.name() == null) {
            result.add(ValidationError.INVALID_NAME);
        }
        if (animal.type() == null) {
            result.add(ValidationError.INVALID_TYPE);
        }
        if (animal.sex() == null) {
            result.add(ValidationError.INVALID_SEX);
        }
        if (animal.age() < 0) {
            result.add(ValidationError.INVALID_AGE);
        }
        if (animal.weight() < 0) {
            result.add(ValidationError.INVALID_WEIGHT);
        }
        if (animal.height() < 0) {
            result.add(ValidationError.INVALID_HEIGHT);
        }

        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    public static Map<String, String> findAnimalsWithReadableErrors(List<Animal> animals) {
        return animals.stream()
            .map(animal -> new AbstractMap.SimpleEntry<>(animal.name(), getReadableValidationError(animal)))
            .filter(entry -> entry.getValue() != null)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static String getReadableValidationError(Animal animal) {
        var errors = getValidationError(animal);
        if (errors == null) {
            return null;
        }

        return errors.stream()
            .map(ValidationError::name)
            .collect(Collectors.joining(", "));
    }
}
