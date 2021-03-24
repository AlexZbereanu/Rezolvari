function updateazaCheltuiala() {
    let id_c = document.getElementById("updateId").value;
    let pret = document.getElementById("updatePret").value;
    let tip = document.getElementById("updateTip").value;
    let descriere = document.getElementById("updateDescriere").value;
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log("Happy little trees")
        }
    };

    let obj = {
        "id_c": id_c,
        "pret": pret,
        "tip": tip,
        "descriere": descriere
    };
    xhttp.open("PUT", "/actualizare_cheltuiala", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(obj));

}