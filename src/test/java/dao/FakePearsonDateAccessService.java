package dao;

import model.Person;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.stream.Stream;

import static com.jayway.jsonpath.Filter.filter;


@Repository("FakeDao")
public class FakePearsonDateAccessService implements PersonDao{
   private static List<Person> DB= new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPerson(){
        return DB;
    }

    @Override
    public int updatePerson(UUID id, Person person) {
        return selectPersonByID(id)
                .map(p ->{
                    int indexOfPersonToDelete=DB.indexOf(p);
                    if(indexOfPersonToDelete >= 0)
                    {
                        DB.set(indexOfPersonToDelete,new Person(id,person.getName()));
                        return 1;
                    }else{
                            return 0;
                    }
                }).orElse(0);
    }

    @Override
    public int deletePerson(UUID id) {
        Optional <Person> maybePerson = selectPersonByID(id);
        if(maybePerson.isEmpty()){
        return 0;
        }else{
            DB.remove(maybePerson.get());
            return 1;}
    }

    @Override
    public Optional<Person> selectPersonByID(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

}
