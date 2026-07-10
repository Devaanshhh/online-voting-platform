const BASE_URL="http://localhost:9999";

loadVoters();

function loadVoters(){

fetch(BASE_URL+"/voter")

.then(res=>res.json())

.then(data=>{

let rows="";

data.forEach(v=>{

rows+=`

<tr>

<td>${v.id}</td>

<td>${v.name}</td>

<td>${v.email}</td>

<td>${v.voterId}</td>

<td>${v.voted}</td>

</tr>

`;

});

document.getElementById("voterTable").innerHTML=rows;

});

}

function registerVoter() {

    const voter = {

        name: document.getElementById("name").value,

        email: document.getElementById("email").value,

        voterId: document.getElementById("voterId").value,

        password: document.getElementById("password").value

    };

    fetch(BASE_URL + "/voter/register", {

        method: "POST",

        headers: {

            "Content-Type": "application/json"

        },

        body: JSON.stringify(voter)

    })

    .then(async response => {

        const data = await response.json();

        if (!response.ok) {

            throw new Error(data.message);

        }

        return data;

    })

    .then(() => {

        alert("Voter registered successfully!");

        document.getElementById("name").value = "";

        document.getElementById("email").value = "";

        document.getElementById("voterId").value = "";

        document.getElementById("password").value = "";

        loadVoters();

    })

    .catch(error => {

        alert(error.message);

    });

}