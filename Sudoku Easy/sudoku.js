var currNum = null;
var curTile = null;
var startBoard = null;
var win = window;

var errs = 0;

var board = [
    "8--------",
    "--36-----",
    "-7--9-2--",
    "-5---7---",
    "----45---",
    "---1---3-",
    "--1----6-",
    "--85---1-",
    "-9----4--"
]

var soln = [
    
]

win.onload = function() {
    if (!validBoard(board)) {
        console.log("Invalid Board");
    } else {
    soln = solveSudoku(board);
    setBoard(board);
    }


    // var goodBoard = validBoard(board);
    // if (goodBoard) {
    //     console.log("Yes");
    // } else {
    //     console.log("no");
    // }
}

function solveSudoku(board) {
    if (solved(board)) {
        console.log(board);
        return board;
    } else {
        const states = makeBoards(board);
        const validStates = keepValid(states);
        return sudokuDFS(validStates);
    }

}

function solved(board) {
    for (var i = 0; i < 9; i++) {
        for(var j = 0; j < 9; j++) {
            if (board[i][j] == "-") {
                return false;
            }
        }
    }
    if (!validBoard(board)) {
        console.log("Nah g")
        return false;
    }
    return true;
}

function sudokuDFS(boards) {
    if (boards.length < 1) {
        return false;
    } else {
        var first = boards.shift();
        const currPath = solveSudoku(first);
        if (currPath != false) {
            return currPath;
        } else {
            return sudokuDFS(boards);
        }
    }
}

function makeBoards(board) {
    var out = [];
    const firstOp = findEmpty(board);
    if (firstOp != undefined) {
        const y = firstOp[0];
        const x = firstOp[1];
        for (var i = 1; i <= 9; i++) {
            var nextBoard = [...board];
            var row = [...nextBoard[y]];
            row[x] = i.toString();
            nextBoard[y] = row;
            out.push(nextBoard);
        }
    }
    return out;
}

function keepValid(boards) {
    var out = [];
    for (var i = 0; i < boards.length; i++) {
        if (validBoard(boards[i])) {
            out.push(boards[i]);
        }
    }
    return out;
}

function findEmpty(board) {
    for (var r = 0; r < 9; r++) {
        for (var c = 0; c < 9; c++) {
            if (board[r][c] == "-") {
                return [r, c];
            }
        }
    }
}


function validBoard(board) {
    return validRows(board) && validCols(board) && validBoxes(board);
}

function validRows(board) {
    for (var i = 0; i < 9; i++) {
        var curr = [];
        for (var j = 0; j < 9; j++) {
            if (curr.includes(board[i][j])) {
                return false;
            } else if (board[i][j] != "-") {
                curr.push(board[i][j]);
            }
        }
    }
    return true;
}

function validCols(board) {
    for (var i = 0; i < 9; i++) {
        var curr = [];
        for (var j = 0; j < 9; j++) {
            if (curr.includes(board[j][i])) {
                return false;
            } else if(board[j][i] != "-") {
                curr.push(board[j][i]);
            }
        }
    }
    return true;

}

function validBoxes(board) {
    const boxCoords = [
        [0, 0], [0, 1], [0, 2],
        [1, 0], [1, 1], [1, 2],
        [2, 0], [2, 1], [2, 2]
    ];

    for (var y = 0; y < 9; y +=3) {
        for (var x = 0; x < 9; x += 3) {

            var curr = [];
            for (var i = 0; i < 9; i++) {
                var coord = [...boxCoords[i]];
                coord[0] += y;
                coord[1] += x;
                if (curr.includes(board[coord[0]][coord[1]])) {
                    return false;
                } else if (board[coord[0]][coord[1]] != "-") {
                    curr.push(board[coord[0]][coord[1]]);
                }
            }
        }
    }
    return true;
}

function setBoard(board) {
    //Setting the actual board
    for (let j = 0; j < 9; j++) {
        for (let k = 0; k < 9; k++) {
            let tile = document.createElement("div");
            tile.id = j.toString() + "-" + k.toString();
            if (board[j][k] != "-") {
                tile.innerText = board[j][k];
                tile.classList.add("start-num");
            }

            if (j == 2 || j == 5) {
                tile.classList.add("hor-line");
            }
            if (k == 2 || k == 5) {
                tile.classList.add("vert-line");
            }
            tile.addEventListener("click", placeNum);
            tile.classList.add("tile");
            document.getElementById("board").append(tile);
        }
    }

    //setting the num options
    for (let i = 1; i <= 9; i++) {
        let num = document.createElement("div");
        num.id = i;
        num.innerText = i;
        num.addEventListener("click", useNum);
        num.classList.add("number");
        document.getElementById("nums").appendChild(num);
    }

    //Solve Button
    // var solBbutton = document.createElement("button");
    // solBbutton.innerHTML = "Solve"
    // document.body.appendChild(solBbutton);
    // solBbutton.classList.add("solButton");
    // solBbutton.addEventListener("click", solveBoard);


    //Reset Button
    // var resButton = document.createElement("button");
    // resButton.innerHTML = "Reset";
    // document.body.append(resButton);
    // resButton.classList.add("resButton");
    // resButton.addEventListener("click", solveBoard);


}

function useNum() {
    if (currNum != null) {
        currNum.classList.remove("selected-num");
    }
    currNum = this;
    currNum.classList.add("selected-num");
}

function placeNum() {
    if (currNum) {
        if (this.innerText != "") {
            return;
        }
        let coord = this.id.split("-");
        let row = parseInt(coord[0]);
        let column = parseInt(coord[1]);

        if (soln[row][column] == currNum.id) {
            this.innerText = currNum.id;
        } else {
            errs++;
            document.getElementById("err").innerText = errs;
        }
    }
}

function solveBoard() {
    for (let j = 0; j < 9; j++) {
        for (let k = 0; k < 9; k++) {
            var posID = parseInt(j) + "-" + parseInt(k);
            var pos = document.getElementById(posID);
            if (pos.innerText == "") {
                pos.innerText = soln[j][k];
            }
        }
    }
}

function resetBoard() {
    for (let j = 0; j < 9; j++) {
        for (let k = 0; k < 9; k++) {
            var posID = parseInt(j) + "-" + parseInt(k);
            var pos = document.getElementById(posID);
            if (pos.innerText != "") {
                if (board[j][k] != "-") {
                    pos.innerText = board[j][k];
                } else {
                    pos.innerText = " ";
                }
            }
        }
    }

    errs = 0;
    document.getElementById("err").innerText = errs;
}

module.exports = {
    validBoard
}