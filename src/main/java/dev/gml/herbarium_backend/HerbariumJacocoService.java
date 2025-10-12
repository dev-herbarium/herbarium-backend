package dev.gml.herbarium_backend;

import org.springframework.stereotype.Service;

@Service
public class HerbariumJacocoService {
    public String getStatus() {
        return "Herbarium-Backend is Active!";
    }

    // An untested method to demonstrate coverage logic:
    public int calculate(int a, int b) {
        if (a > 0) {
            return a + b;
        } else {
            return a - b;
        }
    }
}
