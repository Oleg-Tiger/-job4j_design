package ru.job4j.collection.mail;


import java.util.*;

public class Mail {

    /**
     * Этот метод принимает список пользователей, каждому из которых соответствует
     * множество электронных адресов почт. Если у разных пользователей, есть одинаковые
     * адреса почт, то они объединяются, считается, что это один и тот же полоьзователь.
     * Ему присваивается имя одного из обьъединённых пользователей.
     * В Данном методе есть локальная переменная merge. Это карта, в которую записываются
     * в качестве ключа и значения два объединённых пользователя. Ключ - пользователь,
     * который не занесён в результирующую коллекцию, значение - который занесён.
     * Делается это для того, чтобы в случае необходимости объединения третьего пользователя
     * с пользователем-ключом мы объеденили его сразу с пользователем-значением.
     * @param list список пользователей
     * @return новый списаок пользователей, в котором пользователи, имеющие хотя бы одно
     * совпадение по адресам электронных почт, объединены
     */
    public List<User> mergeUsers(List<User> list) {
        Map<Email, List<User>> map = usersForEachMail(list);
        Set<User> rsl = new HashSet<>();
        Map<User, User> merge = new HashMap<>();
      for (Map.Entry<Email, List<User>> pairs : map.entrySet()) {
          List<User> users = pairs.getValue();
          User firstInList = users.get(0);
          if (!merge.containsKey(firstInList)) {
              rsl.add(firstInList);
              for (int i = 1; i < users.size(); i++) {
                  firstInList.getMailSet().addAll(users.get(i).getMailSet());
                  merge.put(users.get(i), firstInList);
              }
          } else {
              for (int i = 1; i < users.size(); i++) {
                  merge.get(firstInList).getMailSet().addAll(users.get(i).getMailSet());
                  merge.put(users.get(i), merge.get(firstInList));
              }

          }
      }
        return new ArrayList<>(rsl);
      }

    /**
     * Этот метод принимает на вход список пользователей, у которых есть в качестве
     * поля множество почт. Он добавляет каждую почту из всех множеств
     * в качестве ключа в карту. В качечстве значения - список пользователей,
     * у которых данная почта хранится. Важно, что в качестве возвращаемого
     * значения используется имеенно упорядоченная в порядке добавлению карта, т.к.
     * в дальнейшем надо будет перебирать значения карты именно в порядке добавления
     * для правильной работы программы.
     * @param list список пользователей
     * @return карта, содержащая почту вкачестве ключа и список пользователей,
     * хранящих данную почту, в качестве значения
     */
    private Map<Email, List<User>> usersForEachMail(List<User> list) {
        Map<Email, List<User>> rsl = new LinkedHashMap<>();
        for (User user : list) {
            for (Email mails : user.getMailSet()) {
                if (rsl.containsKey(mails)) {
                    rsl.get(mails).add(user);
                } else {
                    List<User> inMap = new ArrayList<>(list.size());
                    inMap.add(user);
                    rsl.put(mails, inMap);
                }
            }
        }
        return rsl;
    }


}



