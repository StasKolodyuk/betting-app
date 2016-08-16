function getAddedBets() {
    var addedBets = [];
    var table = document.getElementById("addBetsTable");
    for(var i = 1; i < table.rows.length; i++) {
        for(var j = 1; j < table.rows[i].cells.length; j++) {
            var input = getCellInput(table, i, j);
            if(input !== "" && input !== null) {
                var addedBet = {};
                addedBet.first = parseInt(getCellInput(table, i, 0));
                addedBet.second = getCellInput(table, 0, j);
                addedBet.third = parseFloat(input);
                addedBets.push(addedBet);
            }
        }
    }
    document.getElementById("newBets").value = JSON.stringify(addedBets);
}

function getCellInput(table, i, j) {
    var inputElements = table.rows[i].cells[j].getElementsByTagName('input');
    if(inputElements !== null && inputElements.length !== 0) {
        return inputElements[0].value;
    }
    return null;
}