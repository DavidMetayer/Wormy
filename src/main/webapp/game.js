/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var row = "";
            var coord = 0;
            for (var a = 0; a < 50; a++) {
                row += "<tr>";
                for (var b = 0; b < 50; b++) {
                    row += "<td id=\"g" + coord + "\"></td>";
                    coord += 1;
                }
                row += "</tr>";
            }
            document.getElementById("board").innerHTML = row;
            
            // Key Events
            // Have to send key data to Java code to determine what action to take.
            //TODO: Worm redWorm = new Worm();
            //TODO: Worm cyanWorm = new Worm();
            //TODO: Pellet eat = new Pellet();
            //TODO: start GameLoop; -- Always check for endGame();
            //TODO: Spawn Pellet
            //TODO: Start moving worms
            
            var LEFT_KEY = 37;
    
            document.onkeydown = function(e) {
                e = e || window.event;
                switch(e.which || e.keyCode) {
                    case LEFT_KEY: // Left
                        //TODO: call moveLeft();
                        //TODO: getCoordinate()-1;
                        var head = this.getElementById((this.getCoordinate()-1));
                        document.getElementById(head).style.background-color(this.getColor());
                    break;

                    case 38: // Up
                        //TODO: call moveUp();
                        var head = this.getElementById((this.getCoordinate()-50));
                        document.getElementById(head).style.background-color(this.getColor());
                    break;

                    case 39: // Right
                        //TODO: call moveRight();
                        var head = this.getElementById((this.getCoordinate()+1));
                        document.getElementById(head).style.background-color(this.getColor());
                    break;

                    case 40: // Down
                        //TODO: call moveDown();
                        var head = this.getElementById((this.getCoordinate()-50));
                        document.getElementById(head).style.background-color(this.getColor());
                    break;

                    default: return; // Exit this handler for other keys
                }
                e.preventDefault(); // Prevent the default action (scroll / move caret)
                
                
                //TODO end GameLoop;
            };

