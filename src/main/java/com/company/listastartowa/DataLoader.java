package com.company.listastartowa;

import com.company.listastartowa.model.User;
import com.company.listastartowa.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        User user1 = new User("Robert C", "robert1216@poczta.wp.pl");
        User user2 = new User("Anka", "rosalinda_b@onet.eu");
        User user3 = new User("Tomek FIT", "ktoscosgdzies@onet.pl");
        User user4 = new User("Basia", "basik2@gmail.com");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        logger.debug("Dodano uzytkownika: {}", user1);
        logger.debug("Dodano uzytkownika: {}", user2);
        logger.debug("Dodano uzytkownika: {}", user3);
        logger.debug("Dodano uzytkownika: {}", user4);
        logger.info("Dodano wiadomosci do bazy danych - klasa DataLoader");
    }

}
