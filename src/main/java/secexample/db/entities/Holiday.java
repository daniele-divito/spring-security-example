package secexample.db.entities;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import secexample.db.enumerations.HolidayStatus;
import secexample.db.enumerations.HolidayType;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Holiday {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    private HolidayType type;
    private LocalDate startTime;
    private LocalDate endTime;
    private String notes;
    private HolidayStatus status;
    private LocalDate requestTime;
    private LocalDate actionTime;
    private LocalDate lastUpdate;


    @ManyToOne
    @JoinColumn(name = "application_user_id",referencedColumnName = "id")
    private ApplicationUser requester;

    @ManyToOne
    @JoinColumn(name = "action_user_id",referencedColumnName = "id")
    private ApplicationUser actioner; // the user who confirmed or rejected


}
