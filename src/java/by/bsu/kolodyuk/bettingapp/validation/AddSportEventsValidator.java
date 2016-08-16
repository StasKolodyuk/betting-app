
package by.bsu.kolodyuk.bettingapp.validation;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.model.entity.SportEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


public class AddSportEventsValidator extends Validator {
    
    private static final String WRONG_SPORT_EVENTS_ERROR_KEY = "error.label.wrongSportEventsAdded";
    private static final String NO_SPORT_EVENTS_ERROR_KEY = "error.label.noSportEventsAdded";

    private List<SportEvent> sportEvents;
    private List<SportEvent> validSportEvents;

    public AddSportEventsValidator(List<SportEvent> sportEvents) {
        this.sportEvents = sportEvents;
        this.validSportEvents = new ArrayList<>();
    }
    
    @Override
    public boolean checkValidity() throws DatabaseException {
        return checkEmptiness() && checkSportEvents();
    }
    
    private boolean checkEmptiness() {
        boolean valid = (sportEvents != null && !sportEvents.isEmpty());
        if (!valid) {
            setErrorMessageKey(NO_SPORT_EVENTS_ERROR_KEY);
        }
        return valid;
    }
    
    private boolean checkSportEvents() {
        validSportEvents = sportEvents.stream().filter(i -> checkSportEvent(i)).collect(Collectors.toList());
        boolean valid = validSportEvents.size() == sportEvents.size();
        if (!valid) {
            setErrorMessageKey(WRONG_SPORT_EVENTS_ERROR_KEY);
        }
        return valid;
    }   
    
    private boolean checkSportEvent(SportEvent sportEvent) {
        return checkDescription(sportEvent.getDescription()) && checkStartAndEndTime(sportEvent.getStartTime(), sportEvent.getEndTime());
    }
    
    private boolean checkDescription(String description) {
        return description != null && !description.isEmpty();
    }
    
    private boolean checkStartAndEndTime(Calendar startTime, Calendar endTime) {
        return checkTime(startTime) && checkTime(endTime) && startTime.before(endTime);
    }
    
    private boolean checkTime(Calendar time) {
        return time != null && time.after(Calendar.getInstance());
    }

    public List<SportEvent> getValidSportEvents() {
        return validSportEvents;
    }
    
}
