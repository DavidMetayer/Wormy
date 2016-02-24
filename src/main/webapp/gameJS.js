/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var row = "";
for (a = 0; a < 50; a++) {
    row += "<tr>"
    for (b = 0; b < 50; b++) {
        coord = a + b;
        row += "<td id=\"" + coord + "\">*</td>";
    }
    row += "</tr>";
}

document.getElementById("board").innerHTML = row;