package ru.job4j.generics;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class UserStoreTest {


    @Test
    public void whenReplace() {
        UserStore uStore = new UserStore();
        uStore.add(new User("123"));
        boolean rsl = uStore.replace("123", new User("456"));
        boolean rsl2 = uStore.replace("123", new User("789"));
        assertNull(uStore.findById("123"));
        assertThat(uStore.findById("456").getId(), is("456"));
        assertThat(rsl, is(true));
        assertThat(rsl2, is(false));
    }

    @Test
    public void whenDelete() {
        UserStore uStore = new UserStore();
        uStore.add(new User("123"));
        boolean rsl = uStore.delete("123");
        boolean rsl2 = uStore.delete("456");
        assertNull(uStore.findById("123"));
        assertThat(rsl, is(true));
        assertThat(rsl2, is(false));

    }

}