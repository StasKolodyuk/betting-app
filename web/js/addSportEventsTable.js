window.onload = addRow;

var currentLanguage;

function init(language) {
    currentLanguage = language;
}

function addRow() {
    var table = document.getElementById("addSportEventsTable");
    var newRow = table.insertRow(table.rows.length);
    var startTimeCell = newRow.insertCell(0);
    startTimeCell.appendChild(createDateTimeCell());
    var endTimeCell = newRow.insertCell(1);
    endTimeCell.appendChild(createDateTimeCell());
    var sportTypeCell = newRow.insertCell(2);
    sportTypeCell.appendChild(createSelectCell());
    var descriptionCell = newRow.insertCell(3);
    descriptionCell.appendChild(createTextCell());
}

function createTextCell() {
    var textCell = document.createElement('input');
    textCell.type = 'text';
    textCell.className = 'mediuminput';
    return textCell;
}

function createNumberCell() {
    var numberCell = document.createElement('input');
    numberCell.type = 'number';
    numberCell.min = 1.01;
    numberCell.step = 0.01;
    numberCell.className = 'smallinput';
    return numberCell;
}

function createDateTimeCell() {
    var dateTimeCell = document.createElement('input');
    dateTimeCell.type = 'datetime-local';
    dateTimeCell.className = 'mediuminput';
    dateTimeCell.style.padding = '4px 0px';
    return dateTimeCell;
}

var optionValues = ["FOOTBALL", "HOCKEY"];
var optionArrayEN = ["Football", "Hockey"];
var optionArrayRU = ["Футбол", "Хоккей"];

function createSelectCell() {
    var optionArray;
    if(currentLanguage === "ru_RU") {
        optionArray = optionArrayRU;
    } else {
        optionArray = optionArrayEN;
    }
    var selectCell = document.createElement('select');
    selectCell.className = 'mediuminput';
    for (var i = 0; i < optionArray.length; i++) {
        var option = document.createElement('option');
        option.value = optionValues[i];
        option.text = optionArray[i];
        selectCell.appendChild(option);
    }
    return selectCell;
}

function getCellInput(table, i, j) {
    return table.rows[i].cells[j].getElementsByTagName('input')[0].value;
}

function getSelectCellInput(table, i, j) {
    return table.rows[i].cells[j].getElementsByTagName('select')[0].value;
}

function addSportEvents() {
    var table = document.getElementById("addSportEventsTable");
    var newSportEvents = [];
    for (var i = 1; i < table.rows.length; i++) {
        var newSportEvent = {};
        newSportEvent.startTime = convertDateToUTC(new Date(getCellInput(table, i, 0))).getTime();
        newSportEvent.endTime = convertDateToUTC(new Date(getCellInput(table, i, 1))).getTime();
        newSportEvent.sportType = getSelectCellInput(table, i, 2);
        newSportEvent.description = getCellInput(table, i, 3);
        newSportEvents.push(newSportEvent);
    }
    document.getElementById("newSportEvents").value = JSON.stringify(newSportEvents);
}

function convertDateToUTC(date) {
    return new Date(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDate(), date.getUTCHours(), date.getUTCMinutes(), date.getUTCSeconds());
}
