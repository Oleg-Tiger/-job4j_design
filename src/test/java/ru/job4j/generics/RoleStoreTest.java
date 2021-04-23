package ru.job4j.generics;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class RoleStoreTest {


    @Test
    public void whenReplace() {
        RoleStore rStore = new RoleStore();
        rStore.add(new Role("123"));
        boolean rsl = rStore.replace("123", new Role("456"));
        boolean rsl2 = rStore.replace("123", new Role("789"));
        assertNull(rStore.findById("123"));
        assertThat(rStore.findById("456").getId(), is("456"));
        assertThat(rsl, is(true));
        assertThat(rsl2, is(false));
    }

    @Test
    public void whenDelete() {
        RoleStore rStore = new RoleStore();
        rStore.add(new Role("123"));
        boolean rsl = rStore.delete("123");
        boolean rsl2 = rStore.delete("456");
        assertNull(rStore.findById("123"));
        assertThat(rsl, is(true));
        assertThat(rsl2, is(false));

    }

}