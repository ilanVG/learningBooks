
var theQuestions = /*[[${selected.questions}]]*/ 'default';
console.log(theQuestions);
console.log(typeof theQuestions);

var randomIndex;

function getRandomQuestion(obj) {
    const keys = Object.keys(obj);
    randomIndex = Math.floor(Math.random() * keys.length);
    return keys[randomIndex];
}

function printQ()
{
    const element = document.getElementById("randomQuestions");
    element.style.backgroundColor = "#aed4c2";
    element.style.border = "3px";
    element.style.borderStyle = "inset";

    document.getElementById("randomQuestions").innerHTML = getRandomQuestion(theQuestions);
}

function printA()
{
    const element = document.getElementById("randomQuestions");
    element.style.backgroundColor = "#1affa3";
    element.style.border = "3px";
    element.style.borderRadius = "6px"
    element.style.borderStyle = "double";
    var questionsArray = Object.values(theQuestions);
    document.getElementById("randomQuestions").innerHTML = questionsArray[randomIndex];
}
