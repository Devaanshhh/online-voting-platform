fetch("/vote")
.then(res => res.json())
.then(data => {

    let rows = "";

    data.forEach(vote => {

        const date = new Date(vote.voteTime);

        const formattedDate = date.toLocaleString("en-IN", {
            day: "2-digit",
            month: "short",
            year: "numeric",
            hour: "2-digit",
            minute: "2-digit",
            second: "2-digit",
            hour12: true
        });

        rows += `
        <tr>
            <td>${vote.id}</td>
            <td>${vote.voter.name}</td>
            <td>${vote.candidate.candidateName}</td>
            <td>${formattedDate}</td>
        </tr>
        `;
    });

    document.getElementById("historyTable").innerHTML = rows;

});