
package by.bsu.kolodyuk.bettingapp.validation;

import by.bsu.kolodyuk.bettingapp.exception.DatabaseException;
import by.bsu.kolodyuk.bettingapp.model.entity.Score;
import by.bsu.kolodyuk.bettingapp.utility.container.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AddSportEventResultsValidator extends Validator {
    
    private static final String WRONG_SPORT_EVENT_RESULTS_ERROR_KEY = "error.label.wrongSportEventResultsAdded";
    private static final String NO_SPORT_EVENT_RESULTS_ADDED_ERROR_KEY = "error.label.noSportEventResultsAdded";

    private List<Pair<Integer, Score>> sportEventResults;
    private List<Pair<Integer, Score>> validSportEventResults;

    public AddSportEventResultsValidator(List<Pair<Integer, Score>> sportEventResults) {
        this.sportEventResults = sportEventResults;
        this.validSportEventResults = new ArrayList<>();
    }
    
    @Override
    public boolean checkValidity() throws DatabaseException {
        return checkEmptiness() && checkSportEventResults();
    }
    
    private boolean checkSportEventResults() {
        validSportEventResults = sportEventResults.stream().filter(i -> checkSportEventResult(i)).collect(Collectors.toList());
        boolean valid = validSportEventResults.size() == sportEventResults.size();
        if (!valid) {
            setErrorMessageKey(WRONG_SPORT_EVENT_RESULTS_ERROR_KEY);
        }
        return valid;
    }
    
    private boolean checkEmptiness() {
        boolean valid = (sportEventResults != null && !sportEventResults.isEmpty());
        if (!valid) {
            setErrorMessageKey(NO_SPORT_EVENT_RESULTS_ADDED_ERROR_KEY);
        }
        return valid;
    }    
    private boolean checkSportEventResult(Pair<Integer, Score> sportEventResult) {
        return sportEventResult != null && checkSportEventId(sportEventResult.getFirst()) && checkScore(sportEventResult.getSecond());
    }
    
    private boolean checkSportEventId(int sportEventId) {
        return sportEventId > 0;
    }
    
    private boolean checkScore(Score score) {
        return score != null && score.getGuestTeamScored() >= 0 && score.getHomeTeamScored() >= 0;
    }

    public List<Pair<Integer, Score>> getValidSportEventResults() {
        return validSportEventResults;
    }
    
}
