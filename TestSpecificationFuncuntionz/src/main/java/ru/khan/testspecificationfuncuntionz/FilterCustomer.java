package ru.khan.testspecificationfuncuntionz;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterCustomer {
    private String firstName;
    private String lastName;
    private String email;
    private Integer ageFrom;
    private Integer ageTo;
    private String prof;

}
