package ibm.grupo2.helloBank.Repositories;

import ibm.grupo2.helloBank.Models.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {


    //Security Methods

//    default void delete(Log log){
//        if (LocalDate.now().isAfter(ChronoLocalDate.from(log.getCreated_at().plusYears(3)))){
//            try {
//                throw new IllegalAccessException();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    default void deleteByID(Long l){
//
//        if (LocalDate.now().isAfter(ChronoLocalDate.from(findById(l).get().getCreated_at().plusYears(3)))){
//            try {
//                throw new IllegalAccessException();
//            } catch (IllegalAccessException e) {
//                e.getMessage();
//            }
//        }
//    }

   List<Log> findAllByOriginAndDateGreaterThanEqualAndDateLessThanEqual(String number, Date date1, Date date2);
}
