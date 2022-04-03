package service;

import dao.PersonDao;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("FakeDao") PersonDao PersonDao){
        this.personDao = PersonDao;
    }

    public int addPerson(Person person){
        return personDao.insertPerson(person);
    }

    public List <Person> getAllPeople(){
        return personDao.selectAllPerson();
    }

    public Optional<Person> getPersonByID(UUID id){
        return personDao.selectPersonByID(id);
    }

    public int deletePerson(UUID id){
        return personDao.deletePerson(id);
    }

    public int updatePerson(UUID id,Person newPerson){
        return personDao.updatePerson(id,newPerson);
    }
}
