const createMainContainer = () =>{
    const container = document.createElement("div");
    container.className = "main-container";
    container.style.gridRow = 5;
    let h3 = document.createElement("h3");
    h3.innerText = "Details";
    container.appendChild(h3);
    return container;
}

const createDetailParagraph = (distance, time) =>{
    const detailParagraph = document.createElement("p");
    detailParagraph.innerText = `Distance(m): ${distance}, time(ms): ${time}`;
    return detailParagraph;
}

const createInstructionH3 = ()=>{
    const instH3 = document.createElement("h3");
    instH3.innerText = "Instructions";
    return instH3;
}

const createInstructionDivs = (instructions)=>{
    return instructions.map(instruction =>{
        const instructionDiv = document.createElement('div');
        const h4 = document.createElement('h4');
        h4.innerText = "Instruction: ";
        const paragraph = document.createElement("p");
        paragraph.classList.add('point-details');
        paragraph.innerHTML += `Sign: ${instruction.sign} <br>
                                       name: ${instruction.name} <br>
                                       distance: ${instruction.distance} <br>
                                       time(ms): ${instruction.time} <br>
                                       angle: ${instruction.angle} <br>
                                       <span> points: ${JSON.stringify(instruction.points,
            null, 4)}</span>`;
        instructionDiv.append(h4);
        instructionDiv.appendChild(paragraph);
        return instructionDiv;
    })
}