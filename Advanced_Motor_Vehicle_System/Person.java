package Advanced_Motor_Vehicle_System;
import java.time.LocalDate;

class Person {
    private String personId, fullName, email, phone;
    private LocalDate dob;

    public Person(String personId, String fullName, LocalDate dob, String email, String phone) {
        this.personId = personId;
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
    }
}
