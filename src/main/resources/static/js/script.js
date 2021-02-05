const srcMenu = document.querySelector("#src-menu");
const source = document.querySelector("#source");
const dstMenu = document.querySelector("#dst-menu");
const destination = document.querySelector("#destination");
const mainContainer = document.querySelector(".details");
const coords = {};

const createOptions = (locations, menu, label) => {
    locations.forEach((l) => {
        let newLocationA = document.createElement("a");
        newLocationA.href = "#";
        newLocationA.className = "dropdown-item";
        newLocationA.innerText = l.name;
        newLocationA.addEventListener("click", evt => {
            label.value = newLocationA.innerText;
        });
        menu.appendChild(newLocationA);
    });
}

const getPointsOfInterest = async () => {
    const response = await fetch('poi/')
    return await response.json()
}

const init = async () => {
    const pointsOfInterest = await getPointsOfInterest();
    pointsOfInterest.forEach((poi) => {
        coords[poi.name] = [poi.lat, poi.lon];
    });

    createOptions(pointsOfInterest, srcMenu, source);
    createOptions(pointsOfInterest, dstMenu, destination);

}

const getInstructions = async (body = {}) => {
    const response = await fetch('/nav', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    });
    return await response.json();
}


const submitForm = async (event) => {
    event.preventDefault();
    let srcCoords = coords[source.value];
    let destCoords = coords[destination.value];

    const {distance, time, instructions} = await getInstructions({
        srcLat: srcCoords[0],
        srcLon: srcCoords[1],
        destLat: destCoords[0],
        destLon: destCoords[1]
    });
    const detailsH3 = createDetailH3();
    const detailParagraph = createDetailParagraph(distance, time);
    const instH3 = createInstructionH3();
    const instructionDivs = createInstructionDivs(instructions);

    mainContainer.innerHTML = "";
    mainContainer.append(detailsH3);
    mainContainer.appendChild(detailParagraph);
    mainContainer.appendChild(instH3);
    instructionDivs.forEach((instructionDiv) => {
        mainContainer.appendChild(instructionDiv);
    });
}

init().then(() => {
    console.log('Coordinates available!')
});