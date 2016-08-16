function getSportEventResults() {
    var table = document.getElementById("setResultsTable");
    var sportEventResults = [];
    for(var i = 1; i < table.rows.length; i++) {
        var columnNumber = table.rows[i].cells.length;
        var resultCellInputs = table.rows[i].cells[columnNumber-1].getElementsByTagName('input');
        var homeTeamScored = resultCellInputs[0].value;
        var guestTeamScored = resultCellInputs[1].value;
        if(homeTeamScored !== null && homeTeamScored !== "" && guestTeamScored !== null && guestTeamScored !== "") {
            var sportEventResult = {};
            sportEventResult.first = parseInt(getCellInput(table, i, 0));
            sportEventResult.second = {homeTeamScored: parseInt(homeTeamScored), guestTeamScored: parseInt(guestTeamScored)};
            sportEventResults.push(sportEventResult);
        }
    }
    document.getElementById("sportEventResults").value = JSON.stringify(sportEventResults);
}

function getCellInput(table, i, j) {
    var inputElements = table.rows[i].cells[j].getElementsByTagName('input');
    if(inputElements !== null && inputElements.length !== 0) {
        return inputElements[0].value;
    }
    return null;
}