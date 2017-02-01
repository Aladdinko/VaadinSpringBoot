package vaadin.spring.boot.example.backend;

import org.springframework.stereotype.Service;

/**
 * Created by Maggouh on 26/01/17.
 */
@Service
public class MyBackendBean implements MyBackend {
    @Override
    public String adminOnlyEcho(String s) {
        return "Admin : " + s;
    }

    @Override
    public String echo(String s) {
        return s;
    }


}
