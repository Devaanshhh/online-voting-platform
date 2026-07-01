const BASE_URL = "http://localhost:9999";
let editing = false;
let editingId = null;
loadCandidates();

function loadCandidates() {

fetch(BASE_URL + "/candidate")

.then(response => response.json())

.then(data => {

let rows = "";

data.forEach(candidate => {

rows += `

<tr>

<td>${candidate.id}</td>

<td>${candidate.candidateName}</td>

<td>${candidate.party}</td>

<td>${candidate.votes}</td>

<td>

<button
class="btn btn-warning btn-sm me-2"
onclick="editCandidate(${candidate.id},
'${candidate.candidateName}',
'${candidate.party}')">

Edit

</button>

<button
class="btn btn-danger btn-sm"
onclick="deleteCandidate(${candidate.id})">

Delete

</button>

</td>

</tr>

`;

});

document.getElementById("candidateTable").innerHTML = rows;

});

}

function addCandidate(){

const candidate={

candidateName:
document.getElementById("candidateName").value,

party:
document.getElementById("party").value

};

if(editing){

fetch(BASE_URL+"/candidate/"+editingId,{

method:"PUT",

headers:{
"Content-Type":"application/json"
},

body:JSON.stringify(candidate)

})

.then(()=>{

editing=false;

editingId=null;

document.querySelector("button").innerText="Add Candidate";

document.getElementById("candidateName").value="";

document.getElementById("party").value="";

loadCandidates();

});

}else{

fetch(BASE_URL+"/candidate",{

method:"POST",

headers:{
"Content-Type":"application/json"
},

body:JSON.stringify(candidate)

})

.then(()=>{

document.getElementById("candidateName").value="";

document.getElementById("party").value="";

loadCandidates();

});

}

}

function deleteCandidate(id){

fetch(BASE_URL+"/candidate/"+id,{

method:"DELETE"

})

.then(()=>loadCandidates());

}
function editCandidate(id, name, party){

editing = true;

editingId = id;

document.getElementById("candidateName").value = name;

document.getElementById("party").value = party;

document.querySelector("button").innerText = "Update Candidate";

}