package com.std.sbb;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
class PersonController {
    int lastId;
    List<Person> people;

    PersonController() {
        lastId = 0;
        people = new ArrayList<>();
    }

    @GetMapping("/person/add")
    @ResponseBody
    public String addPerson(@RequestParam("name") String name,
                            @RequestParam("age") int age) {
        lastId++;

        Person person = new Person(lastId, age, name);
        people.add(person);

        return String.format("%d번째 사람이 추가되었습니다.", person.getId());
    }

    @GetMapping("/person/people")
    @ResponseBody
    public List<Person> getPeople() {
        System.out.println(people);
        return people;
    }

    @GetMapping("/person/remove")
    @ResponseBody
    public String removePerson(@RequestParam("id") int id) {
        boolean removed = false;

        for (Person person : people) {
            if (person.getId() == id) {
                people.remove(person);
                removed = true;
            }
        }

        if (removed == false) {
            return id + "번 사람이 존재하지 않습니다.";
        } else return id + "번 사람이 삭제되었습니다.";
    }

    @GetMapping("/person/remove2")
    @ResponseBody
    public String removePerson2(@RequestParam("id") int id) {
        boolean removed = people.removeIf(person -> person.getId() == id);

        if (removed == false) {
            return id + "번 사람이 존재하지 않습니다.";
        } else return id + "번 사람이 삭제되었습니다.";
    }

    @GetMapping("/person/modify")
    @ResponseBody
    public String modifyPerson(@RequestParam("id") int id, @RequestParam("age") int age, @RequestParam("name") String name) {
        Person foundPerson = null;

        for (Person person : people) {
            if (person.getId() == id) {
                foundPerson = person;
            }
        }

        if (foundPerson == null) {
            return id + "번 사람이 존재하지 않습니다.";
        } else {
            foundPerson.setAge(age);
            foundPerson.setName(name);

            return id + "번 사람이 수정되었습니다.";
        }
    }

    @GetMapping("/person/modify2")
    @ResponseBody
    public String modifyPerson2(@RequestParam("id") int id, @RequestParam("age") int age, @RequestParam("name") String name) {
        Person foundPerson = people
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (foundPerson == null) {
            return id + "번 사람이 존재하지 않습니다.";
        } else {
            foundPerson.setAge(age);
            foundPerson.setName(name);

            return id + "번 사람이 수정되었습니다.";
        }
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Person {
    private int id;
    private int age;
    private String name;
}