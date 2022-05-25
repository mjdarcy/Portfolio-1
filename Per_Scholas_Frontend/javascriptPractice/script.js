function toggle(){
    alert("The button has been clicked.");
    /*
    let text = document.getElementById("toggleText");
    if(text.innerText === "")
    {

    } else
    {

    }*/
}

function update()
{
    let input = document.getElementById("input").innerText;
    let output = document.getElementById("output").innerText;
    output = input;
}

document.getElementById("input").addEventListener('input', update);