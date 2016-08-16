    window.onload = initTableListeners;
    var table;
    var selectedBets;
    var resultBets;
    var betStartTime;
    var resultBetCoefficient;

    function initTableListeners() {
        table = document.getElementById("selectableTable");
        resultBets = [];
        resultBetCoefficient = 1;
        if (table !== null) {
            selectedBets = create2DArray(table.rows.length);
            for (var i = 1; i < table.rows.length; i++) {
                for (var j = 2; j < table.rows[i].cells.length; j++) {
                    table.rows[i].cells[j].onclick = (function(){
                        var currentI = i;
                        var currentJ = j;
                        return function () {
                            onClickCellFunction(currentI, currentJ);
                        };
                    })();
                }
            }
        }
    }

    function deselectCell(i, j) {
        table.rows[i].cells[j].className = table.rows[i].cells[j].className.replace(/(?:^|\s)selected(?!\S)/g, '');
        selectedBets[i][j] = false;
        updateResultBetCoefficient();
    }

    function deselectRow(i) {
        for(var j = 0; j < selectedBets[i].length; j++) {
            if(selectedBets[i][j]) {
                deselectCell(i, j);
            }
        }
    }

    function selectCell(i, j) {
        if(table.rows[i].cells[j].getElementsByTagName('span')[0].innerHTML !== ""){
            selectedBets[i][j] = true;
            table.rows[i].cells[j].className += " selected";
            updateResultBetCoefficient();
        }
    }
    
    function onClickCellFunction(i, j) {
        if (selectedBets[i][j]) {
            deselectCell(i, j);
        } else {
            deselectRow(i);
            selectCell(i, j);
        }
    }
        
    function getSelectedBets() {
        resultBets = [];
        var earliestStartTime = null;
        for(var i = 0; i < selectedBets.length; i++) {
            for(var j = 0; j < selectedBets[i].length; j++) {
                if(selectedBets[i][j]) {
                    var startTime = new Date(parseInt(table.rows[i].cells[0].getElementsByTagName('input')[0].value));
                    if (earliestStartTime === null || startTime.getTime() < earliestStartTime.getTime()) {
                        earliestStartTime = startTime;
                    }
                    resultBets.push(table.rows[i].cells[j].getElementsByTagName("input")[0].value);
                }
            }
        }
        var ass = JSON.stringify(resultBets);
        document.getElementById("resultBet").value = ass;
        document.getElementById("resultBetCoefficient").value = resultBetCoefficient;
        document.getElementById("earliestBetStartTime").value = earliestStartTime.getTime();
    }

    function create2DArray (rows) {
        var arr = [];
        for (var i = 0; i < rows; i++) {
            arr[i] = [];
        }
        return arr;
    }
    
    function updateResultBetCoefficient() {
        resultBetCoefficient = 1;
        for (var i = 0; i < selectedBets.length; i++) {
            for (var j = 0; j < selectedBets[i].length; j++) {
                if (selectedBets[i][j]) {
                    resultBetCoefficient *= table.rows[i].cells[j].getElementsByTagName('span')[0].innerHTML;
                }
            }
        }
        document.getElementById("resultBetCoefficientCounter").innerHTML = resultBetCoefficient.toFixed(3);
    }