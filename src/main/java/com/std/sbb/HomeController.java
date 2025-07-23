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

// @Controller의 의미 :  개발자가 스프링부트에게 말한다. >> 아래에 있는 HomeController는 컨트롤러다.
@Controller
public class HomeController {

    // @GetMapping의 의미 :  개발자가 스프링부트에게 말한다. >> 만약에 /home/main 이런 요청이 오면 아래 메서드를 실행해줘.
    @GetMapping("/home/main")
    @ResponseBody // 있는 그대로 반환하겠다.
    public String ShowMain() {
        return "안녕하세요";
    }

    @GetMapping("/home/main2")
    @ResponseBody // 있는 그대로 반환하겠다.
    public String ShowMain2() {
        return "반갑습니다.";
    }

    @GetMapping("/home/main3")
    @ResponseBody // 있는 그대로 반환하겠다.
    public String ShowMain3() {
        return "즐거웠습니다.";
    }

    private int i;
    HomeController () {
        int i = -1;
    }
    @GetMapping("/home/increase")
    @ResponseBody
    public int ShowIncrease() {
        i++;
        return i;
    }

    @GetMapping("/home/plus")
    @ResponseBody
    public int plus(@RequestParam(value = "a", defaultValue = "0") int a,
                    @RequestParam(value = "b", defaultValue = "0") int b) {
        return a + b;
    }

    @GetMapping("/home/minus")
    @ResponseBody
    public int minus(@RequestParam(value = "a", defaultValue = "0") int a,
                     @RequestParam(value = "b", defaultValue = "0") int b) {
        return a - b;
    }

    @GetMapping("/home/multifly")
    @ResponseBody
    public int multifly(@RequestParam(value = "a", defaultValue = "0") int a,
                        @RequestParam(value = "b", defaultValue = "0") int b) {
        return a * b;
    }

    @GetMapping("/home/divid")
    @ResponseBody
    public int divid(@RequestParam(value = "a", defaultValue = "0") int a,
                     @RequestParam(value = "b", defaultValue = "0") int b) {
        return a / b;
    }

    @GetMapping("/home/calc")
    @ResponseBody
    public int calc(@RequestParam(value = "a", defaultValue = "0") int a,
                    @RequestParam(value = "b", defaultValue = "0") int b,
                    @RequestParam(value = "c") String c) {
        if (c.equals("pl")) return a + b;
        if (c.equals("mi")) return a - b;
        if (c.equals("mu")) return a * b;
        if (c.equals("di")) return a / b;
        return a;
    }

    @GetMapping("/home/returnCar")
    @ResponseBody
    public Car showReturnCar () {
        Car car = new Car();
        car.setId(2);
        car.setSpeed(200);
        car.setName("랭글러");
        car.setIds(new ArrayList(){{
            add(1);
            add(2);
            add(3);
        }});
        return car;
    }
}

// 전체 속성을 포함하는 생성자 함수 생성
//게터 세터 생성
@AllArgsConstructor
@NoArgsConstructor //값이 없을 때의 처리
@Getter
@Setter
class Car {
    private int id;
    private int speed;
    private String name;
    private List<Integer> ids;
}
