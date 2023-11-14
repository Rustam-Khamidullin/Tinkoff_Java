package edu.hw4;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static edu.hw4.Tasks.compareSexCounts;
import static edu.hw4.Tasks.countAnimalTypes;
import static edu.hw4.Tasks.countAnimalsWeightGreaterHeight;
import static edu.hw4.Tasks.findAnimalWithLongestName;
import static edu.hw4.Tasks.findAnimalsNameMoreTwo;
import static edu.hw4.Tasks.findAnimalsUnequalAgeAndPaws;
import static edu.hw4.Tasks.findAnimalsWithErrors;
import static edu.hw4.Tasks.findAnimalsWithReadableErrors;
import static edu.hw4.Tasks.findDangerousAnimals;
import static edu.hw4.Tasks.findHeaviestAnimalBelowK;
import static edu.hw4.Tasks.findHeaviestAnimals;
import static edu.hw4.Tasks.findHeaviestFish;
import static edu.hw4.Tasks.findKOldestAnimal;
import static edu.hw4.Tasks.hasDogHeightGreaterK;
import static edu.hw4.Tasks.sortAnimalsByTypeSexName;
import static edu.hw4.Tasks.sortByHeight;
import static edu.hw4.Tasks.sortByWeightKFirstDESC;
import static edu.hw4.Tasks.spidersBiteMoreThanDogs;
import static edu.hw4.Tasks.sumPaws;
import static edu.hw4.Tasks.sumWeightOfAnimalsByType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TasksTest {
    private static List<Animal> animals;
    private static List<Animal> empty;
    private static List<Animal> lonely;

    @BeforeAll
    public static void setup() {
        empty = new LinkedList<>();
        lonely = List.of(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, -5, 101, 10, true));
        animals = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 5, 25, 10, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 3, 30, 15, true),
            new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 2, 10, 2, false),
            new Animal("Goldfish", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 0, true)
        );
    }

    @Test
    public void testTask1() {
        assertTrue(sortByHeight(empty).isEmpty());

        assertIterableEquals(lonely, sortByHeight(lonely));

        List<Animal> sortedAnimals = sortByHeight(animals);

        assertEquals(1, sortedAnimals.get(0).height());
        assertEquals(5, sortedAnimals.get(1).height());
        assertEquals(10, sortedAnimals.get(2).height());
        assertEquals(25, sortedAnimals.get(3).height());
        assertEquals(30, sortedAnimals.get(4).height());
    }

    @Test
    public void testTask2() {
        int k = 3;

        assertTrue(sortByWeightKFirstDESC(empty, k).isEmpty());

        assertIterableEquals(lonely, sortByWeightKFirstDESC(lonely, k));

        List<Animal> sortedAnimals = sortByWeightKFirstDESC(animals, k);
        assertEquals(k, sortedAnimals.size());

        assertEquals(15, sortedAnimals.get(0).weight());
        assertEquals(10, sortedAnimals.get(1).weight());
        assertEquals(2, sortedAnimals.get(2).weight());
    }

    @Test
    public void testTask3() {
        assertTrue(empty.isEmpty());

        assertEquals(1, countAnimalTypes(lonely).get(Animal.Type.CAT));

        Map<Animal.Type, Long> animalTypeCount = countAnimalTypes(animals);

        assertEquals(1, animalTypeCount.get(Animal.Type.CAT));
        assertEquals(1, animalTypeCount.get(Animal.Type.DOG));
        assertEquals(1, animalTypeCount.get(Animal.Type.BIRD));
        assertEquals(1, animalTypeCount.get(Animal.Type.FISH));
        assertEquals(1, animalTypeCount.get(Animal.Type.SPIDER));
    }

    @Test
    public void testTask4() {
        assertNull(findAnimalWithLongestName(empty));

        assertEquals("Cat", findAnimalWithLongestName(lonely).name());

        Animal animalWithLongestName = findAnimalWithLongestName(animals);

        assertEquals("Goldfish", animalWithLongestName.name());
    }

    @Test
    public void testTask5() {
        assertNull(compareSexCounts(empty));

        assertEquals(Animal.Sex.M, compareSexCounts(lonely));

        Animal.Sex result = compareSexCounts(animals);

        assertEquals(Animal.Sex.M, result);
    }

    @Test
    public void testTask6() {
        assertTrue(findHeaviestAnimals(empty).isEmpty());

        assertEquals(10, findHeaviestAnimals(lonely).get(Animal.Type.CAT).weight());

        List<Animal> animals = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 5, 25, 10, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 3, 30, 15, true),
            new Animal("Dog2", Animal.Type.DOG, Animal.Sex.F, 3, 30, 33, true)
        );

        Map<Animal.Type, Animal> heaviestAnimals = findHeaviestAnimals(animals);

        assertEquals(2, heaviestAnimals.size());
        assertEquals(33, heaviestAnimals.get(Animal.Type.DOG).weight());
        assertEquals(10, heaviestAnimals.get(Animal.Type.CAT).weight());
    }

    @Test
    public void testTask7() {
        int k = 2;
        Animal oldestEmpty = findKOldestAnimal(empty, k);
        assertNull(oldestEmpty);

        Animal oldestLonely = findKOldestAnimal(lonely, k);
        assertNull(oldestLonely);

        Animal oldestAnimals = findKOldestAnimal(animals, k);
        assertEquals("Parrot", oldestAnimals.name());
    }

    @Test
    public void testTask8() {
        int k = 20;

        Optional<Animal> heaviestEmpty = findHeaviestAnimalBelowK(empty, k);
        assertFalse(heaviestEmpty.isPresent());

        Optional<Animal> heaviestLonely = findHeaviestAnimalBelowK(lonely, k);
        assertFalse(heaviestLonely.isPresent());

        Optional<Animal> heaviestAnimals = findHeaviestAnimalBelowK(animals, k);
        assertTrue(heaviestAnimals.isPresent());
        assertEquals("Parrot", heaviestAnimals.get().name());
    }

    @Test
    public void testTask9() {
        int sumEmpty = sumPaws(empty);
        assertEquals(0, sumEmpty);

        int sumLonely = sumPaws(lonely);
        assertEquals(4, sumLonely);

        int sumAnimals = sumPaws(animals);
        assertEquals(18, sumAnimals);
    }

    @Test
    public void testTask10() {
        List<Animal> resultEmpty = findAnimalsUnequalAgeAndPaws(empty);
        assertTrue(resultEmpty.isEmpty());

        List<Animal> resultLonely = findAnimalsUnequalAgeAndPaws(lonely);
        assertTrue(resultLonely.contains(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, -5, 101, 10, true)));

        List<Animal> resultAnimals = findAnimalsUnequalAgeAndPaws(animals);
        assertEquals(4, resultAnimals.size());
        assertTrue(resultAnimals.containsAll(List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 5, 25, 10, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 3, 30, 15, true),
            new Animal("Goldfish", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 0, true)
        )));
    }

    @Test
    public void testTask11() {
        List<Animal> resultEmpty = findDangerousAnimals(empty);
        assertTrue(resultEmpty.isEmpty());

        List<Animal> resultLonely = findDangerousAnimals(lonely);
        assertTrue(resultLonely.contains(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, -5, 101, 10, true)));

        List<Animal> resultAnimals = findDangerousAnimals(animals);
        assertTrue(resultAnimals.isEmpty());
    }

    @Test
    public void testTask12() {
        long countEmpty = countAnimalsWeightGreaterHeight(empty);
        assertEquals(0, countEmpty);

        List<Animal> lonely = List.of(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, -5, 1, 10, true));
        long countLonely = countAnimalsWeightGreaterHeight(lonely);
        assertEquals(1, countLonely);

        long countAnimals = countAnimalsWeightGreaterHeight(animals);
        assertEquals(0, countAnimals);
    }

    @Test
    public void testTask13() {
        List<Animal> resultEmpty = findAnimalsNameMoreTwo(empty);
        assertTrue(resultEmpty.isEmpty());

        List<Animal> resultLonely = findAnimalsNameMoreTwo(lonely);
        assertTrue(resultLonely.isEmpty());

        List<Animal> resultAnimals = findAnimalsNameMoreTwo(animals);
        assertEquals(0, resultAnimals.size());

        List<Animal> animals1 = List.of(
            new Animal("Black Panther", Animal.Type.CAT, Animal.Sex.F, 7, 100, 120, true),
            new Animal("Black Panther Another", Animal.Type.CAT, Animal.Sex.F, 7, 100, 120, true)
        );

        List<Animal> resultAnimals1 = findAnimalsNameMoreTwo(animals1);
        assertEquals(1, resultAnimals1.size());
        assertTrue(resultAnimals1.contains(new Animal(
            "Black Panther Another",
            Animal.Type.CAT,
            Animal.Sex.F,
            7,
            100,
            120,
            true
        )));
    }

    @Test
    public void tesTask14() {
        boolean resultEmpty = hasDogHeightGreaterK(empty, 50);
        assertFalse(resultEmpty);

        boolean resultLonely = hasDogHeightGreaterK(lonely, 20);
        assertFalse(resultLonely);

        boolean resultAnimals1 = hasDogHeightGreaterK(animals, 20);
        assertTrue(resultAnimals1);

        boolean resultAnimals2 = hasDogHeightGreaterK(animals, 100);
        assertFalse(resultAnimals2);
    }

    @Test
    public void testTask15() {
        Map<Animal.Type, Integer> resultEmpty = sumWeightOfAnimalsByType(empty, 0, 10);
        assertTrue(resultEmpty.isEmpty());

        Map<Animal.Type, Integer> resultLonely = sumWeightOfAnimalsByType(lonely, -5, 10);
        assertFalse(resultLonely.isEmpty());
        assertEquals(10, resultLonely.get(Animal.Type.CAT));

        Map<Animal.Type, Integer> resultAnimals = sumWeightOfAnimalsByType(animals, 2, 3);
        assertEquals(2, resultAnimals.size());
        assertEquals(2, resultAnimals.get(Animal.Type.BIRD));
        assertEquals(15, resultAnimals.get(Animal.Type.DOG));
    }

    @Test
    public void testTask16() {
        List<Animal> resultEmpty = sortAnimalsByTypeSexName(empty);
        assertTrue(resultEmpty.isEmpty());

        List<Animal> resultLonely = sortAnimalsByTypeSexName(lonely);
        assertEquals(1, resultLonely.size());

        List<Animal> resultSorted = sortAnimalsByTypeSexName(animals);
        assertIterableEquals(List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 5, 25, 10, false),
            new Animal("Goldfish", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 3, 30, 15, true),
            new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 2, 10, 2, false),
            new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 0, true)
        ), resultSorted);

        var animals2 = List.of(
            new Animal("Cat2", Animal.Type.CAT, Animal.Sex.F, 5, 25, 10, false),
            new Animal("Parrot1", Animal.Type.BIRD, Animal.Sex.M, 2, 10, 2, false),
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.M, 5, 25, 10, false),
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false),
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.F, 3, 30, 15, true),
            new Animal("Parrot2", Animal.Type.BIRD, Animal.Sex.M, 3, 12, 2, false),
            new Animal("Spider1", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 0, true),
            new Animal("Dog2", Animal.Type.DOG, Animal.Sex.M, 2, 35, 12, true),
            new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.F, 2, 2, 0, true),
            new Animal("Fish2", Animal.Type.FISH, Animal.Sex.F, 1, 7, 0, false)
        );

        List<Animal> resultSorted2 = sortAnimalsByTypeSexName(animals2);
        assertIterableEquals(List.of(
            new Animal("Cat1", Animal.Type.CAT, Animal.Sex.M, 5, 25, 10, false),
            new Animal("Cat2", Animal.Type.CAT, Animal.Sex.F, 5, 25, 10, false),
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false),
            new Animal("Fish2", Animal.Type.FISH, Animal.Sex.F, 1, 7, 0, false),
            new Animal("Dog2", Animal.Type.DOG, Animal.Sex.M, 2, 35, 12, true),
            new Animal("Dog1", Animal.Type.DOG, Animal.Sex.F, 3, 30, 15, true),
            new Animal("Parrot1", Animal.Type.BIRD, Animal.Sex.M, 2, 10, 2, false),
            new Animal("Parrot2", Animal.Type.BIRD, Animal.Sex.M, 3, 12, 2, false),
            new Animal("Spider1", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 0, true),
            new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.F, 2, 2, 0, true)
        ), resultSorted2);

    }

    @Test
    public void testTask17() {
        boolean resultEmpty = spidersBiteMoreThanDogs(empty);
        assertFalse(resultEmpty);

        boolean resultLonely = spidersBiteMoreThanDogs(lonely);
        assertFalse(resultLonely);

        boolean resultAnimals1 = spidersBiteMoreThanDogs(animals);
        assertFalse(resultAnimals1);

        var animals2 = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 5, 25, 10, false),
            new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 2, 10, 2, false),
            new Animal("Goldfish", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Black Panther", Animal.Type.CAT, Animal.Sex.F, 7, 100, 120, true),
            new Animal("Husky", Animal.Type.DOG, Animal.Sex.M, 6, 65, 25, true),
            new Animal("Tarantula", Animal.Type.SPIDER, Animal.Sex.M, 2, 1, 0, true),
            new Animal("Spiderwoman", Animal.Type.SPIDER, Animal.Sex.F, 1, 0, 0, true)
        );

        boolean resultAnimals2 = spidersBiteMoreThanDogs(animals2);
        assertTrue(resultAnimals2);
    }

    @Test
    public void testTask18() {
        Animal resultEmpty = findHeaviestFish(List.of(empty));
        assertNull(resultEmpty);

        Animal resultLonely = findHeaviestFish(List.of(lonely));
        assertNull(resultLonely);

        Animal resultFish = findHeaviestFish(List.of(animals));
        assertEquals("Goldfish", resultFish.name());

        var fish1 = List.of(
            new Animal("Goldfish1", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Goldfish2", Animal.Type.FISH, Animal.Sex.M, 1, 5, 0, false),
            new Animal("Goldfish3", Animal.Type.FISH, Animal.Sex.M, 2, 10, 1, false),
            new Animal("Goldfish4", Animal.Type.FISH, Animal.Sex.F, 2, 10, 1, false)
        );
        var fish2 = List.of(
            new Animal("Black Panther", Animal.Type.CAT, Animal.Sex.F, 7, 100, 120, true),
            new Animal("Husky", Animal.Type.DOG, Animal.Sex.M, 6, 65, 25, true),
            new Animal("Shark", Animal.Type.FISH, Animal.Sex.M, 10, 1000, 500, true)
        );

        Animal resultMixed = findHeaviestFish(List.of(fish1, fish2));
        assertEquals("Shark", resultMixed.name());
    }

    @Test
    public void testTask19() {
        Map<String, Set<ValidationError>> resultEmpty = findAnimalsWithErrors(empty);
        assertTrue(resultEmpty.isEmpty());

        Map<String, Set<ValidationError>> resultValidAnimals = findAnimalsWithErrors(animals);
        assertTrue(resultValidAnimals.isEmpty());

        var animalsWithErrors = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, -5, 25, 10, false),
            new Animal(null, Animal.Type.CAT, Animal.Sex.M, 5, 25, 10, false),
            new Animal("Dog", null, Animal.Sex.M, 5, 25, 10, false),
            new Animal("Parrot", Animal.Type.BIRD, null, 5, 25, 10, false),
            new Animal("Goldfish", Animal.Type.FISH, Animal.Sex.F, 0, -100, 10, false),
            new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 5, 25, -2340, false)
        );

        Map<String, Set<ValidationError>> resultAnimalsWithErrors = findAnimalsWithErrors(animalsWithErrors);
        assertEquals(animalsWithErrors.size(), resultAnimalsWithErrors.size());
    }

    @Test
    public void testTask20() {
        Map<String, String> resultEmpty = findAnimalsWithReadableErrors(empty);
        assertTrue(resultEmpty.isEmpty());

        Map<String, String> resultValidAnimals = findAnimalsWithReadableErrors(animals);
        assertTrue(resultValidAnimals.isEmpty());

        var animalsWithErrors = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, -5, 25, 10, false),
            new Animal(null, Animal.Type.CAT, Animal.Sex.M, 5, 25, 10, false),
            new Animal("Dog", null, Animal.Sex.M, 5, 25, 10, false),
            new Animal("Parrot", Animal.Type.BIRD, null, 5, 25, 10, false),
            new Animal("Goldfish", Animal.Type.FISH, Animal.Sex.F, 0, -100, 10, false),
            new Animal("Spider", Animal.Type.SPIDER, null, 5, 25, -2340, false)
        );

        Map<String, String> resultAnimalsWithErrors = findAnimalsWithReadableErrors(animalsWithErrors);
        assertEquals(animalsWithErrors.size(), resultAnimalsWithErrors.size());
        assertEquals("INVALID_AGE", resultAnimalsWithErrors.get("Cat"));
        assertEquals(27, resultAnimalsWithErrors.get("Spider").length());
    }
}
