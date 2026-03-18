package ru.khan.testspecificationfuncuntionz;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class CustomerSpecification {

    public static Specification<Customer> filter(FilterCustomer f){
        return (root, query, criteriaBuilder) ->  {
            List<Predicate> p = new ArrayList<>();

            if (f.getFirstName() != null) {
                p.add(criteriaBuilder.equal(root.get("firstName"), f.getFirstName()));
            }
            if (f.getLastName() != null) {
                p.add(criteriaBuilder.equal(root.get("lastName"), f.getLastName()));
            }
            if (f.getEmail() != null) {
                p.add(criteriaBuilder.like(root.get("email"), f.getEmail()));
            }
            if (f.getAgeFrom() != null){
                p.add(criteriaBuilder.ge(root.get("age"), f.getAgeFrom()));
            }
            if (f.getAgeTo() != null){
                p.add(criteriaBuilder.le(root.get("age"), f.getAgeTo()));
            }if (f.getProf() != null) {
                p.add(criteriaBuilder.like(root.get("prof"), f.getProf()));
            }

            return criteriaBuilder.and(p.toArray(new Predicate[0]));
        };
    }
}
