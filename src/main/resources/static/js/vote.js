const BASE_URL = "http://localhost:9999";

loadVoters();
loadCandidates();

function loadVoters() {

    fetch(BASE_URL + "/voter")
        .then(res => res.json())
        .then(data => {

            let options = "";

            data.forEach(voter => {

                options += `
                    <option value="${voter.id}">
                        ${voter.name} (${voter.voterId})
                    </option>
                `;

            });

            document.getElementById("voter").innerHTML = options;

        });

}

function loadCandidates() {

    fetch(BASE_URL + "/candidate")
        .then(res => res.json())
        .then(data => {

            let options = "";

            data.forEach(candidate => {

                options += `
                    <option value="${candidate.id}">
                        ${candidate.candidateName} (${candidate.party})
                    </option>
                `;

            });

            document.getElementById("candidate").innerHTML = options;

        });

}

function castVote() {

    const voterId = document.getElementById("voter").value;

    const candidateId = document.getElementById("candidate").value;

    fetch(BASE_URL + "/vote/" + voterId + "/" + candidateId, {

        method: "POST"

    })

    .then(response => response.text())

    .then(message => {

        document.getElementById("result").innerHTML = message;

    });

}