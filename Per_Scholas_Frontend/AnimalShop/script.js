let themeBtn = document.getElementById("themeBtn");

let bgImg = document.getElementsByClassName("entry");
let labels = document.getElementsByTagName("label");
let form = document.getElementsByClassName("form");
let buttons = document.getElementsByTagName("button");

let lightSwitch = true;

function toggleMode()
{
    if(lightSwitch)
    {
        for(let e of bgImg) e.classList.add("entryDarkmode");
        for(let e of labels) e.classList.add("labelDarkmode");
        for(let e of form) e.classList.add("formDarkmode");
        for(let e of buttons) e.classList.add("buttonDarkmode");
    } else
    {
        for(let e of bgImg) e.classList.remove("entryDarkmode");
        for(let e of labels) e.classList.remove("labelDarkmode");
        for(let e of form) e.classList.remove("formDarkmode");
        for(let e of buttons) e.classList.remove("buttonDarkmode");
    }
    lightSwitch = !lightSwitch;
}

themeBtn.addEventListener("click", toggleMode);