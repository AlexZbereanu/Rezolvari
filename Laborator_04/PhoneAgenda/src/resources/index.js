function addPerson()
{
    var id = document.getElementById("id");
    var nume = document.getElementById("nume");
    var prenume = document.getElementById("prenume");
    var telefon = document.getElementById("telefon");

    var xhttp = new XMLHttpRequest();
    var url = "http://localhost:8080/person";

    xhttp.open("POST", url, true);

    xhttp.setRequestHeader("Content-Type", "application/json; utf-8");

    xhttp.onreadystatechange = function () {
        if(xhttp.status === 201 && xhttp.readyState === 4)
        {
            alert("Persoana adaugata cu succes!");
        }
        else if(xhttp.status === 400 && xhttp.readyState === 4)
        {
            alert("Datele transmise sunt incorecte!");
        }
    };

    var data = JSON.stringify({"id" : id.value, "firstName" : nume.value,"lastName" : prenume.value,"telephoneNumber": telefon.value});
    xhttp.send(data);
}

function deletePerson(url) {
    var ids = document.getElementById("id_persoana");
    return fetch(url + '/' + ids.value, {
        method: 'delete'
    })
        .then(response => response.json());
}


function showPerson()
{
    var xhttp = new XMLHttpRequest();
    var url = "http://localhost:8080/agenda";

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var element = document.getElementById("persoane");
            var objects = this.responseText;
            data = JSON.parse(objects);
            data.forEach(function(elements){
                element.innerHTML += "<div>Id:" + elements.id + "</div>";
                element.innerHTML += "<div>LastName:" + elements.lastName + "</div>";
                element.innerHTML += "<div>FirstName:" + elements.firstName + "</div>";
                element.innerHTML += "<div>Telephone:" + elements.telephoneNumber + "</div><br />";
            });
        }
    };

    xhttp.open("GET", url, true);
    xhttp.send();

}