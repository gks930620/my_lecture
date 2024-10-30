package com.shop.jpa.start_test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SettingCheckController {
    @PersistenceUnit
    private EntityManagerFactory emf;


    @RequestMapping(value = {"/test"})
    public String home(Model model) {
        EntityManager em = emf.createEntityManager();
        TestEntity test = em.find(TestEntity.class, "test");
        model.addAttribute("test", test);
        em.close();
        return "test";
    }

}
