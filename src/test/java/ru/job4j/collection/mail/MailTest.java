package ru.job4j.collection.mail;

import org.junit.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MailTest {

    @Test
    public void whenUsersMerge() {
        Set<Email> set1 = new HashSet<>(Arrays.asList(
                new Email("xxx@ya.ru"),
                new Email("foo@gmail.com"),
                new Email("lol@mail.ru")
        ));
        Set<Email> set2 = new HashSet<>(Arrays.asList(
                new Email("ups@pisem.net"),
                new Email("foo@gmail.com")

        ));
        Set<Email> set3 = new HashSet<>(Arrays.asList(
                new Email("xyz@pisem.net"),
                new Email("vasya@pupkin.com")
        ));
        Set<Email> set4 = new HashSet<>(Arrays.asList(
                new Email("ups@pisem.net"),
                new Email("aaa@bbb.ru")
        ));
        Set<Email> set5 = new HashSet<>(Arrays.asList(
                new Email("xyz@pisem.net")
        ));
        List<User> list = Arrays.asList(
                new User("User1", set1),
                new User("User2", set2),
                new User("User3", set3),
                new User("User4", set4),
                new User("User5", set5)
        );
        Set<Email> set6 = new HashSet<>(Arrays.asList(
                new Email("xxx@ya.ru"),
                new Email("foo@gmail.com"),
                new Email("ups@pisem.net"),
                new Email("lol@mail.ru"),
                new Email("aaa@bbb.ru")
        ));
        Set<Email> set7 = new HashSet<>(Arrays.asList(
                new Email("xyz@pisem.net"),
                new Email("vasya@pupkin.com")
        ));
        List<User> expected = Arrays.asList(
                new User("User1", set6),
                new User("User3", set7)
        );
        assertThat(new Mail().mergeUsers(list), is(expected));
    }

    @Test
    public void whenUsersNotMerge() {
        Set<Email> set1 = new HashSet<>(Arrays.asList(
                new Email("xxx@ya.ru")
        ));
        Set<Email> set2 = new HashSet<>(Arrays.asList(
                new Email("foo@gmail.com")
        ));
        User user1 = new User("User1", set1);
        User user2 = new User("User2", set2);
        List<User> list = Arrays.asList(user1, user2);
        assertThat(new Mail().mergeUsers(list), is(list));
    }
}