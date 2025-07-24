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
public class CarController {
    int lastId;
    List<MyCar> myCars;

    CarController () {
        lastId = 0;
        myCars = new ArrayList<>();
    }

    @GetMapping("/mycar/add")
    @ResponseBody
    public String myCar (@RequestParam("speed") int speed, @RequestParam("name") String name, @RequestParam("color") String color) {
        lastId++;

        MyCar myCar = new MyCar(lastId, speed, name, color);
        myCars.add(myCar);

        return String.format("%d번째 사람이 추가되었습니다.", myCar.getId());
    }

    @GetMapping("/mycar/list")
    @ResponseBody
    public List<MyCar> myCarList () {
        System.out.println(myCars);
        return myCars;
    }

    @GetMapping("/mycar/remove")
    @ResponseBody
    public String CarRemove (@RequestParam("id") int id) {
        boolean removed = false;

        for (MyCar myCar : myCars) {
            if (id == myCar.getId()) {
                myCars.remove(myCar);
                removed = true;
            }
        }
        if (removed == false) {
            return "없는 차입니다.";
        }
        return "삭제되었습니다.";
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class MyCar {
    int id;
    int speed;
    String name;
    String color;
}